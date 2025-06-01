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
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;

public class Page1View extends LinearLayout implements CanInfoObserver {
   private Context context;
   private SelectionView selection1;
   private SelectionView selection2;
   private SelectionView selection3;
   private SwitchView switch1;
   private SwitchView switch2;
   private SwitchView switch3;
   private View view;

   public Page1View(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public Page1View(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public Page1View(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.context = var1;
      View var6 = LayoutInflater.from(var1).inflate(2131558593, this, true);
      this.view = var6;
      this.switch1 = (SwitchView)var6.findViewById(2131362204);
      this.switch2 = (SwitchView)this.view.findViewById(2131362172);
      this.switch3 = (SwitchView)this.view.findViewById(2131363808);
      ArrayList var7 = new ArrayList();
      var7.add(var1.getString(2131766079));
      var7.add(var1.getString(2131766082));
      SelectionView var4 = (SelectionView)this.view.findViewById(2131362228);
      this.selection1 = var4;
      var4.initItem(var7);
      var7 = new ArrayList();
      var7.add(var1.getString(2131766046));
      var7.add(var1.getString(2131766047));
      var7.add(var1.getString(2131766058));
      SelectionView var5 = (SelectionView)this.view.findViewById(2131362135);
      this.selection2 = var5;
      var5.initItem(var7);
      this.initAction();
      this.updateUi();
   }

   private void initAction() {
      this.switch1.getAction(new ActionDo(this) {
         final Page1View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (WmCarData.douPoHuanJiang) {
               SharePreUtil.setBoolValue(this.this$0.context, "KEY_DPHJ_SWITCH_STATE", false);
               CanbusMsgSender.sendMsg(new byte[]{22, -52, -52, -52, -52, 0, 0, 0, 0, 0, 0, 0, 0, 1});
            } else {
               SharePreUtil.setBoolValue(this.this$0.context, "KEY_DPHJ_SWITCH_STATE", true);
               CanbusMsgSender.sendMsg(new byte[]{22, -52, -52, -52, -52, 1, 0, 0, 0, 0, 0, 0, 0, 1});
            }

         }
      });
      this.switch2.getAction(new ActionDo(this) {
         final Page1View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (WmCarData.daiSuHuanXing) {
               SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 2, 0, 0, 0, 0, 0, -62});
            } else {
               SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 1, 0, 0, 0, 0, 0, 62});
            }

         }
      });
      this.switch3.getAction(new ActionDo(this) {
         final Page1View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (WmCarData.ziDongZhuChe) {
               SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 8, 0, 0, 0, 0, 0, -108});
            } else {
               SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 4, 0, 0, 0, 0, 0, 21});
            }

         }
      });
      this.selection1.getAction(new ActionDo(this) {
         final Page1View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            Integer var2 = (Integer)var1;
            if (var2 == 0) {
               SendUtil.send(new byte[]{22, 0, 0, 3, 118, 1, -64, 0, 0, -1, 0, 0, 32});
               SendUtil.send(new byte[]{22, 0, 0, 3, 118, 1, -64, 0, 0, -1, 16, 0, 32});
               SendUtil.send(new byte[]{22, 0, 0, 3, 118, 1, -64, 0, 0, -1, 0, 0, 32});
            } else if (var2 == 1) {
               SendUtil.send(new byte[]{22, 0, 0, 3, 118, 1, -64, 0, 0, -1, 0, 0, 32});
               SendUtil.send(new byte[]{22, 0, 0, 3, 118, 1, -64, 0, 0, -1, 48, 0, 32});
               SendUtil.send(new byte[]{22, 0, 0, 3, 118, 1, -64, 0, 0, -1, 0, 0, 32});
            }

         }
      });
      this.selection2.getAction(new ActionDo(this) {
         final Page1View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            Integer var2 = (Integer)var1;
            if (var2 == 0) {
               SendUtil.send(new byte[]{22, 0, 0, 1, 110, 64, 0, 0, 0, 0, 0, 0, 115});
            } else if (var2 == 1) {
               SendUtil.send(new byte[]{22, 0, 0, 1, 110, -128, 0, 0, 0, 0, 0, 0, 88});
            } else if (var2 == 2) {
               SendUtil.send(new byte[]{22, 0, 0, 1, 110, -64, 0, 0, 0, 0, 0, 0, 65});
            }

         }
      });
   }

   public void dataChange() {
      this.updateUi();
   }

   public void updateUi() {
      this.switch1.select(WmCarData.douPoHuanJiang);
      this.switch2.select(WmCarData.daiSuHuanXing);
      this.switch3.select(WmCarData.ziDongZhuChe);
      this.selection1.setValue(WmCarData.zhuanXiangLiDu);
      this.selection2.setValue(WmCarData.EspWengDing);
   }
}
