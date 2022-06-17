package com.yyh.web.board;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class BoardDTO {
	private int seq;
	private String writer;
	private String title;
	private String content;
	private Timestamp regDate;
	private int readCount;
	private int ref;
	private int re_step;
	private int re_level;

	private String search_condition;
	private String search_keyword;
}
