package a.com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import a.com.example.administrator.myapplication.R;
import a.com.example.administrator.myapplication.Utils.UtilsHelper;

public class RegisterActivity extends AppCompatActivity {
    private TextView tv_main_title; //标题
    private TextView tv_back; //"返回"按钮
    private Button btn_register; //"注册"按钮
    private TextView tv_user_name_msg;//用户名提示
    private TextView tv_psw_msg;//密码提示
    private boolean isUsernameValid = false;//用于判断用户名是否重复
    private boolean isPasswordValid = false;//用于判断密码是否为混合
    //用户名、密码、再次输入密码的控件
    private EditText et_user_name, et_psw, et_psw_again;
    //用户名、密码、再次输入密码的控件的获取值
    private String userName, psw, pswAgain;
    //标题布局
    private RelativeLayout rl_title_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }
    private void init(){
        tv_main_title = findViewById(R.id.tv_main_title);
        //设置注册界面标题为“注册”
        tv_main_title.setText("注册");
        tv_back = findViewById(R.id.tv_back);
        rl_title_bar = findViewById(R.id.title_bar);
        //设置标题栏背景颜色为透明
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);
        tv_user_name_msg = findViewById(R.id.tv_user_name_msg);
        tv_psw_msg = findViewById(R.id.tv_psw_msg);
        btn_register = findViewById(R.id.btn_register);
        et_user_name = findViewById(R.id.et_user_name);
        et_psw = findViewById(R.id.et_psw);
        et_psw_again = findViewById(R.id.et_psw_again);
        //“返回”按钮的点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
        //实时判断用户名是否重复
        et_user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                validateUsername(editable.toString());
            }
        });
        //实时判断输入的密码是否为混合字母和数字
        et_psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                validatePassword(editable.toString());
            }
        });

        //“注册”按钮的点击事件
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEditString();//获取界面控件中输入的注册信息
                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this, "请输入密码",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else if(!isPasswordValid){
                    Toast.makeText(RegisterActivity.this, "密码不是数字和字母混合，请重输",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "请再次输入密码",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "输入两次的密码不一致",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else if(UtilsHelper.isExistUserName(RegisterActivity.this, userName)){
                    Toast.makeText(RegisterActivity.this, "此用户名已经存在",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(RegisterActivity.this, "注册成功",
                            Toast.LENGTH_SHORT).show();
                    //把用户名和密码保存到 SharedPreferences 文件中
                    UtilsHelper.saveUserInfo(RegisterActivity.this, userName, psw);
                    //注册成功后把用户名传递到 LoginActivity 中
                    Intent data =new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    RegisterActivity.this.finish();
                }
            }
        });
    }

    //获取界面控件中的注册信息
    private void getEditString() {
        //获取注册界面中输入的用户名信息
        userName = et_user_name.getText().toString().trim();
        //获取注册界面输入的密码信息
        psw = et_psw.getText().toString().trim();
        //获取注册界面输入的再次输入密码信息
        pswAgain = et_psw_again.getText().toString().trim();
    }

    //判断是否用户名重复，根据不同结果给出不同提示
    private void validateUsername(String username){
        if (UtilsHelper.isExistUserName(RegisterActivity.this, username)){
            isUsernameValid = false;
        }else {
            isUsernameValid = true;
        }
        if (isUsernameValid) {
            tv_user_name_msg.setText("当前用户名可用.");
            tv_user_name_msg.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        } else {
            tv_user_name_msg.setText("当前用户名重复，请更换.");
            tv_user_name_msg.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        }
    }

    //判断是否为数字字母混合的方法,根据不同结果给出不同提示
    private void validatePassword(String password){
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            // 同时有数字和字母退出
            if (hasLetter && hasDigit) {
                break;
            }
        }
        //为真则表示同时有数字和字母
        isPasswordValid = hasLetter && hasDigit;
        //输出文本,根据输入的密码输出不同的提示
        if (isPasswordValid) {
            tv_psw_msg.setText("当前密码为数字和字母混合.");
            tv_psw_msg.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        } else {
            tv_psw_msg.setText("请输入数字和字母混合的密码.");
            tv_psw_msg.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        }
    }
}
