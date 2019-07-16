// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyBlogServiceImpl.java

package kr.or.connect.ROOT.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.ROOT.dao.BoardDao;
import kr.or.connect.ROOT.dto.BoardDto;
import kr.or.connect.ROOT.dto.CommentDto;
import kr.or.connect.ROOT.dto.GoodDto;
import kr.or.connect.ROOT.dto.SelectListDto;
import kr.or.connect.ROOT.service.MyBlogService;

@Service
public class MyBlogServiceImpl implements MyBlogService{

	@Autowired
	BoardDao boarddao;
	
	@Override
	@Transactional(readOnly = false)
    public BoardDto addBoard(BoardDto boarddto)
    {
        boarddao.insert_Board(boarddto);
        return boarddto;
    }

	@Override
	@Transactional(readOnly = false)
    public CommentDto addComment(CommentDto commentdto)
    {
        boarddao.insert_Comment(commentdto);
        int bCommentCount = boarddao.select_All_Comment_Count(commentdto.getbId());
        boarddao.update_Comment_Count(bCommentCount, commentdto.getbId());
        return commentdto;
    }

	@Override
	@Transactional(readOnly = false)
    public GoodDto addGoodAccount(GoodDto gooddto)
    {
        boarddao.insert_Good(gooddto);
        return gooddto;
    }

	@Override
	@Transactional(readOnly = false)
    public List<BoardDto> getBoard(int nowPageNum, int bType, String sContent)
    {
        int startRow = (nowPageNum - 1) * MyBlogService.LIMIT;
        
        String result = "%" + sContent + "%";
        
        List<BoardDto> list = boarddao.select_All_Board(startRow, bType, result, MyBlogService.LIMIT);
        
        return list;
    }

	@Override
	@Transactional(readOnly = false)
    public List<CommentDto> getComment(int cnowPageNum, int bId)
    {
        int startRow = (cnowPageNum - 1) * 20;
        List<CommentDto> list = boarddao.select_All_Comment(startRow, bId, 20);
        return list;
    }

	@Override
	@Transactional(readOnly = false)
    public List<GoodDto> getGoodAccount(int bId, String gAccount)
    {
        List<GoodDto> list = boarddao.select_Good_Account(bId, gAccount);
        return list;
    }

	@Override
	@Transactional(readOnly = false)
    public List<SelectListDto> getSelectList(int checkNum)
    {
        List<SelectListDto> list = boarddao.select_Select_List(checkNum);
        return list;
    }

	@Override
	@Transactional(readOnly = false)
    public BoardDto getviewContent(int bId)
    {
        BoardDto vieContent = boarddao.select_All_Content(bId);
        return vieContent;
    }

	@Override
	@Transactional(readOnly = false)
    public int getBoardCount(int bType, String sContent)
    {
        String result = "%" + sContent + "%";
        int count = boarddao.select_All_Board_Count(bType, result);
        return count;
    }

	@Override
	@Transactional(readOnly = false)
    public int getCommentCount(int bId)
    {
        int count = boarddao.select_All_Comment_Count(bId);
        return count;
    }

	@Override
	@Transactional(readOnly = false)
    public int getGoodCount(int bId)
    {
        int goodCount = boarddao.select_All_Good_Count(bId);
        return goodCount;
    }

	@Override
	@Transactional(readOnly = false)
    public BoardDto modifyBoard(BoardDto boarddto)
    {
        boarddao.update_Board(boarddto);
        return boarddto;
    }

	@Override
	@Transactional(readOnly = false)
    public CommentDto modifyComment(CommentDto commentdto)
    {
        boarddao.update_Comment(commentdto);
        return commentdto;
    }

	@Override
	@Transactional(readOnly = false)
    public void modifyGoodCount(int checkNum, int bId)
    {
        boarddao.update_Good_Count(checkNum, bId);
    }

	@Override
	@Transactional(readOnly = false)
    public int deleteBoard(int bId)
    {
        int delete = boarddao.delete_Board(bId);
        return delete;
    }

	@Override
	@Transactional(readOnly = false)
    public int deleteComment(int bId, int cId)
    {
        int delete = boarddao.delete_Comment(bId, cId);
        int bCommentCount = boarddao.select_All_Comment_Count(bId);
        boarddao.update_Comment_Count(bCommentCount, bId);
        return delete;
    }

	@Override
	@Transactional(readOnly = false)
    public int deleteGoodAccount(int bId, String gAccount)
    {
        int delete = boarddao.delete_Good_Account(bId, gAccount);
        return delete;
    }

}
