package com.bshyong.android.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
	
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private ImageButton mBackButton;
	private TextView mQuestionTextView;
	
	// initialize array of questions
	private TrueFalse[] mQuestionBank = new TrueFalse[]{
		new TrueFalse(R.string.question_oceans, true),
		new TrueFalse(R.string.question_mideast, false),
		new TrueFalse(R.string.question_africa, false),
		new TrueFalse(R.string.question_americas, true),
		new TrueFalse(R.string.question_asia, true)
	};
	private int mCurrentIndex = 0;
	
	private void updateQuestion(){
        // reference ids are integers
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean userPressedTrue){
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		int messageResId = 0;
		
		if (userPressedTrue == answerIsTrue){
			messageResId = R.string.correct_toast;
		} else {
			messageResId = R.string.incorrect_toast;
		}
		
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inflate ActivityQuiz layout
        setContentView(R.layout.activity_quiz);
        
        // get reference for TextView and set text to current question at index
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        
        // add listener so clicking on TextView will go to next question
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
			}
		});
        
        // get and assign references to widgets
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkAnswer(true);
			}
		});

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkAnswer(false);
			}
		});
        
        // get reference to button
        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				Log.i("QuizActivity", "array index: " + mCurrentIndex);
				updateQuestion();
			}
		});
        // get reference to button
        mBackButton = (ImageButton)findViewById(R.id.previous_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCurrentIndex = ((mCurrentIndex - 1) % mQuestionBank.length + mQuestionBank.length)%mQuestionBank.length;
				Log.i("QuizActivity", "array index: " + mCurrentIndex);
				updateQuestion();
			}
		});
		updateQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }
    
}
