package com.google.gson;

import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GsonBuilder {
   private boolean complexMapKeySerialization;
   private String datePattern;
   private int dateStyle;
   private boolean escapeHtmlChars;
   private Excluder excluder;
   private final List factories;
   private FieldNamingStrategy fieldNamingPolicy;
   private boolean generateNonExecutableJson;
   private final List hierarchyFactories;
   private final Map instanceCreators;
   private boolean lenient;
   private LongSerializationPolicy longSerializationPolicy;
   private boolean prettyPrinting;
   private boolean serializeNulls;
   private boolean serializeSpecialFloatingPointValues;
   private int timeStyle;

   public GsonBuilder() {
      this.excluder = Excluder.DEFAULT;
      this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
      this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
      this.instanceCreators = new HashMap();
      this.factories = new ArrayList();
      this.hierarchyFactories = new ArrayList();
      this.serializeNulls = false;
      this.dateStyle = 2;
      this.timeStyle = 2;
      this.complexMapKeySerialization = false;
      this.serializeSpecialFloatingPointValues = false;
      this.escapeHtmlChars = true;
      this.prettyPrinting = false;
      this.generateNonExecutableJson = false;
      this.lenient = false;
   }

   GsonBuilder(Gson var1) {
      this.excluder = Excluder.DEFAULT;
      this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
      this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
      HashMap var2 = new HashMap();
      this.instanceCreators = var2;
      ArrayList var4 = new ArrayList();
      this.factories = var4;
      ArrayList var3 = new ArrayList();
      this.hierarchyFactories = var3;
      this.serializeNulls = false;
      this.dateStyle = 2;
      this.timeStyle = 2;
      this.complexMapKeySerialization = false;
      this.serializeSpecialFloatingPointValues = false;
      this.escapeHtmlChars = true;
      this.prettyPrinting = false;
      this.generateNonExecutableJson = false;
      this.lenient = false;
      this.excluder = var1.excluder;
      this.fieldNamingPolicy = var1.fieldNamingStrategy;
      var2.putAll(var1.instanceCreators);
      this.serializeNulls = var1.serializeNulls;
      this.complexMapKeySerialization = var1.complexMapKeySerialization;
      this.generateNonExecutableJson = var1.generateNonExecutableJson;
      this.escapeHtmlChars = var1.htmlSafe;
      this.prettyPrinting = var1.prettyPrinting;
      this.lenient = var1.lenient;
      this.serializeSpecialFloatingPointValues = var1.serializeSpecialFloatingPointValues;
      this.longSerializationPolicy = var1.longSerializationPolicy;
      this.datePattern = var1.datePattern;
      this.dateStyle = var1.dateStyle;
      this.timeStyle = var1.timeStyle;
      var4.addAll(var1.builderFactories);
      var3.addAll(var1.builderHierarchyFactories);
   }

   private void addTypeAdaptersForDate(String var1, int var2, int var3, List var4) {
      DefaultDateTypeAdapter var5;
      DefaultDateTypeAdapter var6;
      DefaultDateTypeAdapter var8;
      if (var1 != null && !"".equals(var1.trim())) {
         DefaultDateTypeAdapter var7 = new DefaultDateTypeAdapter(Date.class, var1);
         var6 = new DefaultDateTypeAdapter(Timestamp.class, var1);
         var5 = new DefaultDateTypeAdapter(java.sql.Date.class, var1);
         var8 = var7;
      } else {
         if (var2 == 2 || var3 == 2) {
            return;
         }

         var8 = new DefaultDateTypeAdapter(Date.class, var2, var3);
         var6 = new DefaultDateTypeAdapter(Timestamp.class, var2, var3);
         var5 = new DefaultDateTypeAdapter(java.sql.Date.class, var2, var3);
      }

      var4.add(TypeAdapters.newFactory((Class)Date.class, var8));
      var4.add(TypeAdapters.newFactory((Class)Timestamp.class, var6));
      var4.add(TypeAdapters.newFactory((Class)java.sql.Date.class, var5));
   }

   public GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy var1) {
      this.excluder = this.excluder.withExclusionStrategy(var1, false, true);
      return this;
   }

   public GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy var1) {
      this.excluder = this.excluder.withExclusionStrategy(var1, true, false);
      return this;
   }

   public Gson create() {
      ArrayList var1 = new ArrayList(this.factories.size() + this.hierarchyFactories.size() + 3);
      var1.addAll(this.factories);
      Collections.reverse(var1);
      ArrayList var2 = new ArrayList(this.hierarchyFactories);
      Collections.reverse(var2);
      var1.addAll(var2);
      this.addTypeAdaptersForDate(this.datePattern, this.dateStyle, this.timeStyle, var1);
      return new Gson(this.excluder, this.fieldNamingPolicy, this.instanceCreators, this.serializeNulls, this.complexMapKeySerialization, this.generateNonExecutableJson, this.escapeHtmlChars, this.prettyPrinting, this.lenient, this.serializeSpecialFloatingPointValues, this.longSerializationPolicy, this.datePattern, this.dateStyle, this.timeStyle, this.factories, this.hierarchyFactories, var1);
   }

   public GsonBuilder disableHtmlEscaping() {
      this.escapeHtmlChars = false;
      return this;
   }

   public GsonBuilder disableInnerClassSerialization() {
      this.excluder = this.excluder.disableInnerClassSerialization();
      return this;
   }

   public GsonBuilder enableComplexMapKeySerialization() {
      this.complexMapKeySerialization = true;
      return this;
   }

   public GsonBuilder excludeFieldsWithModifiers(int... var1) {
      this.excluder = this.excluder.withModifiers(var1);
      return this;
   }

   public GsonBuilder excludeFieldsWithoutExposeAnnotation() {
      this.excluder = this.excluder.excludeFieldsWithoutExposeAnnotation();
      return this;
   }

   public GsonBuilder generateNonExecutableJson() {
      this.generateNonExecutableJson = true;
      return this;
   }

   public GsonBuilder registerTypeAdapter(Type var1, Object var2) {
      boolean var4 = var2 instanceof JsonSerializer;
      boolean var3;
      if (!var4 && !(var2 instanceof JsonDeserializer) && !(var2 instanceof InstanceCreator) && !(var2 instanceof TypeAdapter)) {
         var3 = false;
      } else {
         var3 = true;
      }

      $Gson$Preconditions.checkArgument(var3);
      if (var2 instanceof InstanceCreator) {
         this.instanceCreators.put(var1, (InstanceCreator)var2);
      }

      if (var4 || var2 instanceof JsonDeserializer) {
         TypeToken var5 = TypeToken.get(var1);
         this.factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(var5, var2));
      }

      if (var2 instanceof TypeAdapter) {
         this.factories.add(TypeAdapters.newFactory(TypeToken.get(var1), (TypeAdapter)var2));
      }

      return this;
   }

   public GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory var1) {
      this.factories.add(var1);
      return this;
   }

   public GsonBuilder registerTypeHierarchyAdapter(Class var1, Object var2) {
      boolean var4 = var2 instanceof JsonSerializer;
      boolean var3;
      if (!var4 && !(var2 instanceof JsonDeserializer) && !(var2 instanceof TypeAdapter)) {
         var3 = false;
      } else {
         var3 = true;
      }

      $Gson$Preconditions.checkArgument(var3);
      if (var2 instanceof JsonDeserializer || var4) {
         this.hierarchyFactories.add(TreeTypeAdapter.newTypeHierarchyFactory(var1, var2));
      }

      if (var2 instanceof TypeAdapter) {
         this.factories.add(TypeAdapters.newTypeHierarchyFactory(var1, (TypeAdapter)var2));
      }

      return this;
   }

   public GsonBuilder serializeNulls() {
      this.serializeNulls = true;
      return this;
   }

   public GsonBuilder serializeSpecialFloatingPointValues() {
      this.serializeSpecialFloatingPointValues = true;
      return this;
   }

   public GsonBuilder setDateFormat(int var1) {
      this.dateStyle = var1;
      this.datePattern = null;
      return this;
   }

   public GsonBuilder setDateFormat(int var1, int var2) {
      this.dateStyle = var1;
      this.timeStyle = var2;
      this.datePattern = null;
      return this;
   }

   public GsonBuilder setDateFormat(String var1) {
      this.datePattern = var1;
      return this;
   }

   public GsonBuilder setExclusionStrategies(ExclusionStrategy... var1) {
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         ExclusionStrategy var4 = var1[var2];
         this.excluder = this.excluder.withExclusionStrategy(var4, true, true);
      }

      return this;
   }

   public GsonBuilder setFieldNamingPolicy(FieldNamingPolicy var1) {
      this.fieldNamingPolicy = var1;
      return this;
   }

   public GsonBuilder setFieldNamingStrategy(FieldNamingStrategy var1) {
      this.fieldNamingPolicy = var1;
      return this;
   }

   public GsonBuilder setLenient() {
      this.lenient = true;
      return this;
   }

   public GsonBuilder setLongSerializationPolicy(LongSerializationPolicy var1) {
      this.longSerializationPolicy = var1;
      return this;
   }

   public GsonBuilder setPrettyPrinting() {
      this.prettyPrinting = true;
      return this;
   }

   public GsonBuilder setVersion(double var1) {
      this.excluder = this.excluder.withVersion(var1);
      return this;
   }
}
