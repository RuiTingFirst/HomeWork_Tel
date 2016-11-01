package lanou.dllo.homework_tel.contacts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import lanou.dllo.homework_tel.R;
import lanou.dllo.homework_tel.tools.DBTools;

/**
 * Created by dllo on 16/10/24.
 */
public class ContactsFragment extends Fragment {

    private ListView lvContact;
    Context mContext;
    private ArrayList<ContactBean> beanArrayList;
    private DBTools tools;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contacts_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvContact = (ListView) view.findViewById(R.id.lv_contact);
        beanArrayList = new ArrayList<>();

        tools = new DBTools(mContext);
        queryContactFromSystem();
        ContactAdapter adapter = new ContactAdapter(mContext);
        adapter.setBeen(beanArrayList);
        lvContact.setAdapter(adapter);


    }
    private void queryContactFromSystem() {

        // 该方法用于获取数据
        // 内容解析者resolver
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                // 获取联系人图片需要获取两个id
                long photo_id = cursor.getLong(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID));
                long contact_id = cursor.getLong(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                // 如果photo_id > 0, 则有头像
                Bitmap imageBitmap;
                if (photo_id > 0) {
                    Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contact_id);
                    InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);

                    // 将数据流input转换成Bitmap
                    imageBitmap = BitmapFactory.decodeStream(input);
                } else {
                    // 如果没有头像, 默认添加一个机器人头像
                    imageBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                }

                ContactBean bean = new ContactBean();
                bean.setName(name);
                bean.setNumber(number);
                bean.setImage(imageBitmap);
                beanArrayList.add(bean);

                tools.insertContactTable(bean);
            }
            cursor.close();
        }
    }

}
