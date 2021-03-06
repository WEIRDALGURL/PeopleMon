package com.meowisthetime.peoplemon.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.meowisthetime.peoplemon.Components.Constants;
import com.meowisthetime.peoplemon.Components.Utils;
import com.meowisthetime.peoplemon.MainActivity;
import com.meowisthetime.peoplemon.Models.Account;
import com.meowisthetime.peoplemon.Network.RestClient;
import com.meowisthetime.peoplemon.PeopleMonApplication;
import com.meowisthetime.peoplemon.R;
import com.meowisthetime.peoplemon.Stages.MapStage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.meowisthetime.peoplemon.Components.Constants.IMAGE;

/**
 * Created by sheamaynard on 11/9/16.
 */

public class EditProfileView  extends LinearLayout{
    private Context context;
    Flow flow = PeopleMonApplication.getMainFlow();


    @Bind(R.id.edit_name)
    EditText editName;

    @Bind(R.id.imageView)
    ImageView imageView;

    @Bind(R.id.add_image_button)
    Button addImageButton;

    @Bind(R.id.save_button)
    Button saveButton;



    public EditProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;




    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        viewProfile();

//        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDetachedFromWindow() {
//        EventBus.getDefault().unregister(this);
        super.onDetachedFromWindow();
    }

    @OnClick(R.id.add_image_button)
    public void pictureTapped() {
        ((MainActivity) context).getImage();

    }

    @OnClick(R.id.save_button)
    public void saveTapped() {
        History newHistory = flow.getHistory().buildUpon()
                .push(new MapStage())
                .build();
        flow.setHistory(newHistory, Flow.Direction.FORWARD);
        String userName = editName.getText().toString();
        String avatar = IMAGE;
        Account updateInfo = new Account(userName, avatar);
        RestClient restClient = new RestClient();
        restClient.getApiService().updateInfo(updateInfo).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Information Updated", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context, "No Updated", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show();


            }
        });


    }
    public void viewProfile() {
        RestClient restClient = new RestClient();
        restClient.getApiService().getUserInfo().enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    Account authUser = response.body();
                    editName.setText(authUser.getFullName());
                    imageView.setImageBitmap(Utils.decodeImage(authUser.getAvatarBase64()));
                    Constants.myAvatar = (Utils.decodeImage(authUser.getAvatarBase64()));


                } else {
                    Toast.makeText(context, "getinfofailed: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(context, "getinfofailed", Toast.LENGTH_SHORT).show();

            }

        });
    }

}

