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

        List<SubjectMedia> mediaFirst = new ArrayList<>();
        mediaFirst.add(new SubjectMedia(
                "https://upload.wikimedia.org/wikipedia/fr/thumb/3/33/Logo_20_Minutes.svg/938px-Logo_20_Minutes.svg.png",
                "20 Minutes",
                SubjectMedia.MediaType.NEWSPAPER,
                "https://www.20minutes.fr/culture/2236523-20180313-realite-virtuelle-visite-achete-appartement-depuis-canap"));
        mediaFirst.add(new SubjectMedia(
                "https://upload.wikimedia.org/wikipedia/fr/thumb/d/d1/Ouest-France_logo.svg/1280px-Ouest-France_logo.svg.png",
                "Ouest France",
                SubjectMedia.MediaType.NEWSPAPER,
                "www.google.fr"));
        mediaFirst.add(new SubjectMedia(
                "https://upload.wikimedia.org/wikipedia/fr/thumb/f/f8/Le_Figaro_logo.svg/1280px-Le_Figaro_logo.svg.png",
                "Le Figaro",
                SubjectMedia.MediaType.NEWSPAPER,
                "www.google.fr"));
        mediaFirst.add(new SubjectMedia(
                "https://is4-ssl.mzstatic.com/image/thumb/Purple118/v4/15/cb/71/15cb7124-7237-e20f-803c-1c3dcfaddf7d/mzl.xddegeux.png/246x0w.jpg",
                "Le Monde",
                SubjectMedia.MediaType.NEWSPAPER,
                "www.google.fr"));
        mediaFirst.add(new SubjectMedia(
                "https://generationbethune.files.wordpress.com/2017/04/la_voix_du_nord_logo.png?w=960",
                "La voix du nord",
                SubjectMedia.MediaType.NEWSPAPER,
                "www.google.fr"));
        _SubjectList.add(new Subject("http://i.imgur.com/DvpvklR.png", "Bonjour", "", mediaFirst));
        _SubjectList.add(new Subject("test", "Une actu", "", null));
        _SubjectList.add(new Subject("test", "Deux actus", "", null));

        final RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new SubjectAdapter(_SubjectList, this));
    }
}