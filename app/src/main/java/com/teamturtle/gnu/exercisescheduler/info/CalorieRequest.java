package com.teamturtle.gnu.exercisescheduler.info;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CalorieRequest extends StringRequest {
    private static final String CALORIE_REQUEST_URL = "http://exercisemanagementapp.iptime.org/addcart.php";
    private Map<String, String> params;

    public CalorieRequest(String index, Response.Listener<String> listener){
        super(Method.POST, CALORIE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("index",index);
    }



    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
