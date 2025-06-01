package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.bean.TimeSyncMode;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import com.hzbhd.canbus.car_cus._283.view.SettingView;
import com.hzbhd.canbus.receiver.DateTimeReceiver;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarSettingTimeView extends RelativeLayout {
   private static final String DATE_INTERVAL = "-";
   private static final String TIME_INTERVAL = ":";
   private SettingDialogView car_time_mode;
   private ExecutorService executorService;
   private List listDateFormat;
   private List listSource;
   private List listTimeFormat;
   private Context mContext;
   private View mView;
   private SettingDialogView sdv_date_format;
   private SettingDialogView sdv_time_format;
   private SettingDialogView sdv_time_source;
   private SettingView sv_date;
   private SettingView sv_set_time;
   private SettingView sv_time_zone;
   private List timeModeFormat;

   public CarSettingTimeView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarSettingTimeView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarSettingTimeView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.listSource = new ArrayList();
      this.listTimeFormat = new ArrayList();
      this.listDateFormat = new ArrayList();
      this.timeModeFormat = new ArrayList();
      this.executorService = Executors.newSingleThreadExecutor();
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558449, this, true);
      this.initView();
      this.initClick();
      (new Handler()).postDelayed(new CarSettingTimeView$$ExternalSyntheticLambda0(this), 1000L);
   }

   private int getTimeSource(int var1) {
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return DataHandleUtils.setIntByteWithBit(0, 7, var2);
   }

   private String getTwo(int var1) {
      return var1 < 10 ? "0" + var1 : var1 + "";
   }

   private String getYear(int var1) {
      return "20" + this.getTwo(var1);
   }

   private void initClick() {
      CarSettingTimeView$$ExternalSyntheticLambda1 var1 = new CarSettingTimeView$$ExternalSyntheticLambda1(this);
      this.sdv_time_source.setOnItemClickListener(var1);
      this.sdv_time_format.setOnItemClickListener(var1);
      this.sdv_date_format.setOnItemClickListener(var1);
      this.car_time_mode.setOnItemClickListener(var1);
      this.sv_set_time.setOnClickListener(new View.OnClickListener(this) {
         final CarSettingTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            int var2 = GeneralDzData.time_format;
            boolean var3 = true;
            if (var2 != 1) {
               var3 = false;
            }

            (new TimePickerDialog(this.this$0.mContext, new TimePickerDialog.OnTimeSetListener(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onTimeSet(TimePicker var1, int var2, int var3) {
                  MessageSender.sendMsg(new byte[]{22, -53, (byte)this.this$1.this$0.getTimeSource(GeneralDzData.time_source), (byte)var2, (byte)var3, (byte)GeneralDzData.time_date3, (byte)GeneralDzData.time_date4, (byte)GeneralDzData.time_format, (byte)GeneralDzData.date_year, (byte)GeneralDzData.date_month, (byte)GeneralDzData.date_day, (byte)GeneralDzData.date_format});
               }
            }, GeneralDzData.time_hour, GeneralDzData.time_minute, var3)).show();
         }
      });
      this.sv_date.setOnClickListener(new View.OnClickListener(this) {
         final CarSettingTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            (new DatePickerDialog(this.this$0.mContext, new DatePickerDialog.OnDateSetListener(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onDateSet(DatePicker var1, int var2, int var3, int var4) {
                  MessageSender.sendMsg(new byte[]{22, -53, (byte)this.this$1.this$0.getTimeSource(GeneralDzData.time_source), (byte)GeneralDzData.time_hour, (byte)GeneralDzData.time_minute, (byte)GeneralDzData.time_date3, (byte)GeneralDzData.time_date4, (byte)GeneralDzData.time_format, (byte)this.this$1.this$0.setYear(var2), (byte)(var3 + 1), (byte)var4, (byte)GeneralDzData.date_format});
               }
            }, Integer.valueOf(this.this$0.getYear(GeneralDzData.date_year)), GeneralDzData.date_month - 1, GeneralDzData.date_day)).show();
         }
      });
   }

   private void initView() {
      this.sdv_time_source = (SettingDialogView)this.mView.findViewById(2131363283);
      this.car_time_mode = (SettingDialogView)this.mView.findViewById(2131362109);
      this.sdv_time_format = (SettingDialogView)this.mView.findViewById(2131363282);
      this.sdv_date_format = (SettingDialogView)this.mView.findViewById(2131363267);
      this.sv_set_time = (SettingView)this.mView.findViewById(2131363467);
      this.sv_time_zone = (SettingView)this.mView.findViewById(2131363469);
      this.sv_date = (SettingView)this.mView.findViewById(2131363448);
      this.executorService.execute(new Runnable(this) {
         final CarSettingTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listSource.add(new SettingDialogBean(this.this$0.mContext.getString(2131760760)));
            this.this$0.listSource.add(new SettingDialogBean(this.this$0.mContext.getString(2131760761)));
            this.this$0.sdv_time_source.setListFirst(this.this$0.listSource);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listTimeFormat.add(new SettingDialogBean(this.this$0.mContext.getString(2131760758)));
            this.this$0.listTimeFormat.add(new SettingDialogBean(this.this$0.mContext.getString(2131760759)));
            this.this$0.sdv_time_format.setListFirst(this.this$0.listTimeFormat);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listDateFormat.add(new SettingDialogBean(this.this$0.mContext.getString(2131760602)));
            this.this$0.listDateFormat.add(new SettingDialogBean(this.this$0.mContext.getString(2131760603)));
            this.this$0.listDateFormat.add(new SettingDialogBean(this.this$0.mContext.getString(2131760604)));
            this.this$0.sdv_date_format.setListFirst(this.this$0.listDateFormat);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingTimeView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.timeModeFormat.add(new SettingDialogBean(this.this$0.mContext.getString(2131760705)));
            this.this$0.timeModeFormat.add(new SettingDialogBean(this.this$0.mContext.getString(2131760706)));
            this.this$0.timeModeFormat.add(new SettingDialogBean(this.this$0.mContext.getString(2131760707)));
            this.this$0.car_time_mode.setListFirst(this.this$0.timeModeFormat);

            try {
               this.this$0.car_time_mode.setSelect(System.getInt(this.this$0.mContext.getContentResolver(), "283.time.sync.mode"));
            } catch (Settings.SettingNotFoundException var2) {
               var2.printStackTrace();
            }

         }
      });
   }

   private String set12Hour(String param1) {
      // $FF: Couldn't be decompiled
   }

   private int setYear(int var1) {
      return var1 - 2000;
   }

   // $FF: synthetic method
   void lambda$initClick$1$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingTimeView(View var1, int var2) {
      switch (var1.getId()) {
         case 2131362109:
            if (var2 == 0) {
               System.putInt(this.mContext.getContentResolver(), "283.time.sync.mode", TimeSyncMode.MODE_1);
            } else if (var2 == 1) {
               System.putInt(this.mContext.getContentResolver(), "283.time.sync.mode", TimeSyncMode.MODE_2);
            } else if (var2 == 2) {
               System.putInt(this.mContext.getContentResolver(), "283.time.sync.mode", TimeSyncMode.MODE_3);
            }

            this.car_time_mode.setSelect(var2);
            (new DateTimeReceiver()).reportDateAndTime(this.mContext);
            break;
         case 2131363267:
            MessageSender.sendMsg(new byte[]{22, -53, (byte)this.getTimeSource(GeneralDzData.time_source), (byte)GeneralDzData.time_hour, (byte)GeneralDzData.time_minute, (byte)GeneralDzData.time_date3, (byte)GeneralDzData.time_date4, (byte)GeneralDzData.time_format, (byte)GeneralDzData.date_year, (byte)GeneralDzData.date_month, (byte)GeneralDzData.date_day, (byte)(var2 + 1)});
            break;
         case 2131363282:
            MessageSender.sendMsg(new byte[]{22, -53, (byte)this.getTimeSource(GeneralDzData.time_source), (byte)GeneralDzData.time_hour, (byte)GeneralDzData.time_minute, (byte)GeneralDzData.time_date3, (byte)GeneralDzData.time_date4, (byte)var2, (byte)GeneralDzData.date_year, (byte)GeneralDzData.date_month, (byte)GeneralDzData.date_day, (byte)GeneralDzData.date_format});
            break;
         case 2131363283:
            MessageSender.sendMsg(new byte[]{22, -53, (byte)this.getTimeSource(var2), (byte)GeneralDzData.time_hour, (byte)GeneralDzData.time_minute, (byte)GeneralDzData.time_date3, (byte)GeneralDzData.time_date4, (byte)GeneralDzData.time_format, (byte)GeneralDzData.date_year, (byte)GeneralDzData.date_month, (byte)GeneralDzData.date_day, (byte)GeneralDzData.date_format});
      }

   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingTimeView() {
      this.refreshUi();
   }

   public void refreshUi() {
      this.sdv_time_source.setSelect(GeneralDzData.time_source);
      this.sdv_time_format.setSelect(GeneralDzData.time_format);
      if (GeneralDzData.date_format >= 1 && GeneralDzData.date_format <= 3) {
         this.sdv_date_format.setSelect(GeneralDzData.date_format - 1);
      }

      SettingView var4 = this.sv_set_time;
      int var1 = GeneralDzData.time_source;
      boolean var3 = false;
      boolean var2;
      if (var1 == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setEnable(var2);
      var4 = this.sv_time_zone;
      if (GeneralDzData.time_source == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setEnable(var2);
      var4 = this.sv_date;
      var2 = var3;
      if (GeneralDzData.time_source == 1) {
         var2 = true;
      }

      var4.setEnable(var2);
      if (GeneralDzData.time_hour != 255 && GeneralDzData.time_minute != 255) {
         if (GeneralDzData.time_format == 1) {
            this.sv_set_time.setValue(this.getTwo(GeneralDzData.time_hour) + ":" + this.getTwo(GeneralDzData.time_minute));
         } else {
            this.sv_set_time.setValue(this.set12Hour(this.getTwo(GeneralDzData.time_hour) + ":" + this.getTwo(GeneralDzData.time_minute)));
         }
      } else {
         this.sv_set_time.setValue("");
      }

      if (GeneralDzData.date_format == 1) {
         this.sv_date.setValue(this.getTwo(GeneralDzData.date_day) + "-" + this.getTwo(GeneralDzData.date_month) + "-" + this.getYear(GeneralDzData.date_year));
      } else if (GeneralDzData.date_format == 3) {
         this.sv_date.setValue(this.getTwo(GeneralDzData.date_month) + "-" + this.getTwo(GeneralDzData.date_day) + "-" + this.getYear(GeneralDzData.date_year));
      } else {
         this.sv_date.setValue(this.getYear(GeneralDzData.date_year) + "-" + this.getTwo(GeneralDzData.date_month) + "-" + this.getTwo(GeneralDzData.date_day));
      }

   }
}
