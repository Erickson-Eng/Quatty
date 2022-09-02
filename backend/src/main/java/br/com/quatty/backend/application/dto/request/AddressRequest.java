package br.com.quatty.backend.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @NotBlank(message = "The street field cannot be blank or null")
    private String street;
    private String complement;

    @NotBlank(message = "The zip code field cannot be blank or null")
    @Size(min = 9, max = 9)
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "it is necessary to follow the pattern 99999-999")
    private String zipCode;
    @NotBlank
    @Size(min = 2, max = 2, message = "The uf must contain 2 characters")
    private String uf;
    @NotBlank(message = "The city field cannot be blank or null")
    private String city;
}
