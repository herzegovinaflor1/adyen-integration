import {Component, OnInit} from '@angular/core';
import {PaymentInfo} from '../../types/types';
import {PaymentService} from '../service/payment/payment.service';
import {PageServiceService} from '../service/page/page.service.service';
import {ActivatedRoute} from '@angular/router';
import * as uuid from 'uuid';

@Component({
  selector: 'app-payment-status-page',
  templateUrl: './payment-status-page.component.html',
  styleUrls: ['./payment-status-page.component.css']
})
export class PaymentStatusPageComponent implements OnInit {

  payments: PaymentInfo[] = [];
  numOfPages: number[] = [];

  constructor(private paymentService: PaymentService,
              private pageServiceService: PageServiceService,
              private route: ActivatedRoute ) {
    pageServiceService.pages().subscribe(res => {
      const pages = res.data.pages;
      // @ts-ignore
      this.numOfPages = Array(pages).fill().map((x, i) => i + 1);
    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const currentPage = params.page - 1;
      // TODO: add validation for currentPage
      this.paymentService.getPaymentsInfo(currentPage)
        .subscribe(res => {
          this.payments = res.data.paymentsInfo;
        });
    });
  }

  capture(index: number): void {
    const payment = this.payments[index];
    this.paymentService.capture({
      capture: {
        paymentId: payment.paymentId,
        originalReference: payment.pspReference[payment.pspReference.length - 1],
        modificationAmount: {
          value: payment.value,
          currency: payment.currency
        },
        uuid: uuid.v4()
      }
    })
      .subscribe(() => window.location.reload());
  }

  cancel(index: number): void {
    const payment = this.payments[index];
    this.paymentService.cancel({
      cancel: {
        paymentId: payment.paymentId,
        originalReference: payment.pspReference[payment.pspReference.length - 1],
        uuid: uuid.v4()
      }
    })
      .subscribe(() => window.location.reload());
  }

  refund(index: number): void {
    const payment = this.payments[index];
    this.paymentService.refund({
      refund: {
        paymentId: payment.paymentId,
        originalReference: payment.pspReference[0],
        uuid: uuid.v4()
      }
    })
      .subscribe(() => window.location.reload());
  }

}
