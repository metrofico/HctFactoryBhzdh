package com.hzbhd.canbus.car_cus._439.air.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._439.air.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._439.air.Interface.AirInfoObserver;
import com.hzbhd.canbus.car_cus._439.air.data.AirData;
import com.hzbhd.canbus.car_cus._439.air.observer.AirBuilder;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.ui.view.colorview.ColorView;
import com.hzbhd.util.LogUtil;
import java.util.Calendar;

public class AirSettingView extends LinearLayout implements AirInfoObserver {
   int IntEditTimeHour;
   int IntEditTimeMinute;
   private TextView alert;
   private CheckItemView c1;
   private CheckItemView c2;
   private CheckItemView c3;
   private TextView cancel;
   private Context context;
   private TextView currentTimeTitle;
   int hour;
   int minute;
   private View parentView;
   private TextView resultTime;
   private TextView setTime;
   private TextView setTimeOpen;
   private TextView setTimeTitle;
   private FrameLayout settingTime;
   private TextView settingsTimeHour;
   private TextView settingsTimeMin;
   private TextView timeDay;
   private LinearLayout timeDayLine;
   int timeDayStart;
   private ColorView timeToday;
   private ColorView timeTomorrow;
   private TextView title;

   public AirSettingView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AirSettingView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public AirSettingView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.timeDayStart = 0;
      this.hour = 0;
      this.minute = 0;
      this.context = var1;
      View var4 = LayoutInflater.from(var1).inflate(2131558540, this, true);
      this.parentView = var4;
      this.alert = (TextView)var4.findViewById(2131361912);
      ((LinearLayout)this.parentView.findViewById(2131362932)).setOnClickListener(new View.OnClickListener(this) {
         final AirSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            ((InputMethodManager)this.this$0.getContext().getSystemService("input_method")).hideSoftInputFromWindow(var1.getWindowToken(), 0);
         }
      });
      this.resultTime = (TextView)this.parentView.findViewById(2131363099);
      this.title = (TextView)this.parentView.findViewById(2131363545);
      this.setTimeTitle = (TextView)this.parentView.findViewById(2131363316);
      this.currentTimeTitle = (TextView)this.parentView.findViewById(2131362161);
      this.setTimeOpen = (TextView)this.parentView.findViewById(2131363318);
      this.settingTime = (FrameLayout)this.parentView.findViewById(2131363321);
      this.timeDay = (TextView)this.parentView.findViewById(2131363527);
      this.timeToday = (ColorView)this.parentView.findViewById(2131363531);
      this.timeTomorrow = (ColorView)this.parentView.findViewById(2131363532);
      this.c1 = (CheckItemView)this.parentView.findViewById(2131362076);
      this.c2 = (CheckItemView)this.parentView.findViewById(2131362077);
      this.c3 = (CheckItemView)this.parentView.findViewById(2131362078);
      this.timeDayLine = (LinearLayout)this.parentView.findViewById(2131363528);
      this.settingsTimeHour = (TextView)this.parentView.findViewById(2131363324);
      this.settingsTimeMin = (TextView)this.parentView.findViewById(2131363325);
      if (this.timeDayStart == 0) {
         this.timeToday.setSelected(true);
         this.timeTomorrow.setSelected(false);
      } else {
         this.timeToday.setSelected(false);
         this.timeTomorrow.setSelected(true);
      }

      this.c1.setTitle("3s");
      this.c2.setTitle("5s");
      this.c3.setTitle("10s");
      if (AirData.exitTime == 3000) {
         this.c1.check(true);
      } else if (AirData.exitTime == 5000) {
         this.c2.check(true);
      } else if (AirData.exitTime == 10000) {
         this.c3.check(true);
      }

      this.timeDay.setOnClickListener(new View.OnClickListener(this) {
         final AirSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.timeDayLine.getVisibility() == 0) {
               this.this$0.timeDayLine.setVisibility(8);
            } else {
               this.this$0.timeDayLine.setVisibility(0);
            }

         }
      });
      this.timeToday.setOnClickListener(new View.OnClickListener(this) {
         final AirSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.timeToday.setSelected(true);
            this.this$0.timeTomorrow.setSelected(false);
            this.this$0.timeDay.setText(2131766033);
            this.this$0.timeDayStart = 0;
            this.this$0.timeDayLine.setVisibility(8);
         }
      });
      this.timeTomorrow.setOnClickListener(new View.OnClickListener(this) {
         final AirSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.timeToday.setSelected(false);
            this.this$0.timeTomorrow.setSelected(true);
            this.this$0.timeDay.setText(2131766034);
            this.this$0.timeDayStart = 1;
            this.this$0.timeDayLine.setVisibility(8);
         }
      });
      this.setTimeOpen.setOnClickListener(new View.OnClickListener(this) {
         final AirSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.settingTime.setVisibility(0);
         }
      });
      this.settingTime.setOnClickListener(new View.OnClickListener(this) {
         final AirSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.settingTime.getVisibility() == 0) {
               this.this$0.settingTime.setVisibility(8);
            } else {
               this.this$0.settingTime.setVisibility(0);
            }

         }
      });
      TextView var5 = (TextView)this.parentView.findViewById(2131363315);
      this.setTime = var5;
      var5.setOnTouchListener(new View.OnTouchListener(this) {
         final AirSettingView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.setTime.setBackgroundResource(2131231479);
               return true;
            } else if (1 != var2.getAction()) {
               return false;
            } else {
               this.this$0.alert.setVisibility(8);
               this.this$0.alert.setText("");
               SendKeyManager.getInstance().playBeep(0);
               this.this$0.setTime.setBackgroundResource(2131231478);
               Calendar var7 = Calendar.getInstance();
               this.this$0.hour = var7.get(11);
               this.this$0.minute = var7.get(12);
               String var4 = ((EditText)this.this$0.parentView.findViewById(2131363529)).getText().toString();
               String var5 = ((EditText)this.this$0.parentView.findViewById(2131363530)).getText().toString();
               if (!var4.equals("") && !var5.equals("")) {
                  this.this$0.IntEditTimeHour = Integer.parseInt(var4);
                  this.this$0.IntEditTimeMinute = Integer.parseInt(var5);
                  if (LogUtil.log5()) {
                     LogUtil.d("onTouch(): ----" + this.this$0.hour + "-----" + this.this$0.IntEditTimeHour + "-----" + this.this$0.timeDayStart);
                  }

                  float var3;
                  String var8;
                  if (this.this$0.timeDayStart == 0) {
                     if (this.this$0.IntEditTimeHour < this.this$0.hour) {
                        ((EditText)this.this$0.parentView.findViewById(2131363529)).setText("");
                        ((EditText)this.this$0.parentView.findViewById(2131363530)).setText("");
                        this.this$0.alert.setVisibility(0);
                        this.this$0.alert.setText(this.this$0.getContext().getString(2131766032));
                        this.this$0.settingTime.setVisibility(8);
                        return true;
                     }

                     if (this.this$0.IntEditTimeHour == this.this$0.hour && this.this$0.IntEditTimeMinute < this.this$0.minute) {
                        ((EditText)this.this$0.parentView.findViewById(2131363529)).setText("");
                        ((EditText)this.this$0.parentView.findViewById(2131363530)).setText("");
                        this.this$0.alert.setVisibility(0);
                        this.this$0.alert.setText(this.this$0.getContext().getString(2131766032));
                        this.this$0.settingTime.setVisibility(8);
                        return true;
                     }

                     var3 = (float)(60 - this.this$0.minute + this.this$0.IntEditTimeMinute) / 60.0F;
                     if (LogUtil.log5()) {
                        LogUtil.d("onTouch(): ------" + var3);
                     }

                     var8 = String.valueOf((float)(this.this$0.IntEditTimeHour - this.this$0.hour) + var3 - 1.0F);
                  } else {
                     var3 = (float)(60 - this.this$0.minute + this.this$0.IntEditTimeMinute) / 60.0F;
                     if (LogUtil.log5()) {
                        LogUtil.d("onTouch(): ------" + var3);
                     }

                     var8 = String.valueOf((float)(24 - this.this$0.hour + this.this$0.IntEditTimeHour) + var3 - 1.0F);
                  }

                  if (var8.length() == 0) {
                     return true;
                  } else {
                     try {
                        var3 = Float.valueOf(var8);
                     } catch (Exception var6) {
                        ((EditText)this.this$0.parentView.findViewById(2131363529)).setText("");
                        ((EditText)this.this$0.parentView.findViewById(2131363530)).setText("");
                        this.this$0.alert.setVisibility(0);
                        this.this$0.alert.setText(this.this$0.getContext().getString(2131766009));
                        return true;
                     }

                     if (this.this$0.IntEditTimeHour > 24) {
                        ((EditText)this.this$0.parentView.findViewById(2131363529)).setText("");
                        ((EditText)this.this$0.parentView.findViewById(2131363530)).setText("");
                        this.this$0.alert.setVisibility(0);
                        this.this$0.alert.setText(this.this$0.getContext().getString(2131766009));
                        return true;
                     } else if ((double)var3 > 24.0) {
                        ((EditText)this.this$0.parentView.findViewById(2131363529)).setText("");
                        ((EditText)this.this$0.parentView.findViewById(2131363530)).setText("");
                        this.this$0.alert.setVisibility(0);
                        this.this$0.alert.setText(this.this$0.getContext().getString(2131766010));
                        this.this$0.settingTime.setVisibility(8);
                        return true;
                     } else if (this.this$0.IntEditTimeMinute > 60) {
                        ((EditText)this.this$0.parentView.findViewById(2131363529)).setText("");
                        ((EditText)this.this$0.parentView.findViewById(2131363530)).setText("");
                        this.this$0.alert.setVisibility(0);
                        this.this$0.alert.setText(this.this$0.getContext().getString(2131766009));
                        return true;
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -110, 10, (byte)((int)Math.ceil((double)(var3 * 10.0F)))});
                        ((EditText)this.this$0.parentView.findViewById(2131363529)).setText("");
                        ((EditText)this.this$0.parentView.findViewById(2131363530)).setText("");
                        this.this$0.settingsTimeHour.setText(var4);
                        this.this$0.settingsTimeMin.setText(var5);
                        this.this$0.settingTime.setVisibility(8);
                        ((InputMethodManager)this.this$0.getContext().getSystemService("input_method")).hideSoftInputFromWindow(var1.getWindowToken(), 0);
                        return true;
                     }
                  }
               } else {
                  return true;
               }
            }
         }
      });
      var5 = (TextView)this.parentView.findViewById(2131362092);
      this.cancel = var5;
      var5.setOnTouchListener(new View.OnTouchListener(this) {
         final AirSettingView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.cancel.setBackgroundResource(2131231479);
               return true;
            } else if (1 == var2.getAction()) {
               this.this$0.cancel.setBackgroundResource(2131231478);
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 10, -1});
               return true;
            } else {
               return false;
            }
         }
      });
   }

   public void getAction(ActionCallback var1) {
      this.c1.setOnClickListener(new View.OnClickListener(this, var1) {
         final AirSettingView this$0;
         final ActionCallback val$callback;

         {
            this.this$0 = var1;
            this.val$callback = var2;
         }

         public void onClick(View var1) {
            SharePreUtil.setIntValue(this.this$0.context, "key.air.exit.time", 3000);
            AirData.exitTime = 3000;
            this.val$callback.toDo("C1");
            this.this$0.c1.check(true);
            this.this$0.c2.check(false);
            this.this$0.c3.check(false);
         }
      });
      this.c2.setOnClickListener(new View.OnClickListener(this, var1) {
         final AirSettingView this$0;
         final ActionCallback val$callback;

         {
            this.this$0 = var1;
            this.val$callback = var2;
         }

         public void onClick(View var1) {
            SharePreUtil.setIntValue(this.this$0.context, "key.air.exit.time", 5000);
            AirData.exitTime = 5000;
            this.val$callback.toDo("C2");
            this.this$0.c1.check(false);
            this.this$0.c2.check(true);
            this.this$0.c3.check(false);
         }
      });
      this.c3.setOnClickListener(new View.OnClickListener(this, var1) {
         final AirSettingView this$0;
         final ActionCallback val$callback;

         {
            this.this$0 = var1;
            this.val$callback = var2;
         }

         public void onClick(View var1) {
            SharePreUtil.setIntValue(this.this$0.context, "key.air.exit.time", 10000);
            AirData.exitTime = 10000;
            this.val$callback.toDo("C3");
            this.this$0.c1.check(false);
            this.this$0.c2.check(false);
            this.this$0.c3.check(true);
         }
      });
   }

   public void infoChange() {
      if (AirData.appointmentTime == 0) {
         this.resultTime.setText("--");
      } else if (AirData.appointmentTime == 255) {
         this.resultTime.setText("--");
      } else {
         this.resultTime.setText((float)AirData.appointmentTime / 10.0F + "H");
      }

   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      AirBuilder.getInstance().register(this);
   }

   public void resetTxt() {
      this.title.setText(this.getContext().getString(2131765988));
      this.setTimeTitle.setText(this.getContext().getString(2131765989));
      this.setTime.setText(this.getContext().getString(2131765990));
      this.currentTimeTitle.setText(this.getContext().getString(2131765991));
      this.cancel.setText(this.getContext().getString(2131767924));
      this.timeToday.setText(2131766033);
      this.timeTomorrow.setText(2131766034);
      this.setTimeOpen.setText(2131764752);
      if (this.timeDayStart == 0) {
         this.timeDay.setText(2131766033);
      } else {
         this.timeDay.setText(2131766034);
      }

      if (AirData.appointmentTime == 0) {
         this.resultTime.setText("--");
      } else if (AirData.appointmentTime == 255) {
         this.resultTime.setText("--");
      } else {
         this.resultTime.setText((float)AirData.appointmentTime / 10.0F + "H");
      }

   }
}
