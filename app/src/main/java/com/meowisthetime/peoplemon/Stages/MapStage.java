package com.meowisthetime.peoplemon.Stages;

import android.app.Application;

import com.meowisthetime.peoplemon.PeopleMonApplication;
import com.meowisthetime.peoplemon.R;
import com.meowisthetime.peoplemon.Riggers.SlideRigger;

/**
 * Created by sheamaynard on 11/8/16.
 */

public class MapStage extends IndexedStage {

    private final SlideRigger rigger;

    public MapStage(Application context) {
        super(MapStage.class.getName());
        this.rigger = new SlideRigger(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.map_view;
    }

    @Override
    public Rigger getRigger() {
        return rigger;
    }

    public MapStage() {
        this(PeopleMonApplication.getInstance());
    }

}


