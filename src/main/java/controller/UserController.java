package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.User;
import service.UserService;
import service.UserServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	// 요청에 대한 응답 데이터를 jsp 전송하는 역할
	private RequestDispatcher rdp;
	private String destPage;
	
	private UserService usv;  // interface
       
    public UserController() {
        usv = new UserServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실제 처리 메서드
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//uri 경로
		String uri = request.getRequestURI(); 
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>>> path >> {}",path);
		
		switch(path) {
		case "register":
			destPage="/member/register.jsp";
			forwordMethod(request, response);
			break;
		case "insert":
			try {
				// jsp에서 보낸 파라미터 값 받기
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				
				User user = new User(id, pwd, email, phone);
				
				int isOk = usv.insert(user);
				
				log.info(">>>> insert user >>{}", (isOk>0)?"성공":"실패");
				destPage = "/index.jsp";
				
				response.sendRedirect(destPage);
				
				//forwordMethod(request, response);
						
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "login":
			try {
				// jsp에서 보낸 파라미터 값 받기
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				
				// id와 pwd가 일치하는 User 객체를 리턴
				User loginUser = usv.getUser(new User(id, pwd));
				log.info(" >>>> loginUser >>{}", loginUser);
				
				// loginUser가 있다면...
				// 모든 jsp에 해당 객체를 인지 => session 객체 저장
				if(loginUser != null) {
					//session 객체에 저장 => 객체 가져오기
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginUser); // ses 객체에 loginUser를 저장
					ses.setMaxInactiveInterval(60*10); //로그인 유지 시간 초단위
					log.info(">>> ses >> {}", ses);
					destPage = "/";
				}else {
					// 로그인 객체가 없다면..
					// index.jsp 페이지로 메시지 전송
					//request.setAttribute("login_msg", "notUser");
					destPage = "/?login_msg=notUser";
				}
				
				response.sendRedirect(destPage);
				// forwordMethod(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "logout":
			try {
				//세션 무효화 (끊기)
				// 세션의 객체 지우기
				HttpSession ses = request.getSession();
				User loginUser = (User)ses.getAttribute("ses"); 
				// 로그인 날짜 기록 => lastLogin
				int isOk = usv.lastLoginUpdate(loginUser.getId());
				
				// 세션에 저장해 놓은 객체 삭제
				ses.removeAttribute("ses");
				//세션 무효화 (끊기)
				ses.invalidate();
				destPage= "/index.jsp";
				
				response.sendRedirect(destPage);
//				forwordMethod(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "modify":
			destPage = "/member/modify.jsp";
			forwordMethod(request, response);
			break;
			
		case "update":
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				
				User user = new User(id, pwd, email, phone);
				
				int isOk = usv.update(user);
				log.info(">>> update isOk >> {}", (isOk>0)?"성공":"실패");
				
				// 세션을 끊고, 다시 로그인할 수 있게 유도
				if(isOk > 0) {
					HttpSession ses = request.getSession();
					ses.removeAttribute("ses");
					ses.invalidate();
					//request.setAttribute("update_msg", "OK");
					destPage = "/?update_msg=OK";
				}else {
					//request.setAttribute("update_msg", "Fail");
					destPage = "modify?update_msg=Fail";
				}
//				forwordMethod(request, response);
				response.sendRedirect(destPage);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "remove":
			try {
				HttpSession ses = request.getSession();
				String id = ((User)ses.getAttribute("ses")).getId();
				
				int isOk = usv.delete(id);
				
				if(isOk > 0) {
					ses.removeAttribute("ses");
					ses.invalidate();
					request.setAttribute("delete_msg", "OK");
					destPage = "/?delete_msg=OK";
				}else {
					request.setAttribute("delete_msg", "Fail");
					destPage = "modify?delete_msg=Fail";
				}
//				forwordMethod(request, response);
				response.sendRedirect(destPage);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		}
		
	}

	private void forwordMethod(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
