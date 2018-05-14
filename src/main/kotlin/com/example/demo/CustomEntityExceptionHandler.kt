package com.example.demo

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
@RestController
class CustomEntityExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex:Exception, request:WebRequest): ResponseEntity<Any> {
        var exceptionResponse = ExceptionResponse(Date(), ex.message as String, request.getDescription(false));
        return ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleNotFoundExceptioins(ex:Exception, request:WebRequest):ResponseEntity<Any> {
        return ResponseEntity(
                ExceptionResponse(Date(), ex.message as String, request.getDescription(false)),
                HttpStatus.NOT_FOUND
        )
    }

    override
    fun handleMethodArgumentNotValid(ex:MethodArgumentNotValidException, headers:HttpHeaders, status:HttpStatus, request:WebRequest):ResponseEntity<Any> {
        return ResponseEntity(
                ExceptionResponse(Date(), "Validation Failed", ex.bindingResult.toString()),
                HttpStatus.BAD_REQUEST
        )
    }
}