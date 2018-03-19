package neonews.neonews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Subject> _SubjectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    _SubjectList.addAll(ApifyService.getCnewsMatin());
                    _SubjectList.addAll(ApifyService.getLeMonde());
                    sortAllSubject();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bindSubject();
                        }
                    });
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        FloatingActionButton floatingActionButton = findViewById(R.id.actionButtonFilter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("ListSubject", _SubjectList);
                startActivity(intent);
            }
        });
    }

    private void sortAllSubject()
    {
        Collections.sort(_SubjectList, new Comparator<Subject>() {
            @Override
            public int compare(Subject o2, Subject o1) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }

    private void bindSubject()
    {
        final RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new SubjectAdapter(_SubjectList, this));
    }
}