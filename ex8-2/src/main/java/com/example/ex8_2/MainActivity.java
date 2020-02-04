package com.example.ex8_2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    TextView tvNumber;
    ImageView ivSDimage;
    int posNum=0;
    File imageFiles[];
    String imageFname, extName,sdPath;
    ArrayList<File> imgList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPrev=(Button)findViewById(R.id.btnPrev);
        btnNext=(Button)findViewById(R.id.btnNext);
        tvNumber=(TextView)findViewById(R.id.tvNumber);
        ivSDimage=(ImageView)findViewById(R.id.ivSDimage);
        imgList=new ArrayList<File>();
        int permissionCheck=ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck== PackageManager.PERMISSION_DENIED) { //permissionCheck!= PackageManager.PERMISSION_GRANTED
            ActivityCompat.requestPermissions(this,new String[]{Manifest.
                    permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        }else {
            sdcardProcess();
        }

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posNum <=0) {
                    //Toast.makeText(getApplicationContext(),"첫번째 그림입니다.",Toast.LENGTH_SHORT).show();
                    posNum=imgList.size()-1; //마지막 index
                }else {
                    posNum--;
                }
                imageFname = imgList.get(posNum).toString();
                Bitmap bm = BitmapFactory.decodeFile(imageFname);
                ivSDimage.setImageBitmap(bm);
                tvNumber.setText((posNum+1) + " / " + imgList.size());
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posNum>=imgList.size()-1) {
                    //Toast.makeText(getApplicationContext(),"마지막 그림입니다.",Toast.LENGTH_SHORT).show();
                    posNum=0; // 처음 index
                }else {
                    posNum++;
                }
                imageFname=imgList.get(posNum).toString();
                Bitmap bm= BitmapFactory.decodeFile(imageFname);
                ivSDimage.setImageBitmap(bm);
                tvNumber.setText((posNum+1) + " / " + imgList.size());
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        sdcardProcess();
    }
    void sdcardProcess() {
        sdPath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures";
        imageFiles=new File(sdPath).listFiles();
        for(File file:imageFiles){
            imageFname=file.getName();
            extName=imageFname.substring(imageFname.length()-3);
            if(extName.equals("jpg") || extName.equals("png") || extName.equals("gif")){
                imgList.add(file);
            }
        }
        imageFname=imgList.get(posNum).toString();
        Bitmap bm= BitmapFactory.decodeFile(imageFname);
        ivSDimage.setImageBitmap(bm);
        tvNumber.setText("1 / " + imgList.size());
    }
}