package com.google.listeners.console_report;

import com.test.data.utils.AllureUtils;
import com.test.data.utils.HtmlTable;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

public class ConsoleLogHtml {

    public ConsoleLogHtml() {
        this.htmlTable = new HtmlTable();
    }

    private HtmlTable htmlTable;

    public void generateTable(List<LogEntry> logs) {
        StringBuilder html = new StringBuilder();
        html.append("<html>\n<head>\n<style type=\"text/css\">\n");
        html.append(String.format("table, th, td {\nfont-family: %s;\nfont-size: %s;\ncolor: %s;\nborder: %s;\nborder-collapse: %s;\n}\n",
                htmlTable.fontFamily(), htmlTable.fontSize(), htmlTable.fontColor(), htmlTable.border(), htmlTable.borderCollapse()
        ));
        html.append(String.format("th, td {\npadding: %s;\n}\n", htmlTable.thTdPadding()));
        html.append("</style>\n</head>\n");
        html.append("<body>\n<table>\n<tbody>\n");
        html.append("<tr>\n");
        html.append("<th>Level</th>\n<th>Message</th>\n<th>Timestamp</th>\n");
        html.append("</tr>\n");

        for (LogEntry logEntry : logs) {
            html.append("<tr>\n");
            html.append(String.format("<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n", logEntry.getLevel(), logEntry.getMessage(), logEntry.getTimestamp()));
            html.append("</tr>\n");
        }

        html.append("</tbody>\n</table>\n</body>");
        html.append("</html>");
        AllureUtils.attachConsoleLog(html.toString());
    }


}
