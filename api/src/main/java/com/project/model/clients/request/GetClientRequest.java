package com.project.model.clients.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetClientRequest {

    private Long id;
    private String firstname;
    private String lastname;

}
