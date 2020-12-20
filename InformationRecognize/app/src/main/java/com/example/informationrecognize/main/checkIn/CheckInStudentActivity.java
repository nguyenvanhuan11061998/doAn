package com.example.informationrecognize.main.checkIn;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.informationrecognize.base.baseBinding.BaseBindingActivity;
import com.example.informationrecognize.databinding.ActivityBaseBindingBinding;
import com.example.informationrecognize.main.checkIn.checkInStudent.view.CheckInStudentFragment;
import com.example.informationrecognize.main.checkIn.checkInStudent.viewModel.CheckInStudentViewModel;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.ByteArrayOutputStream;

import static com.example.informationrecognize.main.checkIn.checkInStudent.viewModel.CheckInStudentViewModel.PHOTO_REQUEST;

public class CheckInStudentActivity extends BaseBindingActivity<ActivityBaseBindingBinding> {
    private static final int REQUEST_ID_READ_WRITE_PERMISSION = 0;
    public static String ID_ROOM = "ID_ROOM";
    public static String ROOM = "ROOM";
    private TextRecognizer detector;

    private CheckInStudentViewModel viewModel;

    @Override
    protected void intAct() {
        detector = new TextRecognizer.Builder(getApplicationContext()).build();
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
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                if (detector.isOperational() && bitmap != null) {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> textBlocks = detector.detect(frame);
                    String blocks = "";
                    for (int index = 0; index < textBlocks.size(); index++) {
                        TextBlock tBlock = textBlocks.valueAt(index);
                        blocks = blocks + tBlock.getValue() + "\n" + "\n";
                    }
                    if (textBlocks.size() == 0) {
                        Log.e("======", " không thấy có ký tự trong ảnh");
                    } else {
                        Log.e("========", blocks);
                    }
                } else {
                    Log.e("=======", "Could not set up the detector!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
