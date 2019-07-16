// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommentPagingDto.java

package kr.or.connect.ROOT.dto;


public class CommentPagingDto
{

    public CommentPagingDto()
    {
    }

    public int getCpageCount()
    {
        return cpageCount;
    }

    public void setCpageCount(int cpageCount)
    {
        this.cpageCount = cpageCount;
    }

    public int getCpageBlock()
    {
        return cpageBlock;
    }

    public void setCpageBlock(int cpageBlock)
    {
        this.cpageBlock = cpageBlock;
    }

    public int getCstartPageBlock()
    {
        return cstartPageBlock;
    }

    public void setCstartPageBlock(int cstartPageBlock)
    {
        this.cstartPageBlock = cstartPageBlock;
    }

    public int getCendPageBlock()
    {
        return cendPageBlock;
    }

    public void setCendPageBlock(int cendPageBlock)
    {
        this.cendPageBlock = cendPageBlock;
    }

    public int getCcurrentPageNum()
    {
        return ccurrentPageNum;
    }

    public void setCcurrentPageNum(int ccurrentPageNum)
    {
        this.ccurrentPageNum = ccurrentPageNum;
    }

    public String toString()
    {
        return (new StringBuilder("CommentPagingDto [cpageCount=")).append(cpageCount).append(", cpageBlock=").append(cpageBlock).append(", cstartPageBlock=").append(cstartPageBlock).append(", cendPageBlock=").append(cendPageBlock).append(", ccurrentPageNum=").append(ccurrentPageNum).append("]").toString();
    }

    private int cpageCount;
    private int cpageBlock;
    private int cstartPageBlock;
    private int cendPageBlock;
    private int ccurrentPageNum;
}
