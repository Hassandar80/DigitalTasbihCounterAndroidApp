package com.hassandar.digitaltasbih;

import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaCas;
import android.media.MediaCasException;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Vibrator 1
    Vibrator mVibrator;
    Window window;
    ImageButton btnVibrate, btnWatch, resftz, BTureVibrator, btnLed;
    int countDialor=0, countBG=0;
    boolean isTureVibrator =true;
    private Handler mHandler = new Handler();
    LinearLayout linearLayout;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // FULL SCREEN
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // -- FULL SCREEN

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer playSound = MediaPlayer.create(this, R.raw.minikbutton);
        final MediaPlayer playSound1 = MediaPlayer.create(this, R.raw.click);


        linearLayout = findViewById(R.id.mainLayout);
        btnWatch = findViewById(R.id.watch);
        btnLed = findViewById(R.id.btnLed);


        final TextView myTextView = (TextView) findViewById(R.id.textAd);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/digital.ttf");
        myTextView.setTypeface(typeface);



        btnVibrate=(ImageButton)findViewById(R.id.CounntAdd);
        resftz = (ImageButton)findViewById(R.id.restz);
        BTureVibrator = (ImageButton)findViewById(R.id.addvibrator);
        // Vibrator 2
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btnVibrate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vibrates 3 : for 100 Milliseconds
                if(countBG==1)
                {
                    btnVibrate.setBackgroundResource(R.drawable.pressedled);
                    playSound.start();
                }
                else{
                    btnVibrate.setBackgroundResource(R.drawable.pressed);
                    playSound.start();
                }
                if(isTureVibrator){ mVibrator.vibrate(100);}

                count++;
                myTextView.setText( ""+count);

                    mHandler.postDelayed(new Runnable() {
                        public void run() {
                            if(countBG==1){
                                btnVibrate.setBackgroundResource(R.drawable.standardled);
                            }
                            else{
                                btnVibrate.setBackgroundResource(R.drawable.standard);
                            }
                        }
                    }, 100);


            }
        });

        resftz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vibrates 3 : for 100 Milliseconds
                playSound1.start();
                if(isTureVibrator){ mVibrator.vibrate(100);}
                count = 0;
                myTextView.setText( ""+count);

                //screenBrightness
              //  WindowManager.LayoutParams layout = getWindow().getAttributes();
              //  layout.screenBrightness = 1F; // 0.0F to 1.0F -- 20% = 0.2f
              ///  getWindow().setAttributes(layout);
            }
        });

        BTureVibrator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vibrates 3 : for 100 Milliseconds
                mVibrator.vibrate(100);
                if(isTureVibrator){
                    isTureVibrator=false;
                    BTureVibrator.setImageResource(R.drawable.vibrate_off);
                }
                else {
                    isTureVibrator=true;
                    BTureVibrator.setImageResource(R.drawable.vibrate_on);
                }

            }
        });



    }

    public void ledOnOff(View view) {
        final MediaPlayer play = MediaPlayer.create(this,R.raw.click);
        play.start();
        if(countBG==0){
            linearLayout.setBackgroundResource(R.drawable.bgled);
            btnWatch.setBackgroundResource(R.drawable.d6);
            resftz.setBackgroundResource(R.drawable.standardled);
            btnVibrate.setBackgroundResource(R.drawable.standardled);
            btnLed.setBackgroundResource(R.drawable.standardled);

            countBG=1;
        }
        else{
            linearLayout.setBackgroundResource(R.drawable.bg);
            btnWatch.setBackgroundResource(R.drawable.d1);
            resftz.setBackgroundResource(R.drawable.standard);
            btnVibrate.setBackgroundResource(R.drawable.standard);
            btnLed.setBackgroundResource(R.drawable.standard);
            countBG=0;
        }
    }

    public void changeTheme(View view) {
        if(countBG==0){
            if(countDialor==0){
                btnWatch.setBackgroundResource(R.drawable.d2);
                countDialor=1;
            }
            else if(countDialor==1){
                btnWatch.setBackgroundResource(R.drawable.d3);
                countDialor=2;
            }
            else if(countDialor==2){
                btnWatch.setBackgroundResource(R.drawable.d4);
                countDialor=3;
            }
            else if(countDialor==3){
                btnWatch.setBackgroundResource(R.drawable.d5);
                countDialor=4;
            }
            else {
                btnWatch.setBackgroundResource(R.drawable.d1);
                countDialor=0;
            }
        }
    }
}
