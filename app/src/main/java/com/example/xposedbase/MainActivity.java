package com.example.xposedbase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        final EditText phone_edt = (EditText) findViewById(R.id.phone);
        final EditText message_edt = (EditText) findViewById(R.id.message);

        final EditText phone2_edt = (EditText) findViewById(R.id.IDC_PHONE2_ED);
        final EditText path_edt = (EditText) findViewById(R.id.IDC_PATH_ED);
        final Button sendpic_bt = (Button) findViewById(R.id.IDC_SENDPIC_BT);
        final Button start_bt = (Button) findViewById(R.id.IDC_START_BT);

        final Button line_test_bt = (Button)findViewById(R.id.IDC_LINETEST_BT);
        sendpic_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String phone2 = phone2_edt.getText().toString();
                String path = path_edt.getText().toString();
                Intent intent = new Intent("com.Xposebase.onClick2");
                intent.putExtra("phone", phone2);
                intent.putExtra("path", path);
                sendBroadcast(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messagae = message_edt.getText().toString();
                String phone = phone_edt.getText().toString();
                Intent intent = new Intent("com.Xposebase.onClick");
                intent.putExtra("messagae", messagae);
                intent.putExtra("phone", phone);
                sendBroadcast(intent);

            }
        });

        start_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                ComponentName cmp = new ComponentName("com.whatsapp", "com.whatsapp.HomeActivity");
                intent2.setAction(Intent.ACTION_MAIN);
                intent2.addCategory(Intent.CATEGORY_LAUNCHER);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent2.setComponent(cmp);
                startActivity(intent2);
            }
        });

        line_test_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messagae = message_edt.getText().toString();
                String phone = phone_edt.getText().toString();
                Intent intent = new Intent("com.Xposebase.line.onClick");
                intent.putExtra("messagae", messagae);
                intent.putExtra("phone", phone);
                sendBroadcast(intent);
            }
        });
    }
}