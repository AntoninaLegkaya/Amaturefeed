package com.dbbest.amateurfeed.view;

import android.common.framework.IView;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by antonina on 19.01.17.
 */

public interface SignUpView extends IView {

    void showEmptyEmailError();

    void showEmailValidationError();

    void showEmptyPasswordError();

    void showEmptyFullNameError();

    void showFullNameValidationError();

    void showPhoneValidationError();

    void showPasswordLengthValidationError();

    void showPasswordValidationError();

    void showSuccessDialog();

    void showErrorConnectionDialog();

    void showErrorRegistrationDialog();

    void showProgressDialog();

    void dismissProgressDialog();

    void navigateToStartScreen();
}
