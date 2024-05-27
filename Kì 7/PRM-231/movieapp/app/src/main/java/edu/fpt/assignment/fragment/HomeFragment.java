package edu.fpt.assignment.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.fpt.assignment.R;
import edu.fpt.assignment.adapter.HomeSectionAdapter;
import edu.fpt.assignment.application.ApiResource;
import edu.fpt.assignment.dialog.FailDialog;
import edu.fpt.assignment.dto.HomePageResult;
import edu.fpt.assignment.dto.HomeSection;
import edu.fpt.assignment.dto.ResponseObject;
import edu.fpt.assignment.networking.RetrofitFactory;
import edu.fpt.assignment.networking.TheLokLokApiService;
import edu.fpt.assignment.utils.RecycleViewUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private static final String TAG = "FragmentMovie";

    private final Retrofit retrofit;

    private final TheLokLokApiService theLokLokApiService;
    private int page = -1;

    @BindView(R.id.rvHome)
    RecyclerView rvHome;

    private ProgressDialog progressDialog;

    private HomeSectionAdapter homeSectionAdapter;

    private HomePageResult homePageResult;

    private List<HomeSection> dataSet;

    public HomeFragment() {
        this.retrofit = RetrofitFactory.create(ApiResource.LOKLOK);
        this.theLokLokApiService = retrofit.create(TheLokLokApiService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);

        initProgressDialog();
        initView();
        initMovieData();
        initAction();
        return rootView;
    }

    private void initView() {
        this.dataSet = new ArrayList<>();
        rvHome.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvHome.setHasFixedSize(true);
    }

    private void initMovieData() {
        if (page >= 4) return;
        page++;
        this.theLokLokApiService.getHome(287, page, 10).enqueue(new Callback<ResponseObject<HomePageResult>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseObject<HomePageResult>> call, @NonNull Response<ResponseObject<HomePageResult>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    homePageResult = response.body().getData();
                    dataSet.addAll(homePageResult.getRecommendItems());
                    dataSet = dataSet.stream().distinct().collect(Collectors.toList());
                    RecycleViewUtils.setup(homeSectionAdapter, rvHome, adapter -> new HomeSectionAdapter(getContext(), dataSet));
                }
            }

            @Override
            public void onFailure(Call<ResponseObject<HomePageResult>> call, Throwable t) {
                new FailDialog(getContext(), "Failer", "Có lỗi trong quá trình hoạt động").show();
                Log.e(TAG, t.getLocalizedMessage(), t);
            }
        });
    }

    private void initAction() {
        HomeFragment _this = this;

        rvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    _this.initMovieData();
                }
            }
        });
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("The system is currently loading data...");
    }

}