package kz.kineu.mycollege.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import kz.kineu.mycollege.Entities.Link;
import kz.kineu.mycollege.R;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by User on 14.02.2017.
 */

public class ImagesAdapter extends BaseAdapter {

    private final String LOG_TAG = "MyCollegeApp";
    private final String url = "http://78.46.123.237:7777";
    private List<Link> links;
    private Context context;
    private ImageLoader mLoader;

    {
        mLoader = ImageLoader.getInstance();
    }

    public ImagesAdapter(List<Link> links, Context context) {
        this.links = links;
        this.context = context;
    }

    @Override
    public int getCount() {
        return links.size();
    }

    @Override
    public Object getItem(int position) {
        return links.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Link link = (Link) getItem(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.img_item,null);
        ImageView ivImage = (ImageView) view.findViewById(R.id.ivPhoto);
        mLoader.displayImage(url+link.getLink(),ivImage);
        return view;
    }


}
