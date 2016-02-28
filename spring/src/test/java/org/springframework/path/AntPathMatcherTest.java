package org.springframework.path;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class AntPathMatcherTest {
    
    private PathMatcher pathMatcher = new AntPathMatcher();
    
    @Test
    public void testQuestionMark() {
        /*“?”：匹配一个字符*/
        Assert.assertTrue(pathMatcher.match("config?.xml", "config1.xml"));
        Assert.assertFalse(pathMatcher.match("config?.xml", "config12.xml"));
        Assert.assertFalse(pathMatcher.match("config?.xml", "config.xml"));
    }
    
    @Test
    public void testOneAsterisk() {
        /*“*”：匹配零个或多个字符串*/
        Assert.assertTrue(pathMatcher.match("config-*.xml", "config-dao.xml"));
        Assert.assertTrue(pathMatcher.match("config-*.xml", "config-.xml"));
        Assert.assertTrue(pathMatcher.match("config-**.xml", "config-dao.xml"));

        Assert.assertFalse(pathMatcher.match("config-*.xml", "config-1/.xml"));
        Assert.assertFalse(pathMatcher.match("config-*.xml", "config-1/2.xml"));

        Assert.assertTrue(pathMatcher.match("/cn/*/config.xml", "/cn/javass/config.xml"));
        
        Assert.assertFalse(pathMatcher.match("/cn/*/config.xml", "/cn/config.xml"));
        Assert.assertFalse(pathMatcher.match("/cn/*/config.xml", "/cn//config.xml"));
        Assert.assertFalse(pathMatcher.match("/cn/*/config.xml", "/cn/javass/spring/config.xml"));
        
    }

    @Test
    public void testTwoAsterisk() {

        /*“**”：匹配路径中的零个或多个目录*/
        Assert.assertTrue(pathMatcher.match("/cn/**/config-*.xml", "/cn/javass/config-dao.xml"));
        Assert.assertTrue(pathMatcher.match("/cn/**/config-*.xml", "/cn/javass/spring/config-dao.xml"));
        Assert.assertTrue(pathMatcher.match("/cn/**/config-*.xml", "/cn/config-dao.xml"));

    }
}
