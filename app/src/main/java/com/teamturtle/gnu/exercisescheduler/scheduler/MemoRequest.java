package com.teamturtle.gnu.exercisescheduler.scheduler;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 2017-06-02.
 */

public class MemoRequest extends StringRequest {
    private static final String MEMO_REQUEST_URL = "http://exercisemanagementapp.iptime.org/MEMO.php";

    private Map<String, String> params;
    public MemoRequest(String MText, int percentage, int date, Response.Listener<String> listener){
        super(Method.POST, MEMO_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("MText", MText);
        params.put("percentage",percentage+ "");
        params.put("date",date+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
