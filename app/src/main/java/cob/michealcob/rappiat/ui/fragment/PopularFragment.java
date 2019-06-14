package cob.michealcob.rappiat.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidstudy.networkmanager.Monitor;
import com.androidstudy.networkmanager.Tovuti;

import java.util.ArrayList;
import java.util.List;

import cob.michealcob.rappiat.R;
import cob.michealcob.rappiat.model.Constant;
import cob.michealcob.rappiat.model.Movie;
import cob.michealcob.rappiat.network.retrofit.ApiClient;
import cob.michealcob.rappiat.network.retrofit.ApiService;
import cob.michealcob.rappiat.network.retrofit.response.ApiResponse;
import cob.michealcob.rappiat.offline.MovieDB;
import cob.michealcob.rappiat.ui.adapter.ViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    RecyclerView mRecyclerView;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    TextView mStatus, mReload;
    String key = Constant.KEY;

    LinearLayout linearLayout;

    List<Movie> movieList;


    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        mStatus = view.findViewById(R.id.status);

        mRecyclerView = view.findViewById(R.id.rv_popular);
        mReload = view.findViewById(R.id.tv_reload);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);

        linearLayout = view.findViewById(R.id.ll_nothinghere);

        MovieDB movieDB = new MovieDB(getActivity());
        movieList = movieDB.getAllMovies();
        if (movieList.size()==0){
            linearLayout.setVisibility(View.VISIBLE);
            loadOnline();
        }else{
            loadOffline(movieList);
        }

        Tovuti.from(getActivity()).monitor(new Monitor.ConnectivityListener(){
            @Override
            public void onConnectivityChanged(int connectionType, boolean isConnected, boolean isFast){
                // TODO: Handle the connection...
                if (isConnected){
                    linearLayout.setVisibility(View.GONE);
                    Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
                    a.reset();
                    mStatus.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    mStatus.setTextColor(getResources().getColor(R.color.colorWhite));
                    mStatus.setText("Connected");
                    mStatus.clearAnimation();
                    mStatus.startAnimation(a);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mStatus.setVisibility(View.GONE);
                        }
                    }, 4000);

                    if (movieList.size()==0){
                        linearLayout.setVisibility(View.VISIBLE);
                        mReload.setVisibility(View.VISIBLE);
                        mReload.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                callReload();
                            }
                        });
                    }



                }else{
                }
            }
        });




        return view;
    }

    private void callReload() {
        MovieDB movieDB = new MovieDB(getActivity());
        movieList = movieDB.getAllMovies();
        if (movieList.size()==0){
            linearLayout.setVisibility(View.VISIBLE);
            loadOnline();
        }else{
            loadOffline(movieList);
        }
    }

    private void loadOnline() {
        ApiService apiService = ApiClient.getApi().create(ApiService.class);
        final Call<ApiResponse> popularFragmentCall = apiService.getPopularResponse(key);

        popularFragmentCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                linearLayout.setVisibility(View.GONE);

                ApiResponse apiResponse = response.body();

                List<ApiResponse.Results> result = apiResponse.getResults();
                List<Movie> movieListAdd = new ArrayList<>();
                for (int i=0;i<result.size();i++){
                    ApiResponse.Results results = result.get(i);
                    Movie movie = new Movie();
                    movie.setMovieid(results.getId());
                    movie.setTitle(results.getTitle());
                    movie.setRating(results.getVote());
                    movie.setOverview(results.getOverview());
                    movie.setPosterpath(results.getPosterPath());
                    movie.setBackdrop(results.getBackdropPath());

                    MovieDB movieDB1 = new MovieDB(getActivity());
                    movieDB1.createMovie(movie);

                    movieListAdd.add(movie);

                }

                RecyclerView.Adapter mAdapter = new ViewAdapter(getActivity(), movieListAdd);
                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                linearLayout.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadOffline(List<Movie> movieList) {
        /*mStatus.setBackgroundColor(getResources().getColor(R.color.coloGray));
        mStatus.setTextColor(getResources().getColor(R.color.colorWhite));
        mStatus.setText("Loading from cache,\n Please turn on your internet");*/

        RecyclerView.Adapter mAdapter = new ViewAdapter(getActivity(), movieList);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onStop(){
        Tovuti.from(getActivity()).stop();
        super.onStop();
    }


}
