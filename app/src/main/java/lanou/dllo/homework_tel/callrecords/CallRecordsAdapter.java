package lanou.dllo.homework_tel.callrecords;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.dllo.homework_tel.R;
import lanou.dllo.homework_tel.tools.RecordBean;

/**
 * Created by dllo on 16/10/25.
 */
public class CallRecordsAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<RecordBean> myBeen;

    public CallRecordsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setMyBeen(ArrayList<RecordBean> myBeen) {
        this.myBeen = myBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return myBeen == null ? 0 : myBeen.size();
    }

    @Override
    public Object getItem(int i) {
        return myBeen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_call_records, null);
            viewHolder  = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.nameTV.setText(myBeen.get(i).getName());
        viewHolder.dateTV.setText(myBeen.get(i).getDate());
        viewHolder.numberTV.setText(myBeen.get(i).getNumber());

        return view;
    }

    private class ViewHolder {

        private final TextView nameTV;
        private final TextView numberTV;
        private final TextView dateTV;

        public ViewHolder(View view) {
            nameTV = (TextView) view.findViewById(R.id.tv_name);
            numberTV = (TextView) view.findViewById(R.id.tv_number);
            dateTV = (TextView) view.findViewById(R.id.tv_date);
        }
    }
    public void delete(MyBean bean){
        myBeen.remove(bean);
        notifyDataSetChanged();
    }
}
