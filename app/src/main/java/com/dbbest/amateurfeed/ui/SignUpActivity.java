package com.dbbest.amateurfeed.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.dbbest.amateurfeed.view.SignUpView;

/**
 * Created by antonina on 19.01.17.
 */

public class SignUpActivity extends AppCompatActivity implements SignUpView {
    @Override
    public void showEmptyEmailError() {

    }

    @Override
    public void showEmailValidationError() {

    }

    @Override
    public void showEmptyPasswordError() {

    }

    @Override
    public void showPasswordLengthValidationError() {

    }

    @Override
    public void showPasswordValidationError() {

    }

    @Override
    public void showEmptyConfirmPasswordError() {

    }

    @Override
    public void showConfirmPasswordValidationError() {

    }

    @Override
    public void showSuccessDialog() {

    }

    @Override
    public void showErrorConnectionDialog() {

    }

    @Override
    public void showErrorRegistrationDialog() {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @NonNull
    @Override
    public Context getContext() {
        return null;
    }
}