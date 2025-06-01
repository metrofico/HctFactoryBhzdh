package com.hzbhd.setting.proxy;

import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.common.settings.constant.Configs;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.setting.proxy.aidl.ISettingServiceManager;
import com.hzbhd.setting.proxy.aidl.ISettingsCallBack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SettingsManager {
   private static final String TAG = "SettingsManager";
   private static SettingsManager mSettingsManager;
   private HashMap mISettingsCallBacks = new HashMap();
   private HashMap mSettingsListenerLists = new HashMap();

   public static SettingsManager getInstance() {
      // $FF: Couldn't be decompiled
   }

   public byte[] getBytes(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, int var3, int var4) {
      if (SettingServiceManager.getISettingServiceManager() != null) {
         try {
            byte[] var6 = SettingServiceManager.getISettingServiceManager().getBytes(SettingServiceManager.getKey(var1, var2), var3, var4);
            return var6;
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

      return null;
   }

   public String getConfig(Configs.Module var1) {
      String var3;
      if (SettingServiceManager.getISettingServiceManager() != null) {
         try {
            var3 = SettingServiceManager.getISettingServiceManager().getConfig(var1.ordinal());
            return var3;
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }

      var3 = null;
      return var3;
   }

   public int getInt(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, int var3, int var4) {
      if (SettingServiceManager.getISettingServiceManager() != null) {
         try {
            var3 = SettingServiceManager.getISettingServiceManager().getInt(SettingServiceManager.getKey(var1, var2), var3, var4);
            return var3;
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

      return -1;
   }

   public String getString(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, int var3, int var4) {
      if (SettingServiceManager.getISettingServiceManager() != null) {
         try {
            String var6 = SettingServiceManager.getISettingServiceManager().getString(SettingServiceManager.getKey(var1, var2), var3, var4);
            return var6;
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

      return null;
   }

   public void notifyConfig(Configs.Module var1, String var2) {
      if (SettingServiceManager.getISettingServiceManager() != null) {
         try {
            SettingServiceManager.getISettingServiceManager().notifyConfig(var1.ordinal(), var2);
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }

   public void registerSettingsListener(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, ISettingsListener var3) {
      int var4 = SettingServiceManager.getKey(var1, var2);
      if (!this.mSettingsListenerLists.containsKey(var4)) {
         this.mSettingsListenerLists.put(var4, new ArrayList());
      }

      ((ArrayList)this.mSettingsListenerLists.get(var4)).add(var3);
      if (!this.mISettingsCallBacks.containsKey(var4)) {
         this.mISettingsCallBacks.put(var4, new MyISettingsCallBack(this, var4));
         ISettingServiceManager var6 = SettingServiceManager.getISettingServiceManager();
         if (var6 != null) {
            try {
               var6.registerSettingsListener(var4, (ISettingsCallBack)this.mISettingsCallBacks.get(var4));
            } catch (Exception var5) {
               var5.printStackTrace();
            }
         }

      }
   }

   protected void resetSettingsCallBacks() {
      if (!this.mISettingsCallBacks.isEmpty()) {
         Iterator var1 = this.mISettingsCallBacks.keySet().iterator();

         while(var1.hasNext()) {
            Integer var3 = (Integer)var1.next();
            ISettingServiceManager var2 = SettingServiceManager.getISettingServiceManager();
            if (var2 != null) {
               try {
                  var2.registerSettingsListener(var3, (ISettingsCallBack)this.mISettingsCallBacks.get(var3));
               } catch (Exception var4) {
                  var4.printStackTrace();
               }
            }
         }

      }
   }

   public void setBytes(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, int var3, int var4, byte[] var5) {
      if (SettingServiceManager.getISettingServiceManager() != null) {
         try {
            SettingServiceManager.getISettingServiceManager().setBytes(SettingServiceManager.getKey(var1, var2), var3, var4, var5);
         } catch (Exception var6) {
            var6.printStackTrace();
         }
      }

   }

   public void setInt(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, int var3, int var4, int var5) {
      if (SettingServiceManager.getISettingServiceManager() != null) {
         try {
            SettingServiceManager.getISettingServiceManager().setInt(SettingServiceManager.getKey(var1, var2), var3, var4, var5);
         } catch (Exception var6) {
            var6.printStackTrace();
         }
      }

   }

   public void setString(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, int var3, int var4, String var5) {
      if (SettingServiceManager.getISettingServiceManager() != null) {
         try {
            SettingServiceManager.getISettingServiceManager().setString(SettingServiceManager.getKey(var1, var2), var3, var4, var5);
         } catch (Exception var6) {
            var6.printStackTrace();
         }
      }

   }

   public void unregisterSettingsListener(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, ISettingsListener var3) {
      int var4 = SettingServiceManager.getKey(var1, var2);
      if (this.mSettingsListenerLists.containsKey(var4) && ((ArrayList)this.mSettingsListenerLists.get(var4)).contains(var3)) {
         ((ArrayList)this.mSettingsListenerLists.get(var4)).remove(var3);
         if (((ArrayList)this.mSettingsListenerLists.get(var4)).isEmpty()) {
            this.mSettingsListenerLists.remove(var4);
            ISettingServiceManager var6 = SettingServiceManager.getISettingServiceManager();
            if (var6 != null) {
               try {
                  var6.unregisterSettingsListener(var4, (ISettingsCallBack)this.mISettingsCallBacks.get(var4));
               } catch (Exception var5) {
                  var5.printStackTrace();
               }
            }

            this.mISettingsCallBacks.remove(var4);
         }
      }

   }

   private class MyISettingsCallBack extends ISettingsCallBack.Stub {
      private int mKey;
      final SettingsManager this$0;

      private MyISettingsCallBack(SettingsManager var1, int var2) {
         this.this$0 = var1;
         this.mKey = var2;
      }

      // $FF: synthetic method
      MyISettingsCallBack(SettingsManager var1, int var2, Object var3) {
         this(var1, var2);
      }

      public void onSettingsBytes(int var1, int var2, byte[] var3) {
         if (this.this$0.mSettingsListenerLists.containsKey(this.mKey)) {
            if (this.this$0.mSettingsListenerLists.get(this.mKey) != null) {
               for(int var4 = 0; var4 < ((ArrayList)this.this$0.mSettingsListenerLists.get(this.mKey)).size(); ++var4) {
                  ((ISettingsListener)((ArrayList)this.this$0.mSettingsListenerLists.get(this.mKey)).get(var4)).onReceived(var1, var2, var3);
               }

            }
         }
      }

      public void onSettingsInt(int var1, int var2, int var3) {
         if (this.this$0.mSettingsListenerLists.containsKey(this.mKey)) {
            if (this.this$0.mSettingsListenerLists.get(this.mKey) != null) {
               for(int var4 = 0; var4 < ((ArrayList)this.this$0.mSettingsListenerLists.get(this.mKey)).size(); ++var4) {
                  ((ISettingsListener)((ArrayList)this.this$0.mSettingsListenerLists.get(this.mKey)).get(var4)).onReceived(var1, var2, var3);
               }

            }
         }
      }

      public void onSettingsString(int var1, int var2, String var3) {
         if (this.this$0.mSettingsListenerLists.containsKey(this.mKey)) {
            if (this.this$0.mSettingsListenerLists.get(this.mKey) != null) {
               for(int var4 = 0; var4 < ((ArrayList)this.this$0.mSettingsListenerLists.get(this.mKey)).size(); ++var4) {
                  ((ISettingsListener)((ArrayList)this.this$0.mSettingsListenerLists.get(this.mKey)).get(var4)).onReceived(var1, var2, var3);
               }

            }
         }
      }
   }
}
