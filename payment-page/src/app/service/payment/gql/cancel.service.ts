import { Injectable } from '@angular/core';
import {gql, Mutation} from 'apollo-angular';
import {CancelRequestInput, CancelResponse} from '../../../../types/types';

@Injectable({
  providedIn: 'root'
})
export class CancelService extends Mutation<CancelResponse, CancelRequestInput> {

  document = gql`
    mutation Cancel($cancel: CancelRequestInput!) {
      cancel(cancelRequest: $cancel){
        pspReference
        response
      }
    }
  `;
}
