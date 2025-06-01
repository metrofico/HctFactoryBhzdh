package com.hzbhd.proxy.camera.manager;

import android.view.Surface;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.camera.aidl.ICameraCallback;
import com.hzbhd.proxy.camera.aidl.ICameraManager;
import com.hzbhd.proxy.camera.listener.ICameraListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;
import com.hzbhd.util.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001#B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007J\u0016\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0004J\u001e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007J\u0016\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u000bJ\u0016\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001eJ\u0018\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00072\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010 \u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010!\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010\"\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\u0004\u0018\u00010\r8BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006$"},
   d2 = {"Lcom/hzbhd/proxy/camera/manager/CameraManager;", "", "()V", "SERVICE_NAME_CAMERA", "", "callbackMaps", "Ljava/util/HashMap;", "", "Lcom/hzbhd/proxy/camera/manager/CameraManager$MyICameraCallback;", "Lkotlin/collections/HashMap;", "listenerMaps", "Lcom/hzbhd/proxy/camera/listener/ICameraListener;", "mICameraManager", "Lcom/hzbhd/proxy/camera/aidl/ICameraManager;", "getMICameraManager", "()Lcom/hzbhd/proxy/camera/aidl/ICameraManager;", "getCameraInfo", "flag", "type", "getFlagAttr", "onServiceConn", "", "setCameraInfo", "value", "setFlagAttr", "setListener", "cameraFlag", "iCameraListener", "startPreview", "surface", "Landroid/view/Surface;", "startRecord", "startTestPreview", "stopPreview", "stopRecord", "MyICameraCallback", "camera-proxy_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class CameraManager {
   public static final CameraManager INSTANCE = new CameraManager();
   public static final String SERVICE_NAME_CAMERA = "hzbhd_camera";
   private static final HashMap callbackMaps = new HashMap();
   private static final HashMap listenerMaps = new HashMap();
   private static ICameraManager mICameraManager;

   // $FF: synthetic method
   public static void $r8$lambda$5j4zpqf_QXNG_ZUTrl3RYPngB58() {
      _get_mICameraManager_$lambda_2$lambda_1();
   }

   // $FF: synthetic method
   public static void $r8$lambda$6_ir9_p0mkK0AIIB9TL9_QZaukw() {
      _init_$lambda_0();
   }

   static {
      ServiceStateManager.getInstance().registerConnectListener(SourceConstantsDef.MODULE_ID.CAMERA.ordinal(), new CameraManager$$ExternalSyntheticLambda1());
   }

   private CameraManager() {
   }

   private static final void _get_mICameraManager_$lambda_2$lambda_1() {
      mICameraManager = null;
   }

   private static final void _init_$lambda_0() {
      INSTANCE.onServiceConn();
   }

   private final ICameraManager getMICameraManager() {
      // $FF: Couldn't be decompiled
   }

   private final void onServiceConn() {
      if (LogUtil.log5()) {
         StringBuilder var2 = (new StringBuilder()).append("onServiceConn: ");
         boolean var1;
         if (this.getMICameraManager() != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         LogUtil.d(var2.append(var1).toString());
      }

      Exception var10000;
      label51: {
         Iterator var10;
         boolean var10001;
         try {
            var10 = callbackMaps.keySet().iterator();
         } catch (Exception var9) {
            var10000 = var9;
            var10001 = false;
            break label51;
         }

         while(true) {
            Integer var3;
            ICameraManager var4;
            try {
               if (!var10.hasNext()) {
                  return;
               }

               var3 = (Integer)var10.next();
               var4 = this.getMICameraManager();
            } catch (Exception var8) {
               var10000 = var8;
               var10001 = false;
               break;
            }

            if (var4 != null) {
               try {
                  Intrinsics.checkNotNullExpressionValue(var3, "cameraFlag");
                  var4.addCallBack(var3, (ICameraCallback)callbackMaps.get(var3));
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break;
               }
            }

            ICameraListener var12;
            try {
               var12 = (ICameraListener)listenerMaps.get(var3);
            } catch (Exception var6) {
               var10000 = var6;
               var10001 = false;
               break;
            }

            if (var12 != null) {
               try {
                  var12.onServiceConn();
               } catch (Exception var5) {
                  var10000 = var5;
                  var10001 = false;
                  break;
               }
            }
         }
      }

      Exception var11 = var10000;
      var11.printStackTrace();
   }

   public final String getCameraInfo(int var1, int var2) {
      Exception var10000;
      label30: {
         boolean var10001;
         ICameraManager var3;
         try {
            var3 = this.getMICameraManager();
         } catch (Exception var6) {
            var10000 = var6;
            var10001 = false;
            break label30;
         }

         String var7;
         if (var3 != null) {
            try {
               var7 = var3.getCameraInfo(var1, var2);
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
               break label30;
            }
         } else {
            var7 = null;
         }

         try {
            Intrinsics.checkNotNull(var7);
            return var7;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var8 = var10000;
      var8.printStackTrace();
      return "";
   }

   public final int getFlagAttr(int var1, int var2) {
      Exception var10000;
      label30: {
         boolean var10001;
         ICameraManager var3;
         try {
            var3 = this.getMICameraManager();
         } catch (Exception var6) {
            var10000 = var6;
            var10001 = false;
            break label30;
         }

         Integer var7;
         if (var3 != null) {
            try {
               var7 = var3.getFlagAttr(var1, var2);
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
               break label30;
            }
         } else {
            var7 = null;
         }

         try {
            Intrinsics.checkNotNull(var7);
            var1 = var7;
            return var1;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var8 = var10000;
      var8.printStackTrace();
      return -1;
   }

   public final void setCameraInfo(int var1, int var2, String var3) {
      Intrinsics.checkNotNullParameter(var3, "value");

      Exception var10000;
      label28: {
         boolean var10001;
         ICameraManager var4;
         try {
            var4 = this.getMICameraManager();
         } catch (Exception var6) {
            var10000 = var6;
            var10001 = false;
            break label28;
         }

         if (var4 == null) {
            return;
         }

         try {
            var4.setCameraInfo(var1, var2, var3);
            return;
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
         }
      }

      Exception var7 = var10000;
      var7.printStackTrace();
   }

   public final void setFlagAttr(int param1, int param2, int param3) {
      // $FF: Couldn't be decompiled
   }

   public final void setListener(int var1, ICameraListener var2) {
      Intrinsics.checkNotNullParameter(var2, "iCameraListener");
      HashMap var3 = listenerMaps;
      if (var3.containsKey(var1)) {
         var3.replace(var1, var2);
      } else {
         ((Map)var3).put(var1, var2);
      }

      HashMap var4 = callbackMaps;
      if (!var4.containsKey(var1)) {
         ((Map)var4).put(var1, new MyICameraCallback(var1));
         ICameraManager var5 = this.getMICameraManager();
         if (var5 != null) {
            var5.addCallBack(var1, (ICameraCallback)var4.get(var1));
         }
      }

   }

   public final void startPreview(int var1, Surface var2) {
      Intrinsics.checkNotNullParameter(var2, "surface");

      Exception var10000;
      label28: {
         boolean var10001;
         ICameraManager var3;
         try {
            var3 = this.getMICameraManager();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label28;
         }

         if (var3 == null) {
            return;
         }

         try {
            var3.startPreviewWithCallBack(var1, var2, (ICameraCallback)callbackMaps.get(var1));
            return;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
   }

   public final void startRecord(int param1, Surface param2) {
      // $FF: Couldn't be decompiled
   }

   public final void startTestPreview(int param1) {
      // $FF: Couldn't be decompiled
   }

   public final void stopPreview(int param1) {
      // $FF: Couldn't be decompiled
   }

   public final void stopRecord(int param1) {
      // $FF: Couldn't be decompiled
   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0016J\u001a\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"},
      d2 = {"Lcom/hzbhd/proxy/camera/manager/CameraManager$MyICameraCallback;", "Lcom/hzbhd/proxy/camera/aidl/ICameraCallback$Stub;", "cameraFlag", "", "(I)V", "getCameraFlag", "()I", "onAttrChange", "", "attrType", "value", "onInfoChange", "", "onPreviewState", "state", "onSignalChange", "signal", "camera-proxy_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class MyICameraCallback extends ICameraCallback.Stub {
      private final int cameraFlag;

      public MyICameraCallback(int var1) {
         this.cameraFlag = var1;
      }

      public final int getCameraFlag() {
         return this.cameraFlag;
      }

      public void onAttrChange(int var1, int var2) {
         ICameraListener var3 = (ICameraListener)CameraManager.listenerMaps.get(this.cameraFlag);
         if (var3 != null) {
            var3.onAttrChange(var1, var2);
         }

      }

      public void onInfoChange(int var1, String var2) {
         ICameraListener var3 = (ICameraListener)CameraManager.listenerMaps.get(this.cameraFlag);
         if (var3 != null) {
            var3.onInfoChange(var1, var2);
         }

      }

      public void onPreviewState(int var1) {
         ICameraListener var2 = (ICameraListener)CameraManager.listenerMaps.get(this.cameraFlag);
         if (var2 != null) {
            var2.onPreviewState(var1);
         }

      }

      public void onSignalChange(int var1) {
         ICameraListener var2 = (ICameraListener)CameraManager.listenerMaps.get(this.cameraFlag);
         if (var2 != null) {
            var2.onSignalChange(var1);
         }

      }
   }
}
