package com.example.stck6.dcms_admin;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class notice extends Activity {

    EditText notice[] = new EditText[3];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);
        notice[0] = (EditText) findViewById(R.id.notice_1);
        notice[1] = (EditText) findViewById(R.id.notice_2);
        notice[2] = (EditText) findViewById(R.id.notice_3);
    }


    public void onClick2(View view) {
        switch (view.getId()) {
            case R.id.notice_up:
                async_task async_task = new async_task();
                async_task.execute();
                break;
        }
    }
    class async_task extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            Log.e("dsf","dsf");
            try {
                byte trash[] = new byte[10];

                Socket socket = new Socket("192.168.0.15", 8002);

                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();

                outputStream.write("1".getBytes());
                inputStream.read(trash);
                outputStream.write((notice[0].getText().toString()).getBytes());
                inputStream.read(trash);
                outputStream.write((notice[1].getText().toString()).getBytes());
                inputStream.read(trash);
                outputStream.write((notice[2].getText().toString()).getBytes());
                inputStream.read(trash);

                Log.e("dd","DD");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}