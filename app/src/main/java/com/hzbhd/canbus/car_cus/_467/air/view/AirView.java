package com.hzbhd.canbus.car_cus._467.air.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._467.air.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._467.air.Interface.AirInfoObserver;
import com.hzbhd.canbus.car_cus._467.air.data.AirData;
import com.hzbhd.canbus.car_cus._467.air.observer.AirBuilder;
import com.hzbhd.canbus.car_cus._467.air.util.AirTimer;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;

public class AirView extends LinearLayout implements AirInfoObserver, View.OnClickListener {
   private ImageView ac_img;
   private ActionCallback actionCallback;
   private ConstraintLayout air_page;
   private AirSettingView air_setting_view;
   private boolean autoExitTag;
   private ImageView auto_img;
   private ImageView defog_img;
   private Button exit_img;
   private ImageView heat_img;
   private ImageView in_out_cycle_img;
   private Handler mHandler;
   private int modeTag;
   private ImageView power_img;
   private ImageView setting_icon;
   private LinearLayout setting_view;
   private ImageView temp_down;
   private TextView temp_txt;
   private ImageView temp_up;
   private View view;
   private ImageView win_down_img;
   private ImageView win_up_img;
   private WindModeView windModeView;
   private ImageView wind_level_img;

   public AirView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AirView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public AirView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.autoExitTag = true;
      this.mHandler = new Handler(this, Looper.getMainLooper()) {
         final AirView this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            super.handleMessage(var1);
            if (var1.what == 255) {
               this.this$0.updateUi();
            }

         }
      };
      this.modeTag = 0;
      View var4 = LayoutInflater.from(var1).inflate(2131558562, this, true);
      this.view = var4;
      Button var5 = (Button)var4.findViewById(2131362219);
      this.exit_img = var5;
      var5.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
      LinearLayout var6 = (LinearLayout)this.findViewById(2131363322);
      this.setting_view = var6;
      var6.setVisibility(8);
      ImageView var7 = (ImageView)this.view.findViewById(2131363320);
      this.setting_icon = var7;
      var7.setOnClickListener(new View.OnClickListener(this) {
         final AirView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.resetTimer();
            if (this.this$0.setting_view.getVisibility() == 8) {
               this.this$0.setting_view.setVisibility(0);
            } else {
               this.this$0.setting_view.setVisibility(8);
            }

         }
      });
      this.setting_view.setOnClickListener(new View.OnClickListener(this) {
         final AirView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.resetTimer();
            ((InputMethodManager)this.this$0.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.this$0.view.getWindowToken(), 0);
            this.this$0.setting_view.setVisibility(8);
         }
      });
      AirSettingView var8 = (AirSettingView)this.view.findViewById(2131361910);
      this.air_setting_view = var8;
      var8.getAction(new ActionCallback(this) {
         final AirView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            this.this$0.resetTimer();
         }
      });
      this.initAirInfoView();
      this.initSetting();
   }

   private void initAirInfoView() {
      this.win_down_img = (ImageView)this.view.findViewById(2131363787);
      this.win_up_img = (ImageView)this.view.findViewById(2131363788);
      this.temp_txt = (TextView)this.view.findViewById(2131363510);
      this.wind_level_img = (ImageView)this.view.findViewById(2131363792);
      this.power_img = (ImageView)this.view.findViewById(2131362990);
      this.ac_img = (ImageView)this.view.findViewById(2131361842);
      this.in_out_cycle_img = (ImageView)this.view.findViewById(2131362500);
      this.auto_img = (ImageView)this.view.findViewById(2131361928);
      this.defog_img = (ImageView)this.view.findViewById(2131362181);
      this.heat_img = (ImageView)this.view.findViewById(2131362367);
      this.temp_down = (ImageView)this.view.findViewById(2131363509);
      this.temp_up = (ImageView)this.view.findViewById(2131363511);
      this.windModeView = (WindModeView)this.view.findViewById(2131363789);
      this.updateUi();
      this.windModeView.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
      this.power_img.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
      this.ac_img.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
      this.in_out_cycle_img.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
      this.auto_img.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
      this.defog_img.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
      this.heat_img.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
      this.initTouchEvent();
   }

   private void initTouchEvent() {
      this.win_up_img.setOnTouchListener(new View.OnTouchListener(this) {
         final AirView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            this.this$0.resetTimer();
            if (var2.getAction() == 0) {
               this.this$0.win_up_img.setBackgroundResource(2131231284);
               return true;
            } else if (var2.getAction() == 1) {
               SendKeyManager.getInstance().playBeep(0);
               this.this$0.win_up_img.setBackgroundResource(2131231283);
               if (AirData.wind_level != 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -110, 1, (byte)(AirData.wind_level + 1)});
               }

               return true;
            } else {
               return false;
            }
         }
      });
      this.win_down_img.setOnTouchListener(new View.OnTouchListener(this) {
         final AirView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            this.this$0.resetTimer();
            if (var2.getAction() == 0) {
               this.this$0.win_down_img.setBackgroundResource(2131231284);
               return true;
            } else if (var2.getAction() == 1) {
               SendKeyManager.getInstance().playBeep(0);
               this.this$0.win_down_img.setBackgroundResource(2131231283);
               if (AirData.wind_level != 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -110, 1, (byte)(AirData.wind_level - 1), 0});
               }

               return true;
            } else {
               return false;
            }
         }
      });
      this.temp_down.setOnTouchListener(new View.OnTouchListener(this) {
         final AirView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            this.this$0.resetTimer();
            if (var2.getAction() == 0) {
               this.this$0.temp_down.setBackgroundResource(2131232112);
               return true;
            } else if (var2.getAction() == 1) {
               SendKeyManager.getInstance().playBeep(0);
               this.this$0.temp_down.setBackgroundResource(2131232113);
               if (AirData.temp > 18) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -110, 3, (byte)(AirData.temp - 1), 0});
               }

               return true;
            } else {
               return false;
            }
         }
      });
      this.temp_up.setOnTouchListener(new View.OnTouchListener(this) {
         final AirView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            this.this$0.resetTimer();
            if (var2.getAction() == 0) {
               this.this$0.temp_up.setBackgroundResource(2131232114);
               return true;
            } else if (var2.getAction() == 1) {
               SendKeyManager.getInstance().playBeep(0);
               this.this$0.temp_up.setBackgroundResource(2131232115);
               if (AirData.temp < 32) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -110, 3, (byte)(AirData.temp + 1), 0});
               }

               return true;
            } else {
               return false;
            }
         }
      });
   }

   private void resetTimer() {
      AirTimer.getInstance().start(AirData.exitTime, new ActionCallback(this) {
         final AirView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (this.this$0.autoExitTag && this.this$0.actionCallback != null) {
               this.this$0.actionCallback.toDo("EXIT");
            }

         }
      });
   }

   public void getAction(ActionCallback var1) {
      this.actionCallback = var1;
   }

   public void infoChange() {
      if (this.autoExitTag) {
         this.updateUi();
      } else {
         Message var1 = new Message();
         var1.what = 255;
         this.mHandler.sendMessage(var1);
      }

   }

   public void initSetting() {
      this.air_setting_view.resetTxt();
      this.setting_view.setVisibility(8);
      if (SharePreUtil.getBoolValue(this.getContext(), "key.air.settings.show.tag。467", false)) {
         this.setting_icon.setVisibility(0);
      } else {
         this.setting_icon.setVisibility(8);
      }

   }

   public void onClick(View var1) {
      this.resetTimer();
      switch (var1.getId()) {
         case 2131361842:
            if (AirData.ac) {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 5, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 5, 1});
            }
            break;
         case 2131361928:
            if (AirData.auto) {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 6, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 6, 1});
            }
            break;
         case 2131362181:
            if (AirData.defog) {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 7, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 7, 1});
            }
            break;
         case 2131362219:
            ActionCallback var3 = this.actionCallback;
            if (var3 != null) {
               var3.toDo("EXIT");
            }
            break;
         case 2131362367:
            if (AirData.warm_level == 4) {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 9, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 9, (byte)(AirData.warm_level + 1)});
            }
            break;
         case 2131362500:
            if (AirData.in_out_cycle) {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 4, 1});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 4, 0});
            }
            break;
         case 2131362990:
            if (AirData.power) {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 8, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 8, 1});
            }
            break;
         case 2131363789:
            int var2 = this.modeTag;
            if (var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 2, 1});
               this.modeTag = 1;
            } else if (var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 2, 2});
               this.modeTag = 2;
            } else if (var2 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 2, 3});
               this.modeTag = 3;
            } else if (var2 == 3) {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 2, 4});
               this.modeTag = 4;
            } else if (var2 == 4) {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, 2, 5});
               this.modeTag = 0;
            }
      }

   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      AirBuilder.getInstance().register(this);
   }

   public void setAutoExit(boolean var1) {
      this.autoExitTag = var1;
   }

   public void updateUi() {
      if (AirData.temp == 18) {
         this.temp_txt.setText("LO");
      } else if (AirData.temp == 32) {
         this.temp_txt.setText("HI");
      } else {
         this.temp_txt.setText(AirData.temp + "℃");
      }

      if (AirData.wind_level == 0) {
         this.wind_level_img.setBackgroundResource(2131231276);
      } else if (AirData.wind_level == 1) {
         this.wind_level_img.setBackgroundResource(2131231277);
      } else if (AirData.wind_level == 2) {
         this.wind_level_img.setBackgroundResource(2131231278);
      } else if (AirData.wind_level == 3) {
         this.wind_level_img.setBackgroundResource(2131231279);
      } else if (AirData.wind_level == 4) {
         this.wind_level_img.setBackgroundResource(2131231280);
      }

      if (AirData.ac) {
         this.ac_img.setBackgroundResource(2131231232);
      } else {
         this.ac_img.setBackgroundResource(2131231231);
      }

      if (AirData.in_out_cycle) {
         this.in_out_cycle_img.setBackgroundResource(2131231252);
      } else {
         this.in_out_cycle_img.setBackgroundResource(2131231254);
      }

      if (AirData.auto) {
         this.auto_img.setBackgroundResource(2131231234);
      } else {
         this.auto_img.setBackgroundResource(2131231233);
      }

      if (AirData.defog) {
         this.defog_img.setBackgroundResource(2131231237);
      } else {
         this.defog_img.setBackgroundResource(2131231236);
      }

      if (AirData.heat) {
         this.heat_img.setVisibility(0);
      } else {
         this.heat_img.setVisibility(4);
      }

      if (AirData.warm_level == 0) {
         this.heat_img.setBackgroundResource(2131231238);
      } else if (AirData.warm_level == 1) {
         this.heat_img.setBackgroundResource(2131231482);
      } else if (AirData.warm_level == 2) {
         this.heat_img.setBackgroundResource(2131231489);
      } else if (AirData.warm_level == 3) {
         this.heat_img.setBackgroundResource(2131231484);
      } else if (AirData.warm_level == 4) {
         this.heat_img.setBackgroundResource(2131231486);
      }

      this.windModeView.setWindowWind(AirData.wind_window);
      this.windModeView.setBodyWind(AirData.wind_body);
      this.windModeView.setFootWind(AirData.wind_foot);
      if (AirData.power) {
         this.temp_txt.setBackgroundResource(2131231264);
         this.power_img.setBackgroundResource(2131231260);
      } else {
         this.wind_level_img.setBackgroundResource(2131231276);
         this.temp_txt.setBackgroundResource(2131231263);
         this.power_img.setBackgroundResource(2131231261);
         this.ac_img.setBackgroundResource(2131231231);
         this.auto_img.setBackgroundResource(2131231233);
         this.in_out_cycle_img.setBackgroundResource(2131231253);
         this.defog_img.setBackgroundResource(2131231236);
         this.heat_img.setBackgroundResource(2131231238);
         this.windModeView.setWindowWind(false);
         this.windModeView.setBodyWind(false);
         this.windModeView.setFootWind(false);
      }

      this.resetTimer();
   }
}
