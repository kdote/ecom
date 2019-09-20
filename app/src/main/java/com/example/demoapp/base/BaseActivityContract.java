package com.example.demoapp.base;

public interface BaseActivityContract {
    interface Presenter{
        void toolBarStatus(boolean show);
        void snackBarStatus(String message, boolean show);

        void styleableToast(String message);
    }

    interface View{
        void showToolBar();
        void hideToolBar();

        void successSnackBar(String message);
        void errorSnackBar(String message);

        void styleToast(String message);
    }
}
