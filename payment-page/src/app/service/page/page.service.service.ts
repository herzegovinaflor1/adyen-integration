import {Injectable} from '@angular/core';
import {PageServiceGqlService} from './gql/page.service.gql.service';

@Injectable({
  providedIn: 'root'
})
export class PageServiceService {

  constructor(private pageServiceGqlService: PageServiceGqlService) {
  }

  pages = (): any => {
    return this.pageServiceGqlService.fetch();
  }
}
