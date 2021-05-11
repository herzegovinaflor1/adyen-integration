import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PaymentStatusPageComponent} from './payment-status-page/payment-status-page.component';
import {PaymentPageComponent} from './payment-page/payment-page.component';
import {CartPageComponent} from './cart-page/cart-page.component';
import {Auth3dsFormComponent} from './auth3ds-form/auth3ds-form.component';
import {SuccessfulPaymentComponent} from './successful-payment/successful-payment.component';
import {FailedPaymentComponent} from './failed-payment/failed-payment.component';

const routes: Routes = [
  {path: 'payments', component: PaymentStatusPageComponent},
  {path: 'cart', component: CartPageComponent},
  {path: 'checkout', component: PaymentPageComponent},
  {path: 'success', component: SuccessfulPaymentComponent},
  {path: 'failed', component: FailedPaymentComponent},
  {path: 'auth3ds/:issuerUrl/:md/:paRequest/:termUrl', component: Auth3dsFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
