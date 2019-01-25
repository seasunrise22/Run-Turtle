package com.teamturtle.gnu.exercisescheduler.gps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.teamturtle.gnu.exercisescheduler.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 주소 검색 기록 display 하는 액티비티
 */
public class RetrieveAddrActivity extends Activity {

    /**
     * 변수 선언
     */
    ArrayList<String> mItems; // 주소 넣을 ArrayList 배열
    ArrayAdapter<String> mAdapter; // 리스트뷰랑 연결할 ArrayAdapter
    ListView mListView; // 리스트뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieveaddress);

        //================= 뒤로가기 버튼 ======================//
        Button btnFinishRetAddr = (Button) findViewById(R.id.btn_finishRetAddr);
        btnFinishRetAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 자꾸 터져서 핸들러 사용.
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(RetrieveAddrActivity.this, Fragment1_Address.class);
                        startActivity(intent);
                        finish();
                    }
                }, 200);
            }
        });
        //================= 뒤로가기 버튼 끝 ======================//
    }

    //============== 디비에서 가져오는 걸 onResume 에다가 구현하면?? 좀 더 안정적? ==================//
    @Override
    protected void onResume() {
        super.onResume();

        /**
         * 변수 구현
         */
        mItems = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItems);
        mListView = (ListView) findViewById(R.id.addrListView);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mItemClickListener); // 주소 선택하면 반응하는 리스너 달아줌.

        //================= 데이터베이스에서 주소 가져오기 시작 ======================//
        mItems.clear();
        for (int i = 0; i < 10; i++) {
            Response.Listener<String> rResponseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if (success) {
                            // 가져온 주소값을 mItems에 삽입
                            String addrName = jsonResponse.getString("addrName");
                            mItems.add(addrName);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            RetrieveAddrRequest retrieveAddrRequest = new RetrieveAddrRequest((i+1), rResponseListener);
            RequestQueue queue = Volley.newRequestQueue(RetrieveAddrActivity.this);
            queue.add(retrieveAddrRequest);
        }
        //================= 데이터베이스에서 주소 가져오기 끝 ======================//
    }
    //============== 디비에서 가져오는 걸 onResume 에다가 구현하면?? 좀 더 안정적? 끝 ==================//

    //=========== 주소 선택하면 반응하는 리스너 구현 ==========================//
    public AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    final String getAddr = mItems.get(position); // 아이템에서 주소 가져옴.

                    //=================== 데이터베이스에서 주소 지우기 =================//
                    Response.Listener<String> responseListener = new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    Toast.makeText(RetrieveAddrActivity.this, "주소 선택 완료", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    DeleteAddrRequest deleteAddrRequest = new DeleteAddrRequest(getAddr, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RetrieveAddrActivity.this);
                    queue.add(deleteAddrRequest);
                    //=================== 데이터베이스에서 주소 지우기 끝 =================//

                    mItems.remove(position);
                    // 자꾸 터져서 핸들러 사용.
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(RetrieveAddrActivity.this, Fragment1_Address.class);
                            intent.putExtra("addrName", getAddr);
                            startActivity(intent);
                            finish();
                        }
                    }, 200);
                }
            };
    //=========== 주소 선택하면 반응하는 리스너 구현 끝 ==========================//
}

