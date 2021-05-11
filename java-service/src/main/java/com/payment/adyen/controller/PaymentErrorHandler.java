package com.payment.adyen.controller;

import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * The handler for all error types. Will use it for logs
 */
@Component
public class PaymentErrorHandler implements GraphQLErrorHandler {

    // TODO: log all errors
    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        return errors;
    }

}
