package com.github.kostaxy.calculator;

import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    EditText etValue1;
    EditText etValue2;
    TextView tvResult;
    TextView tvOperation;

    Button btnCalculate;
    Button btnAdd;
    Button btnSub;
    Button btnMul;
    Button btnDiv;

    Double result = null;
    String lastOperation = "+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult =(TextView) findViewById(R.id.result);
        etValue1 = (EditText) findViewById(R.id.value1);
        etValue2 = (EditText) findViewById(R.id.value2);
        tvOperation = (TextView) findViewById(R.id.operation);
        btnCalculate = (Button) findViewById(R.id.calculate);

        Button btnAdd = (Button) findViewById(R.id.btn_add);
        Button btnSub = (Button) findViewById(R.id.btn_sub);
        Button btnMul = (Button) findViewById(R.id.btn_mul);
        Button btnDiv = (Button) findViewById(R.id.btn_div);

        btnSub.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnCalculate.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_sub: changeOperation(v); break;
            case R.id.btn_add: changeOperation(v); break;
            case R.id.btn_div: changeOperation(v); break;
            case R.id.btn_mul: changeOperation(v); break;
            case R.id.calculate: calculate(); break;
        }
    }

    private void calculate() {
        double value1 = 0.0;
        double value2 = 0.0;
        try {
            value1 = Double.valueOf(etValue1.getText().toString());
            value2 = Double.valueOf(etValue2.getText().toString());
        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Ooops, incorrect values",Toast.LENGTH_LONG).show();
        }

        final double eps = 0.000001;
        double result = 0.0;
        switch (lastOperation){
            case "+":
                result = value1 + value2;
                tvResult.setText(String.valueOf(result));
                break;
            case "-":
                result = value1 - value2;
                tvResult.setText(String.valueOf(result));
                break;
            case "*":
                result = value1 * value2;
                tvResult.setText(String.valueOf(result));
                break;
            case "/":
                if (Math.abs(value2) > eps){
                    result = value1 / value2;
                    tvResult.setText(String.valueOf(result));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Division by zero",Toast.LENGTH_LONG).show();
                    tvResult.setText("Division by zero");
                }
                break;
            default:
                Toast.makeText(getApplicationContext(),"Something wrong",Toast.LENGTH_LONG).show();
                break;
        }

    }

    public void changeOperation(View v){
        Button btn = (Button) v;
        lastOperation = btn.getText().toString();
        tvOperation.setText(lastOperation);
    }
}
