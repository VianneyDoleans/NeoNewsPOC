package neonews.neonews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<Subject> _list;
    Context _Ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent i = getIntent();
                _list = (ArrayList<Subject>) i.getSerializableExtra("ListSubject");

                Paint color = new Paint();
                color.setTextSize(35);
                color.setColor(Color.WHITE);

                for (final Subject sub : list) {
                    final LatLng pos = new LatLng(sub.getLat(), sub.getLng());
                    Bitmap myBitmap = null;
                    try {
                        URL url = new URL(sub.getImageUrl());
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream input = connection.getInputStream();
                        myBitmap = BitmapFactory.decodeStream(input);
                    } catch (IOException e) {
                        // Log exception
                    }
                    Bitmap.Config conf = Bitmap.Config.ARGB_8888;
                    Bitmap bmp = Bitmap.createBitmap(80, 80, conf);
                    Canvas canvas1 = new Canvas(bmp);
                    canvas1.drawBitmap(myBitmap, 0,0, color);
                    canvas1.drawText(sub.getTitle() , 30, 40, color);
                    final Bitmap finalMyBitmap = myBitmap;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMap.addMarker(new MarkerOptions().position(pos)
                                    .title(sub.getTitle())
                                    .icon(BitmapDescriptorFactory.fromBitmap(finalMyBitmap))
                                    .anchor(0.5f, 1)).showInfoWindow();
                        }
                    });
                }
            }
        }).start();

        LatLng center = new LatLng(46.679603, 1.636908);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(center, 5.5f));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int position = 0;
                for (Subject sub : _list) {
                    if (sub.getTitle().equals(marker.getTitle()))
                        break;
                    position++;
                }
                Subject subject = _list.get(position);
                AlertChooseMedia alert = new AlertChooseMedia(_Ctx, subject);
                alert.show();
                return true;
            }
        });
    }
}
