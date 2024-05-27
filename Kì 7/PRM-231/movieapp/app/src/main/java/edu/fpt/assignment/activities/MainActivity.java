package edu.fpt.assignment.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;

import edu.fpt.assignment.R;
import edu.fpt.assignment.fragment.AccountFragment;
import edu.fpt.assignment.fragment.FavoriteFragment;
import edu.fpt.assignment.fragment.HomeFragment;
import edu.fpt.assignment.fragment.SearchFragment;
import edu.fpt.assignment.utils.BottomBarBehavior;


public class MainActivity extends AppCompatActivity {

    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewConfig();
        initNavBarChangeEvent();
//        checkExpiredTimeToLogin();
    }

    private void initNavBarChangeEvent() {
        BubbleNavigationLinearView navigationBar = findViewById(R.id.navigationBar);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationBar.getLayoutParams();
        layoutParams.setBehavior(new BottomBarBehavior());
        getSupportFragmentManager().beginTransaction().replace(R.id.containerSearch, new HomeFragment()).commit();
        navigationBar.setNavigationChangeListener((view,  position) ->  {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new SearchFragment();
                    break;
                case 2:
                    fragment = new FavoriteFragment();
                    break;
                case 3:
                    fragment = new AccountFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.containerSearch, fragment).commit();
        });
    }

    private void initViewConfig() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        window.setAttributes(winParams);
    }

//    public void checkExpiredTimeToLogin(){
//        SharedPreferences sharedPreferences = getSharedPreferences("AuthLogin",MODE_PRIVATE);
//        String token = sharedPreferences.getString("token", null);
//        long tokenTime = sharedPreferences.getLong("expiredTime",0);
//        long currentTime = System.currentTimeMillis();
//        if(token != null && (currentTime - tokenTime) < 6000000){
//
//        }else{
//            Intent intent = new Intent(this, AuthActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }
}
