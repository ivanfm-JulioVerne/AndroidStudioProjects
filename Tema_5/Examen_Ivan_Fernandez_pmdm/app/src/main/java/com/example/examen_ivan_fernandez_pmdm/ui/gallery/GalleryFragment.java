package com.example.examen_ivan_fernandez_pmdm.ui.gallery;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.examen_ivan_fernandez_pmdm.R;
import com.example.examen_ivan_fernandez_pmdm.databinding.FragmentGalleryBinding;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
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


public class GalleryFragment extends Fragment implements OnMapReadyCallback{

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
        SupportMapFragment map= (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);
        View root = binding.getRoot();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        // obtenerPermisos()
        //obtenerPosicion();
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //previa = new LatLng(-34, 151);
        obtenerPosicion();
        map.addMarker(new MarkerOptions().position(previa).title("Tu Ubicacion"));
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
    //@SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(provider);
    private void obtenerPosicion() {
        //Inicializar el manager que nos va a dar la geoposición en base al GPS

        //Se usa la clase Criteria para obtener el mejor proveedor de localización
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        //false se establece para que no esté activo permanentemente
        provider = locationManager.getBestProvider(criteria, false);

        // Se verifica si la aplicación tiene los permisos para acceder
        // a la ubicación del dispositivo (ACCESS_FINE_LOCATION y ACCESS_COARSE_LOCATION).
        // Si no tiene permisos, se solicita al usuario que los permita mediante
        // la función requestPermissions()
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000
            );

            //map.setMyLocationEnabled(true);
            if (locationManager.isProviderEnabled(provider)) {
                    @SuppressLint("MissingPermission")
                    Location location = locationManager.getLastKnownLocation(provider);
                    previa=new LatLng(location.getLatitude(),location.getLongitude());
            }else{
                //Log.d(":::Provider","");
                previa = new LatLng(-34, 151);
            }

        }else{
            if (locationManager.isProviderEnabled(provider)) {
                @SuppressLint("MissingPermission")
                Location location = locationManager.getLastKnownLocation(provider);
                previa=new LatLng(location.getLatitude(),location.getLongitude());
            }else{
                //Log.d(":::Provider","");
                previa = new LatLng(-34, 151);
            }
        }


        //Obtenemos la primera localización que nos sirve de referencia


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