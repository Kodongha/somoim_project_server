package com.kh.somoim.club.model.vo;

import java.io.Serializable;
import java.util.Date;

public class BoardVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5373661782343512093L;
	private int writingNumber;			//�� ��ȣ 
	private int clubNumber;				//�Ҹ��� ��ȣ 
	private String title;				//�� ����  
	private String content;				//�� ����  
	private int writer;				//�ۼ���  
	private Date writeDay;				//�ۼ���
	private String boardSelect;			//�Խ��� ���� 
	private String imagePath;			//�̹��� ��� 
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
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
	public int getWriter() {
		return writer;
	}

	/**
	 * @param writer the writer to set
	 */
	public void setWriter(int writer) {
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
		return "BoardVO [writingNumber=" + writingNumber + ", clubNumber=" + clubNumber + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", writeDay=" + writeDay + ", boardSelect="
				+ boardSelect + ", imagePath=" + imagePath + "]";
	}
	
	
	
}
