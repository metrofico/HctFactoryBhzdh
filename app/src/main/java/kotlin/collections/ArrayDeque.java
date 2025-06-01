package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(
   d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0007\u0018\u0000 P*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001PB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0007\b\u0016¢\u0006\u0002\u0010\u0006B\u0015\b\u0016\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0016J\u001d\u0010\u0013\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0016\u0010\u001a\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0013\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00028\u0000¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00028\u0000¢\u0006\u0002\u0010\u001cJ\b\u0010\u001e\u001a\u00020\u0017H\u0016J\u0016\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0016J\u001e\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0002J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004H\u0002J\u0010\u0010$\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0004H\u0002J\u001d\u0010'\u001a\u00020\u00142\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140)H\u0082\bJ\u000b\u0010*\u001a\u00028\u0000¢\u0006\u0002\u0010+J\r\u0010,\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010+J\u0016\u0010-\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0004H\u0096\u0002¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0015\u00100\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00101J\u0016\u00102\u001a\u00028\u00002\u0006\u0010!\u001a\u00020\u0004H\u0083\b¢\u0006\u0002\u0010.J\u0011\u0010!\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0083\bJM\u00103\u001a\u00020\u00172>\u00104\u001a:\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(\u000e\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u001705H\u0000¢\u0006\u0002\b8J\b\u00109\u001a\u00020\u0014H\u0016J\u000b\u0010:\u001a\u00028\u0000¢\u0006\u0002\u0010+J\u0015\u0010;\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00101J\r\u0010<\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010+J\u0010\u0010=\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010>\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0015\u0010?\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0016J\u0016\u0010@\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0015\u0010A\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0004H\u0016¢\u0006\u0002\u0010.J\u000b\u0010B\u001a\u00028\u0000¢\u0006\u0002\u0010+J\r\u0010C\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010+J\u000b\u0010D\u001a\u00028\u0000¢\u0006\u0002\u0010+J\r\u0010E\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010+J\u0016\u0010F\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u001e\u0010G\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010HJ\u0017\u0010I\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0000¢\u0006\u0004\bJ\u0010KJ)\u0010I\u001a\b\u0012\u0004\u0012\u0002HL0\u000b\"\u0004\b\u0001\u0010L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HL0\u000bH\u0000¢\u0006\u0004\bJ\u0010NJ\u0015\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0016¢\u0006\u0002\u0010KJ'\u0010O\u001a\b\u0012\u0004\u0012\u0002HL0\u000b\"\u0004\b\u0001\u0010L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HL0\u000bH\u0016¢\u0006\u0002\u0010NR\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006Q"},
   d2 = {"Lkotlin/collections/ArrayDeque;", "E", "Lkotlin/collections/AbstractMutableList;", "initialCapacity", "", "(I)V", "()V", "elements", "", "(Ljava/util/Collection;)V", "elementData", "", "", "[Ljava/lang/Object;", "head", "<set-?>", "size", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "addFirst", "(Ljava/lang/Object;)V", "addLast", "clear", "contains", "copyCollectionElements", "internalIndex", "copyElements", "newCapacity", "decremented", "ensureCapacity", "minCapacity", "filterInPlace", "predicate", "Lkotlin/Function1;", "first", "()Ljava/lang/Object;", "firstOrNull", "get", "(I)Ljava/lang/Object;", "incremented", "indexOf", "(Ljava/lang/Object;)I", "internalGet", "internalStructure", "structure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "internalStructure$kotlin_stdlib", "isEmpty", "last", "lastIndexOf", "lastOrNull", "negativeMod", "positiveMod", "remove", "removeAll", "removeAt", "removeFirst", "removeFirstOrNull", "removeLast", "removeLastOrNull", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "testToArray", "testToArray$kotlin_stdlib", "()[Ljava/lang/Object;", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toArray", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ArrayDeque extends AbstractMutableList {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final int defaultMinCapacity = 10;
   private static final Object[] emptyElementData = new Object[0];
   private static final int maxArraySize = 2147483639;
   private Object[] elementData;
   private int head;
   private int size;

   public ArrayDeque() {
      this.elementData = emptyElementData;
   }

   public ArrayDeque(int var1) {
      Object[] var2;
      if (var1 == 0) {
         var2 = emptyElementData;
      } else {
         if (var1 <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + var1);
         }

         var2 = new Object[var1];
      }

      this.elementData = var2;
   }

   public ArrayDeque(Collection var1) {
      Intrinsics.checkNotNullParameter(var1, "elements");
      super();
      boolean var2 = false;
      Object[] var3 = var1.toArray(new Object[0]);
      Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
      this.elementData = var3;
      this.size = var3.length;
      if (var3.length == 0) {
         var2 = true;
      }

      if (var2) {
         this.elementData = emptyElementData;
      }

   }

   private final void copyCollectionElements(int var1, Collection var2) {
      Iterator var4 = var2.iterator();

      int var3;
      for(var3 = this.elementData.length; var1 < var3 && var4.hasNext(); ++var1) {
         this.elementData[var1] = var4.next();
      }

      var1 = 0;

      for(var3 = this.head; var1 < var3 && var4.hasNext(); ++var1) {
         this.elementData[var1] = var4.next();
      }

      this.size = this.size() + var2.size();
   }

   private final void copyElements(int var1) {
      Object[] var3 = new Object[var1];
      Object[] var4 = this.elementData;
      ArraysKt.copyInto(var4, var3, 0, this.head, var4.length);
      var4 = this.elementData;
      int var2 = var4.length;
      var1 = this.head;
      ArraysKt.copyInto(var4, var3, var2 - var1, 0, var1);
      this.head = 0;
      this.elementData = var3;
   }

   private final int decremented(int var1) {
      if (var1 == 0) {
         var1 = ArraysKt.getLastIndex(this.elementData);
      } else {
         --var1;
      }

      return var1;
   }

   private final void ensureCapacity(int var1) {
      if (var1 >= 0) {
         Object[] var2 = this.elementData;
         if (var1 > var2.length) {
            if (var2 == emptyElementData) {
               this.elementData = new Object[RangesKt.coerceAtLeast(var1, 10)];
            } else {
               this.copyElements(Companion.newCapacity$kotlin_stdlib(var2.length, var1));
            }
         }
      } else {
         throw new IllegalStateException("Deque is too big.");
      }
   }

   private final boolean filterInPlace(Function1 var1) {
      boolean var10 = this.isEmpty();
      byte var4 = 0;
      boolean var7 = false;
      boolean var9 = false;
      boolean var8 = var7;
      if (!var10) {
         boolean var2;
         if (this.elementData.length == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            var8 = var7;
         } else {
            int var5 = this.positiveMod(this.head + this.size());
            int var13 = this.head;
            int var3;
            if (var13 < var5) {
               var3 = var13;

               for(var7 = var9; var13 < var5; ++var13) {
                  Object var14 = this.elementData[var13];
                  if ((Boolean)var1.invoke(var14)) {
                     this.elementData[var3] = var14;
                     ++var3;
                  } else {
                     var7 = true;
                  }
               }

               ArraysKt.fill(this.elementData, (Object)null, var3, var5);
               var13 = var3;
            } else {
               int var6 = this.elementData.length;
               var7 = false;

               Object[] var11;
               Object var12;
               for(var3 = var13; var13 < var6; ++var13) {
                  var11 = this.elementData;
                  var12 = var11[var13];
                  var11[var13] = null;
                  if ((Boolean)var1.invoke(var12)) {
                     this.elementData[var3] = var12;
                     ++var3;
                  } else {
                     var7 = true;
                  }
               }

               var13 = this.positiveMod(var3);

               for(var3 = var4; var3 < var5; ++var3) {
                  var11 = this.elementData;
                  var12 = var11[var3];
                  var11[var3] = null;
                  if ((Boolean)var1.invoke(var12)) {
                     this.elementData[var13] = var12;
                     var13 = this.incremented(var13);
                  } else {
                     var7 = true;
                  }
               }
            }

            var8 = var7;
            if (var7) {
               this.size = this.negativeMod(var13 - this.head);
               var8 = var7;
            }
         }
      }

      return var8;
   }

   private final int incremented(int var1) {
      if (var1 == ArraysKt.getLastIndex(this.elementData)) {
         var1 = 0;
      } else {
         ++var1;
      }

      return var1;
   }

   private final Object internalGet(int var1) {
      return this.elementData[var1];
   }

   private final int internalIndex(int var1) {
      return this.positiveMod(this.head + var1);
   }

   private final int negativeMod(int var1) {
      int var2 = var1;
      if (var1 < 0) {
         var2 = var1 + this.elementData.length;
      }

      return var2;
   }

   private final int positiveMod(int var1) {
      Object[] var3 = this.elementData;
      int var2 = var1;
      if (var1 >= var3.length) {
         var2 = var1 - var3.length;
      }

      return var2;
   }

   public void add(int var1, Object var2) {
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(var1, this.size());
      if (var1 == this.size()) {
         this.addLast(var2);
      } else if (var1 == 0) {
         this.addFirst(var2);
      } else {
         this.ensureCapacity(this.size() + 1);
         int var3 = this.positiveMod(this.head + var1);
         Object[] var5;
         if (var1 < this.size() + 1 >> 1) {
            int var4 = this.decremented(var3);
            var3 = this.decremented(this.head);
            var1 = this.head;
            if (var4 >= var1) {
               var5 = this.elementData;
               var5[var3] = var5[var1];
               ArraysKt.copyInto(var5, var5, var1, var1 + 1, var4 + 1);
            } else {
               var5 = this.elementData;
               ArraysKt.copyInto(var5, var5, var1 - 1, var1, var5.length);
               var5 = this.elementData;
               var5[var5.length - 1] = var5[0];
               ArraysKt.copyInto(var5, var5, 0, 1, var4 + 1);
            }

            this.elementData[var4] = var2;
            this.head = var3;
         } else {
            var1 = this.positiveMod(this.head + this.size());
            if (var3 < var1) {
               var5 = this.elementData;
               ArraysKt.copyInto(var5, var5, var3 + 1, var3, var1);
            } else {
               var5 = this.elementData;
               ArraysKt.copyInto(var5, var5, 1, 0, var1);
               var5 = this.elementData;
               var5[0] = var5[var5.length - 1];
               ArraysKt.copyInto(var5, var5, var3 + 1, var3, var5.length - 1);
            }

            this.elementData[var3] = var2;
         }

         this.size = this.size() + 1;
      }
   }

   public boolean add(Object var1) {
      this.addLast(var1);
      return true;
   }

   public boolean addAll(int var1, Collection var2) {
      Intrinsics.checkNotNullParameter(var2, "elements");
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(var1, this.size());
      if (var2.isEmpty()) {
         return false;
      } else if (var1 == this.size()) {
         return this.addAll(var2);
      } else {
         this.ensureCapacity(this.size() + var2.size());
         int var5 = this.positiveMod(this.head + this.size());
         int var3 = this.positiveMod(this.head + var1);
         int var4 = var2.size();
         Object[] var7;
         if (var1 < this.size() + 1 >> 1) {
            var5 = this.head;
            var1 = var5 - var4;
            if (var3 >= var5) {
               if (var1 >= 0) {
                  var7 = this.elementData;
                  ArraysKt.copyInto(var7, var7, var1, var5, var3);
               } else {
                  var7 = this.elementData;
                  var1 += var7.length;
                  int var6 = var7.length - var1;
                  if (var6 >= var3 - var5) {
                     ArraysKt.copyInto(var7, var7, var1, var5, var3);
                  } else {
                     ArraysKt.copyInto(var7, var7, var1, var5, var5 + var6);
                     var7 = this.elementData;
                     ArraysKt.copyInto(var7, var7, 0, this.head + var6, var3);
                  }
               }
            } else {
               var7 = this.elementData;
               ArraysKt.copyInto(var7, var7, var1, var5, var7.length);
               if (var4 >= var3) {
                  var7 = this.elementData;
                  ArraysKt.copyInto(var7, var7, var7.length - var4, 0, var3);
               } else {
                  var7 = this.elementData;
                  ArraysKt.copyInto(var7, var7, var7.length - var4, 0, var4);
                  var7 = this.elementData;
                  ArraysKt.copyInto(var7, var7, 0, var4, var3);
               }
            }

            this.head = var1;
            this.copyCollectionElements(this.negativeMod(var3 - var4), var2);
         } else {
            var1 = var3 + var4;
            if (var3 < var5) {
               var4 += var5;
               var7 = this.elementData;
               if (var4 <= var7.length) {
                  ArraysKt.copyInto(var7, var7, var1, var3, var5);
               } else if (var1 >= var7.length) {
                  ArraysKt.copyInto(var7, var7, var1 - var7.length, var3, var5);
               } else {
                  var4 = var5 - (var4 - var7.length);
                  ArraysKt.copyInto(var7, var7, 0, var4, var5);
                  var7 = this.elementData;
                  ArraysKt.copyInto(var7, var7, var1, var3, var4);
               }
            } else {
               var7 = this.elementData;
               ArraysKt.copyInto(var7, var7, var4, 0, var5);
               var7 = this.elementData;
               if (var1 >= var7.length) {
                  ArraysKt.copyInto(var7, var7, var1 - var7.length, var3, var7.length);
               } else {
                  ArraysKt.copyInto(var7, var7, 0, var7.length - var4, var7.length);
                  var7 = this.elementData;
                  ArraysKt.copyInto(var7, var7, var1, var3, var7.length - var4);
               }
            }

            this.copyCollectionElements(var3, var2);
         }

         return true;
      }
   }

   public boolean addAll(Collection var1) {
      Intrinsics.checkNotNullParameter(var1, "elements");
      if (var1.isEmpty()) {
         return false;
      } else {
         this.ensureCapacity(this.size() + var1.size());
         this.copyCollectionElements(this.positiveMod(this.head + this.size()), var1);
         return true;
      }
   }

   public final void addFirst(Object var1) {
      this.ensureCapacity(this.size() + 1);
      int var2 = this.decremented(this.head);
      this.head = var2;
      this.elementData[var2] = var1;
      this.size = this.size() + 1;
   }

   public final void addLast(Object var1) {
      this.ensureCapacity(this.size() + 1);
      this.elementData[this.positiveMod(this.head + this.size())] = var1;
      this.size = this.size() + 1;
   }

   public void clear() {
      int var2 = this.positiveMod(this.head + this.size());
      int var1 = this.head;
      if (var1 < var2) {
         ArraysKt.fill(this.elementData, (Object)null, var1, var2);
      } else if (((Collection)this).isEmpty() ^ true) {
         Object[] var3 = this.elementData;
         ArraysKt.fill(var3, (Object)null, this.head, var3.length);
         ArraysKt.fill(this.elementData, (Object)null, 0, var2);
      }

      this.head = 0;
      this.size = 0;
   }

   public boolean contains(Object var1) {
      boolean var2;
      if (this.indexOf(var1) != -1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public final Object first() {
      if (!this.isEmpty()) {
         return this.elementData[this.head];
      } else {
         throw new NoSuchElementException("ArrayDeque is empty.");
      }
   }

   public final Object firstOrNull() {
      Object var1;
      if (this.isEmpty()) {
         var1 = null;
      } else {
         var1 = this.elementData[this.head];
      }

      return var1;
   }

   public Object get(int var1) {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(var1, this.size());
      return this.elementData[this.positiveMod(this.head + var1)];
   }

   public int getSize() {
      return this.size;
   }

   public int indexOf(Object var1) {
      int var3 = this.positiveMod(this.head + this.size());
      int var2 = this.head;
      if (var2 < var3) {
         while(true) {
            if (var2 >= var3) {
               return -1;
            }

            if (Intrinsics.areEqual(var1, this.elementData[var2])) {
               var3 = this.head;
               break;
            }

            ++var2;
         }
      } else {
         if (var2 < var3) {
            return -1;
         }

         int var4 = this.elementData.length;

         while(true) {
            if (var2 >= var4) {
               for(var2 = 0; var2 < var3; ++var2) {
                  if (Intrinsics.areEqual(var1, this.elementData[var2])) {
                     var2 += this.elementData.length;
                     var3 = this.head;
                     return var2 - var3;
                  }
               }

               return -1;
            }

            if (Intrinsics.areEqual(var1, this.elementData[var2])) {
               var3 = this.head;
               break;
            }

            ++var2;
         }
      }

      return var2 - var3;
   }

   public final void internalStructure$kotlin_stdlib(Function2 var1) {
      int var2;
      label13: {
         Intrinsics.checkNotNullParameter(var1, "structure");
         var2 = this.positiveMod(this.head + this.size());
         if (!this.isEmpty()) {
            int var3 = this.head;
            if (var3 >= var2) {
               var2 = var3 - this.elementData.length;
               break label13;
            }
         }

         var2 = this.head;
      }

      var1.invoke(var2, this.toArray());
   }

   public boolean isEmpty() {
      boolean var1;
      if (this.size() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final Object last() {
      if (!this.isEmpty()) {
         return this.elementData[this.positiveMod(this.head + CollectionsKt.getLastIndex((List)this))];
      } else {
         throw new NoSuchElementException("ArrayDeque is empty.");
      }
   }

   public int lastIndexOf(Object var1) {
      int var2 = this.positiveMod(this.head + this.size());
      int var3 = this.head;
      if (var3 < var2) {
         --var2;
         if (var3 > var2) {
            return -1;
         }

         while(!Intrinsics.areEqual(var1, this.elementData[var2])) {
            if (var2 == var3) {
               return -1;
            }

            --var2;
         }

         var3 = this.head;
      } else {
         if (var3 <= var2) {
            return -1;
         }

         --var2;

         while(true) {
            if (-1 >= var2) {
               var2 = ArraysKt.getLastIndex(this.elementData);
               var3 = this.head;
               if (var3 > var2) {
                  return -1;
               }

               while(!Intrinsics.areEqual(var1, this.elementData[var2])) {
                  if (var2 == var3) {
                     return -1;
                  }

                  --var2;
               }

               var3 = this.head;
               break;
            }

            if (Intrinsics.areEqual(var1, this.elementData[var2])) {
               var2 += this.elementData.length;
               var3 = this.head;
               break;
            }

            --var2;
         }
      }

      return var2 - var3;
   }

   public final Object lastOrNull() {
      Object var1;
      if (this.isEmpty()) {
         var1 = null;
      } else {
         var1 = this.elementData[this.positiveMod(this.head + CollectionsKt.getLastIndex((List)this))];
      }

      return var1;
   }

   public boolean remove(Object var1) {
      int var2 = this.indexOf(var1);
      if (var2 == -1) {
         return false;
      } else {
         this.remove(var2);
         return true;
      }
   }

   public boolean removeAll(Collection var1) {
      Intrinsics.checkNotNullParameter(var1, "elements");
      boolean var10 = this.isEmpty();
      byte var4 = 0;
      boolean var9 = false;
      boolean var7 = false;
      boolean var8 = var9;
      if (!var10) {
         boolean var2;
         if (this.elementData.length == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            var8 = var9;
         } else {
            int var5 = this.positiveMod(this.head + this.size());
            int var13 = this.head;
            int var3;
            Object var14;
            if (var13 < var5) {
               for(var3 = var13; var13 < var5; ++var13) {
                  var14 = this.elementData[var13];
                  if (var1.contains(var14) ^ true) {
                     this.elementData[var3] = var14;
                     ++var3;
                  } else {
                     var7 = true;
                  }
               }

               ArraysKt.fill(this.elementData, (Object)null, var3, var5);
               var13 = var3;
            } else {
               int var6 = this.elementData.length;
               var7 = false;

               for(var3 = var13; var13 < var6; ++var13) {
                  Object[] var11 = this.elementData;
                  Object var12 = var11[var13];
                  var11[var13] = null;
                  if (var1.contains(var12) ^ true) {
                     this.elementData[var3] = var12;
                     ++var3;
                  } else {
                     var7 = true;
                  }
               }

               var13 = this.positiveMod(var3);

               for(var3 = var4; var3 < var5; ++var3) {
                  Object[] var15 = this.elementData;
                  var14 = var15[var3];
                  var15[var3] = null;
                  if (var1.contains(var14) ^ true) {
                     this.elementData[var13] = var14;
                     var13 = this.incremented(var13);
                  } else {
                     var7 = true;
                  }
               }
            }

            var8 = var7;
            if (var7) {
               this.size = this.negativeMod(var13 - this.head);
               var8 = var7;
            }
         }
      }

      return var8;
   }

   public Object removeAt(int var1) {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(var1, this.size());
      List var4 = (List)this;
      if (var1 == CollectionsKt.getLastIndex(var4)) {
         return this.removeLast();
      } else if (var1 == 0) {
         return this.removeFirst();
      } else {
         int var2 = this.positiveMod(this.head + var1);
         Object var3 = this.elementData[var2];
         Object[] var5;
         if (var1 < this.size() >> 1) {
            var1 = this.head;
            if (var2 >= var1) {
               var5 = this.elementData;
               ArraysKt.copyInto(var5, var5, var1 + 1, var1, var2);
            } else {
               var5 = this.elementData;
               ArraysKt.copyInto(var5, var5, 1, 0, var2);
               var5 = this.elementData;
               var5[0] = var5[var5.length - 1];
               var1 = this.head;
               ArraysKt.copyInto(var5, var5, var1 + 1, var1, var5.length - 1);
            }

            var5 = this.elementData;
            var1 = this.head;
            var5[var1] = null;
            this.head = this.incremented(var1);
         } else {
            var1 = this.positiveMod(this.head + CollectionsKt.getLastIndex(var4));
            if (var2 <= var1) {
               var5 = this.elementData;
               ArraysKt.copyInto(var5, var5, var2, var2 + 1, var1 + 1);
            } else {
               var5 = this.elementData;
               ArraysKt.copyInto(var5, var5, var2, var2 + 1, var5.length);
               var5 = this.elementData;
               var5[var5.length - 1] = var5[0];
               ArraysKt.copyInto(var5, var5, 0, 1, var1 + 1);
            }

            this.elementData[var1] = null;
         }

         this.size = this.size() - 1;
         return var3;
      }
   }

   public final Object removeFirst() {
      if (!this.isEmpty()) {
         Object[] var2 = this.elementData;
         int var1 = this.head;
         Object var3 = var2[var1];
         var2[var1] = null;
         this.head = this.incremented(var1);
         this.size = this.size() - 1;
         return var3;
      } else {
         throw new NoSuchElementException("ArrayDeque is empty.");
      }
   }

   public final Object removeFirstOrNull() {
      Object var1;
      if (this.isEmpty()) {
         var1 = null;
      } else {
         var1 = this.removeFirst();
      }

      return var1;
   }

   public final Object removeLast() {
      if (!this.isEmpty()) {
         int var1 = this.positiveMod(this.head + CollectionsKt.getLastIndex((List)this));
         Object[] var2 = this.elementData;
         Object var3 = var2[var1];
         var2[var1] = null;
         this.size = this.size() - 1;
         return var3;
      } else {
         throw new NoSuchElementException("ArrayDeque is empty.");
      }
   }

   public final Object removeLastOrNull() {
      Object var1;
      if (this.isEmpty()) {
         var1 = null;
      } else {
         var1 = this.removeLast();
      }

      return var1;
   }

   public boolean retainAll(Collection var1) {
      Intrinsics.checkNotNullParameter(var1, "elements");
      boolean var10 = this.isEmpty();
      byte var4 = 0;
      boolean var9 = false;
      boolean var7 = false;
      boolean var8 = var9;
      if (!var10) {
         boolean var2;
         if (this.elementData.length == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            var8 = var9;
         } else {
            int var5 = this.positiveMod(this.head + this.size());
            int var13 = this.head;
            int var3;
            if (var13 < var5) {
               for(var3 = var13; var13 < var5; ++var13) {
                  Object var14 = this.elementData[var13];
                  if (var1.contains(var14)) {
                     this.elementData[var3] = var14;
                     ++var3;
                  } else {
                     var7 = true;
                  }
               }

               ArraysKt.fill(this.elementData, (Object)null, var3, var5);
               var13 = var3;
            } else {
               int var6 = this.elementData.length;
               var7 = false;

               Object[] var11;
               Object var12;
               for(var3 = var13; var13 < var6; ++var13) {
                  var11 = this.elementData;
                  var12 = var11[var13];
                  var11[var13] = null;
                  if (var1.contains(var12)) {
                     this.elementData[var3] = var12;
                     ++var3;
                  } else {
                     var7 = true;
                  }
               }

               var13 = this.positiveMod(var3);

               for(var3 = var4; var3 < var5; ++var3) {
                  var11 = this.elementData;
                  var12 = var11[var3];
                  var11[var3] = null;
                  if (var1.contains(var12)) {
                     this.elementData[var13] = var12;
                     var13 = this.incremented(var13);
                  } else {
                     var7 = true;
                  }
               }
            }

            var8 = var7;
            if (var7) {
               this.size = this.negativeMod(var13 - this.head);
               var8 = var7;
            }
         }
      }

      return var8;
   }

   public Object set(int var1, Object var2) {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(var1, this.size());
      var1 = this.positiveMod(this.head + var1);
      Object[] var4 = this.elementData;
      Object var3 = var4[var1];
      var4[var1] = var2;
      return var3;
   }

   public final Object[] testToArray$kotlin_stdlib() {
      return this.toArray();
   }

   public final Object[] testToArray$kotlin_stdlib(Object[] var1) {
      Intrinsics.checkNotNullParameter(var1, "array");
      return this.toArray(var1);
   }

   public Object[] toArray() {
      return this.toArray(new Object[this.size()]);
   }

   public Object[] toArray(Object[] var1) {
      Intrinsics.checkNotNullParameter(var1, "array");
      if (var1.length < this.size()) {
         var1 = ArraysKt.arrayOfNulls(var1, this.size());
      }

      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
      int var2 = this.positiveMod(this.head + this.size());
      int var3 = this.head;
      if (var3 < var2) {
         ArraysKt.copyInto$default(this.elementData, var1, 0, var3, var2, 2, (Object)null);
      } else if (((Collection)this).isEmpty() ^ true) {
         Object[] var4 = this.elementData;
         ArraysKt.copyInto(var4, var1, 0, this.head, var4.length);
         var4 = this.elementData;
         ArraysKt.copyInto(var4, var1, var4.length - this.head, 0, var2);
      }

      if (var1.length > this.size()) {
         var1[this.size()] = null;
      }

      return var1;
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"},
      d2 = {"Lkotlin/collections/ArrayDeque$Companion;", "", "()V", "defaultMinCapacity", "", "emptyElementData", "", "[Ljava/lang/Object;", "maxArraySize", "newCapacity", "oldCapacity", "minCapacity", "newCapacity$kotlin_stdlib", "kotlin-stdlib"},
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

      public final int newCapacity$kotlin_stdlib(int var1, int var2) {
         int var3 = var1 + (var1 >> 1);
         var1 = var3;
         if (var3 - var2 < 0) {
            var1 = var2;
         }

         var3 = var1;
         if (var1 - 2147483639 > 0) {
            if (var2 > 2147483639) {
               var3 = Integer.MAX_VALUE;
            } else {
               var3 = 2147483639;
            }
         }

         return var3;
      }
   }
}
