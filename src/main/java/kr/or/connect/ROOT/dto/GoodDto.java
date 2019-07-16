// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GoodDto.java

package kr.or.connect.ROOT.dto;


public class GoodDto
{

    public GoodDto()
    {
    }

    public int getbId()
    {
        return bId;
    }

    public void setbId(int bId)
    {
        this.bId = bId;
    }

    public int getgId()
    {
        return gId;
    }

    public void setgId(int gId)
    {
        this.gId = gId;
    }

    public String getgAccount()
    {
        return gAccount;
    }

    public void setgAccount(String gAccount)
    {
        this.gAccount = gAccount;
    }

    public String toString()
    {
        return (new StringBuilder("GoodDto [bId=")).append(bId).append(", gId=").append(gId).append(", gAccount=").append(gAccount).append("]").toString();
    }

    private int bId;
    private int gId;
    private String gAccount;
}
