package com.example.mainqlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivitySinhVien extends AppCompatActivity {
    ListView lsvSinhVien;
    ImageView imgBack;
    Dialog dialog;
    viewDialog viewDialog;
    SinhVienAdapter adapter;
    TuongTacDatabase database;

    int h;

    boolean them = true, sua = false, xoa = false;
    SinhVien sv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_vien);
        lsvSinhVien = (ListView) findViewById(R.id.lsvSinhVien);
        database = new TuongTacDatabase(this);
        imgBack = (ImageView) findViewById(R.id.imgBackHome);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivitySinhVien.this, ActivityHome.class);
                startActivity(i);
            }
        });

        getDATA();

        adapter = new SinhVienAdapter(this, 0, data.getDt().arrSV);
        lsvSinhVien.setAdapter(adapter);


        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_tsx_sv);
        viewDialog = new viewDialog(dialog);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){

            case R.id.idKhac:
                them = false;
                sua = true;
                xoa =true;
                viewDialog.setbtn1();
                Toast.makeText(ActivitySinhVien.this, "Chọn sinh viên cần sửa", Toast.LENGTH_SHORT).show();
                lsvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        sv = adapter.getItem(i);
                        h = i;
                        viewDialog.setTT(sv);
                        dialog.show();
                    }
                });
                break;
            case R.id.idThem:
                them = true;
                sua = false;
                xoa =false;
                viewDialog.setbtn1();
                lsvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
    public void getDATA(){
        database.open();
        data.getDt().arrSV = new ArrayList<>(database.getAlldata());
        System.out.println("dugh"+ data.getDt().arrSV.size());
        database.close();

    }
    class viewDialog{
        EditText edtTen, edtSDT, edtEmail;
        Button btnThem, btnSua, btnXoa;
        Spinner splLop;
        String lop, gt= "nam";
        RadioGroup GT;

        public viewDialog(Dialog v){
            edtTen = (EditText) v.findViewById(R.id.edtten);
            edtSDT = (EditText) v.findViewById(R.id.edtSDT);
            edtEmail = (EditText) v.findViewById(R.id.edtEmail);

            btnThem = (Button) v.findViewById(R.id.btnThem);
            btnSua = (Button) v.findViewById(R.id.btnSua);
            btnXoa = (Button) v.findViewById(R.id.btnXoa);

            splLop = (Spinner) v.findViewById(R.id.splLop);

            GT = (RadioGroup) v.findViewById(R.id.RGGT);

            setRS();
            setBtn();


        }
        public void setbtn1(){
            btnThem.setEnabled(them);
            btnSua.setEnabled(sua);
            btnXoa.setEnabled(xoa);
        }
        public void setTT(SinhVien sv){
            edtTen.setText(sv.getTen());
            edtSDT.setText(sv.getSodt());
            edtEmail.setText(sv.getEmail());

            if (sv.getGioitinh().equals("nu")){
                GT.check(R.id.nu);
            }
            else {
                GT.check(R.id.nam);
            }

            int c = 0;
            for(int i = 0; i< getResources().getStringArray(R.array.lop).length; i++){
                if (sv.getLophoc().equals(getResources().getStringArray(R.array.lop)[i])){
                    c = i;
                    break;
                }
            }
            splLop.setSelection(c);
        }
        private  void setBtn(){
            btnThem.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {
                    if (GT.getCheckedRadioButtonId() == R.id.nam){
                        gt = "nam";
                    }else {
                        gt = "nu";
                    }
                    data.getDt().arrSV.add(getSV());
                    database.open();
                    database.themSV(getSV());
                    database.close();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });

            btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (GT.getCheckedRadioButtonId() == R.id.nam){
                        gt = "nam";
                    }else {
                        gt = "nu";
                    }
                    SinhVien s = getSV();
                    s.setIdSv(sv.getIdSv());
                    data.getDt().arrSV.set(h,s);
                    database.open();
                    database.suaSV(s);
                    database.close();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });
            btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    data.getDt().arrSV.remove(h);
                    database.open();
                    database.xoaSV(data.getDt().arrSV.get(h).getIdSv());
                    database.close();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();

                }
            });
        }
        private void setRS(){
            splLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    lop=getResources().getStringArray(R.array.lop)[i];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    lop=getResources().getStringArray(R.array.lop)[0];
                }
            });
        }
        private SinhVien getSV(){
            SinhVien sv = new SinhVien();
            sv.setTen(edtTen.getText().toString());
            sv.setEmail(edtEmail.getText().toString());
            sv.setSodt(edtSDT.getText().toString());
            sv.setLophoc(lop);
            sv.setGioitinh(gt);
            return sv;
        }
    }
}