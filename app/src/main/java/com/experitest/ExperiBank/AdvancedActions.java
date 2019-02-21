package com.experitest.ExperiBank;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class AdvancedActions extends Activity {
    private Button OpenBrowser, scanCheck, RecordAudio, Back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advancedactions);

        OpenBrowser = (Button) findViewById(R.id.OpenBrowserButton);
        scanCheck = (Button) findViewById(R.id.ScanCheckButton);
        RecordAudio = (Button) findViewById(R.id.RecordAudioutton);
        Back = (Button) findViewById(R.id.BackButton);

        OpenBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.Experitest.com";
                try {
                    Intent i = new Intent("android.intent.action.MAIN");
                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                catch(ActivityNotFoundException e) {
                    // Chrome is not installed
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }
            }
        });

        scanCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checks.data = null;
                    startActivity(new Intent(AdvancedActions.this, checks.class));
                } catch (Exception ex) {
                    Log.e(this.getClass().getName(), "Error : " + ex.getMessage(), ex);
                }
            }
        });


        RecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    recordAudio.outputFile = null;
                    startActivity(new Intent(AdvancedActions.this, recordAudio.class));
                } catch (Exception ex) {
                    Log.e(this.getClass().getName(), "Error : " + ex.getMessage(), ex);
                }
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdvancedActions.this, PaymentHomeActivity.class));
            }
        });

    }
}

