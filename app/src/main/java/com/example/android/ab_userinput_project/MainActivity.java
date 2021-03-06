package com.example.android.ab_userinput_project;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextAnswer;
    RadioGroup radioGroup;
    RadioButton radioButtonOne;
    RadioButton radioButtonTwo;
    RadioButton radioButtonThree;
    CheckBox checkBoxOne;
    CheckBox checkBoxTwo;
    CheckBox checkBoxThree;
    NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        editTextName = findViewById(R.id.name_view);
        editTextAnswer = findViewById(R.id.answer_view);
        radioGroup = findViewById(R.id.radioGroup_view);
        radioButtonOne = findViewById(R.id.radioButton_one_view);
        radioButtonTwo = findViewById(R.id.radioButton_two_view);
        radioButtonThree = findViewById(R.id.radioButton_three_view);
        checkBoxOne = findViewById(R.id.checkbox_one_view);
        checkBoxTwo = findViewById(R.id.checkbox_two_view);
        checkBoxThree = findViewById(R.id.checkbox_three_view);
        numberPicker = findViewById(R.id.numberPicker_view);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        editTextAnswer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }

    public void submitAnswer(View view) {

        int score = 0;
        String name = editTextName.getText().toString();

        if (editTextName.getText().toString().trim().length() != 0) {
            if (radioGroup.getCheckedRadioButtonId() == -1) {

            } else {
                int radioButtonAnsweredId = radioGroup.getCheckedRadioButtonId();
                RadioButton checkedButton = radioGroup.findViewById(radioButtonAnsweredId);

                if (checkedButton.getId() == radioButtonOne.getId()) {
                    score++;
                }
            }

            if (checkBoxOne.isChecked() == true && checkBoxTwo.isChecked() == true && checkBoxThree.isChecked() == false) {
                score++;
            }

            if (numberPicker.getValue() == 8) {
                score++;
            }

            String etAnswer = editTextAnswer.getText().toString();
            if (etAnswer.equalsIgnoreCase("wilson")) {
                score++;
            }

            String scoreString= String.valueOf(score);

            Toast toast = Toast.makeText(view.getContext(), getResources().getString(R.string.toastComplete, name, scoreString), Toast.LENGTH_LONG);
            toast.show();
        } else {
            Toast toast = Toast.makeText(view.getContext(), getResources().getString(R.string.toastError), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
