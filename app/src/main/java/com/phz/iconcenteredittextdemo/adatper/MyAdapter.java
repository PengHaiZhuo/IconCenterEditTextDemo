package com.phz.iconcenteredittextdemo.adatper;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.phz.iconcenteredittextdemo.R;
import com.phz.iconcenteredittextdemo.bean.MachineInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author haizhuo
 *列表填充器
 */
public class MyAdapter extends BaseQuickAdapter<MachineInfo, MyAdapter.MyViewHolder> {


    public MyAdapter(int layoutResId, @Nullable List<MachineInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull MyViewHolder helper, MachineInfo item) {
        helper.tvName.setText(item.getName());
        helper.tvAddress.setText(item.getAddress());
    }

    class MyViewHolder extends BaseViewHolder {
        TextView tvName;
        TextView tvAddress;
        public MyViewHolder(View view) {
            super(view);
            tvName=view.findViewById(R.id.tv_name);
            tvAddress=view.findViewById(R.id.tv_address);
        }
    }
}