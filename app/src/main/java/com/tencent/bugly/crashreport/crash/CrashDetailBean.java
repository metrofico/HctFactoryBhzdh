package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.proguard.ap;
import java.util.Map;
import java.util.UUID;

public class CrashDetailBean implements Parcelable, Comparable {
   public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      // $FF: synthetic method
      public final Object createFromParcel(Parcel var1) {
         return new CrashDetailBean(var1);
      }
   };
   public String A;
   public String B;
   public long C;
   public long D;
   public long E;
   public long F;
   public long G;
   public long H;
   public long I;
   public long J;
   public long K;
   public String L;
   public String M;
   public String N;
   public String O;
   public String P;
   public long Q;
   public boolean R;
   public Map S;
   public Map T;
   public int U;
   public int V;
   public Map W;
   public Map X;
   public byte[] Y;
   public String Z;
   public long a = -1L;
   public String aa;
   public int b;
   public String c;
   public boolean d;
   public String e;
   public String f;
   public String g;
   public Map h;
   public Map i;
   public boolean j;
   public boolean k;
   public int l;
   public String m;
   public String n;
   public String o;
   public String p;
   public String q;
   public long r;
   public String s;
   public int t;
   public String u;
   public String v;
   public String w;
   public String x;
   public byte[] y;
   public Map z;

   public CrashDetailBean() {
      this.b = 0;
      this.c = UUID.randomUUID().toString();
      this.d = false;
      this.e = "";
      this.f = "";
      this.g = "";
      this.h = null;
      this.i = null;
      this.j = false;
      this.k = false;
      this.l = 0;
      this.m = "";
      this.n = "";
      this.o = "";
      this.p = "";
      this.q = "";
      this.r = -1L;
      this.s = null;
      this.t = 0;
      this.u = "";
      this.v = "";
      this.w = null;
      this.x = null;
      this.y = null;
      this.z = null;
      this.A = "";
      this.B = "";
      this.C = -1L;
      this.D = -1L;
      this.E = -1L;
      this.F = -1L;
      this.G = -1L;
      this.H = -1L;
      this.I = -1L;
      this.J = -1L;
      this.K = -1L;
      this.L = "";
      this.M = "";
      this.N = "";
      this.O = "";
      this.P = "";
      this.Q = -1L;
      this.R = false;
      this.S = null;
      this.T = null;
      this.U = -1;
      this.V = -1;
      this.W = null;
      this.X = null;
      this.Y = null;
      this.Z = null;
      this.aa = null;
   }

   public CrashDetailBean(Parcel var1) {
      boolean var3 = false;
      this.b = 0;
      this.c = UUID.randomUUID().toString();
      this.d = false;
      this.e = "";
      this.f = "";
      this.g = "";
      this.h = null;
      this.i = null;
      this.j = false;
      this.k = false;
      this.l = 0;
      this.m = "";
      this.n = "";
      this.o = "";
      this.p = "";
      this.q = "";
      this.r = -1L;
      this.s = null;
      this.t = 0;
      this.u = "";
      this.v = "";
      this.w = null;
      this.x = null;
      this.y = null;
      this.z = null;
      this.A = "";
      this.B = "";
      this.C = -1L;
      this.D = -1L;
      this.E = -1L;
      this.F = -1L;
      this.G = -1L;
      this.H = -1L;
      this.I = -1L;
      this.J = -1L;
      this.K = -1L;
      this.L = "";
      this.M = "";
      this.N = "";
      this.O = "";
      this.P = "";
      this.Q = -1L;
      this.R = false;
      this.S = null;
      this.T = null;
      this.U = -1;
      this.V = -1;
      this.W = null;
      this.X = null;
      this.Y = null;
      this.Z = null;
      this.aa = null;
      this.b = var1.readInt();
      this.c = var1.readString();
      boolean var2;
      if (var1.readByte() == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.d = var2;
      this.e = var1.readString();
      this.f = var1.readString();
      this.g = var1.readString();
      if (var1.readByte() == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.j = var2;
      if (var1.readByte() == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.k = var2;
      this.l = var1.readInt();
      this.m = var1.readString();
      this.n = var1.readString();
      this.o = var1.readString();
      this.p = var1.readString();
      this.q = var1.readString();
      this.r = var1.readLong();
      this.s = var1.readString();
      this.t = var1.readInt();
      this.u = var1.readString();
      this.v = var1.readString();
      this.w = var1.readString();
      this.z = ap.b(var1);
      this.A = var1.readString();
      this.B = var1.readString();
      this.C = var1.readLong();
      this.D = var1.readLong();
      this.E = var1.readLong();
      this.F = var1.readLong();
      this.G = var1.readLong();
      this.H = var1.readLong();
      this.L = var1.readString();
      this.M = var1.readString();
      this.N = var1.readString();
      this.O = var1.readString();
      this.P = var1.readString();
      this.Q = var1.readLong();
      var2 = var3;
      if (var1.readByte() == 1) {
         var2 = true;
      }

      this.R = var2;
      this.S = ap.b(var1);
      this.h = ap.a(var1);
      this.i = ap.a(var1);
      this.U = var1.readInt();
      this.V = var1.readInt();
      this.W = ap.b(var1);
      this.X = ap.b(var1);
      this.Y = var1.createByteArray();
      this.y = var1.createByteArray();
      this.Z = var1.readString();
      this.aa = var1.readString();
      this.x = var1.readString();
      this.I = var1.readLong();
      this.J = var1.readLong();
      this.K = var1.readLong();
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeInt(this.b);
      var1.writeString(this.c);
      var1.writeByte((byte)this.d);
      var1.writeString(this.e);
      var1.writeString(this.f);
      var1.writeString(this.g);
      var1.writeByte((byte)this.j);
      var1.writeByte((byte)this.k);
      var1.writeInt(this.l);
      var1.writeString(this.m);
      var1.writeString(this.n);
      var1.writeString(this.o);
      var1.writeString(this.p);
      var1.writeString(this.q);
      var1.writeLong(this.r);
      var1.writeString(this.s);
      var1.writeInt(this.t);
      var1.writeString(this.u);
      var1.writeString(this.v);
      var1.writeString(this.w);
      ap.b(var1, this.z);
      var1.writeString(this.A);
      var1.writeString(this.B);
      var1.writeLong(this.C);
      var1.writeLong(this.D);
      var1.writeLong(this.E);
      var1.writeLong(this.F);
      var1.writeLong(this.G);
      var1.writeLong(this.H);
      var1.writeString(this.L);
      var1.writeString(this.M);
      var1.writeString(this.N);
      var1.writeString(this.O);
      var1.writeString(this.P);
      var1.writeLong(this.Q);
      var1.writeByte((byte)this.R);
      ap.b(var1, this.S);
      ap.a(var1, this.h);
      ap.a(var1, this.i);
      var1.writeInt(this.U);
      var1.writeInt(this.V);
      ap.b(var1, this.W);
      ap.b(var1, this.X);
      var1.writeByteArray(this.Y);
      var1.writeByteArray(this.y);
      var1.writeString(this.Z);
      var1.writeString(this.aa);
      var1.writeString(this.x);
      var1.writeLong(this.I);
      var1.writeLong(this.J);
      var1.writeLong(this.K);
   }
}
