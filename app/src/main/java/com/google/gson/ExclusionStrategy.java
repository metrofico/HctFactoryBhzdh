package com.google.gson;

public interface ExclusionStrategy {
   boolean shouldSkipClass(Class var1);

   boolean shouldSkipField(FieldAttributes var1);
}
