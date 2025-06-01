package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable;

public class PlugInBean implements Parcelable {
   public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      // $FF: synthetic method
      public final Object createFromParcel(Parcel var1) {
         return new PlugInBean(var1);
      }
   };
   public final String a;
   public final String b;
   public final String c;

   public PlugInBean(Parcel var1) {
      this.a = var1.readString();
      this.b = var1.readString();
      this.c = var1.readString();
   }

   public PlugInBean(String var1, String var2, String var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }

   public int describeContents() {
      return 0;
   }

   public String toString() {
      return "plid:" + this.a + " plV:" + this.b + " plUUID:" + this.c;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeString(this.a);
      var1.writeString(this.b);
      var1.writeString(this.c);
   }
}
