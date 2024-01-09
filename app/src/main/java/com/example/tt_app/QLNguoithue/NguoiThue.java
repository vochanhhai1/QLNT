package com.example.tt_app.QLNguoithue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tt_app.QLNguoithue.NguoiThueFragment.ViewpagerNguoiThue;
import com.example.tt_app.MainActivity;
import com.example.tt_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class NguoiThue extends AppCompatActivity {

    ImageView NguoithueBack;
    FloatingActionButton fab_nguoithue;

    private ViewPager viewPager;
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_thue);

        anhxa();
        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ViewpagerNguoiThue viewpaperAdapter = new ViewpagerNguoiThue(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewpaperAdapter);
        mTabLayout.setupWithViewPager(viewPager);


        fab_nguoithue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent themnguoithue = new Intent(getApplicationContext(), ThemNguoiThue.class);
                startActivity(themnguoithue);
            }
        });


        NguoithueBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nguoithue = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(nguoithue);
            }
        });
    }

//    public void searchList(String text){
//        ArrayList<DataNguoithue> searchList = new ArrayList<>();
//        for (DataNguoithue dataNguoithue: dataNguoithues){
//            if (dataNguoithue.getHovaten().toLowerCase().contains(text.toLowerCase())){
//                searchList.add(dataNguoithue);
//            }
//        }
//        adapter.searchDataList(searchList);
//    }
    private void anhxa() {
        NguoithueBack = findViewById(R.id.NguoithueBack);
        fab_nguoithue= findViewById(R.id.fab_nguoithue);
        mTabLayout= findViewById(R.id.tablayerNguoithue);
        viewPager = findViewById(R.id.viewPagerNguoithue);

    }
}