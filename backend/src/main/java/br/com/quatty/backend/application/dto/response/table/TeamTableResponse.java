package br.com.quatty.backend.application.dto.response.table;

import br.com.quatty.backend.application.dto.response.TeamResponse;
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
@JsonRootName("Teams")
public class TeamTableResponse {

    private List<TeamResponse> teamResponseList;
}
