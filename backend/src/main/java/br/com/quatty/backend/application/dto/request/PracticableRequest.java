package br.com.quatty.backend.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticableRequest {

    @NotBlank
    private String status;
    private String description;
    @NotNull
    private Long sportId;
    @NotNull
    private Long gymId;
}
