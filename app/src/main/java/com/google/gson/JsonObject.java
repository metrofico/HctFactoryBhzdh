package com.google.gson;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class JsonObject extends JsonElement {
   private final LinkedTreeMap members = new LinkedTreeMap();

   public void add(String var1, JsonElement var2) {
      LinkedTreeMap var4 = this.members;
      Object var3 = var2;
      if (var2 == null) {
         var3 = JsonNull.INSTANCE;
      }

      var4.put(var1, var3);
   }

   public void addProperty(String var1, Boolean var2) {
      Object var3;
      if (var2 == null) {
         var3 = JsonNull.INSTANCE;
      } else {
         var3 = new JsonPrimitive(var2);
      }

      this.add(var1, (JsonElement)var3);
   }

   public void addProperty(String var1, Character var2) {
      Object var3;
      if (var2 == null) {
         var3 = JsonNull.INSTANCE;
      } else {
         var3 = new JsonPrimitive(var2);
      }

      this.add(var1, (JsonElement)var3);
   }

   public void addProperty(String var1, Number var2) {
      Object var3;
      if (var2 == null) {
         var3 = JsonNull.INSTANCE;
      } else {
         var3 = new JsonPrimitive(var2);
      }

      this.add(var1, (JsonElement)var3);
   }

   public void addProperty(String var1, String var2) {
      Object var3;
      if (var2 == null) {
         var3 = JsonNull.INSTANCE;
      } else {
         var3 = new JsonPrimitive(var2);
      }

      this.add(var1, (JsonElement)var3);
   }

   public JsonObject deepCopy() {
      JsonObject var2 = new JsonObject();
      Iterator var1 = this.members.entrySet().iterator();

      while(var1.hasNext()) {
         Map.Entry var3 = (Map.Entry)var1.next();
         var2.add((String)var3.getKey(), ((JsonElement)var3.getValue()).deepCopy());
      }

      return var2;
   }

   public Set entrySet() {
      return this.members.entrySet();
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 == this || var1 instanceof JsonObject && ((JsonObject)var1).members.equals(this.members)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public JsonElement get(String var1) {
      return (JsonElement)this.members.get(var1);
   }

   public JsonArray getAsJsonArray(String var1) {
      return (JsonArray)this.members.get(var1);
   }

   public JsonObject getAsJsonObject(String var1) {
      return (JsonObject)this.members.get(var1);
   }

   public JsonPrimitive getAsJsonPrimitive(String var1) {
      return (JsonPrimitive)this.members.get(var1);
   }

   public boolean has(String var1) {
      return this.members.containsKey(var1);
   }

   public int hashCode() {
      return this.members.hashCode();
   }

   public Set keySet() {
      return this.members.keySet();
   }

   public JsonElement remove(String var1) {
      return (JsonElement)this.members.remove(var1);
   }

   public int size() {
      return this.members.size();
   }
}
