// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserDaoSql.java

package kr.or.connect.ROOT.dao;


public class UserDaoSql
{

    public UserDaoSql()
    {
    }

    public static final String SELECT_USER_LOGIN = "select *from myblog_user where userId = :login_userId and userPw = :login_userPw";
    public static final String SELECT_USERID_LOGIN = "select *from myblog_user where userId = :login_userId";
    public static final String SELECT_USER_CHECK = "select count(*) from myblog_user where userId = :userId";
    public static final String SELECT_USER_NAME_CHECK = "select count(*) from myblog_user where userName = :userName";
    public static final String SELECT_USER_EMAIL_CHECK = "select count(*) from myblog_user where userEmail = :userEmail";
}
