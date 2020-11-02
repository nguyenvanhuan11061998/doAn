package com.example.informationrecognize.main;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseActivity;
import com.example.informationrecognize.main.checkIn.view.CheckInFragment;
import com.example.informationrecognize.main.homeFunction.view.HomeFragment;
import com.example.informationrecognize.main.information.view.InformationFragment;
import com.example.informationrecognize.main.other.mvvm.view.OtherFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.view_pager_main)
    ViewPager viewPagerMain;

    private HomeFragment homeFragment;
    private CheckInFragment checkInFragment;
    private InformationFragment informationFragment;
    private OtherFragment otherFragment;

    private int postionTab = 0;
    private ArrayList<AHBottomNavigationItem> listBottomNavigation;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAct() {
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setCurrentItem(postionTab);
        updateBottomNavigation();
    }

    private void updateBottomNavigation() {
        listBottomNavigation = new ArrayList<>();
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Trang chủ", R.drawable.ic_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Điểm danh", R.drawable.ic_check_in);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Thông tin", R.drawable.ic_information);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Khác", R.drawable.ic_three_dots);

        listBottomNavigation.add(item1);
        listBottomNavigation.add(item2);
        listBottomNavigation.add(item3);
        listBottomNavigation.add(item4);

        bottomNavigation.setAccentColor(Color.parseColor("#218393"));
        bottomNavigation.setInactiveColor(Color.parseColor("#A0AEBB"));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        bottomNavigation.removeAllItems();
        bottomNavigation.addItems(listBottomNavigation);

        initViewPager();
    }

    private void initViewPager() {
        final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        if (homeFragment == null) {
                            homeFragment = HomeFragment.getInstance();
                        }
                        fragment = homeFragment;
                        break;
                    case 1:
                        if (checkInFragment == null) {
                            checkInFragment = CheckInFragment.getInstance();
                        }
                        fragment = checkInFragment;
                        break;
                    case 2:
                        if (informationFragment == null) {
                            informationFragment = InformationFragment.getInstance();
                        }
                        fragment = informationFragment;
                        break;
                    case 3:
                        if (otherFragment == null) {
                            otherFragment = OtherFragment.getInstance();
                        }
                        fragment = otherFragment;
                        break;

                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 4;
            }
        };

        setupViewPager(adapter);
    }

    private void setupViewPager(FragmentPagerAdapter adapter) {
        viewPagerMain.setAdapter(adapter);
        viewPagerMain.setOffscreenPageLimit(adapter.getCount());

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPagerMain.setCurrentItem(position);
                return true;
            }
        });

        viewPagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void pustView(Fragment fragment) {
        pushView(fragment);
    }
}