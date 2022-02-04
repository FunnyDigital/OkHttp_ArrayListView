package com.patrick.bitvilltenologies.nbacard;


import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;




import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    // creating a variable for our array list and context.
    private ArrayList<RecyclerData> courseDataArrayList;
    private Context mcontext;

    // creating a constructor class.
    public RecyclerViewAdapter(ArrayList<RecyclerData> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customcard, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview from our modal class.
        RecyclerData modal = courseDataArrayList.get(position);
       // holder.id.setText(modal.getId());
        holder.abbreviation.setText(modal.getAbbreviation());
        holder.city.setText(modal.getCity());
        holder.conference.setText(modal.getConference());
        holder.division.setText(modal.getDivision());
        holder.fullname.setText(modal.getFull_name());
        holder.name.setText(modal.getName());
        //Picasso.get().load(modal.getCourseimg()).into(holder.courseIV);
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView abbreviation, city, conference, division, fullname, name;
       // private int id;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
           // id = itemView.findViewById(R.id.idid);
           // abbreviation = itemView.findViewById(R.id.idabbreviation);
           // city = itemView.findViewById(R.id.idcity);
           // conference = itemView.findViewById(R.id.idconference);
           // division = itemView.findViewById(R.id.iddivision);
           // fullname = itemView.findViewById(R.id.idcfullname);
           // name = itemView.findViewById(R.id.idname);
        }
    }
}