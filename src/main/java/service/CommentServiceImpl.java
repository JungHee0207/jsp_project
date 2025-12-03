package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Comment;
import repository.UserDAO;
import repository.UserDAOImpl;
import repository.commentDAO;
import repository.commentDAOImpl;

public class CommentServiceImpl implements CommentService {
	// 로그객체
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
		
		// BoardDAO 인터페이스 생성
	private commentDAO cdao;
		
	public CommentServiceImpl() {
		cdao = new commentDAOImpl();
	}
	@Override
	public int insert(Comment c) {
		// TODO Auto-generated method stub
		return cdao.insert(c);
	}
	@Override
	public List<Comment> getList(int bno) {
		// TODO Auto-generated method stub
		return cdao.getList(bno);
	}
	@Override
	public int delete(int cno) {
		// TODO Auto-generated method stub
		return cdao.delete(cno);
	}
	@Override
	public int update(Comment c) {
		// TODO Auto-generated method stub
		return cdao.update(c);
	}

}
