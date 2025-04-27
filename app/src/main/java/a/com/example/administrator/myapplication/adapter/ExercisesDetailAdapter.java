package a.com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import a.com.example.administrator.myapplication.Bean.ExercisesDetailBean;

public class ExercisesDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<ExercisesDetailBean> edbl;
    public ExercisesDetailAdapter(Context context) {
        this.mContext = context;
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
        vh.iv_a.setImageResource(R.drawable.exercises_a);
        vh.iv_b.setImageResource(R.drawable.exercises_b);
        vh.iv_c.setImageResource(R.drawable.exercises_c);
        vh.iv_d.setImageResource(R.drawable.exercises_d);
        return convertView;
    }
    class ViewHolder {
        public TextView subject, tv_a, tv_b, tv_c, tv_d;
        public ImageView iv_a, iv_b, iv_c, iv_d;
    }
    public void setData(List<ExercisesDetailBean> edbl) {
        this.edbl = edbl; //接收传递过来的习题数据
        notifyDataSetChanged(); //刷新界面信息
    }


}
