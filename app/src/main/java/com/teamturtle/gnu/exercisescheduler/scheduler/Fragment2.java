package com.teamturtle.gnu.exercisescheduler.scheduler;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.teamturtle.gnu.exercisescheduler.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import static com.teamturtle.gnu.exercisescheduler.MainActivity.mContext;

/**
 * 그리드뷰를 이용해 월별 캘린더
 *
 */

public class Fragment2 extends Fragment {

    /**
     * 월별 캘린더 뷰 객체
     */
    GridView monthView;

    /**
     * 월별 캘린더 어댑터
     */
    MonthAdapter monthViewAdapter;

    /**
     * 월을 표시하는 텍스트뷰
     */
    TextView monthText;

    TextView checkList;
    TextView checkListMemo;
    /**
     * 현재 연도
     */
    int curYear;

    int curTDay; // 오늘 실제 며칠이냐?

    int day;
    int position;

    int posi=0;
    /**
     * 현재 월
     */
    int curMonth;

    Calendar tCalendar; // 실제 오늘 날짜 계산용 Calendar 변수


    public int getTCurDay() {
        return curTDay;
    } // tCalendar(실제시간)로 계산

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2_main, container, false);

        // 월별 캘린더 뷰 객체 참조
        monthView = (GridView) rootView.findViewById(R.id.monthView);
        monthViewAdapter = new MonthAdapter(mContext); // 이때 Items가 만들어지는 듯
        monthView.setAdapter(monthViewAdapter);

        // 리스너 설정
        monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // 현재 선택한 일자 정보 표시
                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                // MonthAdapter 클래스의 selectedPositon에 현재 선택한 Item의 position을 넘겨줌
                monthViewAdapter.setSelectedPosition(position);

                posi=1;
                day = curItem.getDay();

                monthViewAdapter.notifyDataSetChanged(); // 터치한 즉시 화면 업데이트
                setCheckList();
                Log.d("MainActivity", "Selected : " + day );
                //  Log.d("MainActivity", "Selected : " + month );

            }
        });

        monthText = (TextView) rootView.findViewById(R.id.monthText);
        setMonthText();
        checkList= (TextView) rootView.findViewById(R.id.checkList);
        checkListMemo = (TextView) rootView.findViewById(R.id.checkListMemo);
        setCheckList();

        // 이전 월로 넘어가는 이벤트 처리
        Button monthPrevious = (Button) rootView.findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
                setCheckList();
            }
        });

        // 다음 월로 넘어가는 이벤트 처리
        Button monthNext = (Button) rootView.findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
                setCheckList();
            }
        });

        curTDay = tCalendar.get(Calendar.DAY_OF_MONTH);

        // 체크리스트 추가 버튼 참조
        Button chkListBtn = (Button) rootView.findViewById(R.id.btnChkList);
        chkListBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if(day==curTDay) {
                    // 자꾸 터져서 핸들러 사용. 0.2초 후에 액티비티 전환.
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getActivity(), CheckList.class);
                            startActivity(intent);
                        }
                    }, 200);
                }
                else
                {
                    String mes;
                    mes = "오늘 날짜를 선택해주세요";
                    Toast.makeText(mContext, mes, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    //체크리스트 날짜 표시
    private void setCheckList() {

        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        tCalendar = Calendar.getInstance(); // 실제 날짜 계산용
        curTDay = tCalendar.get(Calendar.DAY_OF_MONTH);
        checkList.setText(curYear + "년 " + (curMonth + 1) + "월"+ (curTDay)+"일");

        if(posi==1){

            monthViewAdapter.notifyDataSetChanged(); // 터치한 즉시 화면 업데이트

            checkList.setText(curYear + "년 " + (curMonth + 1) + "월"+ (day)+"일");}

        //==================== 디비에서 한줄평이랑 퍼센트 받아와서 날짜별로 뿌려주기 =========//
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if(success) {
                        Toast.makeText(getActivity(), "디비에서 받아오기~", Toast.LENGTH_SHORT).show();
                        String MText = jsonResponse.getString("MText"); // 한줄평 저장용 변수
                        int percentage = jsonResponse.getInt("percentage"); // 퍼센트 저장용 변수
                        checkListMemo.setText("\n한줄평 : " + MText + "\n성취도 : " + percentage);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        CalendarRequest calendarRequest = new CalendarRequest(day, responseListener);
        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(calendarRequest);
        //==================== 디비에서 한줄평이랑 퍼센트 받아와서 날짜별로 뿌려주기 =========//
    }



    /**
     * 월 표시 텍스트 설정
     */
    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth + 1) + "월");
    }
}