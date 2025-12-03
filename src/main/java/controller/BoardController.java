package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Board;
import domain.PagingVO;
import handler.FileRemoveHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	private RequestDispatcher rdp;
	private String destPage;
	
	private BoardService bsv;		// 인터페이스
	
    public BoardController() {
        bsv = new BoardServiceImpl();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info(">>> BoardController Service Test >>");
		
		// post로 들어오는 객체는 한글깨짐발생 => 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 응답 객체의 contentType 설정 => jsp 파일로 응답 (동기방식)
		response.setContentType("text/html; charset=UTF-8");
		
		// jsp에서 요청하는 주소를 받는 객체  /brd/register
		String uri = request.getRequestURI();
		log.info(">>> {}", uri);
		
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>> {}", path);
		
		switch(path) {
		case "register":
			destPage = "/board/register.jsp";
			resForword(request, response);			
			break;
		case "insert" :
			try {
				// 첨부파일이 있는 경우 수정코드
				// image 저장 + DB 저장
				// 파일 업로드 시 사용할 물리적인 경로를 설정
				String savePath = getServletContext().getRealPath("/_fileUpload");
				log.info(">>>> savePath>> {}", savePath);
				
				// 파일 객체 생성
				File fileDir = new File(savePath);
				log.info(">>>> fileDir>> {}", fileDir);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				// 파일 저장 경로 => repository
				fileItemFactory.setRepository(fileDir);
				// 파일 저장시 사용할 임시 메모리 공간
				fileItemFactory.setSizeThreshold(1024*1024*3); // 계산가능
				
				Board board = new Board();
				
				// multipart/form-data 형식으로 넘어온 객체를 
				// 다루기 쉽게 변환해주는 클래스
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				
				log.info(">>>> itemList>> {}", itemList);	
				
				for(FileItem item : itemList) {
					// FieldName
					// title, writer, content => text
					// imagefile => image (file) => 경로 주소 필요
					switch(item.getFieldName()) {
					case "title": 
						// 바이트 형태로 풀어져서 전송 => 다시 텍스트로 조합 utf-8 인코딩 해서 조립
						String title = item.getString("UTF-8");
						board.setTitle(title);
						break;
					case "writer": 
						board.setWriter(item.getString("UTF-8"));
						break;
					case "content": 
						board.setContent(item.getString("UTF-8"));
						break;
					case "imagefile": 
						// 이미지 파일 여부 체크
						if(item.getSize() > 0) {
							// 이름 추출
							String fileName = item.getName();
							// 파일이름은 내부에서 구분하기 쉽게 파일의 고유번호를 붙여서 관리
							// UUID  / 시스템의 현재 시간을 이용하여 구분
							fileName = System.currentTimeMillis()+"_"+fileName;
							
							// 경로(fileDir) + 파일 구분자 + 파일이름(fileName)
							// 파일 구분자 (경로기호) => 운영체제마다 다름  / \
							// File.separator : 파일 경로 기호
							File uploadFile = new File(fileDir+File.separator+fileName);
							log.info(">>>> uploadFile >> {}", uploadFile);
							
							// 저장
							try {
								item.write(uploadFile);
								board.setImagefile(fileName); // 저장되는 모든 경로는 동일
								
								// 썸네일 작업
								// list 페이지에서 트레픽 과다 사용 방지 (연결 시간지연 방지)
								// 이미지만 가능
								Thumbnails.of(uploadFile).size(75, 75).toFile(new File(fileDir+File.separator+"th_"+fileName));
								
								destPage="list";
								response.sendRedirect("list");
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								log.info(">> file write on disk error");
							}
							
							
						}
						break;
					} // switch end
				}// for end
				
				// boardService 객체로 해당 객체를 전달
				int isOk = bsv.insert(board);
				
				// DB에서 저장이 잘 완료되면 1이 리턴, 안되면 0이 리턴
				log.info(">>> insert {}", (isOk > 0)? "성공" : "실패");
				
				destPage="list";
				response.sendRedirect("list");
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		case "list":
			try {
				// DB의 전체 리스트를 요청
				// 페이징을 해서 리스트 생성
				// List<Board> list = bsv.getList();
				PagingVO pgvo = new PagingVO(); // pageNo=1, qty=10
				
				if(request.getParameter("pageNo") != null) {
					int pageNo = Integer.parseInt(request.getParameter("pageNo"));
					int qty = Integer.parseInt(request.getParameter("qty"));
					String type = request.getParameter("type");
					String keyword = request.getParameter("keyword");
					
					pgvo = new PagingVO(pageNo, qty, type, keyword);
				}
				
				// select * from board order by bno desc limit #{pageStart}, #{qty}
				List<Board> list = bsv.getPageList(pgvo);
				log.info(">>> list {}", list);
				
				// select count(bno) from board
				int totalCount = bsv.getTotal(pgvo);
				
				PagingHandler ph = new PagingHandler(pgvo, totalCount);
				log.info(">>> ph >> {}", ph);
				
				// list를 jsp로 보내기
				request.setAttribute("list", list);
				request.setAttribute("ph", ph);
				destPage="/board/list.jsp";
				resForword(request, response);
						
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "detail": case "modify":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				
				Board board = bsv.getDetail(bno);
				log.info(">>> detail {}", board);
				request.setAttribute("b", board);
				destPage="/board/"+ path +".jsp";
				resForword(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "update":
			try {
				// 이미지 있는 경우
				String savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				int size = 1024*1024*3;
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(size, fileDir);
				
				Board board = new Board();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				
				String old_file = null; // 기존 이미지 파일이 있으면 저장
				
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "bno": 
						board.setBno(Integer.parseInt(item.getString("UTF-8")));
						break;
					case "title": 
						board.setTitle(item.getString("UTF-8"));
						break;
					case "content": 
						board.setContent(item.getString("UTF-8"));
						break;
					case "imagefile": 
						old_file = item.getString("UTF-8");
						break;
					case "newFile": 
						// 새로 추가되는 파일이 있으면... 
						if(item.getSize() > 0) {
							// 새로 추가되는 파일 있는 경우
							if(old_file != null) {
								// old_file 삭제작업
								// fileRemoveHandler를 통해서 파일 삭제 작업 진행
								FileRemoveHandler fh = new FileRemoveHandler();
								boolean isDel = fh.deleteFile(savePath, old_file);
								
							}
							// 새파일 등록 작업
							String fileName = System.currentTimeMillis()+"_"+item.getName();
							
							// 경로 + 구분자 + 파일이름
							File uploadFile = new File(fileDir+File.separator+fileName);
							try {
								item.write(uploadFile);  // 저장
								board.setImagefile(fileName);
								
								Thumbnails.of(uploadFile)
									.size(75, 75)
									.toFile(new File(fileDir+File.separator+"th_"+fileName));
								
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								log.info("file write update error");
							}
							
							
							
						}else {
							// 새로 추가되는 파일이 없는 경우
							board.setImagefile(old_file);
						}
						
						break;
					}
				}
				int isOk = bsv.update(board);
			
				log.info(">>> update {}", (isOk > 0)? "성공" : "실패");
				response.sendRedirect("detail?bno="+board.getBno());
				
				//destPage = "detail?bno="+bno;
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
			
		case "remove":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				int isOk = bsv.delete(bno);
				log.info(">>> delete {}", (isOk > 0)? "성공" : "실패");
				response.sendRedirect("list");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		}
	}

	private void resForword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 처리가 완료된 만들어진 응답객체를 jsp화면으로 보내기
		// RequestDispatcher 데이터를 전달하는 객체 / 어디로 destPage로...
		rdp = request.getRequestDispatcher(destPage);
		// 요청한 객체를 가지고 destPage에 적힌 주소로 이동 (forword)
		rdp.forward(request, response);		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
