package com.example.tt_app.QLNguoithue.NguoiThueFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tt_app.QLNguoithue.NguoiThueFragment.ChuaCoPhong;
import com.example.tt_app.QLNguoithue.NguoiThueFragment.DaCoPhong;

public class ViewpagerNguoiThue extends FragmentPagerAdapter {
    public ViewpagerNguoiThue(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new DaCoPhong();
            case 1:
                return new ChuaCoPhong();
            default:
                return new DaCoPhong();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title ="";
        switch (position)
        {
            case 0:
                title ="Đã có phòng";
                break;
            case 1:
                title ="Chưa có phòng";
                break;
        }
        return title;
    }

}
