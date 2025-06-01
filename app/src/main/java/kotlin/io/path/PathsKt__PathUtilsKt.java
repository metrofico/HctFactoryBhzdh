package kotlin.io.path;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000²\u0001\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0011\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a*\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00012\u0012\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001a\"\u00020\u0001H\u0087\b¢\u0006\u0002\u0010\u001b\u001a?\u0010\u001c\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0007¢\u0006\u0002\u0010!\u001a6\u0010\u001c\u001a\u00020\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0087\b¢\u0006\u0002\u0010\"\u001aK\u0010#\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00012\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0007¢\u0006\u0002\u0010%\u001aB\u0010#\u001a\u00020\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00012\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0087\b¢\u0006\u0002\u0010&\u001a\u001c\u0010'\u001a\u00020(2\u0006\u0010\u0017\u001a\u00020\u00022\n\u0010)\u001a\u0006\u0012\u0002\b\u00030*H\u0001\u001a\r\u0010+\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010,\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a.\u0010-\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u0002000\u001a\"\u000200H\u0087\b¢\u0006\u0002\u00101\u001a\u001f\u0010-\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\b\b\u0002\u00102\u001a\u000203H\u0087\b\u001a.\u00104\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0087\b¢\u0006\u0002\u00105\u001a.\u00106\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0087\b¢\u0006\u0002\u00105\u001a.\u00107\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0087\b¢\u0006\u0002\u00105\u001a\u0015\u00108\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u0002H\u0087\b\u001a6\u00109\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0087\b¢\u0006\u0002\u0010:\u001a\r\u0010;\u001a\u00020<*\u00020\u0002H\u0087\b\u001a\r\u0010=\u001a\u000203*\u00020\u0002H\u0087\b\u001a\u0015\u0010>\u001a\u00020\u0002*\u00020\u00022\u0006\u0010?\u001a\u00020\u0002H\u0087\n\u001a\u0015\u0010>\u001a\u00020\u0002*\u00020\u00022\u0006\u0010?\u001a\u00020\u0001H\u0087\n\u001a&\u0010@\u001a\u000203*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010B\u001a2\u0010C\u001a\u0002HD\"\n\b\u0000\u0010D\u0018\u0001*\u00020E*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010F\u001a4\u0010G\u001a\u0004\u0018\u0001HD\"\n\b\u0000\u0010D\u0018\u0001*\u00020E*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010F\u001a\r\u0010H\u001a\u00020I*\u00020\u0002H\u0087\b\u001a\r\u0010J\u001a\u00020K*\u00020\u0002H\u0087\b\u001a.\u0010L\u001a\u00020<*\u00020\u00022\b\b\u0002\u0010M\u001a\u00020\u00012\u0012\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020<0OH\u0087\bø\u0001\u0000\u001a0\u0010P\u001a\u0004\u0018\u00010Q*\u00020\u00022\u0006\u0010R\u001a\u00020\u00012\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010S\u001a&\u0010T\u001a\u00020U*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010V\u001a(\u0010W\u001a\u0004\u0018\u00010X*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010Y\u001a,\u0010Z\u001a\b\u0012\u0004\u0012\u00020\\0[*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010]\u001a&\u0010^\u001a\u000203*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010B\u001a\r\u0010_\u001a\u000203*\u00020\u0002H\u0087\b\u001a\r\u0010`\u001a\u000203*\u00020\u0002H\u0087\b\u001a\r\u0010a\u001a\u000203*\u00020\u0002H\u0087\b\u001a&\u0010b\u001a\u000203*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010B\u001a\u0015\u0010c\u001a\u000203*\u00020\u00022\u0006\u0010?\u001a\u00020\u0002H\u0087\b\u001a\r\u0010d\u001a\u000203*\u00020\u0002H\u0087\b\u001a\r\u0010e\u001a\u000203*\u00020\u0002H\u0087\b\u001a\u001c\u0010f\u001a\b\u0012\u0004\u0012\u00020\u00020g*\u00020\u00022\b\b\u0002\u0010M\u001a\u00020\u0001H\u0007\u001a.\u0010h\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u0002000\u001a\"\u000200H\u0087\b¢\u0006\u0002\u00101\u001a\u001f\u0010h\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\b\b\u0002\u00102\u001a\u000203H\u0087\b\u001a&\u0010i\u001a\u000203*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010B\u001a2\u0010j\u001a\u0002Hk\"\n\b\u0000\u0010k\u0018\u0001*\u00020l*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010m\u001a<\u0010j\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010Q0n*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00012\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010o\u001a\r\u0010p\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0014\u0010q\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002H\u0007\u001a\u0016\u0010r\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002H\u0007\u001a\u0014\u0010s\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002H\u0007\u001a8\u0010t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010R\u001a\u00020\u00012\b\u0010u\u001a\u0004\u0018\u00010Q2\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b¢\u0006\u0002\u0010v\u001a\u0015\u0010w\u001a\u00020\u0002*\u00020\u00022\u0006\u0010u\u001a\u00020UH\u0087\b\u001a\u0015\u0010x\u001a\u00020\u0002*\u00020\u00022\u0006\u0010u\u001a\u00020XH\u0087\b\u001a\u001b\u0010y\u001a\u00020\u0002*\u00020\u00022\f\u0010u\u001a\b\u0012\u0004\u0012\u00020\\0[H\u0087\b\u001a\r\u0010z\u001a\u00020\u0002*\u00020{H\u0087\b\u001a@\u0010|\u001a\u0002H}\"\u0004\b\u0000\u0010}*\u00020\u00022\b\b\u0002\u0010M\u001a\u00020\u00012\u0018\u0010~\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u007f\u0012\u0004\u0012\u0002H}0OH\u0087\bø\u0001\u0000¢\u0006\u0003\u0010\u0080\u0001\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001f\u0010\u0007\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\"\u001e\u0010\n\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006\"\u001e\u0010\r\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u001e\u0010\u0010\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0006\"\u001f\u0010\u0013\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u0004\u001a\u0004\b\u0015\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0081\u0001"},
   d2 = {"extension", "", "Ljava/nio/file/Path;", "getExtension$annotations", "(Ljava/nio/file/Path;)V", "getExtension", "(Ljava/nio/file/Path;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath$annotations", "getInvariantSeparatorsPath", "invariantSeparatorsPathString", "getInvariantSeparatorsPathString$annotations", "getInvariantSeparatorsPathString", "name", "getName$annotations", "getName", "nameWithoutExtension", "getNameWithoutExtension$annotations", "getNameWithoutExtension", "pathString", "getPathString$annotations", "getPathString", "Path", "path", "base", "subpaths", "", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/nio/file/Path;", "createTempDirectory", "directory", "prefix", "attributes", "Ljava/nio/file/attribute/FileAttribute;", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "(Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "createTempFile", "suffix", "(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "(Ljava/lang/String;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "fileAttributeViewNotAvailable", "", "attributeViewClass", "Ljava/lang/Class;", "absolute", "absolutePathString", "copyTo", "target", "options", "Ljava/nio/file/CopyOption;", "(Ljava/nio/file/Path;Ljava/nio/file/Path;[Ljava/nio/file/CopyOption;)Ljava/nio/file/Path;", "overwrite", "", "createDirectories", "(Ljava/nio/file/Path;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "createDirectory", "createFile", "createLinkPointingTo", "createSymbolicLinkPointingTo", "(Ljava/nio/file/Path;Ljava/nio/file/Path;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "deleteExisting", "", "deleteIfExists", "div", "other", "exists", "Ljava/nio/file/LinkOption;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Z", "fileAttributesView", "V", "Ljava/nio/file/attribute/FileAttributeView;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/FileAttributeView;", "fileAttributesViewOrNull", "fileSize", "", "fileStore", "Ljava/nio/file/FileStore;", "forEachDirectoryEntry", "glob", "action", "Lkotlin/Function1;", "getAttribute", "", "attribute", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/LinkOption;)Ljava/lang/Object;", "getLastModifiedTime", "Ljava/nio/file/attribute/FileTime;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/FileTime;", "getOwner", "Ljava/nio/file/attribute/UserPrincipal;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/UserPrincipal;", "getPosixFilePermissions", "", "Ljava/nio/file/attribute/PosixFilePermission;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/util/Set;", "isDirectory", "isExecutable", "isHidden", "isReadable", "isRegularFile", "isSameFileAs", "isSymbolicLink", "isWritable", "listDirectoryEntries", "", "moveTo", "notExists", "readAttributes", "A", "Ljava/nio/file/attribute/BasicFileAttributes;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/BasicFileAttributes;", "", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/LinkOption;)Ljava/util/Map;", "readSymbolicLink", "relativeTo", "relativeToOrNull", "relativeToOrSelf", "setAttribute", "value", "(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/Object;[Ljava/nio/file/LinkOption;)Ljava/nio/file/Path;", "setLastModifiedTime", "setOwner", "setPosixFilePermissions", "toPath", "Ljava/net/URI;", "useDirectoryEntries", "T", "block", "Lkotlin/sequences/Sequence;", "(Ljava/nio/file/Path;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib-jdk7"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/io/path/PathsKt"
)
class PathsKt__PathUtilsKt extends PathsKt__PathReadWriteKt {
   public PathsKt__PathUtilsKt() {
   }

   private static final Path Path(String var0) {
      Intrinsics.checkNotNullParameter(var0, "path");
      Path var1 = Paths.get(var0);
      Intrinsics.checkNotNullExpressionValue(var1, "get(path)");
      return var1;
   }

   private static final Path Path(String var0, String... var1) {
      Intrinsics.checkNotNullParameter(var0, "base");
      Intrinsics.checkNotNullParameter(var1, "subpaths");
      Path var2 = Paths.get(var0, (String[])Arrays.copyOf(var1, var1.length));
      Intrinsics.checkNotNullExpressionValue(var2, "get(base, *subpaths)");
      return var2;
   }

   private static final Path absolute(Path var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.toAbsolutePath();
      Intrinsics.checkNotNullExpressionValue(var0, "toAbsolutePath()");
      return var0;
   }

   private static final String absolutePathString(Path var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.toAbsolutePath().toString();
   }

   private static final Path copyTo(Path var0, Path var1, boolean var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "target");
      CopyOption[] var3;
      if (var2) {
         var3 = new CopyOption[]{(CopyOption)StandardCopyOption.REPLACE_EXISTING};
      } else {
         var3 = (CopyOption[])((Object[])(new CopyOption[0]));
      }

      var0 = Files.copy(var0, var1, (CopyOption[])Arrays.copyOf(var3, var3.length));
      Intrinsics.checkNotNullExpressionValue(var0, "copy(this, target, *options)");
      return var0;
   }

   private static final Path copyTo(Path var0, Path var1, CopyOption... var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "target");
      Intrinsics.checkNotNullParameter(var2, "options");
      var0 = Files.copy(var0, var1, (CopyOption[])Arrays.copyOf(var2, var2.length));
      Intrinsics.checkNotNullExpressionValue(var0, "copy(this, target, *options)");
      return var0;
   }

   // $FF: synthetic method
   static Path copyTo$default(Path var0, Path var1, boolean var2, int var3, Object var4) throws IOException {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "target");
      CopyOption[] var5;
      if (var2) {
         var5 = new CopyOption[]{(CopyOption)StandardCopyOption.REPLACE_EXISTING};
      } else {
         var5 = (CopyOption[])((Object[])(new CopyOption[0]));
      }

      var0 = Files.copy(var0, var1, (CopyOption[])Arrays.copyOf(var5, var5.length));
      Intrinsics.checkNotNullExpressionValue(var0, "copy(this, target, *options)");
      return var0;
   }

   private static final Path createDirectories(Path var0, FileAttribute... var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "attributes");
      var0 = Files.createDirectories(var0, (FileAttribute[])Arrays.copyOf(var1, var1.length));
      Intrinsics.checkNotNullExpressionValue(var0, "createDirectories(this, *attributes)");
      return var0;
   }

   private static final Path createDirectory(Path var0, FileAttribute... var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "attributes");
      var0 = Files.createDirectory(var0, (FileAttribute[])Arrays.copyOf(var1, var1.length));
      Intrinsics.checkNotNullExpressionValue(var0, "createDirectory(this, *attributes)");
      return var0;
   }

   private static final Path createFile(Path var0, FileAttribute... var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "attributes");
      var0 = Files.createFile(var0, (FileAttribute[])Arrays.copyOf(var1, var1.length));
      Intrinsics.checkNotNullExpressionValue(var0, "createFile(this, *attributes)");
      return var0;
   }

   private static final Path createLinkPointingTo(Path var0, Path var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "target");
      var0 = Files.createLink(var0, var1);
      Intrinsics.checkNotNullExpressionValue(var0, "createLink(this, target)");
      return var0;
   }

   private static final Path createSymbolicLinkPointingTo(Path var0, Path var1, FileAttribute... var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "target");
      Intrinsics.checkNotNullParameter(var2, "attributes");
      var0 = Files.createSymbolicLink(var0, var1, (FileAttribute[])Arrays.copyOf(var2, var2.length));
      Intrinsics.checkNotNullExpressionValue(var0, "createSymbolicLink(this, target, *attributes)");
      return var0;
   }

   private static final Path createTempDirectory(String var0, FileAttribute... var1) throws IOException {
      Intrinsics.checkNotNullParameter(var1, "attributes");
      Path var2 = Files.createTempDirectory(var0, (FileAttribute[])Arrays.copyOf(var1, var1.length));
      Intrinsics.checkNotNullExpressionValue(var2, "createTempDirectory(prefix, *attributes)");
      return var2;
   }

   public static final Path createTempDirectory(Path var0, String var1, FileAttribute... var2) throws IOException {
      Intrinsics.checkNotNullParameter(var2, "attributes");
      if (var0 != null) {
         var0 = Files.createTempDirectory(var0, var1, (FileAttribute[])Arrays.copyOf(var2, var2.length));
         Intrinsics.checkNotNullExpressionValue(var0, "createTempDirectory(dire…ory, prefix, *attributes)");
      } else {
         var0 = Files.createTempDirectory(var1, (FileAttribute[])Arrays.copyOf(var2, var2.length));
         Intrinsics.checkNotNullExpressionValue(var0, "createTempDirectory(prefix, *attributes)");
      }

      return var0;
   }

   // $FF: synthetic method
   static Path createTempDirectory$default(String var0, FileAttribute[] var1, int var2, Object var3) throws IOException {
      if ((var2 & 1) != 0) {
         var0 = null;
      }

      Intrinsics.checkNotNullParameter(var1, "attributes");
      Path var4 = Files.createTempDirectory(var0, (FileAttribute[])Arrays.copyOf(var1, var1.length));
      Intrinsics.checkNotNullExpressionValue(var4, "createTempDirectory(prefix, *attributes)");
      return var4;
   }

   // $FF: synthetic method
   public static Path createTempDirectory$default(Path var0, String var1, FileAttribute[] var2, int var3, Object var4) throws IOException {
      if ((var3 & 2) != 0) {
         var1 = null;
      }

      return PathsKt.createTempDirectory(var0, var1, var2);
   }

   private static final Path createTempFile(String var0, String var1, FileAttribute... var2) throws IOException {
      Intrinsics.checkNotNullParameter(var2, "attributes");
      Path var3 = Files.createTempFile(var0, var1, (FileAttribute[])Arrays.copyOf(var2, var2.length));
      Intrinsics.checkNotNullExpressionValue(var3, "createTempFile(prefix, suffix, *attributes)");
      return var3;
   }

   public static final Path createTempFile(Path var0, String var1, String var2, FileAttribute... var3) throws IOException {
      Intrinsics.checkNotNullParameter(var3, "attributes");
      if (var0 != null) {
         var0 = Files.createTempFile(var0, var1, var2, (FileAttribute[])Arrays.copyOf(var3, var3.length));
         Intrinsics.checkNotNullExpressionValue(var0, "createTempFile(directory…fix, suffix, *attributes)");
      } else {
         var0 = Files.createTempFile(var1, var2, (FileAttribute[])Arrays.copyOf(var3, var3.length));
         Intrinsics.checkNotNullExpressionValue(var0, "createTempFile(prefix, suffix, *attributes)");
      }

      return var0;
   }

   // $FF: synthetic method
   static Path createTempFile$default(String var0, String var1, FileAttribute[] var2, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         var0 = null;
      }

      if ((var3 & 2) != 0) {
         var1 = null;
      }

      Intrinsics.checkNotNullParameter(var2, "attributes");
      Path var5 = Files.createTempFile(var0, var1, (FileAttribute[])Arrays.copyOf(var2, var2.length));
      Intrinsics.checkNotNullExpressionValue(var5, "createTempFile(prefix, suffix, *attributes)");
      return var5;
   }

   // $FF: synthetic method
   public static Path createTempFile$default(Path var0, String var1, String var2, FileAttribute[] var3, int var4, Object var5) throws IOException {
      if ((var4 & 2) != 0) {
         var1 = null;
      }

      if ((var4 & 4) != 0) {
         var2 = null;
      }

      return PathsKt.createTempFile(var0, var1, var2, var3);
   }

   private static final void deleteExisting(Path var0) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Files.delete(var0);
   }

   private static final boolean deleteIfExists(Path var0) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Files.deleteIfExists(var0);
   }

   private static final Path div(Path var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      var0 = var0.resolve(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "this.resolve(other)");
      return var0;
   }

   private static final Path div(Path var0, Path var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      var0 = var0.resolve(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "this.resolve(other)");
      return var0;
   }

   private static final boolean exists(Path var0, LinkOption... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "options");
      return Files.exists(var0, (LinkOption[])Arrays.copyOf(var1, var1.length));
   }

   public static final Void fileAttributeViewNotAvailable(Path var0, Class var1) {
      Intrinsics.checkNotNullParameter(var0, "path");
      Intrinsics.checkNotNullParameter(var1, "attributeViewClass");
      throw new UnsupportedOperationException("The desired attribute view type " + var1 + " is not available for the file " + var0 + '.');
   }

   // $FF: synthetic method
   private static final FileAttributeView fileAttributesView(Path var0, LinkOption... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "options");
      Intrinsics.reifiedOperationMarker(4, "V");
      Class var2 = (Class)FileAttributeView.class;
      FileAttributeView var4 = Files.getFileAttributeView(var0, FileAttributeView.class, (LinkOption[])Arrays.copyOf(var1, var1.length));
      if (var4 != null) {
         FileAttributeView var3 = (FileAttributeView)var4;
         return var4;
      } else {
         Intrinsics.reifiedOperationMarker(4, "V");
         Class var5 = (Class)FileAttributeView.class;
         PathsKt.fileAttributeViewNotAvailable(var0, FileAttributeView.class);
         throw new KotlinNothingValueException();
      }
   }

   // $FF: synthetic method
   private static final FileAttributeView fileAttributesViewOrNull(Path var0, LinkOption... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "options");
      Intrinsics.reifiedOperationMarker(4, "V");
      Class var2 = (Class)FileAttributeView.class;
      return Files.getFileAttributeView(var0, FileAttributeView.class, (LinkOption[])Arrays.copyOf(var1, var1.length));
   }

   private static final long fileSize(Path var0) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Files.size(var0);
   }

   private static final FileStore fileStore(Path var0) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      FileStore var1 = Files.getFileStore(var0);
      Intrinsics.checkNotNullExpressionValue(var1, "getFileStore(this)");
      return var1;
   }

   private static final void forEachDirectoryEntry(Path var0, String var1, Function1 var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "glob");
      Intrinsics.checkNotNullParameter(var2, "action");
      Closeable var23 = (Closeable)Files.newDirectoryStream(var0, var1);

      label194: {
         Throwable var10000;
         label195: {
            boolean var10001;
            Iterator var25;
            try {
               DirectoryStream var24 = (DirectoryStream)var23;
               Intrinsics.checkNotNullExpressionValue(var24, "it");
               var25 = ((Iterable)var24).iterator();
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label195;
            }

            while(true) {
               try {
                  if (!var25.hasNext()) {
                     break;
                  }

                  var2.invoke(var25.next());
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label195;
               }
            }

            label178:
            try {
               Unit var27 = Unit.INSTANCE;
               break label194;
            } catch (Throwable var20) {
               var10000 = var20;
               var10001 = false;
               break label178;
            }
         }

         Throwable var26 = var10000;

         try {
            throw var26;
         } finally {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var23, var26);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      CloseableKt.closeFinally(var23, (Throwable)null);
      InlineMarker.finallyEnd(1);
   }

   // $FF: synthetic method
   static void forEachDirectoryEntry$default(Path var0, String var1, Function1 var2, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         var1 = "*";
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "glob");
      Intrinsics.checkNotNullParameter(var2, "action");
      Closeable var25 = (Closeable)Files.newDirectoryStream(var0, var1);

      label214: {
         Throwable var10000;
         label215: {
            boolean var10001;
            Iterator var27;
            try {
               DirectoryStream var26 = (DirectoryStream)var25;
               Intrinsics.checkNotNullExpressionValue(var26, "it");
               var27 = ((Iterable)var26).iterator();
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label215;
            }

            while(true) {
               try {
                  if (!var27.hasNext()) {
                     break;
                  }

                  var2.invoke(var27.next());
               } catch (Throwable var24) {
                  var10000 = var24;
                  var10001 = false;
                  break label215;
               }
            }

            label196:
            try {
               Unit var29 = Unit.INSTANCE;
               break label214;
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label196;
            }
         }

         Throwable var28 = var10000;

         try {
            throw var28;
         } finally {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var25, var28);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      CloseableKt.closeFinally(var25, (Throwable)null);
      InlineMarker.finallyEnd(1);
   }

   private static final Object getAttribute(Path var0, String var1, LinkOption... var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "attribute");
      Intrinsics.checkNotNullParameter(var2, "options");
      return Files.getAttribute(var0, var1, (LinkOption[])Arrays.copyOf(var2, var2.length));
   }

   public static final String getExtension(Path var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Path var2 = var0.getFileName();
      String var1 = "";
      String var3 = var1;
      if (var2 != null) {
         String var4 = var2.toString();
         var3 = var1;
         if (var4 != null) {
            var3 = StringsKt.substringAfterLast(var4, '.', "");
            if (var3 == null) {
               var3 = var1;
            }
         }
      }

      return var3;
   }

   // $FF: synthetic method
   public static void getExtension$annotations(Path var0) {
   }

   private static final String getInvariantSeparatorsPath(Path var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return PathsKt.getInvariantSeparatorsPathString(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use invariantSeparatorsPathString property instead.",
      replaceWith = @ReplaceWith(
   expression = "invariantSeparatorsPathString",
   imports = {}
)
   )
   public static void getInvariantSeparatorsPath$annotations(Path var0) {
   }

   public static final String getInvariantSeparatorsPathString(Path var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      String var1 = var0.getFileSystem().getSeparator();
      String var2;
      if (!Intrinsics.areEqual((Object)var1, (Object)"/")) {
         var2 = var0.toString();
         Intrinsics.checkNotNullExpressionValue(var1, "separator");
         var2 = StringsKt.replace$default(var2, var1, "/", false, 4, (Object)null);
      } else {
         var2 = var0.toString();
      }

      return var2;
   }

   // $FF: synthetic method
   public static void getInvariantSeparatorsPathString$annotations(Path var0) {
   }

   private static final FileTime getLastModifiedTime(Path var0, LinkOption... var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "options");
      FileTime var2 = Files.getLastModifiedTime(var0, (LinkOption[])Arrays.copyOf(var1, var1.length));
      Intrinsics.checkNotNullExpressionValue(var2, "getLastModifiedTime(this, *options)");
      return var2;
   }

   public static final String getName(Path var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.getFileName();
      String var2;
      if (var0 != null) {
         var2 = var0.toString();
      } else {
         var2 = null;
      }

      String var1 = var2;
      if (var2 == null) {
         var1 = "";
      }

      return var1;
   }

   // $FF: synthetic method
   public static void getName$annotations(Path var0) {
   }

   public static final String getNameWithoutExtension(Path var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.getFileName();
      String var2;
      if (var0 != null) {
         var2 = var0.toString();
         if (var2 != null) {
            String var1 = StringsKt.substringBeforeLast$default(var2, ".", (String)null, 2, (Object)null);
            var2 = var1;
            if (var1 != null) {
               return var2;
            }
         }
      }

      var2 = "";
      return var2;
   }

   // $FF: synthetic method
   public static void getNameWithoutExtension$annotations(Path var0) {
   }

   private static final UserPrincipal getOwner(Path var0, LinkOption... var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "options");
      return Files.getOwner(var0, (LinkOption[])Arrays.copyOf(var1, var1.length));
   }

   private static final String getPathString(Path var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.toString();
   }

   // $FF: synthetic method
   public static void getPathString$annotations(Path var0) {
   }

   private static final Set getPosixFilePermissions(Path var0, LinkOption... var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "options");
      Set var2 = Files.getPosixFilePermissions(var0, (LinkOption[])Arrays.copyOf(var1, var1.length));
      Intrinsics.checkNotNullExpressionValue(var2, "getPosixFilePermissions(this, *options)");
      return var2;
   }

   private static final boolean isDirectory(Path var0, LinkOption... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "options");
      return Files.isDirectory(var0, (LinkOption[])Arrays.copyOf(var1, var1.length));
   }

   private static final boolean isExecutable(Path var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Files.isExecutable(var0);
   }

   private static final boolean isHidden(Path var0) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Files.isHidden(var0);
   }

   private static final boolean isReadable(Path var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Files.isReadable(var0);
   }

   private static final boolean isRegularFile(Path var0, LinkOption... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "options");
      return Files.isRegularFile(var0, (LinkOption[])Arrays.copyOf(var1, var1.length));
   }

   private static final boolean isSameFileAs(Path var0, Path var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      return Files.isSameFile(var0, var1);
   }

   private static final boolean isSymbolicLink(Path var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Files.isSymbolicLink(var0);
   }

   private static final boolean isWritable(Path var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Files.isWritable(var0);
   }

   public static final List listDirectoryEntries(Path var0, String var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "glob");
      Closeable var9 = (Closeable)Files.newDirectoryStream(var0, var1);

      List var12;
      try {
         DirectoryStream var11 = (DirectoryStream)var9;
         Intrinsics.checkNotNullExpressionValue(var11, "it");
         var12 = CollectionsKt.toList((Iterable)var11);
      } catch (Throwable var8) {
         Throwable var10 = var8;

         try {
            throw var10;
         } finally {
            CloseableKt.closeFinally(var9, var8);
         }
      }

      CloseableKt.closeFinally(var9, (Throwable)null);
      return var12;
   }

   // $FF: synthetic method
   public static List listDirectoryEntries$default(Path var0, String var1, int var2, Object var3) throws IOException {
      if ((var2 & 1) != 0) {
         var1 = "*";
      }

      return PathsKt.listDirectoryEntries(var0, var1);
   }

   private static final Path moveTo(Path var0, Path var1, boolean var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "target");
      CopyOption[] var3;
      if (var2) {
         var3 = new CopyOption[]{(CopyOption)StandardCopyOption.REPLACE_EXISTING};
      } else {
         var3 = (CopyOption[])((Object[])(new CopyOption[0]));
      }

      var0 = Files.move(var0, var1, (CopyOption[])Arrays.copyOf(var3, var3.length));
      Intrinsics.checkNotNullExpressionValue(var0, "move(this, target, *options)");
      return var0;
   }

   private static final Path moveTo(Path var0, Path var1, CopyOption... var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "target");
      Intrinsics.checkNotNullParameter(var2, "options");
      var0 = Files.move(var0, var1, (CopyOption[])Arrays.copyOf(var2, var2.length));
      Intrinsics.checkNotNullExpressionValue(var0, "move(this, target, *options)");
      return var0;
   }

   // $FF: synthetic method
   static Path moveTo$default(Path var0, Path var1, boolean var2, int var3, Object var4) throws IOException {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "target");
      CopyOption[] var5;
      if (var2) {
         var5 = new CopyOption[]{(CopyOption)StandardCopyOption.REPLACE_EXISTING};
      } else {
         var5 = (CopyOption[])((Object[])(new CopyOption[0]));
      }

      var0 = Files.move(var0, var1, (CopyOption[])Arrays.copyOf(var5, var5.length));
      Intrinsics.checkNotNullExpressionValue(var0, "move(this, target, *options)");
      return var0;
   }

   private static final boolean notExists(Path var0, LinkOption... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "options");
      return Files.notExists(var0, (LinkOption[])Arrays.copyOf(var1, var1.length));
   }

   // $FF: synthetic method
   private static final BasicFileAttributes readAttributes(Path var0, LinkOption... var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "options");
      Intrinsics.reifiedOperationMarker(4, "A");
      Class var2 = (Class)BasicFileAttributes.class;
      BasicFileAttributes var4 = Files.readAttributes(var0, BasicFileAttributes.class, (LinkOption[])Arrays.copyOf(var1, var1.length));
      Intrinsics.checkNotNullExpressionValue(var4, "readAttributes(this, A::class.java, *options)");
      BasicFileAttributes var3 = (BasicFileAttributes)var4;
      return var4;
   }

   private static final Map readAttributes(Path var0, String var1, LinkOption... var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "attributes");
      Intrinsics.checkNotNullParameter(var2, "options");
      Map var3 = Files.readAttributes(var0, var1, (LinkOption[])Arrays.copyOf(var2, var2.length));
      Intrinsics.checkNotNullExpressionValue(var3, "readAttributes(this, attributes, *options)");
      return var3;
   }

   private static final Path readSymbolicLink(Path var0) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = Files.readSymbolicLink(var0);
      Intrinsics.checkNotNullExpressionValue(var0, "readSymbolicLink(this)");
      return var0;
   }

   public static final Path relativeTo(Path var0, Path var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "base");

      try {
         Path var2 = PathRelativizer.INSTANCE.tryRelativeTo(var0, var1);
         return var2;
      } catch (IllegalArgumentException var3) {
         throw new IllegalArgumentException(var3.getMessage() + "\nthis path: " + var0 + "\nbase path: " + var1, (Throwable)var3);
      }
   }

   public static final Path relativeToOrNull(Path var0, Path var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "base");

      try {
         var0 = PathRelativizer.INSTANCE.tryRelativeTo(var0, var1);
      } catch (IllegalArgumentException var2) {
         var0 = null;
         var1 = (Path)null;
      }

      return var0;
   }

   public static final Path relativeToOrSelf(Path var0, Path var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "base");
      var1 = PathsKt.relativeToOrNull(var0, var1);
      if (var1 != null) {
         var0 = var1;
      }

      return var0;
   }

   private static final Path setAttribute(Path var0, String var1, Object var2, LinkOption... var3) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "attribute");
      Intrinsics.checkNotNullParameter(var3, "options");
      var0 = Files.setAttribute(var0, var1, var2, (LinkOption[])Arrays.copyOf(var3, var3.length));
      Intrinsics.checkNotNullExpressionValue(var0, "setAttribute(this, attribute, value, *options)");
      return var0;
   }

   private static final Path setLastModifiedTime(Path var0, FileTime var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "value");
      var0 = Files.setLastModifiedTime(var0, var1);
      Intrinsics.checkNotNullExpressionValue(var0, "setLastModifiedTime(this, value)");
      return var0;
   }

   private static final Path setOwner(Path var0, UserPrincipal var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "value");
      var0 = Files.setOwner(var0, var1);
      Intrinsics.checkNotNullExpressionValue(var0, "setOwner(this, value)");
      return var0;
   }

   private static final Path setPosixFilePermissions(Path var0, Set var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "value");
      var0 = Files.setPosixFilePermissions(var0, var1);
      Intrinsics.checkNotNullExpressionValue(var0, "setPosixFilePermissions(this, value)");
      return var0;
   }

   private static final Path toPath(URI var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Path var1 = Paths.get(var0);
      Intrinsics.checkNotNullExpressionValue(var1, "get(this)");
      return var1;
   }

   private static final Object useDirectoryEntries(Path var0, String var1, Function1 var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "glob");
      Intrinsics.checkNotNullParameter(var2, "block");
      Closeable var9 = (Closeable)Files.newDirectoryStream(var0, var1);

      Object var11;
      try {
         DirectoryStream var10 = (DirectoryStream)var9;
         Intrinsics.checkNotNullExpressionValue(var10, "it");
         var11 = var2.invoke(CollectionsKt.asSequence((Iterable)var10));
      } catch (Throwable var8) {
         Throwable var12 = var8;

         try {
            throw var12;
         } finally {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var9, var8);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      CloseableKt.closeFinally(var9, (Throwable)null);
      InlineMarker.finallyEnd(1);
      return var11;
   }

   // $FF: synthetic method
   static Object useDirectoryEntries$default(Path var0, String var1, Function1 var2, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         var1 = "*";
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "glob");
      Intrinsics.checkNotNullParameter(var2, "block");
      Closeable var11 = (Closeable)Files.newDirectoryStream(var0, var1);

      Object var13;
      try {
         DirectoryStream var12 = (DirectoryStream)var11;
         Intrinsics.checkNotNullExpressionValue(var12, "it");
         var13 = var2.invoke(CollectionsKt.asSequence((Iterable)var12));
      } catch (Throwable var10) {
         Throwable var14 = var10;

         try {
            throw var14;
         } finally {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var11, var10);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      CloseableKt.closeFinally(var11, (Throwable)null);
      InlineMarker.finallyEnd(1);
      return var13;
   }
}
