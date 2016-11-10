package com.meowisthetime.peoplemon.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.meowisthetime.peoplemon.Adapters.CaughtUserAdapter;
import com.meowisthetime.peoplemon.Models.User;
import com.meowisthetime.peoplemon.Network.RestClient;
import com.meowisthetime.peoplemon.R;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by sheamaynard on 11/10/16.
 */

public class CaughtView extends LinearLayout {

    private Context context;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;


    private RestClient restClient;
    private CaughtUserAdapter caughtUserAdapter;
    private Map<User, ArrayList<User>> caughtusers;

    public CaughtView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}