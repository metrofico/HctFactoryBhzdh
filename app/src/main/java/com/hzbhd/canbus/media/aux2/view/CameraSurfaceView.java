package com.hzbhd.canbus.media.aux2.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import com.hzbhd.ui.view.lifecycle.BaseLifeFrameLayout;
import com.hzbhd.util.LogUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0016H\u0016J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH&J\b\u0010\u001d\u001a\u00020\u0016H&R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"},
   d2 = {"Lcom/hzbhd/canbus/media/aux2/view/CameraSurfaceView;", "Lcom/hzbhd/ui/view/lifecycle/BaseLifeFrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "mSurface", "Landroid/view/Surface;", "getMSurface", "()Landroid/view/Surface;", "setMSurface", "(Landroid/view/Surface;)V", "textureView", "Landroid/view/TextureView;", "clearSurface", "", "surface", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "refreshPreView", "startPreview", "stopPreview", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class CameraSurfaceView extends BaseLifeFrameLayout {
   public Map _$_findViewCache;
   private Surface mSurface;
   private TextureView textureView;

   public CameraSurfaceView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1);
      TextureView var2 = new TextureView(this.getContext());
      this.textureView = var2;
      this.addView((View)var2);
      this.textureView.setSurfaceTextureListener((TextureView.SurfaceTextureListener)(new TextureView.SurfaceTextureListener(this) {
         final CameraSurfaceView this$0;

         {
            this.this$0 = var1;
         }

         public void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            this.this$0.setMSurface(new Surface(var1));
            this.this$0.refreshPreView();
         }

         public boolean onSurfaceTextureDestroyed(SurfaceTexture var1) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            this.this$0.setMSurface((Surface)null);
            this.this$0.refreshPreView();
            return true;
         }

         public void onSurfaceTextureSizeChanged(SurfaceTexture var1, int var2, int var3) {
            Intrinsics.checkNotNullParameter(var1, "surface");
         }

         public void onSurfaceTextureUpdated(SurfaceTexture var1) {
            Intrinsics.checkNotNullParameter(var1, "surface");
         }
      }));
   }

   public CameraSurfaceView(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1, var2);
      TextureView var3 = new TextureView(this.getContext());
      this.textureView = var3;
      this.addView((View)var3);
      this.textureView.setSurfaceTextureListener((TextureView.SurfaceTextureListener)(new TextureView.SurfaceTextureListener(this) {
         final CameraSurfaceView this$0;

         {
            this.this$0 = var1;
         }

         public void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            this.this$0.setMSurface(new Surface(var1));
            this.this$0.refreshPreView();
         }

         public boolean onSurfaceTextureDestroyed(SurfaceTexture var1) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            this.this$0.setMSurface((Surface)null);
            this.this$0.refreshPreView();
            return true;
         }

         public void onSurfaceTextureSizeChanged(SurfaceTexture var1, int var2, int var3) {
            Intrinsics.checkNotNullParameter(var1, "surface");
         }

         public void onSurfaceTextureUpdated(SurfaceTexture var1) {
            Intrinsics.checkNotNullParameter(var1, "surface");
         }
      }));
   }

   public CameraSurfaceView(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1, var2, var3);
      TextureView var4 = new TextureView(this.getContext());
      this.textureView = var4;
      this.addView((View)var4);
      this.textureView.setSurfaceTextureListener((TextureView.SurfaceTextureListener)(new TextureView.SurfaceTextureListener(this) {
         final CameraSurfaceView this$0;

         {
            this.this$0 = var1;
         }

         public void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            this.this$0.setMSurface(new Surface(var1));
            this.this$0.refreshPreView();
         }

         public boolean onSurfaceTextureDestroyed(SurfaceTexture var1) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            this.this$0.setMSurface((Surface)null);
            this.this$0.refreshPreView();
            return true;
         }

         public void onSurfaceTextureSizeChanged(SurfaceTexture var1, int var2, int var3) {
            Intrinsics.checkNotNullParameter(var1, "surface");
         }

         public void onSurfaceTextureUpdated(SurfaceTexture var1) {
            Intrinsics.checkNotNullParameter(var1, "surface");
         }
      }));
   }

   public CameraSurfaceView(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1, var2, var3, var4);
      TextureView var5 = new TextureView(this.getContext());
      this.textureView = var5;
      this.addView((View)var5);
      this.textureView.setSurfaceTextureListener((TextureView.SurfaceTextureListener)(new TextureView.SurfaceTextureListener(this) {
         final CameraSurfaceView this$0;

         {
            this.this$0 = var1;
         }

         public void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            this.this$0.setMSurface(new Surface(var1));
            this.this$0.refreshPreView();
         }

         public boolean onSurfaceTextureDestroyed(SurfaceTexture var1) {
            Intrinsics.checkNotNullParameter(var1, "surface");
            this.this$0.setMSurface((Surface)null);
            this.this$0.refreshPreView();
            return true;
         }

         public void onSurfaceTextureSizeChanged(SurfaceTexture var1, int var2, int var3) {
            Intrinsics.checkNotNullParameter(var1, "surface");
         }

         public void onSurfaceTextureUpdated(SurfaceTexture var1) {
            Intrinsics.checkNotNullParameter(var1, "surface");
         }
      }));
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

   public void clearSurface(Surface var1) {
      Intrinsics.checkNotNullParameter(var1, "surface");
      EGLDisplay var2 = EGL14.eglGetDisplay(0);
      Intrinsics.checkNotNullExpressionValue(var2, "eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY)");
      int[] var3 = new int[2];
      EGL14.eglInitialize(var2, var3, 0, var3, 1);
      EGLConfig[] var6 = new EGLConfig[1];
      int[] var4 = new int[1];
      EGL14.eglChooseConfig(var2, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12344, 0, 12344}, 0, var6, 0, 1, var4, 0);
      EGLConfig var8 = var6[0];
      EGLContext var7 = EGL14.eglCreateContext(var2, var8, EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
      Intrinsics.checkNotNullExpressionValue(var7, "eglCreateContext(display…GL_NONE\n            ), 0)");
      EGLSurface var5 = EGL14.eglCreateWindowSurface(var2, var8, var1, new int[]{12344}, 0);
      Intrinsics.checkNotNullExpressionValue(var5, "eglCreateWindowSurface(d…GL_NONE\n            ), 0)");
      EGL14.eglMakeCurrent(var2, var5, var5, var7);
      GLES20.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
      GLES20.glClear(16384);
      EGL14.eglSwapBuffers(var2, var5);
      EGL14.eglDestroySurface(var2, var5);
      EGL14.eglMakeCurrent(var2, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
      EGL14.eglDestroyContext(var2, var7);
      EGL14.eglTerminate(var2);
   }

   protected final Surface getMSurface() {
      return this.mSurface;
   }

   public void onLifeCycleChange(Lifecycle.State var1) {
      Intrinsics.checkNotNullParameter(var1, "state");
      super.onLifeCycleChange(var1);
      this.refreshPreView();
   }

   public void refreshPreView() {
      if (LogUtil.log5()) {
         StringBuilder var2 = (new StringBuilder()).append("refreshPreView: ").append(this.getLifecycle().getCurrentState()).append(',');
         boolean var1;
         if (this.mSurface != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         LogUtil.d(var2.append(var1).toString());
      }

      Surface var3;
      if (this.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED) {
         var3 = this.mSurface;
         if (var3 != null) {
            Intrinsics.checkNotNull(var3);
            this.startPreview(var3);
            return;
         }
      }

      this.stopPreview();
      var3 = this.mSurface;
      if (var3 != null) {
         Intrinsics.checkNotNull(var3);
         this.clearSurface(var3);
      }

   }

   protected final void setMSurface(Surface var1) {
      this.mSurface = var1;
   }

   public abstract void startPreview(Surface var1);

   public abstract void stopPreview();
}
