package com.teamturtle.gnu.exercisescheduler.info;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class CartDeleteRequest extends StringRequest {

    private static String CARTDELETE_REQUEST_URL = "http://exercisemanagementapp.iptime.org/deletecart.php";
    private Map<String, String> params;

    public CartDeleteRequest(String index, Response.Listener<String> Listener) {
        super(Method.POST, CARTDELETE_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put("index", index);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


