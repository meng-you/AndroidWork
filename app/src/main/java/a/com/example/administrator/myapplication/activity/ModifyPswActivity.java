package a.com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import a.com.example.administrator.myapplication.R;
import a.com.example.administrator.myapplication.Utils.MD5Utils;
import a.com.example.administrator.myapplication.Utils.UtilsHelper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ModifyPswActivity extends AppCompatActivity implements View.OnClickListener {
    private String userName,spOriginalPsw,originalPsw,newPsw,newPswAgain;
    private TextView tv_main_title,tv_back,tv_new_psw_msg,tv_search;
    private EditText et_original_psw,et_new_psw,et_new_psw_again;
    private Button btn_save;
    private boolean isPasswordValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);
        init();
        userName = UtilsHelper.readLoginUserName(this);
        spOriginalPsw = UtilsHelper.readPsw(this, userName);
    }
    private void init(){
        tv_main_title= findViewById(R.id.tv_main_title);
        tv_main_title.setText("修改密码");
        tv_back= findViewById(R.id.tv_back);
        tv_new_psw_msg=findViewById(R.id.tv_new_psw_msg);
        tv_search = findViewById(R.id.tv_search);
        tv_search.setVisibility(View.GONE);
        et_original_psw= findViewById(R.id.et_original_psw);
        et_new_psw= findViewById(R.id.et_new_psw);
        et_new_psw_again= findViewById(R.id.et_new_psw_again);
        btn_save= findViewById(R.id.btn_save);
        tv_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        //实时判断输入的密码是否为混合字母和数字
        et_new_psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                isPasswordValid = UtilsHelper.validatePassword(editable.toString());
                if (isPasswordValid) {
                    tv_new_psw_msg.setText("当前密码为数字和字母混合.");
                    tv_new_psw_msg.setTextColor(ContextCompat.getColor(ModifyPswActivity.this, android.R.color.holo_green_dark));
                } else {
                    tv_new_psw_msg.setText("请输入数字和字母混合的密码.");
                    tv_new_psw_msg.setTextColor(ContextCompat.getColor(ModifyPswActivity.this, android.R.color.holo_red_dark));
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_back) {
            ModifyPswActivity.this.finish();
        } else if (id == R.id.btn_save) {
            getEditString();
            if (TextUtils.isEmpty(originalPsw)) {
                Toast.makeText(ModifyPswActivity.this, "请输入原始密码",
                        Toast.LENGTH_SHORT).show();
                return;
            } else if (!MD5Utils.md5(originalPsw).equals(spOriginalPsw)) {
                Toast.makeText(ModifyPswActivity.this, "输入的密码与原始密码不相同",
                        Toast.LENGTH_SHORT).show();
                return;
            } else if (MD5Utils.md5(newPsw).equals(spOriginalPsw)) {
                Toast.makeText(ModifyPswActivity.this, "输入的新密码与原始密码不能相同",
                        Toast.LENGTH_SHORT).show();
                return;
            } else if (TextUtils.isEmpty(newPsw)) {
                Toast.makeText(ModifyPswActivity.this, "请输入新密码",
                        Toast.LENGTH_SHORT).show();
                return;
            }else if(!isPasswordValid){
                Toast.makeText(ModifyPswActivity.this, "密码不是数字和字母混合，请重输",
                        Toast.LENGTH_SHORT).show();
                return;
            } else if (TextUtils.isEmpty(newPswAgain)) {
                Toast.makeText(ModifyPswActivity.this, "请再次输入新密码",
                        Toast.LENGTH_SHORT).show();
                return;
            } else if (!newPsw.equals(newPswAgain)) {
                Toast.makeText(ModifyPswActivity.this, "两次输入的新密码不一致",
                        Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(ModifyPswActivity.this, "新密码设置成功",
                        Toast.LENGTH_SHORT).show();
                //保存新密码到SharedPreferences文件中
                UtilsHelper.saveUserInfo(ModifyPswActivity.this, userName, newPsw);
                Intent intent = new Intent(ModifyPswActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                SettingActivity.instance.finish(); //关闭设置界面
                ModifyPswActivity.this.finish();    //关闭修改密码界面
            }
        }
    }
    /**
     * 获取界面输入框控件上的字符串
     */
    private void getEditString() {
        originalPsw = et_original_psw.getText().toString().trim();
        newPsw = et_new_psw.getText().toString().trim();
        newPswAgain = et_new_psw_again.getText().toString().trim();
    }
}
