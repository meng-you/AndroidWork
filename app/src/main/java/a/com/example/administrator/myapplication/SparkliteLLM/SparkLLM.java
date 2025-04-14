package a.com.example.administrator.myapplication.SparkliteLLM;

import android.content.Context;
import android.widget.Toast;

import com.iflytek.sparkchain.core.LLM;
import com.iflytek.sparkchain.core.LLMConfig;
import com.iflytek.sparkchain.core.LLMFactory;
import com.iflytek.sparkchain.core.LLMOutput;
import com.iflytek.sparkchain.core.Memory;
import com.iflytek.sparkchain.core.SparkChain;
import com.iflytek.sparkchain.core.SparkChainConfig;

class SparkLLM {
    public String content;
    public SparkLLM(String question, Context appContext){
        SparkChainConfig config =  SparkChainConfig.builder()
                .appID("b94c8ecc")
                .apiKey("e0e2f518061d1adb59e4cb9dc01197d0")
                .apiSecret("ZTk5ZjBjZjg3MTM0ZTQxMWQxNDEzYWI3");
        int ret = SparkChain.getInst().init(appContext, config);
//
        Memory window_memory = Memory.windowMemory(5);
//        /****** *文本交互 *******/
        LLMConfig chat_llmConfig = LLMConfig.builder()
                .domain("lite")
                .url("wss://spark-api.xf-yun.com/v1.1/chat");
        LLM chat_llm = LLMFactory.textGeneration(chat_llmConfig,window_memory);
        LLMOutput syncOutput = chat_llm.run(question);
        content       = syncOutput.getContent();//获取调用结果
        String syncRaw       = syncOutput.getRaw();//星火原始回复
        int errCode          = syncOutput.getErrCode();//获取结果ID,0:调用成功，非0:调用失败
        String errMsg        = syncOutput.getErrMsg();//获取错误信息
        //System.out.println("content:"+content+" syncRaw:"+syncRaw+" errCode"+errCode+" errMsg"+errMsg);
        SparkChain.getInst().unInit();
    }
}

//Context appContext = getApplicationContext();
//
//question = et1.getText().toString();
//
//        if (!question.isEmpty()){
//SparkLLM sparkLLM = new SparkLLM(question,appContext);
//String answer = sparkLLM.content;
//            tv1.setText(answer);
//        }else {
//                Toast.makeText(this, "您还没有输入问题哦",Toast.LENGTH_SHORT).show();
//        }