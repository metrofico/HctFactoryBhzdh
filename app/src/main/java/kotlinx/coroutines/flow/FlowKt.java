package kotlinx.coroutines.flow;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ReceiveChannel;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt", "kotlinx/coroutines/flow/FlowKt__ChannelsKt", "kotlinx/coroutines/flow/FlowKt__CollectKt", "kotlinx/coroutines/flow/FlowKt__CollectionKt", "kotlinx/coroutines/flow/FlowKt__ContextKt", "kotlinx/coroutines/flow/FlowKt__CountKt", "kotlinx/coroutines/flow/FlowKt__DelayKt", "kotlinx/coroutines/flow/FlowKt__DistinctKt", "kotlinx/coroutines/flow/FlowKt__EmittersKt", "kotlinx/coroutines/flow/FlowKt__ErrorsKt", "kotlinx/coroutines/flow/FlowKt__LimitKt", "kotlinx/coroutines/flow/FlowKt__MergeKt", "kotlinx/coroutines/flow/FlowKt__MigrationKt", "kotlinx/coroutines/flow/FlowKt__ReduceKt", "kotlinx/coroutines/flow/FlowKt__ShareKt", "kotlinx/coroutines/flow/FlowKt__TransformKt", "kotlinx/coroutines/flow/FlowKt__ZipKt"},
   k = 4,
   mv = {1, 4, 0}
)
public final class FlowKt {
   public static final String DEFAULT_CONCURRENCY_PROPERTY_NAME = "kotlinx.coroutines.flow.defaultConcurrency";

   public static final Flow asFlow(Iterable var0) {
      return FlowKt__BuildersKt.asFlow(var0);
   }

   public static final Flow asFlow(Iterator var0) {
      return FlowKt__BuildersKt.asFlow(var0);
   }

   public static final Flow asFlow(Function0 var0) {
      return FlowKt__BuildersKt.asFlow(var0);
   }

   public static final Flow asFlow(Function1 var0) {
      return FlowKt__BuildersKt.asFlow(var0);
   }

   public static final Flow asFlow(IntRange var0) {
      return FlowKt__BuildersKt.asFlow(var0);
   }

   public static final Flow asFlow(LongRange var0) {
      return FlowKt__BuildersKt.asFlow(var0);
   }

   public static final Flow asFlow(Sequence var0) {
      return FlowKt__BuildersKt.asFlow(var0);
   }

   public static final Flow asFlow(BroadcastChannel var0) {
      return FlowKt__ChannelsKt.asFlow(var0);
   }

   public static final Flow asFlow(int[] var0) {
      return FlowKt__BuildersKt.asFlow(var0);
   }

   public static final Flow asFlow(long[] var0) {
      return FlowKt__BuildersKt.asFlow(var0);
   }

   public static final Flow asFlow(Object[] var0) {
      return FlowKt__BuildersKt.asFlow(var0);
   }

   public static final SharedFlow asSharedFlow(MutableSharedFlow var0) {
      return FlowKt__ShareKt.asSharedFlow(var0);
   }

   public static final StateFlow asStateFlow(MutableStateFlow var0) {
      return FlowKt__ShareKt.asStateFlow(var0);
   }

   @Deprecated(
      level = DeprecationLevel.WARNING,
      message = "Use shareIn operator and the resulting SharedFlow as a replacement for BroadcastChannel",
      replaceWith = @ReplaceWith(
   expression = "shareIn(scope, 0, SharingStarted.Lazily)",
   imports = {}
)
   )
   public static final BroadcastChannel broadcastIn(Flow var0, CoroutineScope var1, CoroutineStart var2) {
      return FlowKt__ChannelsKt.broadcastIn(var0, var1, var2);
   }

