package a.com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import a.com.example.administrator.myapplication.R;
import a.com.example.administrator.myapplication.Utils.MD5Utils;
import a.com.example.administrator.myapplication.Utils.UtilsHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 获取界面控件
     */
    private TextView tv_main_title;
    private TextView tv_back;
    private TextView tv_register;
    private TextView tv_find_psw;
    private Button btn_login;
    private EditText et_user_name;
    private EditText et_psw;
    private String userName,psw,spPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText("登录");
        tv_back = findViewById(R.id.tv_back);
        tv_register = findViewById(R.id.tv_register);
        tv_find_psw = findViewById(R.id.tv_find_psw);
        btn_login = findViewById(R.id.btn_login);
        et_user_name = findViewById(R.id.et_user_name);
        et_psw = findViewById(R.id.et_psw);

        tv_back.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_find_psw.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    //实现显示回传数据的功能
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            //从注册界面传递过来的用户名
            String userName =data.getStringExtra("userName");
            if(!TextUtils.isEmpty(userName)){
                et_user_name.setText(userName);
                //设置光标的位置
                et_user_name.setSelection(userName.length());
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back: //"返回"按钮的点击事件
                this.finish();
                break;
            case R.id.tv_register: //"立即注册"文本的点击事件
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_find_psw://"找回密码？"文本的点击事件
                //跳转到找回密码界面
                Intent findPswIntent =
                        new Intent(LoginActivity.this,FindPswActivity.class);
                startActivity(findPswIntent);
                break;
            case R.id.btn_login: //"登录"按钮的点击事件
                userName=et_user_name.getText().toString().trim();
                psw=et_psw.getText().toString().trim();
                String md5Psw=MD5Utils.md5(psw);
                spPsw=UtilsHelper.readPsw(LoginActivity.this,userName);
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(LoginActivity.this, "请输入用户名",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(spPsw)){
                    Toast.makeText(LoginActivity.this, "此用户名不存在",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(LoginActivity.this, "请输入密码",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if((!TextUtils.isEmpty(spPsw)&&!md5Psw.equals(spPsw))){
                    Toast.makeText(LoginActivity.this, "输入的密码不正确",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else if(md5Psw.equals(spPsw)){
                    Toast.makeText(LoginActivity.this, "登录成功",
                            Toast.LENGTH_SHORT).show();
                    //保存登录状态和登录的用户名
                    UtilsHelper.saveLoginStatus(LoginActivity.this,true,userName);
                    //把登录成功的状态传递到 MainActivity 中
                    Intent data=new Intent();
                    data.putExtra("isLogin", true);
                    setResult(RESULT_OK, data);
                    LoginActivity.this.finish();
                }
                break;
        }
    }
}
