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


public class Youtube_Muscular extends YouTubeBaseActivity {

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

        if (data.equals("muscular_shoulder")) {//근력

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("wI_2Amaqt1Y");//원하는 유투브 동영상 주소뒤에 소스
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

        else if (data.equals("muscular_arm")) {

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("PMgGUwdd_B4");//원하는 유투브 동영상 주소뒤에 소스
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

        else if (data.equals("muscular_back")) {

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("SZ_8HOHHs04");//원하는 유투브 동영상 주소뒤에 소스
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

        else if (data.equals("muscular_waist")) {

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("akG7rGEmdRU");//원하는 유투브 동영상 주소뒤에 소스
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

        else if (data.equals("muscular_stomach")) {

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("L9433hhTlkQ");//원하는 유투브 동영상 주소뒤에 소스
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

        else if (data.equals("muscular_thigh")) {

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("hHRdw8RLCXg");//원하는 유투브 동영상 주소뒤에 소스
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

        else if (data.equals("muscular_hip")) {

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("B3RI3Rkfmnk");//원하는 유투브 동영상 주소뒤에 소스
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

        else if (data.equals("muscular_calf")) {

            listener = new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("nb0sEvEsN3o");//원하는 유투브 동영상 주소뒤에 소스
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

