package com.wushaohong.rxjava2cache.data;

import java.io.Serializable;

/**
 * author : wushaohong
 * e-mail : 576218811@qq.com
 * date   : 2019/08/26
 * desc   : 模拟数据
 * version: 1.0
 */
public class Data implements Serializable {

    /**
     * 模拟数据
     */
    private String string;

    private String source;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
