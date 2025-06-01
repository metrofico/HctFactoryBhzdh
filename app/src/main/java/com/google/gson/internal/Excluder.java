package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Excluder implements TypeAdapterFactory, Cloneable {
   public static final Excluder DEFAULT = new Excluder();
   private static final double IGNORE_VERSIONS = -1.0;
   private List deserializationStrategies = Collections.emptyList();
   private int modifiers = 136;
   private boolean requireExpose;
   private List serializationStrategies = Collections.emptyList();
   private boolean serializeInnerClasses = true;
   private double version = -1.0;

   private boolean excludeClassChecks(Class var1) {
      if (this.version != -1.0 && !this.isValidVersion((Since)var1.getAnnotation(Since.class), (Until)var1.getAnnotation(Until.class))) {
         return true;
      } else if (!this.serializeInnerClasses && this.isInnerClass(var1)) {
         return true;
      } else {
         return this.isAnonymousOrLocal(var1);
      }
   }

   private boolean excludeClassInStrategy(Class var1, boolean var2) {
      List var3;
      if (var2) {
         var3 = this.serializationStrategies;
      } else {
         var3 = this.deserializationStrategies;
      }

      Iterator var4 = var3.iterator();

      do {
         if (!var4.hasNext()) {
            return false;
         }
      } while(!((ExclusionStrategy)var4.next()).shouldSkipClass(var1));

      return true;
   }

   private boolean isAnonymousOrLocal(Class var1) {
      boolean var2;
      if (Enum.class.isAssignableFrom(var1) || !var1.isAnonymousClass() && !var1.isLocalClass()) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   private boolean isInnerClass(Class var1) {
      boolean var2;
      if (var1.isMemberClass() && !this.isStatic(var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private boolean isStatic(Class var1) {
      boolean var2;
      if ((var1.getModifiers() & 8) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private boolean isValidSince(Since var1) {
      return var1 == null || !(var1.value() > this.version);
   }

   private boolean isValidUntil(Until var1) {
      return var1 == null || !(var1.value() <= this.version);
   }

   private boolean isValidVersion(Since var1, Until var2) {
      boolean var3;
      if (this.isValidSince(var1) && this.isValidUntil(var2)) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   protected Excluder clone() {
      try {
         Excluder var1 = (Excluder)super.clone();
         return var1;
      } catch (CloneNotSupportedException var2) {
         throw new AssertionError(var2);
      }
   }

   public TypeAdapter create(Gson var1, TypeToken var2) {
      Class var5 = var2.getRawType();
      boolean var4 = this.excludeClassChecks(var5);
      boolean var3;
      if (!var4 && !this.excludeClassInStrategy(var5, true)) {
         var3 = false;
      } else {
         var3 = true;
      }

      if (!var4 && !this.excludeClassInStrategy(var5, false)) {
         var4 = false;
      } else {
         var4 = true;
      }

      return !var3 && !var4 ? null : new TypeAdapter(this, var4, var3, var1, var2) {
         private TypeAdapter delegate;
         final Excluder this$0;
         final Gson val$gson;
         final boolean val$skipDeserialize;
         final boolean val$skipSerialize;
         final TypeToken val$type;

         {
            this.this$0 = var1;
            this.val$skipDeserialize = var2;
            this.val$skipSerialize = var3;
            this.val$gson = var4;
            this.val$type = var5;
         }

         private TypeAdapter delegate() {
            TypeAdapter var1 = this.delegate;
            if (var1 == null) {
               var1 = this.val$gson.getDelegateAdapter(this.this$0, this.val$type);
               this.delegate = var1;
            }

            return var1;
         }

         public Object read(JsonReader var1) throws IOException {
            if (this.val$skipDeserialize) {
               var1.skipValue();
               return null;
            } else {
               return this.delegate().read(var1);
            }
         }

         public void write(JsonWriter var1, Object var2) throws IOException {
            if (this.val$skipSerialize) {
               var1.nullValue();
            } else {
               this.delegate().write(var1, var2);
            }
         }
      };
   }

   public Excluder disableInnerClassSerialization() {
      Excluder var1 = this.clone();
      var1.serializeInnerClasses = false;
      return var1;
   }

   public boolean excludeClass(Class var1, boolean var2) {
      if (!this.excludeClassChecks(var1) && !this.excludeClassInStrategy(var1, var2)) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   public boolean excludeField(Field var1, boolean var2) {
      if ((this.modifiers & var1.getModifiers()) != 0) {
         return true;
      } else if (this.version != -1.0 && !this.isValidVersion((Since)var1.getAnnotation(Since.class), (Until)var1.getAnnotation(Until.class))) {
         return true;
      } else if (var1.isSynthetic()) {
         return true;
      } else {
         if (this.requireExpose) {
            label68: {
               Expose var3 = (Expose)var1.getAnnotation(Expose.class);
               if (var3 != null) {
                  if (var2) {
                     if (var3.serialize()) {
                        break label68;
                     }
                  } else if (var3.deserialize()) {
                     break label68;
                  }
               }

               return true;
            }
         }

         if (!this.serializeInnerClasses && this.isInnerClass(var1.getType())) {
            return true;
         } else if (this.isAnonymousOrLocal(var1.getType())) {
            return true;
         } else {
            List var5;
            if (var2) {
               var5 = this.serializationStrategies;
            } else {
               var5 = this.deserializationStrategies;
            }

            if (!var5.isEmpty()) {
               FieldAttributes var4 = new FieldAttributes(var1);
               Iterator var6 = var5.iterator();

               while(var6.hasNext()) {
                  if (((ExclusionStrategy)var6.next()).shouldSkipField(var4)) {
                     return true;
                  }
               }
            }

            return false;
         }
      }
   }

   public Excluder excludeFieldsWithoutExposeAnnotation() {
      Excluder var1 = this.clone();
      var1.requireExpose = true;
      return var1;
   }

   public Excluder withExclusionStrategy(ExclusionStrategy var1, boolean var2, boolean var3) {
      Excluder var4 = this.clone();
      ArrayList var5;
      if (var2) {
         var5 = new ArrayList(this.serializationStrategies);
         var4.serializationStrategies = var5;
         var5.add(var1);
      }

      if (var3) {
         var5 = new ArrayList(this.deserializationStrategies);
         var4.deserializationStrategies = var5;
         var5.add(var1);
      }

      return var4;
   }

   public Excluder withModifiers(int... var1) {
      Excluder var4 = this.clone();
      int var2 = 0;
      var4.modifiers = 0;

      for(int var3 = var1.length; var2 < var3; ++var2) {
         var4.modifiers |= var1[var2];
      }

      return var4;
   }

   public Excluder withVersion(double var1) {
      Excluder var3 = this.clone();
      var3.version = var1;
      return var3;
   }
}
