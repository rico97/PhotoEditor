package com.example.rico.photoeditor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final int SELECT_IMAGE = 1;
    private Button selectButton;
    private MyView myView;
    private PointF[] points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectButton = (Button) findViewById(R.id.select);
        myView = (MyView) findViewById(R.id.image);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_IMAGE);
            }
        });

        points = new PointF[1000];
        for (int i = 0; i< points.length; ++i){
            points[i] = new PointF();
        }


        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //System.out.println(""+event.getX() + event.getY());

                myView.setPoints(points);

                for (int i = 1; i <points.length; ++i){
                    points[i-1].x = points[i].x;
                    points[i-1].y = points[i].y;
                }

                points[points.length-1].x = event.getX();
                points[points.length-1].y = event.getY();

                myView.invalidate();

                return true;
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == SELECT_IMAGE && resultCode == RESULT_OK ){
            Uri dataUri = intent.getData();

            try {
                Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), dataUri);
                Drawable d = new BitmapDrawable(getResources(),image);
                myView.setBackground(d);
            } catch (IOException e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }




}

