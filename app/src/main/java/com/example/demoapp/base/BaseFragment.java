package com.example.demoapp.base;

import androidx.fragment.app.Fragment;

import com.example.demoapp.App;
import com.example.demoapp.di.component.AppComponent;

public class BaseFragment extends Fragment {

    public BaseActivityContract.Presenter getBasePresenter(){
        BaseActivity activity = (BaseActivity) getActivity();
        return activity.getBasePresenter();
    }

    public AppComponent getAppComponent(){
        return ((App) getActivity().getApplication()).getAppComponent();
    }

}
