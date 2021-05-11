import {
  AuthorizationRequestInput,
  CancelRequestInput,
  CaptureRequestInput,
  DisableStoredPaymentRequestInput,
  RefundRequestInput,
  StoredAuthorizationRequestInput
} from '../../../types/types';

export interface Payment {
  authorize(authorizationRequestInput: AuthorizationRequestInput): any;
  authorizeStored(storedAuthorizationRequestInput: StoredAuthorizationRequestInput): any;
  capture(captureRequestInput: CaptureRequestInput): any;
  cancel(cancelRequestInput: CancelRequestInput): any;
  refund(refundRequestInput: RefundRequestInput): any;
  disableStoredPayment(disableStoredPayment: DisableStoredPaymentRequestInput): any;
  getPaymentsInfo(pageNumber: number): any;
  getStoredPayments(): any;
}
