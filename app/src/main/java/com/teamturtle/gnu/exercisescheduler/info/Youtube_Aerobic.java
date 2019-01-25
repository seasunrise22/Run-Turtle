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


public class Youtube_Aerobic extends YouTubeBaseActivity {

    YouTubePlayerView youtubeView;
    Button button;
    YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_aerobic);

        button = (Button) findViewById(R.id.youtube_button);
        youtubeView = (YouTubePlayerView) findViewById(R.id.youtubeView);
        Intent intent = getIntent();

        String data = intent.getStringExtra("data");

        if (data.equals("aerobic_shoulder")) {//유산소

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("cAK_zQG_27w");//원하는 유투브 동영상 주소뒤에 소스
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


        else   if (data.equals("aerobic_arm")) {//유산소

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("MO87_yr7pjo&list=PLWIVFNp1wOLTzmgY9jAzwp2CjwCXFgj22");//원하는 유투브 동영상 주소뒤에 소스
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

        else   if (data.equals("aerobic_back")) {//유산소

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("ApEyMu6SwuA");//원하는 유투브 동영상 주소뒤에 소스
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

        else   if (data.equals("aerobic_waist")) {//유산소

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("jpTQdM7okkI");//원하는 유투브 동영상 주소뒤에 소스
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

        else   if (data.equals("aerobic_stomach")) {//유산소

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("uviX5Z7Q85I");//원하는 유투브 동영상 주소뒤에 소스
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

        else   if (data.equals("aerobic_thigh")) {//유산소

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("laglzuyfeKA");//원하는 유투브 동영상 주소뒤에 소스
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

        else   if (data.equals("aerobic_hip")) {//유산소

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("rwVzamFR-tg&list=PLGvdnS1_x693yYEgl28hG62Vg82fwc-a9");//원하는 유투브 동영상 주소뒤에 소스
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

        else   if (data.equals("aerobic_calf")) {//유산소

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("pShGgDH16KE");//원하는 유투브 동영상 주소뒤에 소스
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
