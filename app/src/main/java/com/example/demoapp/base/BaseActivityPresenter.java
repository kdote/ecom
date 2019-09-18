package com.example.demoapp.base;

import android.content.SharedPreferences;

import com.example.demoapp.login.LoginActivity;

public class BaseActivityPresenter implements BaseActivityContract.Presenter {
    BaseActivityContract.View view;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public BaseActivityPresenter(BaseActivityContract.View view, SharedPreferences preferences, SharedPreferences.Editor editor) {
        this.view = view;
        this.preferences = preferences;
        this.editor = editor;
    }

    @Override
    public void toolBarStatus(boolean show) {
        if (show) view.showToolBar();
        else view.hideToolBar();
    }
}