   // $FF: synthetic method
   public static BroadcastChannel broadcastIn$default(Flow var0, CoroutineScope var1, CoroutineStart var2, int var3, Object var4) {
      return FlowKt__ChannelsKt.broadcastIn$default(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.4.0, binary compatibility with earlier versions"
   )
   public static final Flow buffer(Flow var0, int var1) {
      return FlowKt__ContextKt.buffer(var0, var1);
   }

   public static final Flow buffer(Flow var0, int var1, BufferOverflow var2) {
      return FlowKt__ContextKt.buffer(var0, var1, var2);
   }

   // $FF: synthetic method
   public static Flow buffer$default(Flow var0, int var1, int var2, Object var3) {
      return FlowKt__ContextKt.buffer$default(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static Flow buffer$default(Flow var0, int var1, BufferOverflow var2, int var3, Object var4) {
      return FlowKt__ContextKt.buffer$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'cache()' is 'shareIn' with unlimited replay and 'started = SharingStared.Lazily' argument'",
      replaceWith = @ReplaceWith(
   expression = "this.shareIn(scope, Int.MAX_VALUE, started = SharingStared.Lazily)",
   imports = {}
)
   )
   public static final Flow cache(Flow var0) {
      return FlowKt__MigrationKt.cache(var0);
   }

   public static final Flow callbackFlow(Function2 var0) {
      return FlowKt__BuildersKt.callbackFlow(var0);
   }

   public static final Flow cancellable(Flow var0) {
      return FlowKt__ContextKt.cancellable(var0);
   }

   public static final Flow catch(Flow var0, Function3 var1) {
      return FlowKt__ErrorsKt.catch(var0, var1);
   }

   public static final Object catchImpl(Flow var0, FlowCollector var1, Continuation var2) {
      return FlowKt__ErrorsKt.catchImpl(var0, var1, var2);
   }

   public static final Flow channelFlow(Function2 var0) {
      return FlowKt__BuildersKt.channelFlow(var0);
   }

   public static final Object collect(Flow var0, Continuation var1) {
      return FlowKt__CollectKt.collect(var0, var1);
   }

   public static final Object collect(Flow var0, Function2 var1, Continuation var2) {
      return FlowKt__CollectKt.collect(var0, var1, var2);
   }

   private static final Object collect$$forInline(Flow var0, Function2 var1, Continuation var2) {
      return FlowKt__CollectKt.collect(var0, var1, var2);
   }

   public static final Object collectIndexed(Flow var0, Function3 var1, Continuation var2) {
      return FlowKt__CollectKt.collectIndexed(var0, var1, var2);
   }

   private static final Object collectIndexed$$forInline(Flow var0, Function3 var1, Continuation var2) {
      return FlowKt__CollectKt.collectIndexed(var0, var1, var2);
   }

   public static final Object collectLatest(Flow var0, Function2 var1, Continuation var2) {
      return FlowKt__CollectKt.collectLatest(var0, var1, var2);
   }

   public static final Object collectWhile(Flow var0, Function2 var1, Continuation var2) {
      return FlowKt__LimitKt.collectWhile(var0, var1, var2);
   }

   private static final Object collectWhile$$forInline(Flow var0, Function2 var1, Continuation var2) {
      return FlowKt__LimitKt.collectWhile(var0, var1, var2);
   }

   // $FF: synthetic method
   public static final Flow combine(Iterable var0, Function2 var1) {
      return FlowKt__ZipKt.combine(var0, var1);
   }

   public static final Flow combine(Flow var0, Flow var1, Function3 var2) {
      return FlowKt__ZipKt.combine(var0, var1, var2);
   }

   public static final Flow combine(Flow var0, Flow var1, Flow var2, Function4 var3) {
      return FlowKt__ZipKt.combine(var0, var1, var2, var3);
   }

   public static final Flow combine(Flow var0, Flow var1, Flow var2, Flow var3, Function5 var4) {
      return FlowKt__ZipKt.combine(var0, var1, var2, var3, var4);
   }

   public static final Flow combine(Flow var0, Flow var1, Flow var2, Flow var3, Flow var4, Function6 var5) {
      return FlowKt__ZipKt.combine(var0, var1, var2, var3, var4, var5);
   }

   // $FF: synthetic method
   public static final Flow combine(Flow[] var0, Function2 var1) {
      return FlowKt__ZipKt.combine(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'combineLatest' is 'combine'",
      replaceWith = @ReplaceWith(
   expression = "this.combine(other, transform)",
   imports = {}
)
   )
   public static final Flow combineLatest(Flow var0, Flow var1, Function3 var2) {
      return FlowKt__MigrationKt.combineLatest(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'combineLatest' is 'combine'",
      replaceWith = @ReplaceWith(
   expression = "combine(this, other, other2, transform)",
   imports = {}
)
   )
   public static final Flow combineLatest(Flow var0, Flow var1, Flow var2, Function4 var3) {
      return FlowKt__MigrationKt.combineLatest(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'combineLatest' is 'combine'",
      replaceWith = @ReplaceWith(
   expression = "combine(this, other, other2, other3, transform)",
   imports = {}
)
   )
   public static final Flow combineLatest(Flow var0, Flow var1, Flow var2, Flow var3, Function5 var4) {
      return FlowKt__MigrationKt.combineLatest(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'combineLatest' is 'combine'",
      replaceWith = @ReplaceWith(
   expression = "combine(this, other, other2, other3, transform)",
   imports = {}
)
   )
   public static final Flow combineLatest(Flow var0, Flow var1, Flow var2, Flow var3, Flow var4, Function6 var5) {
      return FlowKt__MigrationKt.combineLatest(var0, var1, var2, var3, var4, var5);
   }

   // $FF: synthetic method
   public static final Flow combineTransform(Iterable var0, Function3 var1) {
      return FlowKt__ZipKt.combineTransform(var0, var1);
   }

   public static final Flow combineTransform(Flow var0, Flow var1, Function4 var2) {
      return FlowKt__ZipKt.combineTransform(var0, var1, var2);
   }

   public static final Flow combineTransform(Flow var0, Flow var1, Flow var2, Function5 var3) {
      return FlowKt__ZipKt.combineTransform(var0, var1, var2, var3);
   }

   public static final Flow combineTransform(Flow var0, Flow var1, Flow var2, Flow var3, Function6 var4) {
      return FlowKt__ZipKt.combineTransform(var0, var1, var2, var3, var4);
   }

   public static final Flow combineTransform(Flow var0, Flow var1, Flow var2, Flow var3, Flow var4, Function7 var5) {
      return FlowKt__ZipKt.combineTransform(var0, var1, var2, var3, var4, var5);
   }

   // $FF: synthetic method
   public static final Flow combineTransform(Flow[] var0, Function3 var1) {
      return FlowKt__ZipKt.combineTransform(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'compose' is 'let'",
      replaceWith = @ReplaceWith(
   expression = "let(transformer)",
   imports = {}
)
   )
   public static final Flow compose(Flow var0, Function1 var1) {
      return FlowKt__MigrationKt.compose(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'concatMap' is 'flatMapConcat'",
      replaceWith = @ReplaceWith(
   expression = "flatMapConcat(mapper)",
   imports = {}
)
   )
   public static final Flow concatMap(Flow var0, Function1 var1) {
      return FlowKt__MigrationKt.concatMap(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'concatWith' is 'onCompletion'. Use 'onCompletion { emit(value) }'",
      replaceWith = @ReplaceWith(
   expression = "onCompletion { emit(value) }",
   imports = {}
)
   )
   public static final Flow concatWith(Flow var0, Object var1) {
      return FlowKt__MigrationKt.concatWith(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'concatWith' is 'onCompletion'. Use 'onCompletion { emitAll(other) }'",
      replaceWith = @ReplaceWith(
   expression = "onCompletion { emitAll(other) }",
   imports = {}
)
   )
   public static final Flow concatWith(Flow var0, Flow var1) {
      return FlowKt__MigrationKt.concatWith(var0, var1);
   }

   public static final Flow conflate(Flow var0) {
      return FlowKt__ContextKt.conflate(var0);
   }

   public static final Flow consumeAsFlow(ReceiveChannel var0) {
      return FlowKt__ChannelsKt.consumeAsFlow(var0);
   }

   public static final Object count(Flow var0, Continuation var1) {
      return FlowKt__CountKt.count(var0, var1);
   }

   public static final Object count(Flow var0, Function2 var1, Continuation var2) {
      return FlowKt__CountKt.count(var0, var1, var2);
   }

   public static final Flow debounce(Flow var0, long var1) {
      return FlowKt__DelayKt.debounce(var0, var1);
   }

   public static final Flow debounce(Flow var0, Function1 var1) {
      return FlowKt__DelayKt.debounce(var0, var1);
   }

   public static final Flow debounce_8GFy2Ro(Flow var0, double var1) {
      return FlowKt__DelayKt.debounce_8GFy2Ro(var0, var1);
   }

   public static final Flow debounceDuration(Flow var0, Function1 var1) {
      return FlowKt__DelayKt.debounceDuration(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.WARNING,
      message = "Use 'onEach { delay(timeMillis) }'",
      replaceWith = @ReplaceWith(
   expression = "onEach { delay(timeMillis) }",
   imports = {}
)
   )
   public static final Flow delayEach(Flow var0, long var1) {
      return FlowKt__MigrationKt.delayEach(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.WARNING,
      message = "Use 'onStart { delay(timeMillis) }'",
      replaceWith = @ReplaceWith(
   expression = "onStart { delay(timeMillis) }",
   imports = {}
)
   )
   public static final Flow delayFlow(Flow var0, long var1) {
      return FlowKt__MigrationKt.delayFlow(var0, var1);
   }

   public static final Flow distinctUntilChanged(Flow var0) {
      return FlowKt__DistinctKt.distinctUntilChanged(var0);
   }

   public static final Flow distinctUntilChanged(Flow var0, Function2 var1) {
      return FlowKt__DistinctKt.distinctUntilChanged(var0, var1);
   }

   public static final Flow distinctUntilChangedBy(Flow var0, Function1 var1) {
      return FlowKt__DistinctKt.distinctUntilChangedBy(var0, var1);
   }

   public static final Flow drop(Flow var0, int var1) {
      return FlowKt__LimitKt.drop(var0, var1);
   }

   public static final Flow dropWhile(Flow var0, Function2 var1) {
      return FlowKt__LimitKt.dropWhile(var0, var1);
   }

   public static final Object emitAll(FlowCollector var0, ReceiveChannel var1, Continuation var2) {
      return FlowKt__ChannelsKt.emitAll(var0, var1, var2);
   }

   public static final Object emitAll(FlowCollector var0, Flow var1, Continuation var2) {
      return FlowKt__CollectKt.emitAll(var0, var1, var2);
   }

   private static final Object emitAll$$forInline(FlowCollector var0, Flow var1, Continuation var2) {
      return FlowKt__CollectKt.emitAll(var0, var1, var2);
   }

   public static final Flow emptyFlow() {
      return FlowKt__BuildersKt.emptyFlow();
   }

   public static final Flow filter(Flow var0, Function2 var1) {
      return FlowKt__TransformKt.filter(var0, var1);
   }

   // $FF: synthetic method
   public static final Flow filterIsInstance(Flow var0) {
      return FlowKt__TransformKt.filterIsInstance(var0);
   }

   public static final Flow filterNot(Flow var0, Function2 var1) {
      return FlowKt__TransformKt.filterNot(var0, var1);
   }

   public static final Flow filterNotNull(Flow var0) {
      return FlowKt__TransformKt.filterNotNull(var0);
   }

   public static final Object first(Flow var0, Continuation var1) {
      return FlowKt__ReduceKt.first(var0, var1);
   }

   public static final Object first(Flow var0, Function2 var1, Continuation var2) {
      return FlowKt__ReduceKt.first(var0, var1, var2);
   }

   public static final Object firstOrNull(Flow var0, Continuation var1) {
      return FlowKt__ReduceKt.firstOrNull(var0, var1);
   }

   public static final Object firstOrNull(Flow var0, Function2 var1, Continuation var2) {
      return FlowKt__ReduceKt.firstOrNull(var0, var1, var2);
   }

   public static final ReceiveChannel fixedPeriodTicker(CoroutineScope var0, long var1, long var3) {
      return FlowKt__DelayKt.fixedPeriodTicker(var0, var1, var3);
   }

   // $FF: synthetic method
   public static ReceiveChannel fixedPeriodTicker$default(CoroutineScope var0, long var1, long var3, int var5, Object var6) {
      return FlowKt__DelayKt.fixedPeriodTicker$default(var0, var1, var3, var5, var6);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue is 'flatMapConcat'",
      replaceWith = @ReplaceWith(
   expression = "flatMapConcat(mapper)",
   imports = {}
)
   )
   public static final Flow flatMap(Flow var0, Function2 var1) {
      return FlowKt__MigrationKt.flatMap(var0, var1);
   }

   public static final Flow flatMapConcat(Flow var0, Function2 var1) {
      return FlowKt__MergeKt.flatMapConcat(var0, var1);
   }

   public static final Flow flatMapLatest(Flow var0, Function2 var1) {
      return FlowKt__MergeKt.flatMapLatest(var0, var1);
   }

   public static final Flow flatMapMerge(Flow var0, int var1, Function2 var2) {
      return FlowKt__MergeKt.flatMapMerge(var0, var1, var2);
   }

   // $FF: synthetic method
   public static Flow flatMapMerge$default(Flow var0, int var1, Function2 var2, int var3, Object var4) {
      return FlowKt__MergeKt.flatMapMerge$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'flatten' is 'flattenConcat'",
      replaceWith = @ReplaceWith(
   expression = "flattenConcat()",
   imports = {}
)
   )
   public static final Flow flatten(Flow var0) {
      return FlowKt__MigrationKt.flatten(var0);
   }

   public static final Flow flattenConcat(Flow var0) {
      return FlowKt__MergeKt.flattenConcat(var0);
   }

   public static final Flow flattenMerge(Flow var0, int var1) {
      return FlowKt__MergeKt.flattenMerge(var0, var1);
   }

   // $FF: synthetic method
   public static Flow flattenMerge$default(Flow var0, int var1, int var2, Object var3) {
      return FlowKt__MergeKt.flattenMerge$default(var0, var1, var2, var3);
   }

   public static final Flow flow(Function2 var0) {
      return FlowKt__BuildersKt.flow(var0);
   }

   public static final Flow flowCombine(Flow var0, Flow var1, Function3 var2) {
      return FlowKt__ZipKt.flowCombine(var0, var1, var2);
   }

   public static final Flow flowCombineTransform(Flow var0, Flow var1, Function4 var2) {
      return FlowKt__ZipKt.flowCombineTransform(var0, var1, var2);
   }

   public static final Flow flowOf(Object var0) {
      return FlowKt__BuildersKt.flowOf(var0);
   }

   public static final Flow flowOf(Object... var0) {
      return FlowKt__BuildersKt.flowOf(var0);
   }

   public static final Flow flowOn(Flow var0, CoroutineContext var1) {
      return FlowKt__ContextKt.flowOn(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use channelFlow with awaitClose { } instead of flowViaChannel and invokeOnClose { }."
   )
   public static final Flow flowViaChannel(int var0, Function2 var1) {
      return FlowKt__BuildersKt.flowViaChannel(var0, var1);
   }

   // $FF: synthetic method
   public static Flow flowViaChannel$default(int var0, Function2 var1, int var2, Object var3) {
      return FlowKt__BuildersKt.flowViaChannel$default(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "flowWith is deprecated without replacement, please refer to its KDoc for an explanation"
   )
   public static final Flow flowWith(Flow var0, CoroutineContext var1, int var2, Function1 var3) {
      return FlowKt__ContextKt.flowWith(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static Flow flowWith$default(Flow var0, CoroutineContext var1, int var2, Function1 var3, int var4, Object var5) {
      return FlowKt__ContextKt.flowWith$default(var0, var1, var2, var3, var4, var5);
   }

   public static final Object fold(Flow var0, Object var1, Function3 var2, Continuation var3) {
      return FlowKt__ReduceKt.fold(var0, var1, var2, var3);
   }

   private static final Object fold$$forInline(Flow var0, Object var1, Function3 var2, Continuation var3) {
      return FlowKt__ReduceKt.fold(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'forEach' is 'collect'",
      replaceWith = @ReplaceWith(
   expression = "collect(block)",
   imports = {}
)
   )
   public static final void forEach(Flow var0, Function2 var1) {
      FlowKt__MigrationKt.forEach(var0, var1);
   }

   public static final int getDEFAULT_CONCURRENCY() {
      return FlowKt__MergeKt.getDEFAULT_CONCURRENCY();
   }

   // $FF: synthetic method
   public static void getDEFAULT_CONCURRENCY_PROPERTY_NAME$annotations() {
   }

   public static final Job launchIn(Flow var0, CoroutineScope var1) {
      return FlowKt__CollectKt.launchIn(var0, var1);
   }

   public static final Flow map(Flow var0, Function2 var1) {
      return FlowKt__TransformKt.map(var0, var1);
   }

   public static final Flow mapLatest(Flow var0, Function2 var1) {
      return FlowKt__MergeKt.mapLatest(var0, var1);
   }

   public static final Flow mapNotNull(Flow var0, Function2 var1) {
      return FlowKt__TransformKt.mapNotNull(var0, var1);
   }

   public static final Flow merge(Iterable var0) {
      return FlowKt__MergeKt.merge(var0);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'merge' is 'flattenConcat'",
      replaceWith = @ReplaceWith(
   expression = "flattenConcat()",
   imports = {}
)
   )
   public static final Flow merge(Flow var0) {
      return FlowKt__MigrationKt.merge(var0);
   }

   public static final Flow merge(Flow... var0) {
      return FlowKt__MergeKt.merge(var0);
   }

   public static final Void noImpl() {
      return FlowKt__MigrationKt.noImpl();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Collect flow in the desired context instead"
   )
   public static final Flow observeOn(Flow var0, CoroutineContext var1) {
      return FlowKt__MigrationKt.observeOn(var0, var1);
   }

   public static final Flow onCompletion(Flow var0, Function3 var1) {
      return FlowKt__EmittersKt.onCompletion(var0, var1);
   }

   public static final Flow onEach(Flow var0, Function2 var1) {
      return FlowKt__TransformKt.onEach(var0, var1);
   }

   public static final Flow onEmpty(Flow var0, Function2 var1) {
      return FlowKt__EmittersKt.onEmpty(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use catch { e -> if (predicate(e)) emitAll(fallback) else throw e }",
      replaceWith = @ReplaceWith(
   expression = "catch { e -> if (predicate(e)) emitAll(fallback) else throw e }",
   imports = {}
)
   )
   public static final Flow onErrorCollect(Flow var0, Flow var1, Function1 var2) {
      return FlowKt__ErrorsKt.onErrorCollect(var0, var1, var2);
   }

   // $FF: synthetic method
   public static Flow onErrorCollect$default(Flow var0, Flow var1, Function1 var2, int var3, Object var4) {
      return FlowKt__ErrorsKt.onErrorCollect$default(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emitAll(fallback) }'",
      replaceWith = @ReplaceWith(
   expression = "catch { emitAll(fallback) }",
   imports = {}
)
   )
   public static final Flow onErrorResume(Flow var0, Flow var1) {
      return FlowKt__MigrationKt.onErrorResume(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emitAll(fallback) }'",
      replaceWith = @ReplaceWith(
   expression = "catch { emitAll(fallback) }",
   imports = {}
)
   )
   public static final Flow onErrorResumeNext(Flow var0, Flow var1) {
      return FlowKt__MigrationKt.onErrorResumeNext(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emit(fallback) }'",
      replaceWith = @ReplaceWith(
   expression = "catch { emit(fallback) }",
   imports = {}
)
   )
   public static final Flow onErrorReturn(Flow var0, Object var1) {
      return FlowKt__MigrationKt.onErrorReturn(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { e -> if (predicate(e)) emit(fallback) else throw e }'",
      replaceWith = @ReplaceWith(
   expression = "catch { e -> if (predicate(e)) emit(fallback) else throw e }",
   imports = {}
)
   )
   public static final Flow onErrorReturn(Flow var0, Object var1, Function1 var2) {
      return FlowKt__MigrationKt.onErrorReturn(var0, var1, var2);
   }

   // $FF: synthetic method
   public static Flow onErrorReturn$default(Flow var0, Object var1, Function1 var2, int var3, Object var4) {
      return FlowKt__MigrationKt.onErrorReturn$default(var0, var1, var2, var3, var4);
   }

   public static final Flow onStart(Flow var0, Function2 var1) {
      return FlowKt__EmittersKt.onStart(var0, var1);
   }

   public static final SharedFlow onSubscription(SharedFlow var0, Function2 var1) {
      return FlowKt__ShareKt.onSubscription(var0, var1);
   }

   public static final ReceiveChannel produceIn(Flow var0, CoroutineScope var1) {
      return FlowKt__ChannelsKt.produceIn(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'publish()' is 'shareIn'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to 'started = SharingStared.Lazily' argument, \npublish().refCount() translates to 'started = SharingStared.WhileSubscribed()' argument.",
      replaceWith = @ReplaceWith(
   expression = "this.shareIn(scope, 0)",
   imports = {}
)
   )
   public static final Flow publish(Flow var0) {
      return FlowKt__MigrationKt.publish(var0);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'publish(bufferSize)' is 'buffer' followed by 'shareIn'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to 'started = SharingStared.Lazily' argument, \npublish().refCount() translates to 'started = SharingStared.WhileSubscribed()' argument.",
      replaceWith = @ReplaceWith(
   expression = "this.buffer(bufferSize).shareIn(scope, 0)",
   imports = {}
)
   )
   public static final Flow publish(Flow var0, int var1) {
      return FlowKt__MigrationKt.publish(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Collect flow in the desired context instead"
   )
   public static final Flow publishOn(Flow var0, CoroutineContext var1) {
      return FlowKt__MigrationKt.publishOn(var0, var1);
   }

   public static final Flow receiveAsFlow(ReceiveChannel var0) {
      return FlowKt__ChannelsKt.receiveAsFlow(var0);
   }

   public static final Object reduce(Flow var0, Function3 var1, Continuation var2) {
      return FlowKt__ReduceKt.reduce(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'replay()' is 'shareIn' with unlimited replay. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to 'started = SharingStared.Lazily' argument, \nreplay().refCount() translates to 'started = SharingStared.WhileSubscribed()' argument.",
      replaceWith = @ReplaceWith(
   expression = "this.shareIn(scope, Int.MAX_VALUE)",
   imports = {}
)
   )
   public static final Flow replay(Flow var0) {
      return FlowKt__MigrationKt.replay(var0);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'replay(bufferSize)' is 'shareIn' with the specified replay parameter. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to 'started = SharingStared.Lazily' argument, \nreplay().refCount() translates to 'started = SharingStared.WhileSubscribed()' argument.",
      replaceWith = @ReplaceWith(
   expression = "this.shareIn(scope, bufferSize)",
   imports = {}
)
   )
   public static final Flow replay(Flow var0, int var1) {
      return FlowKt__MigrationKt.replay(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "binary compatibility with retries: Int preview version"
   )
   public static final Flow retry(Flow var0, int var1, Function1 var2) {
      return FlowKt__ErrorsKt.retry(var0, var1, var2);
   }

   public static final Flow retry(Flow var0, long var1, Function2 var3) {
      return FlowKt__ErrorsKt.retry(var0, var1, var3);
   }

   // $FF: synthetic method
   public static Flow retry$default(Flow var0, int var1, Function1 var2, int var3, Object var4) {
      return FlowKt__ErrorsKt.retry$default(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static Flow retry$default(Flow var0, long var1, Function2 var3, int var4, Object var5) {
      return FlowKt__ErrorsKt.retry$default(var0, var1, var3, var4, var5);
   }

   public static final Flow retryWhen(Flow var0, Function4 var1) {
      return FlowKt__ErrorsKt.retryWhen(var0, var1);
   }

   public static final Flow runningReduce(Flow var0, Function3 var1) {
      return FlowKt__TransformKt.runningReduce(var0, var1);
   }

   public static final Flow sample(Flow var0, long var1) {
      return FlowKt__DelayKt.sample(var0, var1);
   }

   public static final Flow sample_8GFy2Ro(Flow var0, double var1) {
      return FlowKt__DelayKt.sample_8GFy2Ro(var0, var1);
   }

   public static final Flow scan(Flow var0, Object var1, Function3 var2) {
      return FlowKt__TransformKt.scan(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow has less verbose 'scan' shortcut",
      replaceWith = @ReplaceWith(
   expression = "scan(initial, operation)",
   imports = {}
)
   )
   public static final Flow scanFold(Flow var0, Object var1, Function3 var2) {
      return FlowKt__MigrationKt.scanFold(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.WARNING,
      message = "'scanReduce' was renamed to 'runningReduce' to be consistent with Kotlin standard library",
      replaceWith = @ReplaceWith(
   expression = "runningReduce(operation)",
   imports = {}
)
   )
   public static final Flow scanReduce(Flow var0, Function3 var1) {
      return FlowKt__MigrationKt.scanReduce(var0, var1);
   }

   public static final SharedFlow shareIn(Flow var0, CoroutineScope var1, SharingStarted var2, int var3) {
      return FlowKt__ShareKt.shareIn(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static SharedFlow shareIn$default(Flow var0, CoroutineScope var1, SharingStarted var2, int var3, int var4, Object var5) {
      return FlowKt__ShareKt.shareIn$default(var0, var1, var2, var3, var4, var5);
   }

   public static final Object single(Flow var0, Continuation var1) {
      return FlowKt__ReduceKt.single(var0, var1);
   }

   public static final Object singleOrNull(Flow var0, Continuation var1) {
      return FlowKt__ReduceKt.singleOrNull(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'skip' is 'drop'",
      replaceWith = @ReplaceWith(
   expression = "drop(count)",
   imports = {}
)
   )
   public static final Flow skip(Flow var0, int var1) {
      return FlowKt__MigrationKt.skip(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'startWith' is 'onStart'. Use 'onStart { emit(value) }'",
      replaceWith = @ReplaceWith(
   expression = "onStart { emit(value) }",
   imports = {}
)
   )
   public static final Flow startWith(Flow var0, Object var1) {
      return FlowKt__MigrationKt.startWith(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'startWith' is 'onStart'. Use 'onStart { emitAll(other) }'",
      replaceWith = @ReplaceWith(
   expression = "onStart { emitAll(other) }",
   imports = {}
)
   )
   public static final Flow startWith(Flow var0, Flow var1) {
      return FlowKt__MigrationKt.startWith(var0, var1);
   }

   public static final Object stateIn(Flow var0, CoroutineScope var1, Continuation var2) {
      return FlowKt__ShareKt.stateIn(var0, var1, var2);
   }

   public static final StateFlow stateIn(Flow var0, CoroutineScope var1, SharingStarted var2, Object var3) {
      return FlowKt__ShareKt.stateIn(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead"
   )
   public static final void subscribe(Flow var0) {
      FlowKt__MigrationKt.subscribe(var0);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead"
   )
   public static final void subscribe(Flow var0, Function2 var1) {
      FlowKt__MigrationKt.subscribe(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead"
   )
   public static final void subscribe(Flow var0, Function2 var1, Function2 var2) {
      FlowKt__MigrationKt.subscribe(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use 'flowOn' instead"
   )
   public static final Flow subscribeOn(Flow var0, CoroutineContext var1) {
      return FlowKt__MigrationKt.subscribeOn(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogues of 'switchMap' are 'transformLatest', 'flatMapLatest' and 'mapLatest'",
      replaceWith = @ReplaceWith(
   expression = "this.flatMapLatest(transform)",
   imports = {}
)
   )
   public static final Flow switchMap(Flow var0, Function2 var1) {
      return FlowKt__MigrationKt.switchMap(var0, var1);
   }

   public static final Flow take(Flow var0, int var1) {
      return FlowKt__LimitKt.take(var0, var1);
   }

   public static final Flow takeWhile(Flow var0, Function2 var1) {
      return FlowKt__LimitKt.takeWhile(var0, var1);
   }

   public static final Object toCollection(Flow var0, Collection var1, Continuation var2) {
      return FlowKt__CollectionKt.toCollection(var0, var1, var2);
   }

   public static final Object toList(Flow var0, List var1, Continuation var2) {
      return FlowKt__CollectionKt.toList(var0, var1, var2);
   }

   // $FF: synthetic method
   public static Object toList$default(Flow var0, List var1, Continuation var2, int var3, Object var4) {
      return FlowKt__CollectionKt.toList$default(var0, var1, var2, var3, var4);
   }

   public static final Object toSet(Flow var0, Set var1, Continuation var2) {
      return FlowKt__CollectionKt.toSet(var0, var1, var2);
   }

   // $FF: synthetic method
   public static Object toSet$default(Flow var0, Set var1, Continuation var2, int var3, Object var4) {
      return FlowKt__CollectionKt.toSet$default(var0, var1, var2, var3, var4);
   }

   public static final Flow transform(Flow var0, Function3 var1) {
      return FlowKt__EmittersKt.transform(var0, var1);
   }

   public static final Flow transformLatest(Flow var0, Function3 var1) {
      return FlowKt__MergeKt.transformLatest(var0, var1);
   }

   public static final Flow transformWhile(Flow var0, Function3 var1) {
      return FlowKt__LimitKt.transformWhile(var0, var1);
   }

   public static final Flow unsafeTransform(Flow var0, Function3 var1) {
      return FlowKt__EmittersKt.unsafeTransform(var0, var1);
   }

   public static final Flow withIndex(Flow var0) {
      return FlowKt__TransformKt.withIndex(var0);
   }

   public static final Flow zip(Flow var0, Flow var1, Function3 var2) {
      return FlowKt__ZipKt.zip(var0, var1, var2);
   }
}
