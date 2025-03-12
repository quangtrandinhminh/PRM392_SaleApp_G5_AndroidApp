package quangtdm.example.saleapp_g5.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RegisterRequest {

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String password;

    @NonNull
    private String confirmPassword;

    @Nullable
    private String address;

    // Constructor
    public RegisterRequest(@NonNull String username, @NonNull String email, @NonNull String phoneNumber,
                           @NonNull String password, @NonNull String confirmPassword, @Nullable String address) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.address = address;
    }

    // Getters and Setters
    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NonNull String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    // Validation Method
    public boolean isValid() {
        return isValidUsername() && isValidEmail() && isValidPhoneNumber() && isValidPassword() && isPasswordConfirmed();
    }

    private boolean isValidUsername() {
        return username != null && !username.isEmpty() && username.length() <= 100;
    }

    private boolean isValidEmail() {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPhoneNumber() {
        return phoneNumber != null && phoneNumber.matches("\\d{10}"); // Ensures it's exactly 10 digits
    }

    private boolean isValidPassword() {
        return password != null && password.length() >= 8 && password.length() <= 100;
    }

    private boolean isPasswordConfirmed() {
        return password.equals(confirmPassword);
    }
}


