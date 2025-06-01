package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;

public class FileItemView extends LinearLayout {
   private LinearLayout all_action_lin;
   private TextView file_name;
   private ImageView lock_img;
   private TextView palying_txt;
   private ImageView type_img;
   private View view;

   public FileItemView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public FileItemView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public FileItemView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558604, this, true);
      this.view = var4;
      this.file_name = (TextView)var4.findViewById(2131362243);
      this.palying_txt = (TextView)this.view.findViewById(2131362958);
      this.type_img = (ImageView)this.view.findViewById(2131363722);
      this.lock_img = (ImageView)this.view.findViewById(2131362816);
      this.all_action_lin = (LinearLayout)this.view.findViewById(2131361919);
   }

   public void getAction(ActionCallback var1) {
      this.all_action_lin.setOnClickListener(new View.OnClickListener(this, var1) {
         final FileItemView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void onClick(View var1) {
            this.val$actionCallback.toDo((Object)null);
         }
      });
   }

   public void setData(int var1, String var2, int var3) {
      if (!var2.equals("NONE")) {
         this.all_action_lin.setVisibility(0);
      }

      this.file_name.setText(var2);
      this.type_img.setBackgroundResource(var1);
      this.lock_img.setBackgroundResource(var3);
   }

   public void setPlaying(boolean var1) {
      if (var1) {
         this.palying_txt.setVisibility(0);
      } else {
         this.palying_txt.setVisibility(8);
      }

   }

   public void setSelect(boolean var1) {
      if (var1) {
         this.all_action_lin.setBackgroundResource(2131231578);
      } else {
         this.all_action_lin.setBackgroundResource(2131100046);
      }

   }
}
