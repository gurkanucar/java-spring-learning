package org.gucardev.awssesmailsending.dto;

public record EmailRequest(String to, String subject, String body) {
}
