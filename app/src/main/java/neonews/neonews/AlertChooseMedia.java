package neonews.neonews;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.GridView;

/**
 * Created by Guillaume on 12/03/2018.
 */

public class AlertChooseMedia extends Dialog {

    private Subject _Subject = null;
    private Context _Ctx = null;

    public AlertChooseMedia(Context context, Subject subject) {
        super(context);

        _Subject = subject;
        _Ctx = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_choose_media);

        this.setTitle("Séléctionner le média de votre choix");

        GridView gridView = findViewById(R.id.gridView);
        GridViewMediaSubject gridAdapter = new GridViewMediaSubject(_Ctx, R.layout.gridview_media_subject, _Subject.getListMedia());
        gridView.setAdapter(gridAdapter);
    }
}
