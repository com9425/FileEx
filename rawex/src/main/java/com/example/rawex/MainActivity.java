package com.example.rawex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button btnRawRead;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRawRead=(Button)findViewById(R.id.btnRawRead);
        tvResult=(TextView)findViewById(R.id.tvResult);

        btnRawRead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputStream ins=getResources().openRawResource(R.raw.info);
               try{
                   byte txt[]=new byte[ins.available()];
                   ins.read(txt);
                   tvResult.setText(new String(txt));
                   ins.close();
               } catch (IOException e) {
                   Toast.makeText(getApplicationContext(),"파일을 읽을 수가 없습니다",Toast.LENGTH_SHORT).show();
               }

            }

        });

    }
}
