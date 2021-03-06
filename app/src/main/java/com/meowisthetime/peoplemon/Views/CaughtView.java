package com.meowisthetime.peoplemon.Views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.meowisthetime.peoplemon.Adapters.CaughtUserAdapter;
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
 * Created by sheamaynard on 11/10/16.
 */

public class CaughtView extends LinearLayout {

    private Context context;
    private CaughtUserAdapter caughtAdapter;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;


    public CaughtView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        caughtAdapter = new CaughtUserAdapter(new ArrayList<User>(), context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(caughtAdapter);

        listCaughtPeople();
    }

    private void listCaughtPeople(){

        RestClient restClient = new RestClient();
        restClient.getApiService().caughtUsers().enqueue(new Callback<User[]>() {

            @Override
            public void onResponse(Call<User[]> call, Response<User[]> response) {
                if (response.isSuccessful()){
//

                    caughtAdapter.users = new ArrayList<>(Arrays.asList(response.body()));

                    for (User user : caughtAdapter.users){

                        Log.d("***CAUGHT***", user.getUserName());
                        Log.d("***CAUGHT***", user.getAvatarBase64());

                        caughtAdapter.notifyDataSetChanged();
                    }

                }else{
                    Toast.makeText(context,"Get User Info Failed" + ": " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User[]> call, Throwable t) {
                Toast.makeText(context,"Get User Info Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
