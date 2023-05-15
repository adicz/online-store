package com.project.model.clients.response;

import lombok.*;
import com.project.model.clients.model.User;
import com.project.model.clients.response.status.GetClientResponseStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetClientResponse {

    private GetClientResponseStatus status;
    private User client;

}
