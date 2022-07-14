package br.com.quatty.backend.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AthleteResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String socialName;
    private String birthDate;
    private Double weight;
    private Double height;
    private Double bicepsMeasurement;
    private Double forearmMeasurement;
    private Double chestMeasurement;
    private Double thighMeasurement;
    private Double calfMeasurement;

    @JsonProperty("Address")
    private AddressResponse addressResponse;
}
