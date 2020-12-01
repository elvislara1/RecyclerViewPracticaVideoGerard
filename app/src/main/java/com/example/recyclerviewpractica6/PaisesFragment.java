package com.example.recyclerviewpractica6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.recyclerviewpractica6.databinding.FragmentPaisesBinding;
import com.example.recyclerviewpractica6.databinding.ViewholderPaisBinding;

import java.util.List;

public class PaisesFragment extends Fragment {

    FragmentPaisesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return (binding = FragmentPaisesBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //obtenemos la instacia del viewModel
        PaisesViewModel paisesViewModel = new ViewModelProvider(this).get(PaisesViewModel.class);
        //llamamos al metodo get... para obtener la lista de paises
        PaisAdapter paisAdapter = new PaisAdapter();
        //utiliza este adaptador para rellenarte
        binding.recyclerView.setAdapter(paisAdapter);

        paisesViewModel.paises().observe(getViewLifecycleOwner(), new Observer<List<Pais>>() {
            @Override
            public void onChanged(List<Pais> pais) {
                paisAdapter.setPaisList(pais);
            }
        });
    }

    class  PaisAdapter extends RecyclerView.Adapter<PaisViewHolder>{
        //este list es igual que el que tiene contenido
        List<Pais> paisList;

        //estos tres metodos que nos pide que creemos, son los que llamara el recyclerView para rellenar
        @NonNull
        @Override
        //aquí lo llamara cuando necesite crear un viewHolder (PERO VACIOS), retornamos un objeto de clase viewHolder.
        public PaisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PaisViewHolder(ViewholderPaisBinding.inflate(getLayoutInflater(), parent, false));
        }
        //aqui es donde se rellenan
        @Override
        public void onBindViewHolder(@NonNull PaisViewHolder holder, int position) {
            Pais pais = paisList.get(position);

            holder.binding.pais.setText(pais.nombre);
            holder.binding.anyo.setText(pais.anyo);

            //cargar imagen y donde la queremos
            Glide.with(PaisesFragment.this).load(pais.camiseta).into(holder.binding.camiseta);
        }

        //Aqui es cuantos holder tiene que crear para ver toda la lista
        @Override
        public int getItemCount() {
            //habra tantos como elementos de array
            return paisList == null ? 0 : paisList.size();
        }

        //llamamos a este metodo y le pasaremos la lista.
        void setPaisList(List<Pais> paisList){
            this.paisList = paisList;
            //cada vez que cambie el arraylist notifica al recyclerview que ha cambiado, para que vuelva rellenar
            notifyDataSetChanged();
        }
    }

    // nos permitirá acceder a estos text's view imagenview ...
    class PaisViewHolder extends RecyclerView.ViewHolder{

        ViewholderPaisBinding binding;

        public PaisViewHolder(@NonNull ViewholderPaisBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
    //ahora nos falta el adaptador el encargado de crear los viewholder necesarios.
}