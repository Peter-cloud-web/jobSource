package com.moringa.jobsource.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringa.jobsource.Activity.Profile_Worker;
import com.moringa.jobsource.Activity.Userinformation;
import com.moringa.jobsource.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseAdapter.MyViewHolder> {
    Context context;
    ArrayList<Userinformation> users;

    public FirebaseAdapter(Context context, ArrayList<Userinformation> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_users,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.fName.setText(users.get(position).getfName());
        holder.servName.setText(users.get(position).getServName());
        holder.location.setText(users.get(position).getMlocation());
        holder.mExperience.setText(users.get(position).getmExperience());
        Picasso.get().load(users.get(position).getProfilePhoto()).into(holder.profilePhoto);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                                                   Intent intent = new Intent(context, Profile_Worker.class);
                                                   context.startActivity(intent);

                                               }
                                           });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView fName,mExperience,location,servName;
        ImageView profilePhoto;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fName = itemView.findViewById(R.id.firstname);
            mExperience = itemView.findViewById(R.id.experienced);
            location = itemView.findViewById(R.id.locale);
            servName = itemView.findViewById(R.id.serviceoffered);
            profilePhoto = itemView.findViewById(R.id.profilephoto);
            cardView = itemView.findViewById(R.id.cardview);

        }
    }
}
