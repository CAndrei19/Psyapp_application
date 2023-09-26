package com.example.psyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class QuizzActivity extends AppCompatActivity {
    private MBTIquizz quizz;
    private TextView question[] = new TextView[5];

    RadioButton[] radioButtons = new RadioButton[5];
    private RadioGroup group[] = new RadioGroup[5];
    private Button setResultsButton;

    private String[] string={"I","S","T","J","E","N","T","J"};
    int[] answerId = new int[5];
    private int currentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        MBTIquizz quizz = new MBTIquizz();
        quizz.addQuestion(new QuizzQuestion("\tSocializing with large groups is enjoyable.", "E"));
        quizz.addQuestion(new QuizzQuestion("\tBeing around people energizes me.", "E"));
        quizz.addQuestion(new QuizzQuestion("\tSolitude helps me recharge.", "I"));
        quizz.addQuestion(new QuizzQuestion("\tEngaging in small talk feels natural.", "E"));
        quizz.addQuestion(new QuizzQuestion("\tCraving time alone is frequent for me.", "I"));
        quizz.addQuestion(new QuizzQuestion("\tDetails and facts are my focus.", "S"));
        quizz.addQuestion(new QuizzQuestion("\tThinking about future possibilities excites me.", "N"));
        quizz.addQuestion(new QuizzQuestion("\tExperience is my go-to guide.", "S"));
        quizz.addQuestion(new QuizzQuestion("\tAbstract concepts intrigue me.", "N"));
        quizz.addQuestion(new QuizzQuestion("\tPractical solutions are preferable.", "S"));
        quizz.addQuestion(new QuizzQuestion("\tDecisions are best made using logic.", "T"));
        quizz.addQuestion(new QuizzQuestion("\tPeople's feelings influence my decisions.", "F"));
        quizz.addQuestion(new QuizzQuestion("\tFairness trumps harmony for me.", "T"));
        quizz.addQuestion(new QuizzQuestion("\tGut feelings are often right.", "F"));
        quizz.addQuestion(new QuizzQuestion("\tObjectivity is key in problem-solving.", "T"));
        quizz.addQuestion(new QuizzQuestion("\tSticking to a plan is satisfying.", "J"));
        quizz.addQuestion(new QuizzQuestion("\tSpontaneity brings joy.", "P"));
        quizz.addQuestion(new QuizzQuestion("\tLast-minute changes stress me out.", "J"));
        quizz.addQuestion(new QuizzQuestion("\tKeeping options open is preferable.", "P"));
        quizz.addQuestion(new QuizzQuestion("\tCompleting tasks promptly is fulfilling.", "J"));
        question[0] = findViewById(R.id.textView2);
        question[1] = findViewById(R.id.textView3);
        question[2] = findViewById(R.id.textView4);
        question[3] = findViewById(R.id.textView5);
        question[4] = findViewById(R.id.textView6);
        group[0] = findViewById(R.id.radioGroup);
        group[1] = findViewById(R.id.radioGroup2);
        group[2] = findViewById(R.id.radioGroup3);
        group[3] = findViewById(R.id.radioGroup4);
        group[4] = findViewById(R.id.radioGroup5);
        setResultsButton = findViewById(R.id.NextButton);
        for(int i=0;i<5;i++)
        {
            question[i].setText(quizz.getQuestion(i).getText().toString());
        }
        setResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResultsButton.setText("nasol");
                for(int i=0;i<5;i++)
                {
                    answerId[i]=group[i].getCheckedRadioButtonId();
                    radioButtons[i]=findViewById(answerId[i]);
                }
                /*answerId[0]=group[0].getCheckedRadioButtonId();
                answerId[1]=group[1].getCheckedRadioButtonId();
                answerId[2]=group[2].getCheckedRadioButtonId();
                answerId[3]=group[3].getCheckedRadioButtonId();
                answerId[4]=group[4].getCheckedRadioButtonId();
                radioButtons[0]=findViewById(answerId[0]);
                radioButtons[1]=findViewById(answerId[1]);
                radioButtons[2]=findViewById(answerId[2]);
                radioButtons[3]=findViewById(answerId[3]);
                radioButtons[4]=findViewById(answerId[4]);*/

                for(int i=currentQuestion;i<currentQuestion+5;i++) {
                    String s=quizz.getQuestion(i).getDichtonomy().toString();
                    if(answerId[i%5]!=-1) {
                        int n =Integer.parseInt(radioButtons[i%5].getTag().toString());
                         quizz.UpdateScore(s,n);
                        }
                    else
                    {
                        quizz.UpdateScore(s,0);
                    }
                }
                quizz.DisplayScore();
                currentQuestion+=5;
                if(currentQuestion<20)
                {
                    for(int i=currentQuestion;i<currentQuestion+5;i++)
                    {
                        //update textviewuri cu intrebari
                        question[i%5].setText(quizz.getQuestion(i).getText().toString());
                        group[i%5].clearCheck();
                    }
                }
                else
                {
                    String personality= quizz.GetPersonality();
                    Log.d("personality ",personality);
                    quizz.DisplayScore();


                    SharedPreferences preferences = getSharedPreferences("UserProfile", MODE_PRIVATE);//for testing purposes only
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("personality",personality);
                    for(int i=0;i<8;i++)
                    {
                        editor.putInt(string[i],quizz.getDichotomyScores(i));
                    }

                    editor.apply();



                    Intent i = new Intent(QuizzActivity.this,MainMenuActivity.class);
                    startActivity(i);
                }
            }
        });

    }
}

//    public void SetText(View view) {
//       setResults.setText("apasat");
//        for(int i=0;i<5;i++)
//                {
//                    answerId[i]=group[i].getCheckedRadioButtonId();
//                    radioButtons[i]=findViewById(answerId[i]);
//                }
//                answerId[0]=group[0].getCheckedRadioButtonId();
//                answerId[1]=group[1].getCheckedRadioButtonId();
//                answerId[2]=group[2].getCheckedRadioButtonId();
//                answerId[3]=group[3].getCheckedRadioButtonId();
//                answerId[4]=group[4].getCheckedRadioButtonId();
//                radioButtons[0]=findViewById(answerId[0]);
//                radioButtons[1]=findViewById(answerId[1]);
//                radioButtons[2]=findViewById(answerId[2]);
//                radioButtons[3]=findViewById(answerId[3]);
//                radioButtons[4]=findViewById(answerId[4]);

//                for(int i=currentQuestion;i<currentQuestion+5;i++) {
//                    String s=quizz.getQuestion(i).getDichtonomy().toString();
//                    int n =Integer.parseInt(radioButtons[i%5].getTag().toString());
//                  // quizz.UpdateScore(s,n);
//                }
//                currentQuestion+=5;
//                if(currentQuestion<20)
//                {
////                    for(int i=currentQuestion;i<currentQuestion;i++)
////                    {
////                        //update textviewuri cu intrebari
////                        question[i%5].setText(quizz.getQuestion(i).getText().toString());
////                    }
//                    question[0].setText(quizz.getQuestion(currentQuestion+0).getText().toString());
//                    question[1].setText(quizz.getQuestion(currentQuestion+1).getText().toString());
//                    question[2].setText(quizz.getQuestion(currentQuestion+2).getText().toString());
//                    question[3].setText(quizz.getQuestion(currentQuestion+3).getText().toString());
//                    question[4].setText(quizz.getQuestion(currentQuestion+4).getText().toString());
//                }

