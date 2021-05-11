package com.payment.adyen.controller;

import com.payment.adyen.facade.PaymentFacade;
import com.payment.adyen.model.payment.adyen.request.Authorization3DRequest;
import com.payment.adyen.model.payment.dto.AuthorizationResult;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RestController
public final class Authorize3DSController {

    private static final String REDIRECT_PAGE = "REDIRECT_PAGE";
    private static final String DEFAULT_REDIRECT_PAGE = "http://localhost:4200";

    private final PaymentFacade paymentFacade;
    private final ConversionService conversionService;

    public Authorize3DSController(PaymentFacade paymentFacade, ConversionService conversionService) {
        this.paymentFacade = paymentFacade;
        this.conversionService = conversionService;
    }

    @PostMapping("/checkout/authorize3d")
    public void authorize3d(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var authorization3DRequest = conversionService.convert(request, Authorization3DRequest.class);
        AuthorizationResult authorizationResult = paymentFacade.authorize3d(authorization3DRequest);
        // TODO: Save 3d result
        redirectToMainPage(response);
    }

    private void redirectToMainPage(HttpServletResponse response) throws IOException {
        var redirectPage = Objects.requireNonNullElse(System.getenv(REDIRECT_PAGE), DEFAULT_REDIRECT_PAGE);
        response.sendRedirect(redirectPage);
    }

}
