package com.longder.gov.entity.enumeration;

/**
 * 审批状态枚举
 */
public enum ApproveState {
    WAITING("WAITING","待审批"),
    APPROVING("APPROVING","审批中"),
    FINISHED("FINISHED","已完成");


    ApproveState(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    /**
     * 名称
     */
    private String name;
    /**
     * 展示名
     */
    private String displayName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
