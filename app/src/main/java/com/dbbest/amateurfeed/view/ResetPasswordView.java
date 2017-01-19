package com.dbbest.amateurfeed.view;

import android.common.framework.IView;

/**
 * Created by antonina on 19.01.17.
 */

public interface ResetPasswordView extends IView {
    void showEmptyEmailError();

    void showEmailValidationError();

    void showSuccessDialog();

    void showErrorConnectionDialog();

    void showErrorRegistrationDialog();

    void showProgressDialog();

    void dismissProgressDialog();
}
