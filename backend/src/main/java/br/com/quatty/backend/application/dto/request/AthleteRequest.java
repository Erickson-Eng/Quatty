package br.com.quatty.backend.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AthleteRequest {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String socialName;
    @NotBlank
    private String birthDate;
    @NotNull
    private Long addressId;
    @NotNull
    private Double weight;
    @NotNull
    private Double height;
    private Double bicepsMeasurement;
    private Double forearmMeasurement;
    private Double chestMeasurement;
    private Double thighMeasurement;
    private Double calfMeasurement;
}
