package quangtdm.example.saleapp_g5.Network;

import android.util.Log;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RetrofitClient {
    private static String BASE_URL = "http://localhost:5288";
    private static Retrofit retrofit = null;
    private static String token = null;

    // Set Token (Call this method after user logs in)
    public static void setToken(String authToken) {
        token = authToken;
    }

    // Create an OkHttp Interceptor to add Authorization Header
    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder requestBuilder = originalRequest.newBuilder();

                        // Add token to header if available
                        if (token != null && !token.isEmpty()) {
                            String authHeader = "Bearer " + token;
                            requestBuilder.addHeader("Authorization", authHeader);
                            Log.d("Interceptor", "Adding header: " + authHeader);
                        } else {
                            Log.d("Interceptor", "No token available");
                        }

                        Request modifiedRequest = requestBuilder.build();
                        return chain.proceed(modifiedRequest);
                    }
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    // Create a Retrofit instance
    // Set the base URL
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

