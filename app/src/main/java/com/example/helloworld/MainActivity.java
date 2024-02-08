package com.example.helloworld;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Button countdownButton;
    private CountDownTimer countDownTimer;
    private long countdownToMillis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button expandBtn = findViewById(R.id.buttonExp);
        expandBtn.setOnClickListener(new View.OnClickListener() {
            float scale=1f;
            @Override
            public void onClick(View v) {
                v.setScaleX(scale);
                scale+=0.1;
            }
        });
        Button btnCnt=findViewById(R.id.buttonCounter);
        btnCnt.setOnClickListener(new View.OnClickListener(){
            int clickCount=0;
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(clickCount<=20){
                    btnCnt.setText(String.valueOf(clickCount));
                    clickCount++;
                }else{
                    btnCnt.setText("I'm tired :(");
                    btnCnt.setEnabled(false);
                }
            }
        });
        Button btncntD=findViewById(R.id.CntDwnBtn);
        btncntD.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }

            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
            Date futureDate = null;
            try {
                futureDate = format.parse("01.03.2024 11:40");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (futureDate != null) {
                long millisUntilFinished = futureDate.getTime() - System.currentTimeMillis();
                countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {

                    public void onTick(long millisUntilFinished) {
                        int seconds = (int) (millisUntilFinished / 1000) % 60 ;
                        int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);
                        int hours   = (int) ((millisUntilFinished / (1000*60*60)) % 24);
                        int days = (int) (millisUntilFinished / (1000*60*60*24));
                        String timeRemaining = days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds remaining.";
                        Toast.makeText(getApplicationContext(), timeRemaining, Toast.LENGTH_SHORT).show();
                        countDownTimer.cancel();
                    }

                    public void onFinish() {
                        Toast.makeText(getApplicationContext(), "Countdown finished!", Toast.LENGTH_SHORT).show();
                    }
                }.start();
            }
        }
    });

    }

}