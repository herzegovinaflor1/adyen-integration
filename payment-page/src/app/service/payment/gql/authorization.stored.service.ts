import {Injectable} from '@angular/core';
import {gql, Mutation} from 'apollo-angular';
import {AuthorizationResponse, StoredAuthorizationRequestInput} from '../../../../types/types';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationStoredService extends Mutation<AuthorizationResponse, StoredAuthorizationRequestInput> {

  document = gql`
    mutation StoredAuthorization($authorize: StoredAuthorizationRequestInput!) {
      authorizeStored(storedAuthorizeRequest: $authorize){
        pspReference
        resultCode
        md
        paRequest
        issuerUrl
      }
    }
  `;
}
