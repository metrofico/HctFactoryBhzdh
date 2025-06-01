package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;
import com.hzbhd.canbus.car_cus._446.util.SendUtil;
import java.util.ArrayList;

public class Page3View extends LinearLayout implements CanInfoObserver {
   private CarLogoView logo_icon;
   private SelectionView selection1;
   private SwitchView switch1;
   private SwitchView switch2;
   private SwitchView switch3;
   private SwitchView switch4;
   private View view;

   public Page3View(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public Page3View(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public Page3View(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558595, this, true);
      this.view = var4;
      this.logo_icon = (CarLogoView)var4.findViewById(2131362820);
      this.switch1 = (SwitchView)this.view.findViewById(2131362194);
      this.switch2 = (SwitchView)this.view.findViewById(2131363145);
      this.switch3 = (SwitchView)this.view.findViewById(2131363000);
      this.switch4 = (SwitchView)this.view.findViewById(2131362173);
      ArrayList var5 = new ArrayList();
      var5.add("OFF");
      var5.add("30s");
      var5.add("60s");
      var5.add("90s");
      var5.add("120s");
      SelectionView var6 = (SelectionView)this.view.findViewById(2131361962);
      this.selection1 = var6;
      var6.initItem(var5);
      this.initAction();
   }

   private void initAction() {
      this.logo_icon.getAction(new ActionDo(this) {
         final Page3View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (var1.equals("TURN_ON")) {
               CanbusMsgSender.sendMsg(new byte[]{33, 3, 33, -64, -120, -14, -79, -32, -1, -1, -1});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{33, 3, 33, -64, -120, -14, -79, -32, -2, -1, -1});
            }

         }
      });
      this.switch1.getAction(new ActionDo(this) {
         final Page3View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (WmCarData.dingDengMenKong) {
               SendUtil.send(new byte[]{22, 0, 0, 3, 127, 0, 0, 0, 64, -16, 0, 0, 0});
            } else {
               SendUtil.send(new byte[]{22, 0, 0, 3, 127, 0, 0, 0, -128, -16, 0, 0, 0});
            }

         }
      });
      this.switch2.getAction(new ActionDo(this) {
         final Page3View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (WmCarData.riJianXingCheDeng) {
               SendUtil.send(new byte[]{22, 0, 0, 3, 127, 0, 0, 0, 0, 97, 0, 0, 0});
            } else {
               SendUtil.send(new byte[]{22, 0, 0, 3, 127, 0, 0, 0, 0, 98, 0, 0, 0});
            }

         }
      });
      this.selection1.getAction(new ActionDo(this) {
         final Page3View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            Integer var2 = (Integer)var1;
            if (var2 == 0) {
               SendUtil.send(new byte[]{22, 0, 0, 3, 127, 0, 0, 0, 0, 0, 0, 0, 0});
            } else if (var2 == 1) {
               SendUtil.send(new byte[]{22, 0, 0, 3, 127, 0, 0, 0, 0, 32, 0, 0, 0});
            } else if (var2 == 2) {
               SendUtil.send(new byte[]{22, 0, 0, 3, 127, 0, 0, 0, 0, 64, 0, 0, 0});
            } else if (var2 == 3) {
               SendUtil.send(new byte[]{22, 0, 0, 3, 127, 0, 0, 0, 0, 96, 0, 0, 0});
            } else if (var2 == 4) {
               SendUtil.send(new byte[]{22, 0, 0, 3, 127, 0, 0, 0, 0, -128, 0, 0, 0});
            }

         }
      });
      this.switch3.getAction(new ActionDo(this) {
         final Page3View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (WmCarData.qianZhuangShiDeng) {
               SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 16, 0, 0, 0, 0, 0, 106});
            } else {
               SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 32, 0, 0, 0, 0, 0, 106});
            }

         }
      });
      this.switch4.getAction(new ActionDo(this) {
         final Page3View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (WmCarData.daoCheTiShiYin) {
               SendUtil.send(new byte[]{22, 0, 0, 3, -115, 0, -64, 30, 16, 0, 20, 0, 0});
            } else {
               SendUtil.send(new byte[]{22, 0, 0, 3, -115, 0, -64, 30, 16, 0, 24, 0, 0});
            }

         }
      });
   }

   public void dataChange() {
      this.updateUi();
   }

   public void updateUi() {
      this.switch1.select(WmCarData.dingDengMenKong);
      this.switch2.select(WmCarData.riJianXingCheDeng);
      this.selection1.setValue(WmCarData.banWoHuiJiaDeng);
      this.switch3.select(WmCarData.qianZhuangShiDeng);
      this.switch4.select(WmCarData.daoCheTiShiYin);
      this.logo_icon.updateLogo();
   }
}
