package com.hzbhd.constant.audio;

import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\t\u0003\u0004\u0005\u0006\u0007\b\t\n\u000bB\u0005¢\u0006\u0002\u0010\u0002¨\u0006\f"},
   d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean;", "", "()V", "DelayTime", "Equalizer", "FaderBalance", "Hpf", "Loudness", "Lpf", "SourceEq", "Sub", "UserEq", "audio-constant"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class AudioDataBean {
   @Metadata(
      d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\u0007J%\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0003J/\u0010\u000b\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"},
      d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$DelayTime;", "", "data", "Ljava/util/HashMap;", "Lcom/hzbhd/constant/audio/AudioConstants$HORN_TYPE;", "", "Lkotlin/collections/HashMap;", "(Ljava/util/HashMap;)V", "getData", "()Ljava/util/HashMap;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "audio-constant"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class DelayTime {
      private final HashMap data;

      public DelayTime(HashMap var1) {
         Intrinsics.checkNotNullParameter(var1, "data");
         super();
         this.data = var1;
      }

      // $FF: synthetic method
      public static DelayTime copy$default(DelayTime var0, HashMap var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = var0.data;
         }

         return var0.copy(var1);
      }

      public final HashMap component1() {
         return this.data;
      }

      public final DelayTime copy(HashMap var1) {
         Intrinsics.checkNotNullParameter(var1, "data");
         return new DelayTime(var1);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof DelayTime)) {
            return false;
         } else {
            DelayTime var2 = (DelayTime)var1;
            return Intrinsics.areEqual((Object)this.data, (Object)var2.data);
         }
      }

      public final HashMap getData() {
         return this.data;
      }

      public int hashCode() {
         return this.data.hashCode();
      }

      public String toString() {
         return "DelayTime(data=" + this.data + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0002\u0010\bJ\u0019\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J\u0019\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J\u0019\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003JW\u0010\u0010\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR!\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0018"},
      d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$Equalizer;", "", "q", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "freq", "gain", "(Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getFreq", "()Ljava/util/ArrayList;", "getGain", "getQ", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "audio-constant"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Equalizer {
      private final ArrayList freq;
      private final ArrayList gain;
      private final ArrayList q;

      public Equalizer(ArrayList var1, ArrayList var2, ArrayList var3) {
         Intrinsics.checkNotNullParameter(var1, "q");
         Intrinsics.checkNotNullParameter(var2, "freq");
         Intrinsics.checkNotNullParameter(var3, "gain");
         super();
         this.q = var1;
         this.freq = var2;
         this.gain = var3;
      }

      // $FF: synthetic method
      public static Equalizer copy$default(Equalizer var0, ArrayList var1, ArrayList var2, ArrayList var3, int var4, Object var5) {
         if ((var4 & 1) != 0) {
            var1 = var0.q;
         }

         if ((var4 & 2) != 0) {
            var2 = var0.freq;
         }

         if ((var4 & 4) != 0) {
            var3 = var0.gain;
         }

         return var0.copy(var1, var2, var3);
      }

      public final ArrayList component1() {
         return this.q;
      }

      public final ArrayList component2() {
         return this.freq;
      }

      public final ArrayList component3() {
         return this.gain;
      }

      public final Equalizer copy(ArrayList var1, ArrayList var2, ArrayList var3) {
         Intrinsics.checkNotNullParameter(var1, "q");
         Intrinsics.checkNotNullParameter(var2, "freq");
         Intrinsics.checkNotNullParameter(var3, "gain");
         return new Equalizer(var1, var2, var3);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof Equalizer)) {
            return false;
         } else {
            Equalizer var2 = (Equalizer)var1;
            if (!Intrinsics.areEqual((Object)this.q, (Object)var2.q)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.freq, (Object)var2.freq)) {
               return false;
            } else {
               return Intrinsics.areEqual((Object)this.gain, (Object)var2.gain);
            }
         }
      }

      public final ArrayList getFreq() {
         return this.freq;
      }

      public final ArrayList getGain() {
         return this.gain;
      }

      public final ArrayList getQ() {
         return this.q;
      }

      public int hashCode() {
         return (this.q.hashCode() * 31 + this.freq.hashCode()) * 31 + this.gain.hashCode();
      }

      public String toString() {
         return "Equalizer(q=" + this.q + ", freq=" + this.freq + ", gain=" + this.gain + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\u0007J%\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0003J/\u0010\u000b\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"},
      d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$FaderBalance;", "", "data", "Ljava/util/HashMap;", "Lcom/hzbhd/constant/audio/AudioConstants$HORN_TYPE;", "", "Lkotlin/collections/HashMap;", "(Ljava/util/HashMap;)V", "getData", "()Ljava/util/HashMap;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "audio-constant"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class FaderBalance {
      private final HashMap data;

      public FaderBalance(HashMap var1) {
         Intrinsics.checkNotNullParameter(var1, "data");
         super();
         this.data = var1;
      }

      // $FF: synthetic method
      public static FaderBalance copy$default(FaderBalance var0, HashMap var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = var0.data;
         }

         return var0.copy(var1);
      }

      public final HashMap component1() {
         return this.data;
      }

      public final FaderBalance copy(HashMap var1) {
         Intrinsics.checkNotNullParameter(var1, "data");
         return new FaderBalance(var1);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof FaderBalance)) {
            return false;
         } else {
            FaderBalance var2 = (FaderBalance)var1;
            return Intrinsics.areEqual((Object)this.data, (Object)var2.data);
         }
      }

      public final HashMap getData() {
         return this.data;
      }

      public int hashCode() {
         return this.data.hashCode();
      }

      public String toString() {
         return "FaderBalance(data=" + this.data + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"},
      d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$Hpf;", "", "freq", "", "q", "slope", "", "(DDI)V", "getFreq", "()D", "getQ", "getSlope", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "audio-constant"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Hpf {
      private final double freq;
      private final double q;
      private final int slope;

      public Hpf(double var1, double var3, int var5) {
         this.freq = var1;
         this.q = var3;
         this.slope = var5;
      }

      // $FF: synthetic method
      public static Hpf copy$default(Hpf var0, double var1, double var3, int var5, int var6, Object var7) {
         if ((var6 & 1) != 0) {
            var1 = var0.freq;
         }

         if ((var6 & 2) != 0) {
            var3 = var0.q;
         }

         if ((var6 & 4) != 0) {
            var5 = var0.slope;
         }

         return var0.copy(var1, var3, var5);
      }

      public final double component1() {
         return this.freq;
      }

      public final double component2() {
         return this.q;
      }

      public final int component3() {
         return this.slope;
      }

      public final Hpf copy(double var1, double var3, int var5) {
         return new Hpf(var1, var3, var5);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof Hpf)) {
            return false;
         } else {
            Hpf var2 = (Hpf)var1;
            if (!Intrinsics.areEqual((Object)this.freq, (Object)var2.freq)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.q, (Object)var2.q)) {
               return false;
            } else {
               return this.slope == var2.slope;
            }
         }
      }

      public final double getFreq() {
         return this.freq;
      }

      public final double getQ() {
         return this.q;
      }

      public final int getSlope() {
         return this.slope;
      }

      public int hashCode() {
         return (Double.hashCode(this.freq) * 31 + Double.hashCode(this.q)) * 31 + Integer.hashCode(this.slope);
      }

      public String toString() {
         return "Hpf(freq=" + this.freq + ", q=" + this.q + ", slope=" + this.slope + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001c"},
      d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$Loudness;", "", "enable", "", "gain", "", "highBoost", "(ZDZ)V", "getEnable", "()Z", "setEnable", "(Z)V", "getGain", "()D", "setGain", "(D)V", "getHighBoost", "setHighBoost", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "audio-constant"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Loudness {
      private boolean enable;
      private double gain;
      private boolean highBoost;

      public Loudness(boolean var1, double var2, boolean var4) {
         this.enable = var1;
         this.gain = var2;
         this.highBoost = var4;
      }

      // $FF: synthetic method
      public static Loudness copy$default(Loudness var0, boolean var1, double var2, boolean var4, int var5, Object var6) {
         if ((var5 & 1) != 0) {
            var1 = var0.enable;
         }

         if ((var5 & 2) != 0) {
            var2 = var0.gain;
         }

         if ((var5 & 4) != 0) {
            var4 = var0.highBoost;
         }

         return var0.copy(var1, var2, var4);
      }

      public final boolean component1() {
         return this.enable;
      }

      public final double component2() {
         return this.gain;
      }

      public final boolean component3() {
         return this.highBoost;
      }

      public final Loudness copy(boolean var1, double var2, boolean var4) {
         return new Loudness(var1, var2, var4);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof Loudness)) {
            return false;
         } else {
            Loudness var2 = (Loudness)var1;
            if (this.enable != var2.enable) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.gain, (Object)var2.gain)) {
               return false;
            } else {
               return this.highBoost == var2.highBoost;
            }
         }
      }

      public final boolean getEnable() {
         return this.enable;
      }

      public final double getGain() {
         return this.gain;
      }

      public final boolean getHighBoost() {
         return this.highBoost;
      }

      public int hashCode() {
         byte var3 = this.enable;
         byte var2 = 1;
         byte var1 = var3;
         if (var3 != 0) {
            var1 = 1;
         }

         int var4 = Double.hashCode(this.gain);
         var3 = this.highBoost;
         if (var3 == 0) {
            var2 = var3;
         }

         return (var1 * 31 + var4) * 31 + var2;
      }

      public final void setEnable(boolean var1) {
         this.enable = var1;
      }

      public final void setGain(double var1) {
         this.gain = var1;
      }

      public final void setHighBoost(boolean var1) {
         this.highBoost = var1;
      }

      public String toString() {
         return "Loudness(enable=" + this.enable + ", gain=" + this.gain + ", highBoost=" + this.highBoost + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"},
      d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$Lpf;", "", "freq", "", "q", "slope", "", "(DDI)V", "getFreq", "()D", "getQ", "getSlope", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "audio-constant"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Lpf {
      private final double freq;
      private final double q;
      private final int slope;

      public Lpf(double var1, double var3, int var5) {
         this.freq = var1;
         this.q = var3;
         this.slope = var5;
      }

      // $FF: synthetic method
      public static Lpf copy$default(Lpf var0, double var1, double var3, int var5, int var6, Object var7) {
         if ((var6 & 1) != 0) {
            var1 = var0.freq;
         }

         if ((var6 & 2) != 0) {
            var3 = var0.q;
         }

         if ((var6 & 4) != 0) {
            var5 = var0.slope;
         }

         return var0.copy(var1, var3, var5);
      }

      public final double component1() {
         return this.freq;
      }

      public final double component2() {
         return this.q;
      }

      public final int component3() {
         return this.slope;
      }

      public final Lpf copy(double var1, double var3, int var5) {
         return new Lpf(var1, var3, var5);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof Lpf)) {
            return false;
         } else {
            Lpf var2 = (Lpf)var1;
            if (!Intrinsics.areEqual((Object)this.freq, (Object)var2.freq)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.q, (Object)var2.q)) {
               return false;
            } else {
               return this.slope == var2.slope;
            }
         }
      }

      public final double getFreq() {
         return this.freq;
      }

      public final double getQ() {
         return this.q;
      }

      public final int getSlope() {
         return this.slope;
      }

      public int hashCode() {
         return (Double.hashCode(this.freq) * 31 + Double.hashCode(this.q)) * 31 + Integer.hashCode(this.slope);
      }

      public String toString() {
         return "Lpf(freq=" + this.freq + ", q=" + this.q + ", slope=" + this.slope + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\u0007J%\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0003J/\u0010\u000b\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"},
      d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$SourceEq;", "", "data", "Ljava/util/HashMap;", "", "Lcom/hzbhd/constant/audio/AudioDataBean$Equalizer;", "Lkotlin/collections/HashMap;", "(Ljava/util/HashMap;)V", "getData", "()Ljava/util/HashMap;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "audio-constant"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class SourceEq {
      private final HashMap data;

      public SourceEq(HashMap var1) {
         Intrinsics.checkNotNullParameter(var1, "data");
         super();
         this.data = var1;
      }

      // $FF: synthetic method
      public static SourceEq copy$default(SourceEq var0, HashMap var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = var0.data;
         }

         return var0.copy(var1);
      }

      public final HashMap component1() {
         return this.data;
      }

      public final SourceEq copy(HashMap var1) {
         Intrinsics.checkNotNullParameter(var1, "data");
         return new SourceEq(var1);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof SourceEq)) {
            return false;
         } else {
            SourceEq var2 = (SourceEq)var1;
            return Intrinsics.areEqual((Object)this.data, (Object)var2.data);
         }
      }

      public final HashMap getData() {
         return this.data;
      }

      public int hashCode() {
         return this.data.hashCode();
      }

      public String toString() {
         return "SourceEq(data=" + this.data + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0017\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010¨\u0006 "},
      d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$Sub;", "", "enable", "", "q", "", "freq", "gain", "(ZDDD)V", "getEnable", "()Z", "setEnable", "(Z)V", "getFreq", "()D", "setFreq", "(D)V", "getGain", "setGain", "getQ", "setQ", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "audio-constant"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Sub {
      private boolean enable;
      private double freq;
      private double gain;
      private double q;

      public Sub(boolean var1, double var2, double var4, double var6) {
         this.enable = var1;
         this.q = var2;
         this.freq = var4;
         this.gain = var6;
      }

      // $FF: synthetic method
      public static Sub copy$default(Sub var0, boolean var1, double var2, double var4, double var6, int var8, Object var9) {
         if ((var8 & 1) != 0) {
            var1 = var0.enable;
         }

         if ((var8 & 2) != 0) {
            var2 = var0.q;
         }

         if ((var8 & 4) != 0) {
            var4 = var0.freq;
         }

         if ((var8 & 8) != 0) {
            var6 = var0.gain;
         }

         return var0.copy(var1, var2, var4, var6);
      }

      public final boolean component1() {
         return this.enable;
      }

      public final double component2() {
         return this.q;
      }

      public final double component3() {
         return this.freq;
      }

      public final double component4() {
         return this.gain;
      }

      public final Sub copy(boolean var1, double var2, double var4, double var6) {
         return new Sub(var1, var2, var4, var6);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof Sub)) {
            return false;
         } else {
            Sub var2 = (Sub)var1;
            if (this.enable != var2.enable) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.q, (Object)var2.q)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.freq, (Object)var2.freq)) {
               return false;
            } else {
               return Intrinsics.areEqual((Object)this.gain, (Object)var2.gain);
            }
         }
      }

      public final boolean getEnable() {
         return this.enable;
      }

      public final double getFreq() {
         return this.freq;
      }

      public final double getGain() {
         return this.gain;
      }

      public final double getQ() {
         return this.q;
      }

      public int hashCode() {
         byte var2 = this.enable;
         byte var1 = var2;
         if (var2 != 0) {
            var1 = 1;
         }

         return ((var1 * 31 + Double.hashCode(this.q)) * 31 + Double.hashCode(this.freq)) * 31 + Double.hashCode(this.gain);
      }

      public final void setEnable(boolean var1) {
         this.enable = var1;
      }

      public final void setFreq(double var1) {
         this.freq = var1;
      }

      public final void setGain(double var1) {
         this.gain = var1;
      }

      public final void setQ(double var1) {
         this.q = var1;
      }

      public String toString() {
         return "Sub(enable=" + this.enable + ", q=" + this.q + ", freq=" + this.freq + ", gain=" + this.gain + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\u0002\u0010\u0007J%\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0003J/\u0010\u000b\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"},
      d2 = {"Lcom/hzbhd/constant/audio/AudioDataBean$UserEq;", "", "data", "Ljava/util/HashMap;", "", "Lcom/hzbhd/constant/audio/AudioDataBean$Equalizer;", "Lkotlin/collections/HashMap;", "(Ljava/util/HashMap;)V", "getData", "()Ljava/util/HashMap;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "audio-constant"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class UserEq {
      private final HashMap data;

      public UserEq(HashMap var1) {
         Intrinsics.checkNotNullParameter(var1, "data");
         super();
         this.data = var1;
      }

      // $FF: synthetic method
      public static UserEq copy$default(UserEq var0, HashMap var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = var0.data;
         }

         return var0.copy(var1);
      }

      public final HashMap component1() {
         return this.data;
      }

      public final UserEq copy(HashMap var1) {
         Intrinsics.checkNotNullParameter(var1, "data");
         return new UserEq(var1);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof UserEq)) {
            return false;
         } else {
            UserEq var2 = (UserEq)var1;
            return Intrinsics.areEqual((Object)this.data, (Object)var2.data);
         }
      }

      public final HashMap getData() {
         return this.data;
      }

      public int hashCode() {
         return this.data.hashCode();
      }

      public String toString() {
         return "UserEq(data=" + this.data + ')';
      }
   }
}
