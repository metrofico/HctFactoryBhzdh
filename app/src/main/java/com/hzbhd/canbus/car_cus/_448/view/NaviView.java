package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import java.util.List;

public class NaviView extends LinearLayout {
   private MyButton item1;
   private MyButton item2;
   private MyButton item3;
   private MyButton item4;
   private MyButton item5;
   private MyButton item6;
   private MyButton item7;
   private View view;

   public NaviView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public NaviView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public NaviView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558603, this, true);
      this.view = var4;
      this.item1 = (MyButton)var4.findViewById(2131362511);
      this.item2 = (MyButton)this.view.findViewById(2131362513);
      this.item3 = (MyButton)this.view.findViewById(2131362515);
      this.item4 = (MyButton)this.view.findViewById(2131362517);
      this.item5 = (MyButton)this.view.findViewById(2131362519);
      this.item6 = (MyButton)this.view.findViewById(2131362520);
      this.item7 = (MyButton)this.view.findViewById(2131362521);
   }

   public void getAction(ActionCallback var1) {
      this.item1.getEventUpAction(new ActionCallback(this, var1) {
         final NaviView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo(1);
         }
      });
      this.item2.getEventUpAction(new ActionCallback(this, var1) {
         final NaviView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo(2);
         }
      });
      this.item3.getEventUpAction(new ActionCallback(this, var1) {
         final NaviView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo(3);
         }
      });
      this.item4.getEventUpAction(new ActionCallback(this, var1) {
         final NaviView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo(4);
         }
      });
      this.item5.getEventUpAction(new ActionCallback(this, var1) {
         final NaviView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo(5);
         }
      });
      this.item6.getEventUpAction(new ActionCallback(this, var1) {
         final NaviView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo(6);
         }
      });
      this.item7.getEventUpAction(new ActionCallback(this, var1) {
         final NaviView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo(7);
         }
      });
   }

   public void initItem(List var1) {
      if (var1.size() == 1) {
         this.item1.setText(((String)var1.get(0)).trim());
         this.item2.setVisibility(8);
         this.item3.setVisibility(8);
         this.item4.setVisibility(8);
         this.item5.setVisibility(8);
         this.item6.setVisibility(8);
         this.item7.setVisibility(8);
      }

      if (var1.size() == 2) {
         this.item1.setText(((String)var1.get(0)).trim());
         this.item2.setText(((String)var1.get(1)).trim());
         this.item3.setVisibility(8);
         this.item4.setVisibility(8);
         this.item5.setVisibility(8);
         this.item6.setVisibility(8);
         this.item7.setVisibility(8);
      }

      if (var1.size() == 3) {
         this.item1.setText(((String)var1.get(0)).trim());
         this.item2.setText(((String)var1.get(1)).trim());
         this.item3.setText(((String)var1.get(2)).trim());
         this.item4.setVisibility(8);
         this.item5.setVisibility(8);
         this.item6.setVisibility(8);
         this.item7.setVisibility(8);
      }

      if (var1.size() == 4) {
         this.item1.setText(((String)var1.get(0)).trim());
         this.item2.setText(((String)var1.get(1)).trim());
         this.item3.setText(((String)var1.get(2)).trim());
         this.item4.setText(((String)var1.get(3)).trim());
         this.item5.setVisibility(8);
         this.item6.setVisibility(8);
         this.item7.setVisibility(8);
      }

      if (var1.size() == 5) {
         this.item1.setText(((String)var1.get(0)).trim());
         this.item2.setText(((String)var1.get(1)).trim());
         this.item3.setText(((String)var1.get(2)).trim());
         this.item4.setText(((String)var1.get(3)).trim());
         this.item5.setText(((String)var1.get(4)).trim());
         this.item6.setVisibility(8);
         this.item7.setVisibility(8);
      }

      if (var1.size() == 6) {
         this.item1.setText(((String)var1.get(0)).trim());
         this.item2.setText(((String)var1.get(1)).trim());
         this.item3.setText(((String)var1.get(2)).trim());
         this.item4.setText(((String)var1.get(3)).trim());
         this.item5.setText(((String)var1.get(4)).trim());
         this.item6.setText(((String)var1.get(5)).trim());
         this.item7.setVisibility(8);
      }

   }

   public void select(int var1) {
      if (var1 == 1) {
         this.item1.setBackgroundResource(2131231598);
         this.item2.setBackgroundResource(2131231602);
         this.item3.setBackgroundResource(2131231602);
         this.item4.setBackgroundResource(2131231602);
         this.item5.setBackgroundResource(2131231602);
         this.item6.setBackgroundResource(2131231602);
         this.item7.setBackgroundResource(2131231602);
      }

      if (var1 == 2) {
         this.item1.setBackgroundResource(2131231602);
         this.item2.setBackgroundResource(2131231598);
         this.item3.setBackgroundResource(2131231602);
         this.item4.setBackgroundResource(2131231602);
         this.item5.setBackgroundResource(2131231602);
         this.item6.setBackgroundResource(2131231602);
         this.item7.setBackgroundResource(2131231602);
      }

      if (var1 == 3) {
         this.item1.setBackgroundResource(2131231602);
         this.item2.setBackgroundResource(2131231602);
         this.item3.setBackgroundResource(2131231598);
         this.item4.setBackgroundResource(2131231602);
         this.item5.setBackgroundResource(2131231602);
         this.item6.setBackgroundResource(2131231602);
         this.item7.setBackgroundResource(2131231602);
      }

      if (var1 == 4) {
         this.item1.setBackgroundResource(2131231602);
         this.item2.setBackgroundResource(2131231602);
         this.item3.setBackgroundResource(2131231602);
         this.item4.setBackgroundResource(2131231598);
         this.item5.setBackgroundResource(2131231602);
         this.item6.setBackgroundResource(2131231602);
         this.item7.setBackgroundResource(2131231602);
      }

      if (var1 == 5) {
         this.item1.setBackgroundResource(2131231602);
         this.item2.setBackgroundResource(2131231602);
         this.item3.setBackgroundResource(2131231602);
         this.item4.setBackgroundResource(2131231602);
         this.item5.setBackgroundResource(2131231598);
         this.item6.setBackgroundResource(2131231602);
         this.item7.setBackgroundResource(2131231602);
      }

      if (var1 == 6) {
         this.item1.setBackgroundResource(2131231602);
         this.item2.setBackgroundResource(2131231602);
         this.item3.setBackgroundResource(2131231602);
         this.item4.setBackgroundResource(2131231602);
         this.item5.setBackgroundResource(2131231602);
         this.item6.setBackgroundResource(2131231598);
         this.item7.setBackgroundResource(2131231602);
      }

      if (var1 == 7) {
         this.item1.setBackgroundResource(2131231602);
         this.item2.setBackgroundResource(2131231602);
         this.item3.setBackgroundResource(2131231602);
         this.item4.setBackgroundResource(2131231602);
         this.item5.setBackgroundResource(2131231602);
         this.item6.setBackgroundResource(2131231602);
         this.item7.setBackgroundResource(2131231598);
      }

   }

   public void setItem2Gone(boolean var1) {
      if (var1) {
         this.item2.setVisibility(8);
      } else {
         this.item2.setVisibility(0);
      }

   }
}
