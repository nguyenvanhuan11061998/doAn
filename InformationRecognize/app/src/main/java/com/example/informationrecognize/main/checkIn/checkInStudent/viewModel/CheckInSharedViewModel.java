package com.example.informationrecognize.main.checkIn.checkInStudent.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.example.informationrecognize.base.ViewModelCommon;

public class CheckInSharedViewModel extends ViewModelCommon {
    private MutableLiveData<Boolean> isReload = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsReload() {
        return isReload;
    }

    public void setIsReload(boolean isReload) {
        this.isReload.setValue(isReload);
    }
}
