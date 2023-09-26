package com.example.psyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class CreateProfile extends AppCompatActivity {
    private EditText setName;
    private EditText setPrename;
    private Button saveNames;
    private boolean textChangedName = false;
    private boolean textChangedPrename=false;
    private String originalTextName;
    private String originalTextPrename;
    private TextView setText;
    //aici pun database pt salvare nume prennume etc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        setName= findViewById(R.id.editName);
        setPrename=findViewById(R.id.editPrename);
        originalTextName=setName.getText().toString();
        originalTextPrename=setPrename.getText().toString();
        setText=findViewById(R.id.textView);
       /* try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tabel", "root", "123123");
            // Use the connection for database operations
            //aici vine cod pt query
            String query = "SELECT * FROM persoana";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            //verificare
            if (resultSet.next() && resultSet.getInt(1) == 1) {
                setText.setText("conexiune reusita");
            } else {
                Log.e("Aplicatia", "eroare db");
            }

        } catch (SQLException e) {
            setText.setText("conexiune meganereusita");
            e.printStackTrace();
        }*/
        //test baza date
        setName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    if(textChangedName==false)
                    {
                        setName.setText("");
                    }
                }
                else
                if(textChangedName==false)
                {
                    setName.setText(originalTextName);
                }
            }
        });
        setPrename.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    if(textChangedPrename==false)
                    {
                        setPrename.setText("");
                    }
                }
                else
                if(textChangedPrename==false)
                {
                    setPrename.setText(originalTextPrename);
                }
            }
        });


        setName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textChangedName==false) {
                    textChangedName = true;
                }
            }
        });
        setPrename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textChangedPrename==false){
                    textChangedPrename=true;
                }
            }
        });
        saveNames = findViewById(R.id.saveData);


        //lla buton next, faci salvare in baza de date a nume + prenume sau
        //la test de personalitate salvez nume + parametrii INSJ etc

    }
    public void QuizzActivity(View view)
    {
        SharedPreferences preferences = getSharedPreferences("UserProfile", MODE_PRIVATE);//for testing purposes only
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", setName.getText().toString());//inlocuieste cu alea setName.getText si setPrename
        editor.putString("prename",setPrename.getText().toString());
        editor.putBoolean("existingProfile",true);
        editor.apply();
        String text= preferences.getString("name","default");//preference e un fel de cache permanent, si se ia valoare de la id "nume"
        setText.setText(text);
        //editor.clear(); //still in testing
        Intent i;
        i = new Intent(this, QuizzActivity.class);
        startActivity(i);
    }
}