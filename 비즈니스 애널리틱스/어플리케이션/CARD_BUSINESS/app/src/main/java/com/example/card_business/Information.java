package com.example.card_business;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class Information extends AppCompatActivity {

    private Context mContext;

    private TabLayout mTabLayout;
    String button_text;
    String gender = "N";
    int old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Intent intent = getIntent();

        String intent_string = intent.getExtras().getString("BUTTON_TEXT");
        old = intent.getExtras().getInt("old");
        String gender = intent.getExtras().getString("gender");


        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("카드별") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("혜택별") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("추천") ;
        tabHost1.addTab(ts3) ;

        Button custom = (Button)findViewById(R.id.customize_btn);
        Button pay = (Button)findViewById(R.id.pay_btn);
        Button culture = (Button)findViewById(R.id.culture_btn);
        Button travle= (Button)findViewById(R.id.travle_btn);
        Button edu= (Button)findViewById(R.id.education_btn);
        Button shop = (Button)findViewById(R.id.shopping_btn);
        Button simple_pay = (Button)findViewById(R.id.simple_pay);
        Button wuri = (Button)findViewById(R.id.wuri);
        Button kukmin = (Button)findViewById(R.id.kukmin);
        Button samsung = (Button)findViewById(R.id.samsung);
        Button bc = (Button)findViewById(R.id.bc);
        Button shinhan = (Button)findViewById(R.id.shinhan);

        custom.setText(intent_string);
        custom.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startedIntent("custom");
            }
        }) ;
        pay.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startedIntent("pay");
            }
        }) ;
        culture.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startedIntent("culture");
            }
        }) ;
        travle.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startedIntent("travle");
            }
        }) ;
        edu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startedIntent("edu");
            }
        }) ;
        shop.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startedIntent("shopping");
            }
        }) ;
        simple_pay.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startedIntent("simple_pay");
            }
        }) ;
        wuri.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startedIntent("wuri");
            }
        }) ;
        kukmin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startedIntent("kukmin");
            }
        }) ;
        samsung.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startedIntent("samsung");
            }
        }) ;
        bc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startedIntent("bc");
            }
        }) ;
        shinhan.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startedIntent("shinhan");
            }
        }) ;


    }
    public void startedIntent(String select){

        Intent intent = new Intent(getApplicationContext(),CardBenefits.class);
        intent.putExtra("select", select);
        intent.putExtra("old", old);
        intent.putExtra("gender", gender);
        startActivity(intent);
    }

}
