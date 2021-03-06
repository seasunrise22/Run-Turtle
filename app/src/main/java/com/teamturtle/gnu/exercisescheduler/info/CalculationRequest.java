package com.teamturtle.gnu.exercisescheduler.info;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class CalculationRequest extends StringRequest {

    private static final String CALORIE_REQUEST_URL = "http://exercisemanagementapp.iptime.org/calculation.php";
    private Map<String, String> params;

    public CalculationRequest(String index, Response.Listener<String> listener){
        super(Method.POST, CALORIE_REQUEST_URL, listener , null);
        params = new HashMap<>();
        params.put("index",index);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
