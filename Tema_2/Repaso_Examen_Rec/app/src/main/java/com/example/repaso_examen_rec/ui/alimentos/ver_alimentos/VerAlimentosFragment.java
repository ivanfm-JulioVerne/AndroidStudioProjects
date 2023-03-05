package com.example.repaso_examen_rec.ui.alimentos.ver_alimentos;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.repaso_examen_rec.MainActivity;
import com.example.repaso_examen_rec.R;
import com.example.repaso_examen_rec.databinding.FragmentVerAlimentosBinding;
import com.example.repaso_examen_rec.ui.alimentos.anadir_alimento.AnadirAlimento;

public class VerAlimentosFragment extends Fragment {

    private FragmentVerAlimentosBinding binding;

    private BorrarAlimento borrarAlimento;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentVerAlimentosBinding.inflate(inflater,container,false);
        View root=binding.getRoot();

        RecyclerView recyclerView=(RecyclerView) root.findViewById(R.id.reciclerViewAlimento);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(new VerAlimentosAdapter(((MainActivity)this.getActivity()).getAlimentos()));

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(root.getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d(":::Borra",position+"");
                        AlertDialog.Builder builderAlert= new AlertDialog.Builder(root.getContext());
                        builderAlert
                                .setMessage("¿Seguro que quieres eliminar "
                                    + ((TextView)view.findViewById(R.id.nombreAlimento)).getText() +"?")
                                .setTitle("Borrar").setPositiveButton("Sí",(dialog, id) ->{
                                    borrarAlimento.borrarAlimento(position);
                                    recyclerView.setAdapter(new VerAlimentosAdapter(((MainActivity)view.getContext()).getAlimentos()));
                                    dialog.cancel();
                                })
                                .setNegativeButton("No",(dialog,id)->{
                                    dialog.cancel();
                                });
                        AlertDialog alert=builderAlert.create();
                        alert.show();

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));

        // Inflate the layout for this fragment
        return root;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            borrarAlimento = (BorrarAlimento) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Se debe implementar borrarAlimento");
        }

    }
}