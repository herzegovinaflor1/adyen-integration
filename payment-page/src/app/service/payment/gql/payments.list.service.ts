import { Injectable } from '@angular/core';
import {gql, Query} from 'apollo-angular';

@Injectable({
  providedIn: 'root'
})
export class PaymentsListService extends Query {

  document = gql`
    query($pageNumber: Int) {
      paymentsInfo(pageNumber: $pageNumber) {
        paymentId
        authCode
        resultCode
        pspReference
        creationDate
        value
        currency
        operation
      }
    }
  `;
}
