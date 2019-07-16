// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoardDaoSql.java

package kr.or.connect.ROOT.dao;


public class BoardDaoSql
{

    public BoardDaoSql()
    {
    }

    public static final String INIT_SELECT_BOARD_LIST = "select * from myblog_board where bType != 12 and bType != 13 and (bContent like :sContent or bTitle like :sContent) order by bId desc limit :startRow, :limit";
    public static final String SELECT_BOARD_LIST = "select * from myblog_board where bType = :bType and (bContent like :sContent or bTitle like :sContent) order by bId desc limit :startRow, :limit ";
    public static final String SELECT_BOARD_LIST_ZERO = "select * from myblog_board where (bContent like :sContent or bTitle like :sContent) order by bId desc limit :startRow, :limit";
    public static final String SELECT_COMMENT_LIST = "select * from myblog_comment where bId = :bId order by cId desc limit :cnowPageNum, :limit";
    public static final String INIT_SELECT_ALL_COUNT = "select count(*) from myblog_board where bType != 12 and bType != 13 and (bContent like :sContent or bTitle like :sContent)";
    public static final String SELECT_ALL_COUNT = "select count(*) from myblog_board where bType = :bType and (bContent like :sContent or bTitle like :sContent)";
    public static final String SELECT_ALL_COUNT_ZERO = "select count(*) from myblog_board where bContent like :sContent or bTitle like :sContent";
    public static final String SELECT_BOARD_VIEW = "select * from myblog_board where bId = :bId";
    public static final String SELECT_COMMENT_ALL_COUNT = "select count(*) from myblog_comment where bId = :bId";
    public static final String SELECT_SELECT_LIST = "select * from myblog_selectlist where liType = 1 order by liValue asc";
    public static final String SELECT_SELECT_ALL_LIST = "select * from myblog_selectlist order by liValue asc";
    public static final String SELECT_GOOD_COUNT = "select bGood from myblog_board where bId = :bId";
    public static final String UPDATE_BOARD = "update myblog_board set bType = :bType, bTitle = :bTitle, bContent = :bContent, bMod_Date = current_timestamp, bImgTag = :bImgTag where bId = :bId";
    public static final String UPDATE_COMMENT = "update myblog_comment set cContent = :cContent, cMod_Date = current_timestamp where bId = :bId and cId = :cId";
    public static final String UPDATE_COMMENT_COUNT = "update myblog_board set bCommentCount = :bCommentCount where bId = :bId";
    public static final String INCREMENT_GOOD_COUNT = "update myblog_board set bGood = bGood + 1 where bId = :bId";
    public static final String DECREMENT_GOOD_COUNT = "update myblog_board set bGood = bGood - 1 where bId = :bId";
    public static final String DELETE_BOARD = "delete from myblog_board where bId = :bId";
    public static final String DELETE_COMMENT = "delete from myblog_comment where bId = :bId and cId = :cId";
}
