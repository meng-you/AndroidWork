package a.com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import a.com.example.administrator.myapplication.R;
import androidx.appcompat.app.AppCompatActivity;

public class ModifyUserInfoActivity extends AppCompatActivity implements
        View.OnClickListener{
    private TextView tv_main_title, tv_save;
    private RelativeLayout rl_title_bar;
    private TextView tv_back,tv_search;
    private String title, content;
    private int flag; //flag 为 1 时表示修改昵称，为 2 时表示修改签名
    private EditText et_content;
    private ImageView iv_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_info);
        init();
    }
    private void init() {
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        flag = getIntent().getIntExtra("flag", 0);
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText(title);
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back = findViewById(R.id.tv_back);
        tv_search = findViewById(R.id.tv_search);
        tv_search.setVisibility(View.GONE);
        tv_save = findViewById(R.id.tv_save);
        tv_save.setVisibility(View.VISIBLE);
        et_content = findViewById(R.id.et_content);
        iv_delete = findViewById(R.id.iv_delete);
        if (!TextUtils.isEmpty(content)) {
            et_content.setText(content);
            et_content.setSelection(content.length());
        }
        contentListener();
        tv_back.setOnClickListener(this);
        iv_delete.setOnClickListener(this);
        tv_save.setOnClickListener(this);

    }
    private void contentListener() {
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = et_content.getText(); //获取输入框中的文本信息
                int len = editable.length(); //获取输入的文本长度
                if (len > 0) {
                    iv_delete.setVisibility(View.VISIBLE); //显示清空输入框内容的图片
                } else {
                    iv_delete.setVisibility(View.GONE); //隐藏清空输入框内容的图片
                }
                switch (flag) {
                    case 1: //修改昵称
                        //昵称限制最多 8 个文字，超过 8 个需要截取掉多余的文字
                        if (len > 8) {
                            modifyContent(editable, 8);
                        }
                        break;
                    case 2: //修改签名
                        //签名最多是 16 个文字，超过 16 个需要截取掉多余的文字
                        if (len > 16) {
                            modifyContent(editable, 16);
                        }
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }
    private void modifyContent(Editable editable, int length) {
        int selEndIndex = Selection.getSelectionEnd(editable);
        String str = editable.toString();
        //截取新字符串
        String newStr = str.substring(0, length);
        et_content.setText(newStr);
        editable = et_content.getText();
        //新字符串的长度
        int newLen = editable.length();
        if (selEndIndex > newLen) {
            selEndIndex = editable.length();
        }
        //设置新光标所在的位置
        Selection.setSelection(editable, selEndIndex);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.tv_back){
            ModifyUserInfoActivity.this.finish();
        } else if (id==R.id.iv_delete) {
            et_content.setText("");
        } else if (id==R.id.tv_save) {
            Intent data = new Intent();
            String etContent = et_content.getText().toString().trim();
            switch (flag) {
                case 1:
                    if (!TextUtils.isEmpty(etContent)) {
                        EnterActivity(data, etContent, "nickName");
                    } else {
                        Toast.makeText(ModifyUserInfoActivity.this,
                                "昵称不能为空", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    if (!TextUtils.isEmpty(etContent)) {
                        EnterActivity(data, etContent, "signature");
                    } else {
                        Toast.makeText(ModifyUserInfoActivity.this,
                                "签名不能为空", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
    private void EnterActivity(Intent data, String etContent, String name) {
        data.putExtra(name, etContent);
        setResult(RESULT_OK, data);
        Toast.makeText(ModifyUserInfoActivity.this, "保存成功",
                Toast.LENGTH_SHORT).show();
        ModifyUserInfoActivity.this.finish();
    }
}

