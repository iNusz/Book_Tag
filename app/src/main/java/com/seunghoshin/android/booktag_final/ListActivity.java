package com.seunghoshin.android.booktag_final;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seunghoshin.android.booktag_final.domain.BookInfo;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    // 2번 눌렀을때 뒤로가기를 활성화 해주는 선언
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;


    FirebaseDatabase database;
    DatabaseReference bbsRef;

    RecyclerView recycler;
    ListAdapter adapter;
    Button btnDetail;

    List<BookInfo> data = new ArrayList<>();

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("bbs");

        recycler = (RecyclerView) findViewById(R.id.recycler);
        adapter = new ListAdapter(this, data);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        dialog = new ProgressDialog(this);
        dialog.setTitle("처리중..");
        dialog.setMessage("ing...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();


        btnDetail = (Button) findViewById(R.id.btnDetail);

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WriteActivity.class);
                startActivity(intent);
            }
        });

        loadData();
    }

    public void loadData(){
        bbsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {
                Data.list.clear();
                for(DataSnapshot item : data.getChildren()){
                    // json 데이터를 Bbs 인스턴스로 변환오류 발생 가능성 있음
                    // 그래서 예외 처리 필요
                    try {
                        BookInfo bookInfo = item.getValue(BookInfo.class);
                        Data.list.add(bookInfo);
                    }catch (Exception e){
                        Log.e("Firebase",e.getMessage());
                    }
                }
                refreshList(Data.list);
                dialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void refreshList(List<BookInfo> data){
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }


    // 2번눌렀을때 뒤로가기가 눌리게끔 해줬다
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {

            Intent intent = new Intent();
            String strFlag = "exit";
            intent.putExtra("value", strFlag);
            setResult(RESULT_OK, intent);
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "뒤로 버튼을 한번더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }

}
