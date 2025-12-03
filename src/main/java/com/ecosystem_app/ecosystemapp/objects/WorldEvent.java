package com.ecosystem_app.ecosystemapp.objects;

import java.time.Instant;
import java.util.UUID;

public final class WorldEvent {
    private final EventType type;
    private final UUID subjectId; // opcjonalnie: id organizmu
    private final String message;
    private final int x;
    private final int y;
    private final Instant timestamp;

    public WorldEvent(EventType type, UUID subjectId, String message, int x, int y) {
        this.type = type;
        this.subjectId = subjectId;
        this.message = message;
        this.x = x;
        this.y = y;
        this.timestamp = Instant.now();
    }

    public EventType getType() { return type; }
    public UUID getSubjectId() { return subjectId; }
    public String getMessage() { return message; }
    public int getX() { return x; }
    public int getY() { return y; }
    public Instant getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("[%s] %s (id=%s) at (%d,%d) — %s",
                timestamp, type, subjectId, x, y, message);
    }
}
