package com.example.bmi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button AdviceButton, CalculateButton;
    private EditText Weight, Height;
    private TextView Output;
    private RadioButton Imperial, Metric;

    final int IMPERIAL_CONVERSION = 703;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdviceButton = (Button)findViewById(R.id.getAdviceButton);
        CalculateButton = (Button)findViewById(R.id.calculateBMIButton);

        Weight = (EditText) findViewById(R.id.weightInput);
        Height = (EditText) findViewById(R.id.heightInput);

        Output = (TextView) findViewById(R.id.outputBMI);

        Imperial = (RadioButton) findViewById(R.id.imperialRadio);
        Metric = (RadioButton) findViewById(R.id.metricRadio);

        //Imperial to default
        Imperial.setSelected(true);

        AdviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdvice();
            }
        });

        CalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    public void getAdvice(){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        //Pass data into next activity
        String output_text = Output.getText().toString().trim();
        if(!checkOutput(output_text)) return;

        double bmi = Double.parseDouble(output_text);
        intent.putExtra("CALCULATED_BMI", bmi);
        //data passed into intent

        startActivity(intent);

    }

    boolean checkWeight(String weight){
        if(weight.isEmpty() || weight.length() == 0 || weight.equals("") || weight == null)
        {
            Toast.makeText(getApplicationContext(), "Enter integer for weight.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    boolean checkOutput(String output){
        if(output.isEmpty() || output.length() == 0 || output.equals("") || output == null)
        {
            Toast.makeText(getApplicationContext(), "Calculate BMI before getting advice.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    boolean checkHeight(String height){
        if(height.isEmpty() || height.length() == 0 || height.equals("") || height == null)
        {
            Toast.makeText(getApplicationContext(), "Enter integer for height.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean validData(){
        //Check data entered for weight
        String weight_text = Weight.getText().toString().trim();
        if(checkWeight(weight_text)){
            String stringWeight = Weight.getText().toString();
            int myWeight = Integer.parseInt(stringWeight);
            if(myWeight <= 0){
                Toast.makeText(getApplicationContext(), "Enter positive integer for weight.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            return false;
        }

        //Check data entered for height
        String height_text = Height.getText().toString().trim();
        if(checkHeight(height_text)){
            String stringHeight = Height.getText().toString();
            int myHeight = Integer.parseInt(stringHeight);
            if(myHeight <= 0){
                Toast.makeText(getApplicationContext(), "Enter positive integer for height.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            return false;
        }

        return true;
    }

    public void calculateBMI(){
        if(!validData()) return;

        int myWeight = Integer.parseInt(Weight.getText().toString());
        int myHeight = Integer.parseInt(Height.getText().toString());

        double bmi = 0.0;

        if(Imperial.isChecked()){
            bmi = Math.round(((myWeight * IMPERIAL_CONVERSION)*(1.0)/(myHeight * myHeight)) * 100.0) / 100.0;
        }else if(Metric.isChecked()){
            bmi = Math.round((myWeight * (1.0)/(myHeight * myHeight)) * 100.0) / 100.0;
        }
        Output.setText("" + bmi);
    }
}