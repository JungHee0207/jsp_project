package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Comment;
import orm.DatabasesBuilder;

public class commentDAOImpl implements commentDAO {
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	private SqlSession sql;
	
	public commentDAOImpl() {
		new DatabasesBuilder();
		sql = DatabasesBuilder.getFactory().openSession();
	}

	@Override
	public int insert(Comment c) {
		int isOk = sql.insert("commentMapper.insert", c);
		if(isOk >0) sql.commit();
		return isOk;
	}

	@Override
	public List<Comment> getList(int bno) {
		
		return sql.selectList("commentMapper.List", bno);
	}

	@Override
	public int delete(int cno) {
		int isOk = sql.delete("commentMapper.delete", cno);
		if(isOk >0) sql.commit();
		return isOk;
	}

	@Override
	public int update(Comment c) {
		int isOk = sql.update("commentMapper.update", c);
		if(isOk >0) sql.commit();
		return isOk;
	}
}
