package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import static com.example.myapplication.MainActivity.photographer;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TextView face=root.findViewById(R.id.id_facebook);
        TextView insta=root.findViewById(R.id.id_insta);
        TextView behance=root.findViewById(R.id.id_behance);
        TextView whats=root.findViewById(R.id.id_whats);

        face.setText(photographer.getFacebook());
        insta.setText(photographer.getInstagram());
        behance.setText(photographer.getBehance());
        whats.setText(photographer.getWhatsApp());
        return root;
    }
}