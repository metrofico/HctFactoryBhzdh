package com.hzbhd.canbus.car_cus._467.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._467.smartPanel.NotifyPanel;
import com.hzbhd.canbus.car_cus._467.smartPanel.PanelState;
import com.hzbhd.canbus.car_cus._467.smartPanel.PanelView;
import com.hzbhd.canbus.car_cus._467.smartPanel.RedoUtil;
import com.hzbhd.canbus.interfaces.ActionDo;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class ID439PanelActivity extends Activity implements View.OnClickListener {
   private ActionListener actionListener;
   private RedoUtil addFun1RedoUtil;
   private PanelView p1;
   private PanelView p10;
   private PanelView p11;
   private RedoUtil p11RedoUtil;
   private PanelView p12;
   private RedoUtil p12RedoUtil;
   private PanelView p13;
   private RedoUtil p13RedoUtil;
   private PanelView p14;
   private PanelView p15;
   private PanelView p16;
   private PanelView p17;
   private PanelView p18;
   private RedoUtil p18RedoUtil;
   private PanelView p2;
   private PanelView p3;
   private PanelView p4;
   private PanelView p5;
   private RedoUtil p5RedoUtil;
   private PanelView p6;
   private RedoUtil p6RedoUtil;
   private PanelView p7;
   private RedoUtil p7RedoUtil;
   private PanelView p8;
   private PanelView p9;
   private PanelView pAbs;
   private PanelView pUltraVires;
   private PanelView retarder;

   private void button1() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 1, (byte)(PanelState.sp1 ^ 1)});
   }

   private void button10() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 11, (byte)(PanelState.sp10 ^ 1)});
   }

   private void button14() {
      if (PanelState.sp14 == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 15, 2});
      } else if (PanelState.sp14 == 2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 15, 3});
      } else if (PanelState.sp14 == 3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 15, 4});
      } else if (PanelState.sp14 == 4) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 15, 1});
      }

   }

   private void button15() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, (byte)(PanelState.sp15 ^ 1)});
   }

   private void button16() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 16, (byte)(PanelState.sp16 ^ 1)});
   }

   private void button17() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 17, (byte)(PanelState.sp17 ^ 1)});
   }

   private void button2() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 2, (byte)(PanelState.sp2 ^ 1)});
   }

   private void button3() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 3, (byte)(PanelState.sp3 ^ 1)});
   }

   private void button4() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 4, (byte)(PanelState.sp4 ^ 1)});
   }

   private void button8() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 13, (byte)(PanelState.sp8 ^ 1)});
   }

   private void button9() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 14, (byte)(PanelState.sp9 ^ 1)});
   }

   private void buttonAbs() {
      if (PanelState.abs) {
         boolean var1 = PanelState.ultraVires;
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 20, (byte)0});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 20, (byte)(PanelState.ultraVires ^ 1)});
      }

   }

   private void buttonRetarder() {
      if (PanelState.retarder) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 19, 0});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 19, 1});
      }

   }

   private int get1024x600CofigLayout() {
      if (PanelState.carConfig == 1) {
         return 2131558678;
      } else if (PanelState.carConfig == 2) {
         return 2131558677;
      } else if (PanelState.carConfig == 3) {
         return 2131558681;
      } else if (PanelState.carConfig == 4) {
         return 2131558680;
      } else {
         return PanelState.carConfig == 5 ? 2131558679 : 2131558676;
      }
   }

   private void initView() {
      this.p1 = (PanelView)this.findViewById(2131362934);
      this.p2 = (PanelView)this.findViewById(2131362944);
      this.p3 = (PanelView)this.findViewById(2131362945);
      this.p4 = (PanelView)this.findViewById(2131362946);
      this.p5 = (PanelView)this.findViewById(2131362947);
      this.p6 = (PanelView)this.findViewById(2131362948);
      this.p7 = (PanelView)this.findViewById(2131362949);
      this.p8 = (PanelView)this.findViewById(2131362950);
      this.p9 = (PanelView)this.findViewById(2131362951);
      this.p10 = (PanelView)this.findViewById(2131362935);
      this.p11 = (PanelView)this.findViewById(2131362936);
      this.p12 = (PanelView)this.findViewById(2131362937);
      this.p13 = (PanelView)this.findViewById(2131362938);
      this.p14 = (PanelView)this.findViewById(2131362939);
      this.p15 = (PanelView)this.findViewById(2131362940);
      this.p16 = (PanelView)this.findViewById(2131362941);
      this.p17 = (PanelView)this.findViewById(2131362942);
      this.p18 = (PanelView)this.findViewById(2131362943);
      this.pUltraVires = (PanelView)this.findViewById(2131362954);
      this.pAbs = (PanelView)this.findViewById(2131362952);
      this.retarder = (PanelView)this.findViewById(2131363100);
      this.p1.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p2.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p3.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p4.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p5.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p8.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p9.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p10.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p11.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p12.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p13.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p14.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p15.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p16.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p17.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p18.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.pAbs.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.retarder.setOnClickListener(new ID439PanelActivity$$ExternalSyntheticLambda0(this));
      this.p6.setOnTouchListener(new View.OnTouchListener(this) {
         final ID439PanelActivity this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.startP6Timer();
               return true;
            } else if (var2.getAction() == 1) {
               this.this$0.stopP6Timer();
               return true;
            } else {
               return false;
            }
         }
      });
      this.p7.setOnTouchListener(new View.OnTouchListener(this) {
         final ID439PanelActivity this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.startP7Timer();
               return true;
            } else if (var2.getAction() == 1) {
               this.this$0.stopP7Timer();
               return true;
            } else {
               return false;
            }
         }
      });
      this.p11.setOnTouchListener(new View.OnTouchListener(this) {
         final ID439PanelActivity this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.startP11Timer();
               return true;
            } else if (var2.getAction() == 1) {
               this.this$0.stopP11Timer();
               return true;
            } else {
               return false;
            }
         }
      });
      this.p12.setOnTouchListener(new View.OnTouchListener(this) {
         final ID439PanelActivity this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.startP12Timer();
               return true;
            } else if (var2.getAction() == 1) {
               this.this$0.stopP12Timer();
               return true;
            } else {
               return false;
            }
         }
      });
      this.p18.setOnTouchListener(new View.OnTouchListener(this) {
         final ID439PanelActivity this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               if (LogUtil.log5()) {
                  LogUtil.d("onTouch(): +++++++");
               }

               this.this$0.startP18Timer();
               return true;
            } else if (var2.getAction() == 1) {
               if (LogUtil.log5()) {
                  LogUtil.d("onTouch():--------");
               }

               this.this$0.stopP18Timer();
               return true;
            } else {
               return false;
            }
         }
      });
      this.p13.setOnTouchListener(new View.OnTouchListener(this) {
         final ID439PanelActivity this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.startP13Timer();
               return true;
            } else if (var2.getAction() == 1) {
               this.this$0.stopP13Timer();
               return true;
            } else {
               return false;
            }
         }
      });
      this.p5.setOnTouchListener(new View.OnTouchListener(this) {
         final ID439PanelActivity this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.startP5Timer();
               return true;
            } else if (var2.getAction() == 1) {
               this.this$0.stopP5Timer();
               return true;
            } else {
               return false;
            }
         }
      });
      this.pUltraVires.setOnTouchListener(new View.OnTouchListener(this) {
         final ID439PanelActivity this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.startAddFunP1Timer();
               return true;
            } else if (var2.getAction() == 1) {
               this.this$0.stopAddFunP1Timer();
               return true;
            } else {
               return false;
            }
         }
      });
      this.refreshUi();
      this.actionListener = new ActionListener(this);
      NotifyPanel.getInstance().register(this.actionListener);
   }

   private void refreshUi() {
      this.p1.setSelect(this, PanelState.sp1);
      this.p2.setSelect(this, PanelState.sp2);
      this.p3.setSelect(this, PanelState.sp3);
      this.p4.setSelect(this, PanelState.sp4);
      this.p5.setSelect(this, PanelState.sp5);
      this.p6.setSelect(this, PanelState.sp6);
      this.p7.setSelect(this, PanelState.sp7);
      this.p8.setSelect(this, PanelState.sp8);
      this.p9.setSelect(this, PanelState.sp9);
      this.p10.setSelect(this, PanelState.sp10);
      this.p11.setSelect(this, PanelState.sp11);
      this.p12.setSelect(this, PanelState.sp12);
      this.p13.setSelect(this, PanelState.sp13);
      if (PanelState.sp14 == 1) {
         this.p14.setImg(ContextCompat.getDrawable(this, 2131232191));
      } else if (PanelState.sp14 == 2) {
         this.p14.setImg(ContextCompat.getDrawable(this, 2131232190));
      } else if (PanelState.sp14 == 3) {
         this.p14.setImg(ContextCompat.getDrawable(this, 2131232189));
      } else if (PanelState.sp14 == 4) {
         this.p14.setImg(ContextCompat.getDrawable(this, 2131232188));
      }

      this.p15.setSelect(this, PanelState.sp15);
      this.p16.setSelect(this, PanelState.sp16);
      this.p17.setSelect(this, PanelState.sp17);
      this.p18.setSelect(this, PanelState.sp18);
      this.pUltraVires.setSelect(this, PanelState.ultraVires);
      this.pAbs.setSelect(this, PanelState.abs);
      this.retarder.setSelect(this, PanelState.retarder);
   }

   private void startAddFunP1Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 18, 1});
      if (this.addFun1RedoUtil == null) {
         this.addFun1RedoUtil = new RedoUtil(new ActionDo(this) {
            final ID439PanelActivity this$0;

            {
               this.this$0 = var1;
            }

            public void todo(List var1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -111, 18, 1});
               if (this.this$0.addFun1RedoUtil != null) {
                  this.this$0.addFun1RedoUtil.startTimer(200L);
               }

            }
         });
      }

      this.addFun1RedoUtil.startTimer(200L);
   }

   private void startP11Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 7, 1});
      if (this.p11RedoUtil == null) {
         this.p11RedoUtil = new RedoUtil(new ActionDo(this) {
            final ID439PanelActivity this$0;

            {
               this.this$0 = var1;
            }

            public void todo(List var1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -111, 7, 1});
               if (this.this$0.p11RedoUtil != null) {
                  this.this$0.p11RedoUtil.startTimer(200L);
               }

            }
         });
      }

      this.p11RedoUtil.startTimer(200L);
   }

   private void startP12Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 8, 1});
      if (this.p12RedoUtil == null) {
         this.p12RedoUtil = new RedoUtil(new ActionDo(this) {
            final ID439PanelActivity this$0;

            {
               this.this$0 = var1;
            }

            public void todo(List var1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -111, 8, 1});
               if (this.this$0.p12RedoUtil != null) {
                  this.this$0.p12RedoUtil.startTimer(200L);
               }

            }
         });
      }

      this.p12RedoUtil.startTimer(200L);
   }

   private void startP13Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 9, 1});
      if (this.p13RedoUtil == null) {
         this.p13RedoUtil = new RedoUtil(new ActionDo(this) {
            final ID439PanelActivity this$0;

            {
               this.this$0 = var1;
            }

            public void todo(List var1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -111, 9, 1});
               if (this.this$0.p13RedoUtil != null) {
                  this.this$0.p13RedoUtil.startTimer(200L);
               }

            }
         });
      }

      this.p13RedoUtil.startTimer(200L);
   }

   private void startP18Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 21, 1});
      if (this.p18RedoUtil == null) {
         this.p18RedoUtil = new RedoUtil(new ActionDo(this) {
            final ID439PanelActivity this$0;

            {
               this.this$0 = var1;
            }

            public void todo(List var1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -111, 21, 1});
               if (this.this$0.p18RedoUtil != null) {
                  this.this$0.p18RedoUtil.startTimer(200L);
               }

            }
         });
      }

      this.p18RedoUtil.startTimer(200L);
   }

   private void startP5Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 12, 1});
      if (this.p5RedoUtil == null) {
         this.p5RedoUtil = new RedoUtil(new ActionDo(this) {
            final ID439PanelActivity this$0;

            {
               this.this$0 = var1;
            }

            public void todo(List var1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -111, 12, 1});
               if (this.this$0.p5RedoUtil != null) {
                  this.this$0.p5RedoUtil.startTimer(200L);
               }

            }
         });
      }

      this.p5RedoUtil.startTimer(200L);
   }

   private void startP6Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 5, 1});
      if (this.p6RedoUtil == null) {
         this.p6RedoUtil = new RedoUtil(new ActionDo(this) {
            final ID439PanelActivity this$0;

            {
               this.this$0 = var1;
            }

            public void todo(List var1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -111, 5, 1});
               if (this.this$0.p6RedoUtil != null) {
                  this.this$0.p6RedoUtil.startTimer(200L);
               }

            }
         });
      }

      this.p6RedoUtil.startTimer(200L);
   }

   private void startP7Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 6, 1});
      if (this.p7RedoUtil == null) {
         this.p7RedoUtil = new RedoUtil(new ActionDo(this) {
            final ID439PanelActivity this$0;

            {
               this.this$0 = var1;
            }

            public void todo(List var1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -111, 6, 1});
               if (this.this$0.p7RedoUtil != null) {
                  this.this$0.p7RedoUtil.startTimer(200L);
               }

            }
         });
      }

      this.p7RedoUtil.startTimer(200L);
   }

   private void stopAddFunP1Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 18, 0});
      RedoUtil var1 = this.addFun1RedoUtil;
      if (var1 != null) {
         var1.stopTimer();
      }

   }

   private void stopP11Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 7, 0});
      RedoUtil var1 = this.p11RedoUtil;
      if (var1 != null) {
         var1.stopTimer();
      }

   }

   private void stopP12Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 8, 0});
      RedoUtil var1 = this.p12RedoUtil;
      if (var1 != null) {
         var1.stopTimer();
      }

   }

   private void stopP13Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 9, 0});
      RedoUtil var1 = this.p13RedoUtil;
      if (var1 != null) {
         var1.stopTimer();
      }

   }

   private void stopP18Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 21, 0});
      RedoUtil var1 = this.p18RedoUtil;
      if (var1 != null) {
         var1.stopTimer();
      }

   }

   private void stopP5Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 12, 0});
      RedoUtil var1 = this.p5RedoUtil;
      if (var1 != null) {
         var1.stopTimer();
      }

   }

   private void stopP6Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 5, 0});
      RedoUtil var1 = this.p6RedoUtil;
      if (var1 != null) {
         var1.stopTimer();
      }

   }

   private void stopP7Timer() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 6, 0});
      RedoUtil var1 = this.p7RedoUtil;
      if (var1 != null) {
         var1.stopTimer();
      }

   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131362934:
            this.button1();
            break;
         case 2131362935:
            this.button10();
            break;
         case 2131362939:
            this.button14();
            break;
         case 2131362940:
            this.button15();
            break;
         case 2131362941:
            this.button16();
            break;
         case 2131362942:
            this.button17();
            break;
         case 2131362944:
            this.button2();
            break;
         case 2131362945:
            this.button3();
            break;
         case 2131362946:
            this.button4();
            break;
         case 2131362950:
            this.button8();
            break;
         case 2131362951:
            this.button9();
            break;
         case 2131362952:
            this.buttonAbs();
            break;
         case 2131363100:
            this.buttonRetarder();
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(this.get1024x600CofigLayout());
      this.initView();
   }

   protected void onDestroy() {
      super.onDestroy();
      NotifyPanel.getInstance().unregister(this.actionListener);
   }

   private class ActionListener implements ActionDo {
      final ID439PanelActivity this$0;

      private ActionListener(ID439PanelActivity var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      ActionListener(ID439PanelActivity var1, Object var2) {
         this(var1);
      }

      public void todo(List var1) {
         BaseUtil.INSTANCE.runUi(new Function0(this) {
            final ActionListener this$1;

            {
               this.this$1 = var1;
            }

            public Unit invoke() {
               this.this$1.this$0.refreshUi();
               return null;
            }
         });
      }
   }
}
