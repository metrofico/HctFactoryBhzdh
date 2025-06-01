package kotlin.collections.builders;

import java.io.Externalizable;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0019\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"},
   d2 = {"Lkotlin/collections/builders/SerializedCollection;", "Ljava/io/Externalizable;", "()V", "collection", "", "tag", "", "(Ljava/util/Collection;I)V", "readExternal", "", "input", "Ljava/io/ObjectInput;", "readResolve", "", "writeExternal", "output", "Ljava/io/ObjectOutput;", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class SerializedCollection implements Externalizable {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final long serialVersionUID = 0L;
   public static final int tagList = 0;
   public static final int tagSet = 1;
   private Collection collection;
   private final int tag;

   public SerializedCollection() {
      this((Collection)CollectionsKt.emptyList(), 0);
   }

   public SerializedCollection(Collection var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "collection");
      super();
      this.collection = var1;
      this.tag = var2;
   }

   private final Object readResolve() {
      return this.collection;
   }

   public void readExternal(ObjectInput var1) {
      Intrinsics.checkNotNullParameter(var1, "input");
      int var2 = var1.readByte();
      int var5 = var2 & 1;
      if ((var2 & -2) == 0) {
         int var4 = var1.readInt();
         if (var4 < 0) {
            throw new InvalidObjectException("Illegal size value: " + var4 + '.');
         } else {
            byte var3 = 0;
            var2 = 0;
            Collection var7;
            if (var5 != 0) {
               if (var5 != 1) {
                  throw new InvalidObjectException("Unsupported collection type tag: " + var5 + '.');
               }

               Set var6;
               for(var6 = SetsKt.createSetBuilder(var4); var2 < var4; ++var2) {
                  var6.add(var1.readObject());
               }

               var7 = (Collection)SetsKt.build(var6);
            } else {
               List var8 = CollectionsKt.createListBuilder(var4);

               for(var2 = var3; var2 < var4; ++var2) {
                  var8.add(var1.readObject());
               }

               var7 = (Collection)CollectionsKt.build(var8);
            }

            this.collection = var7;
         }
      } else {
         throw new InvalidObjectException("Unsupported flags value: " + var2 + '.');
      }
   }

   public void writeExternal(ObjectOutput var1) {
      Intrinsics.checkNotNullParameter(var1, "output");
      var1.writeByte(this.tag);
      var1.writeInt(this.collection.size());
      Iterator var2 = this.collection.iterator();

      while(var2.hasNext()) {
         var1.writeObject(var2.next());
      }

   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"},
      d2 = {"Lkotlin/collections/builders/SerializedCollection$Companion;", "", "()V", "serialVersionUID", "", "tagList", "", "tagSet", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }
   }
}
