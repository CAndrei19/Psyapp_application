package com.example.psyapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.format.DateFormat;

import java.util.Date;


public class Journal extends Fragment {


    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private TextView textView;
    private EditText editText;
    private ContentValues contentValues=new ContentValues();
    private Button button;
    private Cursor cursor;
    private Button button2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_journal, container, false);
        databaseHelper = new DatabaseHelper(getActivity());
        database=databaseHelper.getWritableDatabase();
        editText=v.findViewById(R.id.editTextJournal);
        textView=v.findViewById(R.id.textViewJournal);
        button=v.findViewById(R.id.buttonJournal);
        button2=v.findViewById(R.id.buttonJournalRetrieve);
        cursor=database.query("journal",null,null,null,null,null,null);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    if(editText.getText().toString().equals("Write your thoughts for the journal")) {
                        editText.setText("");
                    }
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentTimeMillis=System.currentTimeMillis();
                String date=DateFormat.format("yyyy-MM-dd",currentTimeMillis).toString();
                editText.getText().toString();
                contentValues.put("date",date);
                contentValues.put("text",editText.getText().toString());
                if(database.insert("journal",null,contentValues)==-1)
                {
                    Log.e("dberror","nu s-a inserat");
                }
                else
                {
                    //textView.setText("success");//accesare date+afisare
//                    if (cursor.moveToFirst()) {
//                        do {
//                            @SuppressLint("Range") String dateafisare = cursor.getString(cursor.getColumnIndex("date"));
//                            @SuppressLint("Range") String textafisare = cursor.getString(cursor.getColumnIndex("text"));
//
//                            Log.d("Database data",dateafisare);
//                            Log.d("Database text",textafisare);
//
//                        } while (cursor.moveToNext());
//                    }
                    cursor.close();
                    //cursor=database.query("journal",null,null,null,null,null,null,null);
                    editText.setText("");

                }
            }
        });

        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN)
                {
                   hideKeyboard(editText);
                    editText.clearFocus();
                }
                return false;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor=database.query("journal",null,null,null,null,null,null,null);
                if (cursor.moveToFirst()) {
                    do {
                        @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                        @SuppressLint("Range") String text = cursor.getString(cursor.getColumnIndex("text"));

                        textView.setText(textView.getText().toString()+text+" "+date+'\n');


                    } while (cursor.moveToNext());
                }
               // cursor.close();

            }
        });
        return v;

    }
    //aici inchid database+ce altceva mai este
    //database.close();
    public void hideKeyboard(View v)
    {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

    }
}