package com.csfinale.aashray.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class MainActivity extends AppCompatActivity {

    final Integer CAMERA_CAPTURE_IMAGE_REQUEST_CODE=100;
    private int PIC_CAPTURED = 1;
    private int RESULT_LOAD_IMG = 2;
    ImageView imageView;
    Button take,view;
    File myDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.iv);
        take= (Button) findViewById(R.id.takePic);
        view= (Button) findViewById(R.id.viewPic);
111```
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, PIC_CAPTURED);
            }
        });
        view.setOnClickListener(new View.OnClickLis11111111111tener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), RESULT_LOAD_IMG);

            }
        });
    }

    private String getPictureName() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp=sdf.format(new Date());
        return "IMG"+timestamp+".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==PIC_CAPTURED){
               Bitmap img=(Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(img);
                String root = Environment.getExternalStorageDirectory().toString();
                myDir = new File(root + "/saved_images");
                myDir.mkdirs();
                Random generator = new Random();
                int n = 10000;
                n = generator.nextInt(n);
                String fname = "Image-"+ n +".jpg";
                File file = new File (myDir, fname);
                if (file.exists ())
                    file.delete ();
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
