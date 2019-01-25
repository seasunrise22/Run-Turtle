package com.teamturtle.gnu.exercisescheduler.scheduler;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.teamturtle.gnu.exercisescheduler.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


public class CheckList extends AppCompatActivity {

    /**
     * 변수 선언
     */
    ArrayList<String> Items;
    ArrayAdapter<String> Adapter;
    ListView list;
    ProgressBar mprogbar;
    int percentage;

    boolean checkSuccess; // 토스트 계속 뜨니까 한번만 뜨도록...

    int date;

    Calendar tCalendar; // 실제 오늘 날짜 계산용 Calendar 변수



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment2_checklist);
    }

    //============ DB관련 onCreate가 아닌 onResume 에다가 구현. 좀 더 안정적. ==============//
    @Override
    protected void onResume() {
        super.onResume();

        /**
         * 변수 구현
         */
        final EditText etName = (EditText) findViewById(R.id.newitem); // 운동 입력받기
        final Button bRegister = (Button) findViewById(R.id.add); // 추가 버튼

        Items = new ArrayList<>(); // 운동 담을 리스트 배열

        mprogbar = (ProgressBar) findViewById(R.id.progress);

        Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, Items); // 어댑터
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(Adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setOnItemClickListener(mItemClickListener);
        list.setOnItemLongClickListener(mItemLongClickListener);

        //=================== 디비에서 운동목록 받아와서 리스트뷰에 뿌려주고 인덱스 정리 ===============//
        Items.clear();
        checkSuccess = false;
        for(int i=0; i<10; i++) { // 운동목록은 10개 까지만
            Response.Listener<String> responseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if (success) { // 디비에서 운동명(name) 받아와서 Items에 add
                            String name = jsonResponse.getString("name"); // 리스트뷰에 달아줄 운동명
                            Items.add(name);

                            if(!checkSuccess) { // checkSuccess가 false일 경우에만 토스트가 뜸. 즉, 디비 접속 성공하면 한번만 호출.
                                Toast.makeText(CheckList.this, "DB에서 운동 받아오기 success", Toast.LENGTH_SHORT).show();
                                checkSuccess = true;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            ListRetrieve listRetrieve = new ListRetrieve((i+1), responseListener);
            RequestQueue queue = Volley.newRequestQueue(CheckList.this);
            queue.add(listRetrieve);
        }
        //=================== 디비에서 운동목록 받아와서 리스트뷰에 뿌려주고 인덱스 정리 끝 ===============//

        //=================== 운동 리스트뷰에 추가하고 디비에도 추가 ===============//
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String inputName = etName.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Toast.makeText(CheckList.this, "디비 삽입 success", Toast.LENGTH_SHORT).show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(CheckList.this);
                                builder.setMessage("Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                InputRequest inputRequest = new InputRequest(inputName, responseListener);
                RequestQueue queue = Volley.newRequestQueue(CheckList.this);
                queue.add(inputRequest);
                etName.setText("");
                Items.add(inputName);
                Adapter.notifyDataSetChanged();
            }
        });
        //=================== 운동 리스트뷰에 추가하고 디비에도 추가 끝 ===============//
    }
    //============ DB관련 onCreate가 아닌 onResume 에다가 구현. 좀 더 안정적. 끝. ==============//

    //=========== 하루끝 버튼 ================//
    public void mOnClick(View v) {

        switch (v.getId()) {

            case R.id.done:

                tCalendar = Calendar.getInstance();
                date = tCalendar.get(Calendar.DAY_OF_MONTH);
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("하루를 평가하세요!");

                final EditText etMemo = new EditText(this);
                alert.setView(etMemo);


                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        final String MText = etMemo.getText().toString();

                        Response.Listener<String> responseListener = new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {
                                    Log.e("json", response);
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {

                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(CheckList.this);
                                        builder.setMessage("Writing Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        };

                        MemoRequest memoRequest = new MemoRequest(MText,percentage, date,responseListener);
                        RequestQueue queue = Volley.newRequestQueue(CheckList.this);
                        queue.add(memoRequest);
                    }
                });
                alert.setNegativeButton("no",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();

                percentage = (list.getCheckedItemCount() * 100) / list.getCount();
                list.clearChoices();
                Items.clear();
                Adapter.notifyDataSetChanged();
                mprogbar.setProgress(0);
                TextView zero=(TextView)findViewById(R.id.total);
                zero.setText("0%");
        break;
        }
    }
    //=========== 하루끝 버튼 ================//

    //=============== 지우면 디비에도 지워지도록 =========================//
    public AdapterView.OnItemLongClickListener mItemLongClickListener =
            new AdapterView.OnItemLongClickListener() {
                @Override
                public  boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
                    String deleteName = Items.get(position); // 지울 운동 이름 get

                        Response.Listener<String> responseListener = new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {
                                        Toast.makeText(CheckList.this, "디비에서도 지워집니다~", Toast.LENGTH_SHORT).show();
                                        }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        DeleteRequest deleteRequest = new DeleteRequest(deleteName, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(CheckList.this);
                        queue.add(deleteRequest);

                    Items.remove(position);
                    list.clearChoices();
                    Adapter.notifyDataSetChanged();
                    return false;
                    }
    };
    //=============== 지우면 디비에도 지워지도록 끝 =========================//


    public AdapterView.OnItemClickListener mItemClickListener =
            new AdapterView.OnItemClickListener() {
                @SuppressWarnings("unchecked")
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    String mes;
                    if(list.getCheckedItemCount()*100/list.getCount()<=30){
                        mes = "50%를 위해 노력하세요 ";
                        Toast.makeText(CheckList.this, mes, Toast.LENGTH_SHORT).show();
                    }
                    if(list.getCheckedItemCount()*100/list.getCount()>30&&list.getCheckedItemCount()*100/list.getCount()<50){
                        mes = "70%를 위해 노력하세요 ";
                        Toast.makeText(CheckList.this, mes, Toast.LENGTH_SHORT).show();
                    }
                    if(list.getCheckedItemCount()*100/list.getCount()>50&&list.getCheckedItemCount()*100/list.getCount()<100){
                        mes = "100%를 위해 노력하세요 ";
                        Toast.makeText(CheckList.this, mes, Toast.LENGTH_SHORT).show();
                    }

                    mprogbar.setProgress(list.getCheckedItemCount()*100/list.getCount());


                    TextView text=(TextView)findViewById(R.id.total);
                    text.setText("진행 상황 :  " + list.getCheckedItemCount()*100/list.getCount() + "%");

                }
            };

}
