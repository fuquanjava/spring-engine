package session.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * spring-demo 2015/7/22 21:01
 * fuquanemail@gmail.com
 */
public class CacheHttpSession implements HttpSession ,Serializable {

    protected long creationTime = 0L;
    protected String id;
    protected int maxInactiveInterval;
    protected long lastAccessedTime = 0L;
    public transient boolean expired = false;
    public transient boolean isNew = false;
    public transient boolean isDirty = false;
    // 读取不加锁，写入加锁
    private Map<String, Object> data = new ConcurrentHashMap();


    @Override
    public long getCreationTime() {
        return this.creationTime;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        this.maxInactiveInterval = interval;

    }

    @Override
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getAttribute(String name) {
        return this.data.get(name);
    }

    @Override
    public Object getValue(String name) {
        return this.data.get(name);
    }

    @Override
    public Enumeration getAttributeNames() {
        final Iterator iterator = this.data.keySet().iterator();
        return new Enumeration() {
            public boolean hasMoreElements() {
                return iterator.hasNext();
            }

            public Object nextElement() {
                return iterator.next();
            }
        };
    }

    @Override
    public String[] getValueNames() {
        String[] names = new String[this.data.size()];
        return this.data.keySet().toArray(names);
    }

    @Override
    public void setAttribute(String name, Object value) {
        if(value != null && name != null) {
            this.data.put(name, value);
            this.isDirty = true;
        }
    }

    @Override
    public void putValue(String name, Object value) {
        this.setAttribute(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        this.data.remove(name);
        this.isDirty =false;
    }

    @Override
    public void removeValue(String name) {
        this.removeAttribute(name);
    }

    @Override
    public void invalidate() {
        this.expired = true;
        this.isDirty = true;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }
}
