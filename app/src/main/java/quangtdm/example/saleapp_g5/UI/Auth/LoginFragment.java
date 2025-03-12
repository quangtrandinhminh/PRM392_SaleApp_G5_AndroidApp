package quangtdm.example.saleapp_g5.UI.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import quangtdm.example.saleapp_g5.Models.LoginRequest;
import quangtdm.example.saleapp_g5.R;
import quangtdm.example.saleapp_g5.Repositories.AuthRepository;
import quangtdm.example.saleapp_g5.UI.AdminActivity;
import quangtdm.example.saleapp_g5.UI.MainActivity;
import quangtdm.example.saleapp_g5.Utils.ToastManager;
import quangtdm.example.saleapp_g5.Utils.TokenManager;

public class LoginFragment extends Fragment {
    private EditText edtUsername, edtPassword;
    private AuthRepository authRepository;
    private Button btnLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAnimations(view);
        initViews(view);
    }

    private void initAnimations(View view) {
        CardView cardView = view.findViewById(R.id.cvLogin);
        cardView.post(() -> {
            float initialOffset = cardView.getHeight();
            cardView.setTranslationY(initialOffset);
            cardView.animate().translationY(0).setDuration(300).start();
        });
    }

    private void initViews(View view) {
        edtUsername = view.findViewById(R.id.edtUsername);
        edtPassword = view.findViewById(R.id.edtPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        TextView tvRegister = view.findViewById(R.id.tvRegister);
        authRepository = new AuthRepository();

        btnLogin.setOnClickListener(v -> login());
        tvRegister.setOnClickListener(this::viewRegisterClicked);
    }

    public void viewRegisterClicked(View view) {
        // Replace LoginFragment with RegisterFragment
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flAuth, new RegisterFragment()) // Replace with RegisterFragment
                .addToBackStack(null) // Allows back navigation
                .commit();
    }

    private void login() {
        btnLogin.setEnabled(false);
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            ToastManager.show(getContext(), "Please enter username and password");

            btnLogin.setEnabled(true);
            return;
        }

        LoginRequest loginRequest = new LoginRequest(username, password);
        authRepository.login(loginRequest, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(Object data) {
                TokenManager.getInstance(getContext()).saveAccessToken(data.toString());

                String role = TokenManager.getInstance(getContext()).getRole();
                if (role != null && role.equals("Admin")) {
                    startActivity(new Intent(getActivity(), AdminActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }

                getActivity().finish();
            }

            @Override
            public void onError(int code, String errorMessage) {
                ToastManager.show(getContext(), errorMessage);
            }

            @Override
            public void onFailure(Throwable throwable) {
                ToastManager.show(getContext(), throwable.getMessage());
            }
        });

        btnLogin.setEnabled(true);
    }
}
