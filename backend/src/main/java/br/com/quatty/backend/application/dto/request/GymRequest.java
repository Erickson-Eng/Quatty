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
public class GymRequest {


    @NotBlank(message = "The gym must have a valid name")
    private String name;
    private String information;

    @NotNull
    private Long schoolId;
}
