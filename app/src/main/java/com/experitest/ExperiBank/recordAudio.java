package com.experitest.ExperiBank;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class recordAudio extends Activity {
    class MyTimerTask extends TimerTask
    {
        Calendar myCalender = Calendar.getInstance();
        MyTimerTask(Calendar myCalender)
        {
            this.myCalender = myCalender;
        }


        @Override
        public void run() {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            Date t = myCalender.getTime();
            t.setSeconds(t.getSeconds()+1);
            myCalender.setTime(t);
            final String strDate = simpleDateFormat.format(myCalender.getTime());

            runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    timerTextView.setText(strDate);
                }
            });
        }
    }
    private Button record, play, stop;
    TextView timerTextView;
    private MediaRecorder myAudioRecorder;
    String outputFile, ffile;
    Timer timer = null;
    MyTimerTask myTimerTask;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordaudio);
        record = (Button) findViewById(R.id.recordButton);
        play = (Button) findViewById(R.id.playButton);
        stop = (Button) findViewById(R.id.stopButton);
        timerTextView = (TextView) findViewById(R.id.timer);
        stop.setEnabled(false);
//        play.setEnabled(false);
        final Calendar myCalender = Calendar.getInstance();
        myCalender.set(0,0,0,0,0,0);
        myAudioRecorder = new MediaRecorder();
        RandomAccessFile file = null;


        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3pg";
        myAudioRecorder.setOutputFile(outputFile);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(record.getText().equals("Record")) {

                    try {
                        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                        myAudioRecorder.prepare();
                        myAudioRecorder.start();
                        /*******************************************************************************/
                        if (timer != null)
                            timer.cancel();
                        timer = new Timer();
                        myTimerTask = new MyTimerTask(myCalender);
                        timer.schedule(myTimerTask, 1000, 1000);


                        /*******************************************************************************/
                    } catch (IllegalStateException ise) {
                    }
                    catch (IOException ioe) {
                    }
//                    record.setEnabled(false);

                    record.setText("pause");
                    stop.setEnabled(true);
                }
                else
                    if(record.getText().equals("pause"))
                    {
                        myAudioRecorder.stop();
                        timer.cancel();
                        /**************************************/
                        /***************************************/
                        record.setText("Record");
                    }




                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();

            }


        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();

                try
                {
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
                }
                catch(Exception e) {}


            }
        });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                record.setEnabled(true);
                stop.setEnabled(false);
                play.setEnabled(true);
                Toast.makeText(getApplicationContext(), "AudioRecording successfuly", Toast.LENGTH_LONG).show();

            }
        });

    }
}
