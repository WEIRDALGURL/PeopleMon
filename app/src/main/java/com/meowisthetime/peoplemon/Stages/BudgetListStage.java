package com.meowisthetime.peoplemon.Stages;

import android.app.Application;

import com.meowisthetime.peoplemon.R;
import com.meowisthetime.peoplemon.PeopleMonApplication;
import com.meowisthetime.peoplemon.Riggers.SlideRigger;

/**
 * Created by sheamaynard on 10/31/16.
 */

public class BudgetListStage extends IndexedStage {
    private final SlideRigger rigger;

    public BudgetListStage(Application context) {
        super(BudgetListStage.class.getName());
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

    public BudgetListStage() {
        this(PeopleMonApplication.getInstance());
    }

}
