package com.hostel.exception;

import java.time.LocalDateTime;
import java.net.URI;
import java.util.Objects;


import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private String getBackUrl(HttpServletRequest request) {

	    String contextPath = request.getContextPath();

	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    if (authentication != null && authentication.isAuthenticated()) {

	        boolean isAdmin = authentication.getAuthorities().stream()
	                .anyMatch(authority ->
	                        authority.getAuthority().equals("ROLE_ADMIN"));

	        boolean isStudent = authentication.getAuthorities().stream()
	                .anyMatch(authority ->
	                        authority.getAuthority().equals("ROLE_STUDENT"));

	        if (isAdmin) {
	            return contextPath + "/admin/dashboard";
	        }

	        if (isStudent) {
	            return contextPath + "/student/dashboard";
	        }
	    }

	    return contextPath + "/login";
	}

	    // Handle resource not found
	    @ExceptionHandler(ResourceNotFoundException.class)
	    public String handleNotFound(
	            ResourceNotFoundException ex,
	            HttpServletRequest request,
	            HttpServletResponse response,
	            Model model) {

	        response.setStatus(HttpStatus.NOT_FOUND.value());

	        ErrorResponse error = new ErrorResponse(
	                LocalDateTime.now(),
	                404,
	                "NOT FOUND",
	                ex.getMessage(),
	                request.getRequestURI()
	        );

	        model.addAttribute("error", error);
	        model.addAttribute("backUrl", getBackUrl(request));

	        return "error-response";
	    }

	    // Handle database constraint violations
	    @ExceptionHandler(DataIntegrityViolationException.class)
	    public String handleDataIntegrityViolation(
	            DataIntegrityViolationException ex,
	            HttpServletRequest request,
	            HttpServletResponse response,
	            Model model) {

	        response.setStatus(HttpStatus.CONFLICT.value());

	        ErrorResponse error = new ErrorResponse(
	                LocalDateTime.now(),
	                409,
	                "CONFLICT",
	                "Unable to process request because of a database constraint violation.",
	                request.getRequestURI()
	        );

	        model.addAttribute("error", error);
	        model.addAttribute("backUrl", getBackUrl(request));

	        return "error-response";
	    }

	    // Handle database-related exceptions
	    @ExceptionHandler(DataAccessException.class)
	    public String handleDatabaseException(
	            DataAccessException ex,
	            HttpServletRequest request,
	            HttpServletResponse response,
	            Model model) {

	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

	        ErrorResponse error = new ErrorResponse(
	                LocalDateTime.now(),
	                500,
	                "DATABASE ERROR",
	                "Database error occurred. Please try again later.",
	                request.getRequestURI()
	        );

	        model.addAttribute("error", error);
	        model.addAttribute("backUrl", getBackUrl(request));

	        return "error-response";
	    }

	    // Handle invalid arguments
	    @ExceptionHandler(IllegalArgumentException.class)
	    public String handleInvalidArgument(
	            IllegalArgumentException ex,
	            HttpServletRequest request,
	            HttpServletResponse response,
	            Model model) {

	        response.setStatus(HttpStatus.BAD_REQUEST.value());

	        ErrorResponse error = new ErrorResponse(
	                LocalDateTime.now(),
	                400,
	                "BAD REQUEST",
	                ex.getMessage(),
	                request.getRequestURI()
	        );

	        model.addAttribute("error", error);
	        model.addAttribute("backUrl", getBackUrl(request));

	        return "error-response";
	    }

	    // Handle all other unexpected exceptions
	    @ExceptionHandler(Exception.class)
	    public String handleGlobalException(
	            Exception ex,
	            HttpServletRequest request,
	            HttpServletResponse response,
	            Model model) {

	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

	        ErrorResponse error = new ErrorResponse(
	                LocalDateTime.now(),
	                500,
	                "INTERNAL SERVER ERROR",
	                "Something went wrong. Please try again later.",
	                request.getRequestURI()
	        );

	        model.addAttribute("error", error);
	        model.addAttribute("backUrl", getBackUrl(request));

	        return "error-response";
	    }

}
