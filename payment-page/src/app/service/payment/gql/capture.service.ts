import {Mutation} from 'apollo-angular';
import gql from 'graphql-tag';
import { Injectable } from '@angular/core';
import {CaptureRequestInput, CaptureResponse} from '../../../../types/types';

@Injectable({
  providedIn: 'root'
})
export class CaptureService extends Mutation<CaptureResponse, CaptureRequestInput> {

  document = gql`
    mutation Capture($capture: CaptureRequestInput!) {
      capture(captureRequest: $capture){
        pspReference
        response
      }
    }
  `;
}
