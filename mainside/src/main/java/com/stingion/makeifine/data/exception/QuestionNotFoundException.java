/**
 * Created under not commercial project
 */
package com.stingion.makeifine.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Employee Not Found") //(not redirect to 404 err. page)
public class QuestionNotFoundException extends Exception {
    private static final long serialVersionUID = -3332292346834265371L;

    public QuestionNotFoundException() {
    }

    public QuestionNotFoundException(Integer id) {
        super("Question not found with id = " + id);
    }
}
