package com.example.control.custom;

import com.example.control.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ImageBtn extends RelativeLayout {
 
    private ImageView imageView;
    private TextView  textView;
     
    public ImageBtn(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public ImageBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_imagebtn, this);
        imageView=(ImageView) findViewById(R.id.imageView1);
        textView=(TextView)findViewById(R.id.textView1);
    }
     
    /**
     * ����ͼƬ��Դ
     */ 
    public void setImageResource(int resId) { 
        imageView.setImageResource(resId); 
    } 
   
    /**
     * ������ʾ������
     */ 
    public void setTextViewText(String text) { 
        textView.setText(text); 
    } 
 
}