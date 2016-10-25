package lanou.dllo.homework_tel.dial;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import lanou.dllo.homework_tel.R;

/**
 * Created by dllo on 16/10/24.
 */
public class DialFragment extends Fragment implements View.OnClickListener {

    private ImageButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnPound,btnHash,btnTel;
    private TextView tv;
    private EditText edt;
    String str = "";
    private Button btnDEL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dial_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn0 = (ImageButton)view.findViewById(R.id.btn_0);
        btn1 = (ImageButton)view.findViewById(R.id.btn_1);
        btn2 = (ImageButton)view.findViewById(R.id.btn_2);
        btn3 = (ImageButton)view.findViewById(R.id.btn_3);
        btn4 = (ImageButton)view.findViewById(R.id.btn_4);
        btn5 = (ImageButton)view.findViewById(R.id.btn_5);
        btn6 = (ImageButton)view.findViewById(R.id.btn_6);
        btn7 = (ImageButton)view.findViewById(R.id.btn_7);
        btn8 = (ImageButton)view.findViewById(R.id.btn_8);
        btn9 = (ImageButton)view.findViewById(R.id.btn_9);
        btnPound = (ImageButton)view.findViewById(R.id.btn_pound);
        btnHash = (ImageButton)view.findViewById(R.id.btn_hash);
        btnTel = (ImageButton)view.findViewById(R.id.btn_tel);
        btnDEL = (Button) view.findViewById(R.id.btn_del);
        edt = (EditText)view.findViewById(R.id.edt);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnPound.setOnClickListener(this);
        btnHash.setOnClickListener(this);
        btnTel.setOnClickListener(this);
        btnDEL.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                str += "0";

                break;
            case R.id.btn_1:
                str += "1";

                break;
            case R.id.btn_2:
                str += "2";

                break;
            case R.id.btn_3:
                str += "3";
                edt.setText(str);

                break;
            case R.id.btn_4:
                str += "4";

                break;
            case R.id.btn_5:
                str += "5";

                break;
            case R.id.btn_6:
                str += "6";

                break;
            case R.id.btn_7:
                str += "7";

                break;
            case R.id.btn_8:
                str += "8";

                break;
            case R.id.btn_9:
                str += "9";

                break;
            case R.id.btn_pound:
                str += "*";

                break;
            case R.id.btn_hash:
                str +=  "#";

                break;
            case R.id.btn_tel:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + edt.getText().toString()));
                startActivity(intent);
                break;

            case R.id.btn_del:
                if (str.length() >= 1){
                    str = str.substring(0, str.length() - 1);
                }
                break;

            default:
                break;
        }
        edt.setText(str);
        // 光标出现在输入位置
        edt.setSelection(str.length());
    }
}
