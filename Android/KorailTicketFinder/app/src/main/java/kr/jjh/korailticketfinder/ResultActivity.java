package kr.jjh.korailticketfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle receivedBundle = getIntent().getExtras();
        TicketResult ticketResult = (TicketResult) receivedBundle.getSerializable("ticketResult");
        final ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for (Ticket ticket : ticketResult.tickets) {
            tickets.add(ticket);
        }

        TicketAdapter ticketAdapter = new TicketAdapter(this, tickets);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(ticketAdapter);

        String date = ticketResult.tickets.get(0).date;
        date = date.substring(0,4)+"년 "+date.substring(4,6)+"월 "+date.substring(6,8)+"일 ";

        ((TextView) findViewById(R.id.textViewDate)).setText(date);

        findViewById(R.id.button_korail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.korail.talk");
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.button_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }


}
