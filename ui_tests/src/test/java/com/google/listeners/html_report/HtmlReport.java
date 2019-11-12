package com.google.listeners.html_report;

import com.codeborne.selenide.logevents.EventsCollector;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.test.data.utils.AllureUtils;
import com.test.data.utils.HtmlTable;

public class HtmlReport {

    public HtmlReport() {
        htmlTable = new HtmlTable();
    }

    private HtmlTable htmlTable;

    void start() {
        SelenideLogger.addListener(HtmlReport.class.getName(), new EventsCollector());
    }

    void finish() {
        EventsCollector logEventListener = SelenideLogger.removeListener(HtmlReport.class.getName());

        StringBuilder html = new StringBuilder();
        html.append("<html>\n<head>\n<style type=\"text/css\">\n");
        html.append(String.format("table, th, td {\nfont-family: %s;\nfont-size: %s;\ncolor: %s;\nborder: %s;\nborder-collapse: %s;\n}\n",
                htmlTable.fontFamily(), htmlTable.fontSize(), htmlTable.fontColor(), htmlTable.border(), htmlTable.borderCollapse()
        ));
        html.append(String.format("th, td {\npadding: %s;\n}\n", htmlTable.thTdPadding()));
        html.append("</style>\n</head>\n");
        html.append("<body>\n<table>\n<tbody>\n");
        html.append("<tr>\n");
        html.append("<th>Element</th>\n<th>Subject</th>\n<th>Status</th>\n<th>ms</th>\n");
        html.append("</tr>\n");

        for (LogEvent event : logEventListener.events()) {
            if (event.getStatus() == LogEvent.EventStatus.PASS && event.getDuration() < 2000L) {
                html.append("<tr>\n");
            } else if (event.getDuration() > 2000L && event.getStatus() == LogEvent.EventStatus.PASS) {
                html.append("<tr style=\"background:rgba(255, 145, 0, 0.5);\">\n");
            } else {
                html.append("<tr style=\"background:rgba(255, 0, 0, 0.7);\">\n");
            }
            html.append(String.format("<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n", event.getElement(), event.getSubject(), event.getStatus(), event.getDuration()));
            html.append("</tr>\n");
        }

        html.append("</tbody>\n</table>\n</body>");
        html.append("</html>");
        AllureUtils.attachLog(html.toString());
    }

    void clean() {
        SelenideLogger.removeListener(HtmlReport.class.getName());
    }

}


