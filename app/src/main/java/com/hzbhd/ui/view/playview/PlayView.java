package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.ui.view.lifecycle.BaseLifeRelativeLayout;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\b&\u0018\u00002\u00020\u0001:\u0003STUB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\b\u00107\u001a\u000208H\u0002J\b\u00109\u001a\u00020:H\u0014J\u0012\u0010;\u001a\u0004\u0018\u00010<2\u0006\u0010=\u001a\u00020\u001dH&J\b\u0010>\u001a\u00020?H&J\u0010\u0010@\u001a\u0002082\u0006\u0010A\u001a\u00020\u0012H\u0016J\b\u0010B\u001a\u000208H\u0016J\b\u0010C\u001a\u000208H&J(\u0010D\u001a\u0002082\u0006\u0010E\u001a\u00020\f2\u0006\u0010F\u001a\u00020\f2\u0006\u0010G\u001a\u00020\f2\u0006\u0010H\u001a\u00020\fH&J0\u0010I\u001a\u0002082\u0006\u0010J\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020\f2\u0006\u0010F\u001a\u00020\f2\u0006\u0010G\u001a\u00020\f2\u0006\u0010H\u001a\u00020\fH\u0014J\u0018\u0010K\u001a\u0002082\u0006\u0010L\u001a\u00020\f2\u0006\u0010M\u001a\u00020\fH\u0014J\u0006\u0010N\u001a\u000208J\u0010\u0010O\u001a\u0002082\u0006\u0010P\u001a\u00020\u001dH\u0016J\u0016\u0010Q\u001a\u0002082\u0006\u00104\u001a\u00020\f2\u0006\u00101\u001a\u00020\fJ\u0010\u0010R\u001a\u0002082\u0006\u0010A\u001a\u00020\u0012H\u0016R!\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R\u001a\u00101\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010+\"\u0004\b3\u0010-R\u001a\u00104\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010+\"\u0004\b6\u0010-¨\u0006V"},
   d2 = {"Lcom/hzbhd/ui/view/playview/PlayView;", "Lcom/hzbhd/ui/view/lifecycle/BaseLifeRelativeLayout;", "context", "Landroid/content/Context;", "scalePlayViewInterface", "Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "(Landroid/content/Context;Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;)V", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "coverReasonArray", "Ljava/util/ArrayList;", "Lcom/hzbhd/ui/view/playview/PlayView$CoverReason;", "Lkotlin/collections/ArrayList;", "getCoverReasonArray", "()Ljava/util/ArrayList;", "coverView", "Landroid/view/View;", "getCoverView", "()Landroid/view/View;", "setCoverView", "(Landroid/view/View;)V", "value", "", "fullscreen", "getFullscreen", "()Z", "setFullscreen", "(Z)V", "hideCoverDelayRunnable", "Ljava/lang/Runnable;", "getScalePlayViewInterface", "()Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "setScalePlayViewInterface", "(Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;)V", "showHeight", "getShowHeight", "()I", "setShowHeight", "(I)V", "showWidth", "getShowWidth", "setShowWidth", "videoHeight", "getVideoHeight", "setVideoHeight", "videoWidth", "getVideoWidth", "setVideoWidth", "calculateShow", "", "generateDefaultLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "getPlayerSurface", "Landroid/view/Surface;", "isSoftDecoder", "getTexureView", "Landroid/view/TextureView;", "hideCover", "coverReason", "initCoverView", "initSurfaceView", "layoutSurface", "l", "t", "r", "b", "onLayout", "changed", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "reSizeDisplay", "refreshCover", "cover", "setVideoSize", "showCover", "CoverReason", "DISPLAY_SCALE", "ScalePlayViewInterface", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class PlayView extends BaseLifeRelativeLayout {
   private final ArrayList coverReasonArray;
   private View coverView;
   private boolean fullscreen;
   private final Runnable hideCoverDelayRunnable;
   private ScalePlayViewInterface scalePlayViewInterface;
   private int showHeight;
   private int showWidth;
   private int videoHeight;
   private int videoWidth;

   // $FF: synthetic method
   public static void $r8$lambda$9XN_UyRHHAsYmvr1BuQOR1omuvg(PlayView var0) {
      hideCoverDelayRunnable$lambda_0(var0);
   }

   public PlayView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.coverReasonArray = new ArrayList();
      this.coverView = (View)(new View(this.getContext()) {
         {
            this.setBackgroundColor(-16777216);
         }
      });
      this.initSurfaceView();
      this.initCoverView();
      ScalePlayViewInterface var4 = this.scalePlayViewInterface;
      byte var3 = 0;
      int var2;
      if (var4 != null) {
         var2 = var4.getVideoWidth();
      } else {
         var2 = 0;
      }

      this.videoWidth = var2;
      var4 = this.scalePlayViewInterface;
      var2 = var3;
      if (var4 != null) {
         var2 = var4.getVideoHeight();
      }

      this.videoHeight = var2;
      this.hideCoverDelayRunnable = new PlayView$$ExternalSyntheticLambda0(this);
   }

   public PlayView(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
      this.coverReasonArray = new ArrayList();
      this.coverView = (View)(new View(this.getContext()) {
         {
            this.setBackgroundColor(-16777216);
         }
      });
      this.initSurfaceView();
      this.initCoverView();
      ScalePlayViewInterface var5 = this.scalePlayViewInterface;
      byte var4 = 0;
      int var3;
      if (var5 != null) {
         var3 = var5.getVideoWidth();
      } else {
         var3 = 0;
      }

      this.videoWidth = var3;
      var5 = this.scalePlayViewInterface;
      var3 = var4;
      if (var5 != null) {
         var3 = var5.getVideoHeight();
      }

      this.videoHeight = var3;
      this.hideCoverDelayRunnable = new PlayView$$ExternalSyntheticLambda0(this);
   }

   public PlayView(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
      this.coverReasonArray = new ArrayList();
      this.coverView = (View)(new View(this.getContext()) {
         {
            this.setBackgroundColor(-16777216);
         }
      });
      this.initSurfaceView();
      this.initCoverView();
      ScalePlayViewInterface var5 = this.scalePlayViewInterface;
      byte var4 = 0;
      if (var5 != null) {
         var3 = var5.getVideoWidth();
      } else {
         var3 = 0;
      }

      this.videoWidth = var3;
      var5 = this.scalePlayViewInterface;
      var3 = var4;
      if (var5 != null) {
         var3 = var5.getVideoHeight();
      }

      this.videoHeight = var3;
      this.hideCoverDelayRunnable = new PlayView$$ExternalSyntheticLambda0(this);
   }

   public PlayView(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
      this.coverReasonArray = new ArrayList();
      this.coverView = (View)(new View(this.getContext()) {
         {
            this.setBackgroundColor(-16777216);
         }
      });
      this.initSurfaceView();
      this.initCoverView();
      ScalePlayViewInterface var5 = this.scalePlayViewInterface;
      byte var6 = 0;
      if (var5 != null) {
         var3 = var5.getVideoWidth();
      } else {
         var3 = 0;
      }

      this.videoWidth = var3;
      var5 = this.scalePlayViewInterface;
      var3 = var6;
      if (var5 != null) {
         var3 = var5.getVideoHeight();
      }

      this.videoHeight = var3;
      this.hideCoverDelayRunnable = new PlayView$$ExternalSyntheticLambda0(this);
   }

   public PlayView(Context var1, ScalePlayViewInterface var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "scalePlayViewInterface");
      super(var1);
      this.coverReasonArray = new ArrayList();
      this.coverView = (View)(new View(this.getContext()) {
         {
            this.setBackgroundColor(-16777216);
         }
      });
      this.initSurfaceView();
      this.initCoverView();
      ScalePlayViewInterface var5 = this.scalePlayViewInterface;
      byte var4 = 0;
      int var3;
      if (var5 != null) {
         var3 = var5.getVideoWidth();
      } else {
         var3 = 0;
      }

      this.videoWidth = var3;
      var5 = this.scalePlayViewInterface;
      var3 = var4;
      if (var5 != null) {
         var3 = var5.getVideoHeight();
      }

      this.videoHeight = var3;
      this.hideCoverDelayRunnable = new PlayView$$ExternalSyntheticLambda0(this);
      this.scalePlayViewInterface = var2;
   }

   private final void calculateShow() {
      this.showWidth = this.getMeasuredWidth();
      this.showHeight = this.getMeasuredHeight();
      if (!this.fullscreen) {
         ScalePlayViewInterface var3 = this.scalePlayViewInterface;
         DISPLAY_SCALE var4;
         if (var3 != null) {
            var4 = var3.getDisplayScale();
         } else {
            var4 = null;
         }

         int var1;
         if (var4 == null) {
            var1 = -1;
         } else {
            var1 = WhenMappings.$EnumSwitchMapping$0[var4.ordinal()];
         }

         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     label56: {
                        if (LogUtil.log5()) {
                           LogUtil.d("calculateShow: ORIGINAL = " + this.videoWidth + ',' + this.videoHeight);
                        }

                        int var2 = this.videoWidth;
                        if (var2 != 0) {
                           var1 = this.videoHeight;
                           if (var1 != 0) {
                              this.showWidth = var2;
                              this.showHeight = var1;
                              break label56;
                           }
                        }

                        this.showWidth = this.getMeasuredWidth();
                        this.showHeight = this.getMeasuredHeight();
                     }
                  }
               } else if (this.getMeasuredHeight() * 4 / 3 > this.getMeasuredWidth()) {
                  this.showWidth = this.getMeasuredWidth();
                  this.showHeight = this.getMeasuredWidth() * 3 / 4;
               } else {
                  this.showWidth = this.getMeasuredHeight() * 4 / 3;
                  this.showHeight = this.getMeasuredHeight();
               }
            } else if (this.getMeasuredHeight() * 16 / 9 > this.getMeasuredWidth()) {
               this.showWidth = this.getMeasuredWidth();
               this.showHeight = this.getMeasuredWidth() * 9 / 16;
            } else {
               this.showWidth = this.getMeasuredHeight() * 16 / 9;
               this.showHeight = this.getMeasuredHeight();
            }
         } else {
            this.showWidth = this.getMeasuredWidth();
            this.showHeight = this.getMeasuredHeight();
         }

         if (LogUtil.log3()) {
            LogUtil.d("calculateShow: measure: " + this.getMeasuredWidth() + " , " + this.getMeasuredHeight() + " show " + this.showWidth + " , " + this.showHeight);
         }

      }
   }

   private static final void hideCoverDelayRunnable$lambda_0(PlayView var0) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.hideCover(CoverReason.DELAY);
   }

   protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
      return (ViewGroup.LayoutParams)(new LayoutParams(-1, -1));
   }

   public final ArrayList getCoverReasonArray() {
      return this.coverReasonArray;
   }

   public final View getCoverView() {
      return this.coverView;
   }

   public final boolean getFullscreen() {
      return this.fullscreen;
   }

   public abstract Surface getPlayerSurface(boolean var1);

   public final ScalePlayViewInterface getScalePlayViewInterface() {
      return this.scalePlayViewInterface;
   }

   public final int getShowHeight() {
      return this.showHeight;
   }

   public final int getShowWidth() {
      return this.showWidth;
   }

   public abstract TextureView getTexureView();

   public final int getVideoHeight() {
      return this.videoHeight;
   }

   public final int getVideoWidth() {
      return this.videoWidth;
   }

   public void hideCover(CoverReason var1) {
      Intrinsics.checkNotNullParameter(var1, "coverReason");
      this.coverReasonArray.remove(var1);
      this.refreshCover(this.coverReasonArray.isEmpty() ^ true);
      if (LogUtil.log5()) {
         StringBuilder var2 = (new StringBuilder()).append("hideCover:-- ").append(var1).append(" :: ");
         String var3 = Arrays.toString(this.coverReasonArray.toArray());
         Intrinsics.checkNotNullExpressionValue(var3, "toString(this)");
         LogUtil.d(var2.append(var3).toString());
      }

   }

   public void initCoverView() {
      this.addView(this.coverView);
   }

   public abstract void initSurfaceView();

   public abstract void layoutSurface(int var1, int var2, int var3, int var4);

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      int var10 = Math.abs(var2 - var4);
      int var8 = Math.abs(var3 - var5);
      int var9 = this.showWidth;
      int var7 = var2;
      int var6 = var4;
      if (var9 < var10) {
         var7 = var2 + (var10 - var9) / 2;
         var6 = var4 - (var10 - var9) / 2;
      }

      var9 = this.showHeight;
      var4 = var3;
      var2 = var5;
      if (var9 < var8) {
         var4 = var3 + (var8 - var9) / 2;
         var2 = var5 - (var8 - var9) / 2;
      }

      this.layoutSurface(var7, var4, var6, var2);
      this.coverView.layout(var7, var4, var6, var2);
   }

   protected void onMeasure(int var1, int var2) {
      super.onMeasure(var1, var2);
      this.calculateShow();
   }

   public final void reSizeDisplay() {
      if (LogUtil.log5()) {
         LogUtil.d("fullscreen: " + this.fullscreen);
      }

      this.calculateShow();
      BaseUtil.INSTANCE.runUi((Function0)(new Function0(this) {
         final PlayView this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            this.this$0.requestLayout();
         }
      }));
   }

   public void refreshCover(boolean var1) {
      if (var1) {
         BaseUtil.INSTANCE.runUi((Function0)(new Function0(this) {
            final PlayView this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke() {
               this.this$0.getCoverView().setVisibility(0);
            }
         }));
      } else {
         BaseUtil.INSTANCE.runUi((Function0)(new Function0(this) {
            final PlayView this$0;

            {
               this.this$0 = var1;
            }

            public final void invoke() {
               this.this$0.getCoverView().setVisibility(8);
            }
         }));
      }

   }

   public final void setCoverView(View var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.coverView = var1;
   }

   public final void setFullscreen(boolean var1) {
      this.fullscreen = var1;
      this.reSizeDisplay();
   }

   public final void setScalePlayViewInterface(ScalePlayViewInterface var1) {
      this.scalePlayViewInterface = var1;
   }

   public final void setShowHeight(int var1) {
      this.showHeight = var1;
   }

   public final void setShowWidth(int var1) {
      this.showWidth = var1;
   }

   public final void setVideoHeight(int var1) {
      this.videoHeight = var1;
   }

   public final void setVideoSize(int var1, int var2) {
      if (LogUtil.log5()) {
         LogUtil.d("setVideoSize: " + var1 + " , " + var2);
      }

      this.videoWidth = var1;
      this.videoHeight = var2;
      this.calculateShow();
      BaseUtil.INSTANCE.runUi((Function0)(new Function0(this) {
         final PlayView this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            this.this$0.requestLayout();
         }
      }));
   }

   public final void setVideoWidth(int var1) {
      this.videoWidth = var1;
   }

   public void showCover(CoverReason var1) {
      Intrinsics.checkNotNullParameter(var1, "coverReason");
      if (var1 != CoverReason.DELAY) {
         this.showCover(CoverReason.DELAY);
         this.removeCallbacks(this.hideCoverDelayRunnable);
         this.postDelayed(this.hideCoverDelayRunnable, 1000L);
      }

      if (!this.coverReasonArray.contains(var1)) {
         this.coverReasonArray.add(var1);
      }

      this.refreshCover(this.coverReasonArray.isEmpty() ^ true);
      if (LogUtil.log5()) {
         LogUtil.d("showCover:-- " + var1);
      }

   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"},
      d2 = {"Lcom/hzbhd/ui/view/playview/PlayView$CoverReason;", "", "(Ljava/lang/String;I)V", "RELEASE", "DELAY", "CLICK", "commonview-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum CoverReason {
      private static final CoverReason[] $VALUES = $values();
      CLICK,
      DELAY,
      RELEASE;

      // $FF: synthetic method
      private static final CoverReason[] $values() {
         return new CoverReason[]{RELEASE, DELAY, CLICK};
      }
   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"},
      d2 = {"Lcom/hzbhd/ui/view/playview/PlayView$DISPLAY_SCALE;", "", "(Ljava/lang/String;I)V", "FULL_SCREEN", "_4_3", "_16_9", "ORIGINAL", "commonview-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum DISPLAY_SCALE {
      private static final DISPLAY_SCALE[] $VALUES = $values();
      FULL_SCREEN,
      ORIGINAL,
      _16_9,
      _4_3;

      // $FF: synthetic method
      private static final DISPLAY_SCALE[] $values() {
         return new DISPLAY_SCALE[]{FULL_SCREEN, _4_3, _16_9, ORIGINAL};
      }
   }

   @Metadata(
      d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\bH&¨\u0006\t"},
      d2 = {"Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "", "getDisplayScale", "Lcom/hzbhd/ui/view/playview/PlayView$DISPLAY_SCALE;", "getVideoHeight", "", "getVideoWidth", "requestSurface", "", "commonview-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public interface ScalePlayViewInterface {
      DISPLAY_SCALE getDisplayScale();

      int getVideoHeight();

      int getVideoWidth();

      void requestSurface();
   }

   @Metadata(
      k = 3,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class WhenMappings {
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[DISPLAY_SCALE.values().length];
         var0[DISPLAY_SCALE.FULL_SCREEN.ordinal()] = 1;
         var0[DISPLAY_SCALE._16_9.ordinal()] = 2;
         var0[DISPLAY_SCALE._4_3.ordinal()] = 3;
         var0[DISPLAY_SCALE.ORIGINAL.ordinal()] = 4;
         $EnumSwitchMapping$0 = var0;
      }
   }
}
