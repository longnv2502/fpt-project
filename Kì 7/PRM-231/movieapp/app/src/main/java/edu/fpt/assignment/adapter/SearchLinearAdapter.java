package edu.fpt.assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fpt.assignment.R;
import edu.fpt.assignment.domain.MovieInfoDomain;

public class SearchLinearAdapter extends RecyclerView.Adapter<SearchLinearAdapter.ViewHolder> {
    private final List<MovieInfoDomain> items;
    private final SearchLinearAdapter.onSelectData onSelectData;
    private final Context context;

    public interface onSelectData{
        void onSelected(MovieInfoDomain modelMovie);
    }

    public SearchLinearAdapter(Context context , List<MovieInfoDomain> items, SearchLinearAdapter.onSelectData xSelectData) {
        this.items = items;
        this.onSelectData = xSelectData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film_search_item_linear,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MovieInfoDomain data = items.get(position);

        double rating = 9.0;
        holder.tvTitle.setText(data.getTitle());
        holder.tvRealeseDate.setText(data.getReleaseTime());
        holder.tvDesc.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, ...");

        float newValue = (float) rating;
        holder.ratingBar.setNumStars(5);
        holder.ratingBar.setStepSize((float) 0.5);
        holder.ratingBar.setRating(newValue / 2 );

        Glide.with(context)
                .load(data.getImageUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_image)
                        .transform(new RoundedCorners(16)))
                .into(holder.imgPhoto);
        holder.cvFilm.setOnClickListener(view -> {
            onSelectData.onSelected(data);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cvFilm)
        public CardView cvFilm;

        @BindView(R.id.imgPhoto)
        public ImageView imgPhoto;

        @BindView(R.id.tvTitle)
        public TextView tvTitle;

        @BindView(R.id.tvRealeseDate)
        public TextView tvRealeseDate;

        @BindView(R.id.tvDesc)
        public TextView tvDesc;

        @BindView(R.id.ratingBar)
        public RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
