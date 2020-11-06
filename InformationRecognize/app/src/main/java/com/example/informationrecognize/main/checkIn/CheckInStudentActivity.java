package com.example.informationrecognize.main.checkIn;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.informationrecognize.base.baseBinding.BaseBindingActivity;
import com.example.informationrecognize.databinding.ActivityBaseBindingBinding;
import com.example.informationrecognize.main.checkIn.checkInStudent.view.CheckInStudentFragment;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;

public class CheckInStudentActivity extends BaseBindingActivity<ActivityBaseBindingBinding> {
    public static String ID_ROOM = "ID_ROOM";
    public static String ROOM = "ROOM";

    @Override
    protected void intAct() {
        String idRoom = "";
        ClassItemModel examRoom = null;
        Intent intent = getIntent();
        if (intent != null) {
            idRoom = intent.getStringExtra(ID_ROOM);
            examRoom = (ClassItemModel) intent.getSerializableExtra(ROOM);
        }
        if (examRoom != null) {
            Bundle bundle = new Bundle();
            bundle.putString(ID_ROOM, idRoom);
            bundle.putSerializable(ROOM, examRoom);
            pushView(CheckInStudentFragment.getInstance(bundle));
        }
    }

    @Override
    public void pushView(Fragment fragment) {
        super.pushView(fragment);
    }
}
