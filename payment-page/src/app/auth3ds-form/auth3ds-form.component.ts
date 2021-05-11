import {AfterViewInit, Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {DOCUMENT} from '@angular/common';

@Component({
  selector: 'app-auth3ds-form',
  templateUrl: './auth3ds-form.component.html'
})
export class Auth3dsFormComponent implements OnInit, AfterViewInit {

  md: string;
  issuerUrl: string;
  paRequest: string;
  termUrl: string;

  constructor(private router: ActivatedRoute,
              @Inject(DOCUMENT) private document: Document) {
  }

  ngOnInit(): void {
    this.md = this.router.snapshot.params.md;
    this.issuerUrl = this.router.snapshot.params.issuerUrl;
    this.paRequest = this.router.snapshot.params.paRequest;
    this.termUrl = this.router.snapshot.params.termUrl;
  }

  ngAfterViewInit(): void {
    document.forms['3dform'].submit();
  }

}
