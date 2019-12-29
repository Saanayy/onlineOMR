package com.example.omr.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omr.Adapter.AnswerAdapter;
import com.example.omr.Modals.SharedPrefManager;
import com.example.omr.R;
import com.example.omr.Utils.UtilityFunctions;

import java.util.ArrayList;
import java.util.List;

public class SheetActivity extends AppCompatActivity {

    RecyclerView rvOMRlist;
    SharedPrefManager sPref;
    AnswerAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    int questioncount;
    List<Character> yourAnswerCharacterList = new ArrayList<Character>();
    List<Character> correctAnswerCharacterList = new ArrayList<Character>();

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing app")
                .setMessage("Are you sure you want to close this app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sheet_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.evaluate:
                Intent openEvaluateIntent = new Intent(SheetActivity.this, EvaluateActivity.class);
                openEvaluateIntent.putExtra("questioncount", questioncount);
                startActivity(openEvaluateIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        questioncount = bundle.getInt("questioncount", 20);

        init();


        String yourString = sPref.getString(SharedPrefManager.Key.YOUR_ANSWER);
        yourAnswerCharacterList = UtilityFunctions.ConvertStringtoList(yourString, questioncount);

        String correctString = sPref.getString(SharedPrefManager.Key.CORRECT_ANSWER);
        correctAnswerCharacterList = UtilityFunctions.ConvertStringtoList(correctString, questioncount);

        setRecylerView();


    }


    private void setRecylerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rvOMRlist.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        rvOMRlist.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AnswerAdapter(questioncount, yourAnswerCharacterList, correctAnswerCharacterList);
        rvOMRlist.setAdapter(mAdapter);
        rvOMRlist.addItemDecoration(new DividerItemDecoration(rvOMRlist.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void init() {
        rvOMRlist = findViewById(R.id.sheet_recycler_list);
        sPref = SharedPrefManager.getInstance();

    }


}
