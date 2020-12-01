package com.example.informationrecognize.main.checkIn;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.informationrecognize.base.baseApi.ApiUtils;
import com.example.informationrecognize.base.baseBinding.BaseBindingActivity;
import com.example.informationrecognize.databinding.ActivityBaseBindingBinding;
import com.example.informationrecognize.main.checkIn.checkInStudent.view.CheckInStudentFragment;
import com.example.informationrecognize.main.checkIn.checkInStudent.viewModel.CheckInStudentViewModel;
import com.example.informationrecognize.main.checkIn.infoStudent.model.CheckInResponse;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.informationrecognize.main.checkIn.checkInStudent.viewModel.CheckInStudentViewModel.PHOTO_REQUEST;

public class CheckInStudentActivity extends BaseBindingActivity<ActivityBaseBindingBinding> {
    private static final int REQUEST_ID_READ_WRITE_PERMISSION = 0;
    public static String ID_ROOM = "ID_ROOM";
    public static String ROOM = "ROOM";
    private Uri imageUri;

    private CheckInStudentViewModel viewModel;

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

        initViewModel();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(CheckInStudentViewModel.class);
        viewModel.getIsOpenCamera().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isOpenCamera) {
                if (isOpenCamera) {
                    openCamera();
                }
            }
        });
    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, PHOTO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.getIsOpenCamera().setValue(false);
        if (resultCode == RESULT_OK && requestCode == PHOTO_REQUEST) {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            Uri tempUri = getImageUri(getApplicationContext(), bp);
            viewModel.callApiCheckInByAI(tempUri);
        }
    }

    private Uri getImageUri(Context applicationContext, Bitmap photo) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), photo, "Title", null);
        return Uri.parse(path);
    }

    @Override
    public void pushView(Fragment fragment) {
        super.pushView(fragment);
    }

}
