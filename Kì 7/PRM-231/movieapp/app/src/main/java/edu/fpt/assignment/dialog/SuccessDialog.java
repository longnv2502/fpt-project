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

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fpt.assignment.R;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;


public class SuccessDialog extends Dialog implements View.OnClickListener {

    @BindView(R.id.tv_close)
    TextView tvClose;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_content)
    TextView tvContent;

    @BindView(R.id.imv_close)
    ImageView imvClose;

    private final String title;
    private final String content;
    private final Runnable callbacks;

    public SuccessDialog(@NotNull Context context, @NotNull String title, @NotNull String content, @NotNull Runnable callbacks) {
        super(context);
        this.title = title;
        this.content = content;
        this.callbacks = callbacks;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_success);
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
        tvClose.setOnClickListener(this);
        imvClose.setOnClickListener(this);
        tvTitle.setText(title);
        tvContent.setText(content);
    }

    @Override
    public void onClick(View v) {
        if (Intrinsics.areEqual(v, imvClose)) {
            dismiss();
        } else if (Intrinsics.areEqual(v, tvClose)) {
            dismiss();
            callbacks.run();
        }
    }
}
