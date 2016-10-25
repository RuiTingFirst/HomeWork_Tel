package lanou.dllo.homework_tel.callrecords;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import lanou.dllo.homework_tel.R;

/**
 * Created by dllo on 16/10/24.
 */
public class CallRecordsFragment extends Fragment {

    Context mContext;
    private ListView lvRecords;
    private CallRecordsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.callrecods_fragment, null);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvRecords = (ListView) view.findViewById(R.id.lv_call_records);

        ArrayList<MyBean> beanArrayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            MyBean bean = new MyBean("张三" + i, i + 10 + "", "2016");
            beanArrayList.add(bean);
        }
        adapter = new CallRecordsAdapter(mContext);
        adapter.setMyBeen(beanArrayList);
        lvRecords.setAdapter(adapter);

    }
}
