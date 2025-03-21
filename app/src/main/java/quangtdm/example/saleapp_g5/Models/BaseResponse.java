package quangtdm.example.saleapp_g5.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class BaseResponse<T> {
    @SerializedName("data")
    private T data;

    @SerializedName("additionalData")
    private Object additionalData;

    @SerializedName("message")
    private String message;

    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("code")
    private String code;

    // Getters and setters
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public Object getAdditionalData() {
        return additionalData;
    }
    public void setAdditionalData(Object additionalData) {
        this.additionalData = additionalData;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}

