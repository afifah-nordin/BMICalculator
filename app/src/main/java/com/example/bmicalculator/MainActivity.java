package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    EditText eweight, eheight;
    TextView result;
    Button btn, rst;
    private static DecimalFormat df = new DecimalFormat("0.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eweight = (EditText) findViewById(R.id.eweight);
        eheight = (EditText) findViewById(R.id.eheight);

        result = (TextView) findViewById(R.id.result);

        btn = (Button) findViewById(R.id.btn);
        rst = (Button) findViewById(R.id.rst);

        sharedPref = this.getSharedPreferences("height", Context.MODE_PRIVATE);


        sharedPref = this.getSharedPreferences("weight", Context.MODE_PRIVATE);
        String w = sharedPref.getString("weight", "");
        String h1 = sharedPref.getString("height", "");
//        sharedPref = this.getSharedPreferences("result", Context.MODE_PRIVATE);
////        String result1 = sharedPref.getString("result", "");

        eheight.setText(h1);
        eweight.setText(w);
//        result.setText(result1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                String w = eweight.getText().toString();
                String h = eheight.getText().toString();

                if (w.equals("")) {
                    eweight.setError("Please Enter Your Weight ");
                    eweight.requestFocus();
                    return;
                }
                if (h.equals("")) {
                    eheight.setError("Please Enter Your Height ");
                    eheight.requestFocus();
                    return;
                }

                float weight = Float.parseFloat(w);
                float height = Float.parseFloat(h);
                float cal = height / 100;


                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("height", h);
                editor.putString("weight", w);
//                editor.putString("result",i);
                editor.apply();

                float bmiVal = BMICalculate(weight, cal);

//                String i = df.format(bmiVal);

                result.setText(BMI(bmiVal));
//                result.setText("BMI= "+i);

            }
        });

        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                eheight.setText("");
                eweight.setText("");
                result.setText("");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.bmi) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

       else if (id == R.id.about) {
            Intent intent = new Intent(MainActivity.this, profile.class);
            startActivity(intent);
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }



    public float BMICalculate(float weight, float height) {
        return weight / (height * height);
    }
        public String BMI(float bmiVal)
        {  String i = df.format(bmiVal);
//            result.setText("BMI= "+i);
            if (bmiVal<18.5) {
                return i + " = Underweight & Malnutrition Risk";

            } else if (bmiVal<25) {
                return i+ " = Normal & Low risk";

            } else if (bmiVal<30) {
                return i+ " = Overweight & Enhanced risk";

            } else if (bmiVal<35) {
                return i+ " = Moderately Obese & Medium Risk";

            } else if (bmiVal<40) {
                return i+ " = Severely Obese & High Risk";

            } else if (bmiVal>40) {
                return i+" = Very Severely Obes & Very High Risk";

            } else
                return "Please enter your weight and height correctly";

        }


}