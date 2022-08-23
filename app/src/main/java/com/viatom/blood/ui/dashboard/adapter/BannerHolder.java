package com.viatom.blood.ui.dashboard.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.viatom.blood.R;


public class BannerHolder extends Holder<BannerBean> {

    private ImageView iv;

    public BannerHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        iv = itemView.findViewById(R.id.iv_item_banner);

    }
    int[] gg = {R.drawable.poctor_banner1, R.drawable.poctor_banner2, R.drawable.poctor_banner3,R.drawable.poctor_banner4};
    @Override
    public void updateUI(BannerBean data) {

        Glide.with(itemView.getContext())
                .load(gg[data.getC()])
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv);


    }
}