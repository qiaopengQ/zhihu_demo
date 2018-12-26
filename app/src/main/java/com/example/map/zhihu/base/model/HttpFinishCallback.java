package com.example.map.zhihu.base.model;

public interface HttpFinishCallback {
    void setShowProgressbar();
    void setHideProgressbar();
    void setError(String error);
}
