package com.tencent.bugly.crashreport.crash.jni;

import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import java.util.Map;

public interface NativeExceptionHandler {
   boolean getAndUpdateAnrState();

   void handleNativeException(int var1, int var2, long var3, long var5, String var7, String var8, String var9, String var10, int var11, String var12, int var13, int var14, int var15, String var16, String var17);

   void handleNativeException2(int var1, int var2, long var3, long var5, String var7, String var8, String var9, String var10, int var11, String var12, int var13, int var14, int var15, String var16, String var17, String[] var18);

   CrashDetailBean packageCrashDatas(String var1, String var2, long var3, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, byte[] var14, Map var15, boolean var16, boolean var17);
}
