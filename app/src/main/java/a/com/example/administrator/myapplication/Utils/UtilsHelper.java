package a.com.example.administrator.myapplication.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import a.com.example.administrator.myapplication.activity.RegisterActivity;

public class UtilsHelper {
    /**
     * 判断 SharedPreferences 文件中是否存在要保存的用户名
     */
    public static boolean isExistUserName(Context context,String userName){
        boolean has_userName=false;
        SharedPreferences sp = context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String spPsw = sp.getString(userName,"");
        if (!TextUtils.isEmpty(spPsw)){
            has_userName = true;
        }
        return has_userName;
    }
    public static void saveUserInfo(Context context,String userName,String psw){
        //将密码用 MD5 加密
        String md5Psw = MD5Utils.md5(psw);
        //获取 SharedPreferences 类的对象 sp
        SharedPreferences sp = context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        //获取编辑器对象 editor
        SharedPreferences.Editor editor = sp.edit();
        //将用户名和密码封装到编辑器对象 editor 中
        editor.putString(userName,md5Psw);
        editor.commit();//提交保存信息
    }
    //根据用户名读取 SharedPreferences 文件中的密码
    public static String readPsw(Context context,String userName){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        String spPsw=sp.getString(userName, "");
        return spPsw;
    }
    //保存登录状态与用户名
    public static void saveLoginStatus(Context context,
                                       boolean status,String userName){
        SharedPreferences sp= context.getSharedPreferences
                ("loginInfo",Context.MODE_PRIVATE);
        //获取编辑器
        SharedPreferences.Editor editor=sp.edit();
        //存入 boolean 类型的登录状态
        editor.putBoolean("isLogin", status);
        //存入登录时的用户名
        editor.putString("loginUserName", userName);
        //提交修改
        editor.commit();
    }
    //获取登录状态
    public static boolean readLoginStatus(Context context) {
        SharedPreferences sp = context.getSharedPreferences
                ("loginInfo",Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }
    //清除登录状态与用户名
    public static void clearLoginStatus(Context context) {
        SharedPreferences sp = context.getSharedPreferences
                ("loginInfo",Context.MODE_PRIVATE);
        //获取编辑器
        SharedPreferences.Editor editor = sp.edit();
        //清除登录状态
        editor.putBoolean("isLogin", false);
        //清除登录时的用户名
        editor.putString("loginUserName", "");
        //提交修改
        editor.commit();
    }
    //获取登录时的用户名
    public static String readLoginUserName(Context context){
        SharedPreferences sp=context.getSharedPreferences
                ("loginInfo",Context.MODE_PRIVATE);
        String userName=sp.getString("loginUserName", "");
        return userName;
    }
    //判断是否用户名重复，根据不同结果给出不同提示
    public static boolean validateUsername(Context context,String username) {
        boolean isUsernameValid = false;
        if (UtilsHelper.isExistUserName(context, username)) {
            isUsernameValid = false;
        } else {
            isUsernameValid = true;
        }
        return isUsernameValid;
    }
    //判断是否为数字字母混合的方法,根据不同结果给出不同提示
    public static boolean validatePassword(String password){
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            // 同时有数字和字母退出
            if (hasLetter && hasDigit) {
                break;
            }
        }
        //为真则表示同时有数字和字母
        boolean isPasswordValid = hasLetter && hasDigit;
        return isPasswordValid;
    }
}
