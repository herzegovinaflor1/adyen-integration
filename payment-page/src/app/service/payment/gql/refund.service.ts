import { Injectable } from '@angular/core';
import {gql, Mutation} from 'apollo-angular';
import {RefundResponse, RefundRequestInput} from '../../../../types/types';

@Injectable({
  providedIn: 'root'
})
export class RefundService extends Mutation<RefundResponse, RefundRequestInput> {

  document = gql`
    mutation Refund($refund: RefundRequestInput!) {
      refund(refundRequest: $refund){
        pspReference
        response
      }
    }
  `;
}
