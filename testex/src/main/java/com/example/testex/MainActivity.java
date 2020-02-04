



package com.example.testex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    TextView tvQuestion, tvAnswer;
    RadioButton rdoQ[]=new RadioButton[4];
    Integer rdoID[]={R.id.rdoQ1, R.id.rdoQ2, R.id.rdoQ3, R.id.rdoQ4};
    Integer ivQID[]={R.drawable.q1, R.drawable.q2, R.drawable.q3, R.drawable.q4};
    ImageView ivQ;
    Button btnResult;
    String qtest[];
    String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvQuestion=(TextView)findViewById(R.id.tvQuestion);
        tvAnswer=(TextView)findViewById(R.id.tvAnswer);
        for(int i=0; i<rdoQ.length; i++){
            rdoQ[i]=(RadioButton)findViewById(rdoID[i]);
        }
        ivQ=(ImageView)findViewById(R.id.ivQ);
        btnResult=(Button)findViewById(R.id.btnResult);
        InputStream inS=getResources().openRawResource(R.raw.test);
        try {
            byte txt[]=new byte[inS.available()];
            inS.read(txt);
            String str=new String(txt);
            qtest=str.split("#");
            tvQuestion.setText(qtest[0]);
            for(int i=0; i<rdoQ.length; i++){
                rdoQ[i].setText(qtest[i+1]);
            }
            inS.close();
            for(int i=0; i<rdoQ.length; i++){
                final int index;
                index=i;
                rdoQ[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ivQ.setImageResource(ivQID[index]);
                        answer=qtest[index+5];
                    }
                });
                btnResult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvAnswer.setText(answer);
                    }
                });
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"파일을 읽을 수가 없습니다.",Toast.LENGTH_SHORT).show();
        }

    }
}
