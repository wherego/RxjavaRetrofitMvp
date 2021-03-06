package org.seraph.mvprxjavaretrofit.ui.module.main.adapter;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

import org.seraph.mvprxjavaretrofit.R;
import org.seraph.mvprxjavaretrofit.data.network.picasso.PicassoTool;
import org.seraph.mvprxjavaretrofit.ui.module.base.adapter.BaseListAdapter;
import org.seraph.mvprxjavaretrofit.ui.module.main.ImageBaiduBean;
import org.seraph.mvprxjavaretrofit.ui.views.CustomSelfProportionImageView;
import org.seraph.mvprxjavaretrofit.utlis.Tools;
import org.seraph.mvprxjavaretrofit.utlis.ViewHolder;

import java.util.List;

/**
 * 图片列表
 * date：2017/2/22 13:43
 * author：xiongj
 * mail：417753393@qq.com
 **/
public class ImageListBaiduAdapter extends BaseListAdapter<ImageBaiduBean.BaiduImage> {

    private int screenWidth;

    public ImageListBaiduAdapter(Activity context, List<ImageBaiduBean.BaiduImage> data) {
        super(context, data);
        Display display = context.getWindowManager().getDefaultDisplay();
        Point outPoint = new Point();
        display.getSize(outPoint);
        screenWidth = outPoint.x;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageBaiduBean.BaiduImage baiduImage = data.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_image, parent, false);
        }
        CustomSelfProportionImageView imageView = ViewHolder.get(convertView, R.id.image);
        imageView.setSize(baiduImage.width, baiduImage.height);
//        TextView textTitle = ViewHolder.get(convertView, R.id.tv_title);
//        textTitle.setText(Html.fromHtml(baiduImage.fromPageTitle + " " + baiduImage.width + "x" + baiduImage.height));
//        if (baiduImage.isShowTitle) {
//            textTitle.setVisibility(View.VISIBLE);
//        } else {
//            textTitle.setVisibility(View.GONE);
//        }
        //按照控件的大小来缩放图片的尺寸
        int width = baiduImage.width;
        int height = baiduImage.height;
        //直接使用屏幕宽
        //  int imageViewWidth = imageView.getMeasuredWidth();
        if (width != 0) {
            height = Tools.getNewHeight(width, height, screenWidth);
            width = screenWidth;
        }
        PicassoTool.loadCache(mContext, baiduImage.objURL, imageView, width, height);

        return convertView;
    }



    public void setListData(List<ImageBaiduBean.BaiduImage> newListData) {
        data.clear();
        data.addAll(newListData);
        notifyDataSetChanged();
    }


}
