package edu.fpt.assignment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;

import edu.fpt.assignment.R;

public class IntroActivity extends AppCompatActivity {
    Button btn_intro_signup;
    Button btn_intro_signin;

    void bindingView() {
        btn_intro_signin = findViewById(R.id.btn_intro_signin);
        btn_intro_signup = findViewById(R.id.btn_intro_signup);
    }
    void  bindingAction() {
        btn_intro_signin.setOnClickListener(this::onclickSignIn);
        btn_intro_signup.setOnClickListener(this::onclickSignup);
    }

    private void onclickSignup(View view) {
        Intent intent = new Intent(IntroActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    private void onclickSignIn(View view) {
        Intent intent = new Intent(IntroActivity.this, AuthActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        bindingView();
        bindingAction();
    }
}