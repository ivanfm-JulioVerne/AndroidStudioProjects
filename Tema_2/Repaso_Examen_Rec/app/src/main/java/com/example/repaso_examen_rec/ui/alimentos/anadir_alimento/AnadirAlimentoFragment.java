package com.example.repaso_examen_rec.ui.alimentos.anadir_alimento;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.repaso_examen_rec.databinding.FragmentAnadiralimentoBinding;
import com.example.repaso_examen_rec.modelo.Alimento;

public class AnadirAlimentoFragment extends Fragment {

    private FragmentAnadiralimentoBinding binding;
    private AnadirAlimento anadirAlimento;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentAnadiralimentoBinding.inflate(inflater,container,false);
        View root=binding.getRoot();

        Button anadir=binding.anadirAlimento;
        EditText nombreEditText=binding.editTextNombre;
        EditText kCalEditText=binding.editTextKcal;

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=nombreEditText.getText().toString();
                Float kCal=new Float(kCalEditText.getText().toString());
                Alimento nuevoAlimento=new Alimento(nombre,kCal);
                anadirAlimento.anadirAliemnto(nuevoAlimento);

                nombreEditText.setText("");
                kCalEditText.setText("");
            }
        });


        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            anadirAlimento = (AnadirAlimento) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Se debe implementar anadirAlimento");
        }
    }

}