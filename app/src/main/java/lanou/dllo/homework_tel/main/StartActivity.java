package lanou.dllo.homework_tel.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import lanou.dllo.homework_tel.R;

/**
 * Created by dllo on 16/10/26.
 */
public class StartActivity extends Activity {

    private ImageView ivStart;
    private Button btnStart;
    private Bitmap bitmap;
    private CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        ivStart = (ImageView) findViewById(R.id.iv_start);
        btnStart = (Button) findViewById(R.id.btn_start);

        String str = "http://img3.duitang.com/uploads/item/201208/08/20120808142750_5shit.jpeg";

        setTimeDesign();
        // 开启异步任务
        StartAsyncTask asyncTask = new StartAsyncTask();
        asyncTask.execute(str);


    }

    class StartAsyncTask extends AsyncTask<String, Integer, Bitmap> {

        // 结果回调
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ivStart.setImageBitmap(bitmap);

        }

        // 在子线程进行网络图片获取的耗时操作
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }

    // 倒计时方法
    private void setTimeDesign() {

        timer = new CountDownTimer(6000, 1000) {
        // CountDownTimer(a, b)中的参数
        // a, 代表总的倒计时时间, 结束后调用onFinish方法
        // b, 代表每隔b时长调用一次onTick方法
            int a = 5;
            @Override
            public void onTick(long l) {
                a--;
                btnStart.setText(a + "s");
                if (a == 1) {
                    btnStart.setText(1 + "s");
                    onFinish();
                }
                 // 参数l / 1000 不是整数
//                btnStart.setText((l / 1000) + "秒");
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                // 如果界面已经跳转则不再倒计时
//                timer.cancel();
                finish();
            }
        };
        // 启动
        timer.start();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                // 如果界面已经跳转则不再倒计时
//                timer.cancel();
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
