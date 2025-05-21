package matveyodintsov.weather2.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import matveyodintsov.weather2.dto.WeatherDto;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class WeatherAdapter {

    public WeatherDto fromJson(JsonNode node) {
        return WeatherDto.builder()
                .temp(getDouble(node, "main", "temp"))
                .feelsLike(getDouble(node, "main", "feels_like"))
                .tempMin(getDouble(node, "main", "temp_min"))
                .tempMax(getDouble(node, "main", "temp_max"))
                .pressure(getInt(node, "main", "pressure"))
                .humidity(getInt(node, "main", "humidity"))
                .seaLevel(getInt(node, "main", "sea_level"))
                .grndLevel(getInt(node, "main", "grnd_level"))
                .weatherMain(getText(node, "weather", 0, "main"))
                .weatherDescription(getText(node, "weather", 0, "description"))
                .weatherIcon(getText(node, "weather", 0, "icon"))
                .cloudiness(getInt(node, "clouds", "all"))
                .windSpeed(getDouble(node, "wind", "speed"))
                .windDeg(getInt(node, "wind", "deg"))
                .windGust(getDouble(node, "wind", "gust"))
                .visibility(getInt(node, "visibility"))
                .dt(parseDateTime(node, "dt"))
                .sunrise(parseDateTime(node, "sys", "sunrise"))
                .sunset(parseDateTime(node, "sys", "sunset"))
                .timezone(getInt(node, "timezone"))
                .build();
    }

    private String getText(JsonNode node, String parent, int index, String field) {
        if (node.has(parent) && node.get(parent).has(index) && node.get(parent).get(index).has(field)) {
            return node.get(parent).get(index).get(field).asText();
        }
        return "";
    }

    private double getDouble(JsonNode node, String parent, String field) {
        return node.has(parent) && node.get(parent).has(field) ? node.get(parent).get(field).asDouble() : 0.0;
    }

    private int getInt(JsonNode node, String field) {
        return node.has(field) ? node.get(field).asInt() : 0;
    }

    private int getInt(JsonNode node, String parent, String field) {
        return node.has(parent) && node.get(parent).has(field) ? node.get(parent).get(field).asInt() : 0;
    }

    private LocalDateTime parseDateTime(JsonNode node, String field) {
        return node.has(field) ?
                LocalDateTime.ofInstant(Instant.ofEpochSecond(node.get(field).asLong()), ZoneId.systemDefault()) :
                null;
    }

    private LocalDateTime parseDateTime(JsonNode node, String parent, String field) {
        return node.has(parent) && node.get(parent).has(field) ?
                LocalDateTime.ofInstant(Instant.ofEpochSecond(node.get(parent).get(field).asLong()), ZoneId.systemDefault()) :
                null;
    }
}