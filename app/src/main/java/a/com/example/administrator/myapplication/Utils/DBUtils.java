package a.com.example.administrator.myapplication.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import a.com.example.administrator.myapplication.Bean.UserBean;
import a.com.example.administrator.myapplication.sqlite.SQLiteHelper;

public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;
    public DBUtils(Context context) {
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }
    /**
     * 保存用户信息
     */
    public void saveUserInfo(UserBean bean) {
        ContentValues cv = new ContentValues();
        cv.put("userName", bean.getUserName());
        cv.put("nickName", bean.getNickName());
        cv.put("sex", bean.getSex());
        cv.put("signature", bean.getSignature());
        db.insert(SQLiteHelper.U_USERINFO, null, cv);
    }

    /**
     * 获取用户信息
     * @param userName
     * @return
     */
    public UserBean getUserInfo(String userName) {
        String sql = "SELECT * FROM " + SQLiteHelper.U_USERINFO +
                " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{userName});
        UserBean bean = null;
        while (cursor.moveToNext()) {
            bean = new UserBean();
            bean.setUserName(cursor.getString
                    (cursor.getColumnIndex("userName")));
            bean.setNickName(cursor.getString
                    (cursor.getColumnIndex("nickName")));
            bean.setSex(cursor.getString
                    (cursor.getColumnIndex("sex")));
            bean.setSignature(cursor.getString
                    (cursor.getColumnIndex("signature")));
        }
        cursor.close();
        return bean;
    }
    /**
     *修改用户信息
     */
    public void updateUserInfo(String key, String value,
                               String userName) {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        db.update(SQLiteHelper.U_USERINFO, cv, "userName=?",
                new String[]{userName});
    }
    //判断数据库中是否存在视频播放记录数据
    public boolean hasVideoPlay(int chapterId, int videoId,
                                String userName) {
        boolean hasVideo = false;
        String sql = "SELECT * FROM " + SQLiteHelper.U_VIDEO_PLAY_LIST
                + " WHERE chapterId=? AND videoId=? AND userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]
                { chapterId + "",videoId + "" ,userName});
        if (cursor.moveToFirst()) {
            hasVideo = true;
        }
        cursor.close();
        return hasVideo;
    }
//删除数据库中的视频播放记录数据
    public boolean delVideoPlay(int chapterId,
                                int videoId,String userName) {
        boolean delSuccess=false;
        int row = db.delete(SQLiteHelper.U_VIDEO_PLAY_LIST,
                " chapterId=? AND videoId=? AND userName=?",
                new String[] { chapterId
                        + "", videoId + "" ,userName});
        if (row > 0) {
            delSuccess=true;
        }
        return delSuccess;
    }
//保存视频播放记录信息到数据库
    public void saveVideoPlayList(int id,
                                  String chapterName,VideoBean bean, String userName)
    {
        // 判断如果里面有此播放记录则需删除重新存放
        if (hasVideoPlay(id, bean.getVideoId(),userName)) {
            // 删除之前存入的播放记录
            boolean isDelete=delVideoPlay
                    (id, bean.getVideoId(),userName);
            // 没有删除成功时，则需要调用 return 关键字跳出此方法不再执行下面的语句
            if(!isDelete) return;
        }
        ContentValues cv = new ContentValues();
        cv.put("userName", userName);
        cv.put("chapterId", id);
        cv.put("videoId", bean.getVideoId());
        cv.put("videoPath", bean.getVideoPath());
        cv.put("chapterName", chapterName);
        cv.put("videoName", bean.getVideoName());
        cv.put("videoIcon", bean.getVideoIcon());
        db.insert(SQLiteHelper.U_VIDEO_PLAY_LIST, null, cv);
    }
}
