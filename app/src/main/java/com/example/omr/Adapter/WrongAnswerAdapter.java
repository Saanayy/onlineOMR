package com.example.omr.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omr.Modals.AnswerModal;
import com.example.omr.R;
import com.example.omr.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class WrongAnswerAdapter extends RecyclerView.Adapter<WrongAnswerViewHolder> {


    Context context;
    List<AnswerModal> wrongAnswers = new ArrayList<>();

    public WrongAnswerAdapter(Context context, List<AnswerModal> wrongAnswers) {
        this.context = context;
        this.wrongAnswers = wrongAnswers;
    }

    @NonNull
    @Override
    public WrongAnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_wrong_answer_element, parent, false);

        return new WrongAnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WrongAnswerViewHolder holder, int position) {

        holder.tvQuestionNumber.setText(""+wrongAnswers.get(position).getQuestionNumber());
        holder.tvyourAnswer.setText(""+wrongAnswers.get(position).getYourAnswer());
        holder.tvyourAnswer.setTextColor(Constants.MARKED_BUTTON_COLOR);

        holder.tvCorrectAnswer.setText(""+wrongAnswers.get(position).getCorrectAnswer());
        holder.tvCorrectAnswer.setTextColor(Constants.CORRECTED_BUTTON_COLOR);


    }

    @Override
    public int getItemCount() {
        return wrongAnswers.size();
    }
}


class WrongAnswerViewHolder extends RecyclerView.ViewHolder {

    TextView tvQuestionNumber, tvyourAnswer, tvCorrectAnswer;

    public WrongAnswerViewHolder(@NonNull View itemView) {
        super(itemView);
        tvQuestionNumber = itemView.findViewById(R.id.wrong_answer_element_question_no);
        tvCorrectAnswer = itemView.findViewById(R.id.wrong_answer_element_correctanswer);
        tvyourAnswer = itemView.findViewById(R.id.wrong_answer_element_youranswer);

    }
}