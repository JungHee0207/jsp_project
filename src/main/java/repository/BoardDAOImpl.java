package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Board;
import domain.PagingVO;
import orm.DatabasesBuilder;

public class BoardDAOImpl implements BoardDAO {
	//로그객체
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	private SqlSession sql;
	
	public BoardDAOImpl() {
		new DatabasesBuilder();
		sql = DatabasesBuilder.getFactory().openSession();
	}

	@Override
	public int insert(Board board) {
		int isOk = sql.insert("boardMapper.insert", board);
		sql.commit();
		return isOk;
	}

	@Override
	public List<Board> getPageList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return sql.selectList("boardMapper.getPageList", pgvo);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return sql.selectOne("boardMapper.getTotal", pgvo);
	}

	@Override
	public Board getDetail(int bno) {
		
		return sql.selectOne("boardMapper.getDetail", bno);
	}

	@Override
	public int update(Board board) {
		int isOk = sql.update("boardMapper.update", board);
		sql.commit();
		return isOk;
	}

	@Override
	public int delete(int bno) {
		int isOk = sql.delete("boardMapper.delete", bno);
		sql.commit();
		return isOk;
	}
}
