package com.hzbhd.canbus.car_cus._290.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._290.GeneralCwData;
import com.hzbhd.canbus.car_cus._290.MessageSender;
import com.hzbhd.canbus.car_cus._290.SpaceItemDecoration;
import com.hzbhd.canbus.car_cus._290.adapter.OnOffLvAdapter;
import com.hzbhd.canbus.car_cus._290.entity.OnOffBean;
import com.hzbhd.canbus.car_cus._290.entity.OnOffUpdateEntity;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarOnOffView extends RelativeLayout implements OnOffLvAdapter.ItemClickInterface, ViewInterface {
   private int[] iconResNoSelectedArray = new int[]{2131232657, 2131232651, 2131232645, 2131232647, 2131232663, 2131232661, 2131232643, 2131232653, 2131232641, 2131232649, 2131232659, 2131232637, 2131232639, 2131232655};
   private int[] iconResSelectedArray = new int[]{2131232658, 2131232652, 2131232646, 2131232648, 2131232664, 2131232662, 2131232644, 2131232654, 2131232642, 2131232650, 2131232660, 2131232638, 2131232640, 2131232656};
   private Context mContext;
   private List mList;
   private RecyclerView mLv;
   private OnOffLvAdapter mOnOffLvAdapter;
   private View mView;
   private int[] strResArray = new int[]{2131769472, 2131769473, 2131769478, 2131769479, 2131769480, 2131769481, 2131769482, 2131769483, 2131769484, 2131769485, 2131769474, 2131769475, 2131769476, 2131769477};

   public CarOnOffView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558412, this);
      this.findViews();
      this.initViews();
   }

   private void findViews() {
      this.mLv = (RecyclerView)this.mView.findViewById(2131363208);
   }

   private void initViews() {
      this.mList = new ArrayList();

      for(int var1 = 0; var1 < this.strResArray.length; ++var1) {
         this.mList.add(new OnOffBean(this.strResArray[var1], this.iconResSelectedArray[var1], this.iconResNoSelectedArray[var1]));
      }

      this.mLv.setLayoutManager(new GridLayoutManager(this.mContext, 5, 1, false));
      this.mLv.addItemDecoration(new SpaceItemDecoration(30));
      OnOffLvAdapter var2 = new OnOffLvAdapter(this.mContext, this.mList, this);
      this.mOnOffLvAdapter = var2;
      this.mLv.setAdapter(var2);
   }

   public void onDestroy() {
   }

   public void onItemClick(int var1) {
      LogUtil.showLog("onItemClick:" + var1);
      int var2 = ((OnOffBean)this.mList.get(var1)).getTitleRes();
      int var3 = this.strResArray[13];
      byte var4 = 2;
      if (var2 == var3) {
         if (!((OnOffBean)this.mList.get(var1)).isSelected()) {
            ((OnOffBean)this.mList.get(var1)).setSelected(true);
            this.mOnOffLvAdapter.notifyItemChanged(var1);
            MessageSender.showCommonSwitch(3, 2, true);
            this.postDelayed(new Runnable(this, var1) {
               final CarOnOffView this$0;
               final int val$position;

               {
                  this.this$0 = var1;
                  this.val$position = var2;
               }

               public void run() {
                  ((OnOffBean)this.this$0.mList.get(this.val$position)).setSelected(false);
                  this.this$0.mOnOffLvAdapter.notifyItemChanged(this.val$position);
                  MessageSender.showCommonSwitch(3, 2, false);
               }
            }, 5000L);
         }
      } else {
         byte var5;
         label101: {
            ((OnOffBean)this.mList.get(var1)).setSelected(((OnOffBean)this.mList.get(var1)).isSelected() ^ true);
            this.post(new Runnable(this, var1) {
               final CarOnOffView this$0;
               final int val$position;

               {
                  this.this$0 = var1;
                  this.val$position = var2;
               }

               public void run() {
                  this.this$0.mOnOffLvAdapter.notifyItemChanged(this.val$position);
               }
            });
            if (var1 != 0 && var1 != 1 && var1 != 2 && var1 != 3) {
               if (var1 == 4 || var1 == 5 || var1 == 6 || var1 == 7) {
                  var5 = 1;
                  break label101;
               }

               if (var1 == 8 || var1 == 9 || var1 == 10 || var1 == 11) {
                  var5 = 2;
                  break label101;
               }

               if (var1 == 12) {
                  var5 = 3;
                  break label101;
               }
            }

            var5 = 0;
         }

         byte var6;
         label73: {
            if (var1 != 0 && var1 != 4 && var1 != 8 && var1 != 12) {
               var6 = var4;
               if (var1 == 1) {
                  break label73;
               }

               var6 = var4;
               if (var1 == 5) {
                  break label73;
               }

               if (var1 == 9) {
                  var6 = var4;
                  break label73;
               }

               if (var1 == 2 || var1 == 6 || var1 == 10) {
                  var6 = 4;
                  break label73;
               }

               if (var1 == 3 || var1 == 7 || var1 == 11) {
                  var6 = 6;
                  break label73;
               }
            }

            var6 = 0;
         }

         MessageSender.showCommonSwitch(var5, var6, ((OnOffBean)this.mList.get(var1)).isSelected());
      }

   }

   public void refreshUi(Bundle var1) {
      LogUtil.showLog("CarOnOffView refreshUi");
      List var3 = GeneralCwData.mOnOffUpdateList;
      if (var3 != null) {
         Iterator var2 = var3.iterator();

         while(var2.hasNext()) {
            OnOffUpdateEntity var4 = (OnOffUpdateEntity)var2.next();
            ((OnOffBean)this.mList.get(var4.getIndex())).setSelected(var4.isSelect());
         }

         this.mOnOffLvAdapter.notifyDataSetChanged();
      }
   }
}
