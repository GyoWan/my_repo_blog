// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoardDao.java

package kr.or.connect.ROOT.dao;

import static kr.or.connect.ROOT.dao.BoardDaoSql.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.ROOT.dto.BoardDto;
import kr.or.connect.ROOT.dto.CommentDto;
import kr.or.connect.ROOT.dto.GoodDto;
import kr.or.connect.ROOT.dto.SelectListDto;

@Repository
public class BoardDao
{
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private SimpleJdbcInsert insertAction_Comment;
    private SimpleJdbcInsert insertAction_Good;
    private RowMapper<BoardDto> rowMapper = BeanPropertyRowMapper.newInstance(BoardDto.class);
    private RowMapper<SelectListDto> li_rowMapper = BeanPropertyRowMapper.newInstance(SelectListDto.class);
    private RowMapper<CommentDto> co_rowMapper = BeanPropertyRowMapper.newInstance(CommentDto.class);
    private RowMapper<GoodDto> good_rowMapper = BeanPropertyRowMapper.newInstance(GoodDto.class);

    public BoardDao(DataSource dataSource)
    {
       
        jdbc = new NamedParameterJdbcTemplate(dataSource);
        insertAction = new SimpleJdbcInsert(dataSource)
        		.withTableName("myblog_board")
        		.usingGeneratedKeyColumns("bId");
        
        insertAction_Comment = new SimpleJdbcInsert(dataSource)
        		.withTableName("myblog_comment")
        		.usingGeneratedKeyColumns("cId");
        
        insertAction_Good = new SimpleJdbcInsert(dataSource)
        		.withTableName("myblog_good").
        		usingGeneratedKeyColumns("gId");
    }

    public int insert_Board(BoardDto boarddto)
    {
        SqlParameterSource params = new BeanPropertySqlParameterSource(boarddto);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public int insert_Comment(CommentDto commentdto)
    {
        SqlParameterSource params = new BeanPropertySqlParameterSource(commentdto);
        return insertAction_Comment.executeAndReturnKey(params).intValue();
    }

    public int insert_Good(GoodDto gooddto)
    {
        SqlParameterSource params = new BeanPropertySqlParameterSource(gooddto);
        return insertAction_Good.executeAndReturnKey(params).intValue();
    }

    public List<BoardDto> select_All_Board(int startRow, int bType, String sContent, int limit)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        
        if(bType != 0) {
            params.put("bType", bType);
        }
        
        params.put("sContent", sContent);
        params.put("limit", limit);
        if(bType == 0) {
            return jdbc.query(SELECT_BOARD_LIST_ZERO, params, rowMapper);
        }else if(bType == 100) {
            return jdbc.query(INIT_SELECT_BOARD_LIST, params, rowMapper);
        }else {
            return jdbc.query(SELECT_BOARD_LIST, params, rowMapper);
        }
    }

    public List<CommentDto> select_All_Comment(int cnowPageNum, int bId, int limit)
    {
        Map<String, Integer> params = new HashMap<>();
        params.put("bId", bId);
        params.put("cnowPageNum", cnowPageNum);
        params.put("limit", limit);
        
        return jdbc.query(SELECT_COMMENT_LIST, params, co_rowMapper);
    }

    public List<GoodDto> select_Good_Account(int bId, String gAccount)
    {
        return jdbc.query("select * from myblog_good where bId=" + bId +" and gAccount=" + "'" + gAccount + "'", good_rowMapper);
    }

    public List<SelectListDto> select_Select_List(int checkNum)
    {
        if(checkNum == 1)
            return jdbc.query(SELECT_SELECT_LIST, li_rowMapper);
        else
            return jdbc.query(SELECT_SELECT_ALL_LIST, li_rowMapper);
    }

    public BoardDto select_All_Content(int bId)
    {
        try
        {
            Map<String, Integer> params = new HashMap<>();
            params.put("bId", bId);
            return (BoardDto)jdbc.queryForObject(SELECT_BOARD_VIEW, params, rowMapper);
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public int select_All_Board_Count(int bType, String sContent)
    {
        ;
        try
        {
        	Map<String, Object> params = new HashMap<>();
            params.put("bType", bType);
            params.put("sContent", sContent);
            if(bType == 0) {
                return jdbc.queryForObject(SELECT_ALL_COUNT_ZERO, params, Integer.class);
            }
            else if(bType == 100){
            	return jdbc.queryForObject(INIT_SELECT_ALL_COUNT, params, Integer.class);
            }else {
            	return jdbc.queryForObject(SELECT_ALL_COUNT, params, Integer.class);
            }
        }
        catch(EmptyResultDataAccessException e)
        {
            return (Integer) null;
        }
        
    }

    public int select_All_Comment_Count(int bId)
    {
        try
        {
            Map<String, Integer> params = new HashMap<>();
            params.put("bId", bId);
            return jdbc.queryForObject(SELECT_COMMENT_ALL_COUNT, params, Integer.class);
        }
        catch(EmptyResultDataAccessException e)
        {
            return (Integer) null;
        }
    }

    public int select_All_Good_Count(int bId)
    {
        try
        {
            Map<String, Integer> params = new HashMap<>();
            params.put("bId", bId);
            return jdbc.queryForObject(SELECT_GOOD_COUNT, params, Integer.class);
        }
        catch(EmptyResultDataAccessException e)
        {
            return (Integer) null;
        }
    }

    public int update_Board(BoardDto boarddto)
    {
        SqlParameterSource params = new BeanPropertySqlParameterSource(boarddto);
        return jdbc.update(UPDATE_BOARD, params);
    }

    public int update_Comment(CommentDto commentdto)
    {
        SqlParameterSource params = new BeanPropertySqlParameterSource(commentdto);
        return jdbc.update(UPDATE_COMMENT, params);
    }

    public int update_Comment_Count(int bCommentCount, int bId)
    {
        Map<String, Integer> params = new HashMap<>();
        params.put("bCommentCount", bCommentCount);
        params.put("bId", bId);
        return jdbc.update(UPDATE_COMMENT_COUNT, params);
    }

    public int update_Good_Count(int checkNum, int bId)
    {
        Map<String, Integer> params = new HashMap<>();
        params.put("bId", bId);
        if(checkNum == 0) {
            return jdbc.update(INCREMENT_GOOD_COUNT, params);
        } else {
            return jdbc.update(DECREMENT_GOOD_COUNT, params);
        }
    }

    public int delete_Board(int bId)
    {
        Map<String, Integer> params = new HashMap<>();
        params.put("bId", bId);
        return jdbc.update(DELETE_BOARD, params);
    }

    public int delete_Comment(int bId, int cId)
    {
        Map<String, Integer> params = new HashMap<>();
        params.put("bId", bId);
        params.put("cId", cId);
        return jdbc.update(DELETE_COMMENT, params);
    }

    public int delete_Good_Account(int bId, String gAccount)
    {
        return jdbc.update("delete from myblog_good where bId =" + bId + " and gAccount=" + "'" + gAccount + "'", Collections.emptyMap());
    }

}
