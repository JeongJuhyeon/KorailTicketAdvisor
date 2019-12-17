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

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tickets);
        TicketAdapter ticketAdapter = new TicketAdapter(this, tickets);
        ListView listView = findViewById(R.id.???);
        listView.setAdapter(ticketAdapter);

        ((TextView) findViewById(R.id.textView)).setText(ticketResult.tickets.get(0).arrival_station);
//
//        findViewById(R.id.???).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = getPackageManager().getLaunchIntentForPackage("com.korail.talk");
//                if (intent != null) {
//                    startActivity(intent);
//                }
//            }
//        });
    }


}
