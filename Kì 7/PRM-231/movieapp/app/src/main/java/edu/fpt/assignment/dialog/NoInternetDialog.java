package edu.fpt.assignment.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fpt.assignment.R;
import kotlin.jvm.internal.Intrinsics;

public class NoInternetDialog extends Dialog implements View.OnClickListener {

    @BindView(R.id.tv_ok_dialog_no_internet)
    TextView tvDong;

    public NoInternetDialog(@NotNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_no_internet);
        ButterKnife.bind(this);
        Window window = this.getWindow();
        window.getDecorView().setBackgroundResource(R.color.transparent);
        window.getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        window.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        this.setCancelable(true);
        this.initView();
    }

    private void initView() {
        tvDong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (Intrinsics.areEqual(v, tvDong)) {
            dismiss();
        }
    }
}
