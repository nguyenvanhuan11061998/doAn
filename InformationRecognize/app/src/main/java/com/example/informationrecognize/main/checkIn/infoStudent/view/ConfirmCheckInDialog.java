package com.example.informationrecognize.main.checkIn.infoStudent.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.informationrecognize.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmCheckInDialog extends DialogFragment {
    @BindView(R.id.tv_des)
    TextView descTextView;

    private String nameStudent = "";
    private String idStudent = "";

    private ClickListener clickListener;

    public void setData(String nameStudent, String idStudent, ClickListener clickListener) {
        this.nameStudent = nameStudent;
        this.idStudent = idStudent;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm_checkin, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        String message = String.format(getString(R.string.ban_co_muon_diem_danh_sinh_vien), nameStudent, idStudent);
        descTextView.setText(message);
    }

    @OnClick({R.id.btn_close, R.id.btn_check_in})
    void onClick (View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                if (clickListener != null) {
                    clickListener.onClickCancel();
                }
                break;
            case R.id.btn_check_in:
                if (clickListener != null) {
                    clickListener.onClickCheckIn();
                }
                break;
        }
    }

    public interface ClickListener {
        void onClickCheckIn();
        void onClickCancel();
    }

}
