package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.proguard.ap;
import java.util.Map;

public class StrategyBean implements Parcelable {
   public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      // $FF: synthetic method
      public final Object createFromParcel(Parcel var1) {
         return new StrategyBean(var1);
      }
   };
   public static String a;
   public static String b;
   public static String c;
   public long d = -1L;
   public long e = -1L;
   public boolean f;
   public boolean g;
   public boolean h;
   public boolean i;
   public boolean j;
   public boolean k;
   public boolean l;
   public boolean m;
   public boolean n;
   public long o;
   public long p;
   public String q;
   public String r;
   public String s;
   public Map t;
   public int u;
   public long v;
   public long w;

   public StrategyBean() {
      this.f = true;
      this.g = true;
      this.h = true;
      this.i = true;
      this.j = false;
      this.k = true;
      this.l = true;
      this.m = true;
      this.n = true;
      this.p = 30000L;
      this.q = a;
      this.r = b;
      this.u = 10;
      this.v = 300000L;
      this.w = -1L;
      this.e = System.currentTimeMillis();
      StringBuilder var1 = new StringBuilder();
      var1.append("S(@L@L@)");
      c = var1.toString();
      var1.setLength(0);
      var1.append("*^@K#K@!");
      this.s = var1.toString();
   }

   public StrategyBean(Parcel var1) {
      boolean var3 = true;
      this.f = true;
      this.g = true;
      this.h = true;
      this.i = true;
      this.j = false;
      this.k = true;
      this.l = true;
      this.m = true;
      this.n = true;
      this.p = 30000L;
      this.q = a;
      this.r = b;
      this.u = 10;
      this.v = 300000L;
      this.w = -1L;

      Exception var10000;
      label134: {
         boolean var2;
         boolean var10001;
         label129: {
            label128: {
               try {
                  StringBuilder var4 = new StringBuilder();
                  var4.append("S(@L@L@)");
                  c = var4.toString();
                  this.e = var1.readLong();
                  if (var1.readByte() == 1) {
                     break label128;
                  }
               } catch (Exception var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label134;
               }

               var2 = false;
               break label129;
            }

            var2 = true;
         }

         label121: {
            label120: {
               try {
                  this.f = var2;
                  if (var1.readByte() == 1) {
                     break label120;
                  }
               } catch (Exception var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label134;
               }

               var2 = false;
               break label121;
            }

            var2 = true;
         }

         label113: {
            label112: {
               try {
                  this.g = var2;
                  if (var1.readByte() == 1) {
                     break label112;
                  }
               } catch (Exception var12) {
                  var10000 = var12;
                  var10001 = false;
                  break label134;
               }

               var2 = false;
               break label113;
            }

            var2 = true;
         }

         label105: {
            label104: {
               try {
                  this.h = var2;
                  this.q = var1.readString();
                  this.r = var1.readString();
                  this.s = var1.readString();
                  this.t = ap.b(var1);
                  if (var1.readByte() == 1) {
                     break label104;
                  }
               } catch (Exception var11) {
                  var10000 = var11;
                  var10001 = false;
                  break label134;
               }

               var2 = false;
               break label105;
            }

            var2 = true;
         }

         label97: {
            label96: {
               try {
                  this.i = var2;
                  if (var1.readByte() != 1) {
                     break label96;
                  }
               } catch (Exception var10) {
                  var10000 = var10;
                  var10001 = false;
                  break label134;
               }

               var2 = true;
               break label97;
            }

            var2 = false;
         }

         label89: {
            label88: {
               try {
                  this.j = var2;
                  if (var1.readByte() == 1) {
                     break label88;
                  }
               } catch (Exception var9) {
                  var10000 = var9;
                  var10001 = false;
                  break label134;
               }

               var2 = false;
               break label89;
            }

            var2 = true;
         }

         label81: {
            label80: {
               try {
                  this.m = var2;
                  if (var1.readByte() == 1) {
                     break label80;
                  }
               } catch (Exception var8) {
                  var10000 = var8;
                  var10001 = false;
                  break label134;
               }

               var2 = false;
               break label81;
            }

            var2 = true;
         }

         label73: {
            label72: {
               try {
                  this.n = var2;
                  this.p = var1.readLong();
                  if (var1.readByte() == 1) {
                     break label72;
                  }
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label134;
               }

               var2 = false;
               break label73;
            }

            var2 = true;
         }

         label65: {
            label64: {
               try {
                  this.k = var2;
                  if (var1.readByte() == 1) {
                     break label64;
                  }
               } catch (Exception var6) {
                  var10000 = var6;
                  var10001 = false;
                  break label134;
               }

               var2 = false;
               break label65;
            }

            var2 = var3;
         }

         try {
            this.l = var2;
            this.o = var1.readLong();
            this.u = var1.readInt();
            this.v = var1.readLong();
            this.w = var1.readLong();
            return;
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
         }
      }

      Exception var15 = var10000;
      var15.printStackTrace();
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeLong(this.e);
      var1.writeByte((byte)this.f);
      var1.writeByte((byte)this.g);
      var1.writeByte((byte)this.h);
      var1.writeString(this.q);
      var1.writeString(this.r);
      var1.writeString(this.s);
      ap.b(var1, this.t);
      var1.writeByte((byte)this.i);
      var1.writeByte((byte)this.j);
      var1.writeByte((byte)this.m);
      var1.writeByte((byte)this.n);
      var1.writeLong(this.p);
      var1.writeByte((byte)this.k);
      var1.writeByte((byte)this.l);
      var1.writeLong(this.o);
      var1.writeInt(this.u);
      var1.writeLong(this.v);
      var1.writeLong(this.w);
   }
}
