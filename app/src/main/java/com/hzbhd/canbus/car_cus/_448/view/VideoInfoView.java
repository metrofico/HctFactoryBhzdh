package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.LinearLayout;
import com.hzbhd.proxy.camera.manager.CameraManager;

public class VideoInfoView extends LinearLayout {
   private boolean VIDEO_VIEW_SHOW_TAG;
   private TextureView textureView;
   private int videoChannel;
   private View view;

   public VideoInfoView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public VideoInfoView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public VideoInfoView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.videoChannel = 2;
      this.VIDEO_VIEW_SHOW_TAG = false;
      View var4 = LayoutInflater.from(var1).inflate(2131558611, this, true);
      this.view = var4;
      this.textureView = (TextureView)var4.findViewById(2131363758);
   }

   public void startVideoView() {
      if (!this.VIDEO_VIEW_SHOW_TAG) {
         TextureView var1 = this.textureView;
         if (var1 != null) {
            var1.setSurfaceTextureListener(new TextureView.SurfaceTextureListener(this) {
               final VideoInfoView this$0;

               {
                  this.this$0 = var1;
               }

               public void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
                  Surface var4 = new Surface(var1);
                  CameraManager.INSTANCE.startPreview(this.this$0.videoChannel, var4);
                  this.this$0.VIDEO_VIEW_SHOW_TAG = true;
               }

               public boolean onSurfaceTextureDestroyed(SurfaceTexture var1) {
                  return false;
               }

               public void onSurfaceTextureSizeChanged(SurfaceTexture var1, int var2, int var3) {
               }

               public void onSurfaceTextureUpdated(SurfaceTexture var1) {
               }
            });
         }
      }
   }

   public void stopVideoView() {
      CameraManager.INSTANCE.stopPreview(this.videoChannel);
      this.VIDEO_VIEW_SHOW_TAG = false;
   }
}
