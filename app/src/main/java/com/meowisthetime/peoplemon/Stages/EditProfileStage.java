package com.meowisthetime.peoplemon.Stages;

import android.app.Application;

import com.davidstemmer.screenplay.stage.Stage;
import com.meowisthetime.peoplemon.PeopleMonApplication;
import com.meowisthetime.peoplemon.R;
import com.meowisthetime.peoplemon.Riggers.VerticalSlideRigger;

/**
 * Created by sheamaynard on 11/8/16.
 */

public class EditProfileStage extends IndexedStage {

    private final VerticalSlideRigger rigger;

    public EditProfileStage(Application context) {
        super(EditProfileStage.class.getName());
        this.rigger = new VerticalSlideRigger(context);
    }

    public EditProfileStage() {
        this(PeopleMonApplication.getInstance());
    }

    @Override
    public int getLayoutId() {
        return R.layout.edit_profile_view;
    }

    @Override
    public Stage.Rigger getRigger() {
        return rigger;
    }
}
