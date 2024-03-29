package kr.jjh.korailticketfinder;


import android.content.Context;
import android.content.Intent;
import android.icu.util.LocaleData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;


/**
 * A simple {@link Fragment} subclass.
 */
public class TicketSearchFragment extends Fragment {

    private Context mContext;
    private String departure_station;
    private String arrival_station;
    Date date;
    int year;
    int month;
    int day;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public TicketSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_search, container, false);
        Spinner spinner_departure = view.findViewById(R.id.departureSpinner);
        Spinner spinner_arrival = view.findViewById(R.id.arrivalSpinner);
        final CalendarView calendarView = view.findViewById(R.id.calendarView);

        date = new Date(calendarView.getDate());
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        year = localDate.getYear();
        month = localDate.getMonthValue();
        day = localDate.getDayOfMonth();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.stations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_departure.setAdapter(adapter);
        spinner_arrival.setAdapter(adapter);

        spinner_departure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                departure_station = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_arrival.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arrival_station = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int d) {
                year = y;
                month = m + 1;
                day = d;
            }
        });

        view.findViewById(R.id.submitDateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(departure_station.equals(arrival_station)) {
                    Toast toast = Toast.makeText(mContext, "출발역과 도착역이 달라야 합니다!", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                Intent intent = new Intent(getActivity(), SelectTimeActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                intent.putExtra("day",day);
                intent.putExtra("departure_station", departure_station);
                intent.putExtra("arrival_station", arrival_station);
                startActivity(intent);
            }
        });


        return view;
    }


}
