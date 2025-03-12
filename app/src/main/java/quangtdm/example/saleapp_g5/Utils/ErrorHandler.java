package quangtdm.example.saleapp_g5.Utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import quangtdm.example.saleapp_g5.Models.BaseResponse;
import retrofit2.Response;

public class ErrorHandler {

    // Create a custom Gson instance with UPPER_CAMEL_CASE naming policy
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .create();

    /**
     * Parses the error body of a Retrofit Response using Gson.
     *
     * @param response The Retrofit Response with an error.
     * @return The extracted error message or a default message if parsing fails.
     */
    public static String parseError(Response<?> response) {
        String errorMsg;
        try {
            String errorBody = response.errorBody() != null
                    ? response.errorBody().string()
                    : "";
            BaseResponse<?> errorResponse = gson.fromJson(errorBody, BaseResponse.class);;
            errorMsg = (errorResponse != null && errorResponse.getMessage() != null
                    && !errorResponse.getMessage().isEmpty())
                    ? errorResponse.getMessage()
                    : "An error occurred with status code: " + response.code();
        } catch (Exception e) {
            e.printStackTrace();
            errorMsg = "An error occurred with status code: " + response.code();
        }
        return errorMsg;
    }
}

