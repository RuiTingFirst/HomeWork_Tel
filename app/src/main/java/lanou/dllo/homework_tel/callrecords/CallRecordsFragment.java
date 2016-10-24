package lanou.dllo.homework_tel.callrecords;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lanou.dllo.homework_tel.R;

/**
 * Created by dllo on 16/10/24.
 */
public class CallRecordsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.callrecods_fragment, null);
    }
}
