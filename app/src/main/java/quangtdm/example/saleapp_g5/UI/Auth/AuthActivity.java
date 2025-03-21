package quangtdm.example.saleapp_g5.UI.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import quangtdm.example.saleapp_g5.Network.RetrofitClient;
import quangtdm.example.saleapp_g5.R;
import quangtdm.example.saleapp_g5.UI.AdminActivity;
import quangtdm.example.saleapp_g5.UI.MainActivity;
import quangtdm.example.saleapp_g5.Utils.TokenManager;

public class AuthActivity extends AppCompatActivity {
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_auth);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        checkLogin();
        initViews();
        initListeners();
        setupBackPressedCallback();
    }

    private void initViews() {
        if(getSupportActionBar() != null) getSupportActionBar().hide();

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void initListeners() {
        btnLogin.setOnClickListener(v -> showFragment(new LoginFragment()));
        btnRegister.setOnClickListener(v -> showFragment(new RegisterFragment()));
    }

    private void showFragment(Fragment fragment) {
        findViewById(R.id.flAuth).setVisibility(View.VISIBLE); // Show FrameLayout
        btnLogin.setVisibility(View.GONE);
        btnRegister.setVisibility(View.GONE);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flAuth, fragment)
                .addToBackStack(null) // Allows going back to button selection
                .commit();
    }

    private void setupBackPressedCallback() {
        // Create a callback for handling back press events.
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    // Pop the fragment from the back stack
                    getSupportFragmentManager().popBackStack();
                    // Hide the fragment container and show buttons again
                    findViewById(R.id.flAuth).setVisibility(View.GONE);
                    btnLogin.setVisibility(View.VISIBLE);
                    btnRegister.setVisibility(View.VISIBLE);
                } else {
                    // If no fragment is in the back stack, let the system handle the back press
                    setEnabled(false); // Disable this callback to allow normal back press behavior
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        };

        // Add the callback to the activity's OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    // check if user is logged in
    private void checkLogin() {
        if(TokenManager.getInstance(this).isExpired())
            return;

        String role = TokenManager.getInstance(this).getRole();
        RetrofitClient.setToken(TokenManager.getInstance(this).getAccessToken());
        if (role != null && role.equals("Admin")) {
            startActivity(new Intent(this, AdminActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }

        finish();
    }
}