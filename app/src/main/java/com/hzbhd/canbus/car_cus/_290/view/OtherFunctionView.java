package com.hzbhd.canbus.car_cus._290.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._290.SpaceItemDecoration;
import com.hzbhd.canbus.car_cus._290.adapter.MediaItenAdapter;
import com.hzbhd.canbus.car_cus._290.entity.MediaItemBean;
import com.hzbhd.canbus.util.RealKeyUtil;
import java.util.ArrayList;
import java.util.List;

public class OtherFunctionView extends RelativeLayout implements MediaItenAdapter.ItemClickInterface, ViewInterface {
   private int[] iconPressArray = new int[]{2131232755, 2131232761, 2131232759, 2131232747, 2131232749, 2131232745, 2131232753};
   private int[] iconReleaseArray = new int[]{2131232754, 2131232760, 2131232758, 2131232746, 2131232748, 2131232744, 2131232752};
   private Context mContext;
   private List mList;
   private RecyclerView mLv;
   private MediaItenAdapter mMediaItenAdapter;
   private View mView;
   private int[] strResArray = new int[]{2131760675, 2131760676, 2131760677, 2131767870, 2131760674, 2131767840, 2131768218};
   private int[] targetArray = new int[]{59, 61, 76, 68, 140, 141, 4};

   public OtherFunctionView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558414, this);
      this.findViews();
      this.initViews();
   }

   private void findViews() {
      this.mLv = (RecyclerView)this.mView.findViewById(2131363220);
   }

   private void initViews() {
      this.mList = new ArrayList();

      for(int var1 = 0; var1 < this.strResArray.length; ++var1) {
         this.mList.add(new MediaItemBean(this.strResArray[var1], this.iconPressArray[var1], this.iconReleaseArray[var1], this.targetArray[var1]));
      }

      this.mLv.setLayoutManager(new GridLayoutManager(this.mContext, 5, 1, false));
      this.mLv.addItemDecoration(new SpaceItemDecoration(30));
      MediaItenAdapter var2 = new MediaItenAdapter(this.mContext, this.mList, this);
      this.mMediaItenAdapter = var2;
      this.mLv.setAdapter(var2);
   }

   public void onDestroy() {
   }

   public void onItemClick(int var1) {
      RealKeyUtil.realKeyClick(this.mContext, var1);
   }

   public void refreshUi(Bundle var1) {
   }
}
