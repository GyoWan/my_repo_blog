// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginDto.java

package kr.or.connect.ROOT.dto;


public class LoginDto
{

    public LoginDto()
    {
    }

    public boolean isLogin_Check()
    {
        return login_Check;
    }

    public void setLogin_Check(boolean login_Check)
    {
        this.login_Check = login_Check;
    }

    public String toString()
    {
        return (new StringBuilder("LoginDto [login_Check=")).append(login_Check).append("]").toString();
    }

    private boolean login_Check;
}
