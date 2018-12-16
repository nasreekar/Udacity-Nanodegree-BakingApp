package com.example.abhijithsreekar.bakersinn.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhijithsreekar.bakersinn.models.RecipeStep;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private List<RecipeStep> stepsList;
    private final StepClickListener itemClickListener;

    public interface StepClickListener {
        void onClickStep(RecipeStep step);
    }

    public StepsAdapter(StepClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {
        holder.stepDescription.setText(stepsList.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (stepsList == null) return 0;
        return stepsList.size();
    }

    class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(android.R.id.text1)
        TextView stepDescription;

        StepsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            itemClickListener.onClickStep(stepsList.get(position));
        }
    }

    public void setStepsList(List<RecipeStep> stepsList) {
        this.stepsList = stepsList;
    }
}


