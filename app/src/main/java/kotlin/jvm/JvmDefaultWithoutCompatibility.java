package kotlin.jvm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Metadata(
   d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002"},
   d2 = {"Lkotlin/jvm/JvmDefaultWithoutCompatibility;", "", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@kotlin.annotation.Target(
   allowedTargets = {AnnotationTarget.CLASS}
)
public @interface JvmDefaultWithoutCompatibility {
}
