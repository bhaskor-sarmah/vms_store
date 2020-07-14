package com.bohniman.vmsmaintenance.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDataPayload {
    private String username;
    private String name;
    private String role;
}