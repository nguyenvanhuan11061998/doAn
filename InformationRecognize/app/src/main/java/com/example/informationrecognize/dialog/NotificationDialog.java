package com.example.informationrecognize.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.informationrecognize.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationDialog extends DialogFragment {

    @BindView(R.id.tv_des)
    TextView descTextView;
    @BindView(R.id.btn_confirm)
    Button confirmButton;

    private String message = "";
    private String textButton = "";
    private ClickDialogListener dialogListener;

    public void setData (String message, String textButton, ClickDialogListener dialogListener) {
        this.message = message;
        this.textButton = textButton;
        this.dialogListener = dialogListener;
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
        View view = inflater.inflate(R.layout.dialog_notification, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        confirmButton.setText(textButton);
        descTextView.setText(message);
    }

    @OnClick ({R.id.btn_close, R.id.btn_confirm})
    void onClick (View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                dismiss();
                break;
            case R.id.btn_confirm:
                if (dialogListener != null) {
                    dialogListener.onClickButtonConfirm();
                }
                break;
        }
    }

    public interface ClickDialogListener {
        void onClickButtonConfirm ();
    }
}
