package neonews.neonews;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * Created by Guillaume on 12/03/2018.
 */

public class AlertChooseMedia extends Dialog {

    public AlertChooseMedia(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_choose_media);
    }
}
