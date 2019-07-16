// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyBlogService.java

package kr.or.connect.ROOT.service;

import java.util.List;
import kr.or.connect.ROOT.dto.*;

public interface MyBlogService
{
    public static final int LIMIT = 5;
    public static final int COMMENT_LIMIT = 20;
    public static final int PAGE_BLOCK = 5;
    public static final int COMMENT_PAGE_BLOCK = 10;

    public abstract BoardDto addBoard(BoardDto boarddto);

    public abstract CommentDto addComment(CommentDto commentdto);

    public abstract GoodDto addGoodAccount(GoodDto gooddto);

    public abstract List<BoardDto> getBoard(int nowPageNum, int bType, String sContent);

    public abstract List<CommentDto> getComment(int cnowPageNum, int bId);

    public abstract List<GoodDto> getGoodAccount(int bId, String gAccount);

    public abstract List<SelectListDto> getSelectList(int checkNum);

    public abstract BoardDto getviewContent(int bId);

    public abstract int getBoardCount(int bType, String sContent);

    public abstract int getCommentCount(int bId);

    public abstract int getGoodCount(int bId);

    public abstract BoardDto modifyBoard(BoardDto boarddto);

    public abstract CommentDto modifyComment(CommentDto commentdto);

    public abstract void modifyGoodCount(int checkNum, int bId);

    public abstract int deleteBoard(int bId);

    public abstract int deleteComment(int bId, int cId);

    public abstract int deleteGoodAccount(int bId, String gAccount);

}
