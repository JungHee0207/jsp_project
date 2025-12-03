package repository;

import java.util.List;

import domain.Board;
import domain.PagingVO;

public interface BoardDAO {

	int insert(Board board);

	List<Board> getPageList(PagingVO pgvo);

	int getTotal(PagingVO pgvo);

	Board getDetail(int bno);

	int update(Board board);

	int delete(int bno);

}
