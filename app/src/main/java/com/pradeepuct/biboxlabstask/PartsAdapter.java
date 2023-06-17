package com.pradeepuct.biboxlabstask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PartsAdapter extends RecyclerView.Adapter<PartsAdapter.PartsViewHolder> {

    private List<Parts> parts;
    private PartShowListener partShowListener;

    public PartsAdapter(List<Parts> parts, PartShowListener partShowListener) {
        this.parts = parts;
        this.partShowListener = partShowListener;
    }

    @NonNull
    @Override
    public PartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PartsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container, parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PartsViewHolder holder, int position) {
        holder.bindParts(parts.get(position));
    }

    @Override
    public int getItemCount() {
        return parts.size();
    }

    public List<Parts> getSelectedParts()
    {
        List<Parts> selectedParts = new ArrayList<>();
        for (Parts parts: parts)
        {
            if (parts.isSelected)
            {
                selectedParts.add(parts);
            }
        }
        return selectedParts;
    }

    class PartsViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageParts, partsSelected;
        TextView textParts;

        ConstraintLayout layoutParts;
        View viewBackground;

         PartsViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutParts = itemView.findViewById(R.id.layoutParts);
            imageParts = itemView.findViewById(R.id.imageParts);
            partsSelected = itemView.findViewById(R.id.partSelected);
            textParts = itemView.findViewById(R.id.textParts);
            viewBackground = itemView.findViewById(R.id.viewBackground);
        }

        void bindParts(final Parts parts)
        {
            imageParts.setImageResource(parts.image);
            textParts.setText(parts.partName);
            if (parts.isSelected)
            {
                viewBackground.setBackgroundResource(R.drawable.parts_selected_background);
                partsSelected.setVisibility(View.VISIBLE);
            }
            else
            {
                viewBackground.setBackgroundResource(R.drawable.part_show_background);
                partsSelected.setVisibility(View.GONE);
            }
            layoutParts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (parts.isSelected)
                    {
                        viewBackground.setBackgroundResource(R.drawable.part_show_background);
                        partsSelected.setVisibility(View.GONE);
                        parts.isSelected = false;
                        if (getSelectedParts().size() == 0)
                        {
                            partShowListener.onPartShowAction(false);
                        }
                    }
                    else
                    {
                        viewBackground.setBackgroundResource(R.drawable.parts_selected_background);
                        partsSelected.setVisibility(View.VISIBLE);
                        parts.isSelected= true;
                        partShowListener.onPartShowAction(true);
                    }
                }
            });
        }
    }
}
