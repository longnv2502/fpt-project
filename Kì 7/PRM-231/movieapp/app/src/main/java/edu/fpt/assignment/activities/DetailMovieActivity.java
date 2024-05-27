package edu.fpt.assignment.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.fpt.assignment.R;
import edu.fpt.assignment.adapter.EpisodeAdapter;
import edu.fpt.assignment.adapter.MovieImageCircleAdapter;
import edu.fpt.assignment.adapter.MovieImageVerticalAdapter;
import edu.fpt.assignment.application.Session;
import edu.fpt.assignment.database.ApplicationDatabase;
import edu.fpt.assignment.application.ApiResource;
import edu.fpt.assignment.database.dao.MovieDAO;
import edu.fpt.assignment.database.dao.MovieWithUserDAO;
import edu.fpt.assignment.database.dao.UserDAO;
import edu.fpt.assignment.domain.MovieInfoDomain;
import edu.fpt.assignment.domain.MovieStarDomain;
import edu.fpt.assignment.domain.MovieWithUserDomain;
import edu.fpt.assignment.domain.UserDomain;
import edu.fpt.assignment.dto.MovieDetail;
import edu.fpt.assignment.dto.MovieMedia;
import edu.fpt.assignment.dto.ResponseObject;
import edu.fpt.assignment.enums.DefinitionCode;
import edu.fpt.assignment.enums.MovieUserType;
import edu.fpt.assignment.networking.RetrofitFactory;
import edu.fpt.assignment.networking.TheLokLokApiService;
import edu.fpt.assignment.utils.RecycleViewUtils;
import edu.fpt.assignment.utils.RegexUntils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailMovieActivity extends AppCompatActivity implements EpisodeAdapter.onSelectData, MovieImageVerticalAdapter.onSelectData {

    private static final String TAG = "DetailMovieActivity";

    private final Retrofit retrofit;
    private final TheLokLokApiService theLokLokApiService;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvRating)
    TextView tvRating;

    @BindView(R.id.tvOverview)
    TextView tvOverview;

    @BindView(R.id.imgCover)
    ImageView imgCover;

    @BindView(R.id.imgPhoto)
    ImageView imgPhoto;

    @BindView(R.id.rvEpisode)
    RecyclerView rvEpisode;

    @BindView(R.id.rvActor)
    RecyclerView rvActor;

    @BindView(R.id.rvSimilar)
    RecyclerView rvSimilar;

    @BindView(R.id.imgFavorite)
    MaterialFavoriteButton imgFavorite;

    @BindView(R.id.fabShare)
    FloatingActionButton fabShare;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    RegexUntils.SplitJumpAddress movieInfo;
    MovieDetail movieDetail;
    ProgressDialog progressDialog;
    EpisodeAdapter episodeAdapter;
    MovieImageCircleAdapter movieImageCircleAdapter;
    MovieImageVerticalAdapter movieImageVerticalAdapter;
    Context context;
    MovieWithUserDAO movieWithUserDAO;
    UserDAO userDAO;
    MovieDAO movieDAO;
    MovieWithUserDomain movieWithUserDomain;
    UserDomain user;

    public DetailMovieActivity() {
        this.retrofit = RetrofitFactory.create(ApiResource.LOKLOK);
        this.theLokLokApiService = retrofit.create(TheLokLokApiService.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        this.movieWithUserDAO = ApplicationDatabase.getInstance(this).movieWithUserDAO();
        this.userDAO = ApplicationDatabase.getInstance(this).userDAO();
        this.movieDAO = ApplicationDatabase.getInstance(this).movieDAO();
        this.user = (UserDomain) Session.get("user");
        initProgressDialog();
        initViewConfig();
        initData();
        initFavorite();
    }

    @Override
    public void onSelected(MovieDetail.EpisodeVo modelMovie) {
        DetailMovieActivity _this = this;
        progressDialog.show();
        theLokLokApiService.getMovieMedia(movieInfo.getCategory(), movieInfo.getId(), DefinitionCode.GROOT_SD, modelMovie.getId()).enqueue(new Callback<ResponseObject<MovieMedia>>() {
            @Override
            public void onResponse(Call<ResponseObject<MovieMedia>> call, Response<ResponseObject<MovieMedia>> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    assert response.body() != null;
                    MovieMedia movieMedia = response.body().getData();
                    MovieDetail.Subtitling subtitling = modelMovie.getSubtitlingList().stream().filter(x -> x.getLanguageAbbr().equals("vi")).findFirst().orElse(null);
                    if (null == subtitling) {
                        subtitling = modelMovie.getSubtitlingList().stream().filter(x -> x.getLanguageAbbr().equals("en")).findFirst().orElse(null);
                    }
                    Intent intent = new Intent(_this, PlayerActivity.class);
                    intent.putExtra("movieMedia", movieMedia);
                    intent.putExtra("subtitling", subtitling);
                    _this.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseObject<MovieMedia>> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage(), t);
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onSelected(MovieInfoDomain modelMovie) {
        Intent intent = new Intent(this, DetailMovieActivity.class);
        RegexUntils.SplitJumpAddress movieInfo = RegexUntils.splitJumpAddress(modelMovie.getJumpAddress());
        intent.putExtra("movieInfo", movieInfo);
        this.startActivity(intent);
    }

    private void addFavorite() {
        imgFavorite.setOnFavoriteAnimationEndListener(new MaterialFavoriteButton.OnFavoriteAnimationEndListener() {
            @Override
            public void onAnimationEnd(MaterialFavoriteButton buttonView, boolean favorite) {
                // UserId = 4d26d827-aa84-41e5-8c91-16ba1842d8b7
                // movieId = 29446
//                UserDomain user = new UserDomain().setId(UserDomain.generateID()).setEmail("longnv@fpt.edu.vn").setPassword("admin123").setName("Nguyen Van Long");
//                ApplicationDatabase.getInstance(DetailMovieActivity.this.getApplicationContext()).userDAO().insertAll(user);
                if (favorite) {
                    ApplicationDatabase.getInstance(DetailMovieActivity.this.getApplicationContext()).movieDAO().insertAll(MovieInfoDomain.of(movieDetail));
                    MovieWithUserDomain movieFavoriteDomain = new MovieWithUserDomain()
                            .setMovieID(movieDetail.getId())
                            .setUserID(user.getId())
                            .setTimeUpdate(Calendar.getInstance().getTimeInMillis())
                            .setType(MovieUserType.FAVORITE.toString());
                    movieWithUserDAO.insertAll(movieFavoriteDomain);
                } else {
                    MovieWithUserDomain movieFavoriteDomain = movieWithUserDAO.getByUserIDAndMovieId(user.getId(), movieDetail.getId());
                    movieWithUserDAO.delete(movieFavoriteDomain);
                }
                List<MovieWithUserDomain> list = ApplicationDatabase.getInstance(DetailMovieActivity.this.getApplicationContext()).movieWithUserDAO().getAll();
                Toast.makeText(DetailMovieActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initEpisodeView() {
        if (movieDetail.getCategory() == 0) {
            rvEpisode.setLayoutManager(new GridLayoutManager(this, 1));
        } else {
            rvEpisode.setLayoutManager(new GridLayoutManager(this, 5));
        }
        rvEpisode.setHasFixedSize(true);
        RecycleViewUtils.setup(episodeAdapter, rvEpisode, movieAdapter -> new EpisodeAdapter(DetailMovieActivity.this, movieDetail.getEpisodeVo(), this));
    }

    private void initActorView() {
        rvActor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvActor.setHasFixedSize(true);
        List<MovieStarDomain> items = movieDetail.getMovieStarList().stream().map(MovieStarDomain::of).collect(Collectors.toList());
        RecycleViewUtils.setup(movieImageCircleAdapter, rvActor, movieAdapter -> new MovieImageCircleAdapter(this, items));
    }

    private void initMovieSimilarView() {
        rvSimilar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvSimilar.setHasFixedSize(true);
        List<MovieInfoDomain> items = movieDetail.getMovieSimilarList().stream().map(MovieInfoDomain::of).collect(Collectors.toList());
        RecycleViewUtils.setup(movieImageVerticalAdapter, rvSimilar, movieAdapter -> new MovieImageVerticalAdapter(this, items, this));
    }

    private void initViewConfig() {
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        this.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        this.getWindow().setStatusBarColor(Color.TRANSPARENT);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (movieWithUserDomain != null) {
            imgFavorite.setFavorite(true);
        }
    }

    private void initData() {
        this.movieInfo = (RegexUntils.SplitJumpAddress) getIntent().getSerializableExtra("movieInfo");
        progressDialog.show();
        if (movieInfo != null) {
            theLokLokApiService.getMovieDetail(movieInfo.getCategory(), movieInfo.getId()).enqueue(new Callback<ResponseObject<MovieDetail>>() {
                @Override
                public void onResponse(Call<ResponseObject<MovieDetail>> call, Response<ResponseObject<MovieDetail>> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        movieDetail = response.body().getData();
                        initViewData();
                        initEpisodeView();
                        initActorView();
                        initMovieSimilarView();
                        addFavorite();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ResponseObject<MovieDetail>> call, Throwable t) {
                    Log.e(TAG, t.getLocalizedMessage(), t);
                    progressDialog.dismiss();
                }
            });
        }
    }

    void initFavorite() {
        movieWithUserDomain = movieWithUserDAO.getByUserIDAndMovieId(user.getId(), String.valueOf(movieInfo.getId()));
        imgFavorite.setFavorite(movieWithUserDomain != null);
    }

    private void initViewData() {
        tvTitle.setText(movieDetail.getName());
        tvName.setText(movieDetail.getName());
        tvRating.setText(String.valueOf(movieDetail.getScore()).concat("/10"));
        tvOverview.setText(movieDetail.getIntroduction());
        tvTitle.setSelected(true);
        tvName.setSelected(true);

        float newValue = (float) movieDetail.getScore();
        ratingBar.setNumStars(5);
        ratingBar.setStepSize((float) 0.5);
        ratingBar.setRating(newValue / 2);

        Glide.with(this)
                .load(movieDetail.getCoverHorizontalUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgCover);

        Glide.with(this)
                .load(movieDetail.getCoverVerticalUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgPhoto);
    }


    @OnClick(R.id.fabShare)
    public void onFabShareClick() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String subject = movieDetail.getName();
        String description = movieDetail.getIntroduction();
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(Intent.createChooser(shareIntent, "Share with :"));
    }


    private void initProgressDialog() {
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setTitle("Loading ...");
        this.progressDialog.setCancelable(false);
        this.progressDialog.setMessage("The system is loading trailer data...");
    }


    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        window.setAttributes(winParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
