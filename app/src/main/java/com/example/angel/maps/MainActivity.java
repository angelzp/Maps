package com.example.angel.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends FragmentActivity implements GoogleMap.OnMapClickListener {

    private final LatLng casa = new LatLng(18.36125731, -100.67983984);
    private GoogleMap mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relacionar el mapa con el fragment
        mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        //Indicar tipo de mapa para mostrar al inicio
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(casa, 15));

        //Mostrar/Ocultar mi ubicacion
        mapa.setMyLocationEnabled(true);

        //Mostrar/Ocultar controles de zoom
        mapa.getUiSettings().setZoomControlsEnabled(true);

        //Mostrar/Ocultar icono de compas
        mapa.getUiSettings().setCompassEnabled(true);

        //Indicar un marcador en nuestro mapa
        mapa.addMarker(new MarkerOptions()
                .position(casa)
                .title("Mi Casa")
                .snippet("Calle Beatriz NO.45 Col. Invisur")
                .icon(BitmapDescriptorFactory
                        .fromResource(android.R.drawable.ic_menu_compass))
                .anchor(0.5f, 0.5f));
        mapa.setOnMapClickListener(this);
    }

    //Metodo que permite mover la camara
    public void moveCamera(View view){
        mapa.moveCamera(CameraUpdateFactory.newLatLng(casa));
    }

    //Metodo que permite realizar animaciones en la camara
    public void animateCamera(View view){
        if (mapa.getMyLocation() !=null)
            mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    mapa.getMyLocation().getLatitude(),
                    mapa.getMyLocation().getLongitude()), 15));
    }

    //Metodo que permite añadir marcadores mediante un boton
    public void addMarker(View view) {
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(mapa.getCameraPosition().target.latitude,
                        mapa.getCameraPosition().target.longitude)));
    }

    //Metodo que permite añadir marcadores pulsando el mapa
    @Override
    public void onMapClick(LatLng puntoPulsado) {
        mapa.addMarker(new MarkerOptions().position(puntoPulsado)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Crear un menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        //Cambiar el tipo de mapa, segun la opcion que seleccionemos
        if (id == R.id.action_settings) {
            mapa.setMapType((GoogleMap.MAP_TYPE_NORMAL));
            return true;
        }
        if (id == R.id.action_settings2) {
            mapa.setMapType((GoogleMap.MAP_TYPE_HYBRID));
            return true;
        }
        if (id == R.id.action_settings3) {
            mapa.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
            return true;
        }
        if (id == R.id.action_settings4) {
            mapa.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
