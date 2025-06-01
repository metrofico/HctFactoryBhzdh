package com.hzbhd.canbus.car_cus._283.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car._283.MsgMgr;
import com.hzbhd.canbus.car_cus._283.DialogUtils;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.adapter.AirSelectAdapter;
import com.hzbhd.canbus.car_cus._283.bean.AirSelectBean;
import com.hzbhd.canbus.car_cus._283.view.AirTextView;
import com.hzbhd.canbus.car_cus._283.view.BtnView;
import com.hzbhd.canbus.car_cus._283.view.CenterControlView;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AirActivity extends AbstractBaseActivity implements View.OnClickListener {
   public static final String TEMP_INTERVAL = "_283_temp_interval";
   public static final int WHAT_CHANGE_SYNC = 1;
   public static final int WHAT_FINISH_THIS = 0;
   private BtnView btn_cleanair;
   private BtnView btn_off_on;
   private BtnView btn_rear;
   private BtnView btn_setting;
   private BtnView btn_sync;
   private BtnView btn_wheel;
   private AirTextView center_airTextView;
   private ExecutorService executorService = Executors.newSingleThreadExecutor();
   private ImageView front_down_wind;
   private int[] front_left_cold = new int[]{2131233256, 2131233257, 2131233258};
   private int[] front_left_hot = new int[]{2131233266, 2131233267, 2131233268};
   private ImageView front_left_seat;
   private AirTextView front_right_airTextView;
   private int[] front_right_cold = new int[]{2131233259, 2131233260, 2131233261};
   private int[] front_right_hot = new int[]{2131233269, 2131233270, 2131233271};
   private ImageView front_right_seat;
   private ImageView front_up_wind;
   private ImageView front_windows_wind;
   private ImageView iv_centerControl;
   private int left_front_hot;
   private int left_front_ventilate;
   private TextView left_view;
   private ImageView left_view_iv;
   private List listLeft = new ArrayList();
   private List listRight = new ArrayList();
   private CenterControlView mCenterControlView;
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final AirActivity this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         int var2 = var1.what;
         if (var2 != 0) {
            if (var2 == 1) {
               boolean var3 = SharePreUtil.getBoolValue(this.this$0, "_283_air_sync", false);
               this.this$0.sendMsg((byte)17, var3 ^ true);
               this.this$0.refreshUi((Bundle)null);
            }
         } else {
            this.this$0.finish();
         }

      }
   };
   private float maxC = 23.0F;
   private float maxF = 73.4F;
   private AirTextView rear_right_airTextView;
   private int[] rear_right_cold = new int[]{2131233262, 2131233263, 2131233264};
   private int[] rear_right_hot = new int[]{2131233272, 2131233273, 2131233274};
   private ImageView rear_right_seat;
   private ImageView rear_wind;
   private int right_front_hot;
   private int right_front_ventilate;
   private int right_rear_hot;
   private int right_rear_ventilate;
   private ImageView right_view;
   private float tempInterval = 0.5F;

   private void addTemp(byte var1, String var2) {
      this.setTimeOut();
      this.getTempInterval();

      Exception var10000;
      label98: {
         boolean var10001;
         try {
            if (var2.equals("HI")) {
               return;
            }
         } catch (Exception var15) {
            var10000 = var15;
            var10001 = false;
            break label98;
         }

         boolean var4;
         try {
            var4 = GeneralDzData.fahrenheit_celsius;
         } catch (Exception var14) {
            var10000 = var14;
            var10001 = false;
            break label98;
         }

         float var3;
         if (var4) {
            label99: {
               label71: {
                  label70: {
                     try {
                        if (!var2.equals("LO")) {
                           break label70;
                        }
                     } catch (Exception var9) {
                        var10000 = var9;
                        var10001 = false;
                        break label99;
                     }

                     var3 = 16.0F;
                     break label71;
                  }

                  try {
                     var3 = Float.valueOf(var2.replace(this.getString(2131770016), ""));
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                     break label99;
                  }
               }

               try {
                  var3 = (var3 + this.tempInterval) * 2.0F;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label99;
               }

               if (var3 >= 59.0F) {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, -1});
                     return;
                  } catch (Exception var5) {
                     var10000 = var5;
                     var10001 = false;
                  }
               } else {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)((int)var3)});
                     return;
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                  }
               }
            }
         } else {
            label86: {
               label85: {
                  label84: {
                     try {
                        if (var2.equals("LO")) {
                           break label84;
                        }
                     } catch (Exception var13) {
                        var10000 = var13;
                        var10001 = false;
                        break label86;
                     }

                     try {
                        var3 = Float.valueOf(var2.replace(this.getString(2131770017), ""));
                        break label85;
                     } catch (Exception var12) {
                        var10000 = var12;
                        var10001 = false;
                        break label86;
                     }
                  }

                  var3 = 61.0F;
               }

               var3 = var3 * 2.0F + 2.0F;
               if (var3 >= 170.0F) {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, -1});
                     return;
                  } catch (Exception var10) {
                     var10000 = var10;
                     var10001 = false;
                  }
               } else {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)((int)var3)});
                     return;
                  } catch (Exception var11) {
                     var10000 = var11;
                     var10001 = false;
                  }
               }
            }
         }
      }

      Exception var16 = var10000;
      var16.printStackTrace();
   }

   private void airPowerClose() {
      GeneralDzData.air_front_right_temp = "OFF";
      GeneralDzData.air_front_left_temp = "OFF";
      GeneralDzData.air_rear_temp = "OFF";
      GeneralDzData.sync = false;
      GeneralDzData.steering_wheel_heating = false;
      GeneralDzData.left_front_ventilate = 0;
      GeneralDzData.left_front_hot = 0;
      GeneralDzData.right_front_ventilate = 0;
      GeneralDzData.right_front_hot = 0;
      GeneralDzData.right_rear_hot = 0;
      GeneralDzData.air_front_wind = -1;
      GeneralDzData.air_rear_wind = -1;
      this.iv_centerControl.setEnabled(false);
      if (DialogUtils.getCenterControlPopupWindow() != null && DialogUtils.getCenterControlPopupWindow().isShowing()) {
         DialogUtils.getCenterControlPopupWindow().dismiss();
      }

   }

   private void airPowerOpen() {
      this.iv_centerControl.setEnabled(true);
   }

   private void getTempInterval() {
      try {
         this.tempInterval = Float.valueOf(SharePreUtil.getStringValue(this, "_283_temp_interval", "0.5"));
      } catch (Exception var2) {
         this.tempInterval = 0.5F;
         var2.printStackTrace();
      }

   }

   private void initView() {
      this.left_view_iv = (ImageView)this.findViewById(2131362750);
      this.iv_centerControl = (ImageView)this.findViewById(2131362554);
      this.front_left_seat = (ImageView)this.findViewById(2131362269);
      this.front_right_seat = (ImageView)this.findViewById(2131362290);
      this.rear_right_seat = (ImageView)this.findViewById(2131363068);
      this.front_windows_wind = (ImageView)this.findViewById(2131362294);
      this.front_up_wind = (ImageView)this.findViewById(2131362293);
      this.front_down_wind = (ImageView)this.findViewById(2131362267);
      this.rear_wind = (ImageView)this.findViewById(2131363071);
      this.front_right_airTextView = (AirTextView)this.findViewById(2131362289);
      this.center_airTextView = (AirTextView)this.findViewById(2131362131);
      this.rear_right_airTextView = (AirTextView)this.findViewById(2131363064);
      this.left_view = (TextView)this.findViewById(2131362749);
      this.right_view = (ImageView)this.findViewById(2131363178);
      this.btn_off_on = (BtnView)this.findViewById(2131362035);
      this.btn_sync = (BtnView)this.findViewById(2131362058);
      this.btn_rear = (BtnView)this.findViewById(2131362044);
      this.btn_cleanair = (BtnView)this.findViewById(2131362014);
      this.btn_wheel = (BtnView)this.findViewById(2131362065);
      this.btn_setting = (BtnView)this.findViewById(2131362054);
      this.iv_centerControl.setOnClickListener(this);
      this.left_view_iv.setOnClickListener(this);
      this.left_view.setOnClickListener(this);
      this.right_view.setOnClickListener(this);
      this.btn_off_on.setOnClickListener(this);
      this.btn_sync.setOnClickListener(this);
      this.btn_rear.setOnClickListener(this);
      this.btn_cleanair.setOnClickListener(this);
      this.btn_wheel.setOnClickListener(this);
      this.btn_setting.setOnClickListener(this);
      this.listLeft.add(new AirSelectBean(this.getString(2131760447), (Drawable)null));
      this.listLeft.add(new AirSelectBean(this.getString(2131760671), (Drawable)null));
      this.listLeft.add(new AirSelectBean((String)null, this.getDrawable(2131230814)));
      this.listLeft.add(new AirSelectBean(this.getString(2131760672), (Drawable)null));
      this.listRight.add(new AirSelectBean(this.getString(2131761090), this.getDrawable(2131230816)));
      this.listRight.add(new AirSelectBean(this.getString(2131761091), this.getDrawable(2131230817)));
      this.listRight.add(new AirSelectBean(this.getString(2131761089), this.getDrawable(2131230815)));
      this.center_airTextView.setOnClickListener(new AirTextView.OnClickListener(this) {
         final AirActivity this$0;

         {
            this.this$0 = var1;
         }

         public void add() {
            this.this$0.addTemp((byte)20, GeneralDzData.air_front_left_temp);
         }

         public void sub() {
            this.this$0.subTemp((byte)20, GeneralDzData.air_front_left_temp);
         }
      });
      this.front_right_airTextView.setOnClickListener(new AirTextView.OnClickListener(this) {
         final AirActivity this$0;

         {
            this.this$0 = var1;
         }

         public void add() {
            this.this$0.addTemp((byte)21, GeneralDzData.air_front_right_temp);
         }

         public void sub() {
            this.this$0.subTemp((byte)21, GeneralDzData.air_front_right_temp);
         }
      });
      this.rear_right_airTextView.setOnClickListener(new AirTextView.OnClickListener(this) {
         final AirActivity this$0;

         {
            this.this$0 = var1;
         }

         public void add() {
            this.this$0.addTemp((byte)22, GeneralDzData.air_rear_temp);
         }

         public void sub() {
            this.this$0.subTemp((byte)22, GeneralDzData.air_rear_temp);
         }
      });
   }

   // $FF: synthetic method
   static void lambda$onCreate$0() {
      try {
         Thread.sleep(1000L);
      } catch (InterruptedException var1) {
         var1.printStackTrace();
      }

      MessageSender.getMsg(49);
   }

   private void sendMsg(byte var1, boolean var2) {
      MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)(var2 ^ 1)});
   }

   private void setFrontWind(int var1) {
      if (var1 != -1) {
         if (var1 != 0 && var1 != 1 && var1 != 2) {
            if (var1 != 3) {
               if (var1 != 5) {
                  if (var1 != 6) {
                     switch (var1) {
                        case 11:
                           this.front_windows_wind.setVisibility(0);
                           this.front_up_wind.setVisibility(8);
                           this.front_down_wind.setVisibility(8);
                           GeneralDzData.fornt_left_blow_window = true;
                           GeneralDzData.fornt_left_blow_head = false;
                           GeneralDzData.fornt_left_blow_foot = false;
                           CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 0});
                           break;
                        case 12:
                           this.front_windows_wind.setVisibility(0);
                           this.front_up_wind.setVisibility(8);
                           this.front_down_wind.setVisibility(0);
                           GeneralDzData.fornt_left_blow_window = true;
                           GeneralDzData.fornt_left_blow_head = false;
                           GeneralDzData.fornt_left_blow_foot = true;
                           break;
                        case 13:
                           this.front_windows_wind.setVisibility(0);
                           this.front_up_wind.setVisibility(0);
                           this.front_down_wind.setVisibility(8);
                           GeneralDzData.fornt_left_blow_window = true;
                           GeneralDzData.fornt_left_blow_head = true;
                           GeneralDzData.fornt_left_blow_foot = false;
                           break;
                        case 14:
                           this.front_windows_wind.setVisibility(0);
                           this.front_up_wind.setVisibility(0);
                           this.front_down_wind.setVisibility(0);
                           GeneralDzData.fornt_left_blow_window = true;
                           GeneralDzData.fornt_left_blow_head = true;
                           GeneralDzData.fornt_left_blow_foot = true;
                     }
                  } else {
                     this.front_windows_wind.setVisibility(8);
                     this.front_up_wind.setVisibility(0);
                     this.front_down_wind.setVisibility(8);
                     GeneralDzData.fornt_left_blow_window = false;
                     GeneralDzData.fornt_left_blow_head = true;
                     GeneralDzData.fornt_left_blow_foot = false;
                  }
               } else {
                  this.front_windows_wind.setVisibility(8);
                  this.front_up_wind.setVisibility(0);
                  this.front_down_wind.setVisibility(0);
                  GeneralDzData.fornt_left_blow_window = false;
                  GeneralDzData.fornt_left_blow_head = true;
                  GeneralDzData.fornt_left_blow_foot = true;
               }
            } else {
               this.front_windows_wind.setVisibility(8);
               this.front_up_wind.setVisibility(8);
               this.front_down_wind.setVisibility(0);
               GeneralDzData.fornt_left_blow_window = false;
               GeneralDzData.fornt_left_blow_head = false;
               GeneralDzData.fornt_left_blow_foot = true;
            }
         } else {
            this.front_windows_wind.setVisibility(8);
            this.front_up_wind.setVisibility(8);
            this.front_down_wind.setVisibility(8);
            GeneralDzData.fornt_left_blow_window = false;
            GeneralDzData.fornt_left_blow_head = false;
            GeneralDzData.fornt_left_blow_foot = false;
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 0});
         }
      } else {
         this.front_windows_wind.setVisibility(8);
         this.front_up_wind.setVisibility(8);
         this.front_down_wind.setVisibility(8);
         GeneralDzData.fornt_left_blow_window = false;
         GeneralDzData.fornt_left_blow_head = false;
         GeneralDzData.fornt_left_blow_foot = false;
      }

   }

   private void setRearRightHot(int var1, ImageView var2, int[] var3) {
      if (var1 == 0) {
         var2.setImageDrawable((Drawable)null);
      } else if (var1 >= 1 && var1 <= 3) {
         var2.setImageDrawable(this.getDrawable(var3[var1 - 1]));
      }

   }

   private void setRearWind(int var1) {
      if (var1 != -1 && var1 != 0) {
         if (var1 != 3 && var1 != 5 && var1 != 6) {
            switch (var1) {
               case 12:
               case 13:
               case 14:
                  break;
               default:
                  return;
            }
         }

         this.rear_wind.setVisibility(0);
      } else {
         this.rear_wind.setVisibility(8);
      }

   }

   private void setSeat(int var1, ImageView var2, int[] var3) {
      if (var1 == 0) {
         var2.setImageDrawable((Drawable)null);
      } else if (var1 >= 1 && var1 <= 3) {
         var2.setImageDrawable(this.getDrawable(var3[var1 - 1]));
      }

   }

   private void setSelectLeft(int var1) {
      if (var1 == 2) {
         this.left_view_iv.setVisibility(0);
         this.left_view.setVisibility(4);
      } else {
         this.left_view_iv.setVisibility(4);
         this.left_view.setVisibility(0);
      }

      for(int var2 = 0; var2 < this.listLeft.size(); ++var2) {
         if (var2 == var1) {
            ((AirSelectBean)this.listLeft.get(var2)).setSelect(true);
         } else {
            ((AirSelectBean)this.listLeft.get(var2)).setSelect(false);
         }
      }

   }

   private void setSelectRight(int var1) {
      for(int var2 = 0; var2 < this.listRight.size(); ++var2) {
         if (var2 == var1) {
            ((AirSelectBean)this.listRight.get(var2)).setSelect(true);
         } else {
            ((AirSelectBean)this.listRight.get(var2)).setSelect(false);
         }
      }

   }

   private void setTimeOut() {
   }

   private void setWindColor() {
      Exception var10000;
      label88: {
         boolean var6;
         boolean var10001;
         try {
            var6 = GeneralDzData.air_front_left_temp.contains(this.getString(2131770017));
         } catch (Exception var18) {
            var10000 = var18;
            var10001 = false;
            break label88;
         }

         float var1;
         if (var6) {
            try {
               var1 = this.maxF;
            } catch (Exception var17) {
               var10000 = var17;
               var10001 = false;
               break label88;
            }
         } else {
            try {
               var1 = this.maxC;
            } catch (Exception var16) {
               var10000 = var16;
               var10001 = false;
               break label88;
            }
         }

         float var2;
         float var3;
         float var4;
         if (var6) {
            try {
               var3 = Float.valueOf(GeneralDzData.air_front_left_temp.replace(this.getString(2131770017), ""));
               var2 = Float.valueOf(GeneralDzData.air_front_right_temp.replace(this.getString(2131770017), ""));
               var4 = Float.valueOf(GeneralDzData.air_rear_temp.replace(this.getString(2131770017), ""));
            } catch (Exception var15) {
               var10000 = var15;
               var10001 = false;
               break label88;
            }
         } else {
            try {
               var3 = Float.valueOf(GeneralDzData.air_front_left_temp.replace(this.getString(2131770016), ""));
               var2 = Float.valueOf(GeneralDzData.air_front_right_temp.replace(this.getString(2131770016), ""));
               var4 = Float.valueOf(GeneralDzData.air_rear_temp.replace(this.getString(2131770016), ""));
            } catch (Exception var14) {
               var10000 = var14;
               var10001 = false;
               break label88;
            }
         }

         float var19;
         int var5 = (var19 = var3 - var1) == 0.0F ? 0 : (var19 < 0.0F ? -1 : 1);
         if (var5 >= 0 && var2 >= var1) {
            try {
               this.front_windows_wind.setImageResource(2131230838);
               this.front_up_wind.setImageResource(2131230841);
               this.front_down_wind.setImageResource(2131230835);
            } catch (Exception var13) {
               var10000 = var13;
               var10001 = false;
               break label88;
            }
         } else if (var5 >= 0) {
            try {
               this.front_windows_wind.setImageResource(2131230839);
               this.front_up_wind.setImageResource(2131230842);
               this.front_down_wind.setImageResource(2131230836);
            } catch (Exception var12) {
               var10000 = var12;
               var10001 = false;
               break label88;
            }
         } else if (var2 >= var1) {
            try {
               this.front_windows_wind.setImageResource(2131230840);
               this.front_up_wind.setImageResource(2131230843);
               this.front_down_wind.setImageResource(2131230837);
            } catch (Exception var11) {
               var10000 = var11;
               var10001 = false;
               break label88;
            }
         } else {
            try {
               this.front_windows_wind.setImageResource(2131232957);
               this.front_up_wind.setImageResource(2131232958);
               this.front_down_wind.setImageResource(2131232956);
            } catch (Exception var10) {
               var10000 = var10;
               var10001 = false;
               break label88;
            }
         }

         if (var4 >= var1) {
            try {
               this.rear_wind.setImageResource(2131230844);
               return;
            } catch (Exception var8) {
               var10000 = var8;
               var10001 = false;
            }
         } else {
            try {
               this.rear_wind.setImageResource(2131232959);
               return;
            } catch (Exception var9) {
               var10000 = var9;
               var10001 = false;
            }
         }
      }

      Exception var7 = var10000;
      var7.printStackTrace();
   }

   private void subTemp(byte var1, String var2) {
      this.setTimeOut();
      this.getTempInterval();

      Exception var10000;
      label98: {
         boolean var10001;
         try {
            if (var2.equals("LO")) {
               return;
            }
         } catch (Exception var15) {
            var10000 = var15;
            var10001 = false;
            break label98;
         }

         boolean var4;
         try {
            var4 = GeneralDzData.fahrenheit_celsius;
         } catch (Exception var14) {
            var10000 = var14;
            var10001 = false;
            break label98;
         }

         float var3;
         if (var4) {
            label99: {
               label71: {
                  label70: {
                     try {
                        if (!var2.equals("HI")) {
                           break label70;
                        }
                     } catch (Exception var9) {
                        var10000 = var9;
                        var10001 = false;
                        break label99;
                     }

                     var3 = 29.5F;
                     break label71;
                  }

                  try {
                     var3 = Float.valueOf(var2.replace(this.getString(2131770016), ""));
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                     break label99;
                  }
               }

               try {
                  var3 = (var3 - this.tempInterval) * 2.0F;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label99;
               }

               if (var3 <= 32.0F) {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, -2});
                     return;
                  } catch (Exception var5) {
                     var10000 = var5;
                     var10001 = false;
                  }
               } else {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)((int)var3)});
                     return;
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                  }
               }
            }
         } else {
            label86: {
               label85: {
                  label84: {
                     try {
                        if (var2.equals("HI")) {
                           break label84;
                        }
                     } catch (Exception var13) {
                        var10000 = var13;
                        var10001 = false;
                        break label86;
                     }

                     try {
                        var3 = Float.valueOf(var2.replace(this.getString(2131770017), ""));
                        break label85;
                     } catch (Exception var12) {
                        var10000 = var12;
                        var10001 = false;
                        break label86;
                     }
                  }

                  var3 = 85.0F;
               }

               var3 = var3 * 2.0F - 2.0F;
               if (var3 <= 122.0F) {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, -2});
                     return;
                  } catch (Exception var10) {
                     var10000 = var10;
                     var10001 = false;
                  }
               } else {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)((int)var3)});
                     return;
                  } catch (Exception var11) {
                     var10000 = var11;
                     var10001 = false;
                  }
               }
            }
         }
      }

      Exception var16 = var10000;
      var16.printStackTrace();
   }

   public void onClick(View var1) {
      this.setTimeOut();
      switch (var1.getId()) {
         case 2131362014:
            this.startActivity(new Intent(this, AirCleanActivity.class));
            break;
         case 2131362035:
            this.sendMsg((byte)2, GeneralDzData.air_power);
            break;
         case 2131362044:
            this.sendMsg((byte)18, GeneralDzData.rear_lock);
            break;
         case 2131362054:
            this.startActivity(new Intent(this, AirSettingActivity.class));
            break;
         case 2131362058:
            this.mHandler.removeMessages(1);
            this.sendMsg((byte)17, GeneralDzData.sync);
            break;
         case 2131362065:
            this.sendMsg((byte)35, GeneralDzData.steering_wheel_heating);
            break;
         case 2131362554:
            this.mCenterControlView = DialogUtils.showCenterControl(this, this.iv_centerControl);
            break;
         case 2131362749:
         case 2131362750:
            DialogUtils.showPopupwindows(this, this.left_view, this.listLeft, "", new AirSelectAdapter.OnItemClickListener(this) {
               final AirActivity this$0;

               {
                  this.this$0 = var1;
               }

               public void click(int var1) {
                  if (var1 != 0) {
                     if (var1 != 1) {
                        if (var1 != 2) {
                           if (var1 == 3) {
                              MessageSender.sendMsg(new byte[]{22, 58, -127, 3});
                           }
                        } else {
                           MessageSender.sendMsg(new byte[]{22, 58, -127, 2});
                        }
                     } else {
                        MessageSender.sendMsg(new byte[]{22, 58, -127, 1});
                     }
                  } else {
                     MessageSender.sendMsg(new byte[]{22, 58, -127, 0});
                  }

               }
            });
            break;
         case 2131363178:
            DialogUtils.showPopupwindows(this, this.right_view, this.listRight, this.getString(2131760445), new AirSelectAdapter.OnItemClickListener(this) {
               final AirActivity this$0;

               {
                  this.this$0 = var1;
               }

               public void click(int var1) {
                  if (var1 != 0) {
                     if (var1 != 1) {
                        if (var1 == 2) {
                           MessageSender.sendMsg(new byte[]{22, 58, 1, 2});
                        }
                     } else {
                        MessageSender.sendMsg(new byte[]{22, 58, 1, 1});
                     }
                  } else {
                     MessageSender.sendMsg(new byte[]{22, 58, 1, 0});
                  }

               }
            });
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558427);
      this.initView();
      CanbusMsgSender.sendMsg(new byte[]{22, 58, -16, 0});
      this.mHandler.sendEmptyMessage(1);
      this.mHandler.sendEmptyMessageDelayed(1, 1000L);
      MessageSender.getMsg(49);
      this.refreshUi((Bundle)null);
      (new Thread(new AirActivity$$ExternalSyntheticLambda0())).start();
      this.setTimeOut();
   }

   protected void onDestroy() {
      ((MsgMgr)MsgMgrFactory.getCanMsgMgr(this)).clearCanbusAirInfoCopy();
      super.onDestroy();
      CanbusMsgSender.sendMsg(new byte[]{22, 58, -16});
      CanbusMsgSender.sendMsg(new byte[]{22, 58, -16, 0});
   }

   public void refreshUi(Bundle var1) {
      if (GeneralDzData.auto) {
         this.left_view.setText(((AirSelectBean)this.listLeft.get(0)).getText());
         this.setSelectLeft(0);
      }

      if (GeneralDzData.max_ac) {
         this.left_view.setText(((AirSelectBean)this.listLeft.get(1)).getText());
         this.setSelectLeft(1);
      }

      if (GeneralDzData.front_defog == 2) {
         this.left_view_iv.setImageDrawable(((AirSelectBean)this.listLeft.get(2)).getDrawable());
         this.setSelectLeft(2);
      }

      if (!GeneralDzData.auto && !GeneralDzData.max_ac && GeneralDzData.front_defog != 2) {
         this.left_view.setText(((AirSelectBean)this.listLeft.get(3)).getText());
         this.setSelectLeft(3);
      }

      if (GeneralDzData.auto_wind_power >= 0 && GeneralDzData.auto_wind_power <= 2) {
         this.right_view.setImageDrawable(((AirSelectBean)this.listRight.get(GeneralDzData.auto_wind_power)).getDrawable());
         this.setSelectRight(GeneralDzData.auto_wind_power);
      }

      if (GeneralDzData.air_power) {
         this.btn_off_on.setIcon(2131231380, 2131231379);
         this.airPowerOpen();
      } else {
         this.btn_off_on.setIcon(2131231378, 2131231377);
         this.airPowerClose();
      }

      this.btn_sync.setSelect(GeneralDzData.sync);
      this.btn_wheel.setSelect(GeneralDzData.steering_wheel_heating);
      if (GeneralDzData.rear_lock) {
         this.btn_rear.setIcon(2131231384, 2131231383);
      } else {
         this.btn_rear.setIcon(2131231382, 2131231381);
      }

      this.front_right_airTextView.setText(GeneralDzData.air_front_right_temp);
      this.center_airTextView.setText(GeneralDzData.air_front_left_temp);
      this.rear_right_airTextView.setText(GeneralDzData.air_rear_temp);
      this.setFrontWind(GeneralDzData.air_front_wind);
      this.setRearWind(GeneralDzData.air_rear_wind);
      if (this.left_front_ventilate != GeneralDzData.left_front_ventilate) {
         this.setSeat(GeneralDzData.left_front_ventilate, this.front_left_seat, this.front_left_cold);
      } else if (this.left_front_hot != GeneralDzData.left_front_hot) {
         this.setSeat(GeneralDzData.left_front_hot, this.front_left_seat, this.front_left_hot);
      }

      if (this.right_front_ventilate != GeneralDzData.right_front_ventilate) {
         this.setSeat(GeneralDzData.right_front_ventilate, this.front_right_seat, this.front_right_cold);
      } else if (this.right_front_hot != GeneralDzData.right_front_hot) {
         this.setSeat(GeneralDzData.right_front_hot, this.front_right_seat, this.front_right_hot);
      }

      this.setRearRightHot(GeneralDzData.right_rear_hot, this.rear_right_seat, this.rear_right_hot);
      this.right_front_ventilate = GeneralDzData.right_front_ventilate;
      this.left_front_ventilate = GeneralDzData.left_front_ventilate;
      this.right_front_hot = GeneralDzData.right_front_hot;
      this.left_front_hot = GeneralDzData.left_front_hot;
      CenterControlView var2 = this.mCenterControlView;
      if (var2 != null) {
         var2.refreshUi();
      }

      this.setWindColor();
   }
}
