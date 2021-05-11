import { Injectable } from '@angular/core';
import {gql, Query} from 'apollo-angular';

@Injectable({
  providedIn: 'root'
})
export class StoredPaymentsService extends Query {

  document = gql`
    query {
      storedPayments {
        paymentMethodVariant
        recurringDetailReference
        card {
          expiryMonth
          expiryYear
          holderName
          number
        }
      }
    }
  `;
}
