package bae.project.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid Factoid provided, " +
        "please refer to github to see correct form")
public class InvalidFactoidException extends IllegalArgumentException{
}
