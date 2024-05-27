package edu.fpt.assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fpt.assignment.R;
import edu.fpt.assignment.activities.DetailMovieActivity;
import edu.fpt.assignment.domain.MovieStarDomain;
import edu.fpt.assignment.dto.HomeSection;
import edu.fpt.assignment.domain.MovieInfoDomain;
import edu.fpt.assignment.enums.AdapterType;
import edu.fpt.assignment.utils.RecycleViewUtils;
import edu.fpt.assignment.utils.RegexUntils;

public class HomeSectionAdapter extends RecyclerView.Adapter<HomeSectionAdapter.ViewHolder>{

    private final List<HomeSection> items;
    private final Context mContext;

    public HomeSectionAdapter(Context context, List<HomeSection> items) {
        this.mContext = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        HomeSection data = items.get(position);
        AdapterType type = data.getAdapterType();
        switch (type) {
            case MovieSlider:
                return 1;
            case MovieDetail:
                return 2;
            case MovieImageVertical:
                return 3;
            case MovieImageHorizontal:
                return 4;
            case ActorImage:
                return 5;
        }
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_home_section_silder, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_home_section, parent, false);
        }
        return new HomeSectionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeSection data = items.get(position);
        AdapterType type = data.getAdapterType();
        holder.initView(type);
        holder.initData(type, data);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ViewHolder
            extends
            RecyclerView.ViewHolder
            implements
            MovieSliderHeroAdapter.onSelectData,
            MovieDetailAdapter.onSelectData,
            MovieImageVerticalAdapter.onSelectData,
            MovieImageHorizontalAdapter.onSelectData {

        @BindView(R.id.sectionNameTxt)
        public TextView sectionNameTxt;

        @BindView(R.id.rvMovies)
        public RecyclerView rvMovies;

        MovieImageHorizontalAdapter movieImageHorizontalAdapter;
        MovieImageVerticalAdapter movieImageVerticalAdapter;
        MovieSliderHeroAdapter movieSliderHeroAdapter;
        MovieImageCircleAdapter movieImageCircleAdapter;
        MovieDetailAdapter movieDetailAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onSelected(MovieInfoDomain modelMovie) {
            Intent intent = new Intent(itemView.getContext(), DetailMovieActivity.class);
            RegexUntils.SplitJumpAddress movieInfo = RegexUntils.splitJumpAddress(modelMovie.getJumpAddress());
            intent.putExtra("movieInfo", movieInfo);
            itemView.getContext().startActivity(intent);
        }

        public void initView(AdapterType type) {
            if (type == AdapterType.MovieSlider) {
                rvMovies.setHasFixedSize(true);
                rvMovies.setLayoutManager(new CardSliderLayoutManager(itemView.getContext()));
                new CardSnapHelper().attachToRecyclerView(rvMovies);
            } else {
                rvMovies.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
                rvMovies.setHasFixedSize(true);
            }
        }

        public void initData(AdapterType type, HomeSection homeSection) {
            sectionNameTxt.setText(homeSection.getHomeSectionName());
            switch (type) {
                case MovieImageHorizontal:
                    RecycleViewUtils.setup(movieImageHorizontalAdapter, rvMovies, adapter -> new MovieImageHorizontalAdapter(itemView.getContext(), homeSection.getMovieInfoDomains(), this));
                    break;
                case MovieImageVertical:
                    RecycleViewUtils.setup(movieImageVerticalAdapter, rvMovies, adapter -> new MovieImageVerticalAdapter(itemView.getContext(), homeSection.getMovieInfoDomains(), this));
                    break;
                case MovieDetail:
                    RecycleViewUtils.setup(movieDetailAdapter, rvMovies, adapter -> new MovieDetailAdapter(itemView.getContext(), homeSection.getMovieInfoDomains(), this));
                    break;
                case MovieSlider:
                    RecycleViewUtils.setup(movieSliderHeroAdapter, rvMovies, adapter -> new MovieSliderHeroAdapter(itemView.getContext(), homeSection.getMovieInfoDomains(), this));
                    break;
                case ActorImage:
                    List<MovieStarDomain> items = homeSection.getMovieInfoDomains().stream().map(MovieStarDomain::of).collect(Collectors.toList());
                    RecycleViewUtils.setup(movieImageCircleAdapter, rvMovies, adapter -> new MovieImageCircleAdapter(itemView.getContext(), items));
                    break;
            }
        }



    }
}
