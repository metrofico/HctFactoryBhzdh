package com.hzbhd.canbus.canCustom.canBase;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.ui.binding.BaseViewModel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"},
   d2 = {"Lcom/hzbhd/canbus/canCustom/canBase/CanVm;", "Lcom/hzbhd/ui/binding/BaseViewModel;", "()V", "action", "Lcom/hzbhd/canbus/canCustom/canBase/CanAction;", "getAction", "()Lcom/hzbhd/canbus/canCustom/canBase/CanAction;", "canDocking", "Lcom/hzbhd/canbus/canCustom/canBase/CanDocking;", "getCanDocking", "()Lcom/hzbhd/canbus/canCustom/canBase/CanDocking;", "data", "Lcom/hzbhd/canbus/canCustom/canBase/CanData;", "getData", "()Lcom/hzbhd/canbus/canCustom/canBase/CanData;", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class CanVm extends BaseViewModel {
   public static final Companion Companion;
   private static CanVm vm;
   private static final Lazy vmOwner$delegate;
   private final CanAction action = new CanAction(this);
   private final CanDocking canDocking = new CanDocking();
   private final CanData data = new CanData(this);

   static {
      Companion var0 = new Companion((DefaultConstructorMarker)null);
      Companion = var0;
      vmOwner$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      ViewModel var1 = (new ViewModelProvider((ViewModelStoreOwner)var0.getVmOwner())).get(var0.getVmClass());
      Intrinsics.checkNotNullExpressionValue(var1, "ViewModelProvider(vmOwner).get(getVmClass())");
      vm = (CanVm)var1;
   }

   public CanAction getAction() {
      return this.action;
   }

   public CanDocking getCanDocking() {
      return this.canDocking;
   }

   public CanData getData() {
      return this.data;
   }

   @Metadata(
      d1 = {"\u0000'\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\b\b\u0000\u0010\u0011*\u00020\u0012H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"},
      d2 = {"Lcom/hzbhd/canbus/canCustom/canBase/CanVm$Companion;", "", "()V", "vm", "Lcom/hzbhd/canbus/canCustom/canBase/CanVm;", "getVm", "()Lcom/hzbhd/canbus/canCustom/canBase/CanVm;", "setVm", "(Lcom/hzbhd/canbus/canCustom/canBase/CanVm;)V", "vmOwner", "com/hzbhd/canbus/canCustom/canBase/CanVm$Companion$vmOwner$2$1", "getVmOwner", "()Lcom/hzbhd/canbus/canCustom/canBase/CanVm$Companion$vmOwner$2$1;", "vmOwner$delegate", "Lkotlin/Lazy;", "getVmClass", "Ljava/lang/Class;", "T", "Lcom/hzbhd/ui/binding/BaseViewModel;", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      private final Class getVmClass() {
         String var1 = "com.hzbhd.canbus._" + CanbusConfig.INSTANCE.getCanType() + ".Vm" + CanbusConfig.INSTANCE.getCanType();

         try {
            Class var3 = Class.forName(var1);
            Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type java.lang.Class<T of com.hzbhd.canbus.canCustom.canBase.CanVm.Companion.getVmClass>");
            return var3;
         } catch (Exception var2) {
            var2.printStackTrace();
            return CanVm.class;
         }
      }

      private final <undefinedtype> getVmOwner() {
         return (<undefinedtype>)CanVm.vmOwner$delegate.getValue();
      }

      public final CanVm getVm() {
         return CanVm.vm;
      }

      public final void setVm(CanVm var1) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         CanVm.vm = var1;
      }
   }
}
