package a.com.example.administrator.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import a.com.example.administrator.myapplication.Utils.UtilsHelper;
import a.com.example.administrator.myapplication.View.CourseView;
import a.com.example.administrator.myapplication.View.ExercisesView;
import a.com.example.administrator.myapplication.View.MyInfoView;
import a.com.example.administrator.myapplication.activity.ExercisesDetailActivity;
import a.com.example.administrator.myapplication.activity.ModifyPswActivity;
import a.com.example.administrator.myapplication.activity.SearchActivity;
import a.com.example.administrator.myapplication.activity.SettingActivity;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout mBodyLayout; //中间内容栏
    public LinearLayout mBottomLayout; //底部按钮栏
    private View mCourseBtn,mExercisesBtn,mMyInfoBtn;
    private TextView tv_back,tv_main_title,tv_course,tv_exercises,tv_myInfo,tv_search;
    private RelativeLayout rl_title_bar;
    private ImageView iv_course,iv_exercises,iv_myInfo;
    private MyInfoView mMyInfoView;
    private ExercisesView mExercisesView;
    private CourseView mCourseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListener(); //设置按钮的点击事件的监听器
        selectDisplayView(0);//显示课程界面
    }
    /**
     * 获取界面上的控件
     */
    private void init() {
        tv_back = findViewById(R.id.tv_back);
        tv_search = findViewById(R.id.tv_search);
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText("移动程序设计课程");
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back.setVisibility(View.GONE);
        mBodyLayout = findViewById(R.id.main_body);
        mBottomLayout = findViewById(R.id.main_bottom_bar);
        mCourseBtn = findViewById(R.id.bottom_bar_course_btn);
        mExercisesBtn = findViewById(R.id.bottom_bar_exercises_btn);
        mMyInfoBtn = findViewById(R.id.bottom_bar_myinfo_btn);
        tv_course = findViewById(R.id.bottom_bar_text_course);
        tv_exercises = findViewById(R.id.bottom_bar_text_exercises);
        tv_myInfo = findViewById(R.id.bottom_bar_text_myinfo);
        iv_course = findViewById(R.id.bottom_bar_image_course);
        iv_exercises = findViewById(R.id.bottom_bar_image_exercises);
        iv_myInfo = findViewById(R.id.bottom_bar_image_myinfo);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到 问答界面
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
    /**
     * 设置底部按钮未被选中时的状态
     */
    private void setNotSelectedStatus() {
        tv_course.setTextColor(Color.parseColor("#666666"));
        tv_exercises.setTextColor(Color.parseColor("#666666"));
        tv_myInfo.setTextColor(Color.parseColor("#666666"));
        iv_course.setImageResource(R.drawable.main_course_icon);
        iv_exercises.setImageResource(R.drawable.main_exercises_icon);
        iv_myInfo.setImageResource(R.drawable.main_my_icon);
        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setSelected(false);
        }
    }
    /**
     * 设置底部按钮被选中时的状态
     */
    private void setSelectedStatus(int index) {
        switch (index) {
            case 0:
                mCourseBtn.setSelected(true);
                iv_course.setImageResource(R.drawable.main_course_icon_selected);
                tv_course.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("移动程序设计课程");
                break;
            case 1:
                mExercisesBtn.setSelected(true);
                iv_exercises.setImageResource(R.drawable.main_exercises_icon_selected);
                tv_exercises.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("移动程序设计习题");
                break;
            case 2:
                mMyInfoBtn.setSelected(true);
                iv_myInfo.setImageResource(R.drawable.main_my_icon_selected);
                tv_myInfo.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.GONE);
                break;
        }
    }
    /**
     * 隐藏底部导航栏界面的中间部分视图
     */
    private void hideAllView() {
        for (int i = 0; i < mBodyLayout.getChildCount(); i++) {
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }
    /**
     * 创建视图
     */
    private void createView(int viewIndex) {
        switch (viewIndex) {
            case 0:
                //课程界面
                if (mCourseView == null) {
                    mCourseView = new CourseView(this);
                    // 加载课程界面
                    mBodyLayout.addView(mCourseView.getView());
                } else {
                    // 加载课程界面
                    mCourseView.getView();
                }
                mCourseView.showView(); // 显示课程界面
                break;
            case 1:
                //习题界面
                if (mExercisesView == null) {
                    mExercisesView = new ExercisesView(this);
                    mBodyLayout.addView(mExercisesView.getView());
                // 加载习题界面
                } else {
                    mExercisesView.getView(); // 获取习题界面
                }
                mExercisesView.showView(); // 显示习题界面
                break;
            case 2:
                //“我”的界面
                if (mMyInfoView == null) {
                    mMyInfoView = new MyInfoView(this);
                    // 加载“我”的界面
                    mBodyLayout.addView(mMyInfoView.getView());
                } else {
                    // 获取“我”的界面
                    mMyInfoView.getView();
                }
                // 显示“我”的界面
                mMyInfoView.showView();
                break;
        }
    }
    /**
     * 设置底部按钮被选中时对应的界面中间部分视图
     */
    private void selectDisplayView(int index) {
        //隐藏所有视图
        hideAllView();
        //创建被选中按钮对应的视图
        createView(index);
        //设置被选中按钮的选中状态
        setSelectedStatus(index);
    }
    /**
     * 设置底部 3 个按钮的点击事件的监听器
     */
    private void setListener() {
        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bottom_bar_course_btn) {//"课程"按钮的点击事件
            setNotSelectedStatus();
            selectDisplayView(0);
        } else if (id == R.id.bottom_bar_exercises_btn) {//"习题"按钮的点击事件
            setNotSelectedStatus();
            selectDisplayView(1);
        }else if (id == R.id.bottom_bar_myinfo_btn) {//"我"的按钮的点击事件
            setNotSelectedStatus();
            selectDisplayView(2);
        }
    }
    /**
     * 实现退出程序的功能
     */
    protected long exitTime;//记录第一次点击时的时间
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction()
                == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this,
                        "再按一次退出学习平台",Toast.LENGTH_SHORT).show();
                //记录当前点击返回键的时间
                exitTime = System.currentTimeMillis();
            } else {
                MainActivity.this.finish();
                if (UtilsHelper.readLoginStatus(MainActivity.this)){
                    //清除登录状态与用户名
                    UtilsHelper.clearLoginStatus(MainActivity.this);
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 显示用户名到“我”的界面上
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            //获取从设置界面或登录界面传递过来的登录状态
            boolean isLogin=data.getBooleanExtra("isLogin",false);
            if(isLogin){//登录成功时显示课程界面
                setNotSelectedStatus();
                selectDisplayView(0);
            }
            if (mMyInfoView != null) {
                //登录成功或退出登录时根据 isLogin 的值设置"我"的界面
                mMyInfoView.setLoginParams(isLogin);
            }
        }
    }

}
