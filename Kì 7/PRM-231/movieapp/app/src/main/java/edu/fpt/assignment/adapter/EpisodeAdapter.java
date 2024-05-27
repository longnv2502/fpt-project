package edu.fpt.assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fpt.assignment.R;
import edu.fpt.assignment.dto.MovieDetail;


public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {

    private final List<MovieDetail.EpisodeVo> items;
    private final EpisodeAdapter.onSelectData onSelectData;
    private final Context mContext;

    public EpisodeAdapter(Context context, List<MovieDetail.EpisodeVo> items, EpisodeAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    public interface onSelectData {
        void onSelected(MovieDetail.EpisodeVo modelMovie);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film_episode, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieDetail.EpisodeVo data = items.get(position);
        if (items.size() == 1 && data.getSeriesNo() == 0) {
            holder.episodeBtn.setText("Watch now!");
        } else {
            holder.episodeBtn.setText(String.valueOf(data.getSeriesNo()));
        }
        holder.episodeBtn.setOnClickListener(view -> {
            onSelectData.onSelected(data);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.episodeBtn)
        public Button episodeBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
