package com.example.card_business;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CardBenefits extends AppCompatActivity {
    String select = "";
    int old;
    String gender = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_benefits);

        Intent intent = getIntent(); /*데이터 수신*/

        select = intent.getExtras().getString("select"); /*String형*/
        gender = intent.getExtras().getString("gender"); /*String형*/
        old = intent.getExtras().getInt("old"); /*String형*/

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Recycler_item> items = new ArrayList<>();
        Recycler_item[] item = new Recycler_item[5];
        if(select.equals("bc")){
            item[0] = new Recycler_item(R.drawable.a, "2,000원 이상 결제 시 건당 500원 청구할인\n" + "2019.11.01 (금) ~ 12.31 (화)");
            item[1] = new Recycler_item(R.drawable.b, "BC카드 생활요금 자동 납부 최대 3만원 청구할인\n" + "2019.10.01 (화) ~ 12.31 (화)");
            item[2] = new Recycler_item(R.drawable.c, "C카드 X 위 베어 베어스 한정판 2020년 캘린더\n" + "2019.12.02 (월) ~ 12.15 (일)");
            item[3] = new Recycler_item(R.drawable.d, "좋은 것만 드려요 #마이태그 베스트 혜택\n" + "2019.12.01 (일) ~ 12.31 (화)");
            item[4] = new Recycler_item(R.drawable.e, "미식WEEK 최대 60% 할인\n" + "2019.11.16 (토) ~ 12.15 (일)");

            for (int i = 0; i < 5; i++) items.add(item[i]);

            recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_card_benefits));

        }
        else if(select.equals("shinhan")){
            item[0] = new Recycler_item(R.drawable.sh_1, "신한카드 고객님께만 드리는 Amazon.com 할인 쿠폰!\n" + "2019년 11월 15일 ~ 2019년 12월 15일");
            item[1] = new Recycler_item(R.drawable.sh_2, "신한카드가 드리는 특별한 혜택과 함께, NETFLIX의 다양한 영화/드라마 콘텐츠를 즐겨보세요!\n" + "2019년 10월 01일 ~ 2019년 12월 31일");
            item[2] = new Recycler_item(R.drawable.sh_3, "몰테일 배송비 캐시백은 물론 해외 모든 가맹점 무제한 1.2%캐시백\n" + "2019년 11월 12일 ~ 2019년 12월 31일");
            item[3] = new Recycler_item(R.drawable.sh_4, "제휴카드 등록하고 멤버십 적립과 포인트 행운\n" + "2019년 11월 21일 ~ 2019년 12월 15일");
            item[4] = new Recycler_item(R.drawable.sh_5, "신한카드를 최초로 발급하신 고객님께 풍성한 선물을 드립니다!\n" + "2019년 12월 01일 ~ 2019년 12월 31일");

            for (int i = 0; i < 5; i++) items.add(item[i]);

            recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_card_benefits));

        }
        else if(select.equals("simple_pay")){
            item[0] = new Recycler_item(R.drawable.pay_1, "SUPER SAVE+ \n결제 + 충전 + 쇼핑적립 + 금융혜택 + 다음 달 VIP 혜택까지 PAYCO 포인트로 받아가세요. \n" + "2019. 9. 1. ~ 2020. 12. 31.");
            item[1] = new Recycler_item(R.drawable.pay_2, "꽝없는 100% 당첨 \n" + "삼성페이 3회 결제시마다 \n" + "2019. 12. 3. ~ 2019. 12. 31.");
            item[2] = new Recycler_item(R.drawable.pay_3, "PAYCO 신용카드 혜택 \nPAYCO에 신용카드 등록하고 결제 시 혜택을 드려요. \n" + "2019. 12. 1. ~ 2019. 12. 31.");

            for (int i = 0; i < 3; i++) items.add(item[i]);

            recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_card_benefits));

        }
        else if(select.equals("shopping")){
            item[3] = new Recycler_item(R.drawable.d, "좋은 것만 드려요 #마이태그 베스트 혜택\n" + "2019.12.01 (일) ~ 12.31 (화)");
            item[4] = new Recycler_item(R.drawable.e, "미식WEEK 최대 60% 할인\n" + "2019.11.16 (토) ~ 12.15 (일)");
            item[0] = new Recycler_item(R.drawable.pay_1, "SUPER SAVE+ \n결제 + 충전 + 쇼핑적립 + 금융혜택 + 다음 달 VIP 혜택까지 PAYCO 포인트로 받아가세요. \n" + "2019. 9. 1. ~ 2020. 12. 31.");
            item[2] = new Recycler_item(R.drawable.sh_3, "몰테일 배송비 캐시백은 물론 해외 모든 가맹점 무제한 1.2%캐시백\n" + "2019년 11월 12일 ~ 2019년 12월 31일");
            item[1] = new Recycler_item(R.drawable.sh_1, "신한카드 고객님께만 드리는 Amazon.com 할인 쿠폰!\n" + "2019년 11월 15일 ~ 2019년 12월 15일");

            for (int i = 0; i < 5; i++) items.add(item[i]);

            recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_card_benefits));

        }
        else if(select.equals("culture")){
            item[0] = new Recycler_item(R.drawable.cu1, "BC TOP포인트\n" +
                    "에버랜드 1+1 \n" +
                    "2019.12.07~2019.12.25");
            item[1] = new Recycler_item(R.drawable.cu2, "2020 한정판 캘린더\n" +
                    "2020명 증정 이벤트!\n" + "2019.12.02~2019.12.15");
            item[2] = new Recycler_item(R.drawable.cu3, "하루종일 생활엔BC 혜택 받기\n" + "2019.10.01~2019.12.31");
            item[3] = new Recycler_item(R.drawable.cu4, "[골프엔BC] \n" +
                    "골프대디 연회원 50% 페이백!\n" + "2019.12.04~2019.12.31\n");
            item[4] = new Recycler_item(R.drawable.cu5, "페이북 문화 12월 VIP고객\n" +
                    "문화 공연 초대 이벤트\n" + "2019.12.03~2019.12.25");

            for (int i = 0; i < 5; i++) items.add(item[i]);

            recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_card_benefits));

        }
        else if(select.equals("custom")){
            if(old == 20){
                item[3] = new Recycler_item(R.drawable.d, "좋은 것만 드려요 #마이태그 베스트 혜택\n" + "2019.12.01 (일) ~ 12.31 (화)");
                item[4] = new Recycler_item(R.drawable.e, "미식WEEK 최대 60% 할인\n" + "2019.11.16 (토) ~ 12.15 (일)");
                item[0] = new Recycler_item(R.drawable.pay_1, "SUPER SAVE+ \n결제 + 충전 + 쇼핑적립 + 금융혜택 + 다음 달 VIP 혜택까지 PAYCO 포인트로 받아가세요. \n" + "2019. 9. 1. ~ 2020. 12. 31.");
                item[2] = new Recycler_item(R.drawable.sh_3, "몰테일 배송비 캐시백은 물론 해외 모든 가맹점 무제한 1.2%캐시백\n" + "2019년 11월 12일 ~ 2019년 12월 31일");
                item[1] = new Recycler_item(R.drawable.sh_1, "신한카드 고객님께만 드리는 Amazon.com 할인 쿠폰!\n" + "2019년 11월 15일 ~ 2019년 12월 15일");

                for (int i = 0; i < 5; i++) items.add(item[i]);

                recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_card_benefits));
            }
            else if(old == 40){
                item[0] = new Recycler_item(R.drawable.pay_1, "SUPER SAVE+ \n결제 + 충전 + 쇼핑적립 + 금융혜택 + 다음 달 VIP 혜택까지 PAYCO 포인트로 받아가세요. \n" + "2019. 9. 1. ~ 2020. 12. 31.");
                item[2] = new Recycler_item(R.drawable.sh_3, "몰테일 배송비 캐시백은 물론 해외 모든 가맹점 무제한 1.2%캐시백\n" + "2019년 11월 12일 ~ 2019년 12월 31일");
                item[3] = new Recycler_item(R.drawable.cu4, "[골프엔BC] \n" +
                        "골프대디 연회원 50% 페이백!\n" + "2019.12.04~2019.12.31\n");
                item[1] = new Recycler_item(R.drawable.sh_2, "신한카드가 드리는 특별한 혜택과 함께, NETFLIX의 다양한 영화/드라마 콘텐츠를 즐겨보세요!\n" + "2019년 10월 01일 ~ 2019년 12월 31일");

                for (int i = 0; i < 4; i++) items.add(item[i]);

                recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_card_benefits));

            }
        }

        else{
            item[0] = new Recycler_item(R.drawable.sh_1, "신한카드 고객님께만 드리는 Amazon.com 할인 쿠폰!\n" + "2019년 11월 15일 ~ 2019년 12월 15일");
            item[1] = new Recycler_item(R.drawable.sh_2, "신한카드가 드리는 특별한 혜택과 함께, NETFLIX의 다양한 영화/드라마 콘텐츠를 즐겨보세요!\n" + "2019년 10월 01일 ~ 2019년 12월 31일");
            item[2] = new Recycler_item(R.drawable.sh_3, "몰테일 배송비 캐시백은 물론 해외 모든 가맹점 무제한 1.2%캐시백\n" + "2019년 11월 12일 ~ 2019년 12월 31일");
            item[3] = new Recycler_item(R.drawable.sh_4, "제휴카드 등록하고 멤버십 적립과 포인트 행운\n" + "2019년 11월 21일 ~ 2019년 12월 15일");
            item[4] = new Recycler_item(R.drawable.sh_5, "신한카드를 최초로 발급하신 고객님께 풍성한 선물을 드립니다!\n" + "2019년 12월 01일 ~ 2019년 12월 31일");

            for (int i = 0; i < 5; i++) items.add(item[i]);

            recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_card_benefits));

        }
    }
}