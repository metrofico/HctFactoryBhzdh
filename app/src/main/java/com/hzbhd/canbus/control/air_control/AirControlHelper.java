package com.hzbhd.canbus.control.air_control;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTempTouchListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.control.air_control.button.AirBtnControl;
import com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl;
import com.hzbhd.canbus.control.air_control.wind.AirWindControl;
import com.hzbhd.canbus.interfaces.AirControlInterface;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;

@Metadata(
   d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u0000 R2\u00020\u0001:\u0001RB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010\u0011J\u0010\u0010C\u001a\u00020A2\b\u0010D\u001a\u0004\u0018\u00010EJ\u0010\u0010F\u001a\u00020A2\b\u0010D\u001a\u0004\u0018\u00010EJ$\u0010G\u001a\u00020A2\b\u0010D\u001a\u0004\u0018\u00010E2\b\u0010H\u001a\u0004\u0018\u00010\u00112\b\u0010I\u001a\u0004\u0018\u00010\u0011J\b\u0010J\u001a\u00020AH\u0002J0\u0010K\u001a\u00020A2\u0006\u0010L\u001a\u00020\u00112\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020N2\u0006\u0010P\u001a\u00020E2\u0006\u0010Q\u001a\u00020EH\u0002R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000Rr\u0010\u000f\u001ad\u0012*\u0012(\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011 \u0012*\u0014\u0012\u000e\b\u0001\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011\u0018\u00010\u00100\u0010 \u0012*2\u0012,\b\u0001\u0012(\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011 \u0012*\u0014\u0012\u000e\b\u0001\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011\u0018\u00010\u00100\u0010\u0018\u00010\u00100\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013R6\u0010\u0014\u001a(\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00150\u0015 \u0012*\u0014\u0012\u000e\b\u0001\u0012\n \u0012*\u0004\u0018\u00010\u00150\u0015\u0018\u00010\u00100\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R6\u0010\u001b\u001a(\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u001c0\u001c \u0012*\u0014\u0012\u000e\b\u0001\u0012\n \u0012*\u0004\u0018\u00010\u001c0\u001c\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R6\u0010#\u001a(\u0012\f\u0012\n \u0012*\u0004\u0018\u00010!0! \u0012*\u0014\u0012\u000e\b\u0001\u0012\n \u0012*\u0004\u0018\u00010!0!\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010$R\u0016\u0010%\u001a\n \u0012*\u0004\u0018\u00010&0&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010(\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\nR\u0011\u0010*\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\nR\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010.\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020100\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u00102\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\nR\u0011\u00104\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\nR\u0011\u00106\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\nR\u0011\u00108\u001a\u000209¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010<\u001a\u000209¢\u0006\b\n\u0000\u001a\u0004\b=\u0010;R\u0011\u0010>\u001a\u000209¢\u0006\b\n\u0000\u001a\u0004\b?\u0010;¨\u0006S"},
   d2 = {"Lcom/hzbhd/canbus/control/air_control/AirControlHelper;", "", "mContext", "Landroid/content/Context;", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "(Landroid/content/Context;Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;)V", "leftTemperatureDown", "Lcom/hzbhd/canbus/control/air_control/temperature/AirTemperatureControl;", "getLeftTemperatureDown", "()Lcom/hzbhd/canbus/control/air_control/temperature/AirTemperatureControl;", "leftTemperatureUp", "getLeftTemperatureUp", "mAirBtnControl", "Lcom/hzbhd/canbus/control/air_control/button/AirBtnControl;", "mFrontAirBtnActions", "", "", "kotlin.jvm.PlatformType", "[[Ljava/lang/String;", "mFrontOnAirBtnClickListeners", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "[Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "mHandler", "Landroid/os/Handler;", "mMaxWindLevel", "", "mOnAirTempTouchListeners", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirTempTouchListener;", "[Lcom/hzbhd/canbus/adapter/interfaces/OnAirTempTouchListener;", "mOnAirTempTouchListenersLeft", "mOnAirTempTouchListenersRight", "mOnUpDownClickListenerLeft", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirTemperatureUpDownClickListener;", "mOnUpDownClickListenerRight", "mOnUpDownClickListeners", "[Lcom/hzbhd/canbus/adapter/interfaces/OnAirTemperatureUpDownClickListener;", "mOnWindSpeedClickListener", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;", "mTemperatureSwitch", "rightTemperatureDown", "getRightTemperatureDown", "rightTemperatureUp", "getRightTemperatureUp", "targetDelay", "", "targetTimers", "Lkotlin/Pair;", "", "Lcom/hzbhd/canbus/util/TimerUtil;", "temperatureDown", "getTemperatureDown", "temperatureTarget", "getTemperatureTarget", "temperatureUp", "getTemperatureUp", "windDecrease", "Lcom/hzbhd/canbus/control/air_control/wind/AirWindControl;", "getWindDecrease", "()Lcom/hzbhd/canbus/control/air_control/wind/AirWindControl;", "windIncrease", "getWindIncrease", "windTarget", "getWindTarget", "airControlAction", "", "action", "airControlMost", "control", "Lcom/hzbhd/canbus/interfaces/AirControlInterface;", "airControlStep", "airControlTarget", "type", "value", "checkTemperatureSwitch", "target", "tag", "step", "", "times", "up", "down", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class AirControlHelper {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final float TEMPERATURE_STEP = 0.5F;
   private static final float WIND_STEP = 1.0F;
   private static Timer mTimer;
   private static TimerTask mTimerTask;
   private final AirTemperatureControl leftTemperatureDown;
   private final AirTemperatureControl leftTemperatureUp;
   private AirBtnControl mAirBtnControl;
   private final Context mContext;
   private String[][] mFrontAirBtnActions;
   private OnAirBtnClickListener[] mFrontOnAirBtnClickListeners;
   private final Handler mHandler;
   private int mMaxWindLevel;
   private final OnAirTempTouchListener[] mOnAirTempTouchListeners;
   private OnAirTempTouchListener mOnAirTempTouchListenersLeft;
   private OnAirTempTouchListener mOnAirTempTouchListenersRight;
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft;
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRight;
   private final OnAirTemperatureUpDownClickListener[] mOnUpDownClickListeners;
   private OnAirWindSpeedUpDownClickListener mOnWindSpeedClickListener;
   private int mTemperatureSwitch;
   private final AirTemperatureControl rightTemperatureDown;
   private final AirTemperatureControl rightTemperatureUp;
   private long targetDelay;
   private Pair targetTimers;
   private final AirTemperatureControl temperatureDown;
   private final AirTemperatureControl temperatureTarget;
   private final AirTemperatureControl temperatureUp;
   private final AirWindControl windDecrease;
   private final AirWindControl windIncrease;
   private final AirWindControl windTarget;

   public AirControlHelper(Context var1, AirPageUiSet var2) {
      Intrinsics.checkNotNullParameter(var1, "mContext");
      Intrinsics.checkNotNullParameter(var2, "mAirPageUiSet");
      super();
      this.mContext = var1;
      this.mTemperatureSwitch = -1;
      this.targetDelay = -200L;
      this.mOnUpDownClickListeners = var2.getFrontArea().getTempSetViewOnUpDownClickListeners();
      this.mOnAirTempTouchListeners = var2.getFrontArea().getTempTouchListeners();
      this.mOnWindSpeedClickListener = var2.getFrontArea().getSetWindSpeedViewOnClickListener();
      this.mMaxWindLevel = var2.getFrontArea().getWindMaxLevel();
      this.mFrontAirBtnActions = var2.getFrontArea().getLineBtnAction();
      this.mFrontOnAirBtnClickListeners = var2.getFrontArea().getOnAirBtnClickListeners();
      this.mAirBtnControl = new AirBtnControl(this.mFrontAirBtnActions, this.mFrontOnAirBtnClickListeners);
      this.mHandler = new Handler(Looper.getMainLooper());
      this.temperatureUp = (AirTemperatureControl)(new AirTemperatureControl(this) {
         final AirControlHelper this$0;

         {
            this.this$0 = var1;
         }

         public void step() {
            if (!ArrayUtils.isEmpty(this.this$0.mOnUpDownClickListeners)) {
               OnAirTemperatureUpDownClickListener[] var3 = this.this$0.mOnUpDownClickListeners;
               Intrinsics.checkNotNullExpressionValue(var3, "mOnUpDownClickListeners");
               Object[] var5 = (Object[])var3;
               int var1 = 0;

               for(int var2 = var5.length; var1 < var2; ++var1) {
                  OnAirTemperatureUpDownClickListener var4 = (OnAirTemperatureUpDownClickListener)var5[var1];
                  if (var4 != null) {
                     var4.onClickUp();
                  }
               }

            }
         }
      });
      this.temperatureDown = (AirTemperatureControl)(new AirTemperatureControl(this) {
         final AirControlHelper this$0;

         {
            this.this$0 = var1;
         }

         public void step() {
            if (!ArrayUtils.isEmpty(this.this$0.mOnUpDownClickListeners)) {
               OnAirTemperatureUpDownClickListener[] var3 = this.this$0.mOnUpDownClickListeners;
               Intrinsics.checkNotNullExpressionValue(var3, "mOnUpDownClickListeners");
               Object[] var5 = (Object[])var3;
               int var1 = 0;

               for(int var2 = var5.length; var1 < var2; ++var1) {
                  OnAirTemperatureUpDownClickListener var4 = (OnAirTemperatureUpDownClickListener)var5[var1];
                  if (var4 != null) {
                     var4.onClickDown();
                  }
               }

            }
         }
      });
      this.leftTemperatureUp = (AirTemperatureControl)(new AirTemperatureControl(this) {
         final AirControlHelper this$0;

         {
            this.this$0 = var1;
         }

         public void step() {
            this.this$0.checkTemperatureSwitch();
            OnAirTemperatureUpDownClickListener var1 = this.this$0.mOnUpDownClickListenerLeft;
            if (var1 != null) {
               var1.onClickUp();
            }

         }
      });
      this.leftTemperatureDown = (AirTemperatureControl)(new AirTemperatureControl(this) {
         final AirControlHelper this$0;

         {
            this.this$0 = var1;
         }

         public void step() {
            this.this$0.checkTemperatureSwitch();
            OnAirTemperatureUpDownClickListener var1 = this.this$0.mOnUpDownClickListenerLeft;
            if (var1 != null) {
               var1.onClickDown();
            }

         }
      });
      this.rightTemperatureUp = (AirTemperatureControl)(new AirTemperatureControl(this) {
         final AirControlHelper this$0;

         {
            this.this$0 = var1;
         }

         public void step() {
            this.this$0.checkTemperatureSwitch();
            OnAirTemperatureUpDownClickListener var1 = this.this$0.mOnUpDownClickListenerRight;
            if (var1 != null) {
               var1.onClickUp();
            }

         }
      });
      this.rightTemperatureDown = (AirTemperatureControl)(new AirTemperatureControl(this) {
         final AirControlHelper this$0;

         {
            this.this$0 = var1;
         }

         public void step() {
            this.this$0.checkTemperatureSwitch();
            OnAirTemperatureUpDownClickListener var1 = this.this$0.mOnUpDownClickListenerRight;
            if (var1 != null) {
               var1.onClickDown();
            }

         }
      });
      this.temperatureTarget = (AirTemperatureControl)(new AirTemperatureControl(this) {
         private final Regex highRegex;
         private final Regex lowRegex;
         final AirControlHelper this$0;
         private final Regex unitRegex;

         // $FF: synthetic method
         public static void $r8$lambda$4Av4ySs1YG0ER9CtnIlUhhtM5E0(String var0, Function0 var1, Object var2, long var3, float var5, AirControlHelper var6, String var7, AirControlInterface var8, AirControlInterface var9) {
            targetAbsolute$lambda_1(var0, var1, var2, var3, var5, var6, var7, var8, var9);
         }

         // $FF: synthetic method
         public static void $r8$lambda$_zONUWfKZ7iTrYW0Xc1vPfvLEjg(AirControlHelper var0, String var1, Ref.FloatRef var2) {
            target$lambda_0(var0, var1, var2);
         }

         {
            this.this$0 = var1;
            this.unitRegex = new Regex("[℃℉]");
            this.lowRegex = new Regex("[Ll][Oo].*");
            this.highRegex = new Regex("[Hh][Ii].*");
         }

         private static final void target$lambda_0(AirControlHelper var0, String var1, Ref.FloatRef var2) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "$tag");
            Intrinsics.checkNotNullParameter(var2, "$times");
            var0.target(var1, 0.5F, var2.element, (AirControlInterface)var0.getTemperatureUp(), (AirControlInterface)var0.getTemperatureDown());
         }

         private final void targetAbsolute(String var1, String var2, Function0 var3, AirControlInterface var4, AirControlInterface var5) {
            CharSequence var10 = (CharSequence)var3.invoke();
            boolean var7 = this.lowRegex.matches(var10);
            long var8 = 500L;
            float var6;
            if (var7) {
               this.this$0.getTemperatureUp().step();
               var6 = 0.5F;
            } else {
               var10 = (CharSequence)var3.invoke();
               if (this.highRegex.matches(var10)) {
                  this.this$0.getTemperatureDown().step();
                  var6 = -0.5F;
               } else {
                  var8 = 0L;
                  var6 = 0.0F;
               }
            }

            this.this$0.mHandler.postDelayed(new AirControlHelper$temperatureTarget$1$$ExternalSyntheticLambda1(var2, var3, this, var8, var6, this.this$0, var1, var4, var5), var8);
         }

         private static final void targetAbsolute$lambda_1(String var0, Function0 var1, Object var2, long var3, float var5, AirControlHelper var6, String var7, AirControlInterface var8, AirControlInterface var9) {
            Intrinsics.checkNotNullParameter(var0, "$target");
            Intrinsics.checkNotNullParameter(var1, "$current");
            Intrinsics.checkNotNullParameter(var2, "this$0");
            Intrinsics.checkNotNullParameter(var6, "this$1");
            Intrinsics.checkNotNullParameter(var7, "$tag");
            Intrinsics.checkNotNullParameter(var8, "$up");
            Intrinsics.checkNotNullParameter(var9, "$down");
            float var10 = Float.parseFloat(var0);
            CharSequence var11 = (CharSequence)var1.invoke();
            var10 -= Float.parseFloat(var2.unitRegex.replace(var11, ""));
            Log.i("SpeechDebug", "targetAbsolute: delay[" + var3 + "], offset[" + var5 + "], times[" + var10 + ']');
            var6.target(var7, 0.5F, var10 - var5, var8, var9);
         }

         public void step() {
         }

         public void target(String var1, String var2) {
            Intrinsics.checkNotNullParameter(var1, "type");
            Intrinsics.checkNotNullParameter(var2, "value");
            String var7 = "AirTemperatureControl" + SystemClock.elapsedRealtime();
            int var3 = var1.hashCode();
            if (var3 != 43) {
               if (var3 != 45) {
                  if (var3 == 1728122231 && var1.equals("absolute")) {
                     try {
                        this.targetAbsolute(var7, var2, (Function0)null.INSTANCE, (AirControlInterface)this.this$0.getLeftTemperatureUp(), (AirControlInterface)this.this$0.getLeftTemperatureDown());
                        this.targetAbsolute(var7, var2, (Function0)null.INSTANCE, (AirControlInterface)this.this$0.getRightTemperatureUp(), (AirControlInterface)this.this$0.getRightTemperatureDown());
                     } catch (Exception var8) {
                        var8.printStackTrace();
                     }

                     return;
                  }

                  return;
               }

               if (!var1.equals("-")) {
                  return;
               }
            } else if (!var1.equals("+")) {
               return;
            }

            long var4;
            Ref.FloatRef var6;
            label56: {
               var6 = new Ref.FloatRef();
               var6.element = Float.parseFloat(var1 + var2);
               var4 = 500L;
               var1 = GeneralAirData.front_left_temperature;
               Intrinsics.checkNotNullExpressionValue(var1, "front_left_temperature");
               CharSequence var9 = (CharSequence)var1;
               if (this.lowRegex.matches(var9)) {
                  var1 = GeneralAirData.front_right_temperature;
                  Intrinsics.checkNotNullExpressionValue(var1, "front_right_temperature");
                  var9 = (CharSequence)var1;
                  if (this.lowRegex.matches(var9)) {
                     if (var6.element < 0.0F) {
                        return;
                     }

                     this.this$0.getTemperatureUp().step();
                     var6.element -= 0.5F;
                     break label56;
                  }
               }

               var1 = GeneralAirData.front_left_temperature;
               Intrinsics.checkNotNullExpressionValue(var1, "front_left_temperature");
               var9 = (CharSequence)var1;
               if (this.highRegex.matches(var9)) {
                  var1 = GeneralAirData.front_right_temperature;
                  Intrinsics.checkNotNullExpressionValue(var1, "front_right_temperature");
                  var9 = (CharSequence)var1;
                  if (this.highRegex.matches(var9)) {
                     if (var6.element > 0.0F) {
                        return;
                     }

                     this.this$0.getTemperatureDown().step();
                     var6.element += 0.5F;
                     break label56;
                  }
               }

               var4 = 0L;
            }

            this.this$0.mHandler.postDelayed(new AirControlHelper$temperatureTarget$1$$ExternalSyntheticLambda0(this.this$0, var7, var6), var4);
         }
      });
      this.windDecrease = (AirWindControl)(new AirWindControl(this) {
         final AirControlHelper this$0;

         {
            this.this$0 = var1;
         }

         public boolean isComplete() {
            int var1 = GeneralAirData.front_wind_level;
            boolean var2 = true;
            if (var1 > 1) {
               var2 = false;
            }

            return var2;
         }

         public void step() {
            OnAirWindSpeedUpDownClickListener var1 = this.this$0.mOnWindSpeedClickListener;
            if (var1 != null) {
               var1.onClickLeft();
            }

         }
      });
      this.windIncrease = (AirWindControl)(new AirWindControl(this) {
         final AirControlHelper this$0;

         {
            this.this$0 = var1;
         }

         public boolean isComplete() {
            boolean var1;
            if (GeneralAirData.front_wind_level >= this.this$0.mMaxWindLevel) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public void step() {
            OnAirWindSpeedUpDownClickListener var1 = this.this$0.mOnWindSpeedClickListener;
            if (var1 != null) {
               var1.onClickRight();
            }

         }
      });
      this.windTarget = (AirWindControl)(new AirWindControl(this) {
         final AirControlHelper this$0;

         {
            this.this$0 = var1;
         }

         public boolean isComplete() {
            return false;
         }

         public void most() {
         }

         public void step() {
         }

         public void target(String var1, String var2) {
            Intrinsics.checkNotNullParameter(var1, "type");
            Intrinsics.checkNotNullParameter(var2, "value");
            AirControlHelper var5 = this.this$0;
            String var6 = "AirWindControl" + SystemClock.elapsedRealtime();

            Exception var10000;
            label79: {
               int var4;
               boolean var10001;
               try {
                  var4 = var1.hashCode();
               } catch (Exception var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label79;
               }

               float var3;
               label91: {
                  if (var4 != 43) {
                     if (var4 != 45) {
                        if (var4 != 1728122231) {
                           return;
                        }

                        try {
                           if (!var1.equals("absolute")) {
                              return;
                           }
                        } catch (Exception var12) {
                           var10000 = var12;
                           var10001 = false;
                           break label79;
                        }

                        try {
                           var3 = Float.parseFloat(var2) - (float)GeneralAirData.front_wind_level;
                           break label91;
                        } catch (Exception var10) {
                           var10000 = var10;
                           var10001 = false;
                           break label79;
                        }
                     }

                     try {
                        if (!var1.equals("-")) {
                           return;
                        }
                     } catch (Exception var14) {
                        var10000 = var14;
                        var10001 = false;
                        break label79;
                     }
                  } else {
                     try {
                        if (!var1.equals("+")) {
                           return;
                        }
                     } catch (Exception var13) {
                        var10000 = var13;
                        var10001 = false;
                        break label79;
                     }
                  }

                  StringBuilder var7;
                  try {
                     var7 = new StringBuilder();
                     var7 = var7.append(var1);
                  } catch (Exception var9) {
                     var10000 = var9;
                     var10001 = false;
                     break label79;
                  }

                  var1 = var2;

                  label52: {
                     try {
                        if (!Intrinsics.areEqual((Object)var2, (Object)"")) {
                           break label52;
                        }
                     } catch (Exception var11) {
                        var10000 = var11;
                        var10001 = false;
                        break label79;
                     }

                     var1 = "1";
                  }

                  try {
                     var3 = Float.parseFloat(var7.append(var1).toString());
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                     break label79;
                  }
               }

               var5.target(var6, 1.0F, var3, (AirControlInterface)this.this$0.getWindIncrease(), (AirControlInterface)this.this$0.getWindDecrease());
               return;
            }

            Exception var16 = var10000;
            var16.printStackTrace();
         }
      });
   }

   private final void checkTemperatureSwitch() {
      if (this.mTemperatureSwitch != CommUtil.isAirTemperatureSwitch(this.mContext)) {
         this.mTemperatureSwitch = CommUtil.isAirTemperatureSwitch(this.mContext);
         boolean var4 = ArrayUtils.isEmpty(this.mOnUpDownClickListeners);
         byte var2 = 2;
         byte var1;
         int var3;
         if (!var4) {
            OnAirTemperatureUpDownClickListener[] var5 = this.mOnUpDownClickListeners;
            var3 = this.mTemperatureSwitch;
            if (var3 == 1) {
               var1 = 2;
            } else {
               var1 = 0;
            }

            this.mOnUpDownClickListenerLeft = var5[var1];
            if (var3 == 1) {
               var1 = 0;
            } else {
               var1 = 2;
            }

            this.mOnUpDownClickListenerRight = var5[var1];
         }

         if (!ArrayUtils.isEmpty(this.mOnAirTempTouchListeners)) {
            OnAirTempTouchListener[] var6 = this.mOnAirTempTouchListeners;
            var3 = this.mTemperatureSwitch;
            if (var3 == 1) {
               var1 = 2;
            } else {
               var1 = 0;
            }

            this.mOnAirTempTouchListenersLeft = var6[var1];
            var1 = var2;
            if (var3 == 1) {
               var1 = 0;
            }

            this.mOnAirTempTouchListenersRight = var6[var1];
         }
      }

   }

   private final void target(String var1, float var2, float var3, AirControlInterface var4, AirControlInterface var5) {
      if (!(var3 > 0.0F)) {
         if (!(var3 < 0.0F)) {
            return;
         }

         var4 = var5;
      }

      var4.reset();
      int var6 = (int)(Math.abs(var3) / var2);
      this.targetDelay += (long)200;
      Log.i("SpeechDebug", "target: start delay " + this.targetDelay);
      TimerUtil var7 = new TimerUtil();
      Pair var10 = this.targetTimers;
      String var11;
      if (var10 != null) {
         var11 = (String)var10.getFirst();
      } else {
         var11 = null;
      }

      if (Intrinsics.areEqual((Object)var11, (Object)var1)) {
         Pair var8 = this.targetTimers;
         if (var8 != null) {
            List var9 = (List)var8.getSecond();
            if (var9 != null) {
               var9.add(var7);
            }
         }
      } else {
         var10 = this.targetTimers;
         if (var10 != null) {
            List var12 = (List)var10.getSecond();
            if (var12 != null) {
               Iterator var13 = ((Iterable)var12).iterator();

               while(var13.hasNext()) {
                  ((TimerUtil)var13.next()).stopTimer();
               }
            }
         }

         this.targetTimers = new Pair(var1, CollectionsKt.arrayListOf(new TimerUtil[]{var7}));
      }

      var7.startTimer((TimerTask)(new TimerTask(var6, var4, var7, this) {
         final AirControlInterface $control;
         final int $finalTimes;
         final TimerUtil $this_run;
         private int i;
         final AirControlHelper this$0;

         {
            this.$finalTimes = var1;
            this.$control = var2;
            this.$this_run = var3;
            this.this$0 = var4;
         }

         public final int getI() {
            return this.i;
         }

         public void run() {
            if (this.i < this.$finalTimes) {
               this.$control.step();
               ++this.i;
            } else {
               this.$this_run.stopTimer();
               AirControlHelper var1 = this.this$0;
               var1.targetDelay = var1.targetDelay - (long)200;
               Log.i("SpeechDebug", "target: finish delay " + this.this$0.targetDelay);
            }

         }

         public final void setI(int var1) {
            this.i = var1;
         }
      }), this.targetDelay, 500L);
   }

   public final void airControlAction(String var1) {
      this.mAirBtnControl.action(var1);
   }

   public final void airControlMost(AirControlInterface var1) {
      if (var1 != null) {
         var1.reset();
         var1.most();
      }
   }

   public final void airControlStep(AirControlInterface var1) {
      if (var1 != null) {
         var1.reset();
         var1.step();
      }
   }

   public final void airControlTarget(AirControlInterface var1, String var2, String var3) {
      if (var1 != null) {
         var1.reset();
         var1.target(var2, var3);
      }
   }

   public final AirTemperatureControl getLeftTemperatureDown() {
      return this.leftTemperatureDown;
   }

   public final AirTemperatureControl getLeftTemperatureUp() {
      return this.leftTemperatureUp;
   }

   public final AirTemperatureControl getRightTemperatureDown() {
      return this.rightTemperatureDown;
   }

   public final AirTemperatureControl getRightTemperatureUp() {
      return this.rightTemperatureUp;
   }

   public final AirTemperatureControl getTemperatureDown() {
      return this.temperatureDown;
   }

   public final AirTemperatureControl getTemperatureTarget() {
      return this.temperatureTarget;
   }

   public final AirTemperatureControl getTemperatureUp() {
      return this.temperatureUp;
   }

   public final AirWindControl getWindDecrease() {
      return this.windDecrease;
   }

   public final AirWindControl getWindIncrease() {
      return this.windIncrease;
   }

   public final AirWindControl getWindTarget() {
      return this.windTarget;
   }

   @Metadata(
      d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"},
      d2 = {"Lcom/hzbhd/canbus/control/air_control/AirControlHelper$Companion;", "", "()V", "TEMPERATURE_STEP", "", "WIND_STEP", "mTimer", "Ljava/util/Timer;", "mTimerTask", "Ljava/util/TimerTask;", "startTimer", "", "timerTask", "delay", "", "period", "", "stopTimer", "CanBusInfo_release"},
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

      // $FF: synthetic method
      public static void startTimer$default(Companion var0, TimerTask var1, long var2, int var4, int var5, Object var6) {
         if ((var5 & 4) != 0) {
            var4 = 0;
         }

         var0.startTimer(var1, var2, var4);
      }

      public final void startTimer(TimerTask var1, long var2, int var4) {
         Intrinsics.checkNotNullParameter(var1, "timerTask");
         if (AirControlHelper.mTimer == null) {
            AirControlHelper.mTimer = new Timer();
         }

         AirControlHelper.mTimerTask = var1;
         Timer var5;
         if (var4 == 0) {
            var5 = AirControlHelper.mTimer;
            Intrinsics.checkNotNull(var5);
            var5.schedule(AirControlHelper.mTimerTask, var2);
         } else {
            var5 = AirControlHelper.mTimer;
            Intrinsics.checkNotNull(var5);
            var5.schedule(AirControlHelper.mTimerTask, var2, (long)var4);
         }

      }

      public final void stopTimer() {
         TimerTask var1 = AirControlHelper.mTimerTask;
         if (var1 != null) {
            var1.cancel();
         }

         AirControlHelper.mTimerTask = null;
         Timer var2 = AirControlHelper.mTimer;
         if (var2 != null) {
            var2.cancel();
         }

         AirControlHelper.mTimer = null;
      }
   }
}
