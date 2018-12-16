package com.example.abhijithsreekar.bakersinn.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhijithsreekar.bakersinn.R;
import com.example.abhijithsreekar.bakersinn.adapters.StepsAdapter;
import com.example.abhijithsreekar.bakersinn.models.RecipeStep;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsFragment extends Fragment implements StepsAdapter.StepClickListener {

    private List<RecipeStep> stepsList;

    @BindView(R.id.lv_steps)
    RecyclerView stepList;

    private OnStepItemClickListener clickListener;

    @Override
    public void onClickStep(RecipeStep step) {
        clickListener.onStepItemClicked(step);
    }

    public interface OnStepItemClickListener {
        void onStepItemClicked(RecipeStep stepsModel);
    }

    public StepsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            clickListener = (OnStepItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepItemClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);
        ButterKnife.bind(this, rootView);
        StepsAdapter stepListAdapter = new StepsAdapter(this);
        stepListAdapter.setStepsList(stepsList);
        stepList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        stepList.setAdapter(stepListAdapter);
        stepList.setNestedScrollingEnabled(false);
        return rootView;
    }

    public void setStepsList(List<RecipeStep> stepsList) {
        this.stepsList = stepsList;
    }
}
