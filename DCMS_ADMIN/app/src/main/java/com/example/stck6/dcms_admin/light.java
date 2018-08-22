package com.example.stck6.dcms_admin;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class light extends Activity {
    int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light);
    }

    public void onClick12(View view) {
        switch (view.getId()) {
            case R.id.aa:
                asynkTask1 asyncTask = new asynkTask1();
                asyncTask.execute();
        }
    }

    class asynkTask1 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Log.e("sdf", "sdf");
                Socket socket = new Socket("192.168.0.15", 8002);
                OutputStream outputStream = socket.getOutputStream();
                if (i == 0) {
                    i++;
                    outputStream.write(("2" + String.valueOf(i)).getBytes());
                } else {
                    i--;
                    outputStream.write(("2" + String.valueOf(i)).getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
