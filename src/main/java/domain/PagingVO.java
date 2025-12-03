package domain;

public class PagingVO {
	
	// 페이지 처리를 위해 DB에서 필요한 객체를 만들어주는 VO
	// select * from board order by bno desc limit 0, 10;
	// limit 번지, 개수 (한 화면에 출력할 게시글 수)
	// 번지는 0번지부터 시작 => 계산해서 리턴
	
	private int pageNo; // 페이지네이션의 선택 번호
	private int qty; // 한 화면에 출력할 게시글 수 (10개)
	
	// 검색에 필요한 멤버변수 => type / keyword
	private String type;
	private String keyword;
	
	public PagingVO() {
		// 파라미터 없이 첫 리스트를 호출
		this.pageNo = 1;
		this.qty = 10;
	}

	public PagingVO(int pageNo, int qty, String type, String keyword) {
		this.pageNo = pageNo;
		this.qty = qty;
		this.type = type;
		this.keyword = keyword;
	}



	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	// 시작 번지 계산 getter
	// 
	public int getPageStart() {
		// 선택한 페이지네이션 번호(pageNo)에 따라 변경
		// 1 => 0  2 => 10  3 => 20
		return (this.pageNo - 1) * this.qty;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "PagingVO [pageNo=" + pageNo + ", qty=" + qty + ", type=" + type + ", keyword=" + keyword + "]";
	}

}
