package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Board;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	// 로그객체
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	// BoardDAO 인터페이스 생성
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public int insert(Board board) {
		// TODO Auto-generated method stub
		return bdao.insert(board);
	}

	@Override
	public List<Board> getPageList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getPageList(pgvo);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getTotal(pgvo);
	}

	@Override
	public Board getDetail(int bno) {
		// TODO Auto-generated method stub
		return bdao.getDetail(bno);
	}

	@Override
	public int update(Board board) {
		// TODO Auto-generated method stub
		return bdao.update(board);
	}

	@Override
	public int delete(int bno) {
		// TODO Auto-generated method stub
		return bdao.delete(bno);
	}
}
