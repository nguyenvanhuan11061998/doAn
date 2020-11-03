package com.example.informationrecognize.main.checkIn.checkInStudent.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.informationrecognize.BR;
import com.example.informationrecognize.R;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.StudentModel;

import java.util.ArrayList;

public class CheckInStudentAdapter extends RecyclerView.Adapter<CheckInStudentAdapter.ViewHolder> {

    private ArrayList<StudentModel> listData;
    private Context context;

    public CheckInStudentAdapter(ArrayList<StudentModel> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    public void setListData(ArrayList<StudentModel> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_student, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentModel item = listData.get(position);
        holder.binding.setVariable(BR.item, item);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding binding;

        public ViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
