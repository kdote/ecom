package com.example.demoapp.base;

public interface BaseActivityContract {
    interface Presenter{
        void toolBarStatus(boolean show);
        void snackBarStatus(String message, boolean show);
    }

    interface View{
        void showToolBar();
        void hideToolBar();

        void successSnackBar(String message);
        void errorSnackBar(String message);
    }
}
