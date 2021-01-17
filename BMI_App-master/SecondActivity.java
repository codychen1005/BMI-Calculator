package com.example.bmi_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {

    private TextView Output;

    private ImageView NormalImage, UnderweightImage, OverweightImage, ObeseImage;

    final double NORMAL_LOW = 18.50;
    final double NORMAL_HIGH = 25.00;
    final double OBESE = 30.00;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Output = (TextView) findViewById(R.id.outputBMI);

        ImageView UnderweightImage =(ImageView) findViewById(R.id.underweight_image_view);
        UnderweightImage.setVisibility(View.INVISIBLE);

        ImageView NormalImage = (ImageView) findViewById(R.id.normal_image_view);
        NormalImage.setVisibility(View.INVISIBLE);

        ImageView OverweightImage =(ImageView) findViewById(R.id.overweight_image_view);
        OverweightImage.setVisibility(View.INVISIBLE);

        ImageView ObeseImage =(ImageView) findViewById(R.id.obese_image_view);
        ObeseImage.setVisibility(View.INVISIBLE);

        double bmi = 0.0;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            bmi = extras.getDouble("CALCULATED_BMI");
        }

        if(bmi < NORMAL_LOW){ //underweight
            Output.setText("Underweight");
            UnderweightImage.setVisibility(View.VISIBLE);
        }else if(bmi >= NORMAL_LOW && bmi < NORMAL_HIGH){ //normal
            Output.setText("Normal");
            NormalImage.setVisibility(View.VISIBLE);

        }else if(bmi >= NORMAL_HIGH && bmi < OBESE){ //Overweight
            Output.setText("Overweight");
            OverweightImage.setVisibility(View.VISIBLE);

        }else{ //Obese
            Output.setText("Obese");
            ObeseImage.setVisibility(View.VISIBLE);
        }

    }
}
