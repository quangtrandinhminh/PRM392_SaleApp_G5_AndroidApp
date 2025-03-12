package quangtdm.example.saleapp_g5.UI.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import quangtdm.example.saleapp_g5.Models.RegisterRequest;
import quangtdm.example.saleapp_g5.R;
import quangtdm.example.saleapp_g5.Repositories.AuthRepository;
import quangtdm.example.saleapp_g5.UI.MainActivity;
import quangtdm.example.saleapp_g5.Utils.ToastManager;
import quangtdm.example.saleapp_g5.Utils.TokenManager;

public class RegisterFragment extends Fragment {
    private CardView cvRegister;
    private EditText edtRegisterUsername, edtEmail, edtPhone,
            edtRegisterPassword, edtConfirmPassword, edtAddress;
    private Button btnRegister;
    private TextView tvLogin;

    private AuthRepository authRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the fragment's layout
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAnimations(view);
        initViews(view);
    }

    private void initAnimations(View view) {
        CardView cardView = view.findViewById(R.id.cvRegister);
        cardView.post(() -> {
            float initialOffset = cardView.getHeight();
            cardView.setTranslationY(initialOffset);
            cardView.animate().translationY(0).setDuration(300).start();
        });
    }

    private void initViews(View view) {
        edtRegisterUsername = view.findViewById(R.id.edtRegisterUsername);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtRegisterPassword = view.findViewById(R.id.edtRegisterPassword);
        edtConfirmPassword = view.findViewById(R.id.edtConfirmPassword);
        edtAddress = view.findViewById(R.id.edtAddress);
        btnRegister = view.findViewById(R.id.btnRegister);
        tvLogin = view.findViewById(R.id.tvLogin);
        authRepository = new AuthRepository();

        btnRegister.setOnClickListener(v -> attemptRegister());
        tvLogin.setOnClickListener(this::viewLoginClicked);
    }

    public void viewLoginClicked(View view) {
        // Replace RegisterFragment with LoginFragment
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flAuth, new LoginFragment()) // Replace with LoginFragment
                .addToBackStack(null) // Allows back navigation
                .commit();
    }

    private void attemptRegister() {
        btnRegister.setEnabled(false);
        // Get user input
        String username = edtRegisterUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String password = edtRegisterPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        

        // Simple validations
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(address)) {
            ToastManager.show(getContext(), "Please fill in all fields");

            btnRegister.setEnabled(true);
            return;
        }

        if (!password.equals(confirmPassword)) {
            ToastManager.show(getContext(), "Passwords do not match");

            btnRegister.setEnabled(true);
            return;
        }

        // Create a RegisterRequest model
        RegisterRequest registerRequest = new RegisterRequest(
                username, email, phone, password, confirmPassword, address);

        // Call repository to register
        authRepository.register(registerRequest, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(Object data) {
                ToastManager.show(getContext(), "Registration successful");
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flAuth, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onError(int code, String errorMessage) {
                // API error
                ToastManager.show(getContext(), errorMessage);
            }

            @Override
            public void onFailure(Throwable throwable) {
                // Network or unexpected error
                ToastManager.show(getContext(), throwable.getMessage());
            }
        });

        btnRegister.setEnabled(true);
    }
}
