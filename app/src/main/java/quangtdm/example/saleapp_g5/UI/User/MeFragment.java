package quangtdm.example.saleapp_g5.UI.User;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import quangtdm.example.saleapp_g5.R;
import quangtdm.example.saleapp_g5.UI.Auth.AuthActivity;
import quangtdm.example.saleapp_g5.UI.MainActivity;
import quangtdm.example.saleapp_g5.Utils.TokenManager;

public class MeFragment extends Fragment {
    private TextView tvUsername, tvRole;
    private Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initListeners(view);
        loadUser();
    }

    private void initViews(View view) {
        tvUsername = view.findViewById(R.id.tvUsername);
        tvRole = view.findViewById(R.id.tvRole);
    }

    private void initListeners(View view) {
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> logout());
    }

    private void loadUser() {
        String username = TokenManager.getInstance(getActivity()).getUsername();
        String role = TokenManager.getInstance(getActivity()).getRole();

        tvUsername.setText(username);
        tvRole.setText(role);
    }

    private void logout() {
        TokenManager.getInstance(getActivity()).clearTokens();
        Intent intent = new Intent(getActivity(), AuthActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}