package com.example.e_commerce_app.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.e_commerce_app.R;

public class FavoritesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View locationContainer = view.findViewById(R.id.locationContainer);
        TextView toolbarTitle = view.findViewById(R.id.toolbarTitle);
        View cartContainer = view.findViewById(R.id.cartContainer);

        // Hide Location & Cart, Show only Title
        if (locationContainer != null) locationContainer.setVisibility(View.GONE);
        if (cartContainer != null) cartContainer.setVisibility(View.GONE);

        if (toolbarTitle != null) {
            toolbarTitle.setVisibility(View.VISIBLE);
            toolbarTitle.setText("Favorites");
        }
    }
}