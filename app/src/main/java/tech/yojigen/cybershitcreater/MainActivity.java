package tech.yojigen.cybershitcreater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            // 设置要生成的中文字符串长度
            int length = 20 + random.nextInt(20);

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < length; i++) {
                sb.append(getRandomChar());
            }

            String text = sb.toString();
            ClipData clip = ClipData.newPlainText("赛博狗屎", text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        });

        TextView mouyase = findViewById(R.id.mouyase);
        TextView create_by = findViewById(R.id.create_by);

        // 创建一个Handler对象
        Handler handler = new Handler();
        // 创建一个Runnable对象
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 这里写需要定时执行的逻辑代码
                mouyase.setTextColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                create_by.setTextColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                // 重复调度该Runnable对象，每隔1000ms（1秒）执行一次
                handler.postDelayed(this, 100);
            }
        };

        // 开始定时任务
        handler.post(runnable);
    }

    private String getRandomChar() {
        int type = random.nextInt(7);

        int start = 0;
        int end = 0;

        switch (type) {
            case 0:
                // 4E00-9FBF：CJK 统一表意文字 (CJK Unified Ideographs)
                start = 0x4E00;
                end = 0x9FBF;
                break;
            case 1:
                // 3040-309F：日文平假名 (Hiragana)
                start = 0x3040;
                end = 0x309F;
                break;
            case 2:
                // 0900-097F：天城文 (Devanagari)
                start = 0x0900;
                end = 0x097F;
                break;
            case 3:
                // 0E00-0E7F：泰文 (Thai)
                start = 0x0E00;
                end = 0x0E7F;
                break;
            case 4:
                // 2300-23FF：杂项工业符号 (Miscellaneous Technical)
                start = 0x2300;
                end = 0x23FF;
                break;
            case 5:
                // 0600-06FF：阿拉伯文 (Arabic)
                start = 0x0600;
                end = 0x06FF;
                break;
            case 6:
                // AC00-D7AF：朝鲜文音节 (Hangul Syllables)
                start = 0xAC00;
                end = 0xD7AF;
                break;
            default:
                // 0000-007F：C0控制符及基本拉丁文 (C0 Control and Basic Latin)
                start = 0x0000;
                end = 0x007f;
                break;
        }
        int unicodeValue = start + random.nextInt(end - start);
        StringBuilder sb = new StringBuilder();
        try {
            byte[] bytes = String.valueOf((char) unicodeValue).getBytes(StandardCharsets.UTF_8);
            sb.append(new String(bytes, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}