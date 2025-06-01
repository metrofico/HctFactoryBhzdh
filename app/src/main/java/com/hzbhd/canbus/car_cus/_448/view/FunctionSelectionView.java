package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import java.util.List;

public class FunctionSelectionView extends LinearLayout {
   private ActionCallback actionCallback;
   private LinearLayout all_lin;
   private List items;
   private boolean nextPrevActionNone;
   private ImageView next_img;
   private int nowPos;
   private int posSize;
   private ImageView prev_img;
   private TextView title_txt;
   private TextView value_txt;
   private View view;

   public FunctionSelectionView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public FunctionSelectionView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public FunctionSelectionView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.posSize = -1;
      this.nowPos = 0;
      this.nextPrevActionNone = false;
      View var4 = LayoutInflater.from(var1).inflate(2131558608, this, true);
      this.view = var4;
      this.prev_img = (ImageView)var4.findViewById(2131362994);
      this.next_img = (ImageView)this.view.findViewById(2131362898);
      this.title_txt = (TextView)this.view.findViewById(2131363550);
      this.value_txt = (TextView)this.view.findViewById(2131363750);
      this.all_lin = (LinearLayout)this.view.findViewById(2131361920);
      this.initAction(var1);
   }

   private void initAction(Context var1) {
      this.prev_img.setOnTouchListener(new View.OnTouchListener(this) {
         final FunctionSelectionView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.prev_img.setBackgroundResource(2131231575);
               return true;
            } else {
               if (var2.getAction() == 1) {
                  this.this$0.prev_img.setBackgroundResource(2131231574);
                  if (this.this$0.nowPos == 0) {
                     if (this.this$0.actionCallback != null) {
                        this.this$0.actionCallback.toDo(this.this$0.nowPos);
                     }
                  } else {
                     int var3 = this.this$0.nowPos;
                     if (this.this$0.actionCallback != null) {
                        this.this$0.actionCallback.toDo(var3 - 1);
                     }
                  }
               }

               return false;
            }
         }
      });
      this.next_img.setOnTouchListener(new View.OnTouchListener(this) {
         final FunctionSelectionView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.next_img.setBackgroundResource(2131231583);
               return true;
            } else {
               if (var2.getAction() == 1) {
                  this.this$0.next_img.setBackgroundResource(2131231582);
                  this.this$0.prev_img.setBackgroundResource(2131231574);
                  if (this.this$0.nowPos == this.this$0.posSize - 1) {
                     if (this.this$0.actionCallback != null) {
                        this.this$0.actionCallback.toDo(this.this$0.nowPos);
                     }
                  } else {
                     int var3 = this.this$0.nowPos;
                     if (this.this$0.actionCallback != null) {
                        this.this$0.actionCallback.toDo(var3 + 1);
                     }
                  }
               }

               return false;
            }
         }
      });
      this.all_lin.setOnClickListener(new View.OnClickListener(this) {
         final FunctionSelectionView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.nextPrevActionNone && this.this$0.actionCallback != null) {
               this.this$0.actionCallback.toDo("ALL_ACTION");
            }

         }
      });
   }

   public void getAction(ActionCallback var1) {
      this.actionCallback = var1;
   }

   public void selectItem(int var1) {
      this.nowPos = var1;
      List var2 = this.items;
      if (var2 != null) {
         this.value_txt.setText(((String)var2.get(var1)).trim());
      }

   }

   public void setItems(List var1) {
      this.items = var1;
      this.posSize = var1.size();
      this.selectItem(0);
   }

   public void setNextPrevActionNone(boolean var1) {
      this.nextPrevActionNone = var1;
      if (var1) {
         this.prev_img.setVisibility(4);
         this.next_img.setVisibility(4);
      } else {
         this.prev_img.setVisibility(0);
         this.next_img.setVisibility(0);
      }

   }

   public void setTitle(String var1) {
      this.title_txt.setText(var1);
   }

   public void setValueStr(String var1) {
      this.value_txt.setText(var1);
   }
}
