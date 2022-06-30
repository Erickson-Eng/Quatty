package br.com.quatty.backend.application.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticableResponse {

    private Long id;
    private String status;
    private String description;
    @JsonProperty("Sport")
    private SportResponse sportResponse;
    @JsonProperty("Gym")
    private GymResponse gymResponse;
}
