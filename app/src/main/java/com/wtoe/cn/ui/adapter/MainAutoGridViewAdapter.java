package com.wtoe.cn.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wtoe.cn.ui.R;
import com.wtoe.cn.ui.holder.CommonViewHolder;

import java.util.ArrayList;

import model.bean.GridViewToolsBean;

/**
 * Created by Administrator on 2018/2/12.
 */

public class MainAutoGridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<GridViewToolsBean> list;
    private CommonViewHolder holder;

    public MainAutoGridViewAdapter(Context context, ArrayList<GridViewToolsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder = CommonViewHolder.createCVH(convertView, parent, R.layout.item_child_flow);
        final ImageView pic_view = holder.getIv(R.id.pic_view);
        final TextView tv_content = holder.getTv(R.id.tv_content);
        final GridViewToolsBean gridViewToolsBean = list.get(position);
        Glide.with(context).load(gridViewToolsBean.getUrlPic()).into(pic_view);
        tv_content.setText(gridViewToolsBean.getContent());
        return holder.convertView;
    }
}
