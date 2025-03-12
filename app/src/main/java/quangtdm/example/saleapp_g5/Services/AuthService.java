package quangtdm.example.saleapp_g5.Services;

import quangtdm.example.saleapp_g5.Models.BaseResponse;
import quangtdm.example.saleapp_g5.Models.RegisterRequest;
import quangtdm.example.saleapp_g5.Network.WebAPIEndpoint;
import quangtdm.example.saleapp_g5.Models.LoginRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST(WebAPIEndpoint.Authentication.Login)
    Call<BaseResponse> login(@Body LoginRequest loginRequest);

    @POST(WebAPIEndpoint.Authentication.Register)
    Call<BaseResponse> register(@Body RegisterRequest registerRequest);
}
