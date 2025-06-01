package com.hzbhd.canbus.vm;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.hzbhd.canbus.vm.action.CanBusAction;
import com.hzbhd.canbus.vm.data.CanBusData;
import com.hzbhd.canbus.vm.listener.ReverseListener;
import com.hzbhd.canbus.vm.view.main.ReverseMainView;
import com.hzbhd.config.use.UI;
import com.hzbhd.ui.binding.BaseViewModel;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"},
   d2 = {"Lcom/hzbhd/canbus/vm/Vm;", "Lcom/hzbhd/ui/binding/BaseViewModel;", "()V", "action", "Lcom/hzbhd/canbus/vm/action/CanBusAction;", "getAction", "()Lcom/hzbhd/canbus/vm/action/CanBusAction;", "data", "Lcom/hzbhd/canbus/vm/data/CanBusData;", "getData", "()Lcom/hzbhd/canbus/vm/data/CanBusData;", "reverseListener", "Lcom/hzbhd/canbus/vm/listener/ReverseListener;", "getReverseListener", "()Lcom/hzbhd/canbus/vm/listener/ReverseListener;", "reverseMainView", "Lcom/hzbhd/canbus/vm/view/main/ReverseMainView;", "getReverseMainView", "()Lcom/hzbhd/canbus/vm/view/main/ReverseMainView;", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class Vm extends BaseViewModel {
   public static final Companion Companion;
   private static Vm vm;
   private static final Lazy vmOwner$delegate;
   private final CanBusAction action = new CanBusAction(this);
   private final CanBusData data = new CanBusData(this);
   private final ReverseListener reverseListener = new ReverseListener();
   private final ReverseMainView reverseMainView;

   static {
      Companion var0 = new Companion((DefaultConstructorMarker)null);
      Companion = var0;
      vmOwner$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      ViewModel var1 = (new ViewModelProvider((ViewModelStoreOwner)var0.getVmOwner())).get(var0.getVmClass());
      Intrinsics.checkNotNullExpressionValue(var1, "ViewModelProvider(vmOwner).get(getVmClass())");
      vm = (Vm)var1;
   }

   public Vm() {
      this.reverseMainView = new ReverseMainView(BaseUtil.INSTANCE.getContext());
   }

   public CanBusAction getAction() {
      return this.action;
   }

   public CanBusData getData() {
      return this.data;
   }

   public ReverseListener getReverseListener() {
      return this.reverseListener;
   }

   public ReverseMainView getReverseMainView() {
      return this.reverseMainView;
   }

   @Metadata(
      d1 = {"\u0000'\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\b\b\u0000\u0010\u0011*\u00020\u0012H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"},
      d2 = {"Lcom/hzbhd/canbus/vm/Vm$Companion;", "", "()V", "vm", "Lcom/hzbhd/canbus/vm/Vm;", "getVm", "()Lcom/hzbhd/canbus/vm/Vm;", "setVm", "(Lcom/hzbhd/canbus/vm/Vm;)V", "vmOwner", "com/hzbhd/canbus/vm/Vm$Companion$vmOwner$2$1", "getVmOwner", "()Lcom/hzbhd/canbus/vm/Vm$Companion$vmOwner$2$1;", "vmOwner$delegate", "Lkotlin/Lazy;", "getVmClass", "Ljava/lang/Class;", "T", "Lcom/hzbhd/ui/binding/BaseViewModel;", "CanBusInfo_release"},
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
         String var1 = "com.hzbhd.canbus.vm" + UI.INSTANCE.getUIId() + ".VM";

         try {
            Class var3 = Class.forName(var1);
            Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type java.lang.Class<T of com.hzbhd.canbus.vm.Vm.Companion.getVmClass>");
            return var3;
         } catch (Exception var2) {
            var2.printStackTrace();
            return Vm.class;
         }
      }

      private final <undefinedtype> getVmOwner() {
         return (<undefinedtype>)Vm.vmOwner$delegate.getValue();
      }

      public final Vm getVm() {
         return Vm.vm;
      }

      public final void setVm(Vm var1) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         Vm.vm = var1;
      }
   }
}
