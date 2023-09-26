package com.example.psyapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Encyclopedia extends Fragment {

   Button[] buttons= new Button[16];
   ArrayList <String>arrayList= new ArrayList<>();
   TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_encyclopedia, container, false);
        arrayList.add("ISTJ: Values practicality and logic. Known for their reliability and attention to detail, they excel in roles that require organization and following through on tasks.");
        arrayList.add("ISFJ: Compassionate and dependable, they excel in roles that require empathy and attention to detail. They often put others' needs above their own.");
        arrayList.add("INFJ: Idealistic and insightful, they seek deeper understanding and connection in their relationships. They are often drawn to humanitarian causes.");
        arrayList.add("INTJ: Strategic and logical, they excel in problem-solving. They value knowledge and competence, and usually have high standards for performance.");
        arrayList.add("ISTP: Adaptable and resourceful, they are skilled at understanding systems and solving practical problems. They value freedom and flexibility.");
        arrayList.add("ISFP: Creative and open-minded, they enjoy exploring new things and experiences. They are deeply passionate and enjoy expressing themselves through art.");
        arrayList.add("INFP: Idealistic and compassionate, they are guided by their values and seek to make the world a better place. They are often creative and enjoy abstract thinking.");
        arrayList.add("INTP: Analytical and imaginative, they are driven by a desire to understand systems and ideas. They are independent and skeptical of established rules.");
        arrayList.add("ESTP: Quick-thinking and perceptive, they are excellent at reading people and situations, often thriving in fast-paced environments.");
        arrayList.add("ESFP: Outgoing and social, they are the life of the party. They love new experiences and are very perceptive of others' needs and feelings.");
        arrayList.add("ENFP: Optimistic and energetic, they are excellent at inspiring people. They are great at identifying opportunities and are very creative.");
        arrayList.add("ENTP: Quick-witted and intellectually agile, they love debating ideas and are excellent problem-solvers. They thrive on challenge and change.");
        arrayList.add("ESTJ: Strong organizational skills and a focus on efficiency. They are dependable leaders who value tradition and order.");
        arrayList.add("ESFJ: Highly social and compassionate, they are great team players who are focused on harmony and cooperation.");
        arrayList.add("ENFJ: Inspirational and altruistic, they are skilled communicators who are focused on helping others achieve their potential.");
        arrayList.add("ENTJ: Strategic and assertive, they are natural leaders who excel at logical reasoning and planning for the future.");
        textView=v.findViewById(R.id.textViewEncyclopedia);
        for(int i=1;i<=16;i++)
        {
            String buttonsName= "buttonEncyclopedia"+i;
            int buttonsId=getResources().getIdentifier(buttonsName,"id", getContext().getPackageName());
            buttons[i-1]= v.findViewById(buttonsId);
            int finalI = i;
            buttons[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText(arrayList.get(finalI -1));

                }
            });
        }
        //View v=inflater.inflate(R.layout.fragment_encyclopedia, container, false);

        return v;
    }
}