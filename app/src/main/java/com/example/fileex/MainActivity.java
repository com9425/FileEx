package com.example.fileex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnSave, btnRead;
    TextView tvResult;
    String str="지금 이내용이 저장 됩니다. \n즐거운 주말 되세요";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave=(Button)findViewById(R.id.btnSave);
        btnRead=(Button)findViewById(R.id.btnRead);
        tvResult=(TextView)findViewById(R.id.tvResult);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v){
                try {
                    FileOutputStream fileos = openFileOutput("test.txt", Context.MODE_PRIVATE);// 파일 아웃스트림 저장장치를 보내는것 인풋스트림은 읽어오는것 PRIVATE는 같은이름있으면 덮어씌움
                    fileos.write(str.getBytes()); //겟바이트단위로 읽음
                    fileos.close();
                    showToast("test.txt파일이 저장되었습니다");
                } catch (IOException e) {
                    showToast("test.파일을 저장 할 수 없습니다.");
                }
                btnRead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            FileInputStream fileis=openFileInput("test.txt");
                            byte txt[]=new byte[fileis.available()];
                            fileis.read(txt);
                            tvResult.setText(new String(txt));
                            fileis.close();
                        } catch (IOException e) {
                            showToast("test.해당 파일을 읽을 수 없습니다.");

                        }
                    }
                });
            }

        });

    }
    void showToast(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
