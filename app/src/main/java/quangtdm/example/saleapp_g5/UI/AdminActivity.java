package quangtdm.example.saleapp_g5.UI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import quangtdm.example.saleapp_g5.R;
import quangtdm.example.saleapp_g5.UI.Chat.ChatFragment;
import quangtdm.example.saleapp_g5.UI.Noti.NotiFragment;
import quangtdm.example.saleapp_g5.UI.Product.HomeFragment;
import quangtdm.example.saleapp_g5.UI.User.MeFragment;
import quangtdm.example.saleapp_g5.Utils.TokenManager;

public class AdminActivity extends AppCompatActivity {
    private TextView tvHello;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_admin);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        initView();
        initEvent();
        setupBackPressedCallback();
    }

    private void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        bottomNav = findViewById(R.id.admin_bottom_nav);
    }

    private void initEvent() {
        showHello();
        bottomNav.setOnItemSelectedListener(this::onNavigationItemSelected);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_admin_home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.navigation_admin_chat:
                selectedFragment = new ChatFragment();
            case R.id.navigation_admin_notifications:
                selectedFragment = new NotiFragment();
                break;
            case R.id.navigation_admin_me:
                selectedFragment = new MeFragment();
                break;
        }
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flAdminContainer, selectedFragment)
                    .commit();
        }
        return true;
    }

    private void setupBackPressedCallback() {
        // Create a callback for handling back press events.
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // back to home fragment
                bottomNav.setSelectedItemId(R.id.navigation_admin_home);
            }
        };

        // Add the callback to the activity's OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void showHello() {
        String username = TokenManager.getInstance(this).getUsername() != null
                ? TokenManager.getInstance(this).getUsername() : "User";
        tvHello.setText("Hello Admin " + username);
    }
}