package com.hzbhd.canbus.vm.action;

import android.view.WindowManager;
import androidx.lifecycle.MutableLiveData;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.canbus.vm.util.BhdWindowManager;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.HandlerThreadUtilKt;
import com.hzbhd.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001:\u0003\u0015\u0016\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001f\u0010\u0005\u001a\u00060\u0006R\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001f\u0010\u000b\u001a\u00060\fR\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u0010\u001a\u00060\u0011R\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0018"},
   d2 = {"Lcom/hzbhd/canbus/vm/action/CanBusAction;", "", "vm", "Lcom/hzbhd/canbus/vm/Vm;", "(Lcom/hzbhd/canbus/vm/Vm;)V", "main", "Lcom/hzbhd/canbus/vm/action/CanBusAction$Main;", "getMain", "()Lcom/hzbhd/canbus/vm/action/CanBusAction$Main;", "main$delegate", "Lkotlin/Lazy;", "radar", "Lcom/hzbhd/canbus/vm/action/CanBusAction$Radar;", "getRadar", "()Lcom/hzbhd/canbus/vm/action/CanBusAction$Radar;", "radar$delegate", "reverse", "Lcom/hzbhd/canbus/vm/action/CanBusAction$Reverse;", "getReverse", "()Lcom/hzbhd/canbus/vm/action/CanBusAction$Reverse;", "reverse$delegate", "Main", "Radar", "Reverse", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class CanBusAction {
   private final Lazy main$delegate;
   private final Lazy radar$delegate;
   private final Lazy reverse$delegate;

   public CanBusAction(Vm var1) {
      Intrinsics.checkNotNullParameter(var1, "vm");
      super();
      this.main$delegate = LazyKt.lazy((Function0)(new Function0(this) {
         final CanBusAction this$0;

         {
            this.this$0 = var1;
         }

         public final Main invoke() {
            return this.this$0.new Main(this.this$0);
         }
      }));
      this.reverse$delegate = LazyKt.lazy((Function0)(new Function0(this) {
         final CanBusAction this$0;

         {
            this.this$0 = var1;
         }

         public final Reverse invoke() {
            return this.this$0.new Reverse(this.this$0);
         }
      }));
      this.radar$delegate = LazyKt.lazy((Function0)(new Function0(this) {
         final CanBusAction this$0;

         {
            this.this$0 = var1;
         }

         public final Radar invoke() {
            return this.this$0.new Radar(this.this$0);
         }
      }));
   }

   public final Main getMain() {
      return (Main)this.main$delegate.getValue();
   }

   public final Radar getRadar() {
      return (Radar)this.radar$delegate.getValue();
   }

   public final Reverse getReverse() {
      return (Reverse)this.reverse$delegate.getValue();
   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"},
      d2 = {"Lcom/hzbhd/canbus/vm/action/CanBusAction$Main;", "", "(Lcom/hzbhd/canbus/vm/action/CanBusAction;)V", "init", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public class Main {
      final CanBusAction this$0;

      public Main(CanBusAction var1) {
         this.this$0 = var1;
      }

      public void init() {
         BhdWindowManager.INSTANCE.init(BaseUtil.INSTANCE.getContext());
         BhdWindowManager.INSTANCE.initReverseWindowParams(this.this$0.getReverse().getReverseParams());
      }
   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0006H\u0016¨\u0006\u000f"},
      d2 = {"Lcom/hzbhd/canbus/vm/action/CanBusAction$Radar;", "", "(Lcom/hzbhd/canbus/vm/action/CanBusAction;)V", "setRadarScale", "", "scale", "", "setRadarVisible", "visible", "", "setShowDistanceNotShowLocationUi", "show", "setShowLeftTopOneDistanceUi", "setSmallRadar", "small", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public class Radar {
      final CanBusAction this$0;

      public Radar(CanBusAction var1) {
         this.this$0 = var1;
      }

      public void setRadarScale(boolean var1) {
         if (LogUtil.log5()) {
            LogUtil.d("setRadarScale: " + var1);
         }

         HandlerThreadUtilKt.runUi((Function0)(new Function0(var1) {
            final boolean $scale;

            {
               this.$scale = var1;
            }

            public final void invoke() {
               Vm.Companion.getVm().getData().getRadar().getRadarScale().setValue(this.$scale);
            }
         }));
      }

      public void setRadarVisible(int var1) {
         if (LogUtil.log5()) {
            LogUtil.d("setRadarVisible: " + var1);
         }

         HandlerThreadUtilKt.runUi((Function0)(new Function0(var1) {
            final int $visible;

            {
               this.$visible = var1;
            }

            public final void invoke() {
               Vm.Companion.getVm().getData().getRadar().getRadarVisible().setValue(this.$visible);
            }
         }));
      }

      public void setShowDistanceNotShowLocationUi(boolean var1) {
         if (LogUtil.log5()) {
            LogUtil.d("setShowDistanceNotShowLocationUi: " + var1);
         }

         HandlerThreadUtilKt.runUi((Function0)(new Function0(var1) {
            final boolean $show;

            {
               this.$show = var1;
            }

            public final void invoke() {
               Vm.Companion.getVm().getData().getRadar().isShowDistanceNotShowLocationUi().setValue(this.$show);
            }
         }));
      }

      public void setShowLeftTopOneDistanceUi(boolean var1) {
         if (LogUtil.log5()) {
            LogUtil.d("setShowLeftTopOneDistanceUi: " + var1);
         }

         HandlerThreadUtilKt.runUi((Function0)(new Function0(var1) {
            final boolean $show;

            {
               this.$show = var1;
            }

            public final void invoke() {
               Vm.Companion.getVm().getData().getRadar().isShowLeftTopOneDistanceUi().setValue(this.$show);
            }
         }));
      }

      public void setSmallRadar(boolean var1) {
         if (LogUtil.log5()) {
            LogUtil.d("setSmallRadar: " + var1);
         }

         HandlerThreadUtilKt.runUi((Function0)(new Function0(var1) {
            final boolean $small;

            {
               this.$small = var1;
            }

            public final void invoke() {
               Vm.Companion.getVm().getData().getRadar().getSmallRadar().setValue(this.$small);
            }
         }));
      }
   }

   @Metadata(
      d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016¨\u0006\f"},
      d2 = {"Lcom/hzbhd/canbus/vm/action/CanBusAction$Reverse;", "", "(Lcom/hzbhd/canbus/vm/action/CanBusAction;)V", "getReverseParams", "Landroid/view/WindowManager$LayoutParams;", "setCustomPanoramicVisible", "", "visible", "", "setPanoramicVisible", "", "updatePanoramic", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public class Reverse {
      final CanBusAction this$0;

      public Reverse(CanBusAction var1) {
         this.this$0 = var1;
      }

      public WindowManager.LayoutParams getReverseParams() {
         if (LogUtil.log5()) {
            LogUtil.d("initWindowParam mReverseViewParams: ");
         }

         WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
         var1.type = 2024;
         var1.flags = 16777512;
         var1.x = 0;
         var1.y = 0;
         var1.width = -1;
         var1.height = -1;
         var1.format = 1;
         var1.setTitle((CharSequence)"ReverseWindow");
         return var1;
      }

      public void setCustomPanoramicVisible(boolean var1) {
         if (LogUtil.log5()) {
            LogUtil.d("setCustomPanoramicVisible: " + var1);
         }

         HandlerThreadUtilKt.runUi((Function0)(new Function0(var1) {
            final boolean $visible;

            {
               this.$visible = var1;
            }

            public final void invoke() {
               MutableLiveData var2 = Vm.Companion.getVm().getData().getReverse().getCusPanoramicVisible();
               byte var1;
               if (this.$visible) {
                  var1 = 0;
               } else {
                  var1 = 8;
               }

               var2.setValue(Integer.valueOf(var1));
            }
         }));
      }

      public void setPanoramicVisible(int var1) {
         if (LogUtil.log5()) {
            LogUtil.d("setPanoramicVisible: " + var1);
         }

         HandlerThreadUtilKt.runUi((Function0)(new Function0(var1) {
            final int $visible;

            {
               this.$visible = var1;
            }

            public final void invoke() {
               Vm.Companion.getVm().getData().getReverse().getPanoramicVisible().setValue(this.$visible);
            }
         }));
      }

      public void updatePanoramic() {
         if (LogUtil.log5()) {
            LogUtil.d("updatePanoramic: ");
         }

         HandlerThreadUtilKt.runUi((Function0)null.INSTANCE);
      }
   }
}
