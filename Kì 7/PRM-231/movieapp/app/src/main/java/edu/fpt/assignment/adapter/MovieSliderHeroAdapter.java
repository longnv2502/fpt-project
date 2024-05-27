package edu.fpt.assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fpt.assignment.R;
import edu.fpt.assignment.domain.MovieInfoDomain;


public class MovieSliderHeroAdapter extends RecyclerView.Adapter<MovieSliderHeroAdapter.ViewHolder> {

    private List<MovieInfoDomain> items;
    private MovieSliderHeroAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(MovieInfoDomain modelMovie);
    }

    public MovieSliderHeroAdapter(Context context, List<MovieInfoDomain> items, MovieSliderHeroAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film_hero_slider, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieInfoDomain data = items.get(position);

        Glide.with(mContext)
                .load(data.getImageUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_image)
                        .transform(new RoundedCorners(16)))
                .into(holder.imgPhoto);

        holder.imgPhoto.setOnClickListener(view -> {
            onSelectData.onSelected(data);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgPhoto)
        public ImageView imgPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
