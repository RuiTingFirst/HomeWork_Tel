package lanou.dllo.homework_tel.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import lanou.dllo.homework_tel.callrecords.CallRecordsAdapter;
import lanou.dllo.homework_tel.contacts.ContactBean;

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

    // 根据名字删除record表内容
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

    // 插入contact表内容
    public void insertContactTable(ContactBean bean) {
        // 判断数据库是否有这条数据, 去重复
        Cursor cursor = database.query(DBValues.TABLE_NAME_CONTACT, null, DBValues.TABLE_CONTACT_NUMBER + "=?", new String[]{bean.getNumber()}, null, null, null);
        int count = cursor.getCount();
        if (count > 0){
            // 当联系人已有这个电话时, 就返回不再操作, 否则, 添加联系人
            return;
        }
        ContentValues values = new ContentValues();
        values.put(DBValues.TABLE_CONTACT_NAME, bean.getName());
        values.put(DBValues.TABLE_CONTACT_NUMBER, bean.getNumber());
        // 将ContactBean中的Bitmap转换成二进制, 再存入数据库
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bean.getImage().compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] imageResult = outputStream.toByteArray();
        values.put(DBValues.TABLE_CONTACT_IMAGE, imageResult);
        database.insert(DBValues.TABLE_NAME_CONTACT, null, values);


    }
}
