package com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.detail;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ojtaadaassignment12.databinding.CastCrewItemBinding;
import com.example.ojtaadaassignment12.domain.model.CastAndCrew;

import java.util.List;

public class CastAndCrewAdapter extends RecyclerView.Adapter<CastAndCrewAdapter.ViewHolder> {
    private Context context;
    private List<CastAndCrew> castAndCrewList;

    public CastAndCrewAdapter(List<CastAndCrew> castAndCrewList) {
        Log.d("qz_cast_adapter", castAndCrewList.toString());
        this.context = context;
        this.castAndCrewList = castAndCrewList;
    }

    @NonNull
    @Override
    public CastAndCrewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CastCrewItemBinding binding = CastCrewItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CastAndCrewAdapter.ViewHolder holder, int position) {
        holder.bind(castAndCrewList.get(position));
    }

    @Override
    public int getItemCount() {
        return castAndCrewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CastCrewItemBinding binding;

        public ViewHolder(@NonNull CastCrewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(CastAndCrew castAndCrew) {
            binding.setCastAndCrew(castAndCrew);
        }
    }
}
