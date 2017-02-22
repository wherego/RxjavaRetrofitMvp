package org.seraph.mvprxjavaretrofit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.seraph.mvprxjavaretrofit.R;
import org.seraph.mvprxjavaretrofit.mvp.model.BaiduImageBean;
import org.seraph.mvprxjavaretrofit.utlis.ViewHolder;
import org.seraph.mvprxjavaretrofit.views.CustomSelfFitWidthImageView;

import java.util.List;

/**
 * 图片列表
 * date：2017/2/22 13:43
 * author：xiongj
 * mail：417753393@qq.com
 **/
public class ImageListAdapter extends BaseListAdapter<BaiduImageBean.BaiduImage> {


    public ImageListAdapter(List<BaiduImageBean.BaiduImage> data, Context context) {
        super(data, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaiduImageBean.BaiduImage baiduImage = data.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_image, parent, false);
        }
        CustomSelfFitWidthImageView imageView = ViewHolder.get(convertView, R.id.image);
        Picasso.with(context).load(baiduImage.objURL).placeholder(R.mipmap.ic_launcher).centerCrop().resize(baiduImage.width, baiduImage.height).into(imageView);
        return convertView;
    }
}
