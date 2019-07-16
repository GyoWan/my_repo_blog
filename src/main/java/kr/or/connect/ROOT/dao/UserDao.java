// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserDao.java

package kr.or.connect.ROOT.dao;

import static kr.or.connect.ROOT.dao.UserDaoSql.SELECT_USERID_LOGIN;
import static kr.or.connect.ROOT.dao.UserDaoSql.SELECT_USER_CHECK;
import static kr.or.connect.ROOT.dao.UserDaoSql.SELECT_USER_EMAIL_CHECK;
import static kr.or.connect.ROOT.dao.UserDaoSql.SELECT_USER_LOGIN;
import static kr.or.connect.ROOT.dao.UserDaoSql.SELECT_USER_NAME_CHECK;

import java.util.HashMap;
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

import kr.or.connect.ROOT.dto.UserDto;

@Repository
public class UserDao
{
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<UserDto> rowMapper = BeanPropertyRowMapper.newInstance(UserDto.class);

    public UserDao(DataSource dataSource)
    {
        
        jdbc = new NamedParameterJdbcTemplate(dataSource);
        insertAction = new SimpleJdbcInsert(dataSource)
        		.withTableName("myblog_user")
        		.usingGeneratedKeyColumns("id");
    }

    public int insert_User(UserDto userdto) {
    	
    	SqlParameterSource params = new BeanPropertySqlParameterSource(userdto);
    	return insertAction.executeAndReturnKey(params).intValue();
    }

    public UserDto select_UserId(String login_userId) {
    	
    	try {
    		
    		Map<String, String> params = new HashMap<>();
			params.put("login_userId", login_userId);
    		
    		return jdbc.queryForObject(SELECT_USERID_LOGIN, params, rowMapper);
    		
    	}catch(EmptyResultDataAccessException e) {
    		
    		return null;
    		
    	}
    }
 
    public UserDto select_User(String login_userId, String login_userPw) {
    	
    	try {
    		
    		Map<String, String> params = new HashMap<>();
			params.put("login_userId", login_userId);
			params.put("login_userPw", login_userPw);
    		
    		return jdbc.queryForObject(SELECT_USER_LOGIN, params, rowMapper);
    		
    	}catch(EmptyResultDataAccessException e) {
    		
    		return null;
    		
    	}
    	
    }

    public int select_User_Check(String userId) {
    	 
    	try {
			Map<String, String> params = new HashMap<>();
			params.put("userId", userId);
	
			return jdbc.queryForObject(SELECT_USER_CHECK, params, Integer.class);
    	}catch(EmptyResultDataAccessException e) {
    		
    		return (Integer)null;
    		
    	}
	}

    public int select_User_Name_Check(String userName) {
    	 
		try {
			Map<String, String> params = new HashMap<>();
			params.put("userName", userName);
 
			return jdbc.queryForObject(SELECT_USER_NAME_CHECK, params, Integer.class);
		}catch(EmptyResultDataAccessException e) {
    		
    		return (Integer)null;
    		
    	}
    	
	}
    
    public int select_User_Email_Check(String userEmail) {
 
		try {
			Map<String, String> params = new HashMap<>();
			params.put("userEmail", userEmail);
 
			return jdbc.queryForObject(SELECT_USER_EMAIL_CHECK, params, Integer.class);
		}catch(EmptyResultDataAccessException e) {
    		
    		return (Integer)null;
    		
    	}
    	
	}


}
