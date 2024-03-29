package com.example.tipcalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TipCalculator tipCalc;
    public NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tipCalc = new TipCalculator(0.17f, 100.0f);

        billEditText = (EditText) findViewById(R.id.amount_bill);
        tipEditText = (EditText) findViewById(R.id.amount_tip_percent);

        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);
    }

        public void calculate () {
            String billString = billEditText.getText().toString();
            String tipString = tipEditText.getText().toString();

            TextView tipTextView = (TextView) findViewById(R.id.amount_tip);
            TextView totalTextView = (TextView) findViewById(R.id.amount_total);
            try {
                // convert billString, tipString to floats
                float billAmount = Float.parseFloat(billString);
                int tipPercent = Integer.parseInt(tipString);

                // update the Model
                tipCalc.setBill(billAmount);
                tipCalc.setTip(.01f * tipPercent);
                // ask Model to calculate tip and total amounts
                float tip = tipCalc.tipAmount();
                float total = tipCalc.totalAmount();
                // update the View with formatted tip and total amounts tipTextView.setText(money.format(tip));
                tipTextView.setText(money.format(tip));
                totalTextView.setText(money.format(total));
            } catch (NumberFormatException nfe) {
                // pop up an alert view here
                System.out.println("Can't convert bill or tip number");
            }
        }

    public void calculate(View view) {
        Log.w("MainActivity", "v = " + view);
        billEditText = (EditText) findViewById(R.id.amount_bill);
        tipEditText = (EditText) findViewById(R.id.amount_tip_percent);
        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();
        TextView tipTextView = (TextView) findViewById(R.id.amount_tip);
        TextView totalTextView = (TextView) findViewById(R.id.amount_total);
        try {
            // convert billString, tipString to floats
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);

            // update the Model
            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);
            // ask Model to calculate tip and total amounts
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();
            // update the View with formatted tip and total amounts tipTextView.setText(money.format(tip));
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
        } catch (NumberFormatException nfe) {
            // pop up an alert view here
            System.out.println("Can't convert bill or tip number");
        }
    }

    private class TextChangeHandler implements TextWatcher {
            public void afterTextChanged(Editable e) {
                calculate();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void onTextChanged(CharSequence s, int start, int before, int after) {
            }
        }


}
