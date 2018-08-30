package com.sctce.armagazine;
public class MagPage {
    private int ArtId;
    private String ArtName;
    private String AuthName;
    private byte[] ArtImg;
    private byte[] AuthImg;
    private String Content;
    private String ArtEng;

    public MagPage(int ArtId,String ArtName, String AuthName, byte[] ArtImg,byte[] AuthImg,String Content,String ArtEng) {
        this.ArtId=ArtId;
        this.ArtName=ArtName;
        this.AuthName=AuthName;
        this.ArtImg=ArtImg;
        this.AuthImg=AuthImg;
        this.Content=Content;
        this.ArtEng=ArtEng;

    }

    public int getArtId() { return ArtId; }

    public String getArtName() {
        return ArtName;
    }

    public String getAuthName() {
        return AuthName;
    }

    public byte[] getArtImg() {
        return ArtImg;
    }

    public byte[] getAuthImg() {
        return AuthImg;
    }

    public String getContent() { return Content; }

    public String getArtEng() { return ArtEng; }
}