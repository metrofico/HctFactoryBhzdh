package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import java.util.List;

public class SettingRightLvAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private List mList;
   private RightItemClickInterface mRightItemClickInterface;
   private RightItemTouchInterface mRightItemTouchInterface;

   public SettingRightLvAdapter(Context var1, List var2, RightItemClickInterface var3, RightItemTouchInterface var4) {
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
      return ((SettingPageUiSet.ListBean.ItemListBean)this.mList.get(var1)).getStyle();
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.tvTitle.setText(CommUtil.getStrIdByResId(this.mContext, ((SettingPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getTitleSrn()));
      Object var5 = ((SettingPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getValue();
      boolean var4 = ((SettingPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).isValueStr();
      int var3 = this.getItemViewType(var2);
      String var7;
      if (var3 != 0) {
         var4 = true;
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 == 4 && var5 instanceof Integer) {
                     Switch var6 = var1.aSwitch;
                     if ((Integer)var5 != 1) {
                        var4 = false;
                     }

                     var6.setChecked(var4);
                  }
               } else if (var5 instanceof String) {
                  var7 = (String)var5;
                  if (!TextUtils.isEmpty(var7)) {
                     var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, var7));
                  }
               }
            } else if (var5 instanceof String && !TextUtils.isEmpty((String)var5)) {
               var7 = var5 + CommUtil.getStrByResId(this.mContext, ((SettingPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getUnit());
               var1.tvValue.setText(var7);
            }
         } else if (var5 instanceof String) {
            var7 = (String)var5;
            if (TextUtils.isEmpty(var7)) {
               var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, (String)((SettingPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getValueSrnArray().get(((SettingPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getSelectIndex())));
            } else {
               var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, var7));
            }
         } else {
            var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, (String)((SettingPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getValueSrnArray().get(((SettingPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getSelectIndex())));
         }
      } else if (var5 instanceof String) {
         var7 = (String)var5;
         if (!TextUtils.isEmpty(var7)) {
            if (var4) {
               var1.tvValue.setText(var7);
            } else {
               var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, var7));
            }
         }
      } else if (var5 instanceof Integer || var5 instanceof Float) {
         var1.tvValue.setText(var5 + CommUtil.getStrByResId(this.mContext, ((SettingPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getUnit()));
      }

      var1.relativeLayout.setOnClickListener(new View.OnClickListener(this, var2) {
         final SettingRightLvAdapter this$0;
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
         final SettingRightLvAdapter this$0;
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
      var1.setVisibility(((SettingPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).isEnable());
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     var2 = 0;
                  } else {
                     var2 = 2131558781;
                  }
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
      private Switch aSwitch;
      private RelativeLayout relativeLayout;
      final SettingRightLvAdapter this$0;
      private TextView tvTitle;
      private TextView tvValue;

      ViewHolder(SettingRightLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.relativeLayout = (RelativeLayout)var2.findViewById(2131362786);
         this.tvTitle = (TextView)var2.findViewById(2131363710);
         this.tvValue = (TextView)var2.findViewById(2131363714);
         this.aSwitch = (Switch)var2.findViewById(2131363473);
      }

      private void setVisibility(boolean var1) {
         RecyclerView.LayoutParams var2 = (RecyclerView.LayoutParams)this.itemView.getLayoutParams();
         if (var1) {
            this.itemView.setVisibility(0);
            var2.height = -2;
            var2.width = -1;
         } else {
            this.itemView.setVisibility(8);
            var2.height = 0;
            var2.width = 0;
         }

         this.itemView.setLayoutParams(var2);
      }
   }
}
