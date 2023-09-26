package com.example.psyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.*;

import java.util.ArrayList;
import java.util.List;

public class Profile extends Fragment {


    private TextView name;
    private TextView prename;
    private TextView personality;
    private Button resetButton;
    private SharedPreferences sharedPreferences;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private BarChart barChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        sharedPreferences= getContext().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        name=view.findViewById(R.id.textViewProfile);
        barChart=view.findViewById(R.id.barChart);//proprietati barchart
       // barChart.setDrawGridBackground(false);
        barChart.setGridBackgroundColor(Color.parseColor("#EFEFEF"));
        barChart.setDrawBorders(true);
        barChart.setBorderColor(Color.parseColor("#000000"));
        //barChart.setNoDataTextColor(Color.parseColor("#FF0000"));
        barChart.getDescription().setEnabled(false);
        //barChart.getAxisLeft().setAxisMinimum(0f);
        //end proprietati
        prename=view.findViewById(R.id.textViewProfile2);
        personality=view.findViewById(R.id.textViewProfile3);
        resetButton=view.findViewById(R.id.buttonResetProfile);
        //this.updateTextViews();
        name.setText("Name : "+sharedPreferences.getString("name","Non").toString());
        prename.setText("Surname : "+sharedPreferences.getString("prename","Non"));
        personality.setText("Personality type : "+sharedPreferences.getString("personality","Non"));
        databaseHelper = new DatabaseHelper(getActivity());
        database=databaseHelper.getWritableDatabase();
        //aici incepe graf
        int I = sharedPreferences.getInt("I", 0);
        int S = sharedPreferences.getInt("S", 0);
        int T = sharedPreferences.getInt("T", 0);
        int J = sharedPreferences.getInt("J", 0);
        int E = sharedPreferences.getInt("E", 0);
        int N = sharedPreferences.getInt("N", 0);
        int F = sharedPreferences.getInt("F", 0);
        int P = sharedPreferences.getInt("P", 0);

        List<BarEntry> introversion_extroversion = new ArrayList<>();
        introversion_extroversion.add(new BarEntry(0f, I));
        introversion_extroversion.add(new BarEntry(1f, E));

        List<BarEntry> sensing_intuition = new ArrayList<>();
        sensing_intuition.add(new BarEntry(2f, S));
        sensing_intuition.add(new BarEntry(3f, N));

        List<BarEntry> thinking_feeling = new ArrayList<>();
        thinking_feeling.add(new BarEntry(4f, T));
        thinking_feeling.add(new BarEntry(5f, F));

        List<BarEntry> judging_perceiving = new ArrayList<>();
        judging_perceiving.add(new BarEntry(6f, J));
        judging_perceiving.add(new BarEntry(7f, P));

        BarDataSet introversion_extroversionDataset = new BarDataSet(introversion_extroversion, "Introversion vs Extroversion");
        BarDataSet sensing_intuitionDataset = new BarDataSet(sensing_intuition, "Sensing vs Intuition");
        BarDataSet thinking_feelingDataset = new BarDataSet(thinking_feeling, "Thinking vs Feeling");
        BarDataSet judging_perceivingDataset = new BarDataSet(judging_perceiving, "Judging vs Perceiving");

        introversion_extroversionDataset.setColors(Color.BLUE, Color.RED);
        sensing_intuitionDataset.setColors(Color.GREEN, Color.YELLOW);
        thinking_feelingDataset.setColors(Color.CYAN, Color.MAGENTA);
        judging_perceivingDataset.setColors(Color.GRAY, Color.BLACK);
        BarData barData = new BarData(introversion_extroversionDataset, sensing_intuitionDataset, thinking_feelingDataset, judging_perceivingDataset);
        barChart.getLegend().setTextSize(5f);
        barChart.setData(barData);
        barChart.invalidate();
        //aici se termina cod
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.clear();
                editor.apply();
                database.delete("journal",null,null);//sterge toate coloanele
                database.close();
                if (getActivity() != null) {
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                    getActivity().finish();
                }
            }
        });

        return view;

    }



}