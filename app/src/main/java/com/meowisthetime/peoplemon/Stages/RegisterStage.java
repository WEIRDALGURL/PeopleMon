package com.meowisthetime.peoplemon.Stages;

import android.app.Application;

import com.meowisthetime.peoplemon.R;
import com.meowisthetime.peoplemon.PeopleMonApplication;
import com.meowisthetime.peoplemon.Riggers.SlideRigger;

/**
 * Created by sheamaynard on 10/31/16.
 */

public class RegisterStage extends IndexedStage {
    private final SlideRigger rigger;

    public RegisterStage(Application context) {
        super(RegisterStage.class.getName());
        this.rigger = new SlideRigger(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.register_view;
    }

    @Override
    public Rigger getRigger() {
        return rigger;
    }

    public RegisterStage() {
        this(PeopleMonApplication.getInstance());
    }

}
