package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import java.util.List;

public class PanoramiceBtnLvAdapter extends RecyclerView.Adapter {
   private Context mContext;
   private OnPanoramicItemClickListener mItemClickInterface;
   private List mList;

   public PanoramiceBtnLvAdapter(Context var1, List var2, OnPanoramicItemClickListener var3) {
      this.mItemClickInterface = var3;
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
      return ((ParkPageUiSet.Bean)this.mList.get(var1)).getStyle();
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      int var3 = this.getItemViewType(var2);
      if (var3 != 0) {
         if (var3 == 1) {
            var1.ivImage.setImageResource(CommUtil.getImgIdByResId(this.mContext, ((ParkPageUiSet.Bean)this.mList.get(var2)).getImgRes()));
         }
      } else {
         var1.tvText.setText(CommUtil.getStrIdByResId(this.mContext, ((ParkPageUiSet.Bean)this.mList.get(var2)).getTitleSrn()));
      }

      var1.relativeLayout.setSelected(((ParkPageUiSet.Bean)this.mList.get(var2)).isSelect());
      if (this.mItemClickInterface != null) {
         var1.relativeLayout.setOnClickListener(new View.OnClickListener(this, var2) {
            final PanoramiceBtnLvAdapter this$0;
            final int val$position;

            {
               this.this$0 = var1;
               this.val$position = var2;
            }

            public void onClick(View var1) {
               this.this$0.mItemClickInterface.onClickItem(this.val$position);
            }
         });
      }

   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      if (var2 != 0) {
         if (var2 != 1) {
            var2 = 0;
         } else {
            var2 = 2131558771;
         }
      } else {
         var2 = 2131558770;
      }

      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(var2, var1, false));
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView ivImage;
      private RelativeLayout relativeLayout;
      final PanoramiceBtnLvAdapter this$0;
      private TextView tvText;

      ViewHolder(PanoramiceBtnLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.relativeLayout = (RelativeLayout)var2.findViewById(2131362786);
         this.tvText = (TextView)var2.findViewById(2131363706);
         this.ivImage = (ImageView)var2.findViewById(2131362572);
      }
   }
}
