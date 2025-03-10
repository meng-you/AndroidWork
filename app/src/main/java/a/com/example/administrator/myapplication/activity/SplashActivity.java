package a.com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import a.com.example.administrator.myapplication.MainActivity;
import a.com.example.administrator.myapplication.R;

public class SplashActivity extends AppCompatActivity {
    private TextView tv_version;
    private Button close_button;
    private Timer timer;
    private TimerTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }
    private void init(){
        //获取显示版本号信息的控件tv_version
        tv_version=findViewById(R.id.tv_version);
        try {
            //获取程序包信息
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(),0);
            //将程序版本号信息设置到界面控件上
            tv_version.setText("V"+info.versionName);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            tv_version.setText("V");
        }
        //创建Timer类对象
        timer = new Timer();
        //通过 TimerTask 类实现界面跳转的功能
        task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        //设置程序延迟 3 秒之后自动执行任务 task
        timer.schedule(task,3000);
        //设置点击事件,点击按钮也可以关闭开屏画面，实现界面跳转
        close_button = findViewById(R.id.close_button);
        close_button.setOnClickListener(new View.OnClickListener(){
             public void onClick(View view){
                 // 取消定时任务
                 task.cancel();
                 timer.cancel(); // 需要同时取消Timer，因为TimerTask.cancel()只能取消单个任务
                 Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                 startActivity(intent);
                 SplashActivity.this.finish();
            }
        });
    }

}
