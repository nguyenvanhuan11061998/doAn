package com.example.informationrecognize.main.checkIn;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.informationrecognize.base.baseBinding.BaseBindingActivity;
import com.example.informationrecognize.databinding.ActivityBaseBindingBinding;
import com.example.informationrecognize.main.checkIn.checkInStudent.view.CheckInStudentFragment;
import com.example.informationrecognize.main.checkIn.checkInStudent.viewModel.CheckInStudentViewModel;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;

import java.io.File;
import java.io.FileNotFoundException;

import static com.example.informationrecognize.main.checkIn.checkInStudent.viewModel.CheckInStudentViewModel.PHOTO_REQUEST;

public class CheckInStudentActivity extends BaseBindingActivity<ActivityBaseBindingBinding> {
    private static final int REQUEST_ID_READ_WRITE_PERMISSION = 0;
    public static String ID_ROOM = "ID_ROOM";
    public static String ROOM = "ROOM";
    private Uri imageUri;

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


        initPermission();
    }

    private void initPermission() {
        // Với Android Level >= 23 bạn phải hỏi người dùng cho phép ghi dữ liệu vào thiết bị.
        if (android.os.Build.VERSION.SDK_INT >= 23) {


            // Kiểm tra quyền đọc/ghi dữ liệu vào thiết bị lưu trữ ngoài.
            int readPermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            int writePermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (writePermission != PackageManager.PERMISSION_GRANTED ||
                    readPermission != PackageManager.PERMISSION_GRANTED) {

                // Nếu không có quyền, cần nhắc người dùng cho phép.
                this.requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_ID_READ_WRITE_PERMISSION
                );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ID_READ_WRITE_PERMISSION: {
                // Chú ý: Nếu yêu cầu bị hủy, mảng kết quả trả về là rỗng.
                // Người dùng đã cấp quyền (đọc/ghi).
                if (grantResults.length > 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();

                }
                // Hủy bỏ hoặc bị từ chối.
                else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PHOTO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PHOTO_REQUEST) {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            Log.e("==", bp.toString());
        }
    }

    @Override
    public void pushView(Fragment fragment) {
        super.pushView(fragment);
    }
}
