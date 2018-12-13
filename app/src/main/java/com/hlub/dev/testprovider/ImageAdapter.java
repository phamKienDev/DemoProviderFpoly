package com.hlub.dev.testprovider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class ImageAdapter extends BaseAdapter {

    List<String> list;
    Context context;


    public ImageAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageApp imgRow;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_image, null);

            //lay cac control
            imgRow = new ImageApp();
            imgRow.hinh = view.findViewById(R.id.imgAnh);

            view.setTag(imgRow);
        } else {
            imgRow = (ImageApp) view.getTag();
        }
        //gan du lieu
        File imgFile = new File(list.get(i));

        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


        imgRow.hinh.setImageBitmap(myBitmap);

        return view;
    }
    public static class ImageApp{
        public ImageView hinh;
    }




}
