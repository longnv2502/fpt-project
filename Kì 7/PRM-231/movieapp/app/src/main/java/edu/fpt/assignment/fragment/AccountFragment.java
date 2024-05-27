package edu.fpt.assignment.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fpt.assignment.R;
import edu.fpt.assignment.activities.AuthActivity;
import edu.fpt.assignment.activities.ProfileActivity;
import edu.fpt.assignment.application.Session;
import edu.fpt.assignment.domain.UserDomain;

public class AccountFragment extends Fragment{

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @BindView(R.id.rlLogout)
    RelativeLayout rlLogout;

    UserDomain user;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    public AccountFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, rootView);
        this.user = (UserDomain) Session.get("user");
        initView();
        initAction();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getContext(), gso);
        return rootView;
    }

    private void initAction() {
        rlLogout.setOnClickListener(this::signOut);
    }

    private void initView() {
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());
    }

    private void signOut(View view) {
        SharedPreferences preferences = getContext().getSharedPreferences("application", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Session.remove("user");
        editor.remove("email");
        editor.remove("password");
        editor.putString("remember", "false");
        editor.apply();

        //Signout Google Gmail
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                startActivity(new Intent(getContext(), AuthActivity.class));
            }
        });

    }

}
