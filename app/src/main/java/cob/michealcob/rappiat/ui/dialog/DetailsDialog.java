package cob.michealcob.rappiat.ui.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import cob.michealcob.rappiat.R;
import cob.michealcob.rappiat.model.Constant;
import cob.michealcob.rappiat.network.retrofit.ApiClient;
import cob.michealcob.rappiat.network.retrofit.ApiService;
import cob.michealcob.rappiat.network.retrofit.response.ApiResponse;
import cob.michealcob.rappiat.network.retrofit.response.VideoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsDialog extends DialogFragment {

    public static final String TAG = "setRoute";

    ImageView ivClose, ivBackdrop, ivPlay;

    TextView mTitle, mRating, mOverview;
    String key = Constant.KEY;

    String youtubeId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.details, container, false);

        ivClose = view.findViewById(R.id.iv_back);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelUpload();
            }
        });


        ivPlay = view.findViewById(R.id.play);



        String title = getArguments().getString("title");
        String rating = getArguments().getString("rating");
        String overview = getArguments().getString("overview");

        String backdrop = getArguments().getString("backdrop");

        String movieId = getArguments().getString("id");

        String url = "https://image.tmdb.org/t/p/w500"+backdrop;

        ivBackdrop = view.findViewById(R.id.iv_backdrop);

        ApiService apiService = ApiClient.getApi().create(ApiService.class);
        final Call<VideoResponse> videoResponseCall = apiService.getVideoId(movieId,key);

        videoResponseCall.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                VideoResponse videoResponse = response.body();
                List<VideoResponse.Results> result = videoResponse.getResults();
                for (int i=0;i<result.size();i++){
                    if (i==0){
                        youtubeId = result.get(i).getKey();
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "No network connect", Toast.LENGTH_SHORT).show();
            }
        });

        Picasso.get()
                .load(url)
                .into(ivBackdrop);

        mTitle = view.findViewById(R.id.tv_title);
        mOverview =view.findViewById(R.id.tv_overview);
        mRating = view.findViewById(R.id.tv_rating);

        mTitle.setText(title);
        mOverview.setText(overview);
        mRating.setText(rating);
        ivBackdrop = view.findViewById(R.id.iv_backdrop);


        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayDialog dialog = new PlayDialog();
                Bundle args = new Bundle();
                args.putString("key", youtubeId);
                FragmentTransaction ft = ((AppCompatActivity)getActivity()).getFragmentManager().beginTransaction();
                dialog.setArguments(args);
                dialog.show(ft, "here");
            }
        });

        return view;

    }

    private void cancelUpload() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.dismiss();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}
