package lanou.dllo.homework_tel.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.dllo.homework_tel.R;

/**
 * Created by dllo on 16/11/1.
 */
public class ContactAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<ContactBean> been;

    public void setBeen(ArrayList<ContactBean> been) {
        this.been = been;
    }

    public ContactAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return been != null && been.size() > 0 ? been.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return been != null && been.size() > 0 ? been.get(i) : 0;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_contact, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.nameTV.setText(been.get(i).getName());
        viewHolder.numberTV.setText(been.get(i).getNumber());
        viewHolder.image.setImageBitmap(been.get(i).getImage());

        return view;
    }

    private class ViewHolder {

        private final ImageView image;
        private final TextView nameTV;
        private final TextView numberTV;


        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.iv_contact_item);
            nameTV = (TextView) view.findViewById(R.id.tv_contact_name);
            numberTV = (TextView) view.findViewById(R.id.tv_contact_number);

        }
    }

}
