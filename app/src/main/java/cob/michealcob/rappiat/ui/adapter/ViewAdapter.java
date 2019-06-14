package cob.michealcob.rappiat.ui.adapter;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cob.michealcob.rappiat.R;
import cob.michealcob.rappiat.model.Movie;
import cob.michealcob.rappiat.ui.dialog.DetailsDialog;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {


    List<Movie> resultsList;

    Context context;

    public ViewAdapter(Context mCtx, List<Movie> _resultsList){
        context = mCtx;
        resultsList = _resultsList;
    }

    @NonNull
    @Override
    public ViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        ViewHolder viewHolder = holder;

        Movie results = resultsList.get(i);

        String posterPath = results.getPosterpath();
        final String title = results.getTitle();
        final String rating = results.getRating();
        final String backdrop = results.getBackdrop();
        final String overview = results.getOverview();
        final String movieid = results.getMovieid();
        /*https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg*/
        String url = "https://image.tmdb.org/t/p/w500"+posterPath;

        Picasso.get()
                .load(url)
                .into(viewHolder.imageView);

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*itemClickListener.onItemClicked();*/
                DetailsDialog dialog = new DetailsDialog();

                Bundle args = new Bundle();
                args.putString("title", title);
                args.putString("rating", rating);
                args.putString("backdrop", backdrop);
                args.putString("overview", overview);
                args.putString("id", movieid);
                FragmentTransaction ft = ((AppCompatActivity)context).getFragmentManager().beginTransaction();
                dialog.setArguments(args);
                dialog.show(ft, "here");
            }
        });
    }

    @Override
    public int getItemCount() {
        if(null == resultsList) return 0;
        return this.resultsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
