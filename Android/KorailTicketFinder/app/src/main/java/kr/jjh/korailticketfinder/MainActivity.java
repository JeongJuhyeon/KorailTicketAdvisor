package kr.jjh.korailticketfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.layout_test);
//        ConstraintLayout cl = findViewById(R.id.viewLayout);
//        getLayoutInflater().inflate(R.layout.item_ticket,cl);
        if (savedInstanceState == null && getSupportFragmentManager().getFragments().size() == 0)
        {
            TicketSearchFragment initialFragment = new TicketSearchFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout, initialFragment).commit();
        }

        findViewById(R.id.buttonTicketSearchFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new TicketSearchFragment();
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.framelayout,fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        findViewById(R.id.buttonMapFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new MapFragment();
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.framelayout,fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

    }


}
