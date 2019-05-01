package com.kh.somoim.club.model.vo;

import java.io.Serializable;
import java.util.Date;

public class BoardResponseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5373661782343512093L;
	private int writingNumber;			//글 번호 
	private int clubNumber;				//소모임 번호 
	private String title;				//글 제목  
	private String content;				//글 내용  
	private String writer;				//작성자(한글)
	private Date writeDay;				//작성일
	private String boardSelect;			//게시판 선택 
	private String imagePath;			//이미지 경로 
	
	public BoardResponseVO() {
		// TODO Auto-generated constructor stub
	}

	public BoardResponseVO(int writingNumber, int clubNumber, String title, String content, String writer,
			Date writeDay, String boardSelect, String imagePath) {
		super();
		this.writingNumber = writingNumber;
		this.clubNumber = clubNumber;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writeDay = writeDay;
		this.boardSelect = boardSelect;
		this.imagePath = imagePath;
	}

	/**
	 * @return the writingNumber
	 */
	public int getWritingNumber() {
		return writingNumber;
	}

	/**
	 * @param writingNumber the writingNumber to set
	 */
	public void setWritingNumber(int writingNumber) {
		this.writingNumber = writingNumber;
	}

	/**
	 * @return the clubNumber
	 */
	public int getClubNumber() {
		return clubNumber;
	}

	/**
	 * @param clubNumber the clubNumber to set
	 */
	public void setClubNumber(int clubNumber) {
		this.clubNumber = clubNumber;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the writer
	 */
	public String getWriter() {
		return writer;
	}

	/**
	 * @param writer the writer to set
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}

	/**
	 * @return the writeDay
	 */
	public Date getWriteDay() {
		return writeDay;
	}

	/**
	 * @param writeDay the writeDay to set
	 */
	public void setWriteDay(Date writeDay) {
		this.writeDay = writeDay;
	}

	/**
	 * @return the boardSelect
	 */
	public String getBoardSelect() {
		return boardSelect;
	}

	/**
	 * @param boardSelect the boardSelect to set
	 */
	public void setBoardSelect(String boardSelect) {
		this.boardSelect = boardSelect;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BoardResponseVO [writingNumber=" + writingNumber + ", clubNumber=" + clubNumber + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", writeDay=" + writeDay + ", boardSelect="
				+ boardSelect + ", imagePath=" + imagePath + "]";
	}
	
}
