package com.tencent.bugly.crashreport.biz;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.proguard.ap;
import java.util.Map;

public class UserInfoBean implements Parcelable {
   public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      // $FF: synthetic method
      public final Object createFromParcel(Parcel var1) {
         return new UserInfoBean(var1);
      }
   };
   public long a;
   public int b;
   public String c;
   public String d;
   public long e;
   public long f;
   public long g;
   public long h;
   public long i;
   public String j;
   public long k = 0L;
   public boolean l;
   public String m;
   public String n;
   public int o;
   public int p;
   public int q;
   public Map r;
   public Map s;

   public UserInfoBean() {
      this.l = false;
      this.m = "unknown";
      this.p = -1;
      this.q = -1;
      this.r = null;
      this.s = null;
   }

   public UserInfoBean(Parcel var1) {
      boolean var2 = false;
      this.l = false;
      this.m = "unknown";
      this.p = -1;
      this.q = -1;
      this.r = null;
      this.s = null;
      this.b = var1.readInt();
      this.c = var1.readString();
      this.d = var1.readString();
      this.e = var1.readLong();
      this.f = var1.readLong();
      this.g = var1.readLong();
      this.h = var1.readLong();
      this.i = var1.readLong();
      this.j = var1.readString();
      this.k = var1.readLong();
      if (var1.readByte() == 1) {
         var2 = true;
      }

      this.l = var2;
      this.m = var1.readString();
      this.p = var1.readInt();
      this.q = var1.readInt();
      this.r = ap.b(var1);
      this.s = ap.b(var1);
      this.n = var1.readString();
      this.o = var1.readInt();
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeInt(this.b);
      var1.writeString(this.c);
      var1.writeString(this.d);
      var1.writeLong(this.e);
      var1.writeLong(this.f);
      var1.writeLong(this.g);
      var1.writeLong(this.h);
      var1.writeLong(this.i);
      var1.writeString(this.j);
      var1.writeLong(this.k);
      var1.writeByte((byte)this.l);
      var1.writeString(this.m);
      var1.writeInt(this.p);
      var1.writeInt(this.q);
      ap.b(var1, this.r);
      ap.b(var1, this.s);
      var1.writeString(this.n);
      var1.writeInt(this.o);
   }
}
