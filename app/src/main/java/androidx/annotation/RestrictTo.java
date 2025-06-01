package androidx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
public @interface RestrictTo {
   Scope[] value();

   public static enum Scope {
      private static final Scope[] $VALUES;
      @Deprecated
      GROUP_ID,
      LIBRARY,
      LIBRARY_GROUP,
      LIBRARY_GROUP_PREFIX,
      SUBCLASSES,
      TESTS;

      static {
         Scope var4 = new Scope("LIBRARY", 0);
         LIBRARY = var4;
         Scope var0 = new Scope("LIBRARY_GROUP", 1);
         LIBRARY_GROUP = var0;
         Scope var1 = new Scope("LIBRARY_GROUP_PREFIX", 2);
         LIBRARY_GROUP_PREFIX = var1;
         Scope var2 = new Scope("GROUP_ID", 3);
         GROUP_ID = var2;
         Scope var5 = new Scope("TESTS", 4);
         TESTS = var5;
         Scope var3 = new Scope("SUBCLASSES", 5);
         SUBCLASSES = var3;
         $VALUES = new Scope[]{var4, var0, var1, var2, var5, var3};
      }
   }
}
