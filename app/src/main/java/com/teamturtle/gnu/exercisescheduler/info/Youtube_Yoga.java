package com.teamturtle.gnu.exercisescheduler.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamturtle.gnu.exercisescheduler.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Youtube_Yoga extends YouTubeBaseActivity {

    YouTubePlayerView youtubeView;
    Button button;
    YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_yoga);

        button = (Button) findViewById(R.id.youtube_button);
        youtubeView = (YouTubePlayerView) findViewById(R.id.youtubeView);
        Intent intent = getIntent();

        String data = intent.getStringExtra("data");

        if (data.equals("yoga_shoulder")) {//요가

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("0Qhb9w6GKbY");//원하는 유투브 동영상 주소뒤에 소스
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    youtubeView.initialize("AIzaSyDSABVKsfLOYCnBPZKA_A1-lmeFjb3nz78", listener);//api키 복사사
                }
            });

        }

        else if (data.equals("yoga_arm")) {//요가

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("Ki1HTrr8ogY");//원하는 유투브 동영상 주소뒤에 소스
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    youtubeView.initialize("AIzaSyDSABVKsfLOYCnBPZKA_A1-lmeFjb3nz78", listener);//api키 복사사
                }
            });

        }

        else if (data.equals("yoga_back")) {//요가

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("MPrR-26AEAA");//원하는 유투브 동영상 주소뒤에 소스
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    youtubeView.initialize("AIzaSyDSABVKsfLOYCnBPZKA_A1-lmeFjb3nz78", listener);//api키 복사사
                }
            });

        }

        else if (data.equals("yoga_waist")) {//요가

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("Z6Cen23cZRc");//원하는 유투브 동영상 주소뒤에 소스
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    youtubeView.initialize("AIzaSyDSABVKsfLOYCnBPZKA_A1-lmeFjb3nz78", listener);//api키 복사사
                }
            });

        }

        else if (data.equals("yoga_stomach")) {//요가

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("pd0KhIQbqQk");//원하는 유투브 동영상 주소뒤에 소스
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    youtubeView.initialize("AIzaSyDSABVKsfLOYCnBPZKA_A1-lmeFjb3nz78", listener);//api키 복사사
                }
            });

        }
        else if (data.equals("yoga_thigh")) {//요가

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("SxDyurZLgt8");//원하는 유투브 동영상 주소뒤에 소스
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    youtubeView.initialize("AIzaSyDSABVKsfLOYCnBPZKA_A1-lmeFjb3nz78", listener);//api키 복사사
                }
            });

        }

        else if (data.equals("yoga_hip")) {//요가

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("bv-28fkErMQ");//원하는 유투브 동영상 주소뒤에 소스
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    youtubeView.initialize("AIzaSyDSABVKsfLOYCnBPZKA_A1-lmeFjb3nz78", listener);//api키 복사사
                }
            });

        }

        else if (data.equals("yoga_calf")) {//요가

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("1Jew92mrKMU");//원하는 유투브 동영상 주소뒤에 소스
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    youtubeView.initialize("AIzaSyDSABVKsfLOYCnBPZKA_A1-lmeFjb3nz78", listener);//api키 복사사
                }
            });

        }

    }
}
