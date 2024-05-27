package edu.fpt.assignment.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import edu.fpt.assignment.R;
import edu.fpt.assignment.application.Session;
import edu.fpt.assignment.database.ApplicationDatabase;
import edu.fpt.assignment.database.dao.UserDAO;
import edu.fpt.assignment.dialog.FailDialog;
import edu.fpt.assignment.dialog.SuccessDialog;
import edu.fpt.assignment.domain.UserDomain;

public class AuthActivity extends AppCompatActivity {
    EditText email, password;
    CheckBox cb_rememberme;
    Button btnLogin, btnSignup;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    FloatingActionButton fab_login_google;
    UserDAO userDAO;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        preferences = getSharedPreferences("application", MODE_PRIVATE);
        bindingView();
        bindingAction();
        userDAO = ApplicationDatabase.getInstance(this).userDAO();

    }

//    private void initData() {
//        String isRememberString = preferences.getString("remember", null);
//        boolean isRemember = Boolean.parseBoolean(isRememberString);
//        if (isRemember) {
//            String email = preferences.getString("email", null);
//            String password = preferences.getString("password", null);
//            if (null == email || email.isEmpty()) return;
//            if (null == password || password.isEmpty()) return;
//            doSignIn(email, password);
//        }
//    }

    void bindingView() {
        email = findViewById(R.id.edt_login_email);
        password = findViewById(R.id.edt_login_password);
        btnLogin =  findViewById(R.id.btn_login_1);
        btnSignup = findViewById(R.id.btn_no_account);

        //Google Signin
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        fab_login_google = findViewById(R.id.fab_login_google);

        //CB rememberme
        cb_rememberme = findViewById(R.id.cb_rememberme);
    }

    void bindingAction() {
        //Signin & Signup
        btnLogin.setOnClickListener(this::onclickBtnLogin);
        btnSignup.setOnClickListener(this::onclickSignup);

        //Google Login
        fab_login_google.setOnClickListener(this::handleLoginGoogle);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        //Remember me
        String checkbox = preferences.getString("remember", "");
        if (checkbox.equals("true")) {
            String email = preferences.getString("email", null);
            String password = preferences.getString("password", null);
            if (null != email && null != password) {
                UserDomain user = userDAO.getByEmailAndPassword(email, password);
                if (user != null) {
                    Session.add("user", user);
                    Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                    startActivity(intent);
                }else  {
                    new FailDialog(this, "Login Failed", "Username or Password is wrong. Please check again!!").show();
                }
            }
        }

        cb_rememberme.setOnCheckedChangeListener(this::onCheckedRemember);
    }

    private void onCheckedRemember(CompoundButton compoundButton, boolean b) {
        if (compoundButton.isChecked()) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("remember", "true");
            editor.apply();
            Toast.makeText(AuthActivity.this, "Rememberme Checked", Toast.LENGTH_SHORT).show();
        }else if (!compoundButton.isChecked()) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("remember", "false");
            editor.apply();
            Toast.makeText(AuthActivity.this, "Rememberme Unchecked", Toast.LENGTH_SHORT).show();
        }
    }

    private void onclickSignup(View view) {
        Intent intent = new Intent(AuthActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    private void onclickBtnLogin(View view) {
        signIn();
    }

    private void signIn() {
        String emailString = email.getText().toString().trim();
        String passString = password.getText().toString().trim();

        if (!checkUserEmpty(emailString) || !checkPasswordEmpty(passString)) {
            return;
        }else {
            doSignIn(emailString, passString);
        }
    }

    private void doSignIn(String email, String password) {
//        List<UserDomain> users = userDAO.getAll();
        UserDomain user = userDAO.getByEmailAndPassword(email, password);
        if (user != null) {
            Session.add("user", user);
            Intent intent = new Intent(AuthActivity.this, MainActivity.class);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            startActivity(intent);
        }else  {
            new FailDialog(this, "Login Failed", "Username or Password is wrong. Please check again!!").show();
        }
    }

    private void handleLoginGoogle(View view) {
        googleSignIn();
    }

    private void googleSignIn() {
        //Sign in = GG Account
        Intent googleSignInIntent = gsc.getSignInIntent();
        startActivityForResult(googleSignInIntent, 1000);
    }

    private Boolean checkUserEmpty(String user) {
        if (user.isEmpty()) {
            email.requestFocus();
            email.setError("Field is not empty");
            return false;
        }else {
            email.setError(null);
            return true;
        }
    }
    private Boolean checkPasswordEmpty(String pwd) {
        if (pwd.isEmpty()) {
            password.requestFocus();
            password.setError("Field is not empty");
            return false;
        }else {
            password.setError(null);
            return true;
        }

    }



}