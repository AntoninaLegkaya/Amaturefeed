package com.dbbest.amateurfeed.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dbbest.amateurfeed.R;
import com.dbbest.amateurfeed.app.net.command.Command;
import com.dbbest.amateurfeed.presenter.SignUpPresenter;
import com.dbbest.amateurfeed.ui.util.UIDialogNavigation;
import com.dbbest.amateurfeed.ui.util.UiActivityNavigation;
import com.dbbest.amateurfeed.view.SignUpView;

/**
 * Created by antonina on 19.01.17.
 */

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    private SignUpPresenter mPresenter;
    private TextView mLoginScreenLink;
    private TextView mResetPswLink;
    private DialogFragment mProgressDialog;
    private Button mSignUpButton;
    private AppCompatEditText mEmailEditeText;
    private AppCompatEditText mNameEditeText;
    private AppCompatEditText mPhoneEditeText;
    private AppCompatEditText mPasswordEditeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        mPresenter = new SignUpPresenter();
        mEmailEditeText=(AppCompatEditText) findViewById(R.id.email);
        mNameEditeText=(AppCompatEditText) findViewById(R.id.fullname);
        mPhoneEditeText=(AppCompatEditText) findViewById(R.id.phone);
        mPasswordEditeText=(AppCompatEditText) findViewById(R.id.password);
        mLoginScreenLink = (TextView) findViewById(R.id.login_link);
        mLoginScreenLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(UiActivityNavigation.startActivity(SignUpActivity.this));
            }
        });
        mResetPswLink = (TextView) findViewById(R.id.reset_link);
        mResetPswLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.reset_link) {
                    startActivity(UiActivityNavigation.resetPassActivity(SignUpActivity.this));
                }
            }
        });
        mSignUpButton= (Button) findViewById(R.id.sign_up_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.registration(mEmailEditeText.getText().toString(),mNameEditeText.getText().toString(), mPhoneEditeText.getText().toString(),null,mPasswordEditeText.getText().toString(),null,"Android", null);

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.detachView();
    }

    @Override
    public void showEmptyEmailError() {
        UIDialogNavigation.showWarningDialog(R.string.emptyEmailError).show(getSupportFragmentManager(), "warn");
    }

    @Override
    public void showEmailValidationError() {
        UIDialogNavigation.showWarningDialog(R.string.incorrectEmail).show(getSupportFragmentManager(), "warn");
    }

    @Override
    public void showEmptyPasswordError() {
        UIDialogNavigation.showWarningDialog(R.string.emptyPasswordError).show(getSupportFragmentManager(), "warn");

    }

    @Override
    public void showEmptyFullNameError() {
        UIDialogNavigation.showWarningDialog(R.string.emptyNameError).show(getSupportFragmentManager(), "warn");

    }

    @Override
    public void showFullNameValidationError() {
        UIDialogNavigation.showWarningDialog(R.string.incorrectName).show(getSupportFragmentManager(), "warn");

    }

    @Override
    public void showPhoneValidationError() {
        UIDialogNavigation.showWarningDialog(R.string.incorrectPhone).show(getSupportFragmentManager(), "warn");
    }

    @Override
    public void showPasswordLengthValidationError() {
        UIDialogNavigation.showWarningDialog(R.string.lengthPasswordError).show(getSupportFragmentManager(), "warn");

    }

    @Override
    public void showPasswordValidationError() {
        UIDialogNavigation.showWarningDialog(R.string.characterPasswordError).show(getSupportFragmentManager(), "warn");

    }


    @Override
    public void showSuccessDialog() {
        UIDialogNavigation.showWarningDialog(R.string.success_registartion).show(getSupportFragmentManager(), "warn");
    }

    @Override
    public void showErrorConnectionDialog() {
        UIDialogNavigation.showWarningDialog(R.string.no_internet_connection).show(getSupportFragmentManager(), "warn");
    }

    @Override
    public void showErrorRegistrationDialog() {
        UIDialogNavigation.showWarningDialog(R.string.error_registartion).show(getSupportFragmentManager(), "warn");
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog = UIDialogNavigation.showProgressDialog();
        mProgressDialog.show(getSupportFragmentManager(), "progress");
    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void navigateToStartScreen() {
        startActivity(UiActivityNavigation.startActivity(SignUpActivity.this));
    }

    @NonNull
    @Override
    public Context getContext() {
        return this;
    }
}
