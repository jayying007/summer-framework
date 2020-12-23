package org.summer.framework.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ZhuangJieYing
 * @date 2020/12/22
 */
public class CodecUtilTest {
    @Test
    public void md5Test() {
        Assert.assertEquals(CodecUtil.md5("root"), "63a9f0ea7bb98050796b649e85481845");
        Assert.assertEquals(CodecUtil.md5("normal"), "fea087517c26fadd409bd4b9dc642555");
    }
}
