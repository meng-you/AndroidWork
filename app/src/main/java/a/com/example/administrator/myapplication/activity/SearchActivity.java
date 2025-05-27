package a.com.example.administrator.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import a.com.example.administrator.myapplication.SparkliteLLM.SparkLLM;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import a.com.example.administrator.myapplication.R;

public class SearchActivity extends AppCompatActivity{
    private EditText et_question;
    private TextView tv_answer;
    private Button btn_search;
    public String question = "";//新问题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.loadLibrary("SparkChain");  // 对应 libSparkChain.so
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();
    }

    private void init(){
        tv_answer = findViewById(R.id.tv_answer);
        et_question = findViewById(R.id.et_question);
        btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context appContext = getApplicationContext();
                question = et_question.getText().toString();

                if (!question.isEmpty()){
                    SparkLLM sparkLLM = new SparkLLM(question,appContext);
                    String answer = sparkLLM.content;
                    tv_answer.setText(answer);
                }else {
                    Toast.makeText(SearchActivity.this, "您还没有输入问题哦", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}