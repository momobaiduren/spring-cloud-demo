//package com.springcloud.common;
//
//import java.util.Iterator;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @author ZhangLong on 2019/11/1  6:29 下午
// * @version V1.0
// */
//public class Pubsub {
//    public static Pubsub Default = new Pubsub();
//    private Map<String, ConcurrentHashMap<String, Sub>> subscribeList = new ConcurrentHashMap();
//    private Object lock = new Object();
//
//    public Pubsub() {
//    }
//
//    public <T> void pub(String event, T data) {
//        ConcurrentHashMap<String, Pubsub.Sub> subs = (ConcurrentHashMap)this.subscribeList.get(event);
//        if (subs != null) {
//            Iterator var4 = subs.entrySet().iterator();
//
//            while(var4.hasNext()) {
//                Map.Entry sub = (Map.Entry)var4.next();
//
//                try {
//                    ((Pubsub.Sub)sub.getValue()).action.invoke(data);
//                } catch (Exception var7) {
//                    LogUtils.error(Pubsub.class, CoreProperties.Project, "分发订阅失败", var7);
//                }
//            }
//        }
//
//    }
//
//    private <T> void sub(String event, Pubsub.Sub<T> action) {
//        if (!this.subscribeList.containsKey(event)) {
//            synchronized(this.lock) {
//                if (!this.subscribeList.containsKey(event)) {
//                    this.subscribeList.putIfAbsent(event, new ConcurrentHashMap());
//                }
//            }
//        }
//
//        ((ConcurrentHashMap)this.subscribeList.get(event)).putIfAbsent(action.name, action);
//    }
//
//    public <T> void sub(BsfEventEnum event, Pubsub.Sub<T> action) {
//        this.sub(event.toString(), action);
//    }
//
//    public boolean removeSub(String event, String subName) {
//        ConcurrentHashMap<String, Pubsub.Sub> subs = (ConcurrentHashMap)this.subscribeList.get(event);
//        if (subs != null) {
//            subs.remove(subName);
//            if (subs.size() == 0) {
//                this.subscribeList.remove(event);
//            }
//
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public static class Sub<T> {
//        private String name;
//        private Action1<T> action;
//
//        public String getName() {
//            return this.name;
//        }
//
//        public Action1<T> getAction() {
//            return this.action;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public void setAction(Action1<T> action) {
//            this.action = action;
//        }
//
//        public boolean equals(Object o) {
//            if (o == this) {
//                return true;
//            } else if (!(o instanceof Pubsub.Sub)) {
//                return false;
//            } else {
//                Pubsub.Sub<?> other = (Pubsub.Sub)o;
//                if (!other.canEqual(this)) {
//                    return false;
//                } else {
//                    Object this$name = this.getName();
//                    Object other$name = other.getName();
//                    if (this$name == null) {
//                        if (other$name != null) {
//                            return false;
//                        }
//                    } else if (!this$name.equals(other$name)) {
//                        return false;
//                    }
//
//                    Object this$action = this.getAction();
//                    Object other$action = other.getAction();
//                    if (this$action == null) {
//                        if (other$action != null) {
//                            return false;
//                        }
//                    } else if (!this$action.equals(other$action)) {
//                        return false;
//                    }
//
//                    return true;
//                }
//            }
//        }
//
//        protected boolean canEqual(Object other) {
//            return other instanceof Pubsub.Sub;
//        }
//
//        public int hashCode() {
//            int PRIME = true;
//            int result = 1;
//            Object $name = this.getName();
//            int result = result * 59 + ($name == null ? 43 : $name.hashCode());
//            Object $action = this.getAction();
//            result = result * 59 + ($action == null ? 43 : $action.hashCode());
//            return result;
//        }
//
//        public String toString() {
//            return "Pubsub.Sub(name=" + this.getName() + ", action=" + this.getAction() + ")";
//        }
//
//        public Sub(String name, Action1<T> action) {
//            this.name = name;
//            this.action = action;
//        }
//    }
//}
//
