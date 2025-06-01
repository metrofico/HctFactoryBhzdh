package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\"\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001a\u0010\u001c\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R\u001a\u0010\u001e\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001a\u0010 \u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019R\u001a\u0010\"\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0017\"\u0004\b#\u0010\u0019R\u001a\u0010$\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R\u001a\u0010&\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0017\"\u0004\b'\u0010\u0019R\u001a\u0010(\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0017\"\u0004\b)\u0010\u0019R\u001a\u0010*\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0017\"\u0004\b+\u0010\u0019R\u001a\u0010,\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0017\"\u0004\b-\u0010\u0019R\u001a\u0010.\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0017\"\u0004\b/\u0010\u0019R\u001a\u00100\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0017\"\u0004\b1\u0010\u0019R\u001a\u00102\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0017\"\u0004\b3\u0010\u0019R\u001a\u00104\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0017\"\u0004\b5\u0010\u0019R\u001a\u00106\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0017\"\u0004\b7\u0010\u0019R$\u00108\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u000109X\u0086\u000e¢\u0006\u0010\n\u0002\u0010>\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001c\u0010?\u001a\u0004\u0018\u00010@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001c\u0010E\u001a\u0004\u0018\u00010FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001c\u0010K\u001a\u0004\u0018\u00010LX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001c\u0010Q\u001a\u0004\u0018\u00010RX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001c\u0010W\u001a\u0004\u0018\u00010XX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\u001a\u0010]\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010\u0006\"\u0004\b_\u0010\bR\u001a\u0010`\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\u0006\"\u0004\bb\u0010\bR\u001a\u0010c\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010\u0006\"\u0004\be\u0010\bR\u001a\u0010f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010\u0006\"\u0004\bh\u0010\b¨\u0006i"},
   d2 = {"Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "", "()V", "balanceRange", "", "getBalanceRange", "()I", "setBalanceRange", "(I)V", "bandRange", "getBandRange", "setBandRange", "custom2Title", "", "getCustom2Title", "()Ljava/lang/String;", "setCustom2Title", "(Ljava/lang/String;)V", "customTitle", "getCustomTitle", "setCustomTitle", "isCanBalanceControl", "", "()Z", "setCanBalanceControl", "(Z)V", "isCanRateControl", "setCanRateControl", "isCanSeekBarMinPlus", "setCanSeekBarMinPlus", "isCustom2Enabled", "setCustom2Enabled", "isCustomEnabled", "setCustomEnabled", "isHaveBandMiddle", "setHaveBandMiddle", "isHaveCustom", "setHaveCustom", "isHaveCustom2", "setHaveCustom2", "isHaveMegaBass", "setHaveMegaBass", "isHaveRateControl", "setHaveRateControl", "isHaveVolumeControl", "setHaveVolumeControl", "isHighEnabled", "setHighEnabled", "isLowEnabled", "setLowEnabled", "isMiddleEnabled", "setMiddleEnabled", "isRlControl", "setRlControl", "isVolumeEnabled", "setVolumeEnabled", "lineBtnAction", "", "getLineBtnAction", "()[Ljava/lang/String;", "setLineBtnAction", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "onAirBtnClickListeners", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "getOnAirBtnClickListeners", "()Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "setOnAirBtnClickListeners", "(Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;)V", "onAmplifierCreateAndDestroyListener", "Lcom/hzbhd/canbus/interfaces/OnAmplifierCreateAndDestroyListener;", "getOnAmplifierCreateAndDestroyListener", "()Lcom/hzbhd/canbus/interfaces/OnAmplifierCreateAndDestroyListener;", "setOnAmplifierCreateAndDestroyListener", "(Lcom/hzbhd/canbus/interfaces/OnAmplifierCreateAndDestroyListener;)V", "onAmplifierPositionListener", "Lcom/hzbhd/canbus/interfaces/OnAmplifierPositionListener;", "getOnAmplifierPositionListener", "()Lcom/hzbhd/canbus/interfaces/OnAmplifierPositionListener;", "setOnAmplifierPositionListener", "(Lcom/hzbhd/canbus/interfaces/OnAmplifierPositionListener;)V", "onAmplifierResetPositionListener", "Lcom/hzbhd/canbus/interfaces/OnAmplifierResetPositionListener;", "getOnAmplifierResetPositionListener", "()Lcom/hzbhd/canbus/interfaces/OnAmplifierResetPositionListener;", "setOnAmplifierResetPositionListener", "(Lcom/hzbhd/canbus/interfaces/OnAmplifierResetPositionListener;)V", "onAmplifierSeekBarListener", "Lcom/hzbhd/canbus/interfaces/OnAmplifierSeekBarListener;", "getOnAmplifierSeekBarListener", "()Lcom/hzbhd/canbus/interfaces/OnAmplifierSeekBarListener;", "setOnAmplifierSeekBarListener", "(Lcom/hzbhd/canbus/interfaces/OnAmplifierSeekBarListener;)V", "seekBarMax", "getSeekBarMax", "setSeekBarMax", "seekBarMaxCustom2", "getSeekBarMaxCustom2", "setSeekBarMaxCustom2", "seekBarVolumeMax", "getSeekBarVolumeMax", "setSeekBarVolumeMax", "volumeRange", "getVolumeRange", "setVolumeRange", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class AmplifierPageUiSet {
   private int balanceRange = 14;
   private int bandRange = 14;
   private String custom2Title = "";
   private String customTitle = "";
   private boolean isCanBalanceControl = true;
   private boolean isCanRateControl = true;
   private boolean isCanSeekBarMinPlus = true;
   private boolean isCustom2Enabled = true;
   private boolean isCustomEnabled = true;
   private boolean isHaveBandMiddle = true;
   private boolean isHaveCustom;
   private boolean isHaveCustom2;
   private boolean isHaveMegaBass;
   private boolean isHaveRateControl = true;
   private boolean isHaveVolumeControl;
   private boolean isHighEnabled = true;
   private boolean isLowEnabled = true;
   private boolean isMiddleEnabled = true;
   private boolean isRlControl = true;
   private boolean isVolumeEnabled = true;
   private String[] lineBtnAction;
   private OnAirBtnClickListener onAirBtnClickListeners;
   private OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener;
   private OnAmplifierPositionListener onAmplifierPositionListener;
   private OnAmplifierResetPositionListener onAmplifierResetPositionListener;
   private OnAmplifierSeekBarListener onAmplifierSeekBarListener;
   private int seekBarMax = 10;
   private int seekBarMaxCustom2 = 1;
   private int seekBarVolumeMax = 10;
   private int volumeRange;

   public final int getBalanceRange() {
      return this.balanceRange;
   }

   public final int getBandRange() {
      return this.bandRange;
   }

   public final String getCustom2Title() {
      return this.custom2Title;
   }

   public final String getCustomTitle() {
      return this.customTitle;
   }

   public final String[] getLineBtnAction() {
      return this.lineBtnAction;
   }

   public final OnAirBtnClickListener getOnAirBtnClickListeners() {
      return this.onAirBtnClickListeners;
   }

   public final OnAmplifierCreateAndDestroyListener getOnAmplifierCreateAndDestroyListener() {
      return this.onAmplifierCreateAndDestroyListener;
   }

   public final OnAmplifierPositionListener getOnAmplifierPositionListener() {
      return this.onAmplifierPositionListener;
   }

   public final OnAmplifierResetPositionListener getOnAmplifierResetPositionListener() {
      return this.onAmplifierResetPositionListener;
   }

   public final OnAmplifierSeekBarListener getOnAmplifierSeekBarListener() {
      return this.onAmplifierSeekBarListener;
   }

   public final int getSeekBarMax() {
      return this.seekBarMax;
   }

   public final int getSeekBarMaxCustom2() {
      return this.seekBarMaxCustom2;
   }

   public final int getSeekBarVolumeMax() {
      return this.seekBarVolumeMax;
   }

   public final int getVolumeRange() {
      return this.volumeRange;
   }

   public final boolean isCanBalanceControl() {
      return this.isCanBalanceControl;
   }

   public final boolean isCanRateControl() {
      return this.isCanRateControl;
   }

   public final boolean isCanSeekBarMinPlus() {
      return this.isCanSeekBarMinPlus;
   }

   public final boolean isCustom2Enabled() {
      return this.isCustom2Enabled;
   }

   public final boolean isCustomEnabled() {
      return this.isCustomEnabled;
   }

   public final boolean isHaveBandMiddle() {
      return this.isHaveBandMiddle;
   }

   public final boolean isHaveCustom() {
      return this.isHaveCustom;
   }

   public final boolean isHaveCustom2() {
      return this.isHaveCustom2;
   }

   public final boolean isHaveMegaBass() {
      return this.isHaveMegaBass;
   }

   public final boolean isHaveRateControl() {
      return this.isHaveRateControl;
   }

   public final boolean isHaveVolumeControl() {
      return this.isHaveVolumeControl;
   }

   public final boolean isHighEnabled() {
      return this.isHighEnabled;
   }

   public final boolean isLowEnabled() {
      return this.isLowEnabled;
   }

   public final boolean isMiddleEnabled() {
      return this.isMiddleEnabled;
   }

   public final boolean isRlControl() {
      return this.isRlControl;
   }

   public final boolean isVolumeEnabled() {
      return this.isVolumeEnabled;
   }

   public final void setBalanceRange(int var1) {
      this.balanceRange = var1;
   }

   public final void setBandRange(int var1) {
      this.bandRange = var1;
   }

   public final void setCanBalanceControl(boolean var1) {
      this.isCanBalanceControl = var1;
   }

   public final void setCanRateControl(boolean var1) {
      this.isCanRateControl = var1;
   }

   public final void setCanSeekBarMinPlus(boolean var1) {
      this.isCanSeekBarMinPlus = var1;
   }

   public final void setCustom2Enabled(boolean var1) {
      this.isCustom2Enabled = var1;
   }

   public final void setCustom2Title(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.custom2Title = var1;
   }

   public final void setCustomEnabled(boolean var1) {
      this.isCustomEnabled = var1;
   }

   public final void setCustomTitle(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.customTitle = var1;
   }

   public final void setHaveBandMiddle(boolean var1) {
      this.isHaveBandMiddle = var1;
   }

   public final void setHaveCustom(boolean var1) {
      this.isHaveCustom = var1;
   }

   public final void setHaveCustom2(boolean var1) {
      this.isHaveCustom2 = var1;
   }

   public final void setHaveMegaBass(boolean var1) {
      this.isHaveMegaBass = var1;
   }

   public final void setHaveRateControl(boolean var1) {
      this.isHaveRateControl = var1;
   }

   public final void setHaveVolumeControl(boolean var1) {
      this.isHaveVolumeControl = var1;
   }

   public final void setHighEnabled(boolean var1) {
      this.isHighEnabled = var1;
   }

   public final void setLineBtnAction(String[] var1) {
      this.lineBtnAction = var1;
   }

   public final void setLowEnabled(boolean var1) {
      this.isLowEnabled = var1;
   }

   public final void setMiddleEnabled(boolean var1) {
      this.isMiddleEnabled = var1;
   }

   public final void setOnAirBtnClickListeners(OnAirBtnClickListener var1) {
      this.onAirBtnClickListeners = var1;
   }

   public final void setOnAmplifierCreateAndDestroyListener(OnAmplifierCreateAndDestroyListener var1) {
      this.onAmplifierCreateAndDestroyListener = var1;
   }

   public final void setOnAmplifierPositionListener(OnAmplifierPositionListener var1) {
      this.onAmplifierPositionListener = var1;
   }

   public final void setOnAmplifierResetPositionListener(OnAmplifierResetPositionListener var1) {
      this.onAmplifierResetPositionListener = var1;
   }

   public final void setOnAmplifierSeekBarListener(OnAmplifierSeekBarListener var1) {
      this.onAmplifierSeekBarListener = var1;
   }

   public final void setRlControl(boolean var1) {
      this.isRlControl = var1;
   }

   public final void setSeekBarMax(int var1) {
      this.seekBarMax = var1;
   }

   public final void setSeekBarMaxCustom2(int var1) {
      this.seekBarMaxCustom2 = var1;
   }

   public final void setSeekBarVolumeMax(int var1) {
      this.seekBarVolumeMax = var1;
   }

   public final void setVolumeEnabled(boolean var1) {
      this.isVolumeEnabled = var1;
   }

   public final void setVolumeRange(int var1) {
      this.volumeRange = var1;
   }
}
