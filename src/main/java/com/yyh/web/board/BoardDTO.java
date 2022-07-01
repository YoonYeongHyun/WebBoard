package com.yyh.web.board;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

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

	//검색용
	
	//파일 업로드용
	private MultipartFile uploadFile;
	private String uploadFileName;
}
