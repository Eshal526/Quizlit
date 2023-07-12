package com.example.quizlit;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class ResultFragment extends Fragment {
    private MyDatabaseHelper databaseHelper;
    private TextView resultTextView;

    private List<QuizResult> quizResults;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new MyDatabaseHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        resultTextView = view.findViewById(R.id.result_text_view);

        Bundle arguments = getArguments();
        if (arguments != null) {
            int questionCounter = arguments.getInt("questionCounter");
            SQLiteDatabase database = databaseHelper.getReadableDatabase();
            displayResults(database);
        }

        return view;
    }
    private SQLiteDatabase database;

    public void setDatabase(SQLiteDatabase database) {
        this.database =database;
    }
    private void displayResults(SQLiteDatabase database) {
        Cursor cursor = databaseHelper.getResult(database,1);

        StringBuilder resultBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
            int questionNumber = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_QUESTION_NUMBER));
            String result = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_RESULT));
            resultBuilder.append("Question ").append(questionNumber).append(": ").append(result).append("\n");
        }

        resultTextView.setText(resultBuilder.toString());
        cursor.close();
    }
    public void setQuizResults(List<QuizResult> quizResults) {
        this.quizResults = quizResults;
    }

    private void saveResultsToDatabase() {
        for (QuizResult quizResult : quizResults) {
            MyDatabaseHelper.insertResult(database, quizResult.getQuestionNumber(), quizResult.getUserAnswer(), quizResult.isCorrect());
        }
    }

}