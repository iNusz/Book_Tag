package com.seunghoshin.android.booktag_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.seunghoshin.android.booktag_final.util.PermissionControl;

public class LoginActivity extends AppCompatActivity implements PermissionControl.CallBack {

    // 파이어베이스 인증
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d("Auth", "onAuthStateChanged:signed_in:" + user.getUid());
            } else {
                // User is signed out
                Log.d("Auth", "onAuthStateChanged:signed_out");
            }
            // ...
        }
    };

    // 위젯정의

    EditText editEmail, editPassword;
    Button btnSignin, btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PermissionControl.checkVersion(this);

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionControl.onResult(this, requestCode, grantResults);
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    // 여기에 코드 작성
    @Override
    public void init() {

        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);

    }

    public void signUp(View view) {


        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();


        try {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("Auth", "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "사용자 등록이 되지 않았습니다",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this,
                                        "Sign-up 이 되었습니다",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, "칸을 형식에 맞게 모두 채워주세요", Toast.LENGTH_SHORT).show();
        }
    }

    public void signIn(View view) {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        try {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("Auth", "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w("Auth", "signInWithEmail:failed", task.getException());
                                Toast.makeText(LoginActivity.this,
                                        "Sign-in 이 되지 않았습니다",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this,
                                        "Sign-in 이 되었습니다",
                                        Toast.LENGTH_SHORT).show();
                                goMain();
                            }

                            // ...
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, "칸을 형식에 맞게 모두 채워주세요", Toast.LENGTH_SHORT).show();
        }

    }

    public void goMain(){
        Intent intent  = new Intent(this, ListActivity.class);
        startActivity(intent);
        finish();
    }

}

