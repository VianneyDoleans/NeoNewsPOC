package neonews.neonews;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;

public class TimesActivity extends AppCompatActivity {

    private ArrayList<Subject> _SubjectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        String[] values = {"Article écrit", "Article vidéo", "Article radio"};
        NumberPicker typePicker = findViewById(R.id.type_picker);
        typePicker.setDisplayedValues(values);

        _SubjectList = (ArrayList<Subject>) getIntent().getSerializableExtra("ListSubject");
    }

    public void onclick_valid(View v)
    {
        NumberPicker numberPicker = findViewById(R.id.number_picker);
        NumberPicker typePicket = findViewById(R.id.type_picker);
        int timeNeed = numberPicker.getValue();
        SubjectMedia.MediaType mediaType = SubjectMedia.MediaType.values()[typePicket.getValue()];

        LayoutInflater inflater = getLayoutInflater();
        View content =  inflater.inflate(R.layout.times_select_subject, null);
        NumberPicker subjectPicker = content.findViewById(R.id.subject_picker);
        subjectPicker.setMaxValue(_SubjectList.size());
        subjectPicker.setDisplayedValues(getSubjectTitle());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(content)
                .setTitle("Selectionner un sujet");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String[] getSubjectTitle()
    {
        String[] result = new String[_SubjectList.size()];
        int i = 0;
        for (Subject subject : _SubjectList)
        {
            result[i] = subject.getTitle();
            i++;
        }
        return result;
    }
}
