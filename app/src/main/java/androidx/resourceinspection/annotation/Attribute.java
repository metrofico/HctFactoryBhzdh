package androidx.resourceinspection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD})
public @interface Attribute {
   IntMap[] intMapping() default {};

   String value();

   @Retention(RetentionPolicy.SOURCE)
   @Target({})
   public @interface IntMap {
      int mask() default 0;

      String name();

      int value();
   }
}
