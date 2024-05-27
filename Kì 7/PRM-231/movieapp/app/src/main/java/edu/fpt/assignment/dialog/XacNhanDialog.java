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

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fpt.assignment.R;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public class XacNhanDialog extends Dialog implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_content)
    TextView tvContent;

    @BindView(R.id.tv_xoa)
    TextView tvXoa;

    @BindView(R.id.tv_huy)
    TextView tvHuy;

    @BindView(R.id.imv_close)
    ImageView imvClose;


    private final String title;
    private final String content;
    private final Runnable dongY;
    private final Runnable huy;

    public XacNhanDialog(@NotNull Context context, @NotNull String title, @NotNull String content, @NotNull Runnable dongY, @NotNull Runnable huy) {
        super(context);
        this.title = title;
        this.content = content;
        this.dongY = dongY;
        this.huy = huy;
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
        imvClose.setOnClickListener(this);
        tvXoa.setOnClickListener(this);
        tvHuy.setOnClickListener(this);

        tvTitle.setText(title);
        tvContent.setText(content);
    }


    @Override
    public void onClick(View v) {
        if (Intrinsics.areEqual(v, imvClose) || Intrinsics.areEqual(v, tvHuy)) {
            huy.run();
            dismiss();
        } else if (Intrinsics.areEqual(v, imvClose)) {
            dongY.run();
            dismiss();
        }
    }
}
