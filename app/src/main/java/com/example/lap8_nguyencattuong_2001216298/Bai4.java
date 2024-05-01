package com.example.lap8_nguyencattuong_2001216298;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Bai4 extends AppCompatActivity {
Button btnLoadDataCasi;
ListView  lvDanhSachCaSi;
ArrayList<String> arrayListCaSi = new ArrayList<>();
ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);
        addControl();
        addEvent();
    }

    void addControl()
    {
        btnLoadDataCasi = (Button) findViewById(R.id.btnLoadDataCasi);
        lvDanhSachCaSi = (ListView) findViewById(R.id.lvDanhSachCaSi);
    }

    void addEvent()
    {
        btnLoadDataCasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                arrayListCaSi = loadJsonCS();
                adapter = new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrayListCaSi);
                lvDanhSachCaSi.setAdapter(adapter);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public ArrayList<String> loadJsonCS () throws IOException, JSONException
    {
        ArrayList<String> arrayList = new ArrayList<>();
        // Đọc file bằng inputStream
        InputStream inputStream = getResources().getAssets().open("dscasi.json");
        int size = inputStream.available();
        byte[] data = new byte[size];
        inputStream.read(data);
        inputStream.close();

        String kqDoc = new String(data,"UTF-8");
        // Tách dữ liệu từng Object trong chuôi kqDoc đưa về  ArrayList
        JSONObject jsonObject = new JSONObject(kqDoc);
        JSONArray jsonArray = jsonObject.getJSONArray("dscs");
        for (int i = 0; i< jsonArray.length(); i++)
        {
            JSONObject object = jsonArray.getJSONObject(i);
            String ht = object.getString("hten");
            String qg = object.getString("qgia");
            String loai = object.getString("loai");
            String st = ht + "--" + qg + "--" + loai;
            arrayList.add(st);
        }
            return arrayList;
    }
}