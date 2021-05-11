/* tslint:disable */

export type AuthorizationRequestInput = {
  authorize: {
    value: string
    currency: string
    cardEncryptedJson: string
    stored: boolean
    uuid: string
  }
};

export type StoredAuthorizationRequestInput = {
  authorize: {
    value: string
    currency: string
    cvc: string
    selectedRecurringDetailReference: string
    uuid: string
  }
};

export type DisableStoredPaymentRequestInput = {
  disableRecurring: {
    recurringDetailReference: string
    uuid: string
  }
};

export type CaptureRequestInput = {
  capture: {
    originalReference: string
    paymentId: number
    modificationAmount: {
      value: number
      currency: string
    },
    uuid: string
  }
};

export type CancelRequestInput = {
  cancel: {
    paymentId: number
    originalReference: string
    uuid: string
  }
};

export type RefundRequestInput = {
  refund: {
    paymentId: number
    originalReference: string
    uuid: string
  }
};

export type AuthorizationResponse = {
  pspReference: string
  resultCode: string
  authCode: string
  creationDate: string
  value: number
  currency: string
  operation: string
  md: string
  paRequest: string
  issuerUrl: string
};

export type CaptureResponse = {
  pspReference: string
  response: string
};

export type DisableStoredPaymentResponse = {
  response: string
};

export type CancelResponse = {
  pspReference: string
  response: string
};

export type RefundResponse = {
  pspReference: string
  response: string
};

export type PaymentInfo = {
  paymentId: number
  resultCode: string
  authCode: string
  value: number
  currency: string
  operation: string[]
  pspReference: string[]
  creationDate: string[]
};

export type Card = {
  expiryMonth: string
  expiryYear: string
  holderName: string
  number: string
}

export type StoredPaymentDetail = {
  paymentMethodVariant: string
  recurringDetailReference: string
  card: Card
}
