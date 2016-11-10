package com.meowisthetime.peoplemon.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meowisthetime.peoplemon.Models.User;
import com.meowisthetime.peoplemon.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sheamaynard on 11/9/16.
 */

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.NearbyHolder>{

    public ArrayList<User> users;
    private Context context;

    public NearbyAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }




    @Override
    public NearbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.nearby_user_item, parent, false);
        return new NearbyHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(NearbyHolder holder, int position) {
        User user = users.get(position);
        holder.bindUser(user);
    }


    @Override
    public int getItemCount() {

        return users == null ? 0 : users.size();
    }

    class NearbyHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.username_field)
        TextView userNameField;

        @Bind(R.id.user_imageView)
        ImageView userImageView;


        public NearbyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindUser(User user) {
            userNameField.setText("(" + user.getUserName() + ")");
        }
    }
}
