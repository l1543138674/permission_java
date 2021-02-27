package com.acsk.demo.permission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 权限枚举
 */
public enum PermissionEnum {
    demo(PermissionGroupEnum.demoGroup, "demoPermissionCode", "demoPermissionName"),
    ;
    //权限组
    private PermissionGroupEnum group;
    //权限代码
    private String permissionCode;
    //权限名称
    private String permissionName;

    PermissionEnum() {
    }

    PermissionEnum(PermissionGroupEnum group, String permissionCode, String permissionName) {
        this.group = group;
        this.permissionCode = permissionCode;
        this.permissionName = permissionName;
    }

    public PermissionGroupEnum getGroup() {
        return group;
    }

    public void setGroup(PermissionGroupEnum group) {
        this.group = group;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String toString() {
        return "PermissionEnum{" +
                "group=" + group +
                ", permissionCode='" + permissionCode + '\'' +
                ", permissionName='" + permissionName + '\'' +
                '}';
    }

    // 根据group，获得该分组里的所有权限
    public static List<PermissionEnum> getPermissionsByGroup(PermissionGroupEnum groupEnum) {
        return getPermissionsByGroup(groupEnum.toString());
    }

    // 根据group，获得该分组里的所有权限
    public static List<PermissionEnum> getPermissionsByGroup(String group) {
        List<PermissionEnum> list = new ArrayList<>();
        for (PermissionEnum permissionEnum : PermissionEnum.values()) {
            if (permissionEnum.getGroup().equals(group)) {
                list.add(permissionEnum);
            }
        }
        return list;
    }

    // 传入权限枚举，判断当前会话user是否拥有该权限（isAllMatch：是否为全匹配）
    public static boolean hasPower(Boolean isAllMatch, PermissionEnum... permissionEnums) {
        //(1)超级管理员，直接返回true
        //(2)是否拥有全部权限
        if (null == isAllMatch) {
            return false;
        } else if (isAllMatch == true) {
            Stream<PermissionEnum> permissionEnumStream = Arrays.stream(permissionEnums);//将数组转换成流
            //获取当前用户拥有的全部权限代码集合
            List<String> permissionCodeList = null;

            /**
             * 获取当前用户拥有的全部代码功能----暂未开发
             */

            //方法allMatch(Predicate p)传入一个断言型函数，对流中所有的元素进行判断，如果都满足返回true
            boolean isAllMatchFlag = permissionEnumStream.allMatch(permissionEnum -> isBelongList(permissionEnum.getPermissionCode(), permissionCodeList));
            return isAllMatchFlag;
        } else {
            Stream<PermissionEnum> permissionEnumStream = Arrays.stream(permissionEnums);//将数组转换成流
            //获取当前用户拥有的全部权限代码集合
            List<String> permissionCodeList = null;

            //方法anyMatch(Predicate p)传入一个断言型函数，对流中所有的元素进行判断，如果有满足的返回true
            boolean isAnyMatchFlag = permissionEnumStream.anyMatch(permissionEnum -> isBelongList(permissionEnum.getPermissionCode(), permissionCodeList));
            return isAnyMatchFlag;

        }
    }


    // 判断对象是否存在集合中
    public static boolean isBelongList(String permissionCode, List<String> permissionCodeList) {
        if (permissionCode != null && permissionCodeList != null && permissionCodeList.size() > 0) {
            for (String p : permissionCodeList) {
                if (p.equals(permissionCode)) {
                    return true;
                }
            }
        }
        return false;
    }

}
