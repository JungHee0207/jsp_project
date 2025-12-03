package repository;

import java.util.List;

import domain.Comment;

public interface commentDAO {

	int insert(Comment c);

	List<Comment> getList(int bno);

	int delete(int cno);

	int update(Comment c);

}
