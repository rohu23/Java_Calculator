package com.rtee.javacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultTV, solutionTV;
    MaterialButton button_c, button_open_bracket, button_close_bracket;
    MaterialButton button_divide, button_multiply, button_add, button_subtract;
    MaterialButton button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;

    MaterialButton button_point, button_AC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        resultTV = findViewById(R.id.resultTV);
        solutionTV = findViewById(R.id.solutionTV);

        assignId(button_subtract,R.id.button_subtract);
        assignId(button_divide, R.id.button_divide);
        assignId(button_point,R.id.button_point);
        assignId(button_AC,R.id.button_AC);
        assignId(button_add,R.id.button_add);
        assignId(button_c,R.id.button_c);
        assignId(button_multiply,R.id.button_multiply);
        assignId(button_close_bracket,R.id.button_close_bracket);
        assignId(button_open_bracket,R.id.button_open_bracket);
        assignId(button_c,R.id.button_c);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_0,R.id.button_0);
    }

    void assignId(MaterialButton btn, int id){
        btn =  findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTV.getText().toString();

        if(buttonText.equals("AC")){
            solutionTV.setText("");
            resultTV.setText("0");
        }
        if (buttonText.equals("=")){
            solutionTV.setText(resultTV.getText());
            return;
        }
        if (buttonText.equals("C") && solutionTV.getText()!="0"){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTV.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Error")){
                resultTV.setText(finalResult);
        }
    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data, "Javascript", 1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");

            }
            return finalResult;
        }catch(Exception e) {
            return "Error";
        }
    }
}