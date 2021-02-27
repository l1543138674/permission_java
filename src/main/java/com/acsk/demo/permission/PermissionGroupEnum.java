package com.acsk.demo.permission;

/**
 * 权限分组枚举
 */
public enum PermissionGroupEnum {
    demoGroup("demoGroup", "demoGroupName");
    //权限分组
    private String group;
    //权限分组名称
    private String groupName;

    PermissionGroupEnum() {
    }

    PermissionGroupEnum(String group, String groupName) {
        this.group = group;
        this.groupName = groupName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "PermissionGroupEnum{" +
                "group='" + group + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }


}
