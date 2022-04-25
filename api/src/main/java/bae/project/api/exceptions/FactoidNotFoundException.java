package bae.project.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A factoid with that ID does not exist")
public class FactoidNotFoundException extends EntityNotFoundException {
}
