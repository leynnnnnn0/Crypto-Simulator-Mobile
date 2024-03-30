package com.leynnnnnn.cryptosimulatormobile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagesAdapter extends FragmentStateAdapter {
    public ViewPagesAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // If else statement that will return the fragment of the chosen page
        switch (position) {
            case 0:
                return new Home();
            case 1:
                return new Portfolio();
            case 2:
                return new Rewards();
            default:
                return new Home();
        }
    }

    // Returning the count of the fragments
    @Override
    public int getItemCount() {
        return 3;
    }
}
