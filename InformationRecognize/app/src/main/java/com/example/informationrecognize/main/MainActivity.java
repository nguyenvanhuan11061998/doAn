package com.example.informationrecognize.main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseActivity;
import com.example.informationrecognize.main.checkIn.mvvm.view.CheckInFragment;
import com.example.informationrecognize.main.homeFunction.view.HomeFragment;
import com.example.informationrecognize.main.information.view.InformationFragment;
import com.example.informationrecognize.main.other.mvvm.view.OtherFragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    public final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 1;
    @DialogType
    private int mDialogType;
    private String mRequestPermissions = "We are requesting the camera and Gallery permission as it is absolutely necessary for the app to perform it\'s functionality.\nPlease select \"Grant Permission\" to try again and \"Cancel \" to exit the application.";
    private String mRequsetSettings = "You have rejected the camera and Gallery permission for the application. As it is absolutely necessary for the app to perform you need to enable it in the settings of your device.\nPlease select \"Go to settings\" to go to application settings in your device and \"Cancel \" to exit the application.";
    private String mGrantPermissions = "Grant Permissions";
    private String mCancel = "Cancel";
    private String mGoToSettings = "Go To Settings";


    private String CAMERA_PERMISSION = android.Manifest.permission.CAMERA;
    private String READ_EXTERNAL_STORAGE_PERMISSION = android.Manifest.permission.READ_EXTERNAL_STORAGE;
    private String WRITE_EXTERNAL_STORAGE_PERMISSION = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


    // type of dialog opened in MainActivity
    @IntDef({DialogType.DIALOG_DENY, DialogType.DIALOG_NEVER_ASK})
    @Retention(RetentionPolicy.SOURCE)
    @interface DialogType {
        int DIALOG_DENY = 0, DIALOG_NEVER_ASK = 1;
    }

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
        initPermission();
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

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkMultiplePermissions(REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS, this);
        } else {
            // Open your camera here.
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    // Call your camera here.
                } else {
                    boolean showRationale1 = shouldShowRequestPermissionRationale(CAMERA_PERMISSION);
                    boolean showRationale2 = shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE_PERMISSION);
                    boolean showRationale3 = shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE_PERMISSION);
                    if (showRationale1 && showRationale2 && showRationale3) {
                        //explain to user why we need the permissions
                        mDialogType = DialogType.DIALOG_DENY;
                        // Show dialog with
                        openAlertDialog(mRequestPermissions, mGrantPermissions, mCancel, new OnDialogButtonClickListener() {
                            @Override
                            public void onPositiveButtonClicked() {
                                switch (mDialogType) {
                                    case DialogType.DIALOG_DENY:
                                        checkMultiplePermissions(REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS, MainActivity.this);
                                        break;
                                    case DialogType.DIALOG_NEVER_ASK:
                                        break;

                                }
                            }

                            @Override
                            public void onNegativeButtonClicked() {

                            }
                        }, this);
                    } else {
                        //explain to user why we need the permissions and ask him to go to settings to enable it
                        mDialogType = DialogType.DIALOG_NEVER_ASK;
                        openAlertDialog(mRequsetSettings, mGoToSettings, mCancel, new OnDialogButtonClickListener() {
                            @Override
                            public void onPositiveButtonClicked() {
                                switch (mDialogType) {
                                    case DialogType.DIALOG_DENY:
                                        checkMultiplePermissions(REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS, MainActivity.this);
                                        break;
                                    case DialogType.DIALOG_NEVER_ASK:
                                        break;

                                }
                            }

                            @Override
                            public void onNegativeButtonClicked() {

                            }
                        }, this);
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //check for camera and storage access permissions
    @TargetApi(Build.VERSION_CODES.M)
    private void checkMultiplePermissions(int permissionCode, Context context) {

        String[] PERMISSIONS = {CAMERA_PERMISSION, READ_EXTERNAL_STORAGE_PERMISSION, WRITE_EXTERNAL_STORAGE_PERMISSION};
        if (!hasPermissions(context, PERMISSIONS)) {
            ActivityCompat.requestPermissions((Activity) context, PERMISSIONS, permissionCode);
        } else {
            // Open your camera here.
        }
    }

    private boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void openAlertDialog(String message, String positiveBtnText, String negativeBtnText,
                                       final OnDialogButtonClickListener listener,Context mContext) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setPositiveButton(positiveBtnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                listener.onPositiveButtonClicked();
            }
        });
        builder.setPositiveButton(positiveBtnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                listener.onNegativeButtonClicked();
            }
        });

        builder.setTitle(mContext.getResources().getString(R.string.app_name));
        builder.setMessage(message);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setCancelable(false);
        builder.create().show();
    }

    public interface OnDialogButtonClickListener {

        void onPositiveButtonClicked();

        void onNegativeButtonClicked();
    }

}