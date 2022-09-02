package br.com.quatty.backend.application.dto.response.table;

import br.com.quatty.backend.application.dto.response.AthleteResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("Athletes")
public class AthleteTableResponse {

    @JsonProperty("athleteList")
    private List<AthleteResponse> athleteResponses;
}
