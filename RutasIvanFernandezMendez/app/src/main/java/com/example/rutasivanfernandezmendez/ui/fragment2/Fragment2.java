package com.example.rutasivanfernandezmendez.ui.fragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rutasivanfernandezmendez.MainActivity;
import com.example.rutasivanfernandezmendez.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {

    private Fragment2Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Fragment2ViewModel fragment2ViewModel =
                new ViewModelProvider(this).get(Fragment2ViewModel.class);

        binding = Fragment2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.textViewDistTotal;
        textView.setText(((MainActivity)getActivity()).getDistTotal()+" Kms");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}