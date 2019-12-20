package com.example.card_business;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Details extends AppCompatActivity {
    DatabaseReference mDatabase;
    String gender = "man";
    int old = 20;
    String work = "worker";
    String userid;
    String focusing = "shopping";
    final HashMap result = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent(); /*데이터 수신*/

        userid = intent.getExtras().getString("userid"); /*String형*/

        RadioGroup rg = (RadioGroup)findViewById(R.id.gender);

        RadioButton man = (RadioButton)findViewById(R.id.man);
        man.setChecked(true);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.man){
                    gender = "남성";
                }else if(checkedId == R.id.woman){
                    gender = "여성";
                }
            }
        });
        RadioGroup rgOld = (RadioGroup)findViewById(R.id.old);
        RadioButton twen = (RadioButton)findViewById(R.id.twenty);
        twen.setChecked(true);

        rgOld.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.ten){
                    old = 10;
                }else if(checkedId == R.id.twenty){
                    old = 20;
                }else if(checkedId == R.id.thirty){
                    old = 30;
                }else if(checkedId == R.id.fourty){
                    old = 40;
                }else if(checkedId == R.id.fifty){
                    old = 50;
                }
            }
        });

        RadioGroup rgWork = (RadioGroup)findViewById(R.id.work);
        RadioButton working = (RadioButton)findViewById(R.id.worker);
        working.setChecked(true);

        rgWork.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.worker){
                    work = "worker";
                }else if(checkedId == R.id.student){
                    work = "student";
                }
                else if(checkedId == R.id.owner){
                    work = "owner";
                }
            }
        });
        RadioGroup rgFocus = (RadioGroup)findViewById(R.id.focus);
        RadioButton foncusing = (RadioButton)findViewById(R.id.shopping);
        foncusing.setChecked(true);

        rgFocus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.shopping){
                    focusing = "worker";
                }else if(checkedId == R.id.education){
                    focusing = "student";
                }else if(checkedId == R.id.travle){
                    focusing = "owner";
                }else if(checkedId == R.id.culture){
                    focusing = "student";
                }else if(checkedId == R.id.pay) {
                    focusing = "owner";
                }
            }
        });

        //아이디 정의
        Button add_savebtn = (Button) findViewById(R.id.add_savebtn);

        //온클릭리스너
        add_savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // realtime database

                //hashmap 만들기

//                result.put("gender", gender);
//                result.put("old", old);
//                result.put("work", work);
//
//                //firebase 정의
//                mDatabase = FirebaseDatabase.getInstance().getReference();
//                //firebase에 저장
//                mDatabase.child("details").child(userid).push().setValue(result);

                Toast.makeText(getApplicationContext(), "입력 완료 되었습니다.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),Information.class);
                String intent_string = Integer.toString(old) + "대 + " + gender;
                intent.putExtra("BUTTON_TEXT", intent_string);
                intent.putExtra("old", old);
                intent.putExtra("gender", gender);

                startActivity(intent);


            }
        });



    }
}
