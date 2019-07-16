// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserDto.java

package kr.or.connect.ROOT.dto;


public class UserDto
{

    public UserDto()
    {
    }

    public String getUserSalt()
    {
        return userSalt;
    }

    public void setUserSalt(String userSalt)
    {
        this.userSalt = userSalt;
    }

    public int getUserStatus()
    {
        return userStatus;
    }

    public void setUserStatus(int userStatus)
    {
        this.userStatus = userStatus;
    }

    public int getUserAdmin()
    {
        return userAdmin;
    }

    public void setUserAdmin(int userAdmin)
    {
        this.userAdmin = userAdmin;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserPw()
    {
        return userPw;
    }

    public void setUserPw(String userPw)
    {
        this.userPw = userPw;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String toString()
    {
        return (new StringBuilder("UserDto [userId=")).append(userId).append(", userPw=").append(userPw).append(", userName=").append(userName).append(", userEmail=").append(userEmail).append(", userSalt=").append(userSalt).append(", userAdmin=").append(userAdmin).append(", userStatus=").append(userStatus).append("]").toString();
    }

    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userSalt;
    private int userAdmin;
    private int userStatus;
}
