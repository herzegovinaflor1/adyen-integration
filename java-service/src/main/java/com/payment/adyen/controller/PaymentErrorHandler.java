package com.payment.adyen.controller;

import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The handler for all error types. Will use it for logs
 */
@Component
@Slf4j
public class PaymentErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        log.error("Error occurred: ", errors.toArray());
        return errors;
    }

}
