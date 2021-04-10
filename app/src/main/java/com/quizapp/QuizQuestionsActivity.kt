package com.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPostition: Int = 1;
    private var mQuestionsList: ArrayList<Question>? = null;
    private var mSelectedOption: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionsList = Constants.getQuestions();
        setQuestion();
        defaultOptionsView();

        tv_option_one.setOnClickListener(this);
        tv_option_two.setOnClickListener(this);
        tv_option_three.setOnClickListener(this);
        tv_option_four.setOnClickListener(this);

    }

    private fun setQuestion() {
        mCurrentPostition = 1;
        val question: Question? = mQuestionsList!![mCurrentPostition - 1];

        progress_bar.progress = mCurrentPostition;
        tv_progress.text = "$mCurrentPostition" + "/" + progress_bar.max;

        tv_question.text = question!!.question;
        iv_image.setImageResource(question.image);
        tv_option_one.text = question.optionOne;
        tv_option_two.text = question.optionTwo;
        tv_option_three.text = question.optionThree;
        tv_option_four.text = question.optionFour;
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>();
        options.add(0, tv_option_one);
        options.add(1, tv_option_two);
        options.add(2, tv_option_three);
        options.add(3, tv_option_four);

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"));
            option.typeface = Typeface.DEFAULT;
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_background_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(tv_option_one, 1);
            }
            R.id.tv_option_two -> {
                selectedOptionView(tv_option_two, 2);
            }
            R.id.tv_option_three -> {
                selectedOptionView(tv_option_three, 3);
            }
            R.id.tv_option_four -> {
                selectedOptionView(tv_option_four, 4);
            }
        }
    }



    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView();
        mSelectedOption = selectedOptionNum;
        tv.setTextColor(Color.parseColor("#363A43"));
        tv.setTypeface(tv.typeface,Typeface.BOLD);
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_options_border
        )

    }
}