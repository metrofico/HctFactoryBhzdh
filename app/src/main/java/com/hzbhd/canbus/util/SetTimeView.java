package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetTimeView {
   private Button cancel;
   private TextView day;
   DecimalFormat df_2Integer = new DecimalFormat("00");
   private TextView hour;
   private ImageButton imageButtonAdd1;
   private ImageButton imageButtonAdd2;
   private ImageButton imageButtonAdd3;
   private ImageButton imageButtonAdd4;
   private ImageButton imageButtonAdd5;
   private ImageButton imageButtonReduce1;
   private ImageButton imageButtonReduce2;
   private ImageButton imageButtonReduce3;
   private ImageButton imageButtonReduce4;
   private ImageButton imageButtonReduce5;
   Context mContext;
   private TextView minute;
   private TextView month;
   private Button ok;
   private int timeDay = 1;
   private int timeHour = 0;
   private int timeMinute = 0;
   private int timeMonth = 1;
   private int timeYear = 2000;
   private TextView title;
   private TextView year;

   private void ButtClickEvent(View var1, TimeResultListener var2, AlertDialog var3) {
      if (this.imageButtonAdd1 == null) {
         this.imageButtonAdd1 = (ImageButton)var1.findViewById(2131361898);
      }

      if (this.imageButtonAdd2 == null) {
         this.imageButtonAdd2 = (ImageButton)var1.findViewById(2131361899);
      }

      if (this.imageButtonAdd3 == null) {
         this.imageButtonAdd3 = (ImageButton)var1.findViewById(2131361900);
      }

      if (this.imageButtonAdd4 == null) {
         this.imageButtonAdd4 = (ImageButton)var1.findViewById(2131361901);
      }

      if (this.imageButtonAdd5 == null) {
         this.imageButtonAdd5 = (ImageButton)var1.findViewById(2131361902);
      }

      if (this.imageButtonReduce1 == null) {
         this.imageButtonReduce1 = (ImageButton)var1.findViewById(2131363080);
      }

      if (this.imageButtonReduce2 == null) {
         this.imageButtonReduce2 = (ImageButton)var1.findViewById(2131363081);
      }

      if (this.imageButtonReduce3 == null) {
         this.imageButtonReduce3 = (ImageButton)var1.findViewById(2131363082);
      }

      if (this.imageButtonReduce4 == null) {
         this.imageButtonReduce4 = (ImageButton)var1.findViewById(2131363083);
      }

      if (this.imageButtonReduce5 == null) {
         this.imageButtonReduce5 = (ImageButton)var1.findViewById(2131363084);
      }

      if (this.year == null) {
         this.year = (TextView)var1.findViewById(2131363804);
      }

      if (this.month == null) {
         this.month = (TextView)var1.findViewById(2131362877);
      }

      if (this.day == null) {
         this.day = (TextView)var1.findViewById(2131362177);
      }

      if (this.hour == null) {
         this.hour = (TextView)var1.findViewById(2131362373);
      }

      if (this.minute == null) {
         this.minute = (TextView)var1.findViewById(2131362856);
      }

      if (this.ok == null) {
         this.ok = (Button)var1.findViewById(2131362918);
      }

      if (this.cancel == null) {
         this.cancel = (Button)var1.findViewById(2131362092);
      }

      this.ok.setOnClickListener(new View.OnClickListener(this, var2, var3) {
         final SetTimeView this$0;
         final AlertDialog val$dialog;
         final TimeResultListener val$listener;

         {
            this.this$0 = var1;
            this.val$listener = var2;
            this.val$dialog = var3;
         }

         public void onClick(View var1) {
            this.val$listener.result(this.this$0.timeYear, this.this$0.timeMonth, this.this$0.timeDay, this.this$0.timeHour, this.this$0.timeMinute);
            this.val$dialog.dismiss();
         }
      });
      this.cancel.setOnClickListener(new View.OnClickListener(this, var3) {
         final SetTimeView this$0;
         final AlertDialog val$dialog;

         {
            this.this$0 = var1;
            this.val$dialog = var2;
         }

         public void onClick(View var1) {
            this.val$dialog.dismiss();
         }
      });
      this.imageButtonAdd1.setOnClickListener(new View.OnClickListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.timeYear != 2099) {
               SetTimeView var2 = this.this$0;
               var2.timeYear = var2.timeYear + 1;
            } else {
               this.this$0.timeYear = 2000;
            }

            this.this$0.year.setText(this.this$0.timeYear + this.this$0.mContext.getString(2131759108));
         }
      });
      this.imageButtonReduce1.setOnClickListener(new View.OnClickListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.timeYear != 2000) {
               SetTimeView var2 = this.this$0;
               var2.timeYear = var2.timeYear - 1;
            } else {
               this.this$0.timeYear = 2099;
            }

            this.this$0.year.setText(this.this$0.timeYear + this.this$0.mContext.getString(2131759108));
         }
      });
      this.imageButtonAdd2.setOnClickListener(new View.OnClickListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.timeMonth != 12) {
               SetTimeView var2;
               if (this.this$0.timeMonth == 1 && this.this$0.timeDay > 28) {
                  var2 = this.this$0;
                  var2.timeMonth = var2.timeMonth + 1;
                  this.this$0.timeDay = 1;
               } else {
                  var2 = this.this$0;
                  var2.timeMonth = var2.timeMonth + 1;
               }
            } else {
               this.this$0.timeMonth = 1;
            }

            this.this$0.day.setText(this.this$0.timeDay + this.this$0.mContext.getString(2131759110));
            this.this$0.month.setText(this.this$0.timeMonth + this.this$0.mContext.getString(2131759109));
         }
      });
      this.imageButtonReduce2.setOnClickListener(new View.OnClickListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.timeMonth != 1) {
               SetTimeView var2;
               if (this.this$0.timeMonth == 3 && this.this$0.timeDay > 28) {
                  var2 = this.this$0;
                  var2.timeMonth = var2.timeMonth - 1;
                  this.this$0.timeDay = 1;
               } else {
                  var2 = this.this$0;
                  var2.timeMonth = var2.timeMonth - 1;
               }
            } else {
               this.this$0.timeMonth = 12;
            }

            this.this$0.day.setText(this.this$0.timeDay + this.this$0.mContext.getString(2131759110));
            this.this$0.month.setText(this.this$0.timeMonth + this.this$0.mContext.getString(2131759109));
         }
      });
      this.imageButtonAdd3.setOnClickListener(new View.OnClickListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            SetTimeView var2;
            if (this.this$0.timeMonth != 1 && this.this$0.timeMonth != 3 && this.this$0.timeMonth != 5 && this.this$0.timeMonth != 7 && this.this$0.timeMonth != 8 && this.this$0.timeMonth != 10 && this.this$0.timeMonth != 12) {
               if (this.this$0.timeMonth == 2) {
                  if (this.this$0.timeYear % 4 == 0) {
                     if (this.this$0.timeDay < 29) {
                        var2 = this.this$0;
                        var2.timeDay = var2.timeDay + 1;
                     } else {
                        this.this$0.timeDay = 1;
                     }
                  } else if (this.this$0.timeDay != 28) {
                     var2 = this.this$0;
                     var2.timeDay = var2.timeDay + 1;
                  } else {
                     this.this$0.timeDay = 1;
                  }
               } else if (this.this$0.timeDay != 30) {
                  var2 = this.this$0;
                  var2.timeDay = var2.timeDay + 1;
               } else {
                  this.this$0.timeDay = 1;
               }
            } else if (this.this$0.timeDay != 31) {
               var2 = this.this$0;
               var2.timeDay = var2.timeDay + 1;
            } else {
               this.this$0.timeDay = 1;
            }

            this.this$0.day.setText(this.this$0.timeDay + this.this$0.mContext.getString(2131759110));
         }
      });
      this.imageButtonReduce3.setOnClickListener(new View.OnClickListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            SetTimeView var2;
            if (this.this$0.timeMonth != 1 && this.this$0.timeMonth != 3 && this.this$0.timeMonth != 5 && this.this$0.timeMonth != 7 && this.this$0.timeMonth != 8 && this.this$0.timeMonth != 10 && this.this$0.timeMonth != 12) {
               if (this.this$0.timeMonth == 2) {
                  if (this.this$0.timeYear % 4 == 0) {
                     if (this.this$0.timeDay != 1) {
                        var2 = this.this$0;
                        var2.timeDay = var2.timeDay - 1;
                     } else {
                        this.this$0.timeDay = 29;
                     }
                  } else if (this.this$0.timeDay != 1) {
                     var2 = this.this$0;
                     var2.timeDay = var2.timeDay - 1;
                  } else {
                     this.this$0.timeDay = 28;
                  }
               } else if (this.this$0.timeDay != 1) {
                  var2 = this.this$0;
                  var2.timeDay = var2.timeDay - 1;
               } else {
                  this.this$0.timeDay = 30;
               }
            } else if (this.this$0.timeDay != 1) {
               var2 = this.this$0;
               var2.timeDay = var2.timeDay - 1;
            } else {
               this.this$0.timeDay = 31;
            }

            this.this$0.day.setText(this.this$0.timeDay + this.this$0.mContext.getString(2131759110));
         }
      });
      this.imageButtonAdd4.setOnClickListener(new View.OnClickListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.timeHour != 23) {
               SetTimeView var2 = this.this$0;
               var2.timeHour = var2.timeHour + 1;
            } else {
               this.this$0.timeHour = 0;
            }

            this.this$0.hour.setText(this.this$0.df_2Integer.format((long)this.this$0.timeHour) + this.this$0.mContext.getString(2131759111));
         }
      });
      this.imageButtonReduce4.setOnClickListener(new View.OnClickListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.timeHour != 0) {
               SetTimeView var2 = this.this$0;
               var2.timeHour = var2.timeHour - 1;
            } else {
               this.this$0.timeHour = 23;
            }

            this.this$0.hour.setText(this.this$0.df_2Integer.format((long)this.this$0.timeHour) + this.this$0.mContext.getString(2131759111));
         }
      });
      this.imageButtonAdd5.setOnClickListener(new View.OnClickListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.timeMinute != 59) {
               SetTimeView var2 = this.this$0;
               var2.timeMinute = var2.timeMinute + 1;
            } else {
               this.this$0.timeMinute = 0;
            }

            this.this$0.minute.setText(this.this$0.df_2Integer.format((long)this.this$0.timeMinute) + this.this$0.mContext.getString(2131759112));
         }
      });
      this.imageButtonReduce5.setOnClickListener(new View.OnClickListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.timeMinute != 0) {
               SetTimeView var2 = this.this$0;
               var2.timeMinute = var2.timeMinute - 1;
            } else {
               this.this$0.timeMinute = 59;
            }

            this.this$0.minute.setText(this.this$0.df_2Integer.format((long)this.this$0.timeMinute) + this.this$0.mContext.getString(2131759112));
         }
      });
   }

   private void ButtonUiEvent(View var1) {
      if (this.imageButtonAdd1 == null) {
         this.imageButtonAdd1 = (ImageButton)var1.findViewById(2131361898);
      }

      if (this.imageButtonAdd2 == null) {
         this.imageButtonAdd2 = (ImageButton)var1.findViewById(2131361899);
      }

      if (this.imageButtonAdd3 == null) {
         this.imageButtonAdd3 = (ImageButton)var1.findViewById(2131361900);
      }

      if (this.imageButtonAdd4 == null) {
         this.imageButtonAdd4 = (ImageButton)var1.findViewById(2131361901);
      }

      if (this.imageButtonAdd5 == null) {
         this.imageButtonAdd5 = (ImageButton)var1.findViewById(2131361902);
      }

      if (this.imageButtonReduce1 == null) {
         this.imageButtonReduce1 = (ImageButton)var1.findViewById(2131363080);
      }

      if (this.imageButtonReduce2 == null) {
         this.imageButtonReduce2 = (ImageButton)var1.findViewById(2131363081);
      }

      if (this.imageButtonReduce3 == null) {
         this.imageButtonReduce3 = (ImageButton)var1.findViewById(2131363082);
      }

      if (this.imageButtonReduce4 == null) {
         this.imageButtonReduce4 = (ImageButton)var1.findViewById(2131363083);
      }

      if (this.imageButtonReduce5 == null) {
         this.imageButtonReduce5 = (ImageButton)var1.findViewById(2131363084);
      }

      if (this.ok == null) {
         this.ok = (Button)var1.findViewById(2131362918);
      }

      if (this.cancel == null) {
         this.cancel = (Button)var1.findViewById(2131362092);
      }

      this.imageButtonAdd1.setOnTouchListener(new View.OnTouchListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.imageButtonAdd1.setBackgroundResource(2131234067);
            } else {
               this.this$0.imageButtonAdd1.setBackgroundResource(2131234065);
            }

            return false;
         }
      });
      this.imageButtonAdd2.setOnTouchListener(new View.OnTouchListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.imageButtonAdd2.setBackgroundResource(2131234067);
            } else {
               this.this$0.imageButtonAdd2.setBackgroundResource(2131234065);
            }

            return false;
         }
      });
      this.imageButtonAdd3.setOnTouchListener(new View.OnTouchListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.imageButtonAdd3.setBackgroundResource(2131234067);
            } else {
               this.this$0.imageButtonAdd3.setBackgroundResource(2131234065);
            }

            return false;
         }
      });
      this.imageButtonAdd4.setOnTouchListener(new View.OnTouchListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.imageButtonAdd4.setBackgroundResource(2131234067);
            } else {
               this.this$0.imageButtonAdd4.setBackgroundResource(2131234065);
            }

            return false;
         }
      });
      this.imageButtonAdd5.setOnTouchListener(new View.OnTouchListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.imageButtonAdd5.setBackgroundResource(2131234067);
            } else {
               this.this$0.imageButtonAdd5.setBackgroundResource(2131234065);
            }

            return false;
         }
      });
      this.imageButtonReduce1.setOnTouchListener(new View.OnTouchListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.imageButtonReduce1.setBackgroundResource(2131234068);
            } else {
               this.this$0.imageButtonReduce1.setBackgroundResource(2131234066);
            }

            return false;
         }
      });
      this.imageButtonReduce2.setOnTouchListener(new View.OnTouchListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.imageButtonReduce2.setBackgroundResource(2131234068);
            } else {
               this.this$0.imageButtonReduce2.setBackgroundResource(2131234066);
            }

            return false;
         }
      });
      this.imageButtonReduce3.setOnTouchListener(new View.OnTouchListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.imageButtonReduce3.setBackgroundResource(2131234068);
            } else {
               this.this$0.imageButtonReduce3.setBackgroundResource(2131234066);
            }

            return false;
         }
      });
      this.imageButtonReduce4.setOnTouchListener(new View.OnTouchListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.imageButtonReduce4.setBackgroundResource(2131234068);
            } else {
               this.this$0.imageButtonReduce4.setBackgroundResource(2131234066);
            }

            return false;
         }
      });
      this.imageButtonReduce5.setOnTouchListener(new View.OnTouchListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.imageButtonReduce5.setBackgroundResource(2131234068);
            } else {
               this.this$0.imageButtonReduce5.setBackgroundResource(2131234066);
            }

            return false;
         }
      });
      this.ok.setOnTouchListener(new View.OnTouchListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.ok.setBackgroundResource(2131233359);
            } else {
               this.this$0.ok.setBackgroundResource(2131233358);
            }

            return false;
         }
      });
      this.cancel.setOnTouchListener(new View.OnTouchListener(this) {
         final SetTimeView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.cancel.setBackgroundResource(2131233359);
            } else {
               this.this$0.cancel.setBackgroundResource(2131233358);
            }

            return false;
         }
      });
   }

   private void InitTextViewData(View var1, String var2) {
      if (this.year == null) {
         this.year = (TextView)var1.findViewById(2131363804);
      }

      if (this.month == null) {
         this.month = (TextView)var1.findViewById(2131362877);
      }

      if (this.day == null) {
         this.day = (TextView)var1.findViewById(2131362177);
      }

      if (this.hour == null) {
         this.hour = (TextView)var1.findViewById(2131362373);
      }

      if (this.minute == null) {
         this.minute = (TextView)var1.findViewById(2131362856);
      }

      if (this.title == null) {
         this.title = (TextView)var1.findViewById(2131363545);
      }

      Date var3 = new Date();
      String var4 = (new SimpleDateFormat("yyyyMMddHHmmss")).format(var3);
      this.timeYear = Integer.parseInt(var4.substring(0, 4));
      this.year.setText(this.timeYear + this.mContext.getString(2131759108));
      this.timeMonth = Integer.parseInt(var4.substring(4, 6));
      this.month.setText(this.timeMonth + this.mContext.getString(2131759109));
      this.timeDay = Integer.parseInt(var4.substring(6, 8));
      this.day.setText(this.timeDay + this.mContext.getString(2131759110));
      this.timeHour = Integer.parseInt(var4.substring(8, 10));
      this.hour.setText(this.timeHour + this.mContext.getString(2131759111));
      this.timeMinute = Integer.parseInt(var4.substring(10, 12));
      this.minute.setText(this.timeMinute + this.mContext.getString(2131759112));
      if (var2 != null) {
         this.title.setText("<  " + var2 + "  >");
      } else {
         this.title.setText("<  Set Time  >");
      }

   }

   private void eventMethod(View var1, String var2, boolean var3, boolean var4, boolean var5, boolean var6, boolean var7, TimeResultListener var8, AlertDialog var9) {
      this.intiUi(var1, var3, var4, var5, var6, var7);
      this.InitTextViewData(var1, var2);
      this.ButtonUiEvent(var1);
      this.ButtClickEvent(var1, var8, var9);
   }

   private void intiUi(View var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6) {
      if (this.year == null) {
         this.year = (TextView)var1.findViewById(2131363804);
      }

      if (this.month == null) {
         this.month = (TextView)var1.findViewById(2131362877);
      }

      if (this.day == null) {
         this.day = (TextView)var1.findViewById(2131362177);
      }

      if (this.hour == null) {
         this.hour = (TextView)var1.findViewById(2131362373);
      }

      if (this.minute == null) {
         this.minute = (TextView)var1.findViewById(2131362856);
      }

      if (this.title == null) {
         this.title = (TextView)var1.findViewById(2131363545);
      }

      if (this.imageButtonAdd1 == null) {
         this.imageButtonAdd1 = (ImageButton)var1.findViewById(2131361898);
      }

      if (this.imageButtonAdd2 == null) {
         this.imageButtonAdd2 = (ImageButton)var1.findViewById(2131361899);
      }

      if (this.imageButtonAdd3 == null) {
         this.imageButtonAdd3 = (ImageButton)var1.findViewById(2131361900);
      }

      if (this.imageButtonAdd4 == null) {
         this.imageButtonAdd4 = (ImageButton)var1.findViewById(2131361901);
      }

      if (this.imageButtonAdd5 == null) {
         this.imageButtonAdd5 = (ImageButton)var1.findViewById(2131361902);
      }

      if (this.imageButtonReduce1 == null) {
         this.imageButtonReduce1 = (ImageButton)var1.findViewById(2131363080);
      }

      if (this.imageButtonReduce2 == null) {
         this.imageButtonReduce2 = (ImageButton)var1.findViewById(2131363081);
      }

      if (this.imageButtonReduce3 == null) {
         this.imageButtonReduce3 = (ImageButton)var1.findViewById(2131363082);
      }

      if (this.imageButtonReduce4 == null) {
         this.imageButtonReduce4 = (ImageButton)var1.findViewById(2131363083);
      }

      if (this.imageButtonReduce5 == null) {
         this.imageButtonReduce5 = (ImageButton)var1.findViewById(2131363084);
      }

      if (!var2) {
         this.year.setVisibility(8);
         this.imageButtonAdd1.setVisibility(8);
         this.imageButtonReduce1.setVisibility(8);
      } else {
         this.year.setVisibility(0);
         this.imageButtonAdd1.setVisibility(0);
         this.imageButtonReduce1.setVisibility(0);
      }

      if (!var3) {
         this.month.setVisibility(8);
         this.imageButtonAdd2.setVisibility(8);
         this.imageButtonReduce2.setVisibility(8);
      } else {
         this.month.setVisibility(0);
         this.imageButtonAdd2.setVisibility(0);
         this.imageButtonReduce2.setVisibility(0);
      }

      if (!var4) {
         this.day.setVisibility(8);
         this.imageButtonAdd3.setVisibility(8);
         this.imageButtonReduce3.setVisibility(8);
      } else {
         this.day.setVisibility(0);
         this.imageButtonAdd3.setVisibility(0);
         this.imageButtonReduce3.setVisibility(0);
      }

      if (!var5) {
         this.hour.setVisibility(8);
         this.imageButtonAdd4.setVisibility(8);
         this.imageButtonReduce4.setVisibility(8);
      } else {
         this.hour.setVisibility(0);
         this.imageButtonAdd4.setVisibility(0);
         this.imageButtonReduce4.setVisibility(0);
      }

      if (!var6) {
         this.minute.setVisibility(8);
         this.imageButtonAdd5.setVisibility(8);
         this.imageButtonReduce5.setVisibility(8);
      } else {
         this.minute.setVisibility(0);
         this.imageButtonAdd5.setVisibility(0);
         this.imageButtonReduce5.setVisibility(0);
      }

   }

   public void showDialog(Context var1, String var2, boolean var3, boolean var4, boolean var5, boolean var6, boolean var7, TimeResultListener var8) {
      this.mContext = var1;
      View var9 = LayoutInflater.from(var1).inflate(2131558624, (ViewGroup)null, true);
      AlertDialog var10 = (new AlertDialog.Builder(var1)).setView(var9).create();
      var10.setCancelable(false);
      this.eventMethod(var9, var2, var3, var4, var5, var6, var7, var8, var10);
      var10.getWindow().setBackgroundDrawableResource(17170445);
      var10.show();
   }

   public interface TimeResultListener {
      void result(int var1, int var2, int var3, int var4, int var5);
   }
}
