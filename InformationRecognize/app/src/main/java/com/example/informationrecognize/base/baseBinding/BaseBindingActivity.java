package com.example.informationrecognize.base.baseBinding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.ViewModelCommon;

import butterknife.ButterKnife;

public abstract class BaseBindingActivity <BD extends ViewDataBinding> extends AppCompatActivity {
    protected BD binding;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        ButterKnife.bind(this, binding.getRoot());
        transaction = getSupportFragmentManager().beginTransaction();
        intAct();
    }

    protected abstract void intAct();

    protected int getLayoutId() {
        return R.layout.activity_base_binding;
    }

    protected void pushView(Fragment fragment) {
        transaction.add(R.id.container_frame, fragment);
        transaction.commitAllowingStateLoss();
    }

    public <VM extends ViewModelCommon> VM getViewModel(Class<? extends ViewModelCommon> viewModel) {
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        VM vm = (VM) new ViewModelProvider(this, factory).get(viewModel);
        vm.setActivity(this);
        return vm;
    }
}
