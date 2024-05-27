package edu.fpt.assignment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import edu.fpt.assignment.R;
import edu.fpt.assignment.database.ApplicationDatabase;
import edu.fpt.assignment.database.dao.UserDAO;
import edu.fpt.assignment.dialog.FailDialog;
import edu.fpt.assignment.dialog.SuccessDialog;
import edu.fpt.assignment.domain.UserDomain;

public class SignupActivity extends AppCompatActivity {
    EditText displayname, password_signup, cfpassword_signup, email_signup;
    Button btn_signup_2, btn_haveaccount;
    UserDAO userDAO;

    void bindingView() {
        displayname = findViewById(R.id.edt_signup_name);
        password_signup = findViewById(R.id.edt_signup_password);
        cfpassword_signup = findViewById(R.id.edt_signup_confirmpwd);
        email_signup = findViewById(R.id.edt_signup_email);
        btn_signup_2 = findViewById(R.id.btn_signup_2);
        btn_haveaccount = findViewById(R.id.btn_haveaccount);
    }

    void bindidngAction() {
        btn_signup_2.setOnClickListener(this::onclickSignup);
        btn_haveaccount.setOnClickListener(this::onclicSignin);
    }

    private void onclicSignin(View view) {
        //Remember me
//        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("remember", "false");
//        editor.apply();

        Intent intent = new Intent(SignupActivity.this, AuthActivity.class);
        startActivity(intent);
    }


    //Sign up
    private void onclickSignup(View view) {

        String displayName = displayname.getText().toString();
        String email = email_signup.getText().toString();
        String password = password_signup.getText().toString();
        String cfpasswrod = cfpassword_signup.getText().toString();
        if (!validateName(displayName) | !validatePassword(password) | !validateConfirmPassword(password, cfpasswrod) | !validateEmail(email)) {
            new FailDialog(this, "Registered Failed", "Please check again!!").show();
        } else {
            UserDomain user = new UserDomain().setId(UserDomain.generateID()).setEmail(email).setName(displayName).setPassword(password);
            userDAO.insertAll(user);
            List<UserDomain> users = userDAO.getAll();
            new SuccessDialog(this, "Registered Successfully", "Registration is successful, please login to use the application!", () -> {
                Intent intent = new Intent(SignupActivity.this, AuthActivity.class);
                startActivity(intent);
            }).show();
        }
    }

    private Boolean validateConfirmPassword(String password, String cfpassword) {
        if (cfpassword.isEmpty()) {
            cfpassword_signup.requestFocus();
            cfpassword_signup.setError("Field cannot be empty");
            return false;
        } else if (!(cfpassword.equals(password))) {
            cfpassword_signup.requestFocus();
            cfpassword_signup.setError("Confirm Password is not match with Password");
            return false;
        } else {
            cfpassword_signup.setError(null);
            return true;
        }
    }

    private Boolean validateName(String displayName) {
        if (displayName.isEmpty()) {
            displayname.requestFocus();
            displayname.setError("Field cannot be empty");
            return false;
        } else {
            displayname.setError(null);
            return true;
        }
    }

    private Boolean validateEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        boolean isExistsEmail = userDAO.isExistsEmail(email);
        if (email.isEmpty()) {
            email_signup.requestFocus();
            email_signup.setError("Field cannot be empty");
            return false;
        } else if (!email.matches(emailPattern)) {
            email_signup.requestFocus();
            email_signup.setError("Invalid email address");
            return false;
        } else if (isExistsEmail) {
            email_signup.setError("Email already Exists");
            return false;
        } else {
            email_signup.setError(null);
            return true;
        }
    }

    private Boolean validatePassword(String password) {
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (password.isEmpty()) {
            password_signup.requestFocus();
            password_signup.setError("Field cannot be empty");
            return false;
        } else if (!password.matches(passwordVal)) {
            password_signup.requestFocus();
            password_signup.setError("Password is least 1 digit, 1 lower case letter, 1 special character, 4 characters, no white spaces");
            return false;
        } else {
            password_signup.setError(null);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindingView();
        bindidngAction();
        userDAO = ApplicationDatabase.getInstance(this).userDAO();
    }
}