package kz.kineu.mycollege.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.jess.ui.TwoWayAdapterView;
import com.jess.ui.TwoWayGridView;

import java.text.SimpleDateFormat;
import java.util.List;

import kz.kineu.mycollege.Acitivity.ImagesActivity;
import kz.kineu.mycollege.Adapters.ImagesAdapter;
import kz.kineu.mycollege.Adapters.NewsAdapter;
import kz.kineu.mycollege.Entities.Link;
import kz.kineu.mycollege.Entities.News;
import kz.kineu.mycollege.R;


public class ReadNewsFragment extends Fragment {

    private ImagesAdapter imagesAdapter;
    public ReadNewsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_news,container,false);

        TextView tvTitle = (TextView) view.findViewById(R.id.tvReadTitle);
        TextView tvDate = (TextView) view.findViewById(R.id.tvReadDate);
        TextView tvText = (TextView) view.findViewById(R.id.tvReadText);
        TwoWayGridView gvPhotos = (TwoWayGridView) view.findViewById(R.id.gvPhotos);


        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        final News news = getActivity().getIntent().getParcelableExtra("data");
        if(news.getLinks().size()>0) {
            imagesAdapter = new ImagesAdapter(news.getLinks(), getActivity());
            gvPhotos.setAdapter(imagesAdapter);
        } else {
            gvPhotos.setVisibility(View.GONE);
        }
        tvTitle.setText(news.getTitle());
        tvText.setText(news.getText());
        tvDate.setText(sdf.format(news.getDate()).toString());
        gvPhotos.setOnItemClickListener(new TwoWayAdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(TwoWayAdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra("imgurl",news.getLinks());
                intent.putExtra("selectedimg", position);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
