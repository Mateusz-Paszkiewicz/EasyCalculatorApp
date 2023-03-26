package com.example.easycalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv, solution_tv;
    MaterialButton button_c, button_ac, button_open_bracket, button_close_bracket,
            button_divide, button_multiply, button_plus, button_minus, button_equal,
            button_dot, button_1, button_2, button_3, button_4, button_5, button_6, button_7,
            button_8, button_9, button_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_tv = findViewById(R.id.result_tv);
        solution_tv = findViewById(R.id.solution_tv);

        assignId(button_c, R.id.button_c);
        assignId(button_ac, R.id.button_clear);
        assignId(button_open_bracket, R.id.button_open_bracket);
        assignId(button_close_bracket, R.id.button_close_bracket);
        assignId(button_multiply, R.id.button_multiply);
        assignId(button_divide, R.id.button_divide);
        assignId(button_plus, R.id.button_plus);
        assignId(button_minus, R.id.button_minus);
        assignId(button_equal, R.id.button_equal);
        assignId(button_dot, R.id.button_dot);
        assignId(button_equal, R.id.button_equal);
        assignId(button_0, R.id.button_0);
        assignId(button_1, R.id.button_1);
        assignId(button_2, R.id.button_2);
        assignId(button_3, R.id.button_3);
        assignId(button_4, R.id.button_4);
        assignId(button_5, R.id.button_5);
        assignId(button_6, R.id.button_6);
        assignId(button_7, R.id.button_7);
        assignId(button_8, R.id.button_8);
        assignId(button_9, R.id.button_9);
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_tv.getText().toString();

        if(buttonText.equals("AC")) {
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        else if(buttonText.equals("C")){
            if(dataToCalculate.length() == 1 || dataToCalculate.length() == 0){
                solution_tv.setText("0");
                result_tv.setText("0");
                return;
            } else {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else if(buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        solution_tv.setText(dataToCalculate);

        if(buttonText.equals("0") || buttonText.equals("1") || buttonText.equals("2")
                || buttonText.equals("3") || buttonText.equals("4") || buttonText.equals("5")
                || buttonText.equals("6") || buttonText.equals("7") || buttonText.equals("8")
                || buttonText.equals("9")) {
            String finalResult = getResult(dataToCalculate);

            if (finalResult == "Err") {
                result_tv.setText("Error");
            } else {
                result_tv.setText(finalResult);
            }
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}