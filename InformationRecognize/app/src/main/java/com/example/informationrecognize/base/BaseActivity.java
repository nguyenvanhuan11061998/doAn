package com.example.informationrecognize.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.informationrecognize.R;

public abstract class BaseActivity extends AppCompatActivity {
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        transaction = getSupportFragmentManager().beginTransaction();
        initAct();
    }

    private int getLayoutId() {
        return R.layout.layout_container;
    }

    protected abstract void initAct();

    protected void pushView(Fragment fragment) {
        transaction.add(R.id.container_frame, fragment);
        transaction.commitAllowingStateLoss();
    }

}
