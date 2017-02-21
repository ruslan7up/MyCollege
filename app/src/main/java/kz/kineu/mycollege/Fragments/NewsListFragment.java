package kz.kineu.mycollege.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import kz.kineu.mycollege.Adapters.NewsAdapter;
import kz.kineu.mycollege.R;

public class NewsListFragment extends Fragment {

    private NewsAdapter newslistadapter;
    private ListView mListView;
    private static final String LOG_TAG = "MyCollegeApp";

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh: newslistadapter.clear(); newslistadapter.notifyDataSetChanged(); newslistadapter.refreshNews(); newslistadapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }


}
