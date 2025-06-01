package com.hzbhd.canbus.factory;

import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.Preconditions;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import java.util.function.Consumer;

public class Dependency {
   private static final String TAG = "Dependency";
   private static Dependency sDependency;
   private Context mContext;
   private final ArrayMap mDependencies = new ArrayMap();
   private final ArrayMap mProviders = new ArrayMap();

   public Dependency(Context var1) {
      this.mContext = var1;
   }

   public static void clearDependencies() {
      sDependency = null;
   }

   public static void destroy(Class var0, Consumer var1) {
      sDependency.destroyDependency(var0, var1);
   }

   private void destroyDependency(Class var1, Consumer var2) {
      Object var3 = this.mDependencies.remove(var1);
      if (var3 != null && var2 != null) {
         var2.accept(var3);
      }

   }

   public static Object get(DependencyKey var0) {
      return sDependency.getDependency(var0);
   }

   public static Object get(Class var0) {
      StringBuilder var2 = (new StringBuilder()).append("get: sDependency:");
      boolean var1;
      if (sDependency != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      Log.d("Dependency", var2.append(var1).toString());
      return sDependency.getDependency(var0);
   }

   private Object getDependencyInner(Object var1) {
      synchronized(this){}

      Throwable var10000;
      label75: {
         boolean var10001;
         Object var3;
         try {
            var3 = this.mDependencies.get(var1);
         } catch (Throwable var9) {
            var10000 = var9;
            var10001 = false;
            break label75;
         }

         Object var2 = var3;
         if (var3 != null) {
            return var2;
         }

         label66:
         try {
            var2 = this.createDependency(var1);
            this.mDependencies.put(var1, var2);
            return var2;
         } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            break label66;
         }
      }

      Throwable var10 = var10000;
      throw var10;
   }

   public static Object getNew(Class var0) {
      return sDependency.createDependency(var0);
   }

   public static boolean isStart() {
      boolean var0;
      if (sDependency != null) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   protected Object createDependency(Object var1) {
      boolean var2;
      if (!(var1 instanceof DependencyKey) && !(var1 instanceof Class)) {
         var2 = false;
      } else {
         var2 = true;
      }

      Preconditions.checkArgument(var2);
      DependencyProvider var3 = (DependencyProvider)this.mProviders.get(var1);
      if (var3 != null) {
         return var3.createDependency();
      } else {
         throw new IllegalArgumentException("Unsupported dependency " + var1 + ". " + this.mProviders.size() + " providers known.");
      }
   }

   protected final Object getDependency(DependencyKey var1) {
      return this.getDependencyInner(var1);
   }

   protected final Object getDependency(Class var1) {
      return this.getDependencyInner(var1);
   }

   // $FF: synthetic method
   Object lambda$start$0$com_hzbhd_canbus_factory_Dependency() {
      return new CanSettingProxy(this.mContext);
   }

   public void start() {
      this.mProviders.put(CanSettingProxy.class, new Dependency$$ExternalSyntheticLambda0(this));
      sDependency = this;
   }

   public static final class DependencyKey {
      private final String mDisplayName;

      public DependencyKey(String var1) {
         this.mDisplayName = var1;
      }

      public String toString() {
         return this.mDisplayName;
      }
   }

   public interface DependencyProvider {
      Object createDependency();
   }
}
