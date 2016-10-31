package lanou.dllo.homework_tel.callrecords;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import lanou.dllo.homework_tel.R;
import lanou.dllo.homework_tel.tools.DBTools;
import lanou.dllo.homework_tel.tools.RecordBean;

/**
 * Created by dllo on 16/10/24.
 */
public class CallRecordsFragment extends Fragment implements View.OnClickListener {

    Context mContext;
    private ListView lvRecords;
    private CallRecordsAdapter adapter;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnQuery;
    private DBTools tools;
    private ArrayList<RecordBean> beanArrayList;


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

        btnAdd = (Button) view.findViewById(R.id.btn_add);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
        btnQuery = (Button) view.findViewById(R.id.btn_query);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnQuery.setOnClickListener(this);

        beanArrayList = new ArrayList<>();
        tools = new DBTools(mContext);

        adapter = new CallRecordsAdapter(mContext);
        adapter.setMyBeen(beanArrayList);
        lvRecords.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                    RecordBean bean = new RecordBean();
                    bean.setName("张三");
                    bean.setNumber("1357924680");
                    bean.setDate("2016.10.31");

                    tools.insertRecordTable(bean);
                beanArrayList = tools.queryRecordTable();
                adapter.setMyBeen(beanArrayList);
                lvRecords.setAdapter(adapter);
                break;
            case R.id.btn_delete:
                RecordBean bean1 = new RecordBean();
                bean1.setName("张三");
                tools.deleteRecordTable(bean1);
                beanArrayList = tools.queryRecordTable();
                adapter.setMyBeen(beanArrayList);
                lvRecords.setAdapter(adapter);
                break;
            case R.id.btn_query:

                beanArrayList = tools.queryRecordTable();
                adapter.setMyBeen(beanArrayList);
                lvRecords.setAdapter(adapter);
                break;
        }
    }
}
