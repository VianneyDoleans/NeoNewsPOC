package neonews.neonews;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 12/03/2018.
 */

public class AlertChooseMedia extends Dialog implements Serializable {

    private Subject _Subject = null;
    private Context _Ctx = null;
    private SubjectMedia.MediaType _MediaType = null;

    public AlertChooseMedia(Context context, Subject subject, int position) {
        super(context);

        _Subject = subject;
        _Ctx = context;
        _MediaType = SubjectMedia.MediaType.values()[position];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_choose_media);

        this.setTitle("Séléctionner le média de votre choix");

        //Bind list of media
        List<SubjectMedia> listMedias = new ArrayList<>();
        for (SubjectMedia mediaType : _Subject.getListMedia())
        {
            if (mediaType.getMediaTye() == _MediaType)
                listMedias.add(mediaType);
        }

        if (_Subject.getListMedia() != null) {
            GridView gridView = findViewById(R.id.gridView);
            GridViewMediaSubject gridAdapter = new GridViewMediaSubject(_Ctx, R.layout.gridview_media_subject, listMedias);
            gridView.setAdapter(gridAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(_Ctx, WebviewSubjectActivity.class);
                    intent.putExtra("Subject", _Subject.getListMedia().get(i));
                    _Ctx.startActivity(intent);
                }
            });
        }
    }
}
