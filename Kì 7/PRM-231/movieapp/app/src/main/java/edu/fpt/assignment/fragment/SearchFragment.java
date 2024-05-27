package edu.fpt.assignment.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fpt.assignment.R;
import edu.fpt.assignment.activities.DetailMovieActivity;
import edu.fpt.assignment.adapter.MovieImageVerticalAdapter;
import edu.fpt.assignment.adapter.SearchLinearAdapter;
import edu.fpt.assignment.application.ApiResource;
import edu.fpt.assignment.domain.MovieInfoDomain;
import edu.fpt.assignment.dto.ListResult;
import edu.fpt.assignment.dto.ResponseObject;
import edu.fpt.assignment.dto.SearchCriteria;
import edu.fpt.assignment.dto.SearchResult;
import edu.fpt.assignment.networking.RetrofitFactory;
import edu.fpt.assignment.networking.TheLokLokApiService;
import edu.fpt.assignment.utils.RecycleViewUtils;
import edu.fpt.assignment.utils.RegexUntils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchFragment extends Fragment implements SearchLinearAdapter.onSelectData, MovieImageVerticalAdapter.onSelectData {
    private static final String TAG = "FragmentSearchMovie";
    private final Retrofit retrofit;
    private final TheLokLokApiService theLokLokApiService;
    @BindView(R.id.svSearch)
    SearchView searchView;
    @BindView(R.id.rcvSearchItem)
    RecyclerView rcvSearchItem;
    @BindView(R.id.tvResultSearch)
    TextView tvResultSearch;
    @BindView(R.id.btnShowMore)
    Button btnShowMore;

    private ProgressDialog progressDialog;

    private SearchLinearAdapter searchAdapter;
    private MovieImageVerticalAdapter movieImageVerticalAdapter;

    private ListResult<SearchResult> searchResults;

    private int limits = 9;

    public SearchFragment() {
        this.retrofit = RetrofitFactory.create(ApiResource.LOKLOK);
        this.theLokLokApiService = retrofit.create(TheLokLokApiService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);
        initProgressDialog();
        searchData();
//        initMovieData();
        return rootView;
    }

    @Override
    public void onPause() {
        // Xử lí khi pause Activity sẽ ẩn KeyBoard
        super.onPause();
        hideKeyBoard();
    }

    private void initProgressDialog() {
        // Xử lí Processing khi đang load Data
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("The system is currently loading data...");
    }

    private void initView() {
        // Thiết lập kiểu trình bày Linear
        rcvSearchItem.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvSearchItem.setHasFixedSize(true);
    }

    private void initGridView() {
        // Thiết lập kiểu trình bày Grid
        rcvSearchItem.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rcvSearchItem.setHasFixedSize(true);
    }

    private void initMovieSearchData(String keyword, int size) {
        // Xử lí lấy data từ API và truyền tới SearchLinearAdapter
        SearchCriteria searchCriteria = SearchCriteria.of(keyword, size);
        SearchFragment _this = this;
        theLokLokApiService.searchLeaderboard(searchCriteria).enqueue(new Callback<ResponseObject<ListResult<SearchResult>>>() {
            @Override
            public void onResponse(Call<ResponseObject<ListResult<SearchResult>>> call, Response<ResponseObject<ListResult<SearchResult>>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    searchResults = response.body().getData();
                    List<SearchResult> items = searchResults.getSearchResults();
                    List<MovieInfoDomain> domainLst = items.stream().map(MovieInfoDomain::of).collect(Collectors.toList());
                    if (items.size() <= 10) {
                        RecycleViewUtils.setup(searchAdapter, rcvSearchItem, adapter -> new SearchLinearAdapter(getActivity(), domainLst, _this));
                    } else {
                        RecycleViewUtils.setup(movieImageVerticalAdapter, rcvSearchItem, adapter -> new MovieImageVerticalAdapter(getActivity(), domainLst, _this));
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseObject<ListResult<SearchResult>>> call, Throwable t) {

            }
        });
    }

    private void searchData() {
        searchView.setQueryHint(getString(R.string.search_film));
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Xử lí sự kiện focus trong searchView
                if (hasFocus) {
                    searchView.setBackgroundResource(R.drawable.bg_search_border);
                } else {
                    searchView.setBackgroundResource(R.drawable.bg_search);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private String queryText = "";

            @Override
            public boolean onQueryTextSubmit(String query) {
                // Xử lí sự kiện submit text SearchView
                this.queryText = query;
                initMovieSearchData(query, 10);
                rcvSearchItem.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        // Xửa lí sự kiện cuộn item trong RecycleView
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                        if (lastVisibleItemPosition == limits) {
                            // xử lí khi hiện items cuối trong RecycleView hiển thị nút Button
                            btnShowMore.setVisibility(View.VISIBLE);
                            Animation animationSlideUp = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                                    R.anim.anim_button_showmore_slideup);
                            btnShowMore.startAnimation(animationSlideUp);
                        } else {
                            // Ẩn nút Button khi items khác items cuối và xóa Animation
                            btnShowMore.setVisibility(View.GONE);
                            btnShowMore.clearAnimation();
                            btnShowMore.setOnClickListener(this::onClickShowMore);
                        }
                    }

                    private void onClickShowMore(View view) {
                        // xử lí sự kiện ấn vào Button và trả về trình bày dạng GridView
                        progressDialog.show();
                        initGridView();
                        initMovieSearchData(queryText, 30);
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lí sự kiện thay đổi text trong SearchView
                initMovieSearchData(newText, 10);
                initView();
                return false;
            }
        });
        initView();
    }

    private void hideKeyBoard() {
        // Xử lí sự kiện ẩn KeyBoard khi trở lại trang
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
//        searchView.setQuery("", false);
        searchView.clearFocus();
    }

    @Override
    public void onSelected(MovieInfoDomain modelMovie) {
        Intent intent = new Intent(getContext(), DetailMovieActivity.class);
        RegexUntils.SplitJumpAddress movieInfo = RegexUntils.splitJumpAddress(modelMovie.getJumpAddress());
        intent.putExtra("movieInfo", movieInfo);
        this.getContext().startActivity(intent);
    }

}
