package kr.jjh.korailticketfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.layout_test);
//        ConstraintLayout cl = findViewById(R.id.viewLayout);
//        getLayoutInflater().inflate(R.layout.item_ticket,cl);
        if (savedInstanceState == null && getSupportFragmentManager().getFragments().size() == 0) //mayneedtoadd fVBid frameLayout==null
        {
            TicketSearchFragment numberFragment = new TicketSearchFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout, numberFragment).commit();
        }
    }
}
