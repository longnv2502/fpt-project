package edu.fpt.assignment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fpt.assignment.R;
import edu.fpt.assignment.activities.DetailMovieActivity;
import edu.fpt.assignment.adapter.SearchLinearAdapter;
import edu.fpt.assignment.application.Session;
import edu.fpt.assignment.database.ApplicationDatabase;
import edu.fpt.assignment.database.dao.MovieDAO;
import edu.fpt.assignment.domain.MovieInfoDomain;
import edu.fpt.assignment.domain.UserDomain;
import edu.fpt.assignment.dto.MovieDetail;
import edu.fpt.assignment.utils.RecycleViewUtils;
import edu.fpt.assignment.utils.RegexUntils;

public class FavoriteFragment extends Fragment implements SearchLinearAdapter.onSelectData {

    @BindView(R.id.rvMovieFav)
    RecyclerView rvMovieFav;

    @BindView(R.id.txtNotFound)
    TextView txtNoData;

    public FavoriteFragment() {
    }

    List<MovieInfoDomain> items;
    SearchLinearAdapter searchLinearAdapter;
    MovieDAO movieDAO;
    UserDomain user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, rootView);
        movieDAO = ApplicationDatabase.getInstance(getContext()).movieDAO();
        user = (UserDomain) Session.get("user");
        initData();
        initView();

        return rootView;
    }

    private void initData() {
        // Thiết lập kiểu trình bày linear cho Favorite
        rvMovieFav.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rvMovieFav.setAdapter(new MovieFavoriteAdapter(getActivity(), items, movieDetail,  xSelectData));

        items = movieDAO.getAllWithUserId(user.getId());
        rvMovieFav.setHasFixedSize(true);
    }
//
    private void initView() {
        if (null == items || items.size() == 0) {
            txtNoData.setVisibility(View.VISIBLE);
            rvMovieFav.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            rvMovieFav.setVisibility(View.VISIBLE);
            RecycleViewUtils.setup(searchLinearAdapter, rvMovieFav, adapter -> new SearchLinearAdapter(getActivity(), items, this));
        }
    }



    @Override
    public void onResume() {
        super.onResume();
//        initData();
    }

    @Override
    public void onSelected(MovieInfoDomain modelMovie) {
        Intent intent = new Intent(getContext(), DetailMovieActivity.class);
        RegexUntils.SplitJumpAddress movieInfo = RegexUntils.splitJumpAddress(modelMovie.getJumpAddress());
        intent.putExtra("movieInfo", movieInfo);
        this.getContext().startActivity(intent);
    }
}
