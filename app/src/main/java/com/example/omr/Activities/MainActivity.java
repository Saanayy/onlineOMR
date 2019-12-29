package com.example.omr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.omr.Modals.SharedPrefManager;
import com.example.omr.R;
import com.example.omr.Utils.UtilityFunctions;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextInputEditText etQuestionCount, etOptionCount;

    SharedPrefManager sPref;
    Button btnCreate;
    private int questionCount, optionCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        questionCount = 20; optionCount = 4;


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etQuestionCount.getText().toString().length() != 0)
                    questionCount = Integer.parseInt(etQuestionCount.getText().toString());
                createDefaultAnswerStrings();
                Intent createOMRIntent = new Intent(MainActivity.this, SheetActivity.class);
                createOMRIntent.putExtra("questioncount", questionCount);
                startActivity(createOMRIntent);
                finish();
            }
        });


    }

    private void createDefaultAnswerStrings() {
        List<Character> initialList = new ArrayList<>(questionCount);

        for (int i = 0; i < questionCount; i++) initialList.add('N');

        String correctString = UtilityFunctions.ConvertListtoString(initialList, questionCount);
        String yourString = UtilityFunctions.ConvertListtoString(initialList, questionCount);
        Log.d("hello1", "createDefaultAnswerStrings() returned: " + yourString + "\n" + correctString);
        sPref.put(SharedPrefManager.Key.CORRECT_ANSWER, correctString);
        sPref.put(SharedPrefManager.Key.YOUR_ANSWER, yourString);

    }

    private void init() {
        sPref = SharedPrefManager.getInstance(MainActivity.this);
        etOptionCount = findViewById(R.id.main_option_count_et);
        etQuestionCount = findViewById(R.id.main_question_count_et);
        btnCreate = findViewById(R.id.main_createOMR);

    }


}
