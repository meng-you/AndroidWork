package a.com.example.administrator.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import a.com.example.administrator.myapplication.Bean.ExercisesBean;
import a.com.example.administrator.myapplication.Bean.ExercisesDetailBean;
import a.com.example.administrator.myapplication.R;
import a.com.example.administrator.myapplication.Utils.UtilsHelper;
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
        adapter = new ExercisesDetailAdapter(ExercisesDetailActivity.this,
                new ExercisesDetailAdapter.OnSelectListener() {
                    @Override
                    public void onSelectD(int position, ImageView iv_a,
                                          ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                        //判断如果答案不是 4 即 D 选项
                        SelectValue(position,4);
                        switch (detailList.get(position).getAnswer()) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                iv_d.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 2:
                                iv_d.setImageResource(R.drawable.exercises_error_icon);
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 3:
                                iv_d.setImageResource(R.drawable.exercises_error_icon);
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 4:
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        UtilsHelper.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }
                    @Override
                    public void onSelectC(int position, ImageView iv_a,
                                          ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                        //判断如果答案不是 3 即 C 选项
                        SelectValue(position,3);
                        switch (detailList.get(position).getAnswer()) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                iv_c.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 2:
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                iv_c.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 3:
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 4:
                                iv_c.setImageResource(R.drawable.exercises_error_icon);
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        UtilsHelper.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }
                    @Override
                    public void onSelectB(int position, ImageView iv_a,
                                          ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                        //判断如果答案不是 2 即 B 选项
                        SelectValue(position,2);
                        switch (detailList.get(position).getAnswer()) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                iv_b.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 2:
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 3:
                                iv_b.setImageResource(R.drawable.exercises_error_icon);
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 4:
                                iv_b.setImageResource(R.drawable.exercises_error_icon);
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        UtilsHelper.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }
                    @Override
                    public void onSelectA(int position, ImageView iv_a,
                                          ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                        //判断如果答案不是 1 即 A 选项
                        SelectValue(position,1);
                        switch (detailList.get(position).getAnswer()) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 2:
                                iv_a.setImageResource(R.drawable.exercises_error_icon);
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 3:
                                iv_a.setImageResource(R.drawable.exercises_error_icon);
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 4:
                                iv_a.setImageResource(R.drawable.exercises_error_icon);
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        UtilsHelper.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }
                });
        adapter.setData(detailList);
        lv_list.setAdapter(adapter);
    }
    private void SelectValue(int position,int option){
        if (detailList.get(position).getAnswer() != option) {
            detailList.get(position).setSelect(option);
        } else {
            detailList.get(position).setSelect(0);
        }
    }
}
