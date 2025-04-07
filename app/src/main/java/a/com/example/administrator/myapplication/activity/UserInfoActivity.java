package a.com.example.administrator.myapplication.activity;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import a.com.example.administrator.myapplication.Bean.UserBean;
import a.com.example.administrator.myapplication.Utils.DBUtils;
import a.com.example.administrator.myapplication.Utils.UtilsHelper;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_nickName, tv_signature, tv_user_name, tv_sex;
    private RelativeLayout rl_nickName, rl_sex, rl_signature, rl_title_bar;
    private String spUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        //从SharedPreferences文件中获取登录时的用户名
        spUserName = UtilsHelper.readLoginUserName(this);
        init();
        setData();
        setListener();
    }
    /**
     * 初始化界面控件
     */
    private void init() {
        tv_back = findViewById(R.id.tv_back);
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料");
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        rl_nickName = findViewById(R.id.rl_nickName);
        rl_sex = findViewById(R.id.rl_sex);
        rl_signature = findViewById(R.id.rl_signature);
        tv_nickName = findViewById(R.id.tv_nickName);
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_sex = findViewById(R.id.tv_sex);
        tv_signature = findViewById(R.id.tv_signature);
    }
/*
 *设置界面数据
 */
    private void setData() {
        UserBean bean = null;
        bean = DBUtils.getInstance(this).getUserInfo(spUserName);
        // 首先判断一下数据库是否有数据
        if (bean == null) {
            bean = new UserBean();
            bean.setUserName(spUserName);
            bean.setNickName("小强");
            bean.setSex("男");
            bean.setSignature("好好学习，天天向上");
            // 保存用户信息到数据库中
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        tv_nickName.setText(bean.getNickName());
        tv_user_name.setText(bean.getUserName());
        tv_sex.setText(bean.getSex());
        tv_signature.setText(bean.getSignature());
    }
    /**
     * 实现修改用户性别
    */
    private void sexDialog(String sex) {
        int checkItem = 0;
        if ("男".equals(sex)) {
            checkItem = 0;
        } else if ("女".equals(sex)) {
            checkItem = 1;
        }
        final String items[] = {"男", "女"};
        // 先得到构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("性别"); // 设置标题
        builder.setSingleChoiceItems(items, checkItem,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(UserInfoActivity.this, items[which],
                                Toast.LENGTH_SHORT).show();
                        setSex(items[which]);
                    }
                });
        builder.create().show();
    }
    private void setSex(String sex) {
        tv_sex.setText(sex);
        // 更新数据库中的性别数据
        DBUtils.getInstance(UserInfoActivity.this).updateUserInfo
                ("sex", sex, spUserName);
    }

    private void setListener() {
        tv_back.setOnClickListener(this);
        rl_nickName.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:      // "返回"按钮的点击事件
                this.finish();
                break;
            case R.id.rl_nickName: // 昵称条目的点击事件
                break;
            case R.id.rl_sex:       // 性别条目的点击事件
                String sex = tv_sex.getText().toString(); // 获取性别控件上的数据
                sexDialog(sex);     // 设置性别数据
                break;
            case R.id.rl_signature:// 签名条目的点击事件
                break;
            default:
                break;
        }
    }
}
