package kr.jjh.korailticketfinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class SelectTimeActivity extends AppCompatActivity {
    private Context mContext;
    private String departure_station;
    private String arrival_station;
    private String localURL = "http://192.168.43.238:5000";
    private String herokuURL = "https://knu-mobile-app-korail.herokuapp.com";
    int year;
    int month;
    int day;
    int hour;
    int minute;
    private EditText editTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        departure_station = getIntent().getStringExtra("departure_station");
        arrival_station = getIntent().getStringExtra("arrival_station");

        year = getIntent().getIntExtra("year",2019);
        month = getIntent().getIntExtra("month",12);
        day = getIntent().getIntExtra("day",18);

        TimePicker timePicker = findViewById(R.id.timePicker);
        hour = timePicker.getHour();
        minute = timePicker.getMinute();

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int h, int m) {
                hour = h;
                minute = m;
            }
        });

        findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTicket(departure_station, arrival_station);
            }
        });
    }


    private void getTicket(String station1, String station2) {
        String date = year + numToString(month) + numToString(day);
        String url = localURL + "/ticket/" + station1 + "/" + station2 + "/" + date + "/" + hour + minute + "00";
        System.out.println(url);
        Toast toast = Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG);
        toast.show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TicketResult ticketResult = parseJson(response);

                Bundle bundle = new Bundle();
                bundle.putSerializable("ticketResult",ticketResult);

                Intent intent = new Intent(SelectTimeActivity.this, ResultActivity.class);
                intent.putExtras(bundle);

                startActivityForResult(intent,0);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.err.println(error);
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(7000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    private String numToString(int num) {
        String a = Integer.toString(num);
        if (a.length() == 1)
            a = '0' + a;
        return a;
    }

    private TicketResult parseJson(JSONObject jsonObject) {
        TicketResult ticketResult = null;
        ArrayList<Ticket> tickets = new ArrayList<>();

        System.out.println(jsonObject);

        try {
            boolean isIndirect = jsonObject.getBoolean("isIndirect");
            JSONArray ticketArray = new JSONArray(jsonObject.getString("tickets"));

            for (int i = 0; i < ticketArray.length(); i++) {
                JSONObject ticketJSON = ticketArray.getJSONObject(i);
                String departureStation = ticketJSON.getString("dep_name");
                String arrivalStation = ticketJSON.getString("arr_name");
                String departureTime = ticketJSON.getString("dep_time");
                String arrivalTime = ticketJSON.getString("arr_time");
                String date = ticketJSON.getString("dep_date");
                tickets.add(new Ticket(departureStation,arrivalStation,departureTime,arrivalTime, date));
            }
            ticketResult = new TicketResult(isIndirect,tickets);

        } catch (JSONException e) {
            Log.e("#############", "parseJson: " + e);
        }

        return ticketResult;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("result","resultcode: "+resultCode);
        if(resultCode==RESULT_OK)
            finish();
    }
}
