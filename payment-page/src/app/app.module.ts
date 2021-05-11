import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CartPageComponent} from './cart-page/cart-page.component';
import {PaymentPageComponent} from './payment-page/payment-page.component';
import {GraphQLModule} from './graphql.module';
import {HttpClientModule} from '@angular/common/http';
import {PaymentService} from './service/payment/payment.service';
import {AuthorizationService} from './service/payment/gql/authorization.service';
import {PaymentStatusPageComponent} from './payment-status-page/payment-status-page.component';
import {PaymentsListService} from './service/payment/gql/payments.list.service';
import {CaptureService} from './service/payment/gql/capture.service';
import {CapitalizePipe} from './pipe/capitalize.pipe';
import { Auth3dsFormComponent } from './auth3ds-form/auth3ds-form.component';
import { SuccessfulPaymentComponent } from './successful-payment/successful-payment.component';
import { FailedPaymentComponent } from './failed-payment/failed-payment.component';

@NgModule({
  declarations: [
    AppComponent,
    CartPageComponent,
    PaymentPageComponent,
    PaymentStatusPageComponent,
    CapitalizePipe,
    Auth3dsFormComponent,
    SuccessfulPaymentComponent,
    FailedPaymentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    GraphQLModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    PaymentService,
    AuthorizationService,
    CaptureService,
    PaymentsListService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
