package com.meowisthetime.peoplemon.Stages;

import android.app.Application;

import com.meowisthetime.peoplemon.PeopleMonApplication;
import com.meowisthetime.peoplemon.Riggers.SlideRigger;

/**
 * Created by sheamaynard on 11/10/16.
 */

public class CaughtStage extends IndexedStage {
    private final SlideRigger rigger;

    public CaughtStage(Application context) {
        super(BudgetListStage.class.getName());
        this.rigger = new SlideRigger(context);
    }

    public CaughtStage() {
        this(PeopleMonApplication.getInstance());
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public Rigger getRigger() {
        return null;
    }
}
