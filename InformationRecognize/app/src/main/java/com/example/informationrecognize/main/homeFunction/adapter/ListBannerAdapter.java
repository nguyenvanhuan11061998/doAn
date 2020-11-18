package com.example.informationrecognize.main.homeFunction.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.informationrecognize.R;
import com.example.informationrecognize.Utils.GlobleUtil;
import com.example.informationrecognize.main.homeFunction.model.BannerHomeResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListBannerAdapter extends RecyclerView.Adapter<ListBannerAdapter.ViewHolder> {
    private Context context;
    private List<BannerHomeResponse.BannerHome> listData;
    private OnClickListener clickListener;

    public ListBannerAdapter(Context context, List<BannerHomeResponse.BannerHome> listData, OnClickListener clickListener) {
        this.context = context;
        this.listData = listData;
        this.clickListener = clickListener;
    }

    public List<BannerHomeResponse.BannerHome> getListData() {
        return listData;
    }

    public void setListData(List<BannerHomeResponse.BannerHome> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_banner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BannerHomeResponse.BannerHome item = listData.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image_banner)
        ImageView bannerImage;
        @BindView(R.id.root_layout)
        View mViewRootLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onClickItem(getAdapterPosition());
                    }
                }
            });
        }

        public void bindData(BannerHomeResponse.BannerHome item) {
            Glide.with(bannerImage).load(item.getImageBanner())
                    .into(bannerImage);
            mViewRootLayout.setLayoutParams(getLayoutParamCardView(mViewRootLayout));

        }
    }

    public interface OnClickListener {
        void onClickItem(int position);
    }

    private ViewGroup.LayoutParams getLayoutParamCardView(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int width = context.getResources().getDisplayMetrics().widthPixels;
        width -= (GlobleUtil.convertDpToPixel(32, context));
        width = (int) (width / 1.15);
        layoutParams.width = width;
        layoutParams.height = (int) (width * 0.54);
        return layoutParams;
    }
}
