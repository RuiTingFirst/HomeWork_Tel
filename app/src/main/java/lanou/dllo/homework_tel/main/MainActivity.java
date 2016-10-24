package lanou.dllo.homework_tel.main;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import lanou.dllo.homework_tel.R;
import lanou.dllo.homework_tel.callrecords.CallRecordsFragment;
import lanou.dllo.homework_tel.contacts.ContactsFragment;
import lanou.dllo.homework_tel.dial.DialFragment;
import lanou.dllo.homework_tel.message.MessageFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tb;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = (TabLayout) findViewById(R.id.tb_main);
        vp = (ViewPager) findViewById(R.id.vp_main);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DialFragment());
        fragments.add(new CallRecordsFragment());
        fragments.add(new ContactsFragment());
        fragments.add(new MessageFragment());

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);

        tb.setTabTextColors(Color.BLUE, Color.CYAN);

        tb.getTabAt(0).setIcon(R.drawable.selecte_dail);
        tb.getTabAt(1).setIcon(R.drawable.selecte_call_log);
        tb.getTabAt(2).setIcon(R.drawable.selecte_contact);
        tb.getTabAt(3).setIcon(R.drawable.selecte_sms);

    }
}
