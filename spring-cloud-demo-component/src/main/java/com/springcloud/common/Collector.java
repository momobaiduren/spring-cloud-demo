//package com.springcloud.common;
//
//import com.springcloud.util.PropertyUtils;
//
//import java.lang.reflect.Method;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentSkipListSet;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.atomic.AtomicLong;
//
///**
// * @author ZhangLong on 2019/11/1  6:27 下午
// * @version V1.0
// */
//public class Collector {
//    public static Collector Default = new Collector();
//    private volatile Map<String, Object> map = new ConcurrentHashMap();
//    private Object lock = new Object();
//
//    public Collector() {
//    }
//
//    protected Object get(String key, Class type) {
//        if (!this.map.containsKey(key)) {
//            synchronized(this.lock) {
//                if (!this.map.containsKey(key)) {
//                    Object create = this.createFactory(key, type);
//                    this.map.put(key, create);
//                }
//            }
//        }
//
//        return this.map.get(key);
//    }
//
//    private Object createFactory(String key, Class type) {
//        try {
//            Object obj = type.newInstance();
//            if (type == Collector.Hook.class) {
//                Collector.Hook o = (Collector.Hook)obj;
//                o.setKey(key);
//                o.setMaxLength((Integer) PropertyUtils.getPropertyCache(key + ".length", 10));
//            }
//
//            return obj;
//        } catch (Exception var5) {
//            throw new BsfException(var5);
//        }
//    }
//
//    public Collector.Sum sum(String key) {
//        return (Collector.Sum)this.get(key, Collector.Sum.class);
//    }
//
//    public Collector.Hook hook(String key) {
//        return (Collector.Hook)this.get(key, Collector.Hook.class);
//    }
//
//    public Collector.Call call(String key) {
//        return (Collector.Call)this.get(key, Collector.Call.class);
//    }
//
//    public Collector.Value value(String key) {
//        return (Collector.Value)this.get(key, Collector.Value.class);
//    }
//
//    public static class SortList extends ConcurrentSkipListSet<SortInfo> {
//        protected Map tagCache = new ConcurrentHashMap();
//
//        public SortList() {
//        }
//
//        public boolean add(Collector.SortInfo sortInfo) {
//            Integer hash = sortInfo.tag.hashCode();
//            if (this.tagCache.containsKey(hash)) {
//                Object value = this.tagCache.get(hash);
//                if (value != null) {
//                    Collector.SortInfo sort = (Collector.SortInfo)value;
//                    sort.getCount().getAndIncrement();
//                    if (sort.time < sortInfo.time) {
//                        sort.maxTime = sortInfo.time;
//                    }
//                }
//
//                return false;
//            } else {
//                if (this.tagCache.size() > super.size()) {
//                    LogUtils.info(Collector.SortList.class, CoreProperties.Project, "tagCache 缓存存在溢出风险");
//                }
//
//                if (super.add(sortInfo)) {
//                    this.tagCache.put(hash, sortInfo);
//                }
//
//                return true;
//            }
//        }
//
//        public boolean remove(Object o) {
//            Integer hash = ((Collector.SortInfo)o).tag.hashCode();
//            this.tagCache.remove(hash);
//            return super.remove(o);
//        }
//
//        public Collector.SortInfo getLast() {
//            try {
//                if (!this.isEmpty()) {
//                    return (Collector.SortInfo)this.last();
//                }
//            } catch (NoSuchElementException var2) {
//            }
//
//            return null;
//        }
//
//        public void removeMore(int maxLength) {
//            int count = this.size();
//
//            while(this.size() > maxLength) {
//                --count;
//                Collector.SortInfo last = (Collector.SortInfo)this.pollLast();
//                if (last != null) {
//                    this.remove(last);
//                }
//
//                if (count < -10) {
//                    LogUtils.error(Collector.SortList.class, CoreProperties.Project, "【bsf严重bug】remove more,item:" + (last != null ? last.toString() : ""), new Exception("长时间无法移除导致死循环"));
//                    break;
//                }
//            }
//
//        }
//
//        public String toText() {
//            StringBuilder sb = new StringBuilder();
//            Iterator var2 = this.iterator();
//
//            while(var2.hasNext()) {
//                Collector.SortInfo o = (Collector.SortInfo)var2.next();
//                sb.append(String.format("[耗时ms]%s[tag]%s[次数]%s[最大耗时ms]%s\r\n", NumberUtils.scale(o.time, 2), o.tag.toString(), o.count, NumberUtils.scale(o.maxTime, 2)));
//            }
//
//            return sb.toString();
//        }
//    }
//
//    public static class SortInfo implements Comparable<Collector.SortInfo> {
//        protected Object tag;
//        protected double time;
//        protected double maxTime;
//        protected volatile AtomicLong count;
//
//        public int compareTo(Collector.SortInfo o) {
//            if (o.time > this.time) {
//                return 1;
//            } else {
//                return o.time < this.time ? -1 : 0;
//            }
//        }
//
//        public String toString() {
//            return this.tag.toString() + ":" + this.time;
//        }
//
//        public int hashCode() {
//            return this.tag.hashCode();
//        }
//
//        public boolean equals(Object obj) {
//            return obj.hashCode() == this.hashCode();
//        }
//
//        public SortInfo(Object tag, double time, double maxTime, AtomicLong count) {
//            this.tag = tag;
//            this.time = time;
//            this.maxTime = maxTime;
//            this.count = count;
//        }
//
//        public SortInfo() {
//        }
//
//        public Object getTag() {
//            return this.tag;
//        }
//
//        public double getTime() {
//            return this.time;
//        }
//
//        public double getMaxTime() {
//            return this.maxTime;
//        }
//
//        public AtomicLong getCount() {
//            return this.count;
//        }
//
//        public void setTag(Object tag) {
//            this.tag = tag;
//        }
//
//        public void setTime(double time) {
//            this.time = time;
//        }
//
//        public void setMaxTime(double maxTime) {
//            this.maxTime = maxTime;
//        }
//
//        public void setCount(AtomicLong count) {
//            this.count = count;
//        }
//    }
//
//    public static class Call {
//        private Func0<Object> func;
//
//        public Call() {
//        }
//
//        public void set(Func0<Object> func) {
//            this.func = func;
//        }
//
//        public Object run() {
//            return this.func.invoke();
//        }
//    }
//
//    public static class Hook {
//        protected AtomicLong current = new AtomicLong(0L);
//        protected AtomicLong lastErrorPerSecond = new AtomicLong(0L);
//        protected AtomicLong lastSuccessPerSecond = new AtomicLong(0L);
//        protected Collector.SortList sortList = new Collector.SortList();
//        protected double lastMinTimeSpan = 0.0D;
//        protected Collector.SortList sortListPerMinute = new Collector.SortList();
//        protected double lastMinTimeSpanPerMinute = 0.0D;
//        protected Integer maxLength = 10;
//        protected volatile Long lastSecond = 0L;
//        protected volatile Long lastMinute = 0L;
//        protected Method method;
//        private String key;
//
//        public Hook() {
//        }
//
//        public Long getCurrent() {
//            return this.current.get();
//        }
//
//        public Long getLastErrorPerSecond() {
//            return this.lastErrorPerSecond.get();
//        }
//
//        public Long getLastSuccessPerSecond() {
//            return this.lastSuccessPerSecond.get();
//        }
//
//        public Object run(String tag, Object obj, String methodName, Object[] params) {
//            if (this.method == null) {
//                Optional<Method> find = Arrays.stream(obj.getClass().getMethods()).filter((c) -> {
//                    return methodName.equalsIgnoreCase(c.getName());
//                }).findFirst();
//                if (!find.isPresent()) {
//                    throw new BsfException("未找到方法:" + obj.getClass().getName() + "下" + methodName);
//                }
//
//                this.method = (Method)find.get();
//            }
//
//            return this.run(tag, () -> {
//                try {
//                    return this.method.invoke(obj, params);
//                } catch (Exception var4) {
//                    throw new BsfException(var4);
//                }
//            });
//        }
//
//        public <T> T run(String tag, Func0<T> func) {
//            Object var10;
//            try {
//                if (!(Boolean)PropertyUtils.getPropertyCache(CoreProperties.BsfCollectHookEnabled, true)) {
//                    Object var16 = func.invoke();
//                    return var16;
//                }
//
//                this.current.getAndIncrement();
//                long second = System.currentTimeMillis() / 1000L;
//                if (second != this.lastSecond) {
//                    this.lastSecond = second;
//                    this.lastErrorPerSecond.set(0L);
//                    this.lastSuccessPerSecond.set(0L);
//                    if (this.lastSecond / 60L != this.lastMinute) {
//                        this.lastMinute = this.lastSecond / 60L;
//                        this.sortListPerMinute.removeMore(0);
//                    }
//                }
//
//                long start = System.currentTimeMillis();
//                T result = func.invoke();
//                long timeSpan = System.currentTimeMillis() - start;
//                this.insertOrUpdate(tag, (double)timeSpan);
//                this.insertOrUpdatePerMinute(tag, (double)timeSpan);
//                this.lastSuccessPerSecond.getAndIncrement();
//                var10 = result;
//            } catch (Exception var14) {
//                this.lastErrorPerSecond.getAndIncrement();
//                throw var14;
//            } finally {
//                this.current.getAndDecrement();
//            }
//
//            return var10;
//        }
//
//        protected void insertOrUpdate(Object info, double timeSpan) {
//            if (info != null && timeSpan >= this.lastMinTimeSpan) {
//                try {
//                    this.sortList.add(new Collector.SortInfo(info, timeSpan, timeSpan, new AtomicLong(1L)));
//                    this.sortList.removeMore(this.maxLength);
//                    Collector.SortInfo last = this.sortList.getLast();
//                    if (last != null) {
//                        this.lastMinTimeSpan = last.getTime();
//                    }
//                } catch (Exception var5) {
////                    LogUtils.error(Collector.Hook.class, CoreProperties.Project, "Collector hook 保存耗时统计出错", var5);
//                }
//
//            }
//        }
//
//        protected void insertOrUpdatePerMinute(Object info, double timeSpan) {
//            if (info != null && timeSpan >= this.lastMinTimeSpanPerMinute) {
//                try {
//                    this.sortListPerMinute.add(new Collector.SortInfo(info, timeSpan, timeSpan, new AtomicLong(1L)));
//                    this.sortListPerMinute.removeMore(this.maxLength);
//                    Collector.SortInfo last = this.sortListPerMinute.getLast();
//                    if (last != null) {
//                        this.lastMinTimeSpanPerMinute = last.getTime();
//                    }
//                } catch (Exception var5) {
////                    LogUtils.error(Collector.Hook.class, CoreProperties.Project, "Collector hook 保存耗时统计出错", var5);
//                }
//
//            }
//        }
//
//        public Collector.SortList getMaxTimeSpanList() {
//            return this.sortList;
//        }
//
//        public Collector.SortList getMaxTimeSpanListPerMinute() {
//            return this.sortListPerMinute;
//        }
//
//        public Integer getMaxLength() {
//            return this.maxLength;
//        }
//
//        public void setMaxLength(Integer maxLength) {
//            this.maxLength = maxLength;
//        }
//
//        public String getKey() {
//            return this.key;
//        }
//
//        public void setKey(String key) {
//            this.key = key;
//        }
//    }
//
//    public static class Sum {
//        protected AtomicInteger sum = new AtomicInteger(0);
//
//        public Sum() {
//        }
//
//        public void add(int count) {
//            this.sum.addAndGet(count);
//        }
//
//        public int get() {
//            return this.sum.get();
//        }
//
//        public void reset() {
//            this.sum.set(0);
//        }
//    }
//
//    public static class Value {
//        protected Object value;
//
//        public Value() {
//        }
//
//        public void set(Object value) {
//            this.value = value;
//        }
//
//        public Object get() {
//            return this.value;
//        }
//
//        public void reset() {
//            this.value = null;
//        }
//    }
//}
//
