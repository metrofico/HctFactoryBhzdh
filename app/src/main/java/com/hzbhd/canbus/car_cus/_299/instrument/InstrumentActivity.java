package com.hzbhd.canbus.car_cus._299.instrument;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralDriveData;
import java.util.List;

public class InstrumentActivity extends AbstractBaseActivity {
   private ImageView iv_bottom_left_open;
   private ImageView iv_bottom_open;
   private ImageView iv_bottom_right_open;
   private ImageView iv_hand;
   private TextView iv_rotate_speed;
   private ImageView iv_safety;
   private ImageView iv_top_left_open;
   private ImageView iv_top_open;
   private ImageView iv_top_right_open;
   private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new InstrumentActivity$$ExternalSyntheticLambda0(this);
   private ImageView pointerSpeed;
   private ImageView pointerSpeedRotate;
   private RadioGroup radioGroup;
   private TextView tv_hand;
   private TextView tv_out_temp;
   private TextView tv_safety;
   private TextView tv_speed;

   private void initView() {
      this.radioGroup = (RadioGroup)this.findViewById(2131363038);
      this.pointerSpeed = (ImageView)this.findViewById(2131362645);
      this.pointerSpeedRotate = (ImageView)this.findViewById(2131362632);
      this.iv_bottom_open = (ImageView)this.findViewById(2131362544);
      this.iv_top_open = (ImageView)this.findViewById(2131362655);
      this.iv_top_left_open = (ImageView)this.findViewById(2131362654);
      this.iv_bottom_left_open = (ImageView)this.findViewById(2131362543);
      this.iv_top_right_open = (ImageView)this.findViewById(2131362656);
      this.iv_bottom_right_open = (ImageView)this.findViewById(2131362545);
      this.iv_safety = (ImageView)this.findViewById(2131362633);
      this.iv_hand = (ImageView)this.findViewById(2131362569);
      this.tv_speed = (TextView)this.findViewById(2131363703);
      this.iv_rotate_speed = (TextView)this.findViewById(2131362631);
      this.tv_out_temp = (TextView)this.findViewById(2131363663);
      this.tv_safety = (TextView)this.findViewById(2131363688);
      this.tv_hand = (TextView)this.findViewById(2131363626);
      ((RadioButton)this.findViewById(2131363024)).setOnCheckedChangeListener(this.onCheckedChangeListener);
      ((RadioButton)this.findViewById(2131363025)).setOnCheckedChangeListener(this.onCheckedChangeListener);
      ((RadioButton)this.findViewById(2131363026)).setOnCheckedChangeListener(this.onCheckedChangeListener);
      ((RadioButton)this.findViewById(2131363027)).setOnCheckedChangeListener(this.onCheckedChangeListener);
      ((RadioButton)this.findViewById(2131363028)).setOnCheckedChangeListener(this.onCheckedChangeListener);
      this.radioGroup.setEnabled(false);
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__299_instrument_InstrumentActivity(CompoundButton var1, boolean var2) {
      int var3;
      Resources var4;
      if (var2) {
         var4 = this.getResources();
         var3 = 2131165202;
      } else {
         var4 = this.getResources();
         var3 = 2131165201;
      }

      var1.setTextSize(var4.getDimension(var3));
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558495);
      this.getWindow().setFlags(1024, 1024);
      this.initView();
   }

