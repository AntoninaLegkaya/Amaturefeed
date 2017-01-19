package com.dbbest.amateurfeed.presenter;

import android.common.framework.Presenter;
import android.common.util.TextUtils;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.dbbest.amateurfeed.app.net.NetworkUtil;
import com.dbbest.amateurfeed.app.net.command.Command;
import com.dbbest.amateurfeed.app.net.command.CommandResultReceiver;
import com.dbbest.amateurfeed.app.net.command.RegistrationCommand;
import com.dbbest.amateurfeed.utils.Utils;
import com.dbbest.amateurfeed.view.SignUpView;

import static android.R.attr.data;

/**
 * Created by antonina on 19.01.17.
 */

public class SignUpPresenter extends Presenter<SignUpView> implements CommandResultReceiver.CommandListener {

    private static final int CODE_REGISTRATION = 1;

    private CommandResultReceiver mResultReceiver;

    public void registration(String email, String firstName, String lastName, String password) {

        if (getView() != null) {
            SignUpView view = getView();

            if (TextUtils.isEmpty(email)) {
                view.showEmptyEmailError();
                return;
            } else if (!Utils.isEmailValid(email)) {
                view.showEmailValidationError();
                return;
            }
            if (TextUtils.isEmpty(firstName)) {
                view.showEmptyEmailError();
                return;
            } else if (!Utils.isFirstNameValid(firstName)) {
                view.showEmailValidationError();
                return;
            }
            if (TextUtils.isEmpty(lastName)) {
                view.showEmptyEmailError();
                return;
            } else if (!Utils.isLastNameValid(lastName)) {
                view.showEmailValidationError();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                view.showEmptyPasswordError();
                return;
            } else if (!Utils.isPasswordLengthValid(password)) {
                view.showPasswordLengthValidationError();
                return;
            } else if (!Utils.isPasswordValid(password)) {
                view.showPasswordValidationError();
                return;
            }


            view.showProgressDialog();
            Command command = new RegistrationCommand(email, firstName, lastName, password);
            command.send(CODE_REGISTRATION, mResultReceiver);


        }
    }

    @Override
    protected void onAttachView(@NonNull SignUpView view) {
        if (mResultReceiver == null) {
            mResultReceiver = new CommandResultReceiver();
        }
        mResultReceiver.setListener(this);
    }

    @Override
    protected void onDetachView(@NonNull SignUpView view) {
        if (mResultReceiver != null) {
            mResultReceiver.setListener(null);
        }
    }


    @Override
    public void onSuccess(int code, Bundle data) {
        if (getView() != null) {
            getView().dismissProgressDialog();
            getView().showSuccessDialog();
        }
    }

    @Override
    public void onFail(int code, Bundle data) {
        if (getView() != null) {
            getView().dismissProgressDialog();
            int errCode = Command.grabErrorCode(data);
            if (errCode == NetworkUtil.CODE_SOCKET_TIMEOUT || errCode == NetworkUtil.CODE_UNABLE_TO_RESOLVE_HOST) {
                getView().showErrorConnectionDialog();
            } else {
                getView().showErrorRegistrationDialog();
            }
        }
    }

    @Override
    public void onProgress(int code, Bundle data, int progress) {
        if (getView() != null) {
            getView().dismissProgressDialog();
        }
    }
}