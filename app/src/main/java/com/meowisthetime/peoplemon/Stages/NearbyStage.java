package com.meowisthetime.peoplemon.Stages;

import android.app.Application;

import com.meowisthetime.peoplemon.PeopleMonApplication;
import com.meowisthetime.peoplemon.R;
import com.meowisthetime.peoplemon.Riggers.SlideRigger;

/**
 * Created by sheamaynard on 11/11/16.
 */

public class NearbyStage extends IndexedStage {
    private final SlideRigger rigger;

    public NearbyStage(Application context) {
        super(NearbyStage.class.getName());
        this.rigger = new SlideRigger(context);
    }

    public NearbyStage() {
        this(PeopleMonApplication.getInstance());
    }

    @Override
    public int getLayoutId() {
        return R.layout.nearby_view;
    }

    @Override
    public Rigger getRigger() {
        return rigger;
    }
}

