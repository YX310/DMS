package com.gxm.dts.model.domain;

public class Base {
    public String compareResult(String title, String source, String target) {
        String res = "";
        if(source == null || target == null) {
            if(source == null && target == null) return res;
            if(source == null && target != null) return "\n\n" + "填写了 " + title  + "\n" + target + "\n";
            if(source != null && target == null) return "\n\n" + "删除了 " + title  + "\n" + source + "\n";
        }
        if (!source.equals(target)) {
            res =  "\n\n" + title + "\n" + "由 " + source + " 变更为 " + target ;
        }
        return res;
    }
}
