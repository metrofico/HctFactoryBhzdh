package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\b\u0010+\u001a\u00020,H\u0002J\u0006\u0010-\u001a\u00020\u0011J\u0012\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u00020\u001dH\u0016J\u0006\u00101\u001a\u00020\u0011J\b\u00102\u001a\u00020\u0011H\u0016J\b\u00103\u001a\u00020,H\u0016J\b\u00104\u001a\u00020,H\u0016J(\u00105\u001a\u00020,2\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u00020\f2\u0006\u00109\u001a\u00020\fH\u0016J\u0010\u0010:\u001a\u00020,2\u0006\u0010;\u001a\u00020<H\u0016J\u0010\u0010=\u001a\u00020,2\u0006\u0010>\u001a\u00020\u001dH\u0016R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010\u001bR\u001c\u0010%\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015R\u001a\u0010(\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001f\"\u0004\b*\u0010!¨\u0006?"},
   d2 = {"Lcom/hzbhd/ui/view/playview/DoubleTexturePlayView;", "Lcom/hzbhd/ui/view/playview/PlayView;", "context", "Landroid/content/Context;", "scalePlayViewInterface", "Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "(Landroid/content/Context;Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;)V", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "hardTextureView", "Landroid/view/TextureView;", "getHardTextureView", "()Landroid/view/TextureView;", "setHardTextureView", "(Landroid/view/TextureView;)V", "mHardSurfaceTexture", "Landroid/graphics/SurfaceTexture;", "getMHardSurfaceTexture", "()Landroid/graphics/SurfaceTexture;", "setMHardSurfaceTexture", "(Landroid/graphics/SurfaceTexture;)V", "mIsSoftDecoder", "", "getMIsSoftDecoder", "()Z", "setMIsSoftDecoder", "(Z)V", "mSoftSurfaceTexture", "getMSoftSurfaceTexture", "setMSoftSurfaceTexture", "softTextureView", "getSoftTextureView", "setSoftTextureView", "textureVisible", "getTextureVisible", "setTextureVisible", "checkTextureView", "", "getHardTexureView", "getPlayerSurface", "Landroid/view/Surface;", "isSoftDecoder", "getSoftTexureView", "getTexureView", "initCoverView", "initSurfaceView", "layoutSurface", "l", "t", "r", "b", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "refreshCover", "cover", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class DoubleTexturePlayView extends PlayView {
   private TextureView hardTextureView;
   private SurfaceTexture mHardSurfaceTexture;
   private boolean mIsSoftDecoder;
   private SurfaceTexture mSoftSurfaceTexture;
   private TextureView softTextureView;
   private boolean textureVisible;

   public DoubleTexturePlayView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      SystemProperties.set("vendor.video.play.withoutdisplay", "true");
   }

   public DoubleTexturePlayView(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
      SystemProperties.set("vendor.video.play.withoutdisplay", "true");
   }

   public DoubleTexturePlayView(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
      SystemProperties.set("vendor.video.play.withoutdisplay", "true");
   }

   public DoubleTexturePlayView(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
      SystemProperties.set("vendor.video.play.withoutdisplay", "true");
   }

   public DoubleTexturePlayView(Context var1, ScalePlayViewInterface var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "scalePlayViewInterface");
      super(var1);
      SystemProperties.set("vendor.video.play.withoutdisplay", "true");
      this.setScalePlayViewInterface(var2);
   }

   private final void checkTextureView() {
      if (this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
         if (this.mIsSoftDecoder) {
            if (this.getSoftTexureView().getParent() == null) {
               this.addView((View)this.getSoftTexureView());
            }
         } else if (this.getHardTexureView().getParent() == null) {
            this.addView((View)this.getHardTexureView());
         }
      }

   }

   public final TextureView getHardTextureView() {
      return this.hardTextureView;
   }

   public final TextureView getHardTexureView() {
      if (this.hardTextureView == null) {
         this.hardTextureView = new TextureView(this.getContext());
      }

      TextureView var1 = this.hardTextureView;
      Intrinsics.checkNotNull(var1);
      return var1;
   }

   public final SurfaceTexture getMHardSurfaceTexture() {
      return this.mHardSurfaceTexture;
   }

   public final boolean getMIsSoftDecoder() {
      return this.mIsSoftDecoder;
   }

   public final SurfaceTexture getMSoftSurfaceTexture() {
      return this.mSoftSurfaceTexture;
   }

   public Surface getPlayerSurface(boolean var1) {
      this.mIsSoftDecoder = var1;
      if (LogUtil.log5()) {
         LogUtil.d("getPlayerSurface: isSoftDecoder " + var1 + " textureVisible " + this.textureVisible + " lifecycle.currentState " + this.getLifecycle().getCurrentState());
      }

      if (this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && this.textureVisible) {
         boolean var3 = true;
         boolean var2 = true;
         TextureView var4;
         StringBuilder var5;
         Boolean var6;
         if (var1) {
            if (LogUtil.log5()) {
               var5 = (new StringBuilder()).append("getPlayerSurface: softTextureView?.isAvailable ");
               var4 = this.softTextureView;
               if (var4 != null) {
                  var6 = var4.isAvailable();
               } else {
                  var6 = null;
               }

               LogUtil.d(var5.append(var6).toString());
            }

            var4 = this.softTextureView;
            if (var4 == null || !var4.isAvailable()) {
               var2 = false;
            }

            if (var2) {
               return new Surface(this.mSoftSurfaceTexture);
            }
         } else {
            if (LogUtil.log5()) {
               var5 = (new StringBuilder()).append("getPlayerSurface: hardTextureView?.isAvailable ");
               var4 = this.hardTextureView;
               if (var4 != null) {
                  var6 = var4.isAvailable();
               } else {
                  var6 = null;
               }

               LogUtil.d(var5.append(var6).toString());
            }

            var4 = this.hardTextureView;
            if (var4 != null && var4.isAvailable()) {
               var2 = var3;
            } else {
               var2 = false;
            }

            if (var2) {
               return new Surface(this.mHardSurfaceTexture);
            }
         }
      }

      return null;
   }

   public final TextureView getSoftTextureView() {
      return this.softTextureView;
   }

   public final TextureView getSoftTexureView() {
      if (this.softTextureView == null) {
         this.softTextureView = new TextureView(this.getContext());
      }

      TextureView var1 = this.softTextureView;
      Intrinsics.checkNotNull(var1);
      return var1;
   }

   public final boolean getTextureVisible() {
      return this.textureVisible;
   }

   public TextureView getTexureView() {
      TextureView var1;
      if (this.mIsSoftDecoder) {
         var1 = this.getSoftTexureView();
      } else {
         var1 = this.getHardTexureView();
      }

      return var1;
   }

   public void initCoverView() {
   }

   public void initSurfaceView() {
      this.getSoftTexureView().setSurfaceTextureListener((TextureView.SurfaceTextureListener)(new TextureView.SurfaceTextureListener(this) {
         final DoubleTexturePlayView this$0;

         {
            this.this$0 = var1;
         }

         public void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            boolean var4 = SystemProperties.getBoolean("vendor.video.play.withoutdisplay", true);
            if (LogUtil.log5()) {
               LogUtil.d("onSurfaceTextureAvailable:withoutdisplay " + var4 + "  " + this.this$0.getMSoftSurfaceTexture());
            }

            this.this$0.setTextureVisible(true);
            if (!Intrinsics.areEqual((Object)this.this$0.getMSoftSurfaceTexture(), (Object)var1)) {
               this.this$0.setMSoftSurfaceTexture(var1);
               ScalePlayViewInterface var5 = this.this$0.getScalePlayViewInterface();
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
            this.this$0.setTextureVisible(false);
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
      }));
      this.getHardTexureView().setSurfaceTextureListener((TextureView.SurfaceTextureListener)(new TextureView.SurfaceTextureListener(this) {
         final DoubleTexturePlayView this$0;

         {
            this.this$0 = var1;
         }

         public void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            this.this$0.setTextureVisible(true);
            SystemProperties.getBoolean("vendor.video.play.withoutdisplay", true);
            if (LogUtil.log5()) {
               LogUtil.d("onSurfaceTextureAvailable: " + this.this$0.getMHardSurfaceTexture());
            }

            if (!Intrinsics.areEqual((Object)this.this$0.getMHardSurfaceTexture(), (Object)var1)) {
               this.this$0.setMHardSurfaceTexture(var1);
               ScalePlayViewInterface var4 = this.this$0.getScalePlayViewInterface();
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
            this.this$0.setTextureVisible(false);
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
      }));
   }

   public void layoutSurface(int var1, int var2, int var3, int var4) {
      this.getSoftTexureView().layout(var1, var2, var3, var4);
      this.getHardTexureView().layout(var1, var2, var3, var4);
   }

   public void onLifeCycleChange(Lifecycle.State var1) {
      Intrinsics.checkNotNullParameter(var1, "state");
      super.onLifeCycleChange(var1);
      if (LogUtil.log5()) {
         LogUtil.d("onLifeCycleChange: " + var1);
      }

      if (var1 == Lifecycle.State.RESUMED) {
         this.checkTextureView();
      } else {
         this.removeAllViews();
      }

   }

   public void refreshCover(boolean var1) {
      if (LogUtil.log5()) {
         LogUtil.d("refreshCover: " + var1);
      }

      BaseUtil.INSTANCE.runUi((Function0)(new Function0(var1, this) {
         final boolean $cover;
         final DoubleTexturePlayView this$0;

         {
            this.$cover = var1;
            this.this$0 = var2;
         }

         public final void invoke() {
            if (this.$cover) {
               this.this$0.removeAllViews();
            } else {
               this.this$0.checkTextureView();
            }

         }
      }));
   }

   public final void setHardTextureView(TextureView var1) {
      this.hardTextureView = var1;
   }

   public final void setMHardSurfaceTexture(SurfaceTexture var1) {
      this.mHardSurfaceTexture = var1;
   }

   public final void setMIsSoftDecoder(boolean var1) {
      this.mIsSoftDecoder = var1;
   }

   public final void setMSoftSurfaceTexture(SurfaceTexture var1) {
      this.mSoftSurfaceTexture = var1;
   }

   public final void setSoftTextureView(TextureView var1) {
      this.softTextureView = var1;
   }

   public final void setTextureVisible(boolean var1) {
      this.textureVisible = var1;
   }
}
