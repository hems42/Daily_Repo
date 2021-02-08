package Adapters.FragmentAdapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class FragmentPagerAdapterHastalar extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragments;
    ArrayList<String> tab_isimleri;


    public FragmentPagerAdapterHastalar(@NonNull FragmentManager fm, Context context, ArrayList<Fragment> fragments, ArrayList<String> tab_isimleri) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        this.fragments=fragments;
        this.tab_isimleri=tab_isimleri;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab_isimleri.get(position);
    }
}
