package kotlinx.coroutines.channels;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.selects.SelectClause1;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"kotlinx/coroutines/channels/ChannelsKt__ChannelsKt", "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt"},
   k = 4,
   mv = {1, 4, 0}
)
public final class ChannelsKt {
   public static final String DEFAULT_CLOSE_MESSAGE = "Channel was closed";

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object all(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.all(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object all$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.all(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object any(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.any(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object any(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.any(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object any$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.any(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object associate(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.associate(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object associate$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.associate(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object associateBy(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.associateBy(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object associateBy(ReceiveChannel var0, Function1 var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.associateBy(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object associateBy$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.associateBy(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object associateBy$$forInline(ReceiveChannel var0, Function1 var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.associateBy(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object associateByTo(ReceiveChannel var0, Map var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.associateByTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object associateByTo(ReceiveChannel var0, Map var1, Function1 var2, Function1 var3, Continuation var4) {
      return ChannelsKt__Channels_commonKt.associateByTo(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object associateByTo$$forInline(ReceiveChannel var0, Map var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.associateByTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object associateByTo$$forInline(ReceiveChannel var0, Map var1, Function1 var2, Function1 var3, Continuation var4) {
      return ChannelsKt__Channels_commonKt.associateByTo(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object associateTo(ReceiveChannel var0, Map var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.associateTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object associateTo$$forInline(ReceiveChannel var0, Map var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.associateTo(var0, var1, var2, var3);
   }

   public static final void cancelConsumed(ReceiveChannel var0, Throwable var1) {
      ChannelsKt__Channels_commonKt.cancelConsumed(var0, var1);
   }

   public static final Object consume(BroadcastChannel var0, Function1 var1) {
      return ChannelsKt__Channels_commonKt.consume(var0, var1);
   }

   public static final Object consume(ReceiveChannel var0, Function1 var1) {
      return ChannelsKt__Channels_commonKt.consume(var0, var1);
   }

   public static final Object consumeEach(BroadcastChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.consumeEach(var0, var1, var2);
   }

   public static final Object consumeEach(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.consumeEach(var0, var1, var2);
   }

   private static final Object consumeEach$$forInline(BroadcastChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.consumeEach(var0, var1, var2);
   }

   private static final Object consumeEach$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.consumeEach(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object consumeEachIndexed(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.consumeEachIndexed(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object consumeEachIndexed$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.consumeEachIndexed(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Function1 consumes(ReceiveChannel var0) {
      return ChannelsKt__Channels_commonKt.consumes(var0);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Function1 consumesAll(ReceiveChannel... var0) {
      return ChannelsKt__Channels_commonKt.consumesAll(var0);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object count(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.count(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object count(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.count(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object count$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.count(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel distinct(ReceiveChannel var0) {
      return ChannelsKt__Channels_commonKt.distinct(var0);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel distinctBy(ReceiveChannel var0, CoroutineContext var1, Function2 var2) {
      return ChannelsKt__Channels_commonKt.distinctBy(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel distinctBy$default(ReceiveChannel var0, CoroutineContext var1, Function2 var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.distinctBy$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel drop(ReceiveChannel var0, int var1, CoroutineContext var2) {
      return ChannelsKt__Channels_commonKt.drop(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel drop$default(ReceiveChannel var0, int var1, CoroutineContext var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.drop$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel dropWhile(ReceiveChannel var0, CoroutineContext var1, Function2 var2) {
      return ChannelsKt__Channels_commonKt.dropWhile(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel dropWhile$default(ReceiveChannel var0, CoroutineContext var1, Function2 var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.dropWhile$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object elementAt(ReceiveChannel var0, int var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.elementAt(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object elementAtOrElse(ReceiveChannel var0, int var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.elementAtOrElse(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object elementAtOrElse$$forInline(ReceiveChannel var0, int var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.elementAtOrElse(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object elementAtOrNull(ReceiveChannel var0, int var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.elementAtOrNull(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel filter(ReceiveChannel var0, CoroutineContext var1, Function2 var2) {
      return ChannelsKt__Channels_commonKt.filter(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel filter$default(ReceiveChannel var0, CoroutineContext var1, Function2 var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.filter$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel filterIndexed(ReceiveChannel var0, CoroutineContext var1, Function3 var2) {
      return ChannelsKt__Channels_commonKt.filterIndexed(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel filterIndexed$default(ReceiveChannel var0, CoroutineContext var1, Function3 var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.filterIndexed$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object filterIndexedTo(ReceiveChannel var0, Collection var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.filterIndexedTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object filterIndexedTo(ReceiveChannel var0, SendChannel var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.filterIndexedTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object filterIndexedTo$$forInline(ReceiveChannel var0, Collection var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.filterIndexedTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object filterIndexedTo$$forInline(ReceiveChannel var0, SendChannel var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.filterIndexedTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel filterNot(ReceiveChannel var0, CoroutineContext var1, Function2 var2) {
      return ChannelsKt__Channels_commonKt.filterNot(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel filterNot$default(ReceiveChannel var0, CoroutineContext var1, Function2 var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.filterNot$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel filterNotNull(ReceiveChannel var0) {
      return ChannelsKt__Channels_commonKt.filterNotNull(var0);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object filterNotNullTo(ReceiveChannel var0, Collection var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.filterNotNullTo(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object filterNotNullTo(ReceiveChannel var0, SendChannel var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.filterNotNullTo(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object filterNotTo(ReceiveChannel var0, Collection var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.filterNotTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object filterNotTo(ReceiveChannel var0, SendChannel var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.filterNotTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object filterNotTo$$forInline(ReceiveChannel var0, Collection var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.filterNotTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object filterNotTo$$forInline(ReceiveChannel var0, SendChannel var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.filterNotTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object filterTo(ReceiveChannel var0, Collection var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.filterTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object filterTo(ReceiveChannel var0, SendChannel var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.filterTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object filterTo$$forInline(ReceiveChannel var0, Collection var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.filterTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object filterTo$$forInline(ReceiveChannel var0, SendChannel var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.filterTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object find(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.find(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object find$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.find(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object findLast(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.findLast(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object findLast$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.findLast(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object first(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.first(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object first(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.first(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object first$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.first(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object firstOrNull(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.firstOrNull(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object firstOrNull(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.firstOrNull(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object firstOrNull$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.firstOrNull(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel flatMap(ReceiveChannel var0, CoroutineContext var1, Function2 var2) {
      return ChannelsKt__Channels_commonKt.flatMap(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel flatMap$default(ReceiveChannel var0, CoroutineContext var1, Function2 var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.flatMap$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object fold(ReceiveChannel var0, Object var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.fold(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object fold$$forInline(ReceiveChannel var0, Object var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.fold(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object foldIndexed(ReceiveChannel var0, Object var1, Function3 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.foldIndexed(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object foldIndexed$$forInline(ReceiveChannel var0, Object var1, Function3 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.foldIndexed(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object groupBy(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.groupBy(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object groupBy(ReceiveChannel var0, Function1 var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.groupBy(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object groupBy$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.groupBy(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object groupBy$$forInline(ReceiveChannel var0, Function1 var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.groupBy(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object groupByTo(ReceiveChannel var0, Map var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.groupByTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object groupByTo(ReceiveChannel var0, Map var1, Function1 var2, Function1 var3, Continuation var4) {
      return ChannelsKt__Channels_commonKt.groupByTo(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object groupByTo$$forInline(ReceiveChannel var0, Map var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.groupByTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object groupByTo$$forInline(ReceiveChannel var0, Map var1, Function1 var2, Function1 var3, Continuation var4) {
      return ChannelsKt__Channels_commonKt.groupByTo(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object indexOf(ReceiveChannel var0, Object var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.indexOf(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object indexOfFirst(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.indexOfFirst(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object indexOfFirst$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.indexOfFirst(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object indexOfLast(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.indexOfLast(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object indexOfLast$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.indexOfLast(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object last(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.last(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object last(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.last(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object last$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.last(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object lastIndexOf(ReceiveChannel var0, Object var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.lastIndexOf(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object lastOrNull(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.lastOrNull(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object lastOrNull(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.lastOrNull(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object lastOrNull$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.lastOrNull(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel map(ReceiveChannel var0, CoroutineContext var1, Function2 var2) {
      return ChannelsKt__Channels_commonKt.map(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel map$default(ReceiveChannel var0, CoroutineContext var1, Function2 var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.map$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel mapIndexed(ReceiveChannel var0, CoroutineContext var1, Function3 var2) {
      return ChannelsKt__Channels_commonKt.mapIndexed(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel mapIndexed$default(ReceiveChannel var0, CoroutineContext var1, Function3 var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.mapIndexed$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel mapIndexedNotNull(ReceiveChannel var0, CoroutineContext var1, Function3 var2) {
      return ChannelsKt__Channels_commonKt.mapIndexedNotNull(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel mapIndexedNotNull$default(ReceiveChannel var0, CoroutineContext var1, Function3 var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.mapIndexedNotNull$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object mapIndexedNotNullTo(ReceiveChannel var0, Collection var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object mapIndexedNotNullTo(ReceiveChannel var0, SendChannel var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object mapIndexedNotNullTo$$forInline(ReceiveChannel var0, Collection var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object mapIndexedNotNullTo$$forInline(ReceiveChannel var0, SendChannel var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object mapIndexedTo(ReceiveChannel var0, Collection var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapIndexedTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object mapIndexedTo(ReceiveChannel var0, SendChannel var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapIndexedTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object mapIndexedTo$$forInline(ReceiveChannel var0, Collection var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapIndexedTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object mapIndexedTo$$forInline(ReceiveChannel var0, SendChannel var1, Function2 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapIndexedTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel mapNotNull(ReceiveChannel var0, CoroutineContext var1, Function2 var2) {
      return ChannelsKt__Channels_commonKt.mapNotNull(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel mapNotNull$default(ReceiveChannel var0, CoroutineContext var1, Function2 var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.mapNotNull$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object mapNotNullTo(ReceiveChannel var0, Collection var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapNotNullTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object mapNotNullTo(ReceiveChannel var0, SendChannel var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapNotNullTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object mapNotNullTo$$forInline(ReceiveChannel var0, Collection var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapNotNullTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object mapNotNullTo$$forInline(ReceiveChannel var0, SendChannel var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapNotNullTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object mapTo(ReceiveChannel var0, Collection var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object mapTo(ReceiveChannel var0, SendChannel var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object mapTo$$forInline(ReceiveChannel var0, Collection var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object mapTo$$forInline(ReceiveChannel var0, SendChannel var1, Function1 var2, Continuation var3) {
      return ChannelsKt__Channels_commonKt.mapTo(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object maxBy(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.maxBy(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object maxBy$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.maxBy(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object maxWith(ReceiveChannel var0, Comparator var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.maxWith(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object minBy(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.minBy(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object minBy$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.minBy(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object minWith(ReceiveChannel var0, Comparator var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.minWith(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object none(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.none(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object none(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.none(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object none$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.none(var0, var1, var2);
   }

   public static final SelectClause1 onReceiveOrNull(ReceiveChannel var0) {
      return ChannelsKt__Channels_commonKt.onReceiveOrNull(var0);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object partition(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.partition(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object partition$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.partition(var0, var1, var2);
   }

   public static final Object receiveOrNull(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.receiveOrNull(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object reduce(ReceiveChannel var0, Function2 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.reduce(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object reduce$$forInline(ReceiveChannel var0, Function2 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.reduce(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object reduceIndexed(ReceiveChannel var0, Function3 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.reduceIndexed(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object reduceIndexed$$forInline(ReceiveChannel var0, Function3 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.reduceIndexed(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel requireNoNulls(ReceiveChannel var0) {
      return ChannelsKt__Channels_commonKt.requireNoNulls(var0);
   }

   public static final void sendBlocking(SendChannel var0, Object var1) {
      ChannelsKt__ChannelsKt.sendBlocking(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object single(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.single(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object single(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.single(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object single$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.single(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object singleOrNull(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.singleOrNull(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object singleOrNull(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.singleOrNull(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object singleOrNull$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.singleOrNull(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object sumBy(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.sumBy(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object sumBy$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.sumBy(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object sumByDouble(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.sumByDouble(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   private static final Object sumByDouble$$forInline(ReceiveChannel var0, Function1 var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.sumByDouble(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel take(ReceiveChannel var0, int var1, CoroutineContext var2) {
      return ChannelsKt__Channels_commonKt.take(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel take$default(ReceiveChannel var0, int var1, CoroutineContext var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.take$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel takeWhile(ReceiveChannel var0, CoroutineContext var1, Function2 var2) {
      return ChannelsKt__Channels_commonKt.takeWhile(var0, var1, var2);
   }

   // $FF: synthetic method
   public static ReceiveChannel takeWhile$default(ReceiveChannel var0, CoroutineContext var1, Function2 var2, int var3, Object var4) {
      return ChannelsKt__Channels_commonKt.takeWhile$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object toChannel(ReceiveChannel var0, SendChannel var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.toChannel(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object toCollection(ReceiveChannel var0, Collection var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.toCollection(var0, var1, var2);
   }

   public static final Object toList(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.toList(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object toMap(ReceiveChannel var0, Map var1, Continuation var2) {
      return ChannelsKt__Channels_commonKt.toMap(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object toMap(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.toMap(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object toMutableList(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.toMutableList(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object toMutableSet(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.toMutableSet(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final Object toSet(ReceiveChannel var0, Continuation var1) {
      return ChannelsKt__Channels_commonKt.toSet(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel withIndex(ReceiveChannel var0, CoroutineContext var1) {
      return ChannelsKt__Channels_commonKt.withIndex(var0, var1);
   }

   // $FF: synthetic method
   public static ReceiveChannel withIndex$default(ReceiveChannel var0, CoroutineContext var1, int var2, Object var3) {
      return ChannelsKt__Channels_commonKt.withIndex$default(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel zip(ReceiveChannel var0, ReceiveChannel var1) {
      return ChannelsKt__Channels_commonKt.zip(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Channel operators are deprecated in favour of Flow and will be removed in 1.4.x"
   )
   public static final ReceiveChannel zip(ReceiveChannel var0, ReceiveChannel var1, CoroutineContext var2, Function2 var3) {
      return ChannelsKt__Channels_commonKt.zip(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static ReceiveChannel zip$default(ReceiveChannel var0, ReceiveChannel var1, CoroutineContext var2, Function2 var3, int var4, Object var5) {
      return ChannelsKt__Channels_commonKt.zip$default(var0, var1, var2, var3, var4, var5);
   }
}
