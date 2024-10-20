package top.exfree.web.config.auth;

public class AuthInfoUtil {

    private final static ThreadLocal<GlobeData> threadLocal = new InheritableThreadLocal<>();

    public static GlobeData getGlobeData() {
        if (threadLocal.get() == null) {
            return new GlobeData();
        }
        return threadLocal.get();
    }

    public static void setGlobeData(final GlobeData globeData) {
        threadLocal.set(globeData);
    }

    public static void remove() {
        threadLocal.remove();
    }


}
