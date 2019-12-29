package com.example.omr.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.omr.Adapter.AnswerAdapter;
import com.example.omr.Adapter.WrongAnswerAdapter;
import com.example.omr.Modals.AnswerModal;
import com.example.omr.Modals.SharedPrefManager;
import com.example.omr.R;
import com.example.omr.Utils.UtilityFunctions;

import java.util.ArrayList;
import java.util.List;

public class EvaluateActivity extends AppCompatActivity {

    RecyclerView rvWrongAnswer;
    List<AnswerModal> wrongAnswers = new ArrayList<>();
    List<Character> yourAnswerList = new ArrayList<Character>(), correctAnswerList = new ArrayList<Character>();
    WrongAnswerAdapter mAdapter;
    TextView tvScore;
    RecyclerView.LayoutManager layoutManager;
    SharedPrefManager sPref;
    int questioncount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        questioncount = bundle.getInt("questioncount", 20);

        init();
        wrongAnswers = findWrongAnswers();
        tvScore.setText("Score: "+(questioncount-wrongAnswers.size()) + " out of " + questioncount);
        setRecylerView();
    }

    private List<AnswerModal> findWrongAnswers() {
        List<AnswerModal> answerModals = new ArrayList<>();
        for(int i=0; i<questioncount; i++){
            if(yourAnswerList.get(i) == 'N' || correctAnswerList.get(i) != yourAnswerList.get(i)){
                answerModals.add(new AnswerModal(i+1, correctAnswerList.get(i), yourAnswerList.get(i), false));
            }
        }

        return answerModals;
    }


    private void setRecylerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rvWrongAnswer.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        rvWrongAnswer.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new WrongAnswerAdapter(EvaluateActivity.this, wrongAnswers);

        rvWrongAnswer.setAdapter(mAdapter);
        rvWrongAnswer.addItemDecoration(new DividerItemDecoration(rvWrongAnswer.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void init() {

        rvWrongAnswer = findViewById(R.id.evalute_wrong_recycler);
        sPref = SharedPrefManager.getInstance();
        tvScore = findViewById(R.id.evaluate_score);
        yourAnswerList = UtilityFunctions.ConvertStringtoList(sPref.getString(SharedPrefManager.Key.YOUR_ANSWER), questioncount);
        correctAnswerList = UtilityFunctions.ConvertStringtoList(sPref.getString(SharedPrefManager.Key.CORRECT_ANSWER), questioncount);

    }
}
