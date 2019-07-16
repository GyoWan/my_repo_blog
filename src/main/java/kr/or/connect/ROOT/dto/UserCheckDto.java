// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserCheckDto.java

package kr.or.connect.ROOT.dto;


public class UserCheckDto
{

    public UserCheckDto()
    {
    }

    public boolean isAuthNumberCheck()
    {
        return authNumberCheck;
    }

    public void setAuthNumberCheck(boolean authNumberCheck)
    {
        this.authNumberCheck = authNumberCheck;
    }

    public String getAuthenticationNumber()
    {
        return authenticationNumber;
    }

    public void setAuthenticationNumber(String authenticationNumber)
    {
        this.authenticationNumber = authenticationNumber;
    }

    public int getUserCheckCount()
    {
        return userCheckCount;
    }

    public void setUserCheckCount(int userCheckCount)
    {
        this.userCheckCount = userCheckCount;
    }

    public String toString()
    {
        return (new StringBuilder("UserCheckDto [userCheckCount=")).append(userCheckCount).append(", authenticationNumber=").append(authenticationNumber).append(", authNumberCheck=").append(authNumberCheck).append("]").toString();
    }

    private int userCheckCount;
    private String authenticationNumber;
    private boolean authNumberCheck;
}
