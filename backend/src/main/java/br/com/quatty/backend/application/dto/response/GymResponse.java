package br.com.quatty.backend.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymResponse {

    private String name;
    private String information;
    @JsonProperty("Practicable_Sports")
    private Set<PracticableResponse> practicableResponses;

    @JsonProperty("School")
    private SchoolResponse schoolResponse;
}
