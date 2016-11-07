package com.meowisthetime.peoplemon;

import android.app.Application;

import com.meowisthetime.peoplemon.Stages.BudgetListStage;

import flow.Flow;
import flow.History;

/**
 * Created by sheamaynard on 10/31/16.
 */

public class PeopleMonApplication extends Application {
    private static PeopleMonApplication application;
    public final Flow mainFlow = new Flow(History.single(new BudgetListStage()));

    public static final String API_BASE_URL = "https://efa-peoplemon-api.azurewebsites.net/";

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

    }

    public static PeopleMonApplication getInstance() {
        return application;
    }

    public  static  Flow getMainFlow() {
        return getInstance().mainFlow;
    }
}
