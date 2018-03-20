package neonews.neonews;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 13/03/2018.
 */

public class GridViewMediaSubject extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private List<SubjectMedia> data = new ArrayList<>();

    public GridViewMediaSubject(Context context, int layoutResourceId, List<SubjectMedia> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = row.findViewById(R.id.text);
            holder.image = row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        SubjectMedia item = data.get(position);
        holder.imageTitle.setText(item.getName());
        Picasso.get().load(data.get(position).getLogoUrl()).into(holder.image);
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}
