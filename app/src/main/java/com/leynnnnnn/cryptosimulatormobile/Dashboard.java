package com.leynnnnnn.cryptosimulatormobile;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class Dashboard extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        // Getting the tab layout and view pager from the layout
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);
        // Creating an instance of adapter
        ViewPagesAdapter adapter = new ViewPagesAdapter(this);
        // Setting the adapter for view pager
        viewPager2.setAdapter(adapter);

//        Overall, this code ensures that when a user selects a tab in the TabLayout,
//        the corresponding page is displayed in the ViewPager2, providing a seamless
//        user experience for navigating between tabs and pages.
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        this code ensures that there is a bidirectional synchronization between the ViewPager2 and the TabLayout.
//        When the user selects a tab in the TabLayout, the corresponding page is displayed in the ViewPager2,
//        and when the user swipes to a new page in the ViewPager2, the corresponding tab is selected in the TabLayout.
//        This provides a consistent and intuitive user experience for navigating between tabs and pages.
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}