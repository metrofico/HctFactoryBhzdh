package com.hzbhd.canbus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.entity.MainListEntity;
import java.util.List;

public class MainLvAdapter extends RecyclerView.Adapter {
   private ItemClickInterface mItemClickInterface;
   private List mList;

   public MainLvAdapter(List var1, ItemClickInterface var2) {
      this.mItemClickInterface = var2;
      this.mList = var1;
   }

   public int getItemCount() {
      return this.mList.size();
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      String var9 = ((MainListEntity)this.mList.get(var2)).getAction();
      var9.hashCode();
      int var4 = var9.hashCode();
      byte var6 = 0;
      byte var7 = 0;
      int var3 = -1;
      switch (var4) {
         case -2077028368:
            if (var9.equals("time_set")) {
               var3 = 0;
            }
            break;
         case -1888873393:
            if (var9.equals("original_car_device")) {
               var3 = 1;
            }
            break;
         case -1840463472:
            if (var9.equals("bao_yang")) {
               var3 = 2;
            }
            break;
         case -1821592035:
            if (var9.equals("mqb_hybrid")) {
               var3 = 3;
            }
            break;
         case -1812163278:
            if (var9.equals("sound_set")) {
               var3 = 4;
            }
            break;
         case -1645844070:
            if (var9.equals("general_settings")) {
               var3 = 5;
            }
            break;
         case -1482148444:
            if (var9.equals("panel_key")) {
               var3 = 6;
            }
            break;
         case -1202765494:
            if (var9.equals("hybird")) {
               var3 = 7;
            }
            break;
         case 96586:
            if (var9.equals("air")) {
               var3 = 8;
            }
            break;
         case 3545755:
            if (var9.equals("sync")) {
               var3 = 9;
            }
            break;
         case 3556498:
            if (var9.equals("test")) {
               var3 = 10;
            }
            break;
         case 108270587:
            if (var9.equals("radio")) {
               var3 = 11;
            }
            break;
         case 109641799:
            if (var9.equals("speed")) {
               var3 = 12;
            }
            break;
         case 178891557:
            if (var9.equals("tire_info")) {
               var3 = 13;
            }
            break;
         case 577205567:
            if (var9.equals("drive_data")) {
               var3 = 14;
            }
            break;
         case 1124446108:
            if (var9.equals("warning")) {
               var3 = 15;
            }
            break;
         case 1271599729:
            if (var9.equals("amplifier")) {
               var3 = 16;
            }
            break;
         case 1849102850:
            if (var9.equals("on_start")) {
               var3 = 17;
            }
            break;
         case 1985941072:
            if (var9.equals("setting")) {
               var3 = 18;
            }
      }

      label123: {
         int var5;
         label122: {
            int var8 = 2131768421;
            var4 = var8;
            var5 = var7;
            switch (var3) {
               case 0:
                  var3 = 2131234386;
                  var4 = var6;
                  break label123;
               case 1:
                  var5 = 2131234381;
                  var4 = 2131769517;
                  break label122;
               case 2:
                  var3 = 2131234378;
                  var4 = var6;
                  break label123;
               case 3:
                  var4 = 2131768872;
                  break;
               case 4:
                  var3 = 2131234384;
                  var4 = var6;
                  break label123;
               case 5:
                  var5 = 2131234194;
                  var4 = 2131768682;
                  break label122;
               case 6:
                  var5 = 2131234197;
                  var4 = 2131769586;
                  break label122;
               case 7:
                  var4 = 2131768869;
                  break;
               case 8:
                  var5 = 2131234188;
                  var4 = 2131767769;
                  break label122;
               case 9:
                  var5 = 2131234205;
                  var4 = 2131770083;
               case 10:
                  break label122;
               case 11:
                  var3 = 2131234382;
                  var4 = var6;
                  break label123;
               case 12:
                  var3 = 2131234385;
                  var4 = var6;
                  break label123;
               case 13:
                  var5 = 2131234206;
                  var4 = 2131770121;
                  break label122;
               case 14:
                  var5 = 2131234190;
                  var4 = 2131768157;
                  break label122;
               case 15:
                  var5 = 2131234208;
                  var4 = 2131770841;
                  break label122;
               case 16:
                  var5 = 2131234193;
                  var4 = 2131767788;
                  break label122;
               case 17:
                  var5 = 2131234203;
                  var4 = 2131769486;
                  break label122;
               case 18:
                  var5 = 2131234201;
                  var4 = 2131767981;
                  break label122;
               default:
                  var4 = var8;
                  var5 = var7;
                  break label122;
            }

            var3 = 2131234198;
            break label123;
         }

         var3 = var5;
      }

      var1.textView.setText(var4);
      var1.imageView.setImageResource(var3);
      var1.imageButton.setOnClickListener(new View.OnClickListener(this, var2) {
         final MainLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            this.this$0.mItemClickInterface.onItemClick(this.val$position);
         }
      });
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558765, var1, false));
   }

   public interface ItemClickInterface {
      void onItemClick(int var1);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private ImageButton imageButton;
      private ImageView imageView;
      private TextView textView;
      final MainLvAdapter this$0;

      ViewHolder(MainLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.imageButton = (ImageButton)var2.findViewById(2131362381);
         this.imageView = (ImageView)var2.findViewById(2131362529);
         this.textView = (TextView)var2.findViewById(2131363578);
      }
   }
}
