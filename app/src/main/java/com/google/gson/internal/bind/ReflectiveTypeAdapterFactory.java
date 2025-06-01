package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
   private final ReflectionAccessor accessor = ReflectionAccessor.getInstance();
   private final ConstructorConstructor constructorConstructor;
   private final Excluder excluder;
   private final FieldNamingStrategy fieldNamingPolicy;
   private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;

   public ReflectiveTypeAdapterFactory(ConstructorConstructor var1, FieldNamingStrategy var2, Excluder var3, JsonAdapterAnnotationTypeAdapterFactory var4) {
      this.constructorConstructor = var1;
      this.fieldNamingPolicy = var2;
      this.excluder = var3;
      this.jsonAdapterFactory = var4;
   }

   private BoundField createBoundField(Gson var1, Field var2, String var3, TypeToken var4, boolean var5, boolean var6) {
      boolean var8 = Primitives.isPrimitive(var4.getRawType());
      JsonAdapter var9 = (JsonAdapter)var2.getAnnotation(JsonAdapter.class);
      TypeAdapter var11;
      if (var9 != null) {
         var11 = this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, var1, var4, var9);
      } else {
         var11 = null;
      }

      boolean var7;
      if (var11 != null) {
         var7 = true;
      } else {
         var7 = false;
      }

      TypeAdapter var10 = var11;
      if (var11 == null) {
         var10 = var1.getAdapter(var4);
      }

      return new BoundField(this, var3, var5, var6, var2, var7, var10, var1, var4, var8) {
         final ReflectiveTypeAdapterFactory this$0;
         final Gson val$context;
         final Field val$field;
         final TypeToken val$fieldType;
         final boolean val$isPrimitive;
         final boolean val$jsonAdapterPresent;
         final TypeAdapter val$typeAdapter;

         {
            this.this$0 = var1;
            this.val$field = var5;
            this.val$jsonAdapterPresent = var6;
            this.val$typeAdapter = var7;
            this.val$context = var8;
            this.val$fieldType = var9;
            this.val$isPrimitive = var10;
         }

         void read(JsonReader var1, Object var2) throws IOException, IllegalAccessException {
            Object var3 = this.val$typeAdapter.read(var1);
            if (var3 != null || !this.val$isPrimitive) {
               this.val$field.set(var2, var3);
            }

         }

         void write(JsonWriter var1, Object var2) throws IOException, IllegalAccessException {
            Object var3 = this.val$field.get(var2);
            if (this.val$jsonAdapterPresent) {
               var2 = this.val$typeAdapter;
            } else {
               var2 = new TypeAdapterRuntimeTypeWrapper(this.val$context, this.val$typeAdapter, this.val$fieldType.getType());
            }

            ((TypeAdapter)var2).write(var1, var3);
         }

         public boolean writeField(Object var1) throws IOException, IllegalAccessException {
            boolean var3 = this.serialized;
            boolean var2 = false;
            if (!var3) {
               return false;
            } else {
               if (this.val$field.get(var1) != var1) {
                  var2 = true;
               }

               return var2;
            }
         }
      };
   }

   static boolean excludeField(Field var0, boolean var1, Excluder var2) {
      if (!var2.excludeClass(var0.getType(), var1) && !var2.excludeField(var0, var1)) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private Map getBoundFields(Gson var1, TypeToken var2, Class var3) {
      LinkedHashMap var15 = new LinkedHashMap();
      if (var3.isInterface()) {
         return var15;
      } else {
         Type var14 = var2.getType();

         for(TypeToken var10 = var2; var3 != Object.class; var3 = var10.getRawType()) {
            Field[] var16 = var3.getDeclaredFields();
            int var7 = var16.length;

            for(int var4 = 0; var4 < var7; ++var4) {
               Field var11 = var16[var4];
               boolean var8 = this.excludeField(var11, true);
               boolean var9 = this.excludeField(var11, false);
               if (var8 || var9) {
                  this.accessor.makeAccessible(var11);
                  Type var17 = $Gson$Types.resolve(var10.getType(), var3, var11.getGenericType());
                  List var12 = this.getFieldNames(var11);
                  int var5 = var12.size();
                  BoundField var18 = null;

                  for(int var6 = 0; var6 < var5; ++var6) {
                     String var13 = (String)var12.get(var6);
                     if (var6 != 0) {
                        var8 = false;
                     }

                     BoundField var19 = (BoundField)var15.put(var13, this.createBoundField(var1, var11, var13, TypeToken.get(var17), var8, var9));
                     if (var18 == null) {
                        var18 = var19;
                     }
                  }

                  if (var18 != null) {
                     throw new IllegalArgumentException(var14 + " declares multiple JSON fields named " + var18.name);
                  }
               }
            }

            var10 = TypeToken.get($Gson$Types.resolve(var10.getType(), var3, var3.getGenericSuperclass()));
         }

         return var15;
      }
   }

   private List getFieldNames(Field var1) {
      SerializedName var4 = (SerializedName)var1.getAnnotation(SerializedName.class);
      if (var4 == null) {
         return Collections.singletonList(this.fieldNamingPolicy.translateName(var1));
      } else {
         String var6 = var4.value();
         String[] var5 = var4.alternate();
         if (var5.length == 0) {
            return Collections.singletonList(var6);
         } else {
            ArrayList var7 = new ArrayList(var5.length + 1);
            var7.add(var6);
            int var3 = var5.length;

            for(int var2 = 0; var2 < var3; ++var2) {
               var7.add(var5[var2]);
            }

            return var7;
         }
      }
   }

   public TypeAdapter create(Gson var1, TypeToken var2) {
      Class var3 = var2.getRawType();
      return !Object.class.isAssignableFrom(var3) ? null : new Adapter(this.constructorConstructor.get(var2), this.getBoundFields(var1, var2, var3));
   }

   public boolean excludeField(Field var1, boolean var2) {
      return excludeField(var1, var2, this.excluder);
   }

   public static final class Adapter extends TypeAdapter {
      private final Map boundFields;
      private final ObjectConstructor constructor;

      Adapter(ObjectConstructor var1, Map var2) {
         this.constructor = var1;
         this.boundFields = var2;
      }

      public Object read(JsonReader var1) throws IOException {
         if (var1.peek() == JsonToken.NULL) {
            var1.nextNull();
            return null;
         } else {
            Object var2 = this.constructor.construct();

            label68: {
               IllegalStateException var17;
               label67: {
                  IllegalAccessException var10000;
                  label66: {
                     boolean var10001;
                     try {
                        var1.beginObject();
                     } catch (IllegalStateException var12) {
                        var17 = var12;
                        var10001 = false;
                        break label67;
                     } catch (IllegalAccessException var13) {
                        var10000 = var13;
                        var10001 = false;
                        break label66;
                     }

                     while(true) {
                        BoundField var16;
                        try {
                           if (!var1.hasNext()) {
                              break label68;
                           }

                           String var3 = var1.nextName();
                           var16 = (BoundField)this.boundFields.get(var3);
                        } catch (IllegalStateException var8) {
                           var17 = var8;
                           var10001 = false;
                           break label67;
                        } catch (IllegalAccessException var9) {
                           var10000 = var9;
                           var10001 = false;
                           break;
                        }

                        if (var16 != null) {
                           label79: {
                              try {
                                 if (!var16.deserialized) {
                                    break label79;
                                 }
                              } catch (IllegalStateException var10) {
                                 var17 = var10;
                                 var10001 = false;
                                 break label67;
                              } catch (IllegalAccessException var11) {
                                 var10000 = var11;
                                 var10001 = false;
                                 break;
                              }

                              try {
                                 var16.read(var1, var2);
                                 continue;
                              } catch (IllegalStateException var4) {
                                 var17 = var4;
                                 var10001 = false;
                                 break label67;
                              } catch (IllegalAccessException var5) {
                                 var10000 = var5;
                                 var10001 = false;
                                 break;
                              }
                           }
                        }

                        try {
                           var1.skipValue();
                        } catch (IllegalStateException var6) {
                           var17 = var6;
                           var10001 = false;
                           break label67;
                        } catch (IllegalAccessException var7) {
                           var10000 = var7;
                           var10001 = false;
                           break;
                        }
                     }
                  }

                  IllegalAccessException var14 = var10000;
                  throw new AssertionError(var14);
               }

               IllegalStateException var15 = var17;
               throw new JsonSyntaxException(var15);
            }

            var1.endObject();
            return var2;
         }
      }

      public void write(JsonWriter var1, Object var2) throws IOException {
         if (var2 == null) {
            var1.nullValue();
         } else {
            var1.beginObject();

            label33: {
               IllegalAccessException var10000;
               label32: {
                  boolean var10001;
                  Iterator var3;
                  try {
                     var3 = this.boundFields.values().iterator();
                  } catch (IllegalAccessException var6) {
                     var10000 = var6;
                     var10001 = false;
                     break label32;
                  }

                  while(true) {
                     try {
                        BoundField var4;
                        do {
                           if (!var3.hasNext()) {
                              break label33;
                           }

                           var4 = (BoundField)var3.next();
                        } while(!var4.writeField(var2));

                        var1.name(var4.name);
                        var4.write(var1, var2);
                     } catch (IllegalAccessException var5) {
                        var10000 = var5;
                        var10001 = false;
                        break;
                     }
                  }
               }

               IllegalAccessException var7 = var10000;
               throw new AssertionError(var7);
            }

            var1.endObject();
         }
      }
   }

   abstract static class BoundField {
      final boolean deserialized;
      final String name;
      final boolean serialized;

      protected BoundField(String var1, boolean var2, boolean var3) {
         this.name = var1;
         this.serialized = var2;
         this.deserialized = var3;
      }

      abstract void read(JsonReader var1, Object var2) throws IOException, IllegalAccessException;

      abstract void write(JsonWriter var1, Object var2) throws IOException, IllegalAccessException;

      abstract boolean writeField(Object var1) throws IOException, IllegalAccessException;
   }
}
