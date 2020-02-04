package com.example.sdcardimage;

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
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    ImageView ivSDimage;
    int posNum=0;
    File imageFiles[];
    String imageFname, extName, sdPath;
    ArrayList<String> imgList; //동적배열 동적배열 호출은 str.get(인덱스)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPrev=(Button)findViewById(R.id.btnPrev);
        btnNext=(Button)findViewById(R.id.btnNext);
        ivSDimage=(ImageView)findViewById(R.id.ivSDimage);

        imgList=new ArrayList<String>();
        int permissiongCheck= ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissiongCheck== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        }else{
            sdcardProcess();
        }


        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posNum<=0){
                    Toast.makeText(getApplicationContext(),"첫번째 그림입니다.",Toast.LENGTH_SHORT).show();
                }else {
                    posNum--;
                    imageFname = imgList.get(posNum);
                    Bitmap bm = BitmapFactory.decodeFile(sdPath + "/" + imageFname);//디코딩해야함
                    ivSDimage.setImageBitmap(bm);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posNum>=imgList.size()-1){
                    Toast.makeText(getApplicationContext(),"마지막 그림입니다.",Toast.LENGTH_SHORT).show();
                }else {
                    posNum++;
                    imageFname = imgList.get(posNum);
                    Bitmap bm = BitmapFactory.decodeFile(sdPath + "/" + imageFname);//디코딩해야함
                    ivSDimage.setImageBitmap(bm);


                }
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
        for(File file:imageFiles){//향상된 for문
            imageFname=file.getName();
            extName=imageFname.substring(imageFname.length()-3);//substring 문자이름 뺴는거
            if(extName.equals("jpg") || extName.equals("png") || extName.equals("gif")){
                imgList.add(imageFname);
            }
        }
        imageFname=imgList.get(posNum);
        Bitmap bm= BitmapFactory.decodeFile(sdPath+"/"+imageFname);//디코딩해야함
        ivSDimage.setImageBitmap(bm);

    }
}
