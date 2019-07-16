// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SendGoodDto.java

package kr.or.connect.ROOT.dto;


public class SendGoodDto
{

    public SendGoodDto()
    {
    }

    public boolean isGoodAccountCheck()
    {
        return goodAccountCheck;
    }

    public void setGoodAccountCheck(boolean goodAccountCheck)
    {
        this.goodAccountCheck = goodAccountCheck;
    }

    public int getGoodCount()
    {
        return goodCount;
    }

    public void setGoodCount(int goodCount)
    {
        this.goodCount = goodCount;
    }

    public String toString()
    {
        return (new StringBuilder("SendGoodDto [goodAccountCheck=")).append(goodAccountCheck).append(", goodCount=").append(goodCount).append("]").toString();
    }

    private boolean goodAccountCheck;
    private int goodCount;
}
