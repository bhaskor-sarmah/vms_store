package com.bohniman.vmsmaintenance.utilities;

import com.bohniman.vmsmaintenance.payload.UserDetailsPayload;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * LoggedInUser
 */
public class LoggedInUser {

    public static String getLoggedInUsername() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetailsPayload) {
            username = ((UserDetailsPayload) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;

    }

    public static UserDetailsPayload getLoggedInUser() {
        return (UserDetailsPayload) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}