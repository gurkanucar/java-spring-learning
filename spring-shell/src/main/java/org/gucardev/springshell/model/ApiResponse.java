package org.gucardev.springshell.model;

import java.util.List;


public record ApiResponse<T>(List<T> result) {}