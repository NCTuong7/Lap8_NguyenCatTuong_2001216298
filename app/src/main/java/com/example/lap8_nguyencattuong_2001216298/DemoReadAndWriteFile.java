package com.example.lap8_nguyencattuong_2001216298;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DemoReadAndWriteFile extends AppCompatActivity {
Button btnReadFileDemo, btnWriteFileDemo;
TextView tvContentDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_read_and_write_file);
        addControl();
        addEvent();
    }

    void addControl()
    {
        btnReadFileDemo = (Button) findViewById(R.id.btnReadFileDemo);
        btnWriteFileDemo = (Button) findViewById(R.id.btnWriteFileDemo);
        tvContentDemo = (TextView) findViewById(R.id.tvContentDemo);
    }

    void addEvent()
    {
        btnReadFileDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try {
//              InputStream in=getResources().getAssets().open("dstruyendemo.txt");
                InputStream in = openFileInput("dstruyendemo.txt");
                int size = in.available();
                byte [] data  = new byte[size];
                in.read(data);
                in.close();
                String stKq = new String(data);
                tvContentDemo.setText(stKq);

                String[] dsTruyen = stKq.split("===");

                for (int i = 0; i < dsTruyen.length; i++)
                {
                    String [] truyen = dsTruyen[i].split(";;");
                    System.out.println("Tên truyện " + truyen[0] + "Nội dung truyện " + truyen[1]);
                }

                tvContentDemo.setMovementMethod( new ScrollingMovementMethod());


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            }
        });

        btnWriteFileDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    OutputStream out = openFileOutput("dstruyendemo.txt",MODE_APPEND);

                    String stKq = "\n " + tvContentDemo.getText().toString();
                    out.write(stKq.getBytes());
                    out.close();
                    Toast.makeText(DemoReadAndWriteFile.this, "Ghi thành công vào file", Toast.LENGTH_SHORT).show();
                }

                catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}