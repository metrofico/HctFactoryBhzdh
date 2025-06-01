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
import com.hzbhd.canbus.car_cus._277.ui_set.VehicleMonitorPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import java.util.List;

public class VehicleMonitorRightLvAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private List mList;
   private RightItemClickInterface mRightItemClickInterface;
   private RightItemTouchInterface mRightItemTouchInterface;

   public VehicleMonitorRightLvAdapter(Context var1, List var2, RightItemClickInterface var3, RightItemTouchInterface var4) {
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
      return ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mList.get(var1)).getStyle();
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.tvTitle.setText(CommUtil.getStrIdByResId(this.mContext, ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getTitleSrn()));
      Object var5 = ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getValue();
      String var4 = CommUtil.getStrByResId(this.mContext, ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getUnit());
      int var3 = this.getItemViewType(var2);
      if (var3 != 0) {
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 4) {
                  if (var3 == 5) {
                     if (!(var5 instanceof Integer) && !(var5 instanceof Float)) {
                        if (((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getValue() instanceof String) {
                           var4 = (String)var5;
                           if (!TextUtils.isEmpty(var4)) {
                              var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, var4));
                           }
                        }
                     } else {
                        var1.tvValue.setText(var5 + var4);
                     }

                     if (var2 == 0) {
                        var1.tvIcon.setBackground(this.mContext.getDrawable(2131231368));
                     } else if (var2 == 1) {
                        var1.tvIcon.setBackground(this.mContext.getDrawable(2131231369));
                     } else if (var2 == 2) {
                        var1.tvIcon.setBackground(this.mContext.getDrawable(2131231370));
                     } else if (var2 == 3) {
                        var1.tvIcon.setBackground(this.mContext.getDrawable(2131231371));
                     }
                  }
               } else if (!(var5 instanceof Integer) && !(var5 instanceof Float)) {
                  if (((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getValue() instanceof String) {
                     var4 = (String)var5;
                     if (!TextUtils.isEmpty(var4)) {
                        var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, var4));
                     }
                  }
               } else {
                  var1.tvTitle.setText(Integer.toString(var2 + 1 - 7) + var1.tvTitle.getText());
                  var1.tvValue.setText(var5 + var4);
               }
            } else if (!TextUtils.isEmpty((String)var5)) {
               var4 = ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getValue() + CommUtil.getStrByResId(this.mContext, ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getUnit());
               var1.tvValue.setText(var4);
            }
         } else {
            var4 = (String)var5;
            if (TextUtils.isEmpty(var4)) {
               var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, (String)((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getValueSrnArray().get(((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getSelectIndex())));
            } else {
               var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, var4));
            }
         }
      } else if (!(var5 instanceof Integer) && !(var5 instanceof Float)) {
         if (((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mList.get(var2)).getValue() instanceof String) {
            var4 = (String)var5;
            if (!TextUtils.isEmpty(var4)) {
               var1.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, var4));
            }
         }
      } else {
         var1.tvValue.setText(var5 + var4);
      }

      var1.relativeLayout.setOnClickListener(new View.OnClickListener(this, var2) {
         final VehicleMonitorRightLvAdapter this$0;
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
         final VehicleMonitorRightLvAdapter this$0;
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
      int var4 = 2131558777;
      int var3 = var4;
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  var3 = var4;
                  if (var2 != 4) {
                     if (var2 != 5) {
                        var3 = 0;
                     } else {
                        var3 = 2131558424;
                     }
                  }
               } else {
                  var3 = 2131558780;
               }
            } else {
               var3 = 2131558779;
            }
         } else {
            var3 = 2131558778;
         }
      }

      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(var3, var1, false));
   }

   public interface RightItemClickInterface {
      void onRightItemClick(int var1);
   }

   public interface RightItemTouchInterface {
      void onRightItemTouch(int var1, View var2, MotionEvent var3);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private RelativeLayout relativeLayout;
      final VehicleMonitorRightLvAdapter this$0;
      private TextView tvIcon;
      private TextView tvTitle;
      private TextView tvValue;

      ViewHolder(VehicleMonitorRightLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.relativeLayout = (RelativeLayout)var2.findViewById(2131362786);
         this.tvTitle = (TextView)var2.findViewById(2131363710);
         this.tvValue = (TextView)var2.findViewById(2131363714);
         this.tvIcon = (TextView)var2.findViewById(2131363628);
      }
   }
}
