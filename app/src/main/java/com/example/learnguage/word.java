package com.example.learnguage;

public class word {

    private String defaultWord;
    private String hindWord;
    private int audio;
    private int image;
    private int a,b,c;

    public word(String x,String y,int z){
        defaultWord = x;
        hindWord = y;
        audio = z;
    }

    public word(String x,int p,int q,int r,String z,int audio){
        defaultWord = x;
        hindWord = z;
        a = p;b = q;c = r;
        this.audio = audio;
    }

    public word(String x,int p,String y,int q){
        defaultWord = x;
        hindWord = y;
        image = p;
        audio = q;
    }

    public int getAudio() {
        return audio;
    }

    public int getImage() {
        return image;
    }

    public String getDefaultWord() {
        return defaultWord;
    }

    public String getHindWord() {
        return hindWord;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }
}
