import {Injectable} from '@angular/core';
import {AuthorizationService} from './gql/authorization.service';
import {Payment} from './payment';
import {
  AuthorizationRequestInput,
  CancelRequestInput,
  CaptureRequestInput, DisableStoredPaymentRequestInput, RefundRequestInput,
  StoredAuthorizationRequestInput
} from '../../../types/types';
import {PaymentsListService} from './gql/payments.list.service';
import {CaptureService} from './gql/capture.service';
import {CancelService} from './gql/cancel.service';
import {StoredPaymentsService} from './gql/stored-payments.service';
import {AuthorizationStoredService} from './gql/authorization.stored.service';
import {RefundService} from './gql/refund.service';
import {DisableStoredPaymentService} from './gql/disable.stored.payment.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService implements Payment {

  constructor(private authorizationService: AuthorizationService,
              private storedAuthorizationService: AuthorizationStoredService,
              private captureService: CaptureService,
              private cancelService: CancelService,
              private refundService: RefundService,
              private storedPaymentsService: StoredPaymentsService,
              private disableStoredPaymentService: DisableStoredPaymentService,
              private paymentsListService: PaymentsListService) {
  }

  authorize(authorizationRequestInput: AuthorizationRequestInput): any {
    return this.authorizationService.mutate({
      ...authorizationRequestInput
    });
  }

  authorizeStored(storedAuthorizationRequestInput: StoredAuthorizationRequestInput): any {
    return this.storedAuthorizationService.mutate({
      ...storedAuthorizationRequestInput
    });
  }

  capture(captureRequestInput: CaptureRequestInput): any {
    return this.captureService.mutate({
      ...captureRequestInput
    });
  }

  cancel(cancelRequestInput: CancelRequestInput): any {
    return this.cancelService.mutate({
      ...cancelRequestInput
    });
  }

  refund(refundRequestInput: RefundRequestInput): any {
    return this.refundService.mutate({
      ...refundRequestInput
    });
  }

  getPaymentsInfo(pageNumber: number): any {
    return this.paymentsListService.fetch({
      pageNumber,
    });
  }

  getStoredPayments(): any {
    return this.storedPaymentsService.fetch();
  }

  disableStoredPayment(disableStoredPayment: DisableStoredPaymentRequestInput): any {
    return this.disableStoredPaymentService.mutate({
      ...disableStoredPayment
    });
  }

}
