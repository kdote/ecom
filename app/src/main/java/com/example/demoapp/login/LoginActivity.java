package com.example.demoapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapp.App;
import com.example.demoapp.MainActivity;
import com.example.demoapp.R;
import com.example.demoapp.base.BaseActivity;
import com.example.demoapp.model.User;
import com.example.demoapp.network.ApiInterface;
import com.example.demoapp.register.RegisterActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginActivityContract.View {

    @BindView(R.id.imageSlider)
    SliderView imageSlider;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.loginProgressBar)
    ProgressBar loginProgressBar;
    @BindView(R.id.goToRegister)
    TextView goToRegister;
    @BindView(R.id.textInputEmail1)
    TextInputLayout textInputEmail;
    @BindView(R.id.textInputPassword1)
    TextInputLayout textInputPassword;
    @BindView(R.id.remember)
    CheckBox remember;

    @Inject
    ApiInterface api;

    @Inject
    SharedPreferences preferences;

    @Inject
    SharedPreferences.Editor editor;

    LoginActivityContract.Presenter presenter;

    private User user;

    public static final String AUTH = "auth";
    public static final String USER_EMAIL = "userEmail";
    public static final String IS_REMEMERED = "isRemembered";
    public static final String IS_LOGGEDIN = "isLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getAppComponent().inject(this);
        ButterKnife.bind(this);
        getBasePresenter().toolBarStatus(false);
        hideBottomNavigation();


        imageSliderView();
        presenter = new LoginActivityPresenter(this, api, preferences, editor);

        if (presenter.isLoggedIn()){
            startActivity(MainActivity.createIntent(this));
            finish();
        }
        if (presenter.isRemembered()){
            user = presenter.getAuth().getUser().get(0);
            textInputEmail.getEditText().setText(user.getEmail());
            remember.setChecked(true);
        }
    }

    private void imageSliderView() {
        ImageSliderAdapter adapter = new ImageSliderAdapter(this);

        imageSlider.setSliderAdapter(adapter);

        imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider.setIndicatorSelectedColor(Color.WHITE);
        imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        imageSlider.startAutoCycle();
    }

    public static Intent createIntent(Context context){
        Intent loginIntent = new Intent(context, LoginActivity.class);
        return loginIntent;
    }




    @Override
    public String getInputEmail() {
        return textInputEmail.getEditText().getText().toString().trim();
    }

    @Override
    public String getInputPassword() {
        return textInputPassword.getEditText().getText().toString();
    }

    @Override
    public boolean isRemembered() {
        return remember.isChecked();
    }

    @Override
    public void showColorToastMessage(String message) {
        getBasePresenter().styleableToast(message);
    }

    @Override
    public void showLoginProgressBar() {
        loginProgressBar.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);
    }

    @Override
    public void hideLoginProgressBar() {
        loginProgressBar.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void loginError(String message) {
        getBasePresenter().snackBarStatus(message, false);
        hideLoginProgressBar();
    }

    @Override
    public void setErrorMessage(int inputLayout, String message) {

        switch(inputLayout){
            case 1:
                textInputEmail.setError(message);

            case 2:
                textInputPassword.setError(message);

            default:
        }

    }

    @Override
    public void loginSuccess() {
        hideLoginProgressBar();
        startActivity(MainActivity.createIntent(this));
        finish();
    }



    @OnClick(R.id.loginButton)
    public void setLoginButton(){
        presenter.confirmValidation();
    }

    @OnClick(R.id.goToRegister)
    public void setGoToRegister(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
