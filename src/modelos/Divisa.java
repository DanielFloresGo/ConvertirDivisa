package modelos;

public record Divisa(String time_last_update_utc,
                     String base_code,
                     Object conversion_rates) {
}
