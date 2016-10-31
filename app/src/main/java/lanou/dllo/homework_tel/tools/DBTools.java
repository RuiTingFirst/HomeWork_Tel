package lanou.dllo.homework_tel.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import lanou.dllo.homework_tel.callrecords.CallRecordsAdapter;

/**
 * Created by dllo on 16/10/31.
 */
public class DBTools {

    Context mContext;


    private final SQLiteDatabase database;

    public DBTools(Context context){
        MyHelper helpter = new MyHelper(context, DBValues.DB_NAME, null, 1);
        database = helpter.getWritableDatabase();
        this.mContext = context;
    }

    // 插入record表内容
    public void insertRecordTable(RecordBean bean){
        ContentValues values = new ContentValues();
        values.put(DBValues.RECORD_TABLE_DATE, bean.getDate());
        values.put(DBValues.RECORD_TABLE_NUMBER, bean.getNumber());
        values.put(DBValues.RECORD_TABLE_NAME, bean.getName());
        database.insert(DBValues.TABLE_NAME_RECORD, null, values);
    }

    // 删除record表内容
    public void deleteRecordTable(RecordBean bean){
        database.delete(DBValues.TABLE_NAME_RECORD, DBValues.RECORD_TABLE_NAME + "=?", new String[]{bean.getName()});
    }

    // 查询record表内容
    public ArrayList<RecordBean> queryRecordTable(){
        ArrayList<RecordBean> beanArrayList = new ArrayList<>();
        Cursor cursor = database.query(DBValues.TABLE_NAME_RECORD, null, null, null, null, null, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(DBValues.RECORD_TABLE_NAME));
                String number = cursor.getString(cursor.getColumnIndex(DBValues.RECORD_TABLE_NUMBER));
                String date = cursor.getString(cursor.getColumnIndex(DBValues.RECORD_TABLE_DATE));
                RecordBean bean = new RecordBean();
                bean.setName(name);
                bean.setDate(date);
                bean.setNumber(number);
                beanArrayList.add(bean);

            }
        }
        return beanArrayList;
    }
}
