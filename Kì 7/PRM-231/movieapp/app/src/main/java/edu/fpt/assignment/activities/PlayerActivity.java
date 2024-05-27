package edu.fpt.assignment.activities;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.google.common.collect.ImmutableList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.fpt.assignment.R;
import edu.fpt.assignment.dto.MovieDetail;
import edu.fpt.assignment.dto.MovieMedia;

public class PlayerActivity extends AppCompatActivity {
    ExoPlayer exoPlayer;

    @BindView(R.id.player)
    PlayerView playerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.btnLockScreen)
    ImageView btnLockScreen;

    @BindView(R.id.btnFullScreen)
    ImageView btnFullScreen;

    private MovieMedia movieMedia;

    private MovieDetail.Subtitling subtitling;

    //at 4 second
    long ads = 4000;
    boolean flagAds = false;
    boolean isFullScreen=false;
    boolean isLock = false;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
        this.movieMedia = (MovieMedia) getIntent().getSerializableExtra("movieMedia");
        this.subtitling = (MovieDetail.Subtitling) getIntent().getSerializableExtra("subtitling");
        handler = new Handler(Looper.getMainLooper());
        initView();
        initAction();
        initVideo();
    }
    private Runnable updateProgressAction = () -> onProgress();

    private void initView() {
        //instance the player with skip back duration 5 second or forward 5 second
        //5000 millisecond = 5 second
        exoPlayer = new ExoPlayer.Builder(this)
                .setSeekBackIncrementMs(5000)
                .setSeekForwardIncrementMs(5000)
                .build();
        playerView.setPlayer(exoPlayer);
        //screen alway active
        playerView.setKeepScreenOn(true);
    }

    private void initVideo () {
        //pass the video link and play
        MediaItem.SubtitleConfiguration subtitle = null;
        if (subtitling == null) {
            // do not thing
        } else {
            Uri subtitleUrl = Uri.parse((subtitling.getSubtitlingUrl()));
            subtitle = new MediaItem.SubtitleConfiguration.Builder(subtitleUrl)
                    .setMimeType(MimeTypes.APPLICATION_SUBRIP)
                    .setLanguage(subtitling.getLanguageAbbr())
                    .setSelectionFlags(C.SELECTION_FLAG_DEFAULT)
                    .build();
        }

        Uri mediaUrl = Uri.parse(movieMedia.getMediaUrl());
        MediaItem media;
        if (subtitle != null) {
            media = new MediaItem.Builder()
                    .setUri(mediaUrl)
                    .setSubtitleConfigurations(ImmutableList.of(subtitle))
                    .build();
        } else {
            media = new MediaItem.Builder()
                    .setUri(mediaUrl)
                    .build();
        }

        exoPlayer.setMediaItem(media);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);
    }

    private void initAction() {
        onExoPlayerPlayEvents();
    }

    private void onExoPlayerPlayEvents () {
        //track state
        exoPlayer.addListener(new Player.Listener()
        {
            @Override
            public void onPlaybackStateChanged(int playbackState)
            {
                //when data video fetch stream from internet
                if (playbackState == Player.STATE_BUFFERING)
                {
                    progressBar.setVisibility(View.VISIBLE);

                } else if (playbackState == Player.STATE_READY) {
                    //then if streamed is loaded we hide the progress bar
                    progressBar.setVisibility(View.GONE);
                }

                if(!exoPlayer.getPlayWhenReady())
                {
                    handler.removeCallbacks(updateProgressAction);
                }
                else
                {
                    onProgress();
                }
            }
        });
    }

    @OnClick(R.id.btnFullScreen)
    public void onBtnFullScreenOnclick () {
        //toggle button with change icon fullscreen or exit fullscreen
        //the screen can rotate base on you angle direction sensor
        if (!isFullScreen)
        {
            btnFullScreen.setImageDrawable(
                    ContextCompat
                            .getDrawable(getApplicationContext(), R.drawable.ic_baseline_fullscreen_exit));
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else
        {
            btnFullScreen.setImageDrawable(ContextCompat
                    .getDrawable(getApplicationContext(), R.drawable.ic_baseline_fullscreen));
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        isFullScreen = !isFullScreen;
    }

    @OnClick(R.id.btnLockScreen)
    public void onBtnLockScreenOnclick () {
        //change icon base on toggle lock screen or unlock screen
        if (!isLock)
        {
            btnLockScreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_lock));
        } else
        {
            btnLockScreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_outline_lock_open));
        }
        isLock = !isLock;
        //method for toggle will do next
        lockScreen(isLock);
    }


    private void onProgress() {
        ExoPlayer player= exoPlayer;
        long position = player == null? 0 : player.getCurrentPosition();
        handler.removeCallbacks(updateProgressAction);
        int playbackState = player ==null? Player.STATE_IDLE : player.getPlaybackState();
        if(playbackState != Player.STATE_IDLE && playbackState!= Player.STATE_ENDED)
        {
            long delayMs ;
            if(player.getPlayWhenReady() && playbackState == Player.STATE_READY)
            {
                delayMs  = 1000 - position % 1000;
                if(delayMs < 200)
                {
                    delayMs+=1000;
                }
            }
            else{
                delayMs = 1000;
            }

            //check to display ad
            if((ads-3000 <= position && position<=ads) &&!flagAds)
            {
                flagAds =true;
                initAds();
            }
            handler.postDelayed(updateProgressAction,delayMs);
        }
    }

    RewardedInterstitialAd rewardedInterstitialAd=null;
    private void initAds() {
        if(rewardedInterstitialAd!=null) return ;
        MobileAds.initialize(this);
        RewardedInterstitialAd.load(this, "ca-app-pub-3940256099942544/5354046379",
                new AdRequest.Builder().build(), new RewardedInterstitialAdLoadCallback(){
                    @Override
                    public void onAdLoaded(@NonNull RewardedInterstitialAd p0)
                    {
                        rewardedInterstitialAd = p0;
                        rewardedInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback()
                        {
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError)
                            {
                                Log.d("WatchActivity_AD", adError.getMessage());
                            }

                            @Override
                            public void onAdShowedFullScreenContent()
                            {
                                handler.removeCallbacks(updateProgressAction);
                            }

                            @Override
                            public void onAdDismissedFullScreenContent()
                            {
                                //resume play
                                exoPlayer.setPlayWhenReady(true);
                                rewardedInterstitialAd = null;
                                flagAds = false;
                            }
                        });
                        LinearLayout sec_ad_countdown = findViewById(R.id.sect_ad_countdown);
                        TextView tx_ad_countdown = findViewById(R.id.tx_ad_countdown);
                        sec_ad_countdown.setVisibility(View.VISIBLE);
                        new CountDownTimer(4000,1000)
                        {
                            @Override
                            public void onTick(long l)
                            {
                                tx_ad_countdown.setText("Ads in "+l/1000);
                            }

                            @Override
                            public void onFinish()
                            {
                                sec_ad_countdown.setVisibility(View.GONE);
                                rewardedInterstitialAd.show(PlayerActivity.this, rewardItem ->
                                {

                                });
                            }
                        }.start();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError)
                    {
                        rewardedInterstitialAd = null;
                    }
                });
    }

    void lockScreen(boolean lock) {
        //just hide the control for lock screen and vise versa
        LinearLayout sec_mid = findViewById(R.id.sec_controlvid1);
        LinearLayout sec_bottom = findViewById(R.id.sec_controlvid2);
        if(lock)
        {
            sec_mid.setVisibility(View.INVISIBLE);
            sec_bottom.setVisibility(View.INVISIBLE);
        }
        else
        {
            sec_mid.setVisibility(View.VISIBLE);
            sec_bottom.setVisibility(View.VISIBLE);
        }
    }

    //when is in lock screen we not accept for backpress button
    @Override
    public void onBackPressed() {
        //on lock screen back press button not work
        if(isLock) return;

        //if user is in landscape mode we turn to portriat mode first then we can exit the app.
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            btnFullScreen.performClick();
        }
        else super.onBackPressed();
    }

    // pause or release the player prevent memory leak
    @Override
    protected void onStop() {
        super.onStop();
        exoPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoPlayer.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        exoPlayer.pause();
    }
}