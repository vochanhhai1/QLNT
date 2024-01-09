package com.example.tt_app.QLPhong.DetailPhongFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tt_app.QLPhong.DetailPhongFragment.NguoiThue;
import com.example.tt_app.QLPhong.DetailPhongFragment.ThongTin;

public class ViewpaperPhongAdapter extends FragmentPagerAdapter {
    public ViewpaperPhongAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new ThongTin();
            case 1:
                return new NguoiThue();
            default:
                return new ThongTin();
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
                title ="Thông tin";
                break;
            case 1:
                title ="Người thuê";
                break;

        }
        return title;
    }
}
