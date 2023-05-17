package com.project.model.clients.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetClientRequest {

    private Long id;
    private String firstname;
    private String lastname;

}
