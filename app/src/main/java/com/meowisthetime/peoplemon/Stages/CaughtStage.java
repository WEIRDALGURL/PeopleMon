package com.meowisthetime.peoplemon.Stages;

import android.app.Application;

import com.meowisthetime.peoplemon.PeopleMonApplication;
import com.meowisthetime.peoplemon.R;
import com.meowisthetime.peoplemon.Riggers.SlideRigger;

/**
 * Created by sheamaynard on 11/10/16.
 */

public class CaughtStage extends IndexedStage {
    private final SlideRigger rigger;

    public CaughtStage(Application context) {
        super(CaughtStage.class.getName());
        this.rigger = new SlideRigger(context);
    }

    public CaughtStage() {
        this(PeopleMonApplication.getInstance());
    }

    @Override
    public int getLayoutId() {
        return R.layout.caught_view;
    }

    @Override
    public Rigger getRigger() {
        return rigger;
    }
}
