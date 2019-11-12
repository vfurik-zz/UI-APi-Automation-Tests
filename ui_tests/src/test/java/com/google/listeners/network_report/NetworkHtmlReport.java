package com.google.listeners.network_report;

import com.test.data.utils.HtmlTable;
import net.lightbody.bmp.core.har.HarEntry;

import java.util.List;

public class NetworkHtmlReport {

    public NetworkHtmlReport() {
        this.htmlTable = new HtmlTable();
    }

    private HtmlTable htmlTable;

    public String generateTable(List<HarEntry> harEntries) {
        StringBuilder html = new StringBuilder();
        html.append("<html>\n<head>\n<style type=\"text/css\">\n");
        html.append(String.format("table, th, td {\nfont-family: %s;\nfont-size: %s;\ncolor: %s;\nborder: %s;\nborder-collapse: %s;\n}\n",
                htmlTable.fontFamily(), htmlTable.fontSize(), htmlTable.fontColor(), htmlTable.border(), htmlTable.borderCollapse()
        ));
        html.append(String.format("th, td {\npadding: %s;\n}\n", htmlTable.thTdPadding()));
        html.append("</style>\n</head>\n");
        html.append("<body>\n<table>\n<tbody>\n");
        html.append("<tr>\n");
        html.append("<th>Request</th>\n<th>Status</th>\n<th>Timestamp</th>\n");
        html.append("</tr>\n");

        for (HarEntry logEntry : harEntries) {
            html.append("<tr>\n");
            html.append(String.format("<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n", logEntry.getRequest().getUrl(), logEntry.getResponse().getStatus(), logEntry.getStartedDateTime()));
            html.append("</tr>\n");
        }

        html.append("</tbody>\n</table>\n</body>");
        html.append("</html>");

        return html.toString();
    }

}
