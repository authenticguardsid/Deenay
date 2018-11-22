package com.denaay.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.denaay.fragment.NotifFragment;
import com.denaay.fragment.ProfileFragment;
import com.denaay.R;
import com.denaay.fragment.HomeFragment;
import com.denaay.fragment.QrcodeFragment;
import com.denaay.fragment.StoreFragment;

/**
 * Created by M Taufiq R on 15/11/2018.
 */

public class SectionPagesAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SectionPagesAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeFragment();
        } else if (position == 1) {
            return new StoreFragment();
        } else if (position == 2) {
            return new QrcodeFragment();
        } else if (position == 3) {
            return new NotifFragment();
        } else {
            return new ProfileFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

}
