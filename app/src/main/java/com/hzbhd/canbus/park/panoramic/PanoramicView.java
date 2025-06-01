package com.hzbhd.canbus.park.panoramic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.adapter.PanoramiceBtnLvAdapter;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import java.util.List;

public class PanoramicView extends RelativeLayout {
   private PanoramiceBtnLvAdapter mAdapter;
   private RecyclerView mBtnListRv;
   private Context mContext;
   private View.OnClickListener mRightBtnOnClickListener = new View.OnClickListener(this) {
      final PanoramicView this$0;

      {
         this.this$0 = var1;
      }

      public void onClick(View var1) {
         int var2 = var1.getId();
         if (var2 != 2131363156) {
            if (var2 == 2131363159) {
               this.this$0.mRightPullBtn.setVisibility(8);
               this.this$0.mBtnListRv.setVisibility(0);
               this.this$0.mRightHideBtn.setVisibility(0);
            }
         } else {
            this.this$0.mRightHideBtn.setVisibility(8);
            this.this$0.mBtnListRv.setVisibility(8);
            this.this$0.mRightPullBtn.setVisibility(0);
         }

      }
   };
   private ImageButton mRightHideBtn;
   private ImageButton mRightPullBtn;
   private View mView;

   public PanoramicView(Context var1) {
      super(var1);
   }

   public PanoramicView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.initViews(var1);
   }

   private void initRightSmallView() {
      this.mRightPullBtn = (ImageButton)this.mView.findViewById(2131363159);
      this.mRightHideBtn = (ImageButton)this.mView.findViewById(2131363156);
      this.mBtnListRv = (RecyclerView)this.mView.findViewById(2131363210);
      this.mRightPullBtn.setOnClickListener(this.mRightBtnOnClickListener);
      this.mRightHideBtn.setOnClickListener(this.mRightBtnOnClickListener);
   }

   private void initViews(Context var1) {
      this.mView = LayoutInflater.from(var1).inflate(2131558789, this);
      this.initRightSmallView();
   }

   public PanoramiceBtnLvAdapter getAdapter() {
      return this.mAdapter;
   }

   public void setBtnList(List var1, OnPanoramicItemClickListener var2) {
      this.mAdapter = new PanoramiceBtnLvAdapter(this.mContext, var1, var2);
      LinearLayoutManager var3 = new LinearLayoutManager(this.mContext);
      this.mBtnListRv.setLayoutManager(var3);
      this.mBtnListRv.setAdapter(this.mAdapter);
   }
}
