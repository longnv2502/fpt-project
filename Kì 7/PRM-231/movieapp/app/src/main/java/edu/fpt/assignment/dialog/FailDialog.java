package edu.fpt.assignment.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fpt.assignment.R;
import kotlin.jvm.internal.Intrinsics;

public class FailDialog extends Dialog implements View.OnClickListener {

    @BindView(R.id.tv_close)
    TextView tvClose;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_content)
    TextView tvContent;

    @BindView(R.id.imv_close)
    ImageView imvClose;

    private final String title;
    public final String content;

    public FailDialog(@NotNull Context context, @NotNull String title, @NotNull String content) {
        super(context);
        this.title = title;
        this.content = content;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_fail);
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
        tvTitle.setText(this.title);
        tvContent.setText(this.content);

        tvClose.setOnClickListener(this);
        imvClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (Intrinsics.areEqual(v, tvClose) || Intrinsics.areEqual(v, imvClose)) {
            dismiss();
        }
    }
}

    
