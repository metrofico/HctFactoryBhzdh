package com.tencent.bugly;

import com.tencent.bugly.proguard.aa;
import java.util.Map;

public class BuglyStrategy {
   protected int a = 31;
   protected boolean b = false;
   private String c;
   private String d;
   private String e;
   private long f;
   private String g;
   private String h;
   private String i;
   private boolean j = true;
   private boolean k = true;
   private boolean l = true;
   private boolean m = false;
   private boolean n = true;
   private Class o = null;
   private boolean p = true;
   private boolean q = true;
   private boolean r = true;
   private boolean s = true;
   private boolean t = false;
   private a u;
   private boolean v = false;

   public String getAppChannel() {
      synchronized(this){}

      Throwable var10000;
      label78: {
         String var1;
         boolean var10001;
         try {
            var1 = this.d;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label78;
         }

         if (var1 != null) {
            return var1;
         }

         try {
            var1 = aa.b().s;
         } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            break label78;
         }

         return var1;
      }

      Throwable var8 = var10000;
      throw var8;
   }

   public String getAppPackageName() {
      synchronized(this){}

      Throwable var10000;
      label78: {
         String var1;
         boolean var10001;
         try {
            var1 = this.e;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label78;
         }

         if (var1 != null) {
            return var1;
         }

         try {
            var1 = aa.b().c;
         } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            break label78;
         }

         return var1;
      }

      Throwable var8 = var10000;
      throw var8;
   }

   public long getAppReportDelay() {
      synchronized(this){}

      long var1;
      try {
         var1 = this.f;
      } finally {
         ;
      }

      return var1;
   }

   public String getAppVersion() {
      synchronized(this){}

      Throwable var10000;
      label78: {
         String var1;
         boolean var10001;
         try {
            var1 = this.c;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label78;
         }

         if (var1 != null) {
            return var1;
         }

         try {
            var1 = aa.b().o;
         } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            break label78;
         }

         return var1;
      }

      Throwable var8 = var10000;
      throw var8;
   }

   public int getCallBackType() {
      synchronized(this){}

      int var1;
      try {
         var1 = this.a;
      } finally {
         ;
      }

      return var1;
   }

   public boolean getCloseErrorCallback() {
      synchronized(this){}

      boolean var1;
      try {
         var1 = this.b;
      } finally {
         ;
      }

      return var1;
   }

   public a getCrashHandleCallback() {
      synchronized(this){}

      a var1;
      try {
         var1 = this.u;
      } finally {
         ;
      }

      return var1;
   }

   public String getDeviceID() {
      synchronized(this){}

      String var1;
      try {
         var1 = this.h;
      } finally {
         ;
      }

      return var1;
   }

   public String getDeviceModel() {
      synchronized(this){}

      String var1;
      try {
         var1 = this.i;
      } finally {
         ;
      }

      return var1;
   }

   public String getLibBuglySOFilePath() {
      synchronized(this){}

      String var1;
      try {
         var1 = this.g;
      } finally {
         ;
      }

      return var1;
   }

   public Class getUserInfoActivity() {
      synchronized(this){}

      Class var1;
      try {
         var1 = this.o;
      } finally {
         ;
      }

      return var1;
   }

   public boolean isBuglyLogUpload() {
      synchronized(this){}

      boolean var1;
      try {
         var1 = this.p;
      } finally {
         ;
      }

      return var1;
   }

   public boolean isEnableANRCrashMonitor() {
      synchronized(this){}

      boolean var1;
      try {
         var1 = this.k;
      } finally {
         ;
      }

      return var1;
   }

   public boolean isEnableCatchAnrTrace() {
      synchronized(this){}

      boolean var1;
      try {
         var1 = this.l;
      } finally {
         ;
      }

      return var1;
   }

   public boolean isEnableNativeCrashMonitor() {
      synchronized(this){}

      boolean var1;
      try {
         var1 = this.j;
      } finally {
         ;
      }

      return var1;
   }

   public boolean isEnableRecordAnrMainStack() {
      return this.m;
   }

   public boolean isEnableUserInfo() {
      synchronized(this){}

      boolean var1;
      try {
         var1 = this.n;
      } finally {
         ;
      }

      return var1;
   }

   public boolean isMerged() {
      return this.v;
   }

   public boolean isReplaceOldChannel() {
      return this.q;
   }

   public boolean isUploadProcess() {
      synchronized(this){}

      boolean var1;
      try {
         var1 = this.r;
      } finally {
         ;
      }

      return var1;
   }

   public boolean isUploadSpotCrash() {
      synchronized(this){}

      boolean var1;
      try {
         var1 = this.s;
      } finally {
         ;
      }

      return var1;
   }

   public boolean recordUserInfoOnceADay() {
      synchronized(this){}

      boolean var1;
      try {
         var1 = this.t;
      } finally {
         ;
      }

      return var1;
   }

   public BuglyStrategy setAppChannel(String var1) {
      synchronized(this){}

      try {
         this.d = var1;
      } finally {
         ;
      }

      return this;
   }

   public BuglyStrategy setAppPackageName(String var1) {
      synchronized(this){}

      try {
         this.e = var1;
      } finally {
         ;
      }

      return this;
   }

   public BuglyStrategy setAppReportDelay(long var1) {
      synchronized(this){}

      try {
         this.f = var1;
      } finally {
         ;
      }

      return this;
   }

   public BuglyStrategy setAppVersion(String var1) {
      synchronized(this){}

      try {
         this.c = var1;
      } finally {
         ;
      }

      return this;
   }

   public BuglyStrategy setBuglyLogUpload(boolean var1) {
      synchronized(this){}

      try {
         this.p = var1;
      } finally {
         ;
      }

      return this;
   }

   public void setCallBackType(int var1) {
      synchronized(this){}

      try {
         this.a = var1;
      } finally {
         ;
      }

   }

   public void setCloseErrorCallback(boolean var1) {
      synchronized(this){}

      try {
         this.b = var1;
      } finally {
         ;
      }

   }

   public BuglyStrategy setCrashHandleCallback(a var1) {
      synchronized(this){}

      try {
         this.u = var1;
      } finally {
         ;
      }

      return this;
   }

   public BuglyStrategy setDeviceID(String var1) {
      synchronized(this){}

      try {
         this.h = var1;
      } finally {
         ;
      }

      return this;
   }

   public BuglyStrategy setDeviceModel(String var1) {
      synchronized(this){}

      try {
         this.i = var1;
      } finally {
         ;
      }

      return this;
   }

   public BuglyStrategy setEnableANRCrashMonitor(boolean var1) {
      synchronized(this){}

      try {
         this.k = var1;
      } finally {
         ;
      }

      return this;
   }

   public void setEnableCatchAnrTrace(boolean var1) {
      this.l = var1;
   }

   public BuglyStrategy setEnableNativeCrashMonitor(boolean var1) {
      synchronized(this){}

      try {
         this.j = var1;
      } finally {
         ;
      }

      return this;
   }

   public void setEnableRecordAnrMainStack(boolean var1) {
      this.m = var1;
   }

   public BuglyStrategy setEnableUserInfo(boolean var1) {
      synchronized(this){}

      try {
         this.n = var1;
      } finally {
         ;
      }

      return this;
   }

   public BuglyStrategy setLibBuglySOFilePath(String var1) {
      synchronized(this){}

      try {
         this.g = var1;
      } finally {
         ;
      }

      return this;
   }

   @Deprecated
   public void setMerged(boolean var1) {
      this.v = var1;
   }

   public BuglyStrategy setRecordUserInfoOnceADay(boolean var1) {
      synchronized(this){}

      try {
         this.t = var1;
      } finally {
         ;
      }

      return this;
   }

   public void setReplaceOldChannel(boolean var1) {
      this.q = var1;
   }

   public BuglyStrategy setUploadProcess(boolean var1) {
      synchronized(this){}

      try {
         this.r = var1;
      } finally {
         ;
      }

      return this;
   }

   public void setUploadSpotCrash(boolean var1) {
      synchronized(this){}

      try {
         this.s = var1;
      } finally {
         ;
      }

   }

   public BuglyStrategy setUserInfoActivity(Class var1) {
      synchronized(this){}

      try {
         this.o = var1;
      } finally {
         ;
      }

      return this;
   }

   public static class a {
      public static final int CRASHTYPE_ANR = 4;
      public static final int CRASHTYPE_BLOCK = 7;
      public static final int CRASHTYPE_COCOS2DX_JS = 5;
      public static final int CRASHTYPE_COCOS2DX_LUA = 6;
      public static final int CRASHTYPE_JAVA_CATCH = 1;
      public static final int CRASHTYPE_JAVA_CRASH = 0;
      public static final int CRASHTYPE_NATIVE = 2;
      public static final int CRASHTYPE_U3D = 3;
      public static final int MAX_USERDATA_KEY_LENGTH = 100;
      public static final int MAX_USERDATA_VALUE_LENGTH = 100000;

      public Map onCrashHandleStart(int var1, String var2, String var3, String var4) {
         synchronized(this){}
         return null;
      }

      public byte[] onCrashHandleStart2GetExtraDatas(int var1, String var2, String var3, String var4) {
         synchronized(this){}
         return null;
      }
   }
}
