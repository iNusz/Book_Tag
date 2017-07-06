package com.seunghoshin.android.booktag_final.Message;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seunghoshin.android.booktag_final.R;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // 지금은 토큰값을 확인하려고 하는것이고 , 실제로는 signup에 토큰을 확인해서 다른 토큰만 있는 DB에다가 저장을 한 뒤 나중에 메세지 보낼때 불러온다
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("Message", "Refreshed token: " + refreshedToken);

    }

}
