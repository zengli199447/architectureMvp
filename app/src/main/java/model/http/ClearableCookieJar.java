package model.http;

import okhttp3.CookieJar;

/**
 * Created by Administrator on 2017/10/30.
 */

public interface ClearableCookieJar extends CookieJar {

    /**
     * Clear all the session cookies while maintaining the persisted ones.
     */
    void clearSession();

    /**
     * Clear all the cookies from persistence and from the cache.
     */
    void clear();
}
