package com.example.calculatorappthota;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAc, buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        /*
        Extra Feature 1: I added a text box above the final calculation output text box
        in order for the app to be more user convenient. I was able to do so by researching
        online and finding websites dedicated to android app development. (abhiandroid - website)
         */
        solutionTv = findViewById(R.id.solution_tv);
        /*
        Extra Feature 2: Another one of the additional features I added was having a button for each number
        and mathematical operation. I was able to learn the in's and out's of how to tackle multiple
        multiple buttons through online tutorials of how to connect the id so that when each button is
        clicked the correct mathematical operation takes place.

         */
        assignId(buttonC, R.id.button_c);
        assignId(buttonBrackOpen, R.id.button_open_bracket);
        assignId(buttonBrackClose, R.id.button_close_bracket);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonPlus, R.id.button_add);
        assignId(buttonMinus, R.id.button_subtract);
        assignId(buttonEquals, R.id.button_equals);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonAc, R.id.button_ac);
        assignId(buttonDot, R.id.button_decimal_point);
    }

    /*
    Title: Easy Tuto
    Purpose: Assigns each button with it's correct ID
    and also procedes to follow the sequences needed for
    each particular button when clicked by user.
     */
    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        }
        else{
            dataToCalculate = dataToCalculate + buttonText;
        }

        solutionTv.setText(dataToCalculate);

        /*
        Extra Feature 3: Without using if-else statements, I learned to include a
        dependency(library module) in the Gradle which allowed me to be more efficient in
        my code with it calculating all mathematical operations. This further allowed me to
        output the final result of the calculation without the user clicking a "submit" or "see
        results" type of button, thus making the user experience run more smoothly.
         */
        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Error")){
            resultTv.setText(finalResult);
        }
    }

    /*
    Title: Easy Tuto
    Purpose: Context is used to get "information" about the MainActivity, providing it
    access to various functionalities(newly-created object understands what's going on).

     */
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1); //
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;

        }
        catch (Exception e) {
            return "Error";
        }

    }
}