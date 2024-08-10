package org.gucardev.awssesmailsending.dto;

public record HtmlEmailRequest(String subject, String to, String name, String templateName) {
}
