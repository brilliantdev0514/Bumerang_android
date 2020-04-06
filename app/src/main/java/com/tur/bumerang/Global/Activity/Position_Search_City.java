package com.tur.bumerang.Global.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.R;

import java.io.IOException;
import java.util.List;

public class Position_Search_City extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public EditText locationSearch;
    public LatLng mLatLng = new LatLng(ReqConst.defaultLat, ReqConst.defaultlng);
    public int iType = 0;
    public String findCity = null;
    public Geocoder geocoder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        iType = getIntent().getIntExtra("type", 0);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geocoder = new Geocoder(this);

        locationSearch = (EditText) findViewById(R.id.editText);
        ((ImageView)findViewById(R.id.btn_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((Button)findViewById(R.id.btn_searck)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (iType == 0) {
                    Intent intent = new Intent(Position_Search_City.this, HomeActivity.class);
                    intent.putExtra("findCity", findCity);
                    startActivity(intent);
                }else{
                    Common.lat = mLatLng.latitude;
                    Common.lng = mLatLng.longitude;
                    Common.product.lat = String.valueOf(mLatLng.latitude);
                    Common.product.lng = String.valueOf(mLatLng.longitude);
                }
                finish();
            }
        });
        locationSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    showProgress();
                    onMapSearch();
                }
                return false;
            }
        });
    }

    public void onMapSearch() {
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location == null || location.equals("")) {
            closeProgress();
            return;
        }
        try {
            addressList = geocoder.getFromLocationName(location, 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        closeProgress();
        if(addressList == null || addressList.size() <= 0) {
            showAlertDialog("Can't find the city");
            return;
        }
        setSearchText(addressList.get(0));
        mLatLng = new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(mLatLng).title("Marker"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 5.0f));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 5.0f));
        mMap.addMarker(new MarkerOptions().position(mLatLng).title("Marker"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mLatLng = latLng;
                Geocoder geocoder = new Geocoder(Position_Search_City.this);
                List<Address> addresses = null;
                try {
                    while (addresses==null){
                        addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    }

                    if (addresses != null && addresses.size() > 0)
                        setSearchText(addresses.get(0));
                    else
                        locationSearch.setText(String.valueOf(latLng.latitude)+", "+String.valueOf(latLng.longitude));

                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setSearchText(Address address){
        String strCountry = address.getCountryName();
        String strLocal = address.getLocality();
        if (strLocal == null)
            strLocal = address.getAdminArea();

        findCity = strLocal;
        if (strLocal == null)
            findCity = strCountry;
        locationSearch.setText((strCountry == null ? "" : strCountry)+(strLocal == null ? "" : ", "+strLocal));
    }
}