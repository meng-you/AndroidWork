package a.com.example.administrator.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import a.com.example.administrator.myapplication.Bean.ExercisesBean;
import a.com.example.administrator.myapplication.Bean.ExercisesDetailBean;
import a.com.example.administrator.myapplication.adapter.ExercisesDetailAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class ExercisesDetailActivity extends AppCompatActivity {
    ExercisesBean bean;
    String title;
    private List<ExercisesDetailBean> detailList;
    private TextView tv_main_title,tv_back;
    private RelativeLayout rl_title_bar;
    private ListView lv_list;
    ExercisesDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        //获取从习题界面传递过来的习题数据
        bean = (ExercisesBean) getIntent().getSerializableExtra("detailList");
        if (bean != null) {
            title = bean.getChapterName(); //获取习题所在的章节名称
            detailList = bean.getDetailList(); //获取习题详情界面的数据
        }
        init();
    }
    private void init() {
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_back = findViewById(R.id.tv_back);
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        lv_list = findViewById(R.id.lv_list);
        TextView tv = new TextView(this);
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setTextSize(16.0f);
        tv.setText("一、选择题");
        tv.setPadding(10, 15, 0, 0);
        lv_list.addHeaderView(tv); //将控件 tv 添加到列表控件 lv_list 的上方
        tv_main_title.setText(title);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExercisesDetailActivity.this.finish();
            }
        });
        adapter = new ExercisesDetailAdapter(ExercisesDetailActivity.this);
        adapter.setData(detailList);
        lv_list.setAdapter(adapter);
    }
}
