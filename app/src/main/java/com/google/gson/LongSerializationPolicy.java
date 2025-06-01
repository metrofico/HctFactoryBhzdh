package com.google.gson;

public enum LongSerializationPolicy {
   private static final LongSerializationPolicy[] $VALUES;
   DEFAULT,
   STRING;

   static {
      LongSerializationPolicy var0 = new LongSerializationPolicy("DEFAULT", 0) {
         public JsonElement serialize(Long var1) {
            return new JsonPrimitive(var1);
         }
      };
      DEFAULT = var0;
      LongSerializationPolicy var1 = new LongSerializationPolicy("STRING", 1) {
         public JsonElement serialize(Long var1) {
            return new JsonPrimitive(String.valueOf(var1));
         }
      };
      STRING = var1;
      $VALUES = new LongSerializationPolicy[]{var0, var1};
   }

   private LongSerializationPolicy() {
   }

   // $FF: synthetic method
   LongSerializationPolicy(Object var3) {
      this();
   }

   public abstract JsonElement serialize(Long var1);
}
