package com.meowisthetime.peoplemon.Views;

import android.Manifest;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.meowisthetime.peoplemon.Adapters.NearbyAdapter;
import com.meowisthetime.peoplemon.MainActivity;
import com.meowisthetime.peoplemon.Models.User;
import com.meowisthetime.peoplemon.Network.RestClient;
import com.meowisthetime.peoplemon.PeopleMonApplication;
import com.meowisthetime.peoplemon.R;
import com.meowisthetime.peoplemon.Stages.CaughtStage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import flow.History;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.meowisthetime.peoplemon.R.id.map;

/**
 * Created by sheamaynard on 11/8/16.
 */

public class MapsView extends RelativeLayout implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap mMap;
    private Context context;
    private Handler checkNear;
    private NearbyAdapter nearbyAdapter;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private final String TAG = "************";
    private Location lastLocation;
    private LocationListener locationListener;
    private Double lat = 37.816380;
    private Double lng = -82.809195;
    private String userId;
    private String username;
//    private Double lat;
//    private Double lng;
    private Marker otherUser;

    LatLng Home = new LatLng(lat, lng);


    @Bind(map)
    MapView mapView;

    @Bind(R.id.radar_button)
    FloatingActionButton radarButton;

    @Bind(R.id.chat_button)
    FloatingActionButton chatButton;

    @Bind(R.id.caught_button)
    FloatingActionButton caughtButton;


    public MapsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        mapView.getMapAsync(this);

        mapView.onCreate(((MainActivity) getContext()).savedInstanceState);
        mapView.onResume();


//        mGoogleApiClient = new GoogleApiClient.Builder(context)
//                .addApi(LocationServices.API)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .build();
//
//        mGoogleApiClient.connect();
//
//        mLocationRequest = LocationRequest.create();
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        mLocationRequest.setInterval(20 * 100);

    }

    private void getMapAsync(MapsView mapsView) {

    }

    @Override
    protected void onDetachedFromWindow() {
//        mGoogleApiClient.disconnect();
        super.onDetachedFromWindow();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);

        setCheckIn();

        checkNear = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                checkNear.postDelayed(this, 3000);
                viewNearby();
            }
        };
        checkNear.postDelayed(r, 3000);

//        Toast.makeText(context, "Map loaded", Toast.LENGTH_SHORT).show();



        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        mMap.setMyLocationEnabled(true);

//        LatLng sydney = new LatLng(-33.867, 151.206);
//        LatLng paintsville = new LatLng(37.8145, -82.8071);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Home, 13));




//        GroundOverlayOptions radar = new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.mipmap.radar).position(Home, 500f, 500f));
//
//        GroundOverlay imageOverlay = mMap.addGroundOverlay(radar);

        }

    private void setCheckIn() {
        User checkIn = new User(lat, lng);
        RestClient restClient = new RestClient();
        restClient.getApiService().checkin(checkIn).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Check-in Successful", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context, "Check-in Failed :" + response.code(), Toast.LENGTH_LONG).show();


                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Check-in Failed", Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {


//        try {
//            lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        }
//        if (lastLocation != null) {
//            lastLat = lastLocation.getLatitude();
//            lastLong = lastLocation.getLongitude();
//            Log.d("------------------>", lastLat + "  " + lastLong);
//        } else if (lastLocation == null) {
//            try {
//                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, locationListener);
//            } catch (SecurityException e) {
//                e.printStackTrace();
//            }
//        } else {
//            handleNewLocation(lastLocation);
//        }
//        Log.d(">>>>>>>>>>>>>>>>>>>>", "CONNECTED");

    }

    @Override
    public void onConnectionSuspended(int i) {
//        Log.i(TAG, "GoogleApiClient connection has been suspend");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        Log.i(TAG, "GoogleApiClient connection has failed");

    }


    @Override
    public boolean onMyLocationButtonClick() {
//        LatLng current = new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude());
//        mMap.addMarker(new MarkerOptions().position(current).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        return true;
    }

//    private void handleNewLocation(Location location) {
//        Log.d(TAG, location.toString());
//    }


    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            mMap.clear();
            //  mLastLocation = location;
            lat = location.getLatitude();
            lng = location.getLongitude();
            Home = new LatLng(lat, lng);
            String pos = Home + "";
            Log.d("**", pos);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Home, 16));
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .title("My location")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                    .snippet("I am here")
                    .draggable(true)
                    .position(Home));

            final Circle circle = mMap.addCircle(new CircleOptions().center(Home)
                    .strokeColor(Color.DKGRAY).radius(100));

            ValueAnimator vAnimator = new ValueAnimator();
            vAnimator.setRepeatCount(ValueAnimator.INFINITE);
            vAnimator.setRepeatMode(ValueAnimator.RESTART);  /* PULSE */
            vAnimator.setIntValues(0, 100);
            vAnimator.setDuration(1000);
            vAnimator.setEvaluator(new IntEvaluator());
            vAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            vAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float animatedFraction = valueAnimator.getAnimatedFraction();
                    // Log.e("", "" + animatedFraction);
                    circle.setRadius(animatedFraction * 100);
                }
            });
            vAnimator.start();


        }
    };


    @OnClick(R.id.caught_button)
    public void viewCaught() {
        Flow flow = PeopleMonApplication.getMainFlow();
        History newHistory = flow.getHistory().buildUpon()
                .push(new CaughtStage())
                .build();
        flow.setHistory(newHistory, Flow.Direction.FORWARD);
    }

    public void viewNearby() {
        RestClient restClient = new RestClient();
        restClient.getApiService().findNearby(500).enqueue(new Callback<User[]>() {
            @Override
            public void onResponse(Call<User[]> call, Response<User[]> response) {
                if (response.isSuccessful()) {
                    for (User user : response.body()) {
                        lat = user.getLatitude();
                        lng = user.getLongitude();
                        userId = user.getUserid();
                        username = user.getUserName();
                        Log.d(TAG, username + userId);
                        LatLng userpos = new LatLng(lat, lng);
                        otherUser = mMap.addMarker(new MarkerOptions()
                                .title(username)
//                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.person))
                                .snippet(userId)
                                .position(userpos));



//                        nearbyAdapter.users = new ArrayList<User>(Arrays.asList(response.body()));
//                        nearbyAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(context, "view Nearby Failed: " + response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User[]> call, Throwable t) {
                Toast.makeText(context, "view Nearby Failed", Toast.LENGTH_SHORT).show();

            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                username = marker.getTitle();
                userId = marker.getSnippet();

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setTitle("Do you want to catch " + username);
                alertBuilder.setMessage("You sure?");
                alertBuilder.setNegativeButton("No", null);
                alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        catchUser();
                        Toast.makeText(context, "Caught: " + username, Toast.LENGTH_SHORT).show();
                    }
                });
                alertBuilder.create().show();

                return false;
            }
        });


    }

    public void catchUser() {
        User catchUser = new User(userId, 100);
        RestClient restClient = new RestClient();
        restClient.getApiService().catchUser(catchUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, username + userId, Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}


