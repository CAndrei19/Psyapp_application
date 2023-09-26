package com.example.psyapp;


public class QuizzQuestion {
    private String text;
    private String dichotomy;
    public QuizzQuestion(String text, String dichotomy)
    {
        this.text=text;
        this.dichotomy=dichotomy;
    }

    public String getText() {
        return text;
    }

    public String getDichtonomy() {
        return dichotomy;
    }
}
