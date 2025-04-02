package a.com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import a.com.example.administrator.myapplication.R;
import a.com.example.administrator.myapplication.Utils.UtilsHelper;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_main_title;
    private TextView tv_back;
    private RelativeLayout rl_title_bar;
    private RelativeLayout rl_modify_psw,
            rl_security_setting, rl_exit_login;
    public static SettingActivity instance=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        instance = this;
        init();
    }
    private void init() {
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText("设置");
        tv_back = findViewById(R.id.tv_back);
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor
                (Color.parseColor("#30B4FF"));
        rl_modify_psw = findViewById(R.id.rl_modify_psw);
        rl_security_setting = findViewById
                (R.id.rl_security_setting);
        rl_exit_login = findViewById(R.id.rl_exit_login);
        tv_back.setOnClickListener(this);
        rl_modify_psw.setOnClickListener(this);
        rl_security_setting.setOnClickListener(this);
        rl_exit_login.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_back) {
            this.finish();
        } else if (id == R.id.rl_modify_psw) {
            //跳转到修改密码界面
            Intent intent = new Intent(SettingActivity.this,ModifyPswActivity.class);
            startActivity(intent);
        } else if (id == R.id.rl_security_setting) {
            //跳转到设置密保界面
            Intent securityIntent =
                    new Intent(SettingActivity.this,FindPswActivity.class);
            securityIntent.putExtra("from", "security");
            startActivity(securityIntent);
        } else if (id == R.id.rl_exit_login) {
            Toast.makeText(SettingActivity.this, "退出登录成功",
                    Toast.LENGTH_SHORT).show();
            //清除登录状态和登录时的用户名
            UtilsHelper.clearLoginStatus(SettingActivity.this);
            Intent data = new Intent();
            data.putExtra("isLogin", false);
            setResult(RESULT_OK, data);
            SettingActivity.this.finish();
        }
    }
}
