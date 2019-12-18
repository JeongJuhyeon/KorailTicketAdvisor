package kr.jjh.korailticketfinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TicketAdapter extends ArrayAdapter<Ticket> {
    public TicketAdapter(@NonNull Context context, @NonNull ArrayList<Ticket> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View ticketView, @NonNull ViewGroup parent) {
        Ticket ticket = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (ticketView == null) {
            ticketView = LayoutInflater.from(getContext()).inflate(R.layout.item_ticket, parent,
                    false);
        }

        TextView tvDept = ticketView.findViewById(R.id.ticket_departure);
        TextView tvArr = ticketView.findViewById(R.id.ticket_arrival);
        TextView tvTime = ticketView.findViewById(R.id.ticket_time);
        TextView tvNumber = ticketView.findViewById(R.id.ticket_number);

        tvDept.setText(ticket.departure_station);
        tvArr.setText(ticket.arrival_station);
        tvTime.setText(changeTime( ticket.departure_time )+ " ~ " + changeTime(ticket.arrival_time));
        tvNumber.setText("티켓 #" + (position + 1));

//        The returned view will be rendered
        return ticketView;
    }

    private String changeTime(String time) {
        String noSeconds = time.substring(0,2) + ":" + time.substring(2,4);
        return noSeconds;
    }
}




