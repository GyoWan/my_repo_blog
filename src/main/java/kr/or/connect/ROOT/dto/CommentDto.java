// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommentDto.java

package kr.or.connect.ROOT.dto;

import java.sql.Timestamp;

public class CommentDto
{

    public CommentDto()
    {
    }

    public String getStrDate()
    {
        return strDate;
    }

    public void setStrDate(String strDate)
    {
        this.strDate = strDate;
    }

    public int getCommentCount()
    {
        return commentCount;
    }

    public void setCommentCount(int commentCount)
    {
        this.commentCount = commentCount;
    }

    public int getcId()
    {
        return cId;
    }

    public void setcId(int cId)
    {
        this.cId = cId;
    }

    public int getbId()
    {
        return bId;
    }

    public void setbId(int bId)
    {
        this.bId = bId;
    }

    public String getcContent()
    {
        return cContent;
    }

    public void setcContent(String cContent)
    {
        this.cContent = cContent;
    }

    public String getcAccount()
    {
        return cAccount;
    }

    public void setcAccount(String cAccount)
    {
        this.cAccount = cAccount;
    }

    public Timestamp getcReg_Date()
    {
        return cReg_Date;
    }

    public void setcReg_Date(Timestamp cReg_Date)
    {
        this.cReg_Date = cReg_Date;
    }

    public Timestamp getcMod_Date()
    {
        return cMod_Date;
    }

    public void setcMod_Date(Timestamp cMod_Date)
    {
        this.cMod_Date = cMod_Date;
    }

    public String toString()
    {
        return (new StringBuilder("CommentDto [cId=")).append(cId).append(", bId=").append(bId).append(", cContent=").append(cContent).append(", cAccount=").append(cAccount).append(", cReg_Date=").append(cReg_Date).append(", cMod_Date=").append(cMod_Date).append(", commentCount=").append(commentCount).append(", strDate=").append(strDate).append("]").toString();
    }

    private int cId;
    private int bId;
    private String cContent;
    private String cAccount;
    private Timestamp cReg_Date;
    private Timestamp cMod_Date;
    private int commentCount;
    private String strDate;
}
