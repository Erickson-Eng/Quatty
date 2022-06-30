package br.com.quatty.backend.application.dto.request.table;

import br.com.quatty.backend.application.dto.request.PracticableRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticableTableRequest {

    @JsonProperty("PracticableList")
    @NotBlank
    private List<PracticableRequest> practicableRequests;
}
