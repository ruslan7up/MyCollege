package kz.kineu.mycollege.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import kz.kineu.mycollege.Entities.News;
import kz.kineu.mycollege.R;


public class ReadNewsFragment extends Fragment {

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        News news = getActivity().getIntent().getParcelableExtra("data");
        tvTitle.setText(news.getTitle());
        tvText.setText(news.getText());
        tvDate.setText(sdf.format(news.getDate()).toString());
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}
