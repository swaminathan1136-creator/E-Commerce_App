package com.example.e_commerce_app.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.e_commerce_app.LoginActivity;
import com.example.e_commerce_app.OrderHistoryActivity;
import com.example.e_commerce_app.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Toolbar
        View locationContainer = view.findViewById(R.id.locationContainer);
        TextView toolbarTitle = view.findViewById(R.id.toolbarTitle);
        View cartContainer = view.findViewById(R.id.cartContainer);

        if (locationContainer != null) locationContainer.setVisibility(View.GONE);
        if (cartContainer != null) cartContainer.setVisibility(View.GONE);
        if (toolbarTitle != null) {
            toolbarTitle.setVisibility(View.VISIBLE);
            toolbarTitle.setText("Profile");
        }

        Button editProfileButton = view.findViewById(R.id.editProfileButton);
        LinearLayout allOrdersButton = view.findViewById(R.id.allOrdersButton);
        LinearLayout addressesButton = view.findViewById(R.id.addressesButton);
        LinearLayout paymentButton = view.findViewById(R.id.paymentButton);
        LinearLayout helpButton = view.findViewById(R.id.helpButton);
        LinearLayout logoutButton = view.findViewById(R.id.logoutButton);

        // Hide coupons if present
        LinearLayout couponsButton = view.findViewById(R.id.couponsButton);
        if (couponsButton != null) couponsButton.setVisibility(View.GONE);

        // ========== Edit Profile ==========
        if (editProfileButton != null) {
            editProfileButton.setOnClickListener(v -> showEditProfileBottomSheet());
        }

        // ========== My Orders ==========
        if (allOrdersButton != null) {
            allOrdersButton.setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), OrderHistoryActivity.class))
            );
        }

        // ========== Addresses ==========
        if (addressesButton != null) {
            addressesButton.setOnClickListener(v -> showAddressesBottomSheet());
        }

        // ========== Payment ==========
        if (paymentButton != null) {
            paymentButton.setOnClickListener(v -> showPaymentBottomSheet());
        }

        // ========== Help ==========
        if (helpButton != null) {
            helpButton.setOnClickListener(v -> showHelpBottomSheet());
        }

        // ========== Dark Mode Switch ==========
        SwitchMaterial darkModeSwitch = view.findViewById(R.id.darkModeSwitch);
        if (darkModeSwitch != null) {
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            darkModeSwitch.setChecked(nightMode == AppCompatDelegate.MODE_NIGHT_YES);

            darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                if (getActivity() != null) {
                    getActivity().recreate();
                }
            });
        }

        // ========== Log Out ==========
        if (logoutButton != null) {
            logoutButton.setOnClickListener(v -> {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Log Out")
                        .setMessage("Are you sure you want to log out?")
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Log Out", (dialog, which) -> {
                            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(requireContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        })
                        .show();
            });
        }
    }

    private void showEditProfileBottomSheet() {
        View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_edit_profile, null);
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(sheetView);
        dialog.show();

        Button btnSave = sheetView.findViewById(R.id.btnSaveProfile);
        if (btnSave != null) {
            btnSave.setOnClickListener(v -> {
                Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });
        }
    }

    private void showAddressesBottomSheet() {
        View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_addresses, null);
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(sheetView);
        dialog.show();

        Button btnAdd = sheetView.findViewById(R.id.btnAddAddress);
        if (btnAdd != null) {
            btnAdd.setOnClickListener(v -> {
                Toast.makeText(requireContext(), "Add Address coming soon", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });
        }
    }

    private void showPaymentBottomSheet() {
        View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_payment, null);
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(sheetView);
        dialog.show();

        Button btnAdd = sheetView.findViewById(R.id.btnAddPayment);
        if (btnAdd != null) {
            btnAdd.setOnClickListener(v -> {
                Toast.makeText(requireContext(), "Add Payment coming soon", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });
        }
    }

    private void showHelpBottomSheet() {
        View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_help, null);
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(sheetView);
        dialog.show();
    }
}