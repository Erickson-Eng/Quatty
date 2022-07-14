package br.com.quatty.backend.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembershipResponse {

    private Long athleteId;
    private Long teamId;
    private String teamName;
    private String registrationStatus;
}
