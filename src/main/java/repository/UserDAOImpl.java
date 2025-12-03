package repository;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.User;
import orm.DatabasesBuilder;

public class UserDAOImpl implements UserDAO {
	//로그객체
		private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
		private SqlSession sql;
		
		public UserDAOImpl() {
			new DatabasesBuilder();
			sql = DatabasesBuilder.getFactory().openSession();
		}

		@Override
		public int insert(User user) {
			int isOk = sql.insert("userMapper.insert", user);
			if(isOk> 0) sql.commit();
			return isOk;
		}

		@Override
		public User getUser(User user) {
			// 
			return sql.selectOne("userMapper.getUser", user);
		}

		@Override
		public int lastLoginUpdate(String id) {
			int isOK = sql.update("userMapper.lastLoginUpdate", id);
			if(isOK >0) sql.commit();
			
			return isOK;
		}

		@Override
		public int update(User user) {
			int isOK = sql.update("userMapper.update", user);
			if(isOK >0) sql.commit();
			
			return isOK;
		}

		@Override
		public int delete(String id) {
			int isOK = sql.delete("userMapper.delete", id);
			if(isOK >0) sql.commit();
			
			return isOK;
		}
}
