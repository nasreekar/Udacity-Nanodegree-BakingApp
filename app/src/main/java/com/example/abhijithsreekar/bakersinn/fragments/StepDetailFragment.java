package com.example.abhijithsreekar.bakersinn.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhijithsreekar.bakersinn.R;
import com.example.abhijithsreekar.bakersinn.models.RecipeStep;
import com.example.abhijithsreekar.bakersinn.utils.Constants;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailFragment extends Fragment {

    private RecipeStep stepsModel;

    @BindView(R.id.tv_stepDescription)
    TextView stepDescription;

    @BindView(R.id.tv_video_not_available)
    TextView videoNotAvailable;

    @BindView(R.id.player_stepDetail)
    PlayerView mPlayerView;

    private SimpleExoPlayer player;

    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private Uri uri;

    public StepDetailFragment() {
        // Required empty public constructor
    }

    public static StepDetailFragment newInstance(RecipeStep recipeStep) {
        StepDetailFragment fragment = new StepDetailFragment();
        Bundle args = new Bundle();
        // save recipe details into fragment
        args.putParcelable(Constants.BUNDLE_STEP_INFO, recipeStep);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // restore saved state if any
        if (savedInstanceState != null) {
            playWhenReady = savedInstanceState.getBoolean(Constants.PLAYER_READY_STATUS);
            currentWindow = savedInstanceState.getInt(Constants.PLAYER_CURRENT_BLOCK);
            playbackPosition = savedInstanceState.getLong(Constants.PLAYER_PLAYBACK_POSITION);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        // retrieve recipe details saved as fragment arguments
        if (getArguments() != null) {
            stepsModel = getArguments().getParcelable(Constants.BUNDLE_STEP_INFO);
        }

        // retrieve saved state if any
        if (savedInstanceState != null) {
            if (mPlayerView == null) {
                stepsModel = savedInstanceState.getParcelable(Constants.SAVEINSTANCESTATE_STEP_INFO);
            }
        }
        displayRecipeDetails();
    }

    private void displayRecipeDetails() {

        if (stepsModel != null) {
            if (stepDescription != null) {
                stepDescription.setText(stepsModel.getDescription());
            }

            // display a default image in case of an invalid video/thumbnail url
            if (stepsModel.getVideoURL() == null || TextUtils.isEmpty(stepsModel.getVideoURL())) {
                mPlayerView.setVisibility(View.GONE);
                videoNotAvailable.setVisibility(View.VISIBLE);
                if (stepsModel.getThumbnailURL() != null &&
                        !TextUtils.isEmpty(stepsModel.getThumbnailURL()) &&
                        !stepsModel.getThumbnailURL().contains(".mp4")) {
                }
            } else {
                mPlayerView.setVisibility(View.VISIBLE);
                videoNotAvailable.setVisibility(View.GONE);
            }

            uri = Uri.parse(stepsModel.getVideoURL());
        }
        initializePlayer();
    }

    private void initializePlayer() {

        // set step description as title for mobile screens
        if (getActivity() != null && stepsModel != null) {
            getActivity().setTitle(stepsModel.getShortDescription());
        }

        // initialize player
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(getContext(),
                    new DefaultRenderersFactory(getContext()),
                    new DefaultTrackSelector(),
                    new DefaultLoadControl());
        }

        mPlayerView.setPlayer(player);

        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory(Util.getUserAgent(getContext(),
                        getString(R.string.app_name))))
                .createMediaSource(uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();

            // save player state and current recipe step position
            outState.putBoolean(Constants.PLAYER_READY_STATUS, playWhenReady);
            outState.putInt(Constants.PLAYER_CURRENT_BLOCK, currentWindow);
            outState.putLong(Constants.PLAYER_PLAYBACK_POSITION, playbackPosition);
            outState.putParcelable(Constants.SAVEINSTANCESTATE_STEP_INFO, stepsModel);
        }
    }
}
