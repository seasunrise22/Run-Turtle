package com.teamturtle.gnu.exercisescheduler.info;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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

import static com.teamturtle.gnu.exercisescheduler.R.id.cartView;

public class CartActivity extends AppCompatActivity {

    String mycartJSON;
    private  static  final String CART_RESULT="result";
    private  static  final  String CART_NAME = "c_name";
    private static  final String CART_NUM="c_num";
    JSONArray cartpeoples = null;

    ArrayList<HashMap<String,String>>personcartList;
    ListView  cartlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        cartlist = (ListView)findViewById(cartView);
        personcartList = new ArrayList<HashMap<String, String>>();
        getDbData("http://exercisemanagementapp.iptime.org/showcart.php");
        // IP주소 : PHP 파일이 등록된 서버의 IP 주소 및 경로

    }

    private void getDbData(String string) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

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
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();

                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }

            }

            protected void onPostExecute(String result) {
                mycartJSON = result;
                showcartList();
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(string);

    }

    protected void showcartList() {
        ListAdapter adapter = null;

        try {
            JSONObject jsonObj = new JSONObject(mycartJSON);
            cartpeoples = jsonObj.getJSONArray(CART_RESULT);

            for (int i = 0; i < cartpeoples.length(); i++) {
                JSONObject c = cartpeoples.getJSONObject(i);
                String c_name = c.getString(CART_NAME);
                String c_num = c.getString(CART_NUM);

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(CART_NAME,c_name);
                persons.put(CART_NUM,c_num);

                personcartList.add(persons);
            }

            // 어댑터 생성, R.layout.calorie_item: Layout ID
                 final ListAdapter Adapter = new SimpleAdapter(
                    CartActivity.this, personcartList, R.layout.cart_item,
                    new String[]{CART_NAME, CART_NUM},
                    new int[]{R.id.cart_name, R.id.cart_num}

            );

            cartlist.setAdapter(Adapter); // ListView 에 어댑터 설정

            cartlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {//리스트뷰 아이템 클릭시
                @Override
                public void onItemClick(final AdapterView<?> adapterView,
                                        final View view, final int position, final long id) {
                    //final int text = position + 1;
                    //final String index = String.valueOf(text);//형변환
                   //final String index = (String)adapterView.getAdapter().getItem(position);
                    final TextView nameindex  =(TextView) adapterView.getChildAt(position).findViewById(R.id.cart_name);

                    final String index = String.valueOf(nameindex.getText());
                    //Toast.makeText(CartActivity.this,index, Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(CartActivity.this);
                    alert_confirm.setMessage("리스트에서 제거하시겠습니까?").setCancelable(false).setPositiveButton("예",
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
                                                    Toast.makeText(CartActivity.this,"제거되었습니다.", Toast.LENGTH_SHORT).show();
                                                    String index = jsonResponse.getString("index");
                                                    Intent intent = new Intent(CartActivity.this, CartDeleteRequest.class);
                                                    intent.putExtra("index", index);
                                                    CartActivity.this.startActivity(intent);
                                                    //새로고침
                                                    //((BaseAdapter) Adapter).notifyDataSetChanged();

                                                } else {
                                                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CartActivity.this);
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

                                    CartDeleteRequest  cartdeleteRequest = new CartDeleteRequest(index,responseListener);
                                    RequestQueue queue = Volley.newRequestQueue(CartActivity.this);
                                    queue.add(cartdeleteRequest);

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
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

