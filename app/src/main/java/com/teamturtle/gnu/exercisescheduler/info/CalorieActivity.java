package com.teamturtle.gnu.exercisescheduler.info;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.teamturtle.gnu.exercisescheduler.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static com.teamturtle.gnu.exercisescheduler.R.id.listView;


public class CalorieActivity extends AppCompatActivity {

    private Activity activity;

    String myJSON;

    private static final String TAG_RESULTS="result";
    private  static  final  String TAG_NAME = "name";
    private  static  final  String TAG_CALORIE = "calorie";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calorie);

        findViewById(R.id.button_listshow).setOnClickListener( //먹은음식 버튼클릭
                new Button.OnClickListener() {
                    public void onClick(View v) {
// 다이얼로그 생성
                        Intent intent_act = new Intent(getApplicationContext(),CartActivity.class);
                        startActivity(intent_act);

                    }
            }
        );


        findViewById(R.id.button_calculation).setOnClickListener( //계산 버튼클릭
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),CalculationActivity.class);
                        startActivity(intent_act);
                    }
                }
        );

        list = (ListView) findViewById(listView);
        personList = new ArrayList<HashMap<String,String>>();
        getDbData("http://exercisemanagementapp.iptime.org/showcalorie.php");
        // IP주소 : PHP 파일이 등록된 서버의 IP 주소 및 경로

    }




    private void getDbData(String string) {
        class GetDataJSON extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];
                BufferedReader bufferedReader = null;

                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();

                } catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }

            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(string);

    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String name = c.getString(TAG_NAME);
                String calorie = c.getString(TAG_CALORIE);

                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_NAME,name);
                persons.put(TAG_CALORIE,calorie);

                personList.add(persons);
            }

            // 어댑터 생성, R.layout.calorie_item: Layout ID
            ListAdapter adapter = new SimpleAdapter(
                    CalorieActivity.this, personList, R.layout.calorie_item,
                    new String[]{TAG_NAME,TAG_CALORIE},
                    new int[]{R.id.list_name, R.id.list_calorie}
            );

            list.setAdapter(adapter); // ListView 에 어댑터 설정

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {//리스트뷰 아이템 클릭시
                @Override
                public void onItemClick(final AdapterView<?> adapterView,
                                        final View view, final int position, final long id) {
                    final int text = position + 1;
                    final String index = String.valueOf(text);//형변환

                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(CalorieActivity.this);
                    alert_confirm.setMessage("먹은음식 리스트에 추가하시겠습니까?").setCancelable(false).setPositiveButton("예",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 'YES'

                                    Response.Listener<String> responseListener = new Response.Listener<String>() {

                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject jsonResponse = new JSONObject(response);
                                                boolean success = jsonResponse.getBoolean("success");

                                                if (success) {
                                                    Toast.makeText(CalorieActivity.this,"추가되었습니다.", Toast.LENGTH_SHORT).show();

                                                    String index = jsonResponse.getString("index");
                                                    Intent intent = new Intent(CalorieActivity.this, CalorieRequest.class);
                                                    intent.putExtra("index", index);

                                                    CalorieActivity.this.startActivity(intent);

                                                } else {
                                                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CalorieActivity.this);
                                                    builder.setMessage("에러에러")
                                                            .setNegativeButton("다시입력",null)
                                                            .create()
                                                            .show();
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    };

                                    CalorieRequest  calorieRequest = new CalorieRequest(index,responseListener);
                                    RequestQueue queue = Volley.newRequestQueue(CalorieActivity.this);
                                    queue.add(calorieRequest);

                }
                            }).setNegativeButton("아니오",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT).show();

                                    // 'No'
                                    return;
                                }
                            });
                    AlertDialog alert = alert_confirm.create();
                    alert.show();

                }
            });

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}








