package matveyodintsov.weather2.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import matveyodintsov.weather2.dto.LocationDto;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationDto fromJson(JsonNode node) {
        return LocationDto.builder()
                .name(getText(node, "name"))
                .country(getText(node, "country"))
                .state(getText(node, "state"))
                .lat(getDouble(node, "lat"))
                .lon(getDouble(node, "lon"))
                .build();
    }

    private String getText(JsonNode node, String field) {
        return node.has(field) ? node.get(field).asText() : "";
    }

    private double getDouble(JsonNode node, String field) {
        return node.has(field) ? node.get(field).asDouble() : 0.0;
    }

}
