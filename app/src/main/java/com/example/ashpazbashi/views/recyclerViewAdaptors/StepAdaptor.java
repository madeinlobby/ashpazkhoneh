package com.example.ashpazbashi.views.recyclerViewAdaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashpazbashi.R;
import com.example.ashpazbashi.models.recipe.Step;

import java.util.ArrayList;

public class StepAdaptor extends RecyclerView.Adapter<StepAdaptor.ViewHolder>{

    private ArrayList<Step> data;
    private LayoutInflater inflater;

    public StepAdaptor(ArrayList<Step> data, Context context) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.step_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Step step = data.get(position);
        holder.stepSubject.setText(step.getSubject());
        //load image
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stepSubject;
        ImageView stepImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepSubject = itemView.findViewById(R.id.stepSubjectText);
            stepImage = itemView.findViewById(R.id.stepImageRecycler);
        }
    }
}
