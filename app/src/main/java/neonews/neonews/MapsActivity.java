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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

                for (final Subject sub : _list) {
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
                        return;
                    }

                    LayoutInflater inflater = (LayoutInflater) _Ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    LinearLayout tv = (LinearLayout) inflater.inflate(R.layout.image_map, null, false);

                    TextView text = tv.findViewById(R.id.txt_marker);
                    text.setText(sub.getTitle());

                    ImageView image = tv.findViewById(R.id.img_marker);
                    myBitmap = Bitmap.createScaledBitmap(myBitmap, 200, 120, true);
                    image.setImageBitmap(myBitmap);

                    tv.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    tv.layout(0, 0, 200, 150);

                    tv.setDrawingCacheEnabled(true);
                    tv.buildDrawingCache();
                    Bitmap bm = tv.getDrawingCache();

                    final Bitmap finalMyBitmap = bm;// Bitmap.createScaledBitmap(bm, 200, 120, true);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMap.addMarker(new MarkerOptions().position(pos)
                                    .title(sub.getTitle())
                                    .icon(BitmapDescriptorFactory.fromBitmap(finalMyBitmap))
                                    .anchor(0.5f, 1));
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
                AlertChooseMedia alert = new AlertChooseMedia(_Ctx, subject, 0);
                alert.show();
                return true;
            }
        });
    }
}
