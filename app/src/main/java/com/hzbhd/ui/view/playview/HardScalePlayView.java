package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.view.Surface;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J \u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0016J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0011H\u0016J \u0010 \u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0016J\u0010\u0010!\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0011H\u0016R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\""},
   d2 = {"Lcom/hzbhd/ui/view/playview/HardScalePlayView;", "Lcom/hzbhd/ui/view/playview/OneTexturePlayView;", "context", "Landroid/content/Context;", "scalePlayViewInterface", "Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "(Landroid/content/Context;Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;)V", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "mSurfaceTexture", "Landroid/graphics/SurfaceTexture;", "getMSurfaceTexture", "()Landroid/graphics/SurfaceTexture;", "setMSurfaceTexture", "(Landroid/graphics/SurfaceTexture;)V", "getPlayerSurface", "Landroid/view/Surface;", "isSoftDecoder", "", "onSurfaceTextureAvailable", "", "surface", "width", "height", "onSurfaceTextureDestroyed", "onSurfaceTextureSizeChanged", "onSurfaceTextureUpdated", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class HardScalePlayView extends OneTexturePlayView {
   private SurfaceTexture mSurfaceTexture;

   public HardScalePlayView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
   }

   public HardScalePlayView(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
   }

   public HardScalePlayView(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
   }

   public HardScalePlayView(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
   }

   public HardScalePlayView(Context var1, ScalePlayViewInterface var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "scalePlayViewInterface");
      super(var1, var2);
   }

   public final SurfaceTexture getMSurfaceTexture() {
      return this.mSurfaceTexture;
   }

   public Surface getPlayerSurface(boolean var1) {
      if (LogUtil.log5()) {
         LogUtil.d("getPlayerSurface: " + this.mSurfaceTexture);
      }

      return new Surface(this.mSurfaceTexture);
   }

   public void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "surface");
      if (LogUtil.log5()) {
         LogUtil.d("onSurfaceTextureAvailable:");
      }

      if (!Intrinsics.areEqual((Object)this.mSurfaceTexture, (Object)var1)) {
         this.mSurfaceTexture = var1;
         ScalePlayViewInterface var4 = this.getScalePlayViewInterface();
         if (var4 != null) {
            var4.requestSurface();
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

   public final void setMSurfaceTexture(SurfaceTexture var1) {
      this.mSurfaceTexture = var1;
   }
}
