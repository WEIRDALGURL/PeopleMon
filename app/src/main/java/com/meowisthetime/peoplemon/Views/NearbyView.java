package com.meowisthetime.peoplemon.Views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.Toast;

import com.meowisthetime.peoplemon.Adapters.NearbyAdapter;
import com.meowisthetime.peoplemon.Models.User;
import com.meowisthetime.peoplemon.Network.RestClient;
import com.meowisthetime.peoplemon.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sheamaynard on 11/11/16.
 */

public class NearbyView extends GridLayout {

    private Context context;
    private NearbyAdapter nearbyAdapter;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;


    public NearbyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        nearbyAdapter = new NearbyAdapter(new ArrayList<User>(), context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(nearbyAdapter);

        listNearbyPeople();
    }

    private void listNearbyPeople() {

        RestClient restClient = new RestClient();
        restClient.getApiService().findNearby(500).enqueue(new Callback<User[]>() {
            @Override
            public void onResponse(Call<User[]> call, Response<User[]> response) {
                if (response.isSuccessful()) {
                    nearbyAdapter.users = new ArrayList<>(Arrays.asList(response.body()));
                    for (User user : nearbyAdapter.users) {
//                        Location locat = new Location("");
//                        locat.setLatitude(user.getLatitude());
//                        locat.setLongitude(user.getLongitude());

                        nearbyAdapter.notifyDataSetChanged();
                    }

                } else {
                    Toast.makeText(context, "Get User Info Failed" + ": " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User[]> call, Throwable t) {
                    Toast.makeText(context, "Get User Info Failed", Toast.LENGTH_LONG).show();

            }
        });

    }
}


