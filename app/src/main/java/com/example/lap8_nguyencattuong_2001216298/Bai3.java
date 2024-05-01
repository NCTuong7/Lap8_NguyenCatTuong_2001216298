package com.example.lap8_nguyencattuong_2001216298;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class Bai3 extends AppCompatActivity {
Button btnReadFileXml, btnSaveFileXml;
TextView tvContent3;
ArrayList <NhanVien> nhanVienArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        Intent intent = getIntent();

        //gắn id cho các control
        addControl();

        // Xử lý sự kiện
        addEvent();
    }

    void addControl()
    {
        btnReadFileXml = (Button) findViewById(R.id.btnReadFileXml);
        btnSaveFileXml = (Button) findViewById(R.id.btnSaveFileXml);
        tvContent3 = (TextView) findViewById(R.id.tvContent3);

    }

    void addEvent()
    {
            btnReadFileXml.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readDanhSachNhanVien();

                    String kq = "";
                    for (NhanVien nhanVien:nhanVienArrayList)
                    {
                        kq+= nhanVien.printNhanVien();

                    }

                    tvContent3.setText(kq);
                }
            });

            btnSaveFileXml.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    writeDanhSachNhanVien();
                }
            });
    }

    //Hàm đọc dữ liệu từ file xml đưa vào arrayList
    public  void readDanhSachNhanVien()
    {
        try {
            XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
            XmlPullParser parser  = fc.newPullParser();

            InputStream inputStream = openFileInput("dsnv.xml");
            parser.setInput(inputStream,"UTF-8");

//            InputStream inputStream = getResources().getAssets().open("dsnv.xml");
//            parser.setInput(inputStream,"UTF-8");
            int tagType  = -1;
            String nodeName;

            //đọc file
            while (tagType != XmlPullParser.END_DOCUMENT) // chưa kết thúc file
            {
                tagType = parser.next();  // duyệt từng dòng trong file
                switch (tagType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.END_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:  // thẻ mở cho một dữ liệu
                        nodeName = parser.getName();

                        if (nodeName.equals("nv"))  // kiểm tra đúng tag mình cần xử lý không?
                        {
                            NhanVien nhanVien = new NhanVien();
                            while (tagType!=XmlPullParser.END_TAG)
                            {
                                tagType = parser.next();
                                nodeName = parser.getName();
                                if (tagType == XmlPullParser.START_TAG && nodeName.equals("msnv"))
                                {
                                    nhanVien.setMsnv(parser.nextText());

                                }

                                else if (tagType == XmlPullParser.START_TAG && nodeName.equals("ten"))
                                {
                                    nhanVien.setTen(parser.nextText());
                                }

                                else if (tagType == XmlPullParser.START_TAG && nodeName.equals("sdt"))
                                {
                                    nhanVien.setSdt(parser.nextText());
                                }

                                else  if(tagType == XmlPullParser.START_TAG && nodeName
                                        .equals("chucvu"))
                                {
                                    nhanVien.setChucvu(parser.nextText());
                                }
                            }
                            nhanVienArrayList.add(nhanVien);
                        }
                }
            }
            inputStream.close();
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Hàm ghi danh sách nhân viên xuống file xml
    public void writeDanhSachNhanVien()
    {
        try
        {
          OutputStream outputStream = openFileOutput("dsnv2.xml",MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter stringWriter = new StringWriter();

            xmlSerializer.setOutput(stringWriter);
            xmlSerializer.startDocument("UTF-8",true);
            xmlSerializer.startTag(null,"dsnv");

            for (int i =0; i< nhanVienArrayList.size();i++)
            {
                NhanVien nhanVien = nhanVienArrayList.get(i);
                xmlSerializer.startTag(null,"nv");
                xmlSerializer.startTag(null,"msnv");
                xmlSerializer.text(nhanVien.getMsnv());
                xmlSerializer.endTag(null,"msnv");

                xmlSerializer.startTag(null,"ten");
                xmlSerializer.text(nhanVien.getTen());
                xmlSerializer.endTag(null,"ten");

                xmlSerializer.startTag(null,"sdt");
                xmlSerializer.text(nhanVien.getSdt());
                xmlSerializer.endTag(null,"sdt");

                xmlSerializer.startTag(null,"chucvu");
                xmlSerializer.text(nhanVien.getChucvu());
                xmlSerializer.endTag(null,"chucvu");

                xmlSerializer.endTag(null,"nv");

            }

            xmlSerializer.endTag(null,"dsnv");
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
}