package com.example.lap8_nguyencattuong_2001216298;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Bai1 extends AppCompatActivity {
Button btnReadFile, btnSaveFile;
TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        addControl();
        addEvent();

    }

    void addControl()
    {
        btnReadFile = (Button) findViewById(R.id.btnReadFile);
        btnSaveFile = (Button) findViewById(R.id.btnSaveFile);
        tvContent = (TextView) findViewById(R.id.tvContent);

    }

    void addEvent()
    {
        btnReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try {
                InputStream in = openFileInput("readfiledemo.txt");
                int size = in.available();
                byte[] buffer = new byte[size];
                in.read(buffer);
                in.close();
                tvContent.setText(new String (buffer) + "\n" + "Tào lao");


                    } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            }
        });

        btnSaveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              try {
                  OutputStream op = openFileOutput("readfiledemo.txt",MODE_APPEND);
                  String st = "Xin chào bạn";
                  op.write(st.getBytes());
                  op.close();
                  Toast.makeText(Bai1.this, "Thành công", Toast.LENGTH_SHORT).show();

              } catch (FileNotFoundException e) {
                  throw new RuntimeException(e);
              } catch (IOException e) {
                  throw new RuntimeException(e);
              }

            }


        });
    }
}