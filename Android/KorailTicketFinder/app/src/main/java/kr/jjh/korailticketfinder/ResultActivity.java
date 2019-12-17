package kr.jjh.korailticketfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle receivedBundle = getIntent().getExtras();
        TicketResult ticketResult = (TicketResult) receivedBundle.getSerializable("ticketResult");

        //((TextView) findViewById(R.id.textView)).setText(ticketResult.tickets.get(0).arrival_station);
    }
}
