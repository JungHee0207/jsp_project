package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.User;
import repository.BoardDAO;
import repository.UserDAO;
import repository.UserDAOImpl;

public class UserServiceImpl implements UserService {
	// 로그객체
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	// BoardDAO 인터페이스 생성
	private UserDAO udao;
	
	public UserServiceImpl() {
		udao = new UserDAOImpl();
	}

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		return udao.insert(user);
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return udao.getUser(user);
	}

	@Override
	public int lastLoginUpdate(String id) {
		// TODO Auto-generated method stub
		return udao.lastLoginUpdate(id);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return udao.update(user);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return udao.delete(id);
	}
}
