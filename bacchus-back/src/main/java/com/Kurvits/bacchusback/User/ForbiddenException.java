package com.Kurvits.bacchusback.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="The bid has ended")
public class ForbiddenException extends RuntimeException {}