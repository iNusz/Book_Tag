package com.seunghoshin.android.booktag_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seunghoshin.android.booktag_final.domain.BookInfo;

public class ReadActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txtTitle, txtAuthor, txtContent, txtDate, txtCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);


        imageView = (ImageView) findViewById(R.id.imageView);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtAuthor = (TextView) findViewById(R.id.txtAuthor);
        txtContent = (TextView) findViewById(R.id.txtContent);
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtCount = (TextView) findViewById(R.id.txtCount);

        setData();
    }

    public void setData() {
        // 목록에서 넘어온 position 값을 이용해 상세보기 데이터를 결정
        Intent intent = getIntent();
        int position = intent.getIntExtra("LIST_POSITION", -1);

        if (position > -1) {
        BookInfo bookInfo = Data.list.get(position);
            // 이미지 세팅
            if(bookInfo.fileUriString != null && ! "".equals(bookInfo.fileUriString)){
                Glide.with(this)
                        .load(bookInfo.fileUriString)
                        .into(imageView);
            }

            // 값 세팅
            txtTitle.setText(bookInfo.title);
            txtAuthor.setText(bookInfo.author);
            txtContent.setText(bookInfo.content);
            txtDate.setText("Date"+bookInfo.date);
//            txtCount.setText("Count"+bookInfo.count);


        }
    }


}
