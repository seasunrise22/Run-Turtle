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


public class Youtube_Stretching extends  YouTubeBaseActivity {


    YouTubePlayerView youtubeView;
    Button button;
    YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_stretching);

        button = (Button) findViewById(R.id.youtube_button);
        youtubeView = (YouTubePlayerView) findViewById(R.id.youtubeView);
        Intent intent = getIntent();

        String data = intent.getStringExtra("data");

        if (data.equals("stretching_shoulder")) {//스트레칭

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("ZcsUQD5sloc");//원하는 유투브 동영상 주소뒤에 소스
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

       else if (data.equals("stretching_arm")) {//스트레칭

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("bjNE_xhN_3Q");//원하는 유투브 동영상 주소뒤에 소스
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

        else if (data.equals("stretching_back")) {//스트레칭

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("uMMkBNevhis");//원하는 유투브 동영상 주소뒤에 소스
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

        else if (data.equals("stretching_waist")) {//스트레칭

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

        else if (data.equals("stretching_stomach")) {//스트레칭

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("5FlEOwPIxEY");//원하는 유투브 동영상 주소뒤에 소스
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

        else if (data.equals("stretching_thigh")) {//스트레칭

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("L87-LHsNam8");//원하는 유투브 동영상 주소뒤에 소스
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

        else if (data.equals("stretching_hip")) {//스트레칭

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("7sQk-wXxTuY");//원하는 유투브 동영상 주소뒤에 소스
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

        else if (data.equals("stretching_calf")) {//스트레칭

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("mYHKul4fnac");//원하는 유투브 동영상 주소뒤에 소스
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