package org.seraph.mvprxjavaretrofit.ui.module.test;

import android.content.Context;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.seraph.mvprxjavaretrofit.R;
import org.seraph.mvprxjavaretrofit.data.network.picasso.PicassoTool;
import org.seraph.mvprxjavaretrofit.ui.module.main.ImageBaiduBean;
import org.seraph.mvprxjavaretrofit.utlis.Tools;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 测试布局的适配器
 * date：2017/4/13 17:54
 * author：xiongj
 * mail：417753393@qq.com
 **/
public class DesignLayoutAdapter extends CommonAdapter<ImageBaiduBean.BaiduImage> {

    private int targetWidth;
    private int targetHeight;

    @Inject
    DesignLayoutAdapter(Context mContext) {
        super(mContext, R.layout.item_design, new ArrayList<ImageBaiduBean.BaiduImage>());
        targetWidth = Tools.dip2px(mContext, 200);
        targetHeight = Tools.dip2px(mContext, 150);
    }

    @Override
    protected void convert(ViewHolder holder, ImageBaiduBean.BaiduImage baiduImage, final int position) {
        ImageView imageView = holder.getView(R.id.imageView);
        PicassoTool.loadCache(mContext, baiduImage.objURL, imageView, targetWidth, targetHeight);
    }

    void setListData(List<ImageBaiduBean.BaiduImage> listData) {
        mDatas.clear();
        mDatas.addAll(listData);
        notifyDataSetChanged();
    }

}
