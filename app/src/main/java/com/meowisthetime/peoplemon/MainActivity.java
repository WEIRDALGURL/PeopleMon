package com.meowisthetime.peoplemon;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.davidstemmer.flow.plugin.screenplay.ScreenplayDispatcher;
import com.meowisthetime.peoplemon.Network.UserStore;
import com.meowisthetime.peoplemon.Stages.BudgetListStage;
import com.meowisthetime.peoplemon.Stages.LoginStage;

import butterknife.Bind;
import butterknife.ButterKnife;
import flow.Flow;
import flow.History;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private Flow flow;
    private ScreenplayDispatcher dispatcher;

    @Bind(R.id.container)
    RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MUST HAVE, or container would be null
        ButterKnife.bind(this);

        flow = PeopleMonApplication.getMainFlow();
        dispatcher = new ScreenplayDispatcher(this, container);
        dispatcher.setUp(flow);


        flow = PeopleMonApplication.getMainFlow();
        dispatcher = new ScreenplayDispatcher(this, container);
        dispatcher.setUp(flow);


        UserStore.getInstance().setToken(null);

        if (UserStore.getInstance().getToken() == null ||
                UserStore.getInstance().getTokenExpiration() == null) {
            History newHistory = History.single(new LoginStage());
            flow.setHistory(newHistory, Flow.Direction.REPLACE);
        }


    }

    @Override
    public void onBackPressed() {
        if (!flow.goBack()) {
            flow.removeDispatcher(dispatcher);
            flow.setHistory(History.single(new BudgetListStage()), Flow.Direction.BACKWARD);
            super.onBackPressed();
        }
    }


}
