package com.hzbhd.canbus.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.view.EqSeekBarView;
import com.hzbhd.canbus.view.LineBtnView;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;

@Metadata(
   d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 =2\u00020\u00012\u00020\u0002:\u0003;<=B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010!\u001a\u00020\u0005H\u0002J\b\u0010\"\u001a\u00020\u0005H\u0002J\u0006\u0010#\u001a\u00020$J\u0012\u0010%\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0012\u0010(\u001a\u00020$2\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\b\u0010+\u001a\u00020$H\u0014J\u0012\u0010,\u001a\u00020$2\b\u0010-\u001a\u0004\u0018\u00010*H\u0016J\u0010\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u00020\u0005H\u0002J\u0010\u00100\u001a\u00020$2\u0006\u0010/\u001a\u00020\u0005H\u0002J\u0010\u00101\u001a\u00020$2\u0006\u0010/\u001a\u00020\u0005H\u0002J\u0010\u00102\u001a\u00020$2\u0006\u0010/\u001a\u00020\u0005H\u0002J\u0010\u00103\u001a\u00020$2\u0006\u0010/\u001a\u00020\u0005H\u0002J\u0010\u00104\u001a\u00020$2\u0006\u0010/\u001a\u00020\u0005H\u0002J\b\u00105\u001a\u00020$H\u0002J\b\u00106\u001a\u00020$H\u0002J\b\u00107\u001a\u00020$H\u0002J\u0018\u00108\u001a\u00020$2\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"},
   d2 = {"Lcom/hzbhd/canbus/activity/AmplifierActivity;", "Lcom/hzbhd/canbus/activity/AbstractBaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "mBalanceRange", "", "mBandRange", "mBgWidthHeight", "mEqSeekBarView", "Lcom/hzbhd/canbus/view/EqSeekBarView$OnMinPlusClickListener;", "mFadBalanceFloatSz", "", "mFadBalanceSz", "mFrontRearValue", "mLeftRightValue", "mLineBtnAction", "", "", "[Ljava/lang/String;", "mOldHeight", "mOnAmplifierCreateAndDestroyListener", "Lcom/hzbhd/canbus/interfaces/OnAmplifierCreateAndDestroyListener;", "mOnAmplifierPositionListener", "Lcom/hzbhd/canbus/interfaces/OnAmplifierPositionListener;", "mOnAmplifierResetPositionListener", "Lcom/hzbhd/canbus/interfaces/OnAmplifierResetPositionListener;", "mOnAmplifierSeekBarListener", "Lcom/hzbhd/canbus/interfaces/OnAmplifierSeekBarListener;", "mOnSeekBarChangeListener", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "mPositionTouchListener", "Landroid/view/View$OnTouchListener;", "mVolumeRange", "getPointWidth", "getPointWidth2", "initViews", "", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "refreshUi", "bundle", "reportFrontPosition", "value", "reportFrontRearPosition", "reportLeftPosition", "reportLeftRightPosition", "reportRearPosition", "reportRightPosition", "updatePointByValue", "updatePointFrontRearByValue", "updatePointLeftRightByValue", "updatePointPosition", "x", "y", "AmplifierBand", "AmplifierPosition", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class AmplifierActivity extends AbstractBaseActivity implements View.OnClickListener {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final String SHARE_PRE_AMPLIFIER_INDEX = "share_pre_amplifier_index";
   public static final String SHARE_PRE_USER_BASS = "share_pre_user_bass";
   public static final String SHARE_PRE_USER_CUSTOM_BASS = "share_pre_user_custom_bass";
   public static final String SHARE_PRE_USER_CUSTOM_BASS_2 = "share_pre_user_custom_bass_2";
   public static final String SHARE_PRE_USER_MEGA_BASS = "share_pre_user_mega_bass";
   public static final String SHARE_PRE_USER_MIDDLE = "share_pre_user_middle";
   public static final String SHARE_PRE_USER_TREBLE = "share_pre_user_treble";
   public static final String SHARE_PRE_USER_VOLUME = "share_pre_user_volume";
   public Map _$_findViewCache;
   private int mBalanceRange;
   private int mBandRange;
   private int mBgWidthHeight;
   private EqSeekBarView.OnMinPlusClickListener mEqSeekBarView;
   private float mFadBalanceFloatSz;
   private int mFadBalanceSz;
   private int mFrontRearValue;
   private int mLeftRightValue;
   private String[] mLineBtnAction;
   private int mOldHeight;
   private OnAmplifierCreateAndDestroyListener mOnAmplifierCreateAndDestroyListener;
   private OnAmplifierPositionListener mOnAmplifierPositionListener;
   private OnAmplifierResetPositionListener mOnAmplifierResetPositionListener;
   private OnAmplifierSeekBarListener mOnAmplifierSeekBarListener;
   private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;
   private View.OnTouchListener mPositionTouchListener;
   private int mVolumeRange;

   public AmplifierActivity() {
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super();
      this.mFadBalanceSz = 14;
      this.mFadBalanceFloatSz = 14.0F;
      this.mEqSeekBarView = (EqSeekBarView.OnMinPlusClickListener)(new EqSeekBarView.OnMinPlusClickListener(this) {
         final AmplifierActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin(SeekBar var1) {
            boolean var9 = Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_high)).getSeekBar());
            byte var5 = 0;
            byte var7 = 0;
            byte var6 = 0;
            int var2 = 0;
            byte var4 = 0;
            byte var8 = 0;
            int var3 = 0;
            OnAmplifierSeekBarListener var10;
            if (var9) {
               var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_high)).getSeekBar().getProgress() - 1;
               if (var2 < 0) {
                  var2 = var3;
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.TREBLE, var2);
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.TREBLE_Min, var2);
               }
            } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar())) {
               var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar().getProgress() - 1;
               if (var2 < 0) {
                  var2 = var5;
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.MIDDLE, var2);
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.MIDDLE_Min, var2);
               }
            } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_low)).getSeekBar())) {
               var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_low)).getSeekBar().getProgress() - 1;
               if (var2 < 0) {
                  var2 = var7;
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.BASS, var2);
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.BASS_Min, var2);
               }
            } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar())) {
               var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar().getProgress() - 1;
               if (var2 < 0) {
                  var2 = var6;
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.VOLUME, var2);
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.VOLUME_Min, var2);
               }
            } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar())) {
               var3 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar().getProgress() - 1;
               if (var3 >= 0) {
                  var2 = var3;
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.MEGA_BASS, var2);
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.MEGA_BASS_Min, var2);
               }
            } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar())) {
               var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar().getProgress() - 1;
               if (var2 < 0) {
                  var2 = var4;
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS, var2);
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS_Min, var2);
               }
            } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar())) {
               var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar().getProgress() - 1;
               if (var2 < 0) {
                  var2 = var8;
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.CUSTOM_2_BASS, var2);
               }

               var10 = this.this$0.mOnAmplifierSeekBarListener;
               if (var10 != null) {
                  var10.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS_2_Min, var2);
               }
            }

         }

         public void onClickPlus(SeekBar var1) {
            int var2;
            int var3;
            OnAmplifierSeekBarListener var4;
            if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_high)).getSeekBar())) {
               var3 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_high)).getSeekBar().getProgress() + 1;
               var2 = var3;
               if (var3 > ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_high)).getSeekBar().getMax()) {
                  var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_high)).getSeekBar().getMax();
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.TREBLE, var2);
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.TREBLE_Plus, var2);
               }
            } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar())) {
               var3 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar().getProgress() + 1;
               var2 = var3;
               if (var3 > ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar().getMax()) {
                  var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar().getMax();
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.MIDDLE, var2);
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.MIDDLE_Plus, var2);
               }
            } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_low)).getSeekBar())) {
               var3 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_low)).getSeekBar().getProgress() + 1;
               var2 = var3;
               if (var3 > ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_low)).getSeekBar().getMax()) {
                  var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_low)).getSeekBar().getMax();
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.BASS, var2);
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.BASS_Plus, var2);
               }
            } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar())) {
               var3 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar().getProgress() + 1;
               var2 = var3;
               if (var3 > ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar().getMax()) {
                  var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar().getMax();
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.VOLUME, var2);
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.VOLUME_Plus, var2);
               }
            } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar())) {
               var3 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar().getProgress() + 1;
               var2 = var3;
               if (var3 > ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar().getMax()) {
                  var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar().getMax();
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.MEGA_BASS, var2);
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.MEGA_BASS_Plus, var2);
               }
            } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar())) {
               var3 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar().getProgress() + 1;
               var2 = var3;
               if (var3 > ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar().getMax()) {
                  var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar().getMax();
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS, var2);
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS_Plus, var2);
               }
            } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar())) {
               var3 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar().getProgress() + 1;
               var2 = var3;
               if (var3 > ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar().getMax()) {
                  var2 = ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar().getMax();
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.CUSTOM_2_BASS, var2);
               }

               var4 = this.this$0.mOnAmplifierSeekBarListener;
               if (var4 != null) {
                  var4.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS_2_Plus, var2);
               }
            }

         }
      });
      this.mOnSeekBarChangeListener = (SeekBar.OnSeekBarChangeListener)(new SeekBar.OnSeekBarChangeListener(this) {
         final AmplifierActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            Intrinsics.checkNotNullParameter(var1, "seekBar");

            try {
               if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_high)).getSeekBar())) {
                  ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_high)).setValue(String.valueOf(var2 - this.this$0.mBandRange));
               } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar())) {
                  ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_middle)).setValue(String.valueOf(var2 - this.this$0.mBandRange));
               } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_low)).getSeekBar())) {
                  ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_low)).setValue(String.valueOf(var2 - this.this$0.mBandRange));
               } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar())) {
                  ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_volume)).setValue(String.valueOf(var2 - this.this$0.mVolumeRange));
               } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar())) {
                  ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_mega_bass)).setValue(String.valueOf(var2 - this.this$0.mBandRange));
               } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar())) {
                  ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom)).setValue(String.valueOf(var2 - this.this$0.mBandRange));
               } else if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar())) {
                  ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom_2)).setValue(String.valueOf(var2));
               }
            } catch (Exception var4) {
               var4.printStackTrace();
            }

         }

         public void onStartTrackingTouch(SeekBar var1) {
            Intrinsics.checkNotNullParameter(var1, "seekBar");
         }

         public void onStopTrackingTouch(SeekBar var1) {
            Intrinsics.checkNotNullParameter(var1, "seekBar");

            Exception var10000;
            label176: {
               OnAmplifierSeekBarListener var2;
               boolean var10001;
               label177: {
                  try {
                     SharePreUtil.setIntValue((Context)this.this$0, "share_pre_amplifier_index", 6);
                     if (!Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_high)).getSeekBar())) {
                        break label177;
                     }

                     ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_high)).setValue(String.valueOf(var1.getProgress() - this.this$0.mBandRange));
                     SharePreUtil.setIntValue((Context)this.this$0, "share_pre_user_treble", var1.getProgress());
                     if (this.this$0.mOnAmplifierSeekBarListener == null) {
                        return;
                     }

                     var2 = this.this$0.mOnAmplifierSeekBarListener;
                  } catch (Exception var16) {
                     var10000 = var16;
                     var10001 = false;
                     break label176;
                  }

                  if (var2 == null) {
                     return;
                  }

                  try {
                     var2.progress(AmplifierActivity.AmplifierBand.TREBLE, var1.getProgress());
                     return;
                  } catch (Exception var3) {
                     var10000 = var3;
                     var10001 = false;
                     break label176;
                  }
               }

               label179: {
                  try {
                     if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar())) {
                        ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_middle)).setValue(String.valueOf(var1.getProgress() - this.this$0.mBandRange));
                        SharePreUtil.setIntValue((Context)this.this$0, "share_pre_user_middle", var1.getProgress());
                        if (this.this$0.mOnAmplifierSeekBarListener == null) {
                           return;
                        }

                        var2 = this.this$0.mOnAmplifierSeekBarListener;
                        break label179;
                     }
                  } catch (Exception var15) {
                     var10000 = var15;
                     var10001 = false;
                     break label176;
                  }

                  label181: {
                     try {
                        if (!Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_low)).getSeekBar())) {
                           break label181;
                        }

                        ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_low)).setValue(String.valueOf(var1.getProgress() - this.this$0.mBandRange));
                        SharePreUtil.setIntValue((Context)this.this$0, "share_pre_user_bass", var1.getProgress());
                        if (this.this$0.mOnAmplifierSeekBarListener == null) {
                           return;
                        }

                        var2 = this.this$0.mOnAmplifierSeekBarListener;
                     } catch (Exception var14) {
                        var10000 = var14;
                        var10001 = false;
                        break label176;
                     }

                     if (var2 == null) {
                        return;
                     }

                     try {
                        var2.progress(AmplifierActivity.AmplifierBand.BASS, var1.getProgress());
                        return;
                     } catch (Exception var5) {
                        var10000 = var5;
                        var10001 = false;
                        break label176;
                     }
                  }

                  label183: {
                     try {
                        if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar())) {
                           ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_volume)).setValue(String.valueOf(var1.getProgress() - this.this$0.mVolumeRange));
                           SharePreUtil.setIntValue((Context)this.this$0, "share_pre_user_volume", var1.getProgress());
                           if (this.this$0.mOnAmplifierSeekBarListener == null) {
                              return;
                           }

                           var2 = this.this$0.mOnAmplifierSeekBarListener;
                           break label183;
                        }
                     } catch (Exception var13) {
                        var10000 = var13;
                        var10001 = false;
                        break label176;
                     }

                     label185: {
                        try {
                           if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar())) {
                              ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_mega_bass)).setValue(String.valueOf(var1.getProgress() - this.this$0.mBandRange));
                              SharePreUtil.setIntValue((Context)this.this$0, "share_pre_user_mega_bass", var1.getProgress());
                              if (this.this$0.mOnAmplifierSeekBarListener == null) {
                                 return;
                              }

                              var2 = this.this$0.mOnAmplifierSeekBarListener;
                              break label185;
                           }
                        } catch (Exception var12) {
                           var10000 = var12;
                           var10001 = false;
                           break label176;
                        }

                        label187: {
                           try {
                              if (Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar())) {
                                 ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom)).setValue(String.valueOf(var1.getProgress() - this.this$0.mBandRange));
                                 SharePreUtil.setIntValue((Context)this.this$0, "share_pre_user_custom_bass", var1.getProgress());
                                 if (this.this$0.mOnAmplifierSeekBarListener == null) {
                                    return;
                                 }

                                 var2 = this.this$0.mOnAmplifierSeekBarListener;
                                 break label187;
                              }
                           } catch (Exception var11) {
                              var10000 = var11;
                              var10001 = false;
                              break label176;
                           }

                           try {
                              if (!Intrinsics.areEqual((Object)var1, (Object)((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar())) {
                                 return;
                              }

                              ((EqSeekBarView)this.this$0._$_findCachedViewById(R.id.seek_bar_custom_2)).setValue(String.valueOf(var1.getProgress() - this.this$0.mBandRange));
                              SharePreUtil.setIntValue((Context)this.this$0, "share_pre_user_custom_bass_2", var1.getProgress());
                              if (this.this$0.mOnAmplifierSeekBarListener == null) {
                                 return;
                              }

                              var2 = this.this$0.mOnAmplifierSeekBarListener;
                           } catch (Exception var10) {
                              var10000 = var10;
                              var10001 = false;
                              break label176;
                           }

                           if (var2 == null) {
                              return;
                           }

                           try {
                              var2.progress(AmplifierActivity.AmplifierBand.CUSTOM_2_BASS, var1.getProgress());
                              return;
                           } catch (Exception var9) {
                              var10000 = var9;
                              var10001 = false;
                              break label176;
                           }
                        }

                        if (var2 == null) {
                           return;
                        }

                        try {
                           var2.progress(AmplifierActivity.AmplifierBand.CUSTOM_BASS, var1.getProgress());
                           return;
                        } catch (Exception var8) {
                           var10000 = var8;
                           var10001 = false;
                           break label176;
                        }
                     }

                     if (var2 == null) {
                        return;
                     }

                     try {
                        var2.progress(AmplifierActivity.AmplifierBand.MEGA_BASS, var1.getProgress());
                        return;
                     } catch (Exception var7) {
                        var10000 = var7;
                        var10001 = false;
                        break label176;
                     }
                  }

                  if (var2 == null) {
                     return;
                  }

                  try {
                     var2.progress(AmplifierActivity.AmplifierBand.VOLUME, var1.getProgress());
                     return;
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                     break label176;
                  }
               }

               if (var2 == null) {
                  return;
               }

               try {
                  var2.progress(AmplifierActivity.AmplifierBand.MIDDLE, var1.getProgress());
                  return;
               } catch (Exception var4) {
                  var10000 = var4;
                  var10001 = false;
               }
            }

            Exception var17 = var10000;
            var17.printStackTrace();
         }
      });
      this.mPositionTouchListener = (View.OnTouchListener)(new View.OnTouchListener(this) {
         final AmplifierActivity this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            Intrinsics.checkNotNullParameter(var2, "event");
            int var3 = var2.getAction();
            if (var3 != 0) {
               if (var3 == 1) {
                  SendKeyManager.getInstance().playBeep(0);
                  this.this$0.reportLeftRightPosition(0);
                  Thread.sleep(100L);
                  this.this$0.reportFrontRearPosition(0);
                  return true;
               }

               if (var3 != 2) {
                  return true;
               }
            }

            this.this$0.updatePointPosition(var2.getX(), var2.getY());
            return true;
         }
      });
   }

   private final int getPointWidth() {
      return ((ImageView)this._$_findCachedViewById(R.id.iv_point)).getWidth();
   }

   private final int getPointWidth2() {
      return ((ImageView)this._$_findCachedViewById(R.id.iv_point)).getWidth() / 2;
   }

   private final void reportFrontPosition(int var1) {
      OnAmplifierPositionListener var2 = this.mOnAmplifierPositionListener;
      if (var2 != null) {
         var2.position(AmplifierActivity.AmplifierPosition.FRONT, this.mFrontRearValue + var1);
      }

   }

   private final void reportFrontRearPosition(int var1) {
      OnAmplifierPositionListener var2 = this.mOnAmplifierPositionListener;
      if (var2 != null) {
         var2.position(AmplifierActivity.AmplifierPosition.FRONT_REAR, this.mFrontRearValue + var1);
      }

   }

   private final void reportLeftPosition(int var1) {
      OnAmplifierPositionListener var2 = this.mOnAmplifierPositionListener;
      if (var2 != null) {
         var2.position(AmplifierActivity.AmplifierPosition.LEFT, this.mLeftRightValue + var1);
      }

   }

   private final void reportLeftRightPosition(int var1) {
      OnAmplifierPositionListener var2 = this.mOnAmplifierPositionListener;
      if (var2 != null) {
         var2.position(AmplifierActivity.AmplifierPosition.LEFT_RIGHT, this.mLeftRightValue + var1);
      }

   }

   private final void reportRearPosition(int var1) {
      OnAmplifierPositionListener var2 = this.mOnAmplifierPositionListener;
      if (var2 != null) {
         var2.position(AmplifierActivity.AmplifierPosition.REAR, this.mFrontRearValue + var1);
      }

   }

   private final void reportRightPosition(int var1) {
      OnAmplifierPositionListener var2 = this.mOnAmplifierPositionListener;
      if (var2 != null) {
         var2.position(AmplifierActivity.AmplifierPosition.RIGHT, this.mLeftRightValue + var1);
      }

   }

   private final void updatePointByValue() {
      LogUtil.showLog("updatePointByValue");
      int var4 = this.mLeftRightValue;
      int var3 = this.mBalanceRange;
      float var2 = (float)(var4 + var3 / 2) / (float)var3;
      float var1 = (float)(this.mBgWidthHeight - this.getPointWidth());
      LogUtil.showLog("mFrontRearValue:" + this.mFrontRearValue);
      LogUtil.showLog("mBalanceRange:" + this.mBalanceRange);
      LogUtil.showLog("1mBgWidthHeight:" + this.mBgWidthHeight);
      LogUtil.showLog("1getPointWidth():" + this.getPointWidth());
      var3 = -this.mFrontRearValue;
      var4 = this.mBalanceRange;
      this.updatePointPosition(var2 * var1, (float)(var3 + var4 / 2) / (float)var4 * (float)(this.mBgWidthHeight - this.getPointWidth()));
   }

   private final void updatePointFrontRearByValue() {
      LogUtil.showLog("updatePointFrontRearByValue");
      float var2 = ((ImageView)this._$_findCachedViewById(R.id.iv_point)).getX();
      float var1 = (float)this.getPointWidth2();
      int var3 = -this.mFrontRearValue;
      int var4 = this.mBalanceRange;
      this.updatePointPosition(var2 + var1, (float)(var3 + var4 / 2) / (float)var4 * (float)(this.mBgWidthHeight - this.getPointWidth()) + (float)this.getPointWidth2());
   }

   private final void updatePointLeftRightByValue() {
      LogUtil.showLog("updatePointLeftRightByValue");
      int var2 = this.mLeftRightValue;
      int var1 = this.mBalanceRange;
      this.updatePointPosition((float)(var2 + var1 / 2) / (float)var1 * (float)(this.mBgWidthHeight - this.getPointWidth()) + (float)this.getPointWidth2(), ((ImageView)this._$_findCachedViewById(R.id.iv_point)).getY() + (float)this.getPointWidth2());
   }

   private final void updatePointPosition(float var1, float var2) {
      LogUtil.showLog("y:" + var2);
      float var3 = var1;
      if (var1 < (float)this.getPointWidth2()) {
         var3 = (float)this.getPointWidth2();
      }

      float var4 = var3;
      if (var3 > (float)(((RelativeLayout)this._$_findCachedViewById(R.id.rl_bg)).getHeight() - this.getPointWidth2())) {
         var4 = (float)(((RelativeLayout)this._$_findCachedViewById(R.id.rl_bg)).getHeight() - this.getPointWidth2());
      }

      var1 = var2;
      if (var2 < (float)this.getPointWidth2()) {
         var1 = (float)this.getPointWidth2();
      }

      var2 = var1;
      if (var1 > (float)(((RelativeLayout)this._$_findCachedViewById(R.id.rl_bg)).getHeight() - this.getPointWidth2())) {
         var2 = (float)(((RelativeLayout)this._$_findCachedViewById(R.id.rl_bg)).getHeight() - this.getPointWidth2());
      }

      ((ImageView)this._$_findCachedViewById(R.id.iv_point)).setX(var4 - (float)this.getPointWidth2());
      ((ImageView)this._$_findCachedViewById(R.id.iv_point)).setY(var2 - (float)this.getPointWidth2());
      ((ImageView)this._$_findCachedViewById(R.id.iv_point)).invalidate();
      LogUtil.showLog("setX:" + var4);
      LogUtil.showLog("setY:" + var2);
      LogUtil.showLog("getPointWidth2():" + this.getPointWidth2());
      LogUtil.showLog("mBgWidthHeight:" + this.mBgWidthHeight);
      LogUtil.showLog("getPointWidth():" + this.getPointWidth());
      LogUtil.showLog("mBalanceRange:" + this.mBalanceRange);
      this.mLeftRightValue = MathKt.roundToInt((var4 - (float)this.getPointWidth2()) / (float)(this.mBgWidthHeight - this.getPointWidth()) * (float)this.mBalanceRange) - this.mBalanceRange / 2;
      this.mFrontRearValue = -(MathKt.roundToInt((var2 - (float)this.getPointWidth2()) / (float)(this.mBgWidthHeight - this.getPointWidth()) * (float)this.mBalanceRange) - this.mBalanceRange / 2);
      LogUtil.showLog("update:FrontRear:" + this.mFrontRearValue + " LeftRight:" + this.mLeftRightValue);
      int var5 = this.mFrontRearValue;
      int var6 = this.mBalanceRange;
      if (var5 > var6 / 2) {
         this.mFrontRearValue = var6 / 2;
      }

      if (this.mFrontRearValue < -(var6 / 2)) {
         this.mFrontRearValue = -(var6 / 2);
      }

      if (this.mLeftRightValue < -(var6 / 2)) {
         this.mLeftRightValue = -(var6 / 2);
      }

      if (this.mLeftRightValue > var6 / 2) {
         this.mLeftRightValue = var6 / 2;
      }

      ((TextView)this._$_findCachedViewById(R.id.tv_front_rear_value)).setText((CharSequence)String.valueOf(this.mFrontRearValue));
      ((TextView)this._$_findCachedViewById(R.id.tv_left_right_value)).setText((CharSequence)String.valueOf(this.mLeftRightValue));
   }

   public void _$_clearFindViewByIdCache() {
      this._$_findViewCache.clear();
   }

   public View _$_findCachedViewById(int var1) {
      Map var4 = this._$_findViewCache;
      View var3 = (View)var4.get(var1);
      View var2 = var3;
      if (var3 == null) {
         var2 = this.findViewById(var1);
         if (var2 != null) {
            var4.put(var1, var2);
         } else {
            var2 = null;
         }
      }

      return var2;
   }

   public final void initViews() {
      Context var3 = (Context)this;
      AmplifierPageUiSet var2 = this.getUiMgrInterface(var3).getAmplifierPageUiSet(var3);
      if (var2 != null) {
         ((TextView)this._$_findCachedViewById(R.id.tv_front_rear_value)).setText((CharSequence)"0");
         ((TextView)this._$_findCachedViewById(R.id.tv_left_right_value)).setText((CharSequence)"0");
         if (var2.isCanBalanceControl()) {
            ((RelativeLayout)this._$_findCachedViewById(R.id.rl_bg)).setOnTouchListener(this.mPositionTouchListener);
         } else {
            ((ImageView)this._$_findCachedViewById(R.id.iv_reset_position)).setVisibility(4);
         }

         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_high)).setCanMinPlus(var2.isCanSeekBarMinPlus());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_middle)).setCanMinPlus(var2.isCanSeekBarMinPlus());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_low)).setCanMinPlus(var2.isCanSeekBarMinPlus());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_volume)).setCanMinPlus(var2.isCanSeekBarMinPlus());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_mega_bass)).setCanMinPlus(var2.isCanSeekBarMinPlus());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom)).setCanMinPlus(var2.isCanSeekBarMinPlus());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom_2)).setCanMinPlus(var2.isCanSeekBarMinPlus());
         if (!var2.isRlControl()) {
            ((ImageView)this._$_findCachedViewById(R.id.iv_top)).setVisibility(8);
            ((ImageView)this._$_findCachedViewById(R.id.iv_left)).setVisibility(8);
            ((ImageView)this._$_findCachedViewById(R.id.iv_right)).setVisibility(8);
            ((ImageView)this._$_findCachedViewById(R.id.iv_bottom)).setVisibility(8);
         }

         if (var2.isHaveVolumeControl()) {
            ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_volume)).setVisibility(0);
         } else {
            ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_volume)).setVisibility(8);
         }

         if (var2.isHaveBandMiddle()) {
            ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_middle)).setVisibility(0);
         } else {
            ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_middle)).setVisibility(8);
         }

         if (var2.isHaveMegaBass()) {
            ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_mega_bass)).setVisibility(0);
         } else {
            ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_mega_bass)).setVisibility(8);
         }

         if (var2.isHaveCustom()) {
            ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom)).setVisibility(0);
            if (!TextUtils.isEmpty((CharSequence)var2.getCustomTitle())) {
               ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom)).setTitle(this.getString(CommUtil.getStrIdByResId(var3, var2.getCustomTitle())));
            }
         } else {
            ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom)).setVisibility(8);
         }

         if (var2.isHaveCustom2()) {
            ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom_2)).setVisibility(0);
            if (!TextUtils.isEmpty((CharSequence)var2.getCustom2Title())) {
               ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom_2)).setTitle(this.getString(CommUtil.getStrIdByResId(var3, var2.getCustom2Title())));
            }
         } else {
            ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom_2)).setVisibility(8);
         }

         this.mVolumeRange = var2.getVolumeRange();
         this.mBandRange = var2.getBandRange();
         int var1 = var2.getBalanceRange();
         this.mBalanceRange = var1;
         this.mFadBalanceSz = var1 / 2;
         this.mFadBalanceFloatSz = (float)(var1 / 2);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_volume)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_high)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_middle)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_low)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_mega_bass)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom_2)).setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_volume)).setOnPlusMinClickListener(this.mEqSeekBarView);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_high)).setOnPlusMinClickListener(this.mEqSeekBarView);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_middle)).setOnPlusMinClickListener(this.mEqSeekBarView);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_low)).setOnPlusMinClickListener(this.mEqSeekBarView);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_mega_bass)).setOnPlusMinClickListener(this.mEqSeekBarView);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom)).setOnPlusMinClickListener(this.mEqSeekBarView);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom_2)).setOnPlusMinClickListener(this.mEqSeekBarView);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_volume)).setSeekBarTouchable(var2.isCanRateControl());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_high)).setSeekBarTouchable(var2.isCanRateControl());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_middle)).setSeekBarTouchable(var2.isCanRateControl());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_low)).setSeekBarTouchable(var2.isCanRateControl());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_mega_bass)).setSeekBarTouchable(var2.isCanRateControl());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom)).setSeekBarTouchable(var2.isCanRateControl());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom_2)).setSeekBarTouchable(var2.isCanRateControl());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_volume)).getSeekBar().setMax(var2.getSeekBarVolumeMax());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_high)).getSeekBar().setMax(var2.getSeekBarMax());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_middle)).getSeekBar().setMax(var2.getSeekBarMax());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_low)).getSeekBar().setMax(var2.getSeekBarMax());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_mega_bass)).getSeekBar().setMax(var2.getSeekBarMax());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom)).getSeekBar().setMax(var2.getSeekBarMax());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom_2)).getSeekBar().setMax(var2.getSeekBarMaxCustom2());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_volume)).setEnabled(var2.isVolumeEnabled());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_high)).setEnabled(var2.isHighEnabled());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_middle)).setEnabled(var2.isMiddleEnabled());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_low)).setEnabled(var2.isLowEnabled());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom)).setEnabled(var2.isCustomEnabled());
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom_2)).setEnabled(var2.isCustom2Enabled());
         this.mLineBtnAction = var2.getLineBtnAction();
         this.mOnAmplifierPositionListener = var2.getOnAmplifierPositionListener();
         this.mOnAmplifierSeekBarListener = var2.getOnAmplifierSeekBarListener();
         this.mOnAmplifierResetPositionListener = var2.getOnAmplifierResetPositionListener();
         this.mOnAmplifierCreateAndDestroyListener = var2.getOnAmplifierCreateAndDestroyListener();
         if (ArrayUtils.isEmpty(this.mLineBtnAction)) {
            ((LineBtnView)this._$_findCachedViewById(R.id.lbv_top)).setVisibility(8);
         } else {
            ((LineBtnView)this._$_findCachedViewById(R.id.lbv_top)).initButton(var3, this.mLineBtnAction, true, (String[])null, var2.getOnAirBtnClickListeners());
         }

         if (var2.isHaveRateControl()) {
            ((LinearLayout)this._$_findCachedViewById(R.id.ll_rate_control)).setVisibility(0);
         } else {
            ((LinearLayout)this._$_findCachedViewById(R.id.ll_rate_control)).setVisibility(4);
         }

         ((RelativeLayout)this._$_findCachedViewById(R.id.rl_bg)).getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)(new ViewTreeObserver.OnGlobalLayoutListener(this) {
            final AmplifierActivity this$0;

            {
               this.this$0 = var1;
            }

            public void onGlobalLayout() {
               LogUtil.showLog("fang", "mOldHeight:" + this.this$0.mOldHeight + " rl_bg.height:" + ((RelativeLayout)this.this$0._$_findCachedViewById(R.id.rl_bg)).getHeight());
               if (this.this$0.mOldHeight != ((RelativeLayout)this.this$0._$_findCachedViewById(R.id.rl_bg)).getHeight()) {
                  AmplifierActivity var1 = this.this$0;
                  var1.mOldHeight = ((RelativeLayout)var1._$_findCachedViewById(R.id.rl_bg)).getHeight();
                  ViewGroup.LayoutParams var2 = ((RelativeLayout)this.this$0._$_findCachedViewById(R.id.rl_bg)).getLayoutParams();
                  var2.width = ((RelativeLayout)this.this$0._$_findCachedViewById(R.id.rl_bg)).getHeight();
                  ((RelativeLayout)this.this$0._$_findCachedViewById(R.id.rl_bg)).setLayoutParams(var2);
                  ((RelativeLayout)this.this$0._$_findCachedViewById(R.id.rl_bg)).invalidate();
                  this.this$0.mBgWidthHeight = var2.width;
                  this.this$0.refreshUi((Bundle)null);
               }

            }
         }));
      }
   }

   public void onClick(View var1) {
      Integer var3;
      if (var1 != null) {
         var3 = var1.getId();
      } else {
         var3 = null;
      }

      if (var3 != null && var3 == 2131362653) {
         this.reportFrontRearPosition(1);
         this.reportFrontPosition(1);
      } else if (var3 != null && var3 == 2131362580) {
         this.reportLeftRightPosition(-1);
         this.reportLeftPosition(-1);
      } else if (var3 != null && var3 == 2131362542) {
         this.reportFrontRearPosition(-1);
         this.reportRearPosition(-1);
      } else if (var3 != null && var3 == 2131362613) {
         this.reportLeftRightPosition(1);
         this.reportRightPosition(1);
      } else if (var3 != null && var3 == 2131362612) {
         OnAmplifierResetPositionListener var4 = this.mOnAmplifierResetPositionListener;
         if (var4 != null) {
            var4.resetClick();
         }

         int var2 = this.mBgWidthHeight;
         this.updatePointPosition((float)var2 / 2.0F, (float)var2 / 2.0F);
         this.reportFrontRearPosition(0);
         Thread.sleep(100L);
         this.reportLeftRightPosition(0);
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558655);
      this.initViews();
      OnAmplifierCreateAndDestroyListener var2 = this.mOnAmplifierCreateAndDestroyListener;
      if (var2 != null) {
         var2.create();
      }

   }

   protected void onDestroy() {
      super.onDestroy();
      OnAmplifierCreateAndDestroyListener var1 = this.mOnAmplifierCreateAndDestroyListener;
      if (var1 != null) {
         var1.destroy();
      }

   }

   public void refreshUi(Bundle var1) {
      if (this.isShouldRefreshUi()) {
         LogUtil.showLog("AmplifierActivity refreshUi");
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_volume)).setProgress(GeneralAmplifierData.volume);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_high)).setProgress(GeneralAmplifierData.bandTreble);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_middle)).setProgress(GeneralAmplifierData.bandMiddle);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_low)).setProgress(GeneralAmplifierData.bandBass);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_mega_bass)).setProgress(GeneralAmplifierData.megaBass);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom)).setProgress(GeneralAmplifierData.customBass);
         ((EqSeekBarView)this._$_findCachedViewById(R.id.seek_bar_custom_2)).setProgress(GeneralAmplifierData.custom2Bass);
         LogUtil.showLog("refresh:FrontRear:" + GeneralAmplifierData.frontRear + " LeftRight:" + GeneralAmplifierData.leftRight);
         LogUtil.showLog("refresh:mFrontRearValue:" + this.mFrontRearValue + " mLeftRightValue:" + this.mLeftRightValue);
         if (this.mFrontRearValue != GeneralAmplifierData.frontRear && this.mLeftRightValue != GeneralAmplifierData.leftRight) {
            this.mFrontRearValue = GeneralAmplifierData.frontRear;
            this.mLeftRightValue = GeneralAmplifierData.leftRight;
            this.updatePointByValue();
         }

         if (this.mFrontRearValue != GeneralAmplifierData.frontRear) {
            this.mFrontRearValue = GeneralAmplifierData.frontRear;
            this.updatePointFrontRearByValue();
         }

         if (this.mLeftRightValue != GeneralAmplifierData.leftRight) {
            this.mLeftRightValue = GeneralAmplifierData.leftRight;
            this.updatePointLeftRightByValue();
         }

         if (GeneralAmplifierData.frontRear == 0) {
            this.mFrontRearValue = GeneralAmplifierData.frontRear;
            this.updatePointFrontRearByValue();
         }

         if (GeneralAmplifierData.leftRight == 0) {
            this.mLeftRightValue = GeneralAmplifierData.leftRight;
            this.updatePointLeftRightByValue();
         }

         String[] var4 = this.mLineBtnAction;
         if (var4 != null) {
            IntRange var5;
            if (var4 != null) {
               var5 = ArraysKt.getIndices(var4);
            } else {
               var5 = null;
            }

            Intrinsics.checkNotNull(var5);
            int var2 = var5.getFirst();
            int var3 = var5.getLast();
            if (var2 <= var3) {
               while(true) {
                  var4 = this.mLineBtnAction;
                  Intrinsics.checkNotNull(var4);
                  if (Intrinsics.areEqual((Object)var4[var2], (Object)"bose_center")) {
                     ((LineBtnView)this._$_findCachedViewById(R.id.lbv_top)).getBtnItemView(var2).turn(GeneralAmplifierData.bose_center_b);
                  }

                  if (var2 == var3) {
                     break;
                  }

                  ++var2;
               }
            }
         }

      }
   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0017\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017¨\u0006\u0018"},
      d2 = {"Lcom/hzbhd/canbus/activity/AmplifierActivity$AmplifierBand;", "", "(Ljava/lang/String;I)V", "VOLUME", "TREBLE", "MIDDLE", "BASS", "MEGA_BASS", "CUSTOM_BASS", "CUSTOM_2_BASS", "VOLUME_Min", "TREBLE_Min", "MIDDLE_Min", "BASS_Min", "MEGA_BASS_Min", "CUSTOM_BASS_Min", "CUSTOM_BASS_2_Min", "VOLUME_Plus", "TREBLE_Plus", "MIDDLE_Plus", "BASS_Plus", "MEGA_BASS_Plus", "CUSTOM_BASS_Plus", "CUSTOM_BASS_2_Plus", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum AmplifierBand {
      private static final AmplifierBand[] $VALUES = $values();
      BASS,
      BASS_Min,
      BASS_Plus,
      CUSTOM_2_BASS,
      CUSTOM_BASS,
      CUSTOM_BASS_2_Min,
      CUSTOM_BASS_2_Plus,
      CUSTOM_BASS_Min,
      CUSTOM_BASS_Plus,
      MEGA_BASS,
      MEGA_BASS_Min,
      MEGA_BASS_Plus,
      MIDDLE,
      MIDDLE_Min,
      MIDDLE_Plus,
      TREBLE,
      TREBLE_Min,
      TREBLE_Plus,
      VOLUME,
      VOLUME_Min,
      VOLUME_Plus;

      // $FF: synthetic method
      private static final AmplifierBand[] $values() {
         return new AmplifierBand[]{VOLUME, TREBLE, MIDDLE, BASS, MEGA_BASS, CUSTOM_BASS, CUSTOM_2_BASS, VOLUME_Min, TREBLE_Min, MIDDLE_Min, BASS_Min, MEGA_BASS_Min, CUSTOM_BASS_Min, CUSTOM_BASS_2_Min, VOLUME_Plus, TREBLE_Plus, MIDDLE_Plus, BASS_Plus, MEGA_BASS_Plus, CUSTOM_BASS_Plus, CUSTOM_BASS_2_Plus};
      }
   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"},
      d2 = {"Lcom/hzbhd/canbus/activity/AmplifierActivity$AmplifierPosition;", "", "(Ljava/lang/String;I)V", "LEFT_RIGHT", "FRONT_REAR", "LEFT", "RIGHT", "FRONT", "REAR", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum AmplifierPosition {
      private static final AmplifierPosition[] $VALUES = $values();
      FRONT,
      FRONT_REAR,
      LEFT,
      LEFT_RIGHT,
      REAR,
      RIGHT;

      // $FF: synthetic method
      private static final AmplifierPosition[] $values() {
         return new AmplifierPosition[]{LEFT_RIGHT, FRONT_REAR, LEFT, RIGHT, FRONT, REAR};
      }
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"},
      d2 = {"Lcom/hzbhd/canbus/activity/AmplifierActivity$Companion;", "", "()V", "SHARE_PRE_AMPLIFIER_INDEX", "", "SHARE_PRE_USER_BASS", "SHARE_PRE_USER_CUSTOM_BASS", "SHARE_PRE_USER_CUSTOM_BASS_2", "SHARE_PRE_USER_MEGA_BASS", "SHARE_PRE_USER_MIDDLE", "SHARE_PRE_USER_TREBLE", "SHARE_PRE_USER_VOLUME", "CanBusInfo_release"},
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
   }
}
