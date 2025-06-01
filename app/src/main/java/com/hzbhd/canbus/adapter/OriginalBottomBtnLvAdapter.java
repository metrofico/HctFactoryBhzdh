package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import java.util.Arrays;
import java.util.List;

public class OriginalBottomBtnLvAdapter extends RecyclerView.Adapter {
   private OnOriginalBottomBtnClickListener mBtnClickInterface;
   private int mCurrentClick = 0;
   private List mDisplayList;
   private List mOriginalList;

   public OriginalBottomBtnLvAdapter(Context var1, String[] var2, List var3, OnOriginalBottomBtnClickListener var4) {
      this.mBtnClickInterface = var4;
      this.mOriginalList = Arrays.asList(var2);
      this.mDisplayList = var3;
   }

   public int getItemCount() {
      return this.mOriginalList.size();
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.mIb.setOnClickListener(new View.OnClickListener(this, var2) {
         final OriginalBottomBtnLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            String var3 = (String)this.this$0.mDisplayList.get(this.val$position);
            int var2 = this.this$0.mOriginalList.indexOf(var3);
            if (this.this$0.mBtnClickInterface != null) {
               this.this$0.mBtnClickInterface.onClickBottomBtnItem(var2);
            }

         }
      });
      if (var1.mIb.getDrawable() == null) {
         String var3 = (String)this.mDisplayList.get(var2);
         var3.hashCode();
         switch (var3) {
            case "preset_scan":
               var1.mIb.setImageResource(2131234259);
               break;
            case "random":
               var1.mIb.setImageResource(2131234255);
               break;
            case "repeat":
               var1.mIb.setImageResource(2131234057);
               break;
            case "prev_disc":
               var1.mIb.setImageResource(2131234253);
               break;
            case "auto_store":
               var1.mIb.setImageResource(2131234261);
               break;
            case "radio_scan":
               var1.mIb.setImageResource(2131234262);
               break;
            case "up":
               var1.mIb.setImageResource(2131234395);
               break;
            case "band":
               var1.mIb.setImageResource(2131234257);
               break;
            case "down":
               var1.mIb.setImageResource(2131234394);
               break;
            case "left":
               var1.mIb.setImageResource(2131234396);
               break;
            case "mode":
               var1.mIb.setImageResource(2131234011);
               break;
            case "play":
               var1.mIb.setImageResource(2131234401);
               break;
            case "stop":
               var1.mIb.setImageResource(2131234402);
               break;
            case "cycle":
               var1.mIb.setImageResource(2131234256);
               break;
            case "pause":
               var1.mIb.setImageResource(2131234400);
               break;
            case "power":
               var1.mIb.setImageResource(2131234017);
               break;
            case "right":
               var1.mIb.setImageResource(2131234398);
               break;
            case "next_disc":
               var1.mIb.setImageResource(2131234254);
               break;
            case "play_pause":
               var1.mIb.setImageResource(2131234401);
               var1.mIb.setOnClickListener(new View.OnClickListener(this, var1) {
                  final OriginalBottomBtnLvAdapter this$0;
                  final ViewHolder val$holder;

                  {
                     this.this$0 = var1;
                     this.val$holder = var2;
                  }

                  public void onClick(View var1) {
                     if (this.this$0.mCurrentClick == 0) {
                        this.val$holder.mIb.setImageResource(2131234400);
                        this.this$0.mCurrentClick = 1;
                     } else {
                        this.val$holder.mIb.setImageResource(2131234401);
                        this.this$0.mCurrentClick = 0;
                     }

                  }
               });
         }
      }

   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558861, var1, false));
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private ImageButton mIb;
      final OriginalBottomBtnLvAdapter this$0;

      ViewHolder(OriginalBottomBtnLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.mIb = (ImageButton)var2.findViewById(2131362009);
      }
   }
}
