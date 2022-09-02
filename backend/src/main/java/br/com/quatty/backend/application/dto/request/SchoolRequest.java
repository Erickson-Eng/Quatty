package br.com.quatty.backend.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolRequest {

    @NotBlank(message = "Need to enter a name for school")
    private String name;
    private String biography;
    private String telephone;

    private Long addressId;

}
