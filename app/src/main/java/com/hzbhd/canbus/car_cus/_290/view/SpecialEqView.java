package com.hzbhd.canbus.car_cus._290.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._290.GeneralCwData;
import com.hzbhd.canbus.car_cus._290.MessageSender;
import com.hzbhd.canbus.car_cus._290.adapter.OnOffLvAdapter;
import com.hzbhd.canbus.car_cus._290.entity.OnOffBean;
import com.hzbhd.canbus.car_cus._290.entity.OnOffUpdateEntity;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpecialEqView extends RelativeLayout implements OnOffLvAdapter.ItemClickInterface, ViewInterface {
   private static final int CHANG_STATE = 0;
   private static final String TAG = "SpecialEqView";
   private int[] iconResNoSelectedArray = new int[]{2131232730, 2131232738, 2131232724, 2131232722, 2131232740, 2131232728, 2131232734, 2131232732, 2131232726, 2131232736};
   private int[] iconResSelectedArray = new int[]{2131232731, 2131232739, 2131232725, 2131232723, 2131232741, 2131232729, 2131232735, 2131232733, 2131232727, 2131232737};
   private Context mContext;
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final SpecialEqView this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         Log.d("SpecialEqView", var1.what + "<--------->" + var1.obj);
         if (var1.what == 0) {
            int var2 = (Integer)var1.obj;
            ((OnOffBean)this.this$0.mList.get(var2)).setSelected(false);
            this.this$0.setCanClick(var2, true);
            this.this$0.mOnOffLvAdapter.notifyItemChanged(var2);
            this.this$0.setSelectBtn(var2, false);
         }

      }
   };
   private List mList;
   private LinearLayout mLlRight;
   private RecyclerView mLv;
   private OnOffLvAdapter mOnOffLvAdapter;
   private RelativeLayout mRlleft;
   private View mView;
   private int[] strResArray = new int[]{2131769963, 2131769964, 2131769965, 2131769966, 2131769967, 2131769968, 2131769969, 2131769970, 2131769971, 2131769972};

   public SpecialEqView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558416, this);
      this.findViews();
      this.initViews();
   }

   private void findViews() {
      this.mLv = (RecyclerView)this.mView.findViewById(2131363208);
      this.mRlleft = (RelativeLayout)this.mView.findViewById(2131363194);
      this.mLlRight = (LinearLayout)this.mView.findViewById(2131362798);
   }

   private Message getMessage(int var1, int var2) {
      Message var3 = new Message();
      var3.what = var1;
      var3.obj = var2;
      return var3;
   }

   private void initViews() {
      this.mList = new ArrayList();

      for(int var1 = 0; var1 < this.strResArray.length; ++var1) {
         this.mList.add(new OnOffBean(this.strResArray[var1], this.iconResSelectedArray[var1], this.iconResNoSelectedArray[var1]));
      }

      this.mLv.setLayoutManager(new GridLayoutManager(this.mContext, 5, 1, false));
      OnOffLvAdapter var2 = new OnOffLvAdapter(this.mContext, this.mList, this);
      this.mOnOffLvAdapter = var2;
      this.mLv.setAdapter(var2);
      this.mRlleft.setVisibility(4);
      this.mLlRight.setVisibility(4);
   }

   private void setCanClick(int var1, boolean var2) {
      ((OnOffBean)this.mList.get(var1)).setClickable(var2);
      if (var1 == 0) {
         ((OnOffBean)this.mList.get(5)).setClickable(var2);
      } else if (var1 == 5) {
         ((OnOffBean)this.mList.get(0)).setClickable(var2);
      } else if (var1 == 6) {
         ((OnOffBean)this.mList.get(7)).setClickable(var2);
      } else if (var1 == 7) {
         ((OnOffBean)this.mList.get(6)).setClickable(var2);
      } else if (var1 == 8) {
         ((OnOffBean)this.mList.get(9)).setClickable(var2);
      } else if (var1 == 9) {
         ((OnOffBean)this.mList.get(8)).setClickable(var2);
      }

   }

   private void setSelectBtn(int var1, boolean var2) {
      byte var5 = 4;
      byte var3;
      if (var1 != 1 && var1 != 2) {
         if (var1 != 0 && var1 != 3 && var1 != 5 && var1 != 6) {
            if (var1 != 7 && var1 != 8 && var1 != 9) {
               var3 = 0;
            } else {
               var3 = 5;
            }
         } else {
            var3 = 4;
         }
      } else {
         var3 = 3;
      }

      byte var4;
      label79: {
         if (var1 != 3 && var1 != 7) {
            label78: {
               if (var1 != 0 && var1 != 8) {
                  var4 = var5;
                  if (var1 == 1) {
                     break label79;
                  }

                  var4 = var5;
                  if (var1 == 5) {
                     break label79;
                  }

                  if (var1 == 9) {
                     var4 = var5;
                     break label79;
                  }

                  if (var1 != 2 && var1 != 6 && var1 != 11) {
                     break label78;
                  }

                  var4 = 6;
                  break label79;
               }

               var4 = 2;
               break label79;
            }
         }

         var4 = 0;
      }

      MessageSender.showCommonSwitch(var3, var4, var2);
   }

   public void onDestroy() {
   }

   public void onItemClick(int var1) {
      LogUtil.showLog("onItemClick:" + var1);
      if (((OnOffBean)this.mList.get(var1)).getTitleRes() == this.strResArray[4]) {
         ((OnOffBean)this.mList.get(var1)).setSelected(((OnOffBean)this.mList.get(var1)).isSelected() ^ true);
         this.mOnOffLvAdapter.notifyItemChanged(var1);
         MessageSender.showCommonSwitch(5, 6, ((OnOffBean)this.mList.get(var1)).isSelected());
      } else if (!((OnOffBean)this.mList.get(var1)).isSelected()) {
         ((OnOffBean)this.mList.get(var1)).setSelected(true);
         this.setCanClick(var1, false);
         this.mOnOffLvAdapter.notifyItemChanged(var1);
         this.mHandler.sendMessageDelayed(this.getMessage(0, var1), 5000L);
         this.setSelectBtn(var1, true);
      }

   }

   public void refreshUi(Bundle var1) {
      List var3 = GeneralCwData.mSpecialEqUpdateList;
      if (var3 != null) {
         Iterator var4 = var3.iterator();

         while(var4.hasNext()) {
            OnOffUpdateEntity var2 = (OnOffUpdateEntity)var4.next();
            ((OnOffBean)this.mList.get(var2.getIndex())).setSelected(var2.isSelect());
         }

         this.mOnOffLvAdapter.notifyDataSetChanged();
      }
   }
}
