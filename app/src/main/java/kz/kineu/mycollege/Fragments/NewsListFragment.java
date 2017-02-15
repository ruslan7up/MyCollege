package kz.kineu.mycollege.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

import kz.kineu.mycollege.Adapters.NewsAdapter;
import kz.kineu.mycollege.Entities.News;
import kz.kineu.mycollege.R;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NewsListFragment extends Fragment {

    private ArrayList<News> list;
    private NewsAdapter newslistadapter;
    private ListView mListView;
    private String url = "http://78.46.123.237:7777";
    private static final String LOG_TAG = "MyCollegeApp";
    private Retrofit client;


    public NewsListFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.news);
        client = new Retrofit.Builder().addConverterFactory(JacksonConverterFactory.create()).baseUrl(url).build();
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        newslistadapter = new NewsAdapter(getActivity(),0);
        mListView = (ListView) view.findViewById(R.id.lvNews);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), kz.kineu.mycollege.Acitivity.FragmentActivity.class);
                intent.putExtra("fragment","readnews");
                intent.putExtra("data",newslistadapter.getItem(position));
                startActivity(intent);
            }
        });
        mListView.setAdapter(newslistadapter);
        return view;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh: newslistadapter.clear(); newslistadapter.notifyDataSetChanged(); newslistadapter.refreshNews(); newslistadapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
}
