package a.com.example.administrator.myapplication.Bean;

import java.io.Serializable;

public class ExercisesDetailBean implements Serializable {
    //序列化时保持 ExercisesDetailBean 类版本的兼容性
    private static final long serialVersionUID = 1L;
    private int subjectId; //习题 Id
    private String subject; //题干
    private String a; // A 选项
    private String b; // B 选项
    private String c; // C 选项
    private String d; // D 选项
    private int answer; //正确答案
    /**
     * select 为 0 表示所选项是对的，1 表示选中的 A 选项是错的，2 表示选中的 B 选项是错的，
     * 3 表示选中的 C 选项是错的，4 表示选中的 D 选项是错的
     */
    private int select;
    public int getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
    public String getA() {
        return a;
    }
    public void setA(String a) {
        this.a = a;
    }
    public String getB() {
        return b;
    }
    public void setB(String b) {
        this.b = b;
    }
    public String getC() {
        return c;
    }
    public void setC(String c) {
        this.c = c;
    }
    public String getD() {
        return d;
    }
    public void setD(String d) {
        this.d = d;
    }
    public int getAnswer() {
        return answer;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }
    public int getSelect() {
        return select;
    }
    public void setSelect(int select) {
        this.select = select;
    }
}