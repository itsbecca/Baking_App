/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.becca.bakingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.becca.bakingapp.org.RecipeStepClass;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class RecipeMediaFragment extends Fragment {

    private SimpleExoPlayer mPlayer;
    private SimpleExoPlayerView mPlayerView;

    private long mPlaybackPosition;
    private int mCurrentWindow;
    private boolean mPlayWhenReady = true;

    Uri mVideoUri;
    Uri mImageUri;
    Boolean playVideo = false;

    private ImageView mImageView;
    private Context mContext;

    public RecipeMediaFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_media, container, false);
        mPlayerView = rootView.findViewById(R.id.video_view);
        mImageView = rootView.findViewById(R.id.photo_view);

        Bundle bundle = this.getArguments();
        RecipeStepClass currentStep = bundle.getParcelable("StepKey"); //TODO static
        mVideoUri = currentStep.getVideoUri();
        mImageUri = currentStep.getThumbnail(); //todo Image display not working. Use Picasso?

        if (!Uri.EMPTY.equals(mVideoUri) && mVideoUri != null) {
            playVideo = true;
            mPlayerView.setVisibility(View.VISIBLE);
        } else {
            mPlayerView.setVisibility(View.GONE);
            playVideo = false;
        }
        //todo account for images and incorrect mime types

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() { //todo if having fullscreen products look up codelabs example project
        super.onResume();
        if ((Util.SDK_INT <= 23 || mPlayer == null)) {
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

    private void initializePlayer() {
        if (playVideo) {
            mPlayerView.setVisibility(View.VISIBLE);

            mPlayer = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(mContext),
                new DefaultTrackSelector(), new DefaultLoadControl());

            mPlayerView.setPlayer(mPlayer);
            mPlayer.setPlayWhenReady(mPlayWhenReady);
            mPlayer.seekTo(mCurrentWindow, mPlaybackPosition);

            MediaSource mediaSource = buildMediaSource(mVideoUri);
            mPlayer.prepare(mediaSource, true, false);
        }
    }

    private void releasePlayer() {
        if (mPlayer != null) {
            mPlaybackPosition = mPlayer.getCurrentPosition();
            mCurrentWindow = mPlayer.getCurrentWindowIndex();
            mPlayWhenReady = mPlayer.getPlayWhenReady();
            mPlayer.release();
            mPlayer = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory("ua"),
                new DefaultExtractorsFactory(), null, null);
    }
}
