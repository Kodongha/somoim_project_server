package com.kh.somoim.server.process;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import com.kh.somoim.home.model.vo.BoardVO;
import com.kh.somoim.home.model.vo.ClubVO;

public class BoardProcess {
	
	private BufferedReader br;
	
	public Object getBoardWritingList(Object obj, ClubVO clubVO) {
		BoardVO requestBoardVO = (BoardVO)obj;
		
		ArrayList<BoardVO> boardWritingList = new ArrayList<BoardVO>();
		try {
			br = new BufferedReader(new FileReader("board.txt"));
			String[] tempStringArray;
			
			String line = "";
			while((line = br.readLine()) != null) {
				BoardVO boardVO = new BoardVO();
				
				tempStringArray = line.split("¡×¡×");
				boardVO.setClubNumber(clubVO.getClubNumber());
				boardVO.setTitle(tempStringArray[1]);
				boardVO.setContent(tempStringArray[2]);
				boardVO.setWriter(tempStringArray[3]);
				boardVO.setWriteDay(Date.parse(tempStringArray[4]));
				boardVO.
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return boardWritingList;
	}

}
