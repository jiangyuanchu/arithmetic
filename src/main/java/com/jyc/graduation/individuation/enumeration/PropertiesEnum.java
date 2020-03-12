package com.jyc.graduation.individuation.enumeration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PropertiesEnum {
    n("01", "普通名词"),
    f("02", "方位名词"),
    s("03", "处所名词"),
    w("03", "标点符号"),  //no table
    t("05", "时间名词"),
    nr("06", "人名"),
    ns("07", "地名"),
    nt("08", "机构团体名"),
    nw("09", "作品名"),
    nz("10", "其他专名"),
    v("11", "普通动词"),
    vd("12", "动副词"),
    vn("13", "名动词"),
    a("14", "形容词"),
    ad("15", "副形词"),
    an("16", "名形词"),
    d("17", "副词"),    //no table
    m("18", "数量词"),    //no table
    q("19", "量词"),    //no table
    r("20", "代词"),    //no table
    p("21", "介词"),    //no table
    c("22", "连词"),    //no table
    u("23", "助词"),    //no table
    xc("24", "其他虚词"),    //no table
    PER("T01", "人名"),
    LOC("T03", "地名"),
    ORG("T02", "机构名"),
    TIME("T04", "时间"),
    ;

    private String code;
    private String displayName;
}
