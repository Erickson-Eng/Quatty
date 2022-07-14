package br.com.quatty.backend.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {

    private Long id;
    private String name;
    private LocalDate teamCreationDate;
}
