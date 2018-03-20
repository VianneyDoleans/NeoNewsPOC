package neonews.neonews;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.CarouselViewHolder> {

    private List<Subject> _SubjectList = new ArrayList<>();
    private List<Integer> _MediaType = new ArrayList<>();
    private AppCompatActivity _Ctx;

    public SubjectAdapter(List<Subject> _SubjectList, AppCompatActivity ctx) {
        this._SubjectList = _SubjectList;

        _MediaType.add(R.drawable.newspaper);
        _MediaType.add(R.drawable.television);
        _MediaType.add(R.drawable.radio);

        _Ctx = ctx;
    }

    @Override
    public int getItemCount() {
        return _SubjectList.size();
    }

    @Override
    public CarouselViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.carousel_subject, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarouselViewHolder holder, int position) {
        Subject subject = _SubjectList.get(position);
        holder.display(subject);
    }

    public class CarouselViewHolder extends RecyclerView.ViewHolder {

        private CarouselView carouselView = null;

        public CarouselViewHolder(final View itemView) {
            super(itemView);

            carouselView = itemView.findViewById(R.id.carouselView);
        }

        public void display(final Subject subject) {

            carouselView.setPageCount(_MediaType.size());
            carouselView.setViewListener(new ViewListener() {

                @Override
                public View setViewForPosition(final int position) {
                    View customView =  _Ctx.getLayoutInflater().inflate(R.layout.subject_item, null);
                    ImageView iv_pres = customView.findViewById(R.id.iv_pres);
                    ImageView mediaPicture = customView.findViewById(R.id.iv_picture_media);
                    TextView tv_title = customView.findViewById(R.id.tv_title);
                    TextView tv_description = customView.findViewById(R.id.tv_description);
                    TextView tv_date = customView.findViewById(R.id.tv_date);

                    Picasso.get().load(subject.getImageUrl()).into(iv_pres);
                    mediaPicture.setImageResource(_MediaType.get(position));
                    tv_title.setText(subject.getTitle());
                    tv_description.setText(subject.getDescription());
                    Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                    tv_date.setText(formatter.format(subject.getDate()));
                    customView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertChooseMedia alert = new AlertChooseMedia(_Ctx, subject, position);
                            alert.show();
                        }
                    });
                    return customView;
                }
            });
        }
    }

}