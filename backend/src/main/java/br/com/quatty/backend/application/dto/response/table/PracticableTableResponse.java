package br.com.quatty.backend.application.dto.response.table;

import br.com.quatty.backend.application.dto.response.PracticableResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("Practicable")
public class PracticableTableResponse {

    @JsonProperty("PracticableList")
    private List<PracticableResponse> practicableResponseList;
}
