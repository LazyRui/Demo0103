package com.bawei.demo0102;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Callback;

/**
 * ProjectName: Demo0102
 * PackageName: com.bawei.demo0102
 * ClassName:   Adaipter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/2_9:03
 */
public class Cdaipter extends RecyclerView.Adapter<Cdaipter.VH> {


    private final Context context;
    private List<ProductEntity.ResultBean.PzshBean.CommodityListBeanX> commodityList;

    public Cdaipter(Context context, List<ProductEntity.ResultBean.PzshBean.CommodityListBeanX> commodityList) {

        this.context = context;
        this.commodityList = commodityList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(View.inflate(context,R.layout.item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ProductEntity.ResultBean.PzshBean.CommodityListBeanX list = commodityList.get(position);

        Glide.with(context)
                .load(list.getMasterPic()).into(holder.imageView);

        holder.t1.setText(list.getCommodityName());
        holder.t2.setText("￥:" + list.getPrice());



    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    class VH extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView t1,t2;
        public VH(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
            t1 = itemView.findViewById(R.id.tv1);
            t2 = itemView.findViewById(R.id.tv2);
        }
    }
}
