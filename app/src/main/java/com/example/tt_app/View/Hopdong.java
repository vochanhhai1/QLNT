package com.example.tt_app.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tt_app.Adapter.ViewpaperAdapter;
import com.example.tt_app.MainActivity;
import com.example.tt_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class Hopdong extends AppCompatActivity {

    private TabLayout mTabLayout;
    ImageView PhongBack;
    private ViewPager viewPager;
    FloatingActionButton fab_hopdong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hopdong);

        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        anhxaid();

        ViewpaperAdapter viewpaperAdapter = new ViewpaperAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewpaperAdapter);
        mTabLayout.setupWithViewPager(viewPager);

        PhongBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hopdong.this, MainActivity.class);
                startActivity(intent);
            }
        });
        fab_hopdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hopdong.this,ThemHopdong.class);
                startActivity(intent);
            }
        });
    }

    private void anhxaid() {
        mTabLayout= findViewById(R.id.tablayerHopdong);
        viewPager = findViewById(R.id.viewPager);
        PhongBack= findViewById(R.id.PhongBack);
        fab_hopdong= findViewById(R.id.fab_hopdong);
    }
}