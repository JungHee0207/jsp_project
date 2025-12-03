package handler;

import domain.PagingVO;

public class PagingHandler {
	
	// DB/서버에서는 필요하지 않지만 다른쪽에서 (화면/처리가) 필요한 경우
	// DB 에서 필요한 객체는 VO  => domain
	// 데이터 전달용 DTO  => domain
	// 그 외 모든 처리는 handler 패키지로 저장
	
	// list 하단에 페이지네이션의 핸들링을 위한 클래스
	// < 1 2 3 4 5 6 7 8 9 10 >
	private int startPage; // 현재 화면의 페이지네이션의 시작번호 1  11  21  31 ...
	private int endPage; // 현재 화면의 페이지네이션의 끝번호    10  20  30  40 ...
	private boolean prev; // < 이전 페이지의 존재여부
	private boolean next; // > 다음 페이지의 존재여부
	private int realEndPage; // 정말 마지막 페이지
	
	// 컨트롤러에서 받아오기
	private PagingVO pgvo; 
	private int totalCount; // DB에서 검색해오기
	
	public PagingHandler (PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		// pageNo 1~10  => 10
		// pageNo 11~20 => 20
		// pageNo / 10 => 0.1  0.2  0.3 ... => 올림 1 * 10
		
		// 정수 / 정수 = 정수
		this.endPage = (int)(Math.ceil((double)this.pgvo.getPageNo() / 10)*10);
		this.startPage = this.endPage - 9;
		
		// realEndPage 205 / 10 => 20.5
		// 전체 게시글 수 / 한 페이지에 표시되는 게시글 수 qty => 올림
		// 소수점의 값이 남으면 무조건 한페이지가 더 필요
		this.realEndPage = (int)Math.ceil((double)this.totalCount / this.pgvo.getQty());
		
		// 실제 리얼 마지막 페이지가 21페이지라면 1~10 11~20 21  30
		// endPage 변경
		if(this.realEndPage < this.endPage) {
			this.endPage = this.realEndPage;
		}
		
		// 이전 다음 유무
		this.prev = this.startPage > 1;// startPage = 1  11  21
		this.next = this.endPage < this.realEndPage; 
		
	}
	
	

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public PagingVO getPgvo() {
		return pgvo;
	}

	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getRealEndPage() {
		return realEndPage;
	}



	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}


	@Override
	public String toString() {
		return "PagingHandler [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", realEndPage=" + realEndPage + ", pgvo=" + pgvo + ", totalCount=" + totalCount + "]";
	}


}
