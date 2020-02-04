package com.example.sdcardimage2;

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
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext,btnColorChange;
    TextView tvNumbner;
    myImageView ivSDcardimage;
    File imgFile[];// sd카드 파일리스트를 담을 배열
    String imgName;//동적배열에 마지막 확장자이름
    int posNum=0;//이미지배열 인덱스번호 ,초기값을 0으로해야 0번쨰부터
    String sdcardPath; //SD카드 경로
    ArrayList<File> imgList; //SD카드에 있는 이미지 폴더 중에 이미지만을 담을 동적배열
    static float satur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPrev=(Button)findViewById(R.id.btnPrev);
        btnNext=(Button)findViewById(R.id.btnNext);
        tvNumbner=(TextView)findViewById(R.id.tvNumber);
        ivSDcardimage=(myImageView)findViewById(R.id.ivSDcardimage);
        btnColorChange=(Button)findViewById(R.id.btnColorChange);

        imgList=new ArrayList<File>(); //인스턴트객체 생성해야 쓸수있음
        int permissionCheck= ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE); //허용 화면창
        }else {
            sdcardprocess();
        }
        btnColorChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnColorChange.getText().equals("흑백")){
                    btnColorChange.setText("칼라");
                    satur=0;
                }else {
                    btnColorChange.setText("흑백");
                    satur =1;
                }
                ivSDcardimage.s=satur;
                ivSDcardimage.invalidate();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posNum<=0){
                    posNum=imgList.size()-1;

                }else {
                    posNum--;
                }
                imgName=imgList.get(posNum).toString();
                ivSDcardimage.imagePath=imgName;
                ivSDcardimage.invalidate(); // invalidate 무효한다라는뜻 , onDraw를 호출한다 받았던 도화지를 지우고 부모한테 새로운 도화지즐 받는다
                tvNumbner.setText((posNum+1)+"/" + imgList.size());
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posNum>=imgList.size()-1){
                    posNum= 0;
                }else {
                    posNum++;
                }
                imgName=imgList.get(posNum).toString();
                ivSDcardimage.imagePath=imgName;
                ivSDcardimage.invalidate(); // invalidate 무효한다라는뜻 , onDraw를 호출한다 받았던 도화지를 지우고 부모한테 새로운 도화지즐 받는다
                tvNumbner.setText((posNum+1)+"/" + imgList.size());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults); //퍼미션 결과를 선택
        sdcardprocess();


    }
    void sdcardprocess(){
        sdcardPath= Environment.getExternalStorageDirectory().getAbsolutePath(); // 환경 경로를 설정하는데 절대경로를 저장
        imgFile=new File(sdcardPath+"/Pictures").listFiles();// sd카드안에 픽쳐라는데 리스트파일을 배열에 집어넣는다
        String fileName, extName; //파일이름,확장자
        for(File file:imgFile){//file변수를 만들어서 리스트 0번쨰부터 넣음
            fileName=file.getName();
            extName=fileName.substring(fileName.length()-3); //확장자를 담음
            if(extName.equals("png") || extName.equals("gif") || extName.equals("jpg")) {
                imgList.add(file); //파일자체를(경로)? 에드함

            }
        } //포문이 막 도는데 동적 배열로 담겨짐
        imgName=imgList.get(posNum).toString();
       ivSDcardimage.imagePath=imgName;
       ivSDcardimage.invalidate(); // invalidate 무효한다라는뜻 , onDraw를 호출한다 받았던 도화지를 지우고 부모한테 새로운 도화지즐 받는다
        tvNumbner.setText((posNum+1)+"/" + imgList.size());
    }
}
