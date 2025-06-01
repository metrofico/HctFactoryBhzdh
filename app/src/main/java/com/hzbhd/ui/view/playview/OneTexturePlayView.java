package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\b\u0010\u0016\u001a\u00020\u0011H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J(\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\fH\u0016J \u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\fH\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020 H\u0016J \u0010%\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\fH\u0016J\u0010\u0010&\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 H\u0016R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006'"},
   d2 = {"Lcom/hzbhd/ui/view/playview/OneTexturePlayView;", "Lcom/hzbhd/ui/view/playview/PlayView;", "context", "Landroid/content/Context;", "scalePlayViewInterface", "Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "(Landroid/content/Context;Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;)V", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "textureView", "Landroid/view/TextureView;", "getTextureView", "()Landroid/view/TextureView;", "setTextureView", "(Landroid/view/TextureView;)V", "getTexureView", "initSurfaceView", "", "layoutSurface", "l", "t", "r", "b", "onSurfaceTextureAvailable", "surface", "Landroid/graphics/SurfaceTexture;", "width", "height", "onSurfaceTextureDestroyed", "", "onSurfaceTextureSizeChanged", "onSurfaceTextureUpdated", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class OneTexturePlayView extends PlayView {
   private TextureView textureView;

   public OneTexturePlayView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
   }

   public OneTexturePlayView(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
   }

   public OneTexturePlayView(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
   }

   public OneTexturePlayView(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
   }

   public OneTexturePlayView(Context var1, ScalePlayViewInterface var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "scalePlayViewInterface");
      super(var1);
      this.setScalePlayViewInterface(var2);
   }

   public final TextureView getTextureView() {
      return this.textureView;
   }

   public TextureView getTexureView() {
      if (this.textureView == null) {
         this.textureView = new TextureView(this.getContext());
      }

      TextureView var1 = this.textureView;
      Intrinsics.checkNotNull(var1);
      return var1;
   }

   public void initSurfaceView() {
      this.addView((View)this.getTexureView());
      this.getTexureView().setSurfaceTextureListener((TextureView.SurfaceTextureListener)(new TextureView.SurfaceTextureListener(this) {
         final OneTexturePlayView this$0;

         {
            this.this$0 = var1;
         }

         public void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            this.this$0.onSurfaceTextureAvailable(var1, var2, var3);
         }

         public boolean onSurfaceTextureDestroyed(SurfaceTexture var1) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            return this.this$0.onSurfaceTextureDestroyed(var1);
         }

         public void onSurfaceTextureSizeChanged(SurfaceTexture var1, int var2, int var3) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            this.this$0.onSurfaceTextureSizeChanged(var1, var2, var3);
         }

         public void onSurfaceTextureUpdated(SurfaceTexture var1) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            this.this$0.onSurfaceTextureUpdated(var1);
         }
      }));
   }

   public void layoutSurface(int var1, int var2, int var3, int var4) {
      this.getTexureView().layout(var1, var2, var3, var4);
   }

   public void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "surface");
   }

   public boolean onSurfaceTextureDestroyed(SurfaceTexture var1) {
      Intrinsics.checkNotNullParameter(var1, "surface");
      return false;
   }

   public void onSurfaceTextureSizeChanged(SurfaceTexture var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "surface");
   }

   public void onSurfaceTextureUpdated(SurfaceTexture var1) {
      Intrinsics.checkNotNullParameter(var1, "surface");
   }

   public final void setTextureView(TextureView var1) {
      this.textureView = var1;
   }
}
