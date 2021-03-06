package org.seraph.mvprxjavaretrofit.ui.module.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import org.seraph.mvprxjavaretrofit.AppApplication;
import org.seraph.mvprxjavaretrofit.R;
import org.seraph.mvprxjavaretrofit.data.network.picasso.PicassoTool;
import org.seraph.mvprxjavaretrofit.di.component.test.DaggerDesignLayoutComponent;
import org.seraph.mvprxjavaretrofit.di.module.DesignLayoutModule;
import org.seraph.mvprxjavaretrofit.ui.module.base.BaseActivity;
import org.seraph.mvprxjavaretrofit.ui.module.common.photopreview.PhotoPreviewActivity;
import org.seraph.mvprxjavaretrofit.ui.module.common.photopreview.PhotoPreviewBean;
import org.seraph.mvprxjavaretrofit.ui.module.main.ImageBaiduBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Design风格布局效果测试
 * date：2017/4/12 10:21
 * author：xiongj
 * mail：417753393@qq.com
 **/
public class DesignLayoutTestActivity extends BaseActivity implements DesignLayoutTestContract.View {

    @BindView(R.id.app_bar_image)
    ImageView appBarImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rv_data)
    RecyclerView mRecyclerView;


    @Override
    public int getContextView() {
        return R.layout.test_design_layout;
    }

    @Inject
    DesignLayoutTestPresenter mPresenter;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    DesignLayoutAdapter mDesignLayoutAdapter;

    private LoadMoreWrapper mLoadMoreWrapper;


    @Override
    public void setupActivityComponent() {
        DaggerDesignLayoutComponent.builder().appComponent(AppApplication.getAppComponent())
                .designLayoutModule(new DesignLayoutModule(this)).build().inject(this);
    }

    @Override
    public void initCreate(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle("Tomia相册");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        RxToolbar.navigationClicks(toolbar).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                finish();
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);

        mDesignLayoutAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                startPhotoPreview(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        initLoadMore();

        mPresenter.setView(this);
        mPresenter.start();
    }

    /**
     * 初始化加载更多
     */
    public void initLoadMore() {
        mLoadMoreWrapper = new LoadMoreWrapper(mDesignLayoutAdapter);

        mLoadMoreWrapper.setLoadMoreView(LayoutInflater.from(this).inflate(R.layout.default_foot_more_loading, mRecyclerView, false));

        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
               mPresenter.requestNextPage();
            }
        });

        mRecyclerView.setAdapter(mLoadMoreWrapper);
    }


    @OnClick(value = {R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if (layoutManager.findFirstVisibleItemPosition() > 5) {
                    mRecyclerView.scrollToPosition(5);
                }
                mRecyclerView.smoothScrollToPosition(0);
                break;
        }
    }


    private void startPhotoPreview(int position) {
        Intent intent = new Intent(this, PhotoPreviewActivity.class);
        ArrayList<PhotoPreviewBean> photoList = new ArrayList<>();
        List<ImageBaiduBean.BaiduImage> list = mDesignLayoutAdapter.getDatas();
        for (ImageBaiduBean.BaiduImage baiduImage : list) {
            PhotoPreviewBean photoPreviewBean = new PhotoPreviewBean();
            photoPreviewBean.objURL = baiduImage.objURL;
            photoPreviewBean.height = baiduImage.height;
            photoPreviewBean.width = baiduImage.width;
            photoPreviewBean.type = baiduImage.type;
            photoList.add(photoPreviewBean);
        }
        intent.putExtra(PhotoPreviewActivity.PHOTO_LIST, photoList);
        intent.putExtra(PhotoPreviewActivity.CURRENT_POSITION, position);
        startActivity(intent);
    }

    @Override
    public void setImageListData(List<ImageBaiduBean.BaiduImage> baiduImages, boolean isMore) {
        PicassoTool.loadNoCache(this, baiduImages.get((int) (Math.random() * baiduImages.size())).objURL, appBarImage);
        mDesignLayoutAdapter.setListData(baiduImages);
        mLoadMoreWrapper.notifyDataSetChanged();
    }

}
