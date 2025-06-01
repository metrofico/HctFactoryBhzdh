package com.google.gson.stream;

public enum JsonToken {
   private static final JsonToken[] $VALUES;
   BEGIN_ARRAY,
   BEGIN_OBJECT,
   BOOLEAN,
   END_ARRAY,
   END_DOCUMENT,
   END_OBJECT,
   NAME,
   NULL,
   NUMBER,
   STRING;

   static {
      JsonToken var1 = new JsonToken("BEGIN_ARRAY", 0);
      BEGIN_ARRAY = var1;
      JsonToken var7 = new JsonToken("END_ARRAY", 1);
      END_ARRAY = var7;
      JsonToken var8 = new JsonToken("BEGIN_OBJECT", 2);
      BEGIN_OBJECT = var8;
      JsonToken var2 = new JsonToken("END_OBJECT", 3);
      END_OBJECT = var2;
      JsonToken var6 = new JsonToken("NAME", 4);
      NAME = var6;
      JsonToken var0 = new JsonToken("STRING", 5);
      STRING = var0;
      JsonToken var9 = new JsonToken("NUMBER", 6);
      NUMBER = var9;
      JsonToken var5 = new JsonToken("BOOLEAN", 7);
      BOOLEAN = var5;
      JsonToken var3 = new JsonToken("NULL", 8);
      NULL = var3;
      JsonToken var4 = new JsonToken("END_DOCUMENT", 9);
      END_DOCUMENT = var4;
      $VALUES = new JsonToken[]{var1, var7, var8, var2, var6, var0, var9, var5, var3, var4};
   }
}
