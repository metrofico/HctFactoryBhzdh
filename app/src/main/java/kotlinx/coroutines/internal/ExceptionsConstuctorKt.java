package kotlinx.coroutines.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u001a*\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0006j\u0004\u0018\u0001`\u00072\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0002\u001a1\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006j\u0002`\u00072\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0006H\u0082\b\u001a!\u0010\u000f\u001a\u0004\u0018\u0001H\u0010\"\b\b\u0000\u0010\u0010*\u00020\u00052\u0006\u0010\u0011\u001a\u0002H\u0010H\u0000¢\u0006\u0002\u0010\u0012\u001a\u001b\u0010\u0013\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\u00042\b\b\u0002\u0010\u0014\u001a\u00020\tH\u0082\u0010\u001a\u0018\u0010\u0015\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0016\u001a\u00020\tH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"4\u0010\u0002\u001a(\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006j\u0002`\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000*(\b\u0002\u0010\u0017\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00062\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006¨\u0006\u0018"},
   d2 = {"cacheLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "exceptionCtors", "Ljava/util/WeakHashMap;", "Ljava/lang/Class;", "", "Lkotlin/Function1;", "Lkotlinx/coroutines/internal/Ctor;", "throwableFields", "", "createConstructor", "constructor", "Ljava/lang/reflect/Constructor;", "safeCtor", "block", "tryCopyException", "E", "exception", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "fieldsCount", "accumulator", "fieldsCountOrDefault", "defaultValue", "Ctor", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class ExceptionsConstuctorKt {
   private static final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
   private static final WeakHashMap exceptionCtors = new WeakHashMap();
   private static final int throwableFields = fieldsCountOrDefault(Throwable.class, -1);

   private static final Function1 createConstructor(Constructor var0) {
      Class[] var4 = var0.getParameterTypes();
      int var1 = var4.length;
      Object var3 = null;
      Function1 var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = (Function1)var3;
            } else {
               var2 = (Function1)var3;
               if (Intrinsics.areEqual((Object)var4[0], (Object)String.class)) {
                  var2 = (Function1)var3;
                  if (Intrinsics.areEqual((Object)var4[1], (Object)Throwable.class)) {
                     var2 = (Function1)(new Function1(var0) {
                        final Constructor $constructor$inlined;

                        public {
                           this.$constructor$inlined = var1;
                        }

                        public final Throwable invoke(Throwable var1) {
                           Object var15;
                           label115: {
                              Throwable var10000;
                              Result.Companion var2;
                              label114: {
                                 boolean var10001;
                                 try {
                                    var2 = Result.Companion;
                                    var15 = this.$constructor$inlined.newInstance(var1.getMessage(), var1);
                                 } catch (Throwable var14) {
                                    var10000 = var14;
                                    var10001 = false;
                                    break label114;
                                 }

                                 if (var15 != null) {
                                    label110:
                                    try {
                                       var15 = Result.constructor_impl((Throwable)var15);
                                       break label115;
                                    } catch (Throwable var13) {
                                       var10000 = var13;
                                       var10001 = false;
                                       break label110;
                                    }
                                 } else {
                                    label107:
                                    try {
                                       NullPointerException var17 = new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                                       throw var17;
                                    } catch (Throwable var12) {
                                       var10000 = var12;
                                       var10001 = false;
                                       break label107;
                                    }
                                 }
                              }

                              var1 = var10000;
                              var2 = Result.Companion;
                              var15 = Result.constructor_impl(ResultKt.createFailure(var1));
                           }

                           Object var16 = var15;
                           if (Result.isFailure_impl(var15)) {
                              var16 = null;
                           }

                           return (Throwable)var16;
                        }
                     });
                  }
               }
            }
         } else {
            Class var5 = var4[0];
            if (Intrinsics.areEqual((Object)var5, (Object)Throwable.class)) {
               var2 = (Function1)(new Function1(var0) {
                  final Constructor $constructor$inlined;

                  public {
                     this.$constructor$inlined = var1;
                  }

                  public final Throwable invoke(Throwable var1) {
                     Object var15;
                     label115: {
                        Throwable var10000;
                        Result.Companion var2;
                        label114: {
                           boolean var10001;
                           try {
                              var2 = Result.Companion;
                              var15 = this.$constructor$inlined.newInstance(var1);
                           } catch (Throwable var14) {
                              var10000 = var14;
                              var10001 = false;
                              break label114;
                           }

                           if (var15 != null) {
                              label110:
                              try {
                                 var15 = Result.constructor_impl((Throwable)var15);
                                 break label115;
                              } catch (Throwable var13) {
                                 var10000 = var13;
                                 var10001 = false;
                                 break label110;
                              }
                           } else {
                              label107:
                              try {
                                 NullPointerException var17 = new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                                 throw var17;
                              } catch (Throwable var12) {
                                 var10000 = var12;
                                 var10001 = false;
                                 break label107;
                              }
                           }
                        }

                        var1 = var10000;
                        var2 = Result.Companion;
                        var15 = Result.constructor_impl(ResultKt.createFailure(var1));
                     }

                     Object var16 = var15;
                     if (Result.isFailure_impl(var15)) {
                        var16 = null;
                     }

                     return (Throwable)var16;
                  }
               });
            } else {
               var2 = (Function1)var3;
               if (Intrinsics.areEqual((Object)var5, (Object)String.class)) {
                  var2 = (Function1)(new Function1(var0) {
                     final Constructor $constructor$inlined;

                     public {
                        this.$constructor$inlined = var1;
                     }

                     public final Throwable invoke(Throwable var1) {
                        Object var15;
                        Object var17;
                        label115: {
                           Throwable var10000;
                           Throwable var19;
                           label114: {
                              boolean var10001;
                              try {
                                 Result.Companion var2 = Result.Companion;
                                 var17 = this.$constructor$inlined.newInstance(var1.getMessage());
                              } catch (Throwable var14) {
                                 var10000 = var14;
                                 var10001 = false;
                                 break label114;
                              }

                              if (var17 != null) {
                                 label110:
                                 try {
                                    var19 = (Throwable)var17;
                                    var19.initCause(var1);
                                    var15 = Result.constructor_impl(var19);
                                    break label115;
                                 } catch (Throwable var13) {
                                    var10000 = var13;
                                    var10001 = false;
                                    break label110;
                                 }
                              } else {
                                 label107:
                                 try {
                                    NullPointerException var18 = new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                                    throw var18;
                                 } catch (Throwable var12) {
                                    var10000 = var12;
                                    var10001 = false;
                                    break label107;
                                 }
                              }
                           }

                           var19 = var10000;
                           Result.Companion var16 = Result.Companion;
                           var15 = Result.constructor_impl(ResultKt.createFailure(var19));
                        }

                        var17 = var15;
                        if (Result.isFailure_impl(var15)) {
                           var17 = null;
                        }

                        return (Throwable)var17;
                     }
                  });
               }
            }
         }
      } else {
         var2 = (Function1)(new Function1(var0) {
            final Constructor $constructor$inlined;

            public {
               this.$constructor$inlined = var1;
            }

            public final Throwable invoke(Throwable var1) {
               Object var15;
               Object var17;
               label115: {
                  Throwable var10000;
                  Throwable var19;
                  label114: {
                     boolean var10001;
                     try {
                        Result.Companion var2 = Result.Companion;
                        var17 = this.$constructor$inlined.newInstance();
                     } catch (Throwable var14) {
                        var10000 = var14;
                        var10001 = false;
                        break label114;
                     }

                     if (var17 != null) {
                        label110:
                        try {
                           var19 = (Throwable)var17;
                           var19.initCause(var1);
                           var15 = Result.constructor_impl(var19);
                           break label115;
                        } catch (Throwable var13) {
                           var10000 = var13;
                           var10001 = false;
                           break label110;
                        }
                     } else {
                        label107:
                        try {
                           NullPointerException var18 = new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                           throw var18;
                        } catch (Throwable var12) {
                           var10000 = var12;
                           var10001 = false;
                           break label107;
                        }
                     }
                  }

                  var19 = var10000;
                  Result.Companion var16 = Result.Companion;
                  var15 = Result.constructor_impl(ResultKt.createFailure(var19));
               }

               var17 = var15;
               if (Result.isFailure_impl(var15)) {
                  var17 = null;
               }

               return (Throwable)var17;
            }
         });
      }

      return var2;
   }

   private static final int fieldsCount(Class var0, int var1) {
      do {
         Field[] var6 = var0.getDeclaredFields();
         int var5 = var6.length;
         int var3 = 0;

         int var2;
         int var4;
         for(var4 = 0; var3 < var5; var4 = var2) {
            var2 = var4;
            if (Modifier.isStatic(var6[var3].getModifiers()) ^ true) {
               var2 = var4 + 1;
            }

            ++var3;
         }

         var1 += var4;
         var0 = var0.getSuperclass();
      } while(var0 != null);

      return var1;
   }

   // $FF: synthetic method
   static int fieldsCount$default(Class var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 0;
      }

      return fieldsCount(var0, var1);
   }

   private static final int fieldsCountOrDefault(Class var0, int var1) {
      JvmClassMappingKt.getKotlinClass(var0);

      Result.Companion var2;
      Object var5;
      label32:
      try {
         var2 = Result.Companion;
         var5 = Result.constructor_impl(fieldsCount$default(var0, 0, 1, (Object)null));
      } catch (Throwable var4) {
         var2 = Result.Companion;
         var5 = Result.constructor_impl(ResultKt.createFailure(var4));
         break label32;
      }

      Object var6 = var5;
      if (Result.isFailure_impl(var5)) {
         var6 = var1;
      }

      return ((Number)var6).intValue();
   }

   private static final Function1 safeCtor(Function1 var0) {
      return (Function1)(new Function1(var0) {
         final Function1 $block;

         public {
            this.$block = var1;
         }

         public final Throwable invoke(Throwable var1) {
            Result.Companion var2;
            Object var5;
            label29:
            try {
               var2 = Result.Companion;
               var5 = Result.constructor_impl((Throwable)this.$block.invoke(var1));
            } catch (Throwable var4) {
               var2 = Result.Companion;
               var5 = Result.constructor_impl(ResultKt.createFailure(var4));
               break label29;
            }

            Object var6 = var5;
            if (Result.isFailure_impl(var5)) {
               var6 = null;
            }

            return (Throwable)var6;
         }
      });
   }

   public static final Throwable tryCopyException(Throwable var0) {
      boolean var7 = var0 instanceof CopyableThrowable;
      Object var10 = null;
      ReentrantReadWriteLock var8 = null;
      if (var7) {
         Object var60;
         label979:
         try {
            Result.Companion var70 = Result.Companion;
            var60 = Result.constructor_impl(((CopyableThrowable)var0).createCopy());
         } catch (Throwable var54) {
            Result.Companion var59 = Result.Companion;
            var60 = Result.constructor_impl(ResultKt.createFailure(var54));
            break label979;
         }

         if (Result.isFailure_impl(var60)) {
            var60 = var8;
         }

         return (Throwable)var60;
      } else {
         var8 = cacheLock;
         ReentrantReadWriteLock.ReadLock var9 = var8.readLock();
         var9.lock();

         Function1 var11;
         try {
            var11 = (Function1)exceptionCtors.get(var0.getClass());
         } finally {
            var9.unlock();
         }

         if (var11 != null) {
            return (Throwable)var11.invoke(var0);
         } else {
            int var1 = throwableFields;
            Class var62 = var0.getClass();
            byte var3 = 0;
            byte var5 = 0;
            byte var6 = 0;
            byte var4 = 0;
            int var2;
            if (var1 != fieldsCountOrDefault(var62, 0)) {
               var9 = var8.readLock();
               if (var8.getWriteHoldCount() == 0) {
                  var1 = var8.getReadHoldCount();
               } else {
                  var1 = 0;
               }

               for(var2 = 0; var2 < var1; ++var2) {
                  var9.unlock();
               }

               ReentrantReadWriteLock.WriteLock var65 = var8.writeLock();
               var65.lock();
               boolean var39 = false;

               try {
                  var39 = true;
                  ((Map)exceptionCtors).put(var0.getClass(), null.INSTANCE);
                  Unit var58 = Unit.INSTANCE;
                  var39 = false;
               } finally {
                  if (var39) {
                     for(var2 = var3; var2 < var1; ++var2) {
                        var9.lock();
                     }

                     var65.unlock();
                  }
               }

               for(var2 = var4; var2 < var1; ++var2) {
                  var9.lock();
               }

               var65.unlock();
               return null;
            } else {
               Function1 var61 = (Function1)null;
               Iterator var66 = ArraysKt.sortedWith(var0.getClass().getConstructors(), (Comparator)(new Comparator() {
                  public final int compare(Object var1, Object var2) {
                     return ComparisonsKt.compareValues((Comparable)((Constructor)var2).getParameterTypes().length, (Comparable)((Constructor)var1).getParameterTypes().length);
                  }
               })).iterator();
               var61 = null;

               Function1 var63;
               while(var66.hasNext()) {
                  var63 = createConstructor((Constructor)var66.next());
                  var61 = var63;
                  if (var63 != null) {
                     var61 = var63;
                     break;
                  }
               }

               ReentrantReadWriteLock var64 = cacheLock;
               ReentrantReadWriteLock.ReadLock var68 = var64.readLock();
               if (var64.getWriteHoldCount() == 0) {
                  var1 = var64.getReadHoldCount();
               } else {
                  var1 = 0;
               }

               for(var2 = 0; var2 < var1; ++var2) {
                  var68.unlock();
               }

               ReentrantReadWriteLock.WriteLock var12 = var64.writeLock();
               var12.lock();

               label1036: {
                  Throwable var10000;
                  label1037: {
                     Map var13;
                     Class var14;
                     boolean var10001;
                     try {
                        var13 = (Map)exceptionCtors;
                        var14 = var0.getClass();
                     } catch (Throwable var57) {
                        var10000 = var57;
                        var10001 = false;
                        break label1037;
                     }

                     if (var61 != null) {
                        var63 = var61;
                     } else {
                        try {
                           var63 = (Function1)null.INSTANCE;
                        } catch (Throwable var56) {
                           var10000 = var56;
                           var10001 = false;
                           break label1037;
                        }
                     }

                     label1006:
                     try {
                        var13.put(var14, var63);
                        Unit var67 = Unit.INSTANCE;
                        break label1036;
                     } catch (Throwable var55) {
                        var10000 = var55;
                        var10001 = false;
                        break label1006;
                     }
                  }

                  var0 = var10000;

                  for(var2 = var6; var2 < var1; ++var2) {
                     var68.lock();
                  }

                  var12.unlock();
                  throw var0;
               }

               for(var2 = var5; var2 < var1; ++var2) {
                  var68.lock();
               }

               var12.unlock();
               Throwable var69 = (Throwable)var10;
               if (var61 != null) {
                  var69 = (Throwable)var61.invoke(var0);
               }

               return var69;
            }
         }
      }
   }
}
