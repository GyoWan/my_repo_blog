// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectListDto.java

package kr.or.connect.ROOT.dto;


public class SelectListDto
{

    public SelectListDto()
    {
    }

    public String getLiType()
    {
        return liType;
    }

    public void setLiType(String liType)
    {
        this.liType = liType;
    }

    public int getLiId()
    {
        return liId;
    }

    public void setLiId(int liId)
    {
        this.liId = liId;
    }

    public String getLiValue()
    {
        return liValue;
    }

    public void setLiValue(String liValue)
    {
        this.liValue = liValue;
    }

    public String getLiName()
    {
        return liName;
    }

    public void setLiName(String liName)
    {
        this.liName = liName;
    }

    public String toString()
    {
        return (new StringBuilder("SelectListDto [liId=")).append(liId).append(", liValue=").append(liValue).append(", liName=").append(liName).append(", liType=").append(liType).append("]").toString();
    }

    private int liId;
    private String liValue;
    private String liName;
    private String liType;
}
