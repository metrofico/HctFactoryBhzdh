package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._283.UiMgr;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CenterControlView extends LinearLayout implements View.OnClickListener {
   private BtnView ac;
   private BtnView add;
   private BtnView auto_cycle;
   private ExecutorService executorService;
   private ImageView image_wind_1;
   private ImageView image_wind_2;
   private ImageView image_wind_3;
   private ImageView image_wind_4;
   private ImageView image_wind_5;
   private ImageView image_wind_6;
   private ImageView image_wind_7;
   private List listWinds;
   private Context mContext;
   private View mView;
   private BtnView sub;
   private BtnView wind_down;
   private BtnView wind_up;
   private BtnView wind_windows;

   public CenterControlView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CenterControlView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CenterControlView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.listWinds = new ArrayList();
      this.executorService = Executors.newSingleThreadExecutor();
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558438, this, true);
      this.initView();
   }

   private void initView() {
      this.add = (BtnView)this.mView.findViewById(2131362067);
      this.sub = (BtnView)this.mView.findViewById(2131362069);
      this.ac = (BtnView)this.mView.findViewById(2131362004);
      this.wind_windows = (BtnView)this.mView.findViewById(2131362071);
      this.wind_up = (BtnView)this.mView.findViewById(2131362070);
      this.wind_down = (BtnView)this.mView.findViewById(2131362068);
      this.auto_cycle = (BtnView)this.mView.findViewById(2131362008);
      this.image_wind_1 = (ImageView)this.mView.findViewById(2131362480);
      this.image_wind_2 = (ImageView)this.mView.findViewById(2131362481);
      this.image_wind_3 = (ImageView)this.mView.findViewById(2131362482);
      this.image_wind_4 = (ImageView)this.mView.findViewById(2131362483);
      this.image_wind_5 = (ImageView)this.mView.findViewById(2131362484);
      this.image_wind_6 = (ImageView)this.mView.findViewById(2131362485);
      this.image_wind_7 = (ImageView)this.mView.findViewById(2131362486);
      this.executorService.execute(new Runnable(this) {
         final CenterControlView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listWinds.add(this.this$0.image_wind_7);
            this.this$0.listWinds.add(this.this$0.image_wind_6);
            this.this$0.listWinds.add(this.this$0.image_wind_5);
            this.this$0.listWinds.add(this.this$0.image_wind_4);
            this.this$0.listWinds.add(this.this$0.image_wind_3);
            this.this$0.listWinds.add(this.this$0.image_wind_2);
            this.this$0.listWinds.add(this.this$0.image_wind_1);
         }
      });
      this.add.setOnClickListener(this);
      this.sub.setOnClickListener(this);
      this.ac.setOnClickListener(this);
      this.wind_windows.setOnClickListener(this);
      this.wind_up.setOnClickListener(this);
      this.wind_down.setOnClickListener(this);
      this.auto_cycle.setOnClickListener(this);
   }

   private void sendMsg(byte var1, boolean var2) {
      MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)(var2 ^ 1)});
   }

   private void setFrontWind(int var1) {
      if (var1 != 0 && var1 != 1 && var1 != 2) {
         if (var1 != 3) {
            if (var1 != 5) {
               if (var1 != 6) {
                  switch (var1) {
                     case 11:
                        this.wind_windows.setSelect(true);
                        this.wind_up.setSelect(false);
                        this.wind_down.setSelect(false);
                        break;
                     case 12:
                        this.wind_windows.setSelect(true);
                        this.wind_up.setSelect(false);
                        this.wind_down.setSelect(true);
                        break;
                     case 13:
                        this.wind_windows.setSelect(true);
                        this.wind_up.setSelect(true);
                        this.wind_down.setSelect(false);
                        break;
                     case 14:
                        this.wind_windows.setSelect(true);
                        this.wind_up.setSelect(true);
                        this.wind_down.setSelect(true);
                  }
               } else {
                  this.wind_windows.setSelect(false);
                  this.wind_up.setSelect(true);
                  this.wind_down.setSelect(false);
               }
            } else {
               this.wind_windows.setSelect(false);
               this.wind_up.setSelect(true);
               this.wind_down.setSelect(true);
            }
         } else {
            this.wind_windows.setSelect(false);
            this.wind_up.setSelect(false);
            this.wind_down.setSelect(true);
         }
      } else {
         this.wind_windows.setSelect(false);
         this.wind_up.setSelect(false);
         this.wind_down.setSelect(false);
      }

   }

   private void setWindPower(int var1) {
      for(int var2 = 1; var2 <= this.listWinds.size(); ++var2) {
         if (var2 <= var1) {
            ((ImageView)this.listWinds.get(var2 - 1)).setVisibility(0);
         } else {
            ((ImageView)this.listWinds.get(var2 - 1)).setVisibility(4);
         }
      }

   }

   public void onClick(View var1) {
      int var2 = var1.getId();
      if (var2 != 2131362004) {
         if (var2 != 2131362008) {
            switch (var2) {
               case 2131362067:
                  UiMgr.addWind();
                  break;
               case 2131362068:
                  this.sendMsg((byte)25, GeneralDzData.fornt_left_blow_foot);
                  if (!GeneralDzData.fornt_left_blow_foot) {
                     if (GeneralDzData.fornt_left_blow_head) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 3});
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 1});
                     }
                  } else if (!GeneralDzData.fornt_left_blow_head) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 0});
                  }
                  break;
               case 2131362069:
                  UiMgr.subWind();
                  break;
               case 2131362070:
                  this.sendMsg((byte)24, GeneralDzData.fornt_left_blow_head);
                  if (!GeneralDzData.fornt_left_blow_head) {
                     if (GeneralDzData.fornt_left_blow_foot) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 3});
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 2});
                     }
                  } else if (!GeneralDzData.fornt_left_blow_foot) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 0});
                  }
                  break;
               case 2131362071:
                  this.sendMsg((byte)26, GeneralDzData.fornt_left_blow_window);
            }
         } else {
            this.sendMsg((byte)19, GeneralAirData.in_out_cycle);
         }
      } else {
         this.sendMsg((byte)15, GeneralAirData.ac);
      }

   }

   public void refreshUi() {
      this.ac.setSelect(GeneralAirData.ac);
      this.auto_cycle.setSelect(GeneralAirData.in_out_cycle);
      this.setFrontWind(GeneralDzData.air_front_wind);
      this.setWindPower(GeneralAirData.front_wind_level);
   }
}
