package com.example.informationrecognize.main.checkIn.checkInStudent;

import android.content.Intent;
import android.os.Bundle;

import com.example.informationrecognize.base.baseBinding.BaseBindingActivity;
import com.example.informationrecognize.databinding.ActivityBaseBindingBinding;
import com.example.informationrecognize.main.checkIn.checkInStudent.view.CheckInStudentFragment;

public class CheckInStudentActivity extends BaseBindingActivity<ActivityBaseBindingBinding> {
    public static String ID_ROOM = "ID_ROOM";

    @Override
    protected void intAct() {
        String idRoom = "";
        Intent intent = getIntent();
        if (intent != null) {
            idRoom = intent.getStringExtra(ID_ROOM);
        }
        Bundle bundle = new Bundle();
        bundle.putString(ID_ROOM, idRoom);
        pushView(CheckInStudentFragment.getInstance(bundle));
    }
}
