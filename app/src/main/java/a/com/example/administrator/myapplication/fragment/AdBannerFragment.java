package a.com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import a.com.example.administrator.myapplication.Bean.BannerBean;
import a.com.example.administrator.myapplication.R;
import androidx.fragment.app.Fragment;

public class AdBannerFragment extends Fragment {
   private BannerBean bb;
   private ImageView iv;
   public static AdBannerFragment newInstance(Bundle args) {
      AdBannerFragment af = new AdBannerFragment();
      af.setArguments(args);
      return af;
   }
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Bundle arg = getArguments();
      bb = (BannerBean) arg.getSerializable("ad"); //获取广告对象的数据
   }
   @Override
   public void onResume() {
      super.onResume();
      if (bb != null) {
         Glide
                 .with(getActivity())
                 .load(bb.getBannerImg())
                 .error(R.mipmap.ic_launcher)
                 .into(iv);
      }
   }
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      iv = new ImageView(getActivity()); //创建一个 ImageView 控件的对象
      ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.MATCH_PARENT);
      iv.setLayoutParams(lp); //设置 ImageView 控件的宽和高
      iv.setScaleType(ImageView.ScaleType.FIT_XY); //把图片填满整个控件
      return iv;
   }
}