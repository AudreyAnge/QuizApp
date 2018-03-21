package com.example.android.quizcooking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private LinearLayout resultQuizLayout;
    private ScrollView quizScrollView;
    private TextView resultText;
    private ImageView resultImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultQuizLayout = findViewById(R.id.result_quiz_layout);
        quizScrollView = findViewById(R.id.quiz_scroll_view);
        resultText = findViewById(R.id.result_text);
        resultImageView = findViewById(R.id.result_image_view_id);
    }

    /**this method is called when the user submit his answers
    by clicking on the button Submit*/
    public void submit(View view) {
        //compare the right answer to the user answer for question 1
        RadioGroup radioGroup_quiz1 = findViewById(R.id.radio_group_quiz1);
        RadioButton radioButton_quiz1 = findViewById(radioGroup_quiz1.getCheckedRadioButtonId());
        String user_answer1 = "";
        if (radioButton_quiz1 != null){
            user_answer1 = radioButton_quiz1.getText().toString();
        }
        String answer1 = getString(R.string.answer1_quiz1);
        if (answer1.equals(user_answer1)){
            score += 1;
        }

        //compare the right answer to the user answer for question 2
        CheckBox eggCheckbox = findViewById(R.id.egg_checkbox);
        CheckBox milkCheckbox = findViewById(R.id.milk_checkbox);
        CheckBox sugarCheckbox = findViewById(R.id.sugar_checkbox_id);
        CheckBox butterCheckbox = findViewById(R.id.butter_checkbox_id);
        boolean isEggChecked = eggCheckbox.isChecked();
        boolean isMilkChecked = milkCheckbox.isChecked();
        boolean isSugarChecked = sugarCheckbox.isChecked();
        boolean isButterChecked = butterCheckbox.isChecked();
        if ((isEggChecked && isSugarChecked) && (!isMilkChecked && !isButterChecked)){
            score += 1;
        }

        //compare the right answer to the user answer for question 3
        EditText temperature = findViewById(R.id.temp_editText_id);
        String userTemperature = temperature.getText().toString();
        int answer3 = getResources().getInteger(R.integer.water_boiled_temperature);
        if (String.valueOf(answer3).equals(userTemperature)){
            score += 1;
        }

        //compare the right answer to the user answer for question 4
        CheckBox strawberryCheckbox = findViewById(R.id.strawberry_checkbox_id);
        CheckBox raspberryCheckbox = findViewById(R.id.raspberry_checkbox_id);
        CheckBox bananaCheckbox = findViewById(R.id.banana_checkbox_id);
        boolean isStrawberryChecked = strawberryCheckbox.isChecked();
        boolean isRaspberryChecked = raspberryCheckbox.isChecked();
        boolean isBananaChecked = bananaCheckbox.isChecked();
        if ((isStrawberryChecked && isRaspberryChecked) && !isBananaChecked){
            score += 1;
        }

        //compare the right answer to the user answer for question 5
        EditText cheese = findViewById(R.id.cheese_id);
        String cheeseName = cheese.getText().toString().trim();
        String answer5 = getString(R.string.answer5);
        if (answer5.equals(cheeseName)){
            score += 1;
        }

        //compare the right answer to the user answer for question 6
        RadioGroup radioGroup_quiz6 = findViewById(R.id.radioGroup_quiz6);
        RadioButton radioButton_quiz6 = findViewById(radioGroup_quiz6.getCheckedRadioButtonId());
        String user_answer6 = "";
        if (radioButton_quiz6 != null){
            user_answer6 = radioButton_quiz6.getText().toString();
        }
        String answer6 = getString(R.string.answer2_quiz6);
        if (answer6.equals(user_answer6)){
            score += 1;
        }

        setResultViews(resultText, resultImageView);
        //switch the scrollView that contains quiz to the layout that contains result
        quizScrollView.setVisibility(View.GONE);
        resultQuizLayout.setVisibility(View.VISIBLE);
        //show toast message
        Toast.makeText(this, getString(R.string.toast_message, score), Toast.LENGTH_SHORT).show();
    }

    /**
     * Initialize views when the user submit his answers.
     */
    private void setResultViews(TextView resultText, ImageView imageView) {
        if (score > 3){
            resultText.setText(getString(R.string.excellent_result_text,score));
            imageView.setImageResource(R.drawable.happy_emoticon);
        }else if (score < 3){
            resultText.setText(getString(R.string.bad_result_text, score));
            imageView.setImageResource(R.drawable.loose_emoticon);
        }else {
            resultText.setText(getString(R.string.good_result_text, score));
            imageView.setImageResource(R.drawable.cute_emoticon);
        }
    }

    /**to begin a new game when the button replay is clicked on*/
    public void replay(View view) {
        //clean up
        clearUserAnswers();
        //switch the layout that contains result to the scrollView that contains quiz
        resultQuizLayout.setVisibility(View.GONE);
        quizScrollView.setVisibility(View.VISIBLE);
        //set the score to zero because a new game begins
        score = 0;
    }

    /**to clean all user answers  before a new game*/
    private void clearUserAnswers() {
        LinearLayout quizLayout = findViewById(R.id.quiz_layout);
        for (int i = 0; i < quizLayout.getChildCount(); i++){
            View child = quizLayout.getChildAt(i);
            if (child instanceof EditText) {
                ((EditText) child).setText("");
            }
            if (child instanceof CheckBox) {
                ((CheckBox) child).setChecked(false);
            }
            if (child instanceof RadioGroup) {
                ((RadioGroup)child).clearCheck();
            }
        }
    }
}
