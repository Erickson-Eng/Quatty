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
public class SchoolResponse {

    private Long id;
    private String name;
    private String biography;
    private String telephone;

    @JsonProperty("Address")
    private AddressResponse addressResponse;
}
