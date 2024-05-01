package com.example.lap8_nguyencattuong_2001216298;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;

public class Bai7 extends AppCompatActivity {
EditText edtUS, edtPW;
Button btnLogin;
ArrayList<TaiKhoan> taiKhoanArrayList = new ArrayList<>();

    @Override
    protected void onPause() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
String us = edtUS.getText().toString();
String pw = edtPW.getText().toString();
    editor.putString("us",us);
    editor.putString("pw",pw);
    editor.commit();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai7);
        Intent intent = getIntent();

        addControl();
SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        edtUS.setText(sharedPreferences.getString("us",null));
        edtPW.setText(sharedPreferences.getString("pw",null));
        addEvent();
    }



    void addControl()
    {
        edtUS = (EditText) findViewById(R.id.edtUS);
        edtPW = (EditText) findViewById(R.id.edtPW);
        btnLogin = (Button) findViewById(R.id.btnLogin);

    }

    void addEvent()
    {

btnLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent  = new Intent(Bai7.this,Bai7ThongTin.class);
        startActivity(intent);
        try
        {
            OutputStream outputStream = openFileOutput("dstk.xml",MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter stringWriter = new StringWriter();

            xmlSerializer.setOutput(stringWriter);
            xmlSerializer.startDocument("UTF-8",true);
            xmlSerializer.startTag(null,"dstk");

            for (int i =0; i< taiKhoanArrayList.size();i++)
            {
                TaiKhoan taiKhoan = taiKhoanArrayList.get(i);
                xmlSerializer.startTag(null,"tk");
                xmlSerializer.startTag(null,"us");
                xmlSerializer.text(taiKhoan.getTaiKhoan());
                xmlSerializer.endTag(null,"tk");

                xmlSerializer.startTag(null,"mk");
                xmlSerializer.text(taiKhoan.getMatKhau());
                xmlSerializer.endTag(null,"mk");



                xmlSerializer.endTag(null,"kt");

            }

            xmlSerializer.endTag(null,"dstk");
            xmlSerializer.endDocument();
            xmlSerializer.flush();

            String dataWrite  = stringWriter.toString();
            outputStream.write(dataWrite.getBytes());
            outputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
});
    }

}