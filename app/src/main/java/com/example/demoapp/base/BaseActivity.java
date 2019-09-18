package com.example.demoapp.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.demoapp.App;
import com.example.demoapp.R;
import com.example.demoapp.cart.CartFragment;
import com.example.demoapp.categories.CategoryFragment;
import com.example.demoapp.home.HomeFragment;
import com.example.demoapp.information.InformationFragment;
import com.example.demoapp.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BaseActivityContract.View {

    Toolbar toolbar;
    BottomNavigationView bottomNavigation;
    CoordinatorLayout baseLayout;

    @Inject
    SharedPreferences preferences;

    @Inject
    SharedPreferences.Editor editor;

    BaseActivityContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        ((App) getApplication()).getAppComponent().inject(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        presenter = new BaseActivityPresenter(this, preferences, editor);
    }

    public BaseActivityContract.Presenter getBasePresenter(){
        return presenter;
    }

    @Override
    public void setContentView(int layoutResID) {
        baseLayout = findViewById(R.id.baseLayout);
        ViewGroup viewGroup = findViewById(R.id.child_element);
        baseLayout.inflate(this, layoutResID, viewGroup);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch(menuItem.getItemId()) {
            case R.id.home_navigation:
                fragment = new HomeFragment();
                break;

            case R.id.categories_navigation:
                fragment = new CategoryFragment();
                break;

            case R.id.cart_navigation:
                fragment = new CartFragment();
                break;

            case R.id.info_navigation:
                fragment = new InformationFragment();
                break;
        }
        return loadFragment(fragment);
    }

    protected boolean loadFragment(Fragment fragment) {
        if (fragment !=null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.navigationItemContainer, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logOut_navigation:
                logOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Message");
        builder.setMessage("Are You Sure? You want to Log Out");
        builder.setPositiveButton("Log Out", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                clearUserdata();
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }

    private void clearUserdata() {
            if (preferences.getBoolean(LoginActivity.IS_REMEMERED, false)){
                editor.remove(LoginActivity.IS_LOGGEDIN);
            }
            else{
                editor.clear();
            }
            editor.commit();
        startActivity(LoginActivity.createIntent(this));
        finish();
    }


    protected void hideBottomNavigation(){
        bottomNavigation.setVisibility(View.GONE);
    }

    @Override
    public void showToolBar() {
        getSupportActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void hideToolBar() {
        getSupportActionBar().hide();
    }

    @Override
    public void successSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(baseLayout, message, Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(Color.GREEN);
        TextView textView = snackView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(18);
        snackbar.show();
    }

    @Override
    public void errorSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(baseLayout, message, Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(Color.RED);
        TextView textView = snackView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(18);
        snackbar.show();
    }
}
