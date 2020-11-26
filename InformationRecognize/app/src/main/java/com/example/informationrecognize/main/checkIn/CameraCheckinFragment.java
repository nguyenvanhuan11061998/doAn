package com.example.informationrecognize.main.checkIn;

import android.os.Environment;

import com.camerakit.CameraKitView;
import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;
import com.example.informationrecognize.main.checkIn.checkInStudent.viewModel.CheckInStudentViewModel;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;

public class CameraCheckinFragment extends BaseFragment {
    @BindView(R.id.camera)
    CameraKitView cameraKitView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_camera_checkin;
    }

    public static CameraCheckinFragment getInstance() {
        return new CameraCheckinFragment();
    }

    @Override
    protected void initFragment() {
        cameraKitView.captureImage(new CameraKitView.ImageCallback() {
            @Override
            public void onImage(CameraKitView cameraKitView, byte[] capturedImage) {
                File savedPhoto = new File(Environment.getExternalStorageDirectory(), "photoStudentCheckIn.jpg");
                try {
                    FileOutputStream outputStream = new FileOutputStream(savedPhoto.getPath());
                    outputStream.write(capturedImage);
                    outputStream.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    public void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }
}
