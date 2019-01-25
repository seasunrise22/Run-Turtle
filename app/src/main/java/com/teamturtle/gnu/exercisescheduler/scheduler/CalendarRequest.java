package com.teamturtle.gnu.exercisescheduler.scheduler;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CalendarRequest extends StringRequest {
    private static final String CALENDAR_REQUEST_URL = "http://exercisemanagementapp.iptime.org/calendar.php";
    private Map<String, String> params;

    public CalendarRequest(int date, Response.Listener<String> Listener) {
        super(Method.POST, CALENDAR_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put("date", date + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
