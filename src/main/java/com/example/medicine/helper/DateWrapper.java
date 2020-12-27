package com.example.medicine.helper;

import java.time.LocalDateTime;

public class DateWrapper {
    private LocalDateTime dateTime;

    public DateWrapper() {
    }

    public DateWrapper(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
