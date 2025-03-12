package quangtdm.example.saleapp_g5.Repositories;

import java.io.IOException;

import quangtdm.example.saleapp_g5.Models.RegisterRequest;
import quangtdm.example.saleapp_g5.Network.RetrofitClient;
import quangtdm.example.saleapp_g5.Models.BaseResponse;
import quangtdm.example.saleapp_g5.Models.LoginRequest;
import quangtdm.example.saleapp_g5.Services.AuthService;

import quangtdm.example.saleapp_g5.Utils.ErrorHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final AuthService authService;

    public interface AuthCallback {
        void onSuccess(Object data);
        void onError(int code, String errorMessage);
        void onFailure(Throwable throwable);
    }

    public AuthRepository() {
        authService = RetrofitClient.getClient().create(AuthService.class);
    }

    public void login(LoginRequest loginRequest, final AuthCallback callback) {
        authService.login(loginRequest);
        Call<BaseResponse> call = authService.login(loginRequest);

        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().getData());
                } else {
                    String errorMsg = ErrorHandler.parseError(response);
                    callback.onError(response.code(), errorMsg);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                // Handle network error
                callback.onFailure(t);
            }
        });
    }

    public void register(RegisterRequest registerRequest, final AuthCallback callback) {
        Call<BaseResponse> call = authService.register(registerRequest);

        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().getData());
                } else {
                    String errorMsg = ErrorHandler.parseError(response);
                    callback.onError(response.code(), errorMsg);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
