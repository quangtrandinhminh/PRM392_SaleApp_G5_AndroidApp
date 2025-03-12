package quangtdm.example.saleapp_g5.Models;

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(String email, String password) {
        this.username = email;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
