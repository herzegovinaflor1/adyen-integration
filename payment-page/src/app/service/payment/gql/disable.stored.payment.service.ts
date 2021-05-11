import { Injectable } from '@angular/core';
import {gql, Mutation} from 'apollo-angular';
import {
  DisableStoredPaymentRequestInput,
  DisableStoredPaymentResponse
} from '../../../../types/types';

@Injectable({
  providedIn: 'root'
})
export class DisableStoredPaymentService extends Mutation<DisableStoredPaymentResponse, DisableStoredPaymentRequestInput> {

  document = gql`
    mutation RemoveStoredPayment($disableRecurring: DisableRecurringRequestInput!) {
      removeStoredPayment(disableRecurringRequest: $disableRecurring){
        response
      }
    }
  `;
}
