package com.example.final_project;

import static com.example.final_project.Register.TAG;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.final_project.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {
    private static final int ACCESS_LOCATION_REQUEST_CODE = 1;
    SupportMapFragment mapFragment;
    SearchView searchView;
    List<MarkerOptions> markers = new ArrayList<>();
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Geocoder geocoder;
    FusedLocationProviderClient fusedLocationProviderClient;
    ArrayList<LatLng> arrayList = new ArrayList<LatLng>();
    ArrayList<String> addr = new ArrayList<String>();
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        //Adding Markers
        LatLng pune = new LatLng(18.62009698460526, 73.76645564358247);
        arrayList.add(pune);
        addr.add("Pune");


        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        searchView = findViewById(R.id.sv_location);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;
                if (location != null || location.equals("")) {
                    Geocoder geocoder1 = new Geocoder(MapsActivity.this);
                    try {
                        addressList = geocoder1.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);
        geocoder = new Geocoder(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        markers.add(new MarkerOptions().position(new LatLng(18.62009698460526, 73.76645564358247)).title("abc1"));
        markers.add(new MarkerOptions().position(new LatLng(18.632459912009388, 73.73813151892693)).title("abc2"));
        markers.add(new MarkerOptions().position(new LatLng(20.83782844250551, 74.75610859766644)).title("abc3"));

        markers.add(new MarkerOptions().position(new LatLng(19.278574821969645, 72.96400803143324)).title("xyz1"));
        markers.add(new MarkerOptions().position(new LatLng(19.19273985372536, 77.28689769907574)).title("xyz2"));
        markers.add(new MarkerOptions().position(new LatLng(17.633291821140105, 75.90964812241931)).title("xyz3"));

        markers.add(new MarkerOptions().position(new LatLng(19.012207399866025, 72.83814545216195)).title("pqr1"));
        markers.add(new MarkerOptions().position(new LatLng(16.7001006835029, 74.24281595840402)).title("pqr2"));
        markers.add(new MarkerOptions().position(new LatLng(19.22029315031916, 75.12177992312617)).title("pqr3"));

        markers.add(new MarkerOptions().position(new LatLng(19.99105872362391, 73.77848605924554)).title("lmn1"));
        markers.add(new MarkerOptions().position(new LatLng(18.632459912009388, 73.73813151892693)).title("lmn2"));
        markers.add(new MarkerOptions().position(new LatLng(21.149357840347225, 79.09574795159004)).title("lmn3"));

        markers.add(new MarkerOptions().position(new LatLng(20.707953310415352, 77.01694219261888)).title("efg1"));
        markers.add(new MarkerOptions().position(new LatLng(20.53605006914256, 76.18611693786839)).title("efg2"));
        markers.add(new MarkerOptions().position(new LatLng(20.377869210672273, 78.14034332252376)).title("efg3"));


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(int i = 0; i < arrayList.size(); i++){
            for (int j = 0; j < addr.size(); j++){
                mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(addr.get(j))));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                String markerTitle = marker.getTitle();

                Intent intent = new Intent(MapsActivity.this, Booking.class);
                intent.putExtra("name", name);
                startActivity(intent);

                return false;
            }
        });

        for (MarkerOptions marker : markers) {
            googleMap.addMarker(marker);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableuserlocation();
            zoomTuUserLocation();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
            }
        }

    }

    private void enableuserlocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    private void zoomTuUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                mMap.addMarker(new MarkerOptions().position(latLng));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ACCESS_LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableuserlocation();
                zoomTuUserLocation();
            } else {
                //Permission not granted
            }
        }
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        Log.d(TAG, "onMaplongclick" + latLng.toString());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                String streetAddress = address.getAddressLine(0);
                mMap.addMarker(new MarkerOptions().position(latLng).title(streetAddress));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}