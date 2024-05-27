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
import edu.fpt.assignment.domain.MovieStarDomain;
import edu.fpt.assignment.dto.MovieStar;

public class MovieImageCircleAdapter extends RecyclerView.Adapter<MovieImageCircleAdapter.ViewHolder> {

    private final List<MovieStarDomain> items;
    private final Context mContext;

    public MovieImageCircleAdapter(Context context, List<MovieStarDomain> items) {
        this.mContext = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film_image_circle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MovieStarDomain data = items.get(position);

        Glide.with(mContext)
                .load(data.getImageUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_image)
                        .transform(new RoundedCorners(16)))
                .circleCrop()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