   public void refreshUi(Bundle var1) {
      ImageView var17 = this.iv_bottom_open;
      boolean var4 = GeneralDoorData.isBackOpen;
      byte var3 = 8;
      int var2;
      if (var4) {
         var2 = 0;
      } else {
         var2 = 8;
      }

      var17.setVisibility(var2);
      var17 = this.iv_top_open;
      if (GeneralDoorData.isFrontOpen) {
         var2 = 0;
      } else {
         var2 = 8;
      }

      var17.setVisibility(var2);
      var17 = this.iv_top_left_open;
      if (GeneralDoorData.isLeftFrontDoorOpen) {
         var2 = 0;
      } else {
         var2 = 8;
      }

      var17.setVisibility(var2);
      var17 = this.iv_bottom_left_open;
      if (GeneralDoorData.isLeftRearDoorOpen) {
         var2 = 0;
      } else {
         var2 = 8;
      }

      var17.setVisibility(var2);
      var17 = this.iv_top_right_open;
      if (GeneralDoorData.isRightFrontDoorOpen) {
         var2 = 0;
      } else {
         var2 = 8;
      }

      var17.setVisibility(var2);
      var17 = this.iv_bottom_right_open;
      var2 = var3;
      if (GeneralDoorData.isRightRearDoorOpen) {
         var2 = 0;
      }

      var17.setVisibility(var2);
      var17 = this.iv_hand;
      if (GeneralDoorData.isHandBrakeUp) {
         var2 = 2131232107;
      } else {
         var2 = 2131230887;
      }

      var17.setImageResource(var2);
      var17 = this.iv_safety;
      if (GeneralDoorData.isSeatBeltTie) {
         var2 = 2131232106;
      } else {
         var2 = 2131230874;
      }

      var17.setImageResource(var2);
      TextView var18 = this.tv_hand;
      if (GeneralDoorData.isHandBrakeUp) {
         var2 = 2131761308;
      } else {
         var2 = 2131761309;
      }

      var18.setText(this.getString(var2));
      var18 = this.tv_safety;
      if (GeneralDoorData.isSeatBeltTie) {
         var2 = 2131761310;
      } else {
         var2 = 2131761311;
      }

      var18.setText(this.getString(var2));
      List var19 = GeneralDriveData.dataList;
      this.tv_speed.setText(((DriverUpdateEntity)var19.get(0)).getValue());
      this.iv_rotate_speed.setText(((DriverUpdateEntity)var19.get(1)).getValue());
      this.tv_out_temp.setText(((DriverUpdateEntity)var19.get(2)).getValue());

      Exception var5;
      Exception var10000;
      boolean var10001;
      label159: {
         label158: {
            try {
               var2 = Integer.valueOf(((DriverUpdateEntity)var19.get(6)).getValue());
            } catch (Exception var16) {
               var10000 = var16;
               var10001 = false;
               break label158;
            }

            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 != 4) {
                           if (var2 != 5) {
                              break label159;
                           }

                           try {
                              this.radioGroup.check(2131363028);
                              break label159;
                           } catch (Exception var10) {
                              var10000 = var10;
                              var10001 = false;
                           }
                        } else {
                           try {
                              this.radioGroup.check(2131363027);
                              break label159;
                           } catch (Exception var11) {
                              var10000 = var11;
                              var10001 = false;
                           }
                        }
                     } else {
                        try {
                           this.radioGroup.check(2131363026);
                           break label159;
                        } catch (Exception var12) {
                           var10000 = var12;
                           var10001 = false;
                        }
                     }
                  } else {
                     try {
                        this.radioGroup.check(2131363025);
                        break label159;
                     } catch (Exception var13) {
                        var10000 = var13;
                        var10001 = false;
                     }
                  }
               } else {
                  try {
                     this.radioGroup.check(2131363024);
                     break label159;
                  } catch (Exception var14) {
                     var10000 = var14;
                     var10001 = false;
                  }
               }
            } else {
               try {
                  this.radioGroup.clearCheck();
                  break label159;
               } catch (Exception var15) {
                  var10000 = var15;
                  var10001 = false;
               }
            }
         }

         var5 = var10000;
         var5.printStackTrace();
      }

      label134: {
         label166: {
            try {
               var2 = Integer.valueOf(((DriverUpdateEntity)var19.get(0)).getValue());
            } catch (Exception var9) {
               var10000 = var9;
               var10001 = false;
               break label166;
            }

            if (var2 < 0 || var2 > 330) {
               break label134;
            }

            var2 = (int)((float)var2 * 0.8030303F);

            try {
               this.pointerSpeed.setRotation((float)var2);
               break label134;
            } catch (Exception var8) {
               var10000 = var8;
               var10001 = false;
            }
         }

         var5 = var10000;
         var5.printStackTrace();
      }

      label167: {
         try {
            var2 = Integer.valueOf(((DriverUpdateEntity)var19.get(1)).getValue());
         } catch (Exception var7) {
            var10000 = var7;
            var10001 = false;
            break label167;
         }

         if (var2 < 0 || var2 > 8000) {
            return;
         }

         var2 = (int)((float)var2 * 0.030375F);

         try {
            this.pointerSpeedRotate.setRotation((float)(-var2));
            return;
         } catch (Exception var6) {
            var10000 = var6;
            var10001 = false;
         }
      }

      Exception var20 = var10000;
      var20.printStackTrace();
   }
}
