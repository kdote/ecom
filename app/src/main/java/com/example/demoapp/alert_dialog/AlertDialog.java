package com.example.demoapp.alert_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demoapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AlertDialog extends Dialog {

    @BindView(R.id.alertLayout)
    RelativeLayout alertLayout;
    @BindView(R.id.dialogLogo)
    CircleImageView dialogLogo;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.bodyText)
    TextView bodyText;
    @BindView(R.id.cancelButton)
    Button cancelButton;
    @BindView(R.id.okButton)
    TextView okButton;

    private String title;
    private String body;
    private String buttonOkText;
    private String buttonCancelText;
    private int icon=0;
    private int color;
    private View.OnClickListener okListener = null;
    private View.OnClickListener cancelListener = null;

    public AlertDialog(Context context) {
        super(context);
    }

    public AlertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        ButterKnife.bind(this);

        titleText.setText(getTitle());
        bodyText.setText(getBody());
        dialogLogo.setImageResource(getIcon());
        titleText.setBackgroundColor(getColor());
        alertLayout.setBackgroundColor(Color.TRANSPARENT);
        okButton.setText(buttonOkText);
        okButton.setOnClickListener(okListener);
        cancelButton.setText(buttonCancelText);
        cancelButton.setOnClickListener(cancelListener);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setPositiveButton(String ok, View.OnClickListener okListener){
        dismiss();
        this.buttonOkText = ok;
        this.okListener = okListener;
    }

    public void setNegativeButton(String cancel, View.OnClickListener cancelListener){
        dismiss();
        this.buttonCancelText = cancel;
        this.cancelListener = cancelListener;
    }
}
