package com.example.tt_app.QLHopdong.HopdongFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tt_app.QLHopdong.HopdongFragment.DangHoatDong;
import com.example.tt_app.QLHopdong.HopdongFragment.DangThanhLy;
import com.example.tt_app.QLHopdong.HopdongFragment.QuaHan;

public class ViewpaperAdapter extends FragmentPagerAdapter {
    public ViewpaperAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new DangHoatDong();
            case 1:
                return new QuaHan();
            case 2:
                return new DangThanhLy();
            default:
                return new DangHoatDong();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title ="";
        switch (position)
        {
            case 0:
                title ="Đang hoạt động";
                break;
            case 1:
                title ="Quá hạn";
                break;
            case 2:
                title ="Đang thanh lý";
                break;
        }
        return title;
    }
}
