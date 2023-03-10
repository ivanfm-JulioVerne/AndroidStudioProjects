package com.example.probarmapas2023.ui.gallery;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;



import com.example.probarmapas2023.R;

import com.example.probarmapas2023.databinding.FragmentGalleryBinding;
import com.example.probarmapas2023.databinding.FragmentHomeBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GalleryFragment extends Fragment implements OnMapReadyCallback {

    private FragmentGalleryBinding binding;
    private GoogleMap map;
    private LatLng previa, siguiente, ultima;
    private double distancia, distanciaTotal;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private LocationManager locationManager;
    private String provider;
    private Polyline ruta;
    private Polyline polyline;
    private ArrayList<LatLng> posiciones = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getActivity());
        SupportMapFragment map = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);
        return binding.getRoot();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // obtenerPermisos()
        //obtenerPosicion();
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        previa = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(previa).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(previa));
        posiciones.add(previa);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng ultima) {
                map.clear(); // Limpiar marcadores previos
                posiciones.add(ultima);
                if (ultima != null) {
                    distancia = SphericalUtil.computeDistanceBetween(ultima, previa);
                    distancia /= 1000;
                    distanciaTotal += distancia;
                    double rumbo=SphericalUtil.computeHeading(previa, ultima);
                    if (rumbo<0)rumbo+=360;
                    previa = ultima;
                    MarkerOptions markerOptions = new MarkerOptions().position(ultima);
                    map.addMarker(markerOptions);

                    DecimalFormat df = new DecimalFormat("#.##");
                    String distanciaFormat = df.format(distancia);

                    Toast.makeText(getContext(), "Distancia: " + distanciaFormat+ "Rumbo: "+rumbo, Toast.LENGTH_SHORT).show();

                    PolylineOptions polylineOptions = new PolylineOptions().addAll(posiciones).color(Color.RED);
                    map.addPolyline(polylineOptions);


                }
            }
        });
    }

    private void obtenerPosicion() {
        //Inicializar el manager que nos va a dar la geoposici??n en base al GPS

        //Se usa la clase Criteria para obtener el mejor proveedor de localizaci??n
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        //false se establece para que no est?? activo permanentemente
        provider = locationManager.getBestProvider(criteria, false);

        // Se verifica si la aplicaci??n tiene los permisos para acceder
        // a la ubicaci??n del dispositivo (ACCESS_FINE_LOCATION y ACCESS_COARSE_LOCATION).
        // Si no tiene permisos, se solicita al usuario que los permita mediante
        // la funci??n requestPermissions()
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000
            );

            //map.setMyLocationEnabled(true);
            if (locationManager.isProviderEnabled(provider)) {
       //         Location location = locationManager.getLastKnownLocation(provider);
            }

        }


        //Obtenemos la primera localizaci??n que nos sirve de referencia


    }

    /*
    private void obtenerPermisos() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            return;
        }

 */
}






