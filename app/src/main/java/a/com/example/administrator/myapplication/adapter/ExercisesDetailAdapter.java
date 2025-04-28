package a.com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a.com.example.administrator.myapplication.Bean.ExercisesDetailBean;
import a.com.example.administrator.myapplication.R;
import a.com.example.administrator.myapplication.Utils.UtilsHelper;

public class ExercisesDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<ExercisesDetailBean> edbl;
    public ExercisesDetailAdapter(Context context) {
        this.mContext = context;
    }
    //记录已点击过的所有位置
    private ArrayList<String> selectedPosition = new ArrayList<String>();
    private OnSelectListener onSelectListener;
    public ExercisesDetailAdapter(Context context, OnSelectListener onSelectListener) {
        this.mContext = context;
        this.onSelectListener = onSelectListener;
    }
    @Override
    public int getCount() {
        return edbl == null ? 0 : edbl.size();
    }
    @Override
    public ExercisesDetailBean getItem(int position) {
        return edbl == null ? null : edbl.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.exercises_detail_list_item, null);
            vh.subject = convertView.findViewById(R.id.tv_subject);
            vh.tv_a = convertView.findViewById(R.id.tv_a);
            vh.tv_b = convertView.findViewById(R.id.tv_b);
            vh.tv_c = convertView.findViewById(R.id.tv_c);
            vh.tv_d = convertView.findViewById(R.id.tv_d);
            vh.iv_a = convertView.findViewById(R.id.iv_a);
            vh.iv_b = convertView.findViewById(R.id.iv_b);
            vh.iv_c = convertView.findViewById(R.id.iv_c);
            vh.iv_d = convertView.findViewById(R.id.iv_d);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final ExercisesDetailBean bean = getItem(position);
        if (bean != null) {
            vh.subject.setText(bean.getSubject()); //设置题干信息
            vh.tv_a.setText(bean.getA()); //设置 A 选项数据
            vh.tv_b.setText(bean.getB()); //设置 B 选项数据
            vh.tv_c.setText(bean.getC()); //设置 C 选项数据
            vh.tv_d.setText(bean.getD()); //设置 D 选项数据
        }
        if (!selectedPosition.contains("" + position)) {
            vh.iv_a.setImageResource(R.drawable.exercises_a);
            vh.iv_b.setImageResource(R.drawable.exercises_b);
            vh.iv_c.setImageResource(R.drawable.exercises_c);
            vh.iv_d.setImageResource(R.drawable.exercises_d);
            UtilsHelper.setABCDEnable(true, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
        } else {
            UtilsHelper.setABCDEnable(false, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
            switch (bean.getSelect()) {
                case 0:
                    //用户选择的选项是正确的
                    if (bean.getAnswer() == 1) {
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 2) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 3) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 1:
                    //用户选择的选项 A 是错误的
                    vh.iv_a.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 2) {
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 3) {
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer()== 4) {
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 2:
                    //用户选择的选项 B 是错误的
                    vh.iv_b.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1) {
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 3) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 3:
                    //用户选择的选项 C 是错误的
                    vh.iv_c.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1) {
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 2) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 4:
                    //用户选择的选项 D 是错误的
                    vh.iv_d.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1) {
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                    } else if (bean.getAnswer() == 2) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                    } else if (bean.getAnswer() == 3) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                default:
                    break;
            }
        }
        //A 选项的点击事件
        vh.iv_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition(position);
                //调用接口中的 onSelectA()方法，在该方法中实现 A 选项的点击事件
                onSelectListener.onSelectA(position, vh.iv_a, vh.iv_b,
                        vh.iv_c, vh.iv_d);
            }
        });
        //B 选项的点击事件
        vh.iv_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition(position);
                //调用接口中的 onSelectB()方法，在该方法中实现 B 选项的点击事件
                onSelectListener.onSelectB(position, vh.iv_a, vh.iv_b,
                        vh.iv_c, vh.iv_d);
            }
        });
        //C 选项的点击事件
        vh.iv_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition(position);
                //调用接口中的 onSelectC()方法，在该方法中实现 C 选项的点击事件
                onSelectListener.onSelectC(position, vh.iv_a, vh.iv_b,
                        vh.iv_c, vh.iv_d);
            }
        });
        //D 选项的点击事件
        vh.iv_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition(position);
                //调用接口中的 onSelectD()方法，在该方法中实现 D 选项的点击事件
                onSelectListener.onSelectD(position, vh.iv_a, vh.iv_b,
                        vh.iv_c, vh.iv_d);
            }
        });
        return convertView;
    }
    private void selectedPosition(int position){
        if (!selectedPosition.contains("" + position)) {
            selectedPosition.add(position + "");
        }
    }
    class ViewHolder {
        public TextView subject, tv_a, tv_b, tv_c, tv_d;
        public ImageView iv_a, iv_b, iv_c, iv_d;
    }
    public void setData(List<ExercisesDetailBean> edbl) {
        this.edbl = edbl; //接收传递过来的习题数据
        notifyDataSetChanged(); //刷新界面信息
    }
    public interface OnSelectListener {
        void onSelectA(int position, ImageView iv_a,
                       ImageView iv_b, ImageView iv_c, ImageView iv_d);
        void onSelectB(int position, ImageView iv_a,
                       ImageView iv_b,ImageView iv_c, ImageView iv_d);
        void onSelectC(int position, ImageView iv_a,
                       ImageView iv_b,ImageView iv_c, ImageView iv_d);
        void onSelectD(int position, ImageView iv_a,
                       ImageView iv_b, ImageView iv_c, ImageView iv_d);
    }

}
