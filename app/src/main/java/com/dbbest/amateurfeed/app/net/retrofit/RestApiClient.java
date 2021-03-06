package com.dbbest.amateurfeed.app.net.retrofit;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.dbbest.amateurfeed.App;
import com.dbbest.amateurfeed.app.net.NetworkUtil;
import com.dbbest.amateurfeed.app.net.request.LoginRequestModel;
import com.dbbest.amateurfeed.app.net.request.RegistrationFaceBookRequestModel;
import com.dbbest.amateurfeed.app.net.request.RegistrationRequestModel;
import com.dbbest.amateurfeed.app.net.request.ResetRequestPasswordModel;
import com.dbbest.amateurfeed.app.net.response.LoginResponseModel;
import com.dbbest.amateurfeed.app.net.response.ResetResponse;
import com.dbbest.amateurfeed.app.net.response.ResponseWrapper;
import com.dbbest.amateurfeed.utils.ActionUtils;
import com.dbbest.amateurfeed.utils.Utils;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by antonina on 20.01.17.
 */

public class RestApiClient {

    private static final String USER_BLOCKED_MSG_RESPONSE = "User is blocked";
    private RestApiServices mApiService;

    public RestApiClient(RestApiServices restApiService) {
        mApiService = restApiService;
    }


    private <T> ResponseWrapper<T> executeCall(Call<ResponseWrapper<T>> call) {
        try {
            Response<ResponseWrapper<T>> response = call.execute();
            if (!response.isSuccessful()) {
                Log.i(Utils.TAG_LOG, "Response code: " + response.code() + "message: " + response.message() + "successful: " + response.isSuccessful());
                if (response.errorBody() != null) {
                    Log.i(Utils.TAG_LOG, "Error body: " + response.errorBody().string());
                }
            }

            if (response.body() != null && !response.body().isSuccessful()) {
                Log.i(Utils.TAG_LOG, "Response body code: " + response.body().code() + "message: " + response.body().message() + "successful: " + response.body().isSuccessful());
                if (response.body().code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    String action = ActionUtils.ACTION_UNAUTHORIZED;
                    if (response.body().message() != null && response.body().message().equals(USER_BLOCKED_MSG_RESPONSE)) {
                        action = ActionUtils.ACTION_USER_BLOCKED;
                    }
                    Intent intent = new Intent(action);
                    LocalBroadcastManager.getInstance(App.instance())
                            .sendBroadcast(intent);
                }
            }

            return response.body();
        } catch (Exception e) {
            Log.e(Utils.TAG_LOG,"network error: "+ e);
            return NetworkUtil.handleError(e);
        }
    }


    public ResponseWrapper<LoginResponseModel> registration(RegistrationRequestModel request) {
        return executeCall(mApiService.register(request));
    }

    public ResponseWrapper<LoginResponseModel> registrationFacebook(RegistrationFaceBookRequestModel request) {
        return executeCall(mApiService.registerFacebook(request));
    }

    public ResponseWrapper<LoginResponseModel> login(LoginRequestModel request) {
        return executeCall(mApiService.login(request));
    }

    public ResponseWrapper<Object> forgotPassword(ResetRequestPasswordModel request) {
        return executeCall(mApiService.forgotPassword(request));
    }

}
