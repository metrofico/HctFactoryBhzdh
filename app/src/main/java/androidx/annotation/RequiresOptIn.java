package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.ANNOTATION_TYPE})
@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0002\u0018\u00002\u00020\u0001:\u0001\u0005B\n\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003R\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u0006"},
   d2 = {"Landroidx/annotation/RequiresOptIn;", "", "level", "Landroidx/annotation/RequiresOptIn$Level;", "()Landroidx/annotation/RequiresOptIn$Level;", "Level", "annotation-experimental_release"},
   k = 1,
   mv = {1, 4, 2}
)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@kotlin.annotation.Target(
   allowedTargets = {AnnotationTarget.ANNOTATION_CLASS}
)
public @interface RequiresOptIn {
   Level level() default RequiresOptIn.Level.ERROR;

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"},
      d2 = {"Landroidx/annotation/RequiresOptIn$Level;", "", "(Ljava/lang/String;I)V", "WARNING", "ERROR", "annotation-experimental_release"},
      k = 1,
      mv = {1, 4, 2}
   )
   public static enum Level {
      private static final Level[] $VALUES;
      ERROR,
      WARNING;

      static {
         Level var0 = new Level("WARNING", 0);
         WARNING = var0;
         Level var1 = new Level("ERROR", 1);
         ERROR = var1;
         $VALUES = new Level[]{var0, var1};
      }
   }
}
