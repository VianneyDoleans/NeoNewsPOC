package neonews.neonews;

import android.content.Context;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.CarouselViewHolder> {

    private List<Subject> _SubjectList = new ArrayList<>();
    private List<Integer> _MediaType = new ArrayList<>();
    private AppCompatActivity _Ctx;

    public SubjectAdapter(List<Subject> _SubjectList, AppCompatActivity ctx) {
        this._SubjectList = _SubjectList;

        _MediaType.add(R.drawable.newspaper);
        _MediaType.add(R.drawable.radio);
        _MediaType.add(R.drawable.television);

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
                public View setViewForPosition(int position) {
                    View customView =  _Ctx.getLayoutInflater().inflate(R.layout.subject_item, null);
                    ImageView iv_pres = customView.findViewById(R.id.iv_pres);
                    ImageView mediaPicture = customView.findViewById(R.id.iv_picture_media);
                    TextView tv_title = customView.findViewById(R.id.tv_title);
                    TextView tv_description = customView.findViewById(R.id.tv_description);

                    Picasso.get().load(subject.getImageUrl()).into(iv_pres);
                    mediaPicture.setImageResource(_MediaType.get(position));
                    tv_title.setText(subject.getTitle());
                    tv_description.setText(subject.getDescription());
                    customView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    return customView;
                }
            });
        }
    }

}