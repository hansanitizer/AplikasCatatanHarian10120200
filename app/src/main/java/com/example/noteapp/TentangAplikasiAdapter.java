package com.example.noteapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.w3c.dom.Text;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class TentangAplikasiAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public int[] list_gambar = {
            R.drawable.info,
            R.drawable.about
    };

    public String[] list_title = {
            "TENTANG APLIKASI",
            "INFO APLIKASI"
    };

    public String[] list_deskripsi = {
            "Aplikasi catatan harian berbasis Android ini dibuat bagi pengguna yang ingin menulis sesuatu dengan mudah dan cepat",
            "Aplikasi ini dibuat untuk memenuhi tugas UTS Aplikasi Komputasi Bergerak"
    };

    public int[] list_bg_warna = {
            Color.rgb(112,165,186),
            Color.rgb(112,165,186)
    };

    public  TentangAplikasiAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_viewpager,container, false);
        LinearLayout linearLayout = view.findViewById(R.id.slider);
        ImageView imageView = view.findViewById(R.id.slider_img);
        TextView header_text = view.findViewById(R.id.slider_header);
        TextView deskripsi_text = view.findViewById(R.id.slider_deskripsi);
        linearLayout.setBackgroundColor(list_bg_warna[position]);
        imageView.setImageResource(list_gambar[position]);
        header_text.setText(list_title[position]);
        deskripsi_text.setText(list_deskripsi[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        container.removeView((LinearLayout)object);
    }
}
