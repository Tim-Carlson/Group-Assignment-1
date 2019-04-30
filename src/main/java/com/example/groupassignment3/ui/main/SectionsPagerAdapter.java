package com.example.groupassignment3.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.groupassignment3.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;
    private ReadFragment readFragment;
    private WriteFragment writeFragment;
    private AddReadingFragment addReadingFragment;
    private ViewFragment viewFragment;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return readFragment = ReadFragment.newInstance();
        } else if (position == 1) {
            return writeFragment = WriteFragment.newInstance();
        } else if (position == 2) {
            return addReadingFragment = AddReadingFragment.newInstance();
        } else if (position == 3) {
            return viewFragment = ViewFragment.newInstance();
        }
        return PlaceholderFragment.newInstance(5);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }

    public Fragment getReadFragment() {

        return readFragment;

    }
}