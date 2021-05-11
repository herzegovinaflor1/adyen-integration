import { Injectable } from '@angular/core';
import {Mutation} from 'apollo-angular';
import gql from 'graphql-tag';
import {AuthorizationRequestInput, AuthorizationResponse} from '../../../../types/types';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService extends Mutation<AuthorizationResponse, AuthorizationRequestInput> {

  document = gql`
    mutation Authorization($authorize: AuthorizationRequestInput!) {
      authorize(authorizeRequest: $authorize){
        pspReference
        resultCode
        md
        paRequest
        issuerUrl
      }
    }
  `;
}
