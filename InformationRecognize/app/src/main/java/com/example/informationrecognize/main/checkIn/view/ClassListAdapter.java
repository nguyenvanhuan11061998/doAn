package com.example.informationrecognize.main.checkIn.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.informationrecognize.R;
import com.example.informationrecognize.main.checkIn.model.ClassItemModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> {
    private List<ClassItemModel> listData;
    private Context context;
    private ClickItemListener clickItemListener;

    public ClassListAdapter(Context context, List<ClassItemModel> listData, ClickItemListener clickItemListener) {
        this.listData = listData;
        this.context = context;
        this.clickItemListener = clickItemListener;
    }

    public void setData(List<ClassItemModel> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_class_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClassItemModel item = listData.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_exam_room)
        TextView examRoomTextView;
        @BindView(R.id.tv_status)
        TextView statusTextView;
        @BindView(R.id.tv_course)
        TextView courseTextView;
        @BindView(R.id.tv_branch)
        TextView branchTextView;
        @BindView(R.id.tv_number_student)
        TextView numberStudentTextView;
        @BindView(R.id.tv_exam_time)
        TextView examTimeTextView;
        @BindView(R.id.tv_start_time)
        TextView startTimeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(ClassItemModel item) {
            examRoomTextView.setText(item.getNameClass());
            if ("0".equals(item.getStatus())) {

            } else if ("1".equals(item.getStatus())) {

            } else {

            }
            courseTextView.setText(item.getCourse());
            branchTextView.setText(item.getBranch());
            numberStudentTextView.setText(item.getNumberStudent());
            examTimeTextView.setText(item.getTimeExam());
            startTimeTextView.setText(item.getStartTime());

            itemView.setOnClickListener(new View.OnClickListener() {
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
        void onClickItem (int position);
    }
}
