package kotlin.io.path;

import java.nio.file.Path;
import java.nio.file.Paths;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"},
   d2 = {"Lkotlin/io/path/PathRelativizer;", "", "()V", "emptyPath", "Ljava/nio/file/Path;", "kotlin.jvm.PlatformType", "parentPath", "tryRelativeTo", "path", "base", "kotlin-stdlib-jdk7"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class PathRelativizer {
   public static final PathRelativizer INSTANCE = new PathRelativizer();
   private static final Path emptyPath = Paths.get("");
   private static final Path parentPath = Paths.get("..");

   private PathRelativizer() {
   }

   public final Path tryRelativeTo(Path var1, Path var2) {
      Intrinsics.checkNotNullParameter(var1, "path");
      Intrinsics.checkNotNullParameter(var2, "base");
      Path var5 = var2.normalize();
      var2 = var1.normalize();
      var1 = var5.relativize(var2);
      int var4 = Math.min(var5.getNameCount(), var2.getNameCount());

      for(int var3 = 0; var3 < var4; ++var3) {
         Path var6 = var5.getName(var3);
         Path var7 = parentPath;
         if (!Intrinsics.areEqual((Object)var6, (Object)var7)) {
            break;
         }

         if (!Intrinsics.areEqual((Object)var2.getName(var3), (Object)var7)) {
            throw new IllegalArgumentException("Unable to compute relative path");
         }
      }

      if (!Intrinsics.areEqual((Object)var2, (Object)var5) && Intrinsics.areEqual((Object)var5, (Object)emptyPath)) {
         var1 = var2;
      } else {
         String var8 = var1.toString();
         String var9 = var1.getFileSystem().getSeparator();
         Intrinsics.checkNotNullExpressionValue(var9, "rn.fileSystem.separator");
         if (StringsKt.endsWith$default(var8, var9, false, 2, (Object)null)) {
            var1 = var1.getFileSystem().getPath(StringsKt.dropLast(var8, var1.getFileSystem().getSeparator().length()));
         }
      }

      Intrinsics.checkNotNullExpressionValue(var1, "r");
      return var1;
   }
}
