package com.example.omr.Adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omr.Modals.SharedPrefManager;
import com.example.omr.R;
import com.example.omr.Utils.Constants;
import com.example.omr.Utils.UtilityFunctions;

import java.util.ArrayList;
import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerViewHolder> {

    Context context;
    int questionCount;
    String TAG = "hello";
    SharedPrefManager sPref = SharedPrefManager.getInstance();
    List<Character> yourAnswerCharacterList;
    List<Character> correctAnswerCharacterList;

    public AnswerAdapter(int questionCount, List<Character> yourAnswerCharacterList, List<Character> correctAnswerCharacterList) {
        this.questionCount = questionCount;
        this.yourAnswerCharacterList = yourAnswerCharacterList;
        this.correctAnswerCharacterList = correctAnswerCharacterList;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_omr_element, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AnswerViewHolder holder, final int position) {


        holder.tvQuestionNumber.setText("" + (position + 1) + ".");
        Log.d(TAG, "onBindViewHolder: " + position);

        switch (yourAnswerCharacterList.get(position)) {
            case 'A':
                clearAllButtons(holder);
                holder.btnA.setBackgroundColor(Constants.MARKED_BUTTON_COLOR);
                break;
            case 'B':
                clearAllButtons(holder);
                holder.btnB.setBackgroundColor(Constants.MARKED_BUTTON_COLOR);
                break;
            case 'C':
                clearAllButtons(holder);
                holder.btnC.setBackgroundColor(Constants.MARKED_BUTTON_COLOR);
                break;
            case 'D':
                clearAllButtons(holder);
                holder.btnD.setBackgroundColor(Constants.MARKED_BUTTON_COLOR);
                break;
            case 'N':
                clearAllButtons(holder);
        }

        holder.btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllButtons(holder);
                holder.btnA.setBackgroundColor(Constants.MARKED_BUTTON_COLOR);
                yourAnswerCharacterList.set(position, 'A');
                String newString = UtilityFunctions.ConvertListtoString(yourAnswerCharacterList, questionCount);
                Log.d(TAG, "onBindViewHolder: " + newString);
                sPref.put(SharedPrefManager.Key.YOUR_ANSWER, newString);

            }
        });

        holder.btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllButtons(holder);
                holder.btnB.setBackgroundColor(Constants.MARKED_BUTTON_COLOR);
                yourAnswerCharacterList.set(position, 'B');
                String newString = UtilityFunctions.ConvertListtoString(yourAnswerCharacterList, questionCount);
                sPref.put(SharedPrefManager.Key.YOUR_ANSWER, newString);
                Log.d(TAG, "onBindViewHolder: " + sPref.getString(SharedPrefManager.Key.YOUR_ANSWER));
            }
        });

        holder.btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllButtons(holder);
                holder.btnC.setBackgroundColor(Constants.MARKED_BUTTON_COLOR);
                yourAnswerCharacterList.set(position, 'C');
                String newString = UtilityFunctions.ConvertListtoString(yourAnswerCharacterList, questionCount);
                sPref.put(SharedPrefManager.Key.YOUR_ANSWER, newString);
                Log.d(TAG, "onBindViewHolder: " + newString);

            }
        });

        holder.btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllButtons(holder);
                holder.btnD.setBackgroundColor(Constants.MARKED_BUTTON_COLOR);
                yourAnswerCharacterList.set(position, 'D');
                String newString = UtilityFunctions.ConvertListtoString(yourAnswerCharacterList, questionCount);
                sPref.put(SharedPrefManager.Key.YOUR_ANSWER, newString);
                Log.d(TAG, "onBindViewHolder: " + newString);

            }
        });

        holder.btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAnswerCharacterList.set(position, yourAnswerCharacterList.get(position));
                String newString = UtilityFunctions.ConvertListtoString(correctAnswerCharacterList, questionCount);
                sPref.put(SharedPrefManager.Key.CORRECT_ANSWER, newString);
            }
        });


        holder.btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ViewGroup viewGroup = holder.(android.R.id.content);
                Button btnWrongA, btnWrongB, btnWrongC, btnWrongD;
                ImageView imgCross;

                View dialogView = LayoutInflater.from(context).inflate(R.layout.wrong_dialog, null, false);
                //Now we need an AlertDialog.Builder object
                btnWrongA = dialogView.findViewById(R.id.wrong_a);
                btnWrongB = dialogView.findViewById(R.id.wrong_b);
                btnWrongC = dialogView.findViewById(R.id.wrong_c);
                btnWrongD = dialogView.findViewById(R.id.wrong_d);
                imgCross = dialogView.findViewById(R.id.wrong_cross);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(dialogView);

                final AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setCancelable(false);

                alertDialog.show();
                switch (yourAnswerCharacterList.get(position)) {
                    case 'A':
                        btnWrongA.setEnabled(false);
                        break;
                    case 'B':
                        btnWrongB.setEnabled(false);
                        break;
                    case 'C':
                        btnWrongC.setEnabled(false);
                        break;
                    case 'D':
                        btnWrongD.setEnabled(false);
                        break;

                }


                btnWrongA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.btnA.setBackgroundColor(Constants.CORRECTED_BUTTON_COLOR);
                        setCorrectAnswer(position, 'A');
                        alertDialog.dismiss();
                    }
                });


                btnWrongB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.btnB.setBackgroundColor(Constants.CORRECTED_BUTTON_COLOR);
                        setCorrectAnswer(position, 'B');
                        alertDialog.dismiss();
                    }
                });


                btnWrongC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.btnC.setBackgroundColor(Constants.CORRECTED_BUTTON_COLOR);
                        setCorrectAnswer(position, 'C');
                        alertDialog.dismiss();
                    }
                });


                btnWrongD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.btnD.setBackgroundColor(Constants.CORRECTED_BUTTON_COLOR);
                        setCorrectAnswer(position, 'D');
                        alertDialog.dismiss();
                    }
                });

                imgCross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


            }

            private void setCorrectAnswer(int position, char d) {
                correctAnswerCharacterList.set(position, d);
                String newString = UtilityFunctions.ConvertListtoString(correctAnswerCharacterList, questionCount);
                sPref.put(SharedPrefManager.Key.CORRECT_ANSWER, newString);
            }
        });


    }


    private void clearAllButtons(AnswerViewHolder holder) {
        holder.btnA.setBackgroundColor(Constants.DEFAULT_BUTTON_COLOR);
        holder.btnB.setBackgroundColor(Constants.DEFAULT_BUTTON_COLOR);
        holder.btnC.setBackgroundColor(Constants.DEFAULT_BUTTON_COLOR);
        holder.btnD.setBackgroundColor(Constants.DEFAULT_BUTTON_COLOR);
    }

    @Override
    public int getItemCount() {
        return questionCount;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}

class AnswerViewHolder extends RecyclerView.ViewHolder {

    Button btnA, btnB, btnC, btnD, btnCorrect, btnWrong;
    TextView tvQuestionNumber;


    public AnswerViewHolder(@NonNull View itemView) {
        super(itemView);
        btnA = itemView.findViewById(R.id.sheet_option1);
        btnB = itemView.findViewById(R.id.sheet_option2);
        btnC = itemView.findViewById(R.id.sheet_option3);
        btnD = itemView.findViewById(R.id.sheet_option4);
        tvQuestionNumber = itemView.findViewById(R.id.sheet_question_number);
        btnCorrect = itemView.findViewById(R.id.sheet_correct);
        btnWrong = itemView.findViewById(R.id.sheet_wrong);
    }
}
