package com.example.mainqlsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    ArrayList<SinhVien> arr;
    Context ct;

    public SinhVienAdapter(@NonNull Context context, int resource, ArrayList<SinhVien> o) {
        super(context, resource, o);
        this.ct = context;
        this.arr = data.getDt().arrSV;
        System.out.println("dugh"+ arr.size());
    }
    @Override
    public void notifyDataSetChanged(){
        this.arr = data.getDt().arrSV;
        super.notifyDataSetChanged();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        viewHolder v;
        if (row == null){
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_sinh_vien, null );
            v = new viewHolder(row);
            row.setTag(v);
        }
        else {
            v = (viewHolder) row.getTag();
        }

        if (arr.size() > 0 ){
            v.setView(arr.get(position));
        }
        return  row;

        }
        class viewHolder {
            TextView txvTen, txvSDT, txvEmail, txvLop;
            ImageView imgGT;
            public  viewHolder (View v){
                txvTen = (TextView) v.findViewById(R.id.txtTen);
                txvEmail = (TextView) v.findViewById(R.id.txtEmail);
                txvLop = (TextView) v.findViewById(R.id.txtlop);
                txvSDT = (TextView) v.findViewById(R.id.txtSDT);
                imgGT = (ImageView) v.findViewById(R.id.imgGT);

            }
            public void  setView (SinhVien sv){
                txvTen.setText(sv.getTen());
                txvEmail.setText(sv.getEmail());
                txvLop.setText(sv.getLophoc());
                txvSDT.setText(sv.getSodt());

                if (sv.getGioitinh().equals("nu")){
                    imgGT.setImageResource(R.drawable.girl);
                }
                else {
                    imgGT.setImageResource(R.drawable.boy);
                }
            }
    }
}
