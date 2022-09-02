package br.com.quatty.backend.application.dto.response.table;

import br.com.quatty.backend.application.dto.response.MembershipResponse;
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
@JsonRootName("Membership")
public class MembershipTableResponse {

    @JsonProperty("membershipList")
    private List<MembershipResponse> membershipResponseList;
}
