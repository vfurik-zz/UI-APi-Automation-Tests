package com.test.data.utils;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class HtmlTable {

    private String fontFamily = "Consalas";
    private String fontSize = "16px";
    private String fontColor = "#383737";
    private String border = "1px solid #383737";
    private String borderCollapse = "collapse";
    private String thTdPadding = "3px 10px 3px 10px";


}
