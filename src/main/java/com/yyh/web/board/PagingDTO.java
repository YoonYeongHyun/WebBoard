package com.yyh.web.board;

import lombok.Data;

@Data
public class PagingDTO {
	private int pageNum, startPage, endPage, cntWriting, lastPage, cntPerPage, start, end;
	private String search_condition;
	private String search_keyword;
	private int pageScale = 10;
	

	public PagingDTO() {
		
	}
	public PagingDTO(int total, int nowPage, int cntPerPage, String search_condition, String search_keyword) {
		setPageNum(nowPage);
		setCntPerPage(cntPerPage);
		setCntWriting(total);
		setSearch_condition(search_condition);
		setSearch_keyword(search_keyword);
		calcLastPage(getCntWriting(), getCntPerPage());
		calcStartEndPage(getPageNum(), pageScale);
		calcStartEnd(getPageNum(), getCntPerPage());
	}
	
	public void calcLastPage(int total, int cntPerPage) {
		setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
	}

	public void calcStartEndPage(int nowPage, int cntPage) {
		setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
		if (getLastPage() < getEndPage()) {
			setEndPage(getLastPage());
		}
		setStartPage(getEndPage() - cntPage + 1);
		if (getStartPage() < 1) {
			setStartPage(1);
		}
	}
	// DB 쿼리에서 사용할 start, end값 계산
	public void calcStartEnd(int nowPage, int cntPerPage) {
		setEnd(nowPage * cntPerPage);
		setStart(getEnd() - cntPerPage + 1);
	}
}