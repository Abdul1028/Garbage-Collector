package com.example.garbagecollector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptorComplaint extends RecyclerView.Adapter<CustomAdaptorComplaint.MyViewHolder> {

    Context context;
    ArrayList <Complaint> userArrayList;

    public CustomAdaptorComplaint(Context context, ArrayList<Complaint> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public CustomAdaptorComplaint.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.data_layout,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Complaint comp  = userArrayList.get(position);
        holder.name.setText(comp.getName());
        holder.place.setText(comp.getPlace());
        holder.complaint.setText(comp.getComplaint());

       holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.complaint_list_animation));

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,place,complaint;
        private CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_data);
            place = itemView.findViewById(R.id.getPlace);
            name = itemView.findViewById(R.id.getName);
            complaint = itemView.findViewById(R.id.getComplaint);
        }
    }
}
