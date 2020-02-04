package com.example.sdcardex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnSDcardRead,btnMkdir,btnRmdir;
    TextView tvResult;
    String sdcardPath;
    File myDir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSDcardRead=(Button)findViewById(R.id.btnSdcardRead);
        tvResult=(TextView)findViewById(R.id.tvResult);
        btnMkdir=(Button)findViewById(R.id.btnMkdir);
        btnRmdir=(Button)findViewById(R.id.btnRmdir);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        sdcardPath= Environment.getExternalStorageDirectory().getAbsolutePath();//sdcardPath=환경중에. 외부기억장치폴더 .절대경로
        myDir=new File(sdcardPath+"/chulsu");
        btnSDcardRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileis=new FileInputStream(sdcardPath+"/sdtest.txt");//어떤 폰이던 접근하는방법:
                    byte txt[]=new byte[fileis.available()];
                    fileis.read(txt);
                    tvResult.setText(new String(txt));
                    fileis.close();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"파일 못불",Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnMkdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDir.mkdir();
            }
        });

        btnRmdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDir.delete();
            }
        });
    }
}
