package dev.sm1le.avaranusSMP.modules.webhook;

public enum WebhookType {

    REPORT("📣 REPORT", 0x3498db),
    SUGGEST("💡 SUGGEST", 0xf1c40f),

    AUTH_ALERT("🚨 AUTH ALERT", 0xe74c3c),
    AUTH_SUCCESS("✅ AUTH SUCCESS", 0x2ecc71),

    EXPLOIT_ALERT("⚠️ EXPLOIT", 0xe67e22),
    ANTIXRAY_ALERT("⛏️ ANTIXRAY", 0x9b59b6),

    SERVER_ALERT("🖥️ SERVER ALERT", 0x95a5a6);

    private final String title;
    private final int color;

    WebhookType(String title, int color) {
        this.title = title;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public int getColor() {
        return color;
    }
}