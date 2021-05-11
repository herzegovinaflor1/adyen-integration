import { Injectable } from '@angular/core';
import {gql, Query} from 'apollo-angular';

@Injectable({
  providedIn: 'root'
})
export class PageServiceGqlService extends Query {

  document = gql`
    query {
      pages
    }
  `;
}
