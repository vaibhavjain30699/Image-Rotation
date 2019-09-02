package com.vaibhav.imagerotation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private Button select,rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image__);
        //image.setScaleType(ImageView.ScaleType.MATRIX);
        select = findViewById(R.id.select);
        rotate = findViewById(R.id.rotate);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i,"Select the image"),100);
            }
        });

        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setRotation((image.getRotation()+90)%360);
                Log.e("Rotation",image.getRotation()+"");
//                Matrix matrix = new Matrix();
//                matrix.postRotate((float) 90,image.getDrawable().getBounds().width()/2,image.getDrawable().getBounds().height()/2);
//                image.setImageMatrix(matrix);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==100){
//                Picasso.with(MainActivity.this).load(data.getData()).noPlaceholder().fit().centerCrop().into(image);
                Uri uri = data.getData();

                Bitmap b = null;
                try {
                    b = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                image.setImageBitmap(b);
            }
        }
    }
}
