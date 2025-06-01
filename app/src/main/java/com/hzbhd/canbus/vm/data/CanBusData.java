package com.hzbhd.canbus.vm.data;

import androidx.lifecycle.MutableLiveData;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.config.use.CanBusDefault;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001f\u0010\u0005\u001a\u00060\u0006R\u00020\u00008VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001f\u0010\u000b\u001a\u00060\fR\u00020\u00008VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"},
   d2 = {"Lcom/hzbhd/canbus/vm/data/CanBusData;", "", "vm", "Lcom/hzbhd/canbus/vm/Vm;", "(Lcom/hzbhd/canbus/vm/Vm;)V", "radar", "Lcom/hzbhd/canbus/vm/data/CanBusData$RadarData;", "getRadar", "()Lcom/hzbhd/canbus/vm/data/CanBusData$RadarData;", "radar$delegate", "Lkotlin/Lazy;", "reverse", "Lcom/hzbhd/canbus/vm/data/CanBusData$ReverseData;", "getReverse", "()Lcom/hzbhd/canbus/vm/data/CanBusData$ReverseData;", "reverse$delegate", "RadarData", "ReverseData", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class CanBusData {
   private final Lazy radar$delegate;
   private final Lazy reverse$delegate;

   public CanBusData(Vm var1) {
      Intrinsics.checkNotNullParameter(var1, "vm");
      super();
      this.reverse$delegate = LazyKt.lazy((Function0)(new Function0(this) {
         final CanBusData this$0;

         {
            this.this$0 = var1;
         }

         public final ReverseData invoke() {
            return this.this$0.new ReverseData(this.this$0);
         }
      }));
      this.radar$delegate = LazyKt.lazy((Function0)(new Function0(this) {
         final CanBusData this$0;

         {
            this.this$0 = var1;
         }

         public final RadarData invoke() {
            return this.this$0.new RadarData(this.this$0);
         }
      }));
   }

   public RadarData getRadar() {
      return (RadarData)this.radar$delegate.getValue();
   }

   public ReverseData getReverse() {
      return (ReverseData)this.reverse$delegate.getValue();
   }

   @Metadata(
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0007R\"\u0010\b\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007R\"\u0010\t\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\"\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\f0\f0\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\"\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007¨\u0006\u0010"},
      d2 = {"Lcom/hzbhd/canbus/vm/data/CanBusData$RadarData;", "", "(Lcom/hzbhd/canbus/vm/data/CanBusData;)V", "isShowDistanceNotShowLocationUi", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "()Landroidx/lifecycle/MutableLiveData;", "isShowLeftTopOneDistanceUi", "radarScale", "getRadarScale", "radarVisible", "", "getRadarVisible", "smallRadar", "getSmallRadar", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public class RadarData {
      private final MutableLiveData isShowDistanceNotShowLocationUi;
      private final MutableLiveData isShowLeftTopOneDistanceUi;
      private final MutableLiveData radarScale;
      private final MutableLiveData radarVisible;
      private final MutableLiveData smallRadar;
      final CanBusData this$0;

      public RadarData(CanBusData var1) {
         this.this$0 = var1;
         Boolean var2 = false;
         this.radarVisible = new MutableLiveData(0);
         this.radarScale = new MutableLiveData(true);
         this.isShowDistanceNotShowLocationUi = new MutableLiveData(var2);
         this.isShowLeftTopOneDistanceUi = new MutableLiveData(var2);
         this.smallRadar = new MutableLiveData(var2);
      }

      public MutableLiveData getRadarScale() {
         return this.radarScale;
      }

      public MutableLiveData getRadarVisible() {
         return this.radarVisible;
      }

      public MutableLiveData getSmallRadar() {
         return this.smallRadar;
      }

      public MutableLiveData isShowDistanceNotShowLocationUi() {
         return this.isShowDistanceNotShowLocationUi;
      }

      public MutableLiveData isShowLeftTopOneDistanceUi() {
         return this.isShowLeftTopOneDistanceUi;
      }
   }

   @Metadata(
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\n0\n0\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\"\u0010\f\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\"\u0010\r\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\n0\n0\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\"\u0010\u000f\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\n0\n0\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\b¨\u0006\u0011"},
      d2 = {"Lcom/hzbhd/canbus/vm/data/CanBusData$ReverseData;", "", "(Lcom/hzbhd/canbus/vm/data/CanBusData;)V", "backBtn", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getBackBtn", "()Landroidx/lifecycle/MutableLiveData;", "cusPanoramicVisible", "", "getCusPanoramicVisible", "isReverse", "panoramicState", "getPanoramicState", "panoramicVisible", "getPanoramicVisible", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public class ReverseData {
      private final MutableLiveData backBtn;
      private final MutableLiveData cusPanoramicVisible;
      private final MutableLiveData isReverse;
      private final MutableLiveData panoramicState;
      private final MutableLiveData panoramicVisible;
      final CanBusData this$0;

      public ReverseData(CanBusData var1) {
         this.this$0 = var1;
         this.isReverse = new MutableLiveData(false);
         Integer var2 = 8;
         this.cusPanoramicVisible = new MutableLiveData(var2);
         this.panoramicVisible = new MutableLiveData(var2);
         this.panoramicState = new MutableLiveData(0);
         this.backBtn = new MutableLiveData(CanBusDefault.INSTANCE.getCanBusBackBtn());
      }

      public MutableLiveData getBackBtn() {
         return this.backBtn;
      }

      public MutableLiveData getCusPanoramicVisible() {
         return this.cusPanoramicVisible;
      }

      public MutableLiveData getPanoramicState() {
         return this.panoramicState;
      }

      public MutableLiveData getPanoramicVisible() {
         return this.panoramicVisible;
      }

      public MutableLiveData isReverse() {
         return this.isReverse;
      }
   }
}
