package com.example.tt_app.QLHoadon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_app.QLHoadon.Adapter.ChonphongAdapter;
import com.example.tt_app.R;
import com.example.tt_app.model.DataPhong;

import java.text.DecimalFormat;

public class Laphoadon extends AppCompatActivity {

    ImageView laphoadonBack;
    TextView rlChonphong, tvTiencoc,tvGiaphong,tvGiadien,tvGianuoc,TotalDien,TotalNuoc,tvttTienphong,tvttDichvu,tvttTotal,tvttThanhtien;
    String tenphong;

    public CardView cvHopdong;
    LinearLayout lltienphong, lldichvu;
    Integer gianuoc,giadien,tienphong;
    CheckBox chkSelectedNuoc,chkSelectedDien;
    EditText edtNuocsocu,edtNuocsomoi,editDiensocu,edtDiensomoi;
    private int tienDien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laphoadon);
        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        anhxaid();
        DecimalFormat dcf = new DecimalFormat("###,###,###");


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            DataPhong obj = (DataPhong) bundle.getSerializable("dataphong");
            tenphong = obj.getPhong();
            tienphong = obj.getChiphithue();
            gianuoc= obj.getGianuoc();
            giadien = obj.getGiadien();
        }

        if (tienphong != null |tenphong != null | gianuoc != null | giadien != null) {
            tvTiencoc.setText(dcf.format(Double.parseDouble(String.valueOf(tienphong))) + "đ");
            rlChonphong.setText("Phòng số " + tenphong);
            tvGiaphong.setText(dcf.format(Double.parseDouble(String.valueOf(tienphong))) + "đ");
            tvGianuoc.setText(dcf.format(Integer.parseInt(String.valueOf(gianuoc))) + " đ/m\u00B3");
            tvGiadien.setText(dcf.format(Integer.parseInt(String.valueOf(giadien))) + " đ/Kwh");
            tvttTienphong.setText(dcf.format(Double.parseDouble(String.valueOf(tienphong))) + "đ");

        } else {
            tvTiencoc.setText("0đ");
            tvGianuoc.setText("0đ");
            tvGiadien.setText("0đ");
            rlChonphong.setHint("Chọn phòng");
        }

        Toast.makeText(getApplicationContext(),""+tienDien,Toast.LENGTH_SHORT).show();

        chkSelectedNuoc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    try {
                        tinhtiennuoc();
                    } catch (NumberFormatException e) {

                    }
                }else
                {
                    TotalNuoc.setText("0 đ");
                }
            }
        });

        chkSelectedDien.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {

                    try {
                        tinhtiendien();

                    } catch (NumberFormatException e) {

                    }
                }else
                {
                    TotalDien.setText("0 đ");
                }
            }
        });
        validate();
        cvHopdong.setVisibility(View.VISIBLE);
        lltienphong.setVisibility(View.VISIBLE);
        lldichvu.setVisibility(View.VISIBLE);
        rlChonphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChonPhong.class);
                startActivity(intent);


            }
        });

        laphoadonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HoaDon.class);
                startActivity(intent);
            }
        });
    }

    private void tinhtiennuoc() {
        int Nuocsomoi =Integer.parseInt(edtNuocsomoi.getText().toString());
        int Nuocsocu = Integer.parseInt(edtNuocsocu.getText().toString());
        DecimalFormat dcf = new DecimalFormat("###,###,###");
        if (Nuocsomoi < Nuocsocu || Nuocsocu < 0 || Nuocsomoi < 0) {
            TotalNuoc.setText("0 đ");
        } else {
            int soNuocSuDung = Nuocsomoi - Nuocsocu;
            int tienNuoc = soNuocSuDung * gianuoc;
//            Toast.makeText(this,""+tienNuoc,Toast.LENGTH_SHORT).show();
            TotalNuoc.setText(dcf.format(Integer.parseInt(String.valueOf(tienNuoc))) + "đ");
        }
    }

    private void tinhtiendien() {
        int Diensomoi =Integer.parseInt(edtDiensomoi.getText().toString());
        int Diensocu = Integer.parseInt(editDiensocu.getText().toString());
        DecimalFormat dcf = new DecimalFormat("###,###,###");
        if (Diensomoi < Diensocu || Diensocu < 0 || Diensomoi < 0) {
            TotalDien.setText("0 đ");
        } else {
            int soDienSuDung = Diensomoi - Diensocu;
            tienDien = soDienSuDung * giadien;
            TotalDien.setText(dcf.format(Integer.parseInt(String.valueOf(tienDien))) + "đ");
        }
    }

    private void validate()
    {
        edtNuocsomoi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (chkSelectedNuoc.isChecked()) {
                    try {
                        tinhtiennuoc();
                    } catch (NumberFormatException e) {

                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtNuocsocu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (chkSelectedNuoc.isChecked()) {
                    try {
                        tinhtiennuoc();
                    } catch (NumberFormatException e) {

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtDiensomoi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (chkSelectedNuoc.isChecked()) {
                    try {
                        tinhtiendien();
                    } catch (NumberFormatException e) {

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editDiensocu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (chkSelectedNuoc.isChecked()) {
                    try {
                        tinhtiendien();
                    } catch (NumberFormatException e) {

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    private void anhxaid() {
        laphoadonBack = findViewById(R.id.laphoadonBack);
        rlChonphong = findViewById(R.id.rlChonphong);
        cvHopdong = findViewById(R.id.cvHopdong);
        lltienphong = findViewById(R.id.lltienphong);
        lldichvu = findViewById(R.id.lldichvu);
        tvTiencoc = findViewById(R.id.tvTiencoc);
        tvGiaphong= findViewById(R.id.tvGiaphong);
        tvGiadien= findViewById(R.id.tvGiadien);
        tvGianuoc= findViewById(R.id.tvGianuoc);
        chkSelectedNuoc = findViewById(R.id.chkSelectedNuoc);
        edtNuocsocu= findViewById(R.id.edtNuocsocu);
        edtNuocsomoi= findViewById(R.id.edtNuocsomoi);
        TotalNuoc=findViewById(R.id.TotalNuoc);
        chkSelectedDien= findViewById(R.id.chkSelectedDien);
        editDiensocu = findViewById(R.id.editDiensocu);
        edtDiensomoi = findViewById(R.id.edtDiensomoi);
        TotalDien = findViewById(R.id.totalDien);
        tvttTienphong = findViewById(R.id.tvttTienphong);
        tvttDichvu= findViewById(R.id.tvttDichvu);
        tvttTotal = findViewById(R.id.tvttTotal);
        tvttThanhtien = findViewById(R.id.tvttThanhtien);
    }
}