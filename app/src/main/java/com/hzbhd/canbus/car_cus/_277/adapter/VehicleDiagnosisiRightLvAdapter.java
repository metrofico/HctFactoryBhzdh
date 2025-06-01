package com.hzbhd.canbus.car_cus._277.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._277.ui_set.VehicleDiagnosisPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import java.util.List;

public class VehicleDiagnosisiRightLvAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private List mList;
   private RightItemClickInterface mRightItemClickInterface;
   private RightItemTouchInterface mRightItemTouchInterface;

   public VehicleDiagnosisiRightLvAdapter(Context var1, List var2, RightItemClickInterface var3, RightItemTouchInterface var4) {
      this.mRightItemClickInterface = var3;
      this.mRightItemTouchInterface = var4;
      this.mList = var2;
      this.mContext = var1;
   }

   public int getItemCount() {
      return this.mList.size();
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public int getItemViewType(int var1) {
      return ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mList.get(var1)).getStyle();
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.tvTitle.setText(CommUtil.getStrIdByResId(this.mContext, ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getTitleSrn()));
      Object var4 = ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getValue();
      int var3 = this.getItemViewType(var2);
      String var5;
      if (var3 != 0) {
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 == 3 && var4 instanceof String) {
                  var5 = (String)var4;
                  if (!TextUtils.isEmpty(var5)) {
                     var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, var5));
                  }
               }
            } else if (var4 instanceof String && !TextUtils.isEmpty((String)var4)) {
               var5 = ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getValue() + CommUtil.getStrByResId(this.mContext, ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getUnit());
               var1.tvValue.setText(var5);
            }
         } else if (var4 instanceof String) {
            var5 = (String)var4;
            if (TextUtils.isEmpty(var5)) {
               var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, (String)((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getValueSrnArray().get(((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getSelectIndex())));
            } else {
               var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, var5));
            }
         }
      } else if (var4 instanceof String) {
         var5 = (String)var4;
         if (!TextUtils.isEmpty(var5)) {
            var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, var5));
            if (!"geely_e6_item_1".equals(var5) && !"geely_e6_item_2".equals(var5) && !"geely_e6_item_3".equals(var5) && !"geely_e6_item_4".equals(var5) && !"_268_diagnosis_page6_item11_1".equals(var5) && !"_268_diagnosis_page6_item11_9".equals(var5) && !"_268_diagnosis_page6_item11_10".equals(var5) && !"_268_diagnosis_page6_item11_12".equals(var5)) {
               var1.tvValue.setTextColor(-1);
            } else {
               var1.tvValue.setTextColor(-65536);
            }
         }
      } else if (var4 instanceof Integer || var4 instanceof Float) {
         var1.tvValue.setText(var4 + CommUtil.getStrByResId(this.mContext, ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getUnit()));
      }

      LogUtil.showLog("setOnClickListener");
      var1.relativeLayout.setOnClickListener(new View.OnClickListener(this, var2) {
         final VehicleDiagnosisiRightLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            this.this$0.mRightItemClickInterface.onRightItemClick(this.val$position);
         }
      });
      var1.relativeLayout.setOnTouchListener(new View.OnTouchListener(this, var2) {
         final VehicleDiagnosisiRightLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (this.this$0.mRightItemTouchInterface != null) {
               this.this$0.mRightItemTouchInterface.onRightItemTouch(this.val$position, var1, var2);
            }

            return false;
         }
      });
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  var2 = 0;
               } else {
                  var2 = 2131558780;
               }
            } else {
               var2 = 2131558779;
            }
         } else {
            var2 = 2131558778;
         }
      } else {
         var2 = 2131558777;
      }

      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(var2, var1, false));
   }

   public interface RightItemClickInterface {
      void onRightItemClick(int var1);
   }

   public interface RightItemTouchInterface {
      void onRightItemTouch(int var1, View var2, MotionEvent var3);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private RelativeLayout relativeLayout;
      final VehicleDiagnosisiRightLvAdapter this$0;
      private TextView tvTitle;
      private TextView tvValue;

      ViewHolder(VehicleDiagnosisiRightLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.relativeLayout = (RelativeLayout)var2.findViewById(2131362786);
         this.tvTitle = (TextView)var2.findViewById(2131363710);
         this.tvValue = (TextView)var2.findViewById(2131363714);
      }
   }
}
