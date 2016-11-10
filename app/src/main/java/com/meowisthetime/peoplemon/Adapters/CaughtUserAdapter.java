package com.meowisthetime.peoplemon.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meowisthetime.peoplemon.Models.User;
import com.meowisthetime.peoplemon.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sheamaynard on 11/10/16.
 */

public class CaughtUserAdapter extends RecyclerView.Adapter<CaughtUserAdapter.CaughtHolder>{

    public ArrayList<User> caughtusers;
    private Context context;

    public CaughtUserAdapter(ArrayList<User> caughtusers, Context context) {
        this.caughtusers = caughtusers;
        this.context = context;
    }




    @Override
    public CaughtUserAdapter.CaughtHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.caught_user_item, parent, false);
        return new CaughtUserAdapter.CaughtHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(CaughtUserAdapter.CaughtHolder holder, int position) {
        User user = caughtusers.get(position);
        holder.bindUser(user);
    }


    @Override
    public int getItemCount() {

        return caughtusers == null ? 0 : caughtusers.size();
    }

    class CaughtHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.username_field)
        TextView userNameField;



        public CaughtHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindUser(User user) {
            userNameField.setText("(" + user.getUserName() + ")");
        }
    }
}


