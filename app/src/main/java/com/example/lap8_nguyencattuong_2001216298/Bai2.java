package com.example.lap8_nguyencattuong_2001216298;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Bai2 extends AppCompatActivity {
    Button btnAdd2, btnLoad2;
    TextView tvContent2;
    EditText edtName2, edtClass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);


        addControl();



        addEvent();

    }

    void addControl()
    {
edtName2 = (EditText)  findViewById(R.id.edtClass2);
edtClass2 = (EditText) findViewById(R.id.edtName2);
tvContent2 = (TextView) findViewById(R.id.tvContent2);
btnAdd2 = (Button) findViewById(R.id.btnAdd2);
btnLoad2 = (Button) findViewById(R.id.btnLoad2);
    }

    void addEvent()
    {


        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OutputStream outputStream = openFileOutput("dssv.txt",MODE_APPEND);
                    String st = "\n" +edtName2.getText().toString() + ";;" + edtClass2.getText().toString()  +"===";
                    outputStream.write(st.getBytes());
                    outputStream.close();
                    Toast.makeText(Bai2.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnLoad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    tvContent2.setText("");
                    List<String> dsMaSinhVien = new ArrayList<String>();
                    int dem = 0;
                    InputStream inputStream  = openFileInput("dssv.txt");
                    int size = inputStream.available();
                    byte []buf  = new byte[size];
                    inputStream.read(buf);
                    inputStream.close();
                    String kq = new String(buf);
                    String [] dsSinhVien = kq.split("===");
                    for (int i = 0; i < dsSinhVien.length; i++)
                    {
                        dem ++;
                        String info = "";

                        String [] sinhVien = dsSinhVien[i].split(";;");
                        info =     (sinhVien[0].toString() + "\n" + sinhVien[1].toString())+ "\n";

                        tvContent2.append(info);
                        String tempLop = "";
                        tempLop = sinhVien[0].toString();
//                 Toast.makeText(Bai2.this, tempLop, Toast.LENGTH_SHORT).show();
                        if (dsMaSinhVien.isEmpty())
                        {
                         //   Toast.makeText(Bai2.this, "True", Toast.LENGTH_SHORT).show();
                            dsMaSinhVien.add(tempLop);
                        //    break;
                           // Toast.makeText(Bai2.this, "Đã thêm", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            int flag = 0;
                            for (int j = 0; j < dsMaSinhVien.size(); j++) {
                                //  Toast.makeText(Bai2.this, "Lần " + String.valueOf(flag), Toast.LENGTH_SHORT).show();

                                if (tempLop == dsMaSinhVien.get(j))
                                    flag++;
                            }

                                if (flag > 0)
                                    break;
                                else
                                    dsMaSinhVien.add(tempLop);


                            for (int j = 0; j< dsMaSinhVien.size();j++)
                            {
                                if (sinhVien[0] == dsMaSinhVien.get(j))
                                {
                                    tvContent2.append("\n" + sinhVien[1].toString());
                                }
                            }
                        }

                    }
                    Toast.makeText(Bai2.this, "Đã nhập " + String.valueOf(dem) +" sinh viên", Toast.LENGTH_SHORT).show();
                    tvContent2.setMovementMethod(new ScrollingMovementMethod());

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}