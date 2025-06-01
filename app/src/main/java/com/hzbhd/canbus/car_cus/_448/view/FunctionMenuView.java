package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.hzbhd.canbus.car_cus._448.DvrObserver;
import com.hzbhd.canbus.car_cus._448.DvrSender;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface;
import com.hzbhd.canbus.car_cus._448.data.DvrData;
import com.hzbhd.canbus.car_cus._448.window.AlertWindow;
import java.util.ArrayList;

public class FunctionMenuView extends LinearLayout implements DvrDataInterface {
   private FunctionSelectionView fun1;
   private FunctionSeekBarView fun10;
   private FunctionSeekBarView fun11;
   private FunctionSelectionView fun2;
   private FunctionSelectionView fun3;
   private FunctionSelectionView fun4;
   private FunctionSelectionView fun5;
   private FunctionSelectionView fun6;
   private FunctionSelectionView fun7;
   private FunctionSelectionView fun8;
   private FunctionSeekBarView fun9;
   private View view;

   public FunctionMenuView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public FunctionMenuView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public FunctionMenuView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558606, this, true);
      this.view = var4;
      this.fun1 = (FunctionSelectionView)var4.findViewById(2131362296);
      this.fun2 = (FunctionSelectionView)this.view.findViewById(2131362299);
      this.fun3 = (FunctionSelectionView)this.view.findViewById(2131362300);
      this.fun4 = (FunctionSelectionView)this.view.findViewById(2131362301);
      this.fun5 = (FunctionSelectionView)this.view.findViewById(2131362302);
      this.fun6 = (FunctionSelectionView)this.view.findViewById(2131362303);
      this.fun7 = (FunctionSelectionView)this.view.findViewById(2131362304);
      this.fun8 = (FunctionSelectionView)this.view.findViewById(2131362305);
      this.fun9 = (FunctionSeekBarView)this.view.findViewById(2131362306);
      this.fun10 = (FunctionSeekBarView)this.view.findViewById(2131362297);
      this.fun11 = (FunctionSeekBarView)this.view.findViewById(2131362298);
      this.initData(var1);
   }

   private void initData(Context var1) {
      this.fun1.setTitle("分辨率");
      ArrayList var2 = new ArrayList();
      var2.add("640x480/30帧");
      var2.add("1280x720/30帧");
      var2.add("1920x1080/30帧");
      this.fun1.setItems(var2);
      this.fun1.getAction(new ActionCallback(this) {
         final FunctionMenuView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (DvrData.fbl == 2) {
               DvrSender.send(new byte[]{80, 0});
            } else if (DvrData.fbl == 0) {
               DvrSender.send(new byte[]{80, 1});
            } else if (DvrData.fbl == 1) {
               DvrSender.send(new byte[]{80, 2});
            }

            DvrSender.send(new byte[]{80, -1});
         }
      });
      this.fun2.setTitle("时间标志");
      var2 = new ArrayList();
      var2.add("关闭");
      var2.add("日期");
      var2.add("日期和时间");
      this.fun2.setItems(var2);
      this.fun2.getAction(new ActionCallback(this) {
         final FunctionMenuView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (DvrData.sjbz == 2) {
               DvrSender.send(new byte[]{81, 0});
            } else if (DvrData.sjbz == 0) {
               DvrSender.send(new byte[]{81, 1});
            } else if (DvrData.sjbz == 1) {
               DvrSender.send(new byte[]{81, 2});
            }

            DvrSender.send(new byte[]{81, -1});
         }
      });
      this.fun3.setTitle("循环录影");
      var2 = new ArrayList();
      var2.add("1分钟");
      var2.add("3分钟");
      var2.add("5分钟");
      this.fun3.setItems(var2);
      this.fun3.getAction(new ActionCallback(this) {
         final FunctionMenuView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (DvrData.xhly == 2) {
               DvrSender.send(new byte[]{82, 1});
            } else if (DvrData.xhly == 0) {
               DvrSender.send(new byte[]{82, 3});
            } else if (DvrData.xhly == 1) {
               DvrSender.send(new byte[]{82, 5});
            }

            DvrSender.send(new byte[]{82, -1});
         }
      });
      this.fun4.setTitle("录像声音");
      var2 = new ArrayList();
      var2.add("关闭");
      var2.add("开启");
      this.fun4.setItems(var2);
      this.fun4.getAction(new ActionCallback(this) {
         final FunctionMenuView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (DvrData.lxsy == 0) {
               DvrSender.send(new byte[]{83, 1});
            } else if (DvrData.lxsy == 1) {
               DvrSender.send(new byte[]{83, 0});
            }

            DvrSender.send(new byte[]{83, -1});
         }
      });
      this.fun5.setTitle("重力感应");
      var2 = new ArrayList();
      var2.add("关闭");
      var2.add("高");
      var2.add("普通");
      var2.add("低");
      this.fun5.setItems(var2);
      this.fun5.getAction(new ActionCallback(this) {
         final FunctionMenuView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (DvrData.zlgy == 3) {
               DvrSender.send(new byte[]{85, 0});
            } else if (DvrData.zlgy == 0) {
               DvrSender.send(new byte[]{85, 1});
            } else if (DvrData.zlgy == 1) {
               DvrSender.send(new byte[]{85, 3});
            } else if (DvrData.zlgy == 2) {
               DvrSender.send(new byte[]{85, 5});
            }

            DvrSender.send(new byte[]{85, -1});
         }
      });
      this.fun6.setTitle("重置系统");
      this.fun6.setNextPrevActionNone(true);
      this.fun6.getAction(new ActionCallback(this, var1) {
         final FunctionMenuView this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void toDo(Object var1) {
            if (var1.equals("ALL_ACTION")) {
               (new AlertWindow(this.val$context, "您确定要重置系统？", "确定", new ActionCallback(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void toDo(Object var1) {
                     DvrSender.send(new byte[]{92, 0});
                  }
               })).show();
            }

         }
      });
      this.fun7.setTitle("格式化");
      this.fun7.setNextPrevActionNone(true);
      this.fun7.getAction(new ActionCallback(this, var1) {
         final FunctionMenuView this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void toDo(Object var1) {
            if (var1.equals("ALL_ACTION")) {
               (new AlertWindow(this.val$context, "您确定要格式化SD卡？", "确定", new ActionCallback(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void toDo(Object var1) {
                     DvrSender.send(new byte[]{91, 0});
                  }
               })).show();
            }

         }
      });
      this.fun8.setTitle("软件版本");
      this.fun8.setNextPrevActionNone(true);
      this.fun9.setTitleStr("亮度");
      this.fun9.setMax(100);
      this.fun9.setValue(DvrData.ld);
      this.fun9.getAction(new ActionCallback(this) {
         final FunctionMenuView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
         }
      });
      this.fun10.setTitleStr("对比度");
      this.fun10.setMax(100);
      this.fun10.setValue(DvrData.dbd);
      this.fun10.getAction(new ActionCallback(this) {
         final FunctionMenuView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
         }
      });
      this.fun11.setTitleStr("色度");
      this.fun11.setMax(100);
      this.fun11.setValue(DvrData.sd);
      this.fun11.getAction(new ActionCallback(this) {
         final FunctionMenuView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
         }
      });
   }

   public void dataChange(String var1) {
      if (var1.equals("function.settings.mode")) {
         this.updateUi();
      }

   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      DvrObserver.getInstance().register(this);
      DvrSender.send(new byte[]{90, 0, 0, 0, 0});
   }

   public void updateUi() {
      this.fun1.selectItem(DvrData.fbl);
      this.fun2.selectItem(DvrData.sjbz);
      this.fun3.selectItem(DvrData.xhly);
      this.fun4.selectItem(DvrData.lxsy);
      this.fun5.selectItem(DvrData.zlgy);
      this.fun6.setValueStr(DvrData.czxt);
      this.fun7.setValueStr(DvrData.gsh);
      this.fun8.setValueStr(DvrData.rjbb);
   }
}
