package a.com.example.administrator.myapplication.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import a.com.example.administrator.myapplication.Bean.BannerBean;
import a.com.example.administrator.myapplication.Bean.CourseBean;
import a.com.example.administrator.myapplication.Bean.ExercisesBean;

public class JsonParse {
    private static JsonParse instance;
    private JsonParse() {
    }
    public static JsonParse getInstance() {
        if (instance == null) {
            instance = new JsonParse();
        }
        return instance;
    }
    public List<ExercisesBean> getExercisesList(String json) {
        Gson gson = new Gson();
        // 创建一个 TypeToken 的匿名子类对象，并调用该对象的 getType()方法
        Type listType = new TypeToken<List<ExercisesBean>>() {
        }.getType();
        // 把获取到的数据存放到集合 exercisesList 中
        List<ExercisesBean> exercisesList = gson.fromJson
                (json, listType);
        return exercisesList;
    }
    public List<BannerBean> getBannerList(String json) {
        Gson gson = new Gson();
        // 创建一个 TypeToken 的匿名子类对象，并调用该对象的 getType()方法
        Type listType = new TypeToken<List<BannerBean>>
                () {}.getType();
        // 把获取到的数据存放在集合 bannerList 中
        List<BannerBean> bannerList = gson.fromJson(json, listType);
        return bannerList;
    }
    public List<CourseBean> getCourseList(String json) {
        Gson gson = new Gson();
        // 创建一个 TypeToken 的匿名子类对象，并调用该对象的 getType()方法
        Type listType = new TypeToken<List<CourseBean>>
                () {}.getType();
        // 把获取到的数据存放在集合 courseList 中
        List<CourseBean> courseList = gson.fromJson(json, listType);
        return courseList;
    }
}
