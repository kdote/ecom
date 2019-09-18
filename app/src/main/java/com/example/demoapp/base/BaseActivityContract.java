package com.example.demoapp.base;

public interface BaseActivityContract {
    interface Presenter{
        void toolBarStatus(boolean show);
    }

    interface View{
        void showToolBar();
        void hideToolBar();
    }
}
