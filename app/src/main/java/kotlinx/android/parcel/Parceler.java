package kotlinx.android.parcel;

import android.os.Parcel;
import kotlin.Metadata;
import kotlin.NotImplementedError;

@Metadata(
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ!\u0010\f\u001a\u00020\r*\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\nH&¢\u0006\u0002\u0010\u000f¨\u0006\u0010"},
   d2 = {"Lkotlinx/android/parcel/Parceler;", "T", "", "create", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)Ljava/lang/Object;", "newArray", "", "size", "", "(I)[Ljava/lang/Object;", "write", "", "flags", "(Ljava/lang/Object;Landroid/os/Parcel;I)V", "kotlin-android-extensions-runtime"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public interface Parceler {
   Object create(Parcel var1);

   Object[] newArray(int var1);

   void write(Object var1, Parcel var2, int var3);

   @Metadata(
      k = 3,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class DefaultImpls {
      public static Object[] newArray(Parceler var0, int var1) {
         throw new NotImplementedError("Generated by Android Extensions automatically");
      }
   }
}
