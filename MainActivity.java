package com.arjuj.eggtimerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter=1;
    boolean stopIsOn=false;
    CountDownTimer cdTimer;


    public void updateTimer(int secondsLeft) {

        SeekBar seekBar=(SeekBar)findViewById(R.id.seekBar);
        final TextView setTimer=(TextView) findViewById(R.id.setTimer);
        int minutes=secondsLeft/60;
        int seconds=secondsLeft-(minutes*60);

        seekBar.setProgress(secondsLeft);

        String secondString=Integer.toString(seconds);

        if(Integer.parseInt(secondString)<10)
        {
            secondString="0"+secondString;
        }

        setTimer.setText(Integer.toString(minutes)+":"+secondString);



    }

    public void startButton(View view) {

        Button button1=(Button)findViewById(R.id.button1);
        final SeekBar seekBar=(SeekBar)findViewById(R.id.seekBar);
        final TextView textView=(TextView) findViewById(R.id.textView);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final TextView setTimer=(TextView) findViewById(R.id.setTimer);

        imageView.setImageResource(R.drawable.egg);

        if(stopIsOn) {
        seekBar.setEnabled(true);
        cdTimer.cancel();
        button1.setText("t o u c h   t o   s t a r t ");
        stopIsOn=false;
        textView.setText("s e t   t i m e ");
        }

        else {

            stopIsOn=true;
            seekBar.setEnabled(false);
            button1.setText("t o u c h   t o   s t o p ");
            cdTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                public void onTick(long msud) {
                    Log.i("Seconds left", String.valueOf(msud / 1000));

                    textView.setText("      s t a r t e d . . .");

                    updateTimer((int) msud / 1000);


                }

                public void onFinish() {
                    textView.setText("d o n e.   t r y   a g a i n ?");
                    Button button1 = (Button) findViewById(R.id.button1);
                    button1.setText("t o u c h   t o   s t a r t ");
                    counter++;

                    imageView.setImageResource(R.drawable.broken);
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.cracksound);
                    mediaPlayer.start();
                    seekBar.setEnabled(true);
                    stopIsOn=false;


                }
            }.start();
        }






    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_layout);


        int Max=1000;


        SeekBar seekBar=(SeekBar)findViewById(R.id.seekBar);
        final TextView setTimer=(TextView) findViewById(R.id.setTimer);

        seekBar.setMax(1800);
        seekBar.setProgress(900);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override

            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int stepSize=5;
                i = ((int)Math.round(i/stepSize))*stepSize;
                int minutes=i/60;
                int seconds=i-(minutes*60);

                seekBar.setProgress(i);

                String secondString=Integer.toString(seconds);

                if(Integer.parseInt(secondString)<10)
                {
                    secondString="0"+secondString;
                }

                setTimer.setText(Integer.toString(minutes)+":"+secondString);

                

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }



        });





    }
}
