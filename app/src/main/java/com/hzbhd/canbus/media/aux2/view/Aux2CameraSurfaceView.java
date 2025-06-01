package com.hzbhd.canbus.media.aux2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Surface;
import com.hzbhd.canbus.media.aux2.manager.Aux2Manager;
import com.hzbhd.proxy.camera.manager.CameraManager;

public class Aux2CameraSurfaceView extends CameraSurfaceView {
   public Aux2CameraSurfaceView(Context var1) {
      super(var1);
   }

   public Aux2CameraSurfaceView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public Aux2CameraSurfaceView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public Aux2CameraSurfaceView(Context var1, AttributeSet var2, int var3, int var4) {
      super(var1, var2, var3, var4);
   }

   public void startPreview(Surface var1) {
      CameraManager.INSTANCE.startPreview(Aux2Manager.getInstance().getCameraFlag(), var1);
   }

   public void stopPreview() {
      CameraManager.INSTANCE.stopPreview(Aux2Manager.getInstance().getCameraFlag());
   }
}
