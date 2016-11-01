package lanou.dllo.homework_tel.callrecords;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
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

        queryRecordFromSystem();

        adapter.setMyBeen(beanArrayList);
        lvRecords.setAdapter(adapter);
        lvRecords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + beanArrayList.get(i).getNumber()));
                startActivity(intent);
            }
        });
        lvRecords.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Cursor cursor = mContext.getContentResolver().delete(CallLog.Calls.CONTENT_URI,"number = ?", new String[]{});
                return true;
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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

    private void queryRecordFromSystem() {
        ContentResolver resolver = mContext.getContentResolver();
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            do {
                String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                String date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));

                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                String time = format.format(Long.valueOf(date));
                RecordBean bean = new RecordBean();
                if (name == null){
                    bean.setName("陌生人");
                } else {
                    bean.setName(name);
                }
                bean.setDate(time);
                bean.setNumber(number);
                beanArrayList.add(bean);

            }
            while (cursor.moveToNext());
            cursor.close();
        }
    }

}
