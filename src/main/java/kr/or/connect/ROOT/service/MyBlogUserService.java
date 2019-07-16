// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyBlogUserService.java

package kr.or.connect.ROOT.service;

import kr.or.connect.ROOT.dto.UserDto;

public interface MyBlogUserService
{

    public abstract UserDto addUser(UserDto userdto);

    public abstract UserDto modifyUser(UserDto userdto);

    public abstract UserDto getUser(String userId);

    public abstract UserDto loginUser(String login_userId, String login_userPw);

    public abstract int userCheck(String userId);

    public abstract int userNameCheck(String userName);

    public abstract int userEmailCheck(String userEmail);
}
