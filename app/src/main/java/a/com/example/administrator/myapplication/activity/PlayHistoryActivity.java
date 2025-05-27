package a.com.example.administrator.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import a.com.example.administrator.myapplication.Bean.VideoBean;
import a.com.example.administrator.myapplication.R;
import a.com.example.administrator.myapplication.Utils.DBUtils;
import a.com.example.administrator.myapplication.Utils.UtilsHelper;
import a.com.example.administrator.myapplication.adapter.PlayHistoryAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlayHistoryActivity extends AppCompatActivity {
    private TextView tv_main_title, tv_back,tv_none,tv_search;
    private RelativeLayout rl_title_bar;
    private RecyclerView rv_list;
    private PlayHistoryAdapter adapter;
    private List<VideoBean> vbl;
    private DBUtils db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_history);
        db= DBUtils.getInstance(this);
        vbl=new ArrayList<>();
        //从数据库中获取播放记录信息
        vbl=db.getVideoHistory(UtilsHelper.readLoginUserName(this));
        init();
    }
    private void init() {
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText("播放记录");
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back = findViewById(R.id.tv_back);
        tv_search = findViewById(R.id.tv_search);
        tv_search.setVisibility(View.GONE);
        rv_list= findViewById(R.id.rv_list);
        tv_none= findViewById(R.id.tv_none);
        if(vbl.size()==0){
            tv_none.setVisibility(View.VISIBLE);
        }
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        adapter=new PlayHistoryAdapter(this);
        adapter.setData(vbl);
        rv_list.setAdapter(adapter);
        // “返回”按钮的点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayHistoryActivity.this.finish();
            }
        });
    }
}
