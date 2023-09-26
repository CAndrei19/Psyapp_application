package com.example.psyapp;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;

public class MBTIquizz {
    private ArrayList<QuizzQuestion> questionList;//lista intrebari; ArrayList
    private int[] dichotomyScores;
    public MBTIquizz()
    {
        questionList=new ArrayList<>();
        dichotomyScores=new int[8];
    }
    public void addQuestion(QuizzQuestion question)
    {
        questionList.add(question);

    }
    public QuizzQuestion getQuestion(int number)
    {
        return this.questionList.get(number);
    }
    public void UpdateScore(String dichotomy, int score)
    {
        switch (dichotomy) {
            case "I":
                dichotomyScores[0] += score;
                break;
            case "S":
                dichotomyScores[1] += score;
                break;
            case "T":
                dichotomyScores[2] += score;
                break;
            case "J":
                dichotomyScores[3] += score;
                break;
            case "E":
                dichotomyScores[4] += score;
                break;
            case "N":
                dichotomyScores[5] += score;
                break;
            case "F":
                dichotomyScores[6] += score;
                break;
            case "P":
                dichotomyScores[7] += score;
                break;
        }

    }
    public int getDichotomyScores(int n)
    {
        //return Arrays.copyOf(dichotomyScores,dichotomyScores.length);
        return dichotomyScores[n];
    }
    public void DisplayScore() {
        for (int i = 0; i < 4; i++)//i s t j - e n f p diferenta de 4
            switch (i) {
                case 0: {
                    Log.d("Aplicatie Afisare", "scor Introversion= " + dichotomyScores[i]);
                    Log.d("Aplicatie Afisare", "scor Extroversion= " + dichotomyScores[i+4]);
                    break;
                }
                case 1:
                {
                    Log.d("Aplicatie Afisare", "scor Sensing= " + dichotomyScores[i]);
                    Log.d("Aplicatie Afisare", "scor Intuition= " + dichotomyScores[i+4]);
                    break;

                }
                case 2:
                {
                    Log.d("Aplicatie Afisare", "scor Thinking= " + dichotomyScores[i]);
                    Log.d("Aplicatie Afisare", "scor Feeling= " + dichotomyScores[i+4]);
                    break;

                }
                case 3:
                {
                    Log.d("Aplicatie Afisare", "scor Judging= " + dichotomyScores[i]);
                    Log.d("Aplicatie Afisare", "scor Perceiving= " + dichotomyScores[i+4]);
                    break;

                }
            }
    }
    public String GetPersonality()
    {
        String personality="";
        if(dichotomyScores[0]>dichotomyScores[4])
        {
            personality=personality+"I";
        }
        else
        {
            personality=personality+"E";
        }
        if(dichotomyScores[1]>dichotomyScores[5])
        {
            personality=personality+"S";
        }
        else
        {
            personality=personality+"N";
        }
        if(dichotomyScores[2]>dichotomyScores[6])
        {
            personality=personality+"T";
        }
        else
        {
            personality=personality+"F";
        }
        if(dichotomyScores[3]>dichotomyScores[7])
        {
            personality=personality+"J";
        }
        else
        {
            personality=personality+"P";
        }
        return personality;

    }

}
