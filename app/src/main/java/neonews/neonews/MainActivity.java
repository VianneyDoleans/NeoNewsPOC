package neonews.neonews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Subject> _SubjectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _SubjectList.add(new Subject("http://i.imgur.com/DvpvklR.png", "Bonjour", ""));
        _SubjectList.add(new Subject("test", "Une actu", ""));
        _SubjectList.add(new Subject("test", "Deux actus", ""));

        final RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new SubjectAdapter(_SubjectList, this));
    }
}