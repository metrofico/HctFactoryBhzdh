package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.lifecycle.Lifecycle;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\b\u0010+\u001a\u00020\u001dH\u0002J\b\u0010,\u001a\u00020-H\u0014J\b\u0010.\u001a\u00020\u0011H\u0003J\b\u0010/\u001a\u00020\u0017H\u0003J\u0012\u00100\u001a\u0004\u0018\u00010\u00112\u0006\u00101\u001a\u00020\u001dH\u0016J\b\u00102\u001a\u00020\u0011H\u0003J\b\u00103\u001a\u00020\u0017H\u0003J\u0010\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0016J \u00108\u001a\u0002052\u0006\u00109\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fH\u0016J\u0010\u0010<\u001a\u00020\u001d2\u0006\u00109\u001a\u00020\u0017H\u0016J \u0010=\u001a\u0002052\u0006\u00109\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fH\u0016J\u0010\u0010>\u001a\u0002052\u0006\u00109\u001a\u00020\u0017H\u0016R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0013\"\u0004\b$\u0010\u0015R\u001c\u0010%\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0019\"\u0004\b'\u0010\u001bR\u001c\u0010(\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0019\"\u0004\b*\u0010\u001b¨\u0006?"},
   d2 = {"Lcom/hzbhd/ui/view/playview/ScalePlayView;", "Lcom/hzbhd/ui/view/playview/OneTexturePlayView;", "context", "Landroid/content/Context;", "scalePlayViewInterface", "Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "(Landroid/content/Context;Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;)V", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "mHardSurface", "Landroid/view/Surface;", "getMHardSurface", "()Landroid/view/Surface;", "setMHardSurface", "(Landroid/view/Surface;)V", "mHardTexture", "Landroid/graphics/SurfaceTexture;", "getMHardTexture", "()Landroid/graphics/SurfaceTexture;", "setMHardTexture", "(Landroid/graphics/SurfaceTexture;)V", "mIsSoftDecoder", "", "getMIsSoftDecoder", "()Z", "setMIsSoftDecoder", "(Z)V", "mSoftSurface", "getMSoftSurface", "setMSoftSurface", "mSoftTexture", "getMSoftTexture", "setMSoftTexture", "mSurfaceTexture", "getMSurfaceTexture", "setMSurfaceTexture", "changeSurface", "generateDefaultLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "getHardSurface", "getHardSurfaceTexture", "getPlayerSurface", "isSoftDecoder", "getSoftSurface", "getSoftSurfaceTexture", "onLifeCycleChange", "", "state", "Landroidx/lifecycle/Lifecycle$State;", "onSurfaceTextureAvailable", "surface", "width", "height", "onSurfaceTextureDestroyed", "onSurfaceTextureSizeChanged", "onSurfaceTextureUpdated", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ScalePlayView extends OneTexturePlayView {
   private Surface mHardSurface;
   private SurfaceTexture mHardTexture;
   private boolean mIsSoftDecoder;
   private Surface mSoftSurface;
   private SurfaceTexture mSoftTexture;
   private SurfaceTexture mSurfaceTexture;

   public ScalePlayView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
   }

   public ScalePlayView(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
   }

   public ScalePlayView(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
   }

   public ScalePlayView(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
   }

   public ScalePlayView(Context var1, ScalePlayViewInterface var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "scalePlayViewInterface");
      super(var1, var2);
   }

   private final boolean changeSurface() {
      if (this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && this.getTexureView().isAvailable()) {
         boolean var1;
         SurfaceTexture var2;
         if (this.mIsSoftDecoder) {
            var1 = Intrinsics.areEqual((Object)this.mSurfaceTexture, (Object)this.getSoftSurfaceTexture()) ^ true;
            if (var1) {
               this.mSurfaceTexture = this.getSoftSurfaceTexture();
            }

            if (!Intrinsics.areEqual((Object)this.getTexureView().getSurfaceTexture(), (Object)this.mSurfaceTexture)) {
               var2 = this.mSurfaceTexture;
               if (var2 != null) {
                  this.getTexureView().setSurfaceTexture(var2);
               }
            }

            return var1;
         } else {
            var1 = Intrinsics.areEqual((Object)this.mSurfaceTexture, (Object)this.getHardSurfaceTexture()) ^ true;
            if (var1) {
               this.mSurfaceTexture = this.getHardSurfaceTexture();
            }

            if (!Intrinsics.areEqual((Object)this.getTexureView().getSurfaceTexture(), (Object)this.mSurfaceTexture)) {
               var2 = this.mSurfaceTexture;
               if (var2 != null) {
                  this.getTexureView().setSurfaceTexture(var2);
               }
            }

            return var1;
         }
      } else {
         return false;
      }
   }

   private final Surface getHardSurface() {
      label17: {
         SurfaceTexture var1;
         if (this.mHardSurface != null) {
            var1 = this.mHardTexture;
            if (var1 != null) {
               Intrinsics.checkNotNull(var1);
               if (!var1.isReleased()) {
                  break label17;
               }
            }
         }

         var1 = new SurfaceTexture(false);
         this.mHardTexture = var1;
         Intrinsics.checkNotNull(var1);
         var1.setDefaultBufferSize(1024, 600);
         this.mHardSurface = new Surface(this.mHardTexture);
      }

      if (LogUtil.log5()) {
         StringBuilder var3 = (new StringBuilder()).append("getHardSurface: isRelease ");
         SurfaceTexture var2 = this.mHardTexture;
         Intrinsics.checkNotNull(var2);
         LogUtil.d(var3.append(var2.isReleased()).toString());
      }

      Surface var4 = this.mHardSurface;
      Intrinsics.checkNotNull(var4);
      return var4;
   }

   private final SurfaceTexture getHardSurfaceTexture() {
      SurfaceTexture var1;
      label11: {
         var1 = this.mHardTexture;
         if (var1 != null) {
            Intrinsics.checkNotNull(var1);
            if (!var1.isReleased()) {
               break label11;
            }
         }

         this.getHardSurface();
      }

      var1 = this.mHardTexture;
      Intrinsics.checkNotNull(var1);
      return var1;
   }

   private final Surface getSoftSurface() {
      label17: {
         SurfaceTexture var1;
         if (this.mSoftSurface != null) {
            var1 = this.mSoftTexture;
            if (var1 != null) {
               Intrinsics.checkNotNull(var1);
               if (!var1.isReleased()) {
                  break label17;
               }
            }
         }

         var1 = new SurfaceTexture(false);
         this.mSoftTexture = var1;
         Intrinsics.checkNotNull(var1);
         var1.setDefaultBufferSize(1024, 600);
         this.mSoftSurface = new Surface(this.mSoftTexture);
      }

      if (LogUtil.log5()) {
         StringBuilder var3 = (new StringBuilder()).append("getSoftSurface: isRelease ");
         SurfaceTexture var2 = this.mSoftTexture;
         Intrinsics.checkNotNull(var2);
         LogUtil.d(var3.append(var2.isReleased()).toString());
      }

      Surface var4 = this.mSoftSurface;
      Intrinsics.checkNotNull(var4);
      return var4;
   }

   private final SurfaceTexture getSoftSurfaceTexture() {
      SurfaceTexture var1;
      label11: {
         var1 = this.mSoftTexture;
         if (var1 != null) {
            Intrinsics.checkNotNull(var1);
            if (!var1.isReleased()) {
               break label11;
            }
         }

         this.getSoftSurface();
      }

      var1 = this.mSoftTexture;
      Intrinsics.checkNotNull(var1);
      return var1;
   }

   protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
      return (ViewGroup.LayoutParams)(new LayoutParams(-1, -1));
   }

   public final Surface getMHardSurface() {
      return this.mHardSurface;
   }

   public final SurfaceTexture getMHardTexture() {
      return this.mHardTexture;
   }

   public final boolean getMIsSoftDecoder() {
      return this.mIsSoftDecoder;
   }

   public final Surface getMSoftSurface() {
      return this.mSoftSurface;
   }

   public final SurfaceTexture getMSoftTexture() {
      return this.mSoftTexture;
   }

   public final SurfaceTexture getMSurfaceTexture() {
      return this.mSurfaceTexture;
   }

   public Surface getPlayerSurface(boolean var1) {
      this.mIsSoftDecoder = var1;
      Surface var2;
      if (var1) {
         this.mSoftTexture = null;
         var2 = this.getSoftSurface();
         this.changeSurface();
         return var2;
      } else {
         this.mHardTexture = null;
         var2 = this.getHardSurface();
         this.changeSurface();
         return var2;
      }
   }

   public void onLifeCycleChange(Lifecycle.State var1) {
      Intrinsics.checkNotNullParameter(var1, "state");
      super.onLifeCycleChange(var1);
      boolean var2 = this.changeSurface();
      if (LogUtil.log5()) {
         LogUtil.d("onLifeCycleChange: " + var1 + " , changeSurface = " + var2);
      }

      if (var1 == Lifecycle.State.RESUMED) {
         if (var2) {
            ScalePlayViewInterface var3 = this.getScalePlayViewInterface();
            if (var3 != null) {
               var3.requestSurface();
            }
         }
      } else {
         SurfaceTexture var4 = this.mSurfaceTexture;
         if (var4 != null) {
            var4.release();
         }

         this.mSurfaceTexture = null;
      }

   }

   public void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "surface");
      boolean var4 = this.changeSurface();
      if (LogUtil.log5()) {
         LogUtil.d("onSurfaceTextureAvailable: " + var4);
      }

      if (var4) {
         ScalePlayViewInterface var5 = this.getScalePlayViewInterface();
         if (var5 != null) {
            var5.requestSurface();
         }
      }

      SystemProperties.set("vendor.video.play.withoutdisplay", "false");
   }

   public boolean onSurfaceTextureDestroyed(SurfaceTexture var1) {
      Intrinsics.checkNotNullParameter(var1, "surface");
      if (LogUtil.log5()) {
         LogUtil.d("[playSurfaceView:onSurfaceTextureDestroyed]:");
      }

      SystemProperties.set("vendor.video.play.withoutdisplay", "true");
      return false;
   }

   public void onSurfaceTextureSizeChanged(SurfaceTexture var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "surface");
      if (LogUtil.log5()) {
         LogUtil.d("onSurfaceTextureSizeChanged: ");
      }

   }

   public void onSurfaceTextureUpdated(SurfaceTexture var1) {
      Intrinsics.checkNotNullParameter(var1, "surface");
   }

   public final void setMHardSurface(Surface var1) {
      this.mHardSurface = var1;
   }

   public final void setMHardTexture(SurfaceTexture var1) {
      this.mHardTexture = var1;
   }

   public final void setMIsSoftDecoder(boolean var1) {
      this.mIsSoftDecoder = var1;
   }

   public final void setMSoftSurface(Surface var1) {
      this.mSoftSurface = var1;
   }

   public final void setMSoftTexture(SurfaceTexture var1) {
      this.mSoftTexture = var1;
   }

   public final void setMSurfaceTexture(SurfaceTexture var1) {
      this.mSurfaceTexture = var1;
   }
}
