package com.example.demoapp.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapp.App;
import com.example.demoapp.R;
import com.example.demoapp.login.LoginActivity;
import com.example.demoapp.network.ApiInterface;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    @BindView(R.id.textInputFirstname)
    TextInputLayout textInputFirstname;
    @BindView(R.id.textInputLastname)
    TextInputLayout textInputLastname;
    @BindView(R.id.textInputEmail)
    TextInputLayout textInputEmail;
    @BindView(R.id.textInputAddress)
    TextInputLayout textInputAddress;
    @BindView(R.id.textInputCity)
    TextInputLayout textInputCity;
    @BindView(R.id.textInputTelephone)
    TextInputLayout textInputTelephone;
    @BindView(R.id.textInputPassword)
    TextInputLayout textInputPassword;
    @BindView(R.id.textInputConfirmPassword)
    TextInputLayout textInputConfirmPassword;

    @BindView(R.id.registerButton)
    Button registerButton;

    @BindView((R.id.registerProgressBar))
    ProgressBar registerProgressBar;

    @BindView(R.id.goToLogin)
    TextView goToLogin;

    RegisterContract.Presenter presenter;
//    List<TextInputLayout> textInputLayoutList;

    @Inject
    ApiInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ((App) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        presenter = new RegisterActivityPresenter(this, api);
//        textInputLayoutList = new ArrayList<>();
//        addLayoutToValidate();
    }

//    private void addLayoutToValidate() {
//        textInputLayoutList.add(textInputFirstname);
//        textInputLayoutList.add(textInputLastname);
//    }


    @Override
    public void showRegisterProgressBar() {
        registerProgressBar.setVisibility(View.VISIBLE);
        registerButton.setVisibility(View.GONE);
    }

    @Override
    public void hideRegisterProgressBar() {
        registerProgressBar.setVisibility(View.GONE);
        registerButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void registrationSuccess() {
        startActivity(LoginActivity.createIntent(this));
    }

    @Override
    public void registrationError() {
        Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
        hideRegisterProgressBar();
    }

    @Override
    public String getUserFirstname() {
        return textInputFirstname.getEditText().getText().toString().trim();
    }

    @Override
    public String getUserLastname() {
        return textInputLastname.getEditText().getText().toString().trim();
    }

    @Override
    public String getUserEmail() {
        return textInputEmail.getEditText().getText().toString().trim();
    }

    @Override
    public String getUserAddress() {
        return textInputAddress.getEditText().getText().toString().trim();
    }

    @Override
    public String getUserCity() {
        return textInputCity.getEditText().getText().toString().trim();
    }

    @Override
    public String getUserTelephone() {
        return textInputTelephone.getEditText().getText().toString().trim();
    }

    @Override
    public String getUserPassword() {
        return textInputPassword.getEditText().getText().toString().trim();
    }

    @Override
    public String getConfirmPassword() {
        return textInputConfirmPassword.getEditText().getText().toString().trim();
    }

    @Override
    public void setErrorMessage(int inputLayout, String message) {
        switch (inputLayout){
            case 1:
                textInputFirstname.setError(message);
            case 2:
                textInputLastname.setError(message);
            case 3:
                textInputEmail.setError(message);
            case 4:
                textInputAddress.setError(message);
            case 5:
                textInputCity.setError(message);
            case 6:
                textInputTelephone.setError(message);
            case 7:
                textInputPassword.setError(message);
            case 8:
                textInputConfirmPassword.setError(message);
            default:
        }
    }

//    @Override
////    public void setError(TextInputLayout textInputLayout) {
////        textInputLayout.setError("This field cannot be empty!");
////    }

    @OnClick(R.id.registerButton)
    public void setRegisterButton(){
        presenter.confirmValidation();
//        presenter.register();
//        if(textInputLayoutList!= null) {
//            if (presenter.isLayoutValid(textInputLayoutList)) {
//                presenter.register();
//            }
//        }
    }

    @OnClick(R.id.goToLogin)
    public void setGoToLogin(){
        startActivity(LoginActivity.createIntent(this));
    }


}
