package com.example.work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>{


    List<Jobs> jobsDisplay = new ArrayList<>();
    Context mContext;

    public DetailsAdapter(Context context) {
        mContext = context;
    }
    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();

        int id = R.layout.recyclerviewitem;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(id, parent, shouldAttachToParentImmediately);

        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position)
    {
        Jobs job = jobsDisplay.get(position);

        holder.comp.setText(job.getCompanyName());
        holder.type.setText(job.getProfession());
        holder.locate.setText(job.getLocation());
        holder.when.setText(job.getDate());
    }
    @Override
    public int getItemCount()
    {
        return jobsDisplay.size();
    }

    public void setJobList(List<Jobs> jobsArrayList)
    {
        this.jobsDisplay  = jobsArrayList;
//        Without it no code shows
        notifyDataSetChanged();
    }

    class DetailsViewHolder extends RecyclerView.ViewHolder{
        TextView comp;
        TextView type;
        TextView locate;
        TextView when;
        public DetailsViewHolder(View itemView) {
            super(itemView);
            comp = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.jobtype);
            locate = itemView.findViewById(R.id.located);
            when = itemView.findViewById(R.id.time);
        }

    }

}


