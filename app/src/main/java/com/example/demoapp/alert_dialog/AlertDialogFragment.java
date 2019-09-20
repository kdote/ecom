package com.example.demoapp.alert_dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.demoapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AlertDialogFragment extends DialogFragment {

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alert_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        titleText.setText(getTitle());
        bodyText.setText(getBody());
        dialogLogo.setImageResource(getIcon());
        titleText.setTextColor(getColor());

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

