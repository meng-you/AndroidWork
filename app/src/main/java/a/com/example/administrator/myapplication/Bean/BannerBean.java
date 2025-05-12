package a.com.example.administrator.myapplication.Bean;

import java.io.Serializable;

public class BannerBean implements Serializable {
   //序列化时保持 BannerBean 类版本的兼容性
   private static final long serialVersionUID = 1L;
   private int id; //广告 id
   private String bannerImg; //广告图片
   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
   public String getBannerImg() {
      return bannerImg;
   }
   public void setBannerImg(String bannerImg) {
      this.bannerImg = bannerImg;
   }
}