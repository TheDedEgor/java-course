package edu.project3;

import java.time.LocalDate;

public record CommandParameters(String path, LocalDate from, LocalDate to, String format) {
}
