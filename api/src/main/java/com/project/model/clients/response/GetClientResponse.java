package com.project.model.clients.response;

import com.project.model.clients.model.User;
import com.project.model.clients.response.status.GetClientResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetClientResponse {

    private GetClientResponseStatus status;
    private User client;

}
