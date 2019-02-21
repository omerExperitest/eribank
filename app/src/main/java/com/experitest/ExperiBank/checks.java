package com.experitest.ExperiBank;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class checks extends Activity {
    private Button OpenCamera;
    private ImageView CheckImage;
    private Bitmap bitmap;
    static Intent data;
//    private Camera mCamera;
//    private CameraPreview mPreview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checks);

        OpenCamera = (Button) findViewById(R.id.OpenCameraButton);
        CheckImage = (ImageView) findViewById(R.id.CheckImage) ;

        OpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camaraIntent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null)
        {
            this.data = data;
            bitmap = (Bitmap) this.data.getExtras().get("data");
            CheckImage.setImageBitmap(bitmap);
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(data!=null)
//        {
//            bitmap = (Bitmap) data.getExtras().get("data");
//            CheckImage.setImageBitmap(bitmap);
//        }
//    }

}
