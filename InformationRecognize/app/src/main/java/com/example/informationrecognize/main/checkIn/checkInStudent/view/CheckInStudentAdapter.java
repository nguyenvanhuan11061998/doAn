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
import java.util.List;

public class CheckInStudentAdapter extends RecyclerView.Adapter<CheckInStudentAdapter.ViewHolder> {

    private List<StudentModel> listData;
    private Context context;
    private ClickItemListener clickItemListener;

    public CheckInStudentAdapter(Context context, List<StudentModel> listData, ClickItemListener clickItemListener) {
        this.listData = listData;
        this.context = context;
        this.clickItemListener = clickItemListener;
    }

    public List<StudentModel> getListData() {
        return listData;
    }

    public void setListData(List<StudentModel> listData) {
        this.listData = listData;
        notifyDataSetChanged();
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
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount()    {
        return listData == null ? 0 : listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding binding;

        public ViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickItemListener != null) {
                        clickItemListener.onClickItem(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface ClickItemListener {
        void onClickItem(int position);
    }
}
