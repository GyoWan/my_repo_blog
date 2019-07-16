// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoardDto.java

package kr.or.connect.ROOT.dto;

import java.sql.Timestamp;

public class BoardDto
{

    public BoardDto()
    {
    }

    public int getbCommentCount()
    {
        return bCommentCount;
    }

    public void setbCommentCount(int bCommentCount)
    {
        this.bCommentCount = bCommentCount;
    }

    public int getbImgTag()
    {
        return bImgTag;
    }

    public void setbImgTag(int bImgTag)
    {
        this.bImgTag = bImgTag;
    }

    public int getbId()
    {
        return bId;
    }

    public void setbId(int bId)
    {
        this.bId = bId;
    }

    public String getbAccount()
    {
        return bAccount;
    }

    public void setbAccount(String bAccount)
    {
        this.bAccount = bAccount;
    }

    public int getbType()
    {
        return bType;
    }

    public void setbType(int bType)
    {
        this.bType = bType;
    }

    public String getbTitle()
    {
        return bTitle;
    }

    public void setbTitle(String bTitle)
    {
        this.bTitle = bTitle;
    }

    public String getbContent()
    {
        return bContent;
    }

    public void setbContent(String bContent)
    {
        this.bContent = bContent;
    }

    public int getbHit()
    {
        return bHit;
    }

    public void setbHit(int bHit)
    {
        this.bHit = bHit;
    }

    public int getbStep()
    {
        return bStep;
    }

    public void setbStep(int bStep)
    {
        this.bStep = bStep;
    }

    public int getbIndent()
    {
        return bIndent;
    }

    public void setbIndent(int bIndent)
    {
        this.bIndent = bIndent;
    }

    public int getbGroup()
    {
        return bGroup;
    }

    public void setbGroup(int bGroup)
    {
        this.bGroup = bGroup;
    }

    public int getbGood()
    {
        return bGood;
    }

    public void setbGood(int bGood)
    {
        this.bGood = bGood;
    }

    public Timestamp getbReg_Date()
    {
        return bReg_Date;
    }

    public void setbReg_Date(Timestamp bReg_Date)
    {
        this.bReg_Date = bReg_Date;
    }

    public Timestamp getbMod_Date()
    {
        return bMod_Date;
    }

    public void setbMod_Date(Timestamp bMod_Date)
    {
        this.bMod_Date = bMod_Date;
    }

    public String toString()
    {
        return (new StringBuilder("BoardDto [bId=")).append(bId).append(", bAccount=").append(bAccount).append(", bType=").append(bType).append(", bTitle=").append(bTitle).append(", bContent=").append(bContent).append(", bHit=").append(bHit).append(", bStep=").append(bStep).append(", bIndent=").append(bIndent).append(", bGroup=").append(bGroup).append(", bGood=").append(bGood).append(", bReg_Date=").append(bReg_Date).append(", bMod_Date=").append(bMod_Date).append(", bImgTag=").append(bImgTag).append(", bCommentCount=").append(bCommentCount).append("]").toString();
    }

    private int bId;
    private String bAccount;
    private int bType;
    private String bTitle;
    private String bContent;
    private int bHit;
    private int bStep;
    private int bIndent;
    private int bGroup;
    private int bGood;
    private Timestamp bReg_Date;
    private Timestamp bMod_Date;
    private int bImgTag;
    private int bCommentCount;
}
