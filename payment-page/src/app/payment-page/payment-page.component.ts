import {Component, OnInit} from '@angular/core';
import {Apollo} from 'apollo-angular';
import {Router} from '@angular/router';
import {PaymentService} from '../service/payment/payment.service';
import {StoredPaymentDetail} from '../../types/types';
import * as uuid from 'uuid';

declare var adyen: any;

@Component({
  selector: 'app-payment-page',
  templateUrl: './payment-page.component.html',
  styleUrls: ['./payment-page.component.css']
})
export class PaymentPageComponent implements OnInit {

  private static VISA_REGEX = new RegExp('^4[0-9]{12}(?:[0-9]{3})?$');
  private static MASTER_REGEX = new RegExp('^(5018|5555|2222|5020|5038|6304|6759|6761|6763)[0-9]{8,15}$');

  cardNumber: string;
  holderName: string;
  expMonth: string;
  expYear: string;
  cvc: string;
  storeCurrentCard: boolean;
  storedPayments: StoredPaymentDetail[] = [];
  selectedStoredPayment: StoredPaymentDetail;
  disabledCard: 'visa' | 'master';

  constructor(
    private apollo: Apollo,
    private paymentService: PaymentService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.paymentService.getStoredPayments().subscribe(el => {
      this.storedPayments = el.data.storedPayments;
    });
  }

  set selectedStoredPaymentsMod(value) {
    const payment = this.storedPayments[value];
    const card = payment.card;

    this.selectedStoredPayment = payment;
    this.cardNumber = '**** **** **** ' + card.number;
    this.expMonth = card.expiryMonth;
    this.expYear = card.expiryYear;
    this.holderName = card.holderName;
    this.storeCurrentCard = false;
  }

  removeCard = () => {
    this.paymentService.disableStoredPayment({
      disableRecurring: {
        recurringDetailReference: this.selectedStoredPayment.recurringDetailReference,
        uuid: uuid.v4(),
      }
    }).subscribe(() => {
      window.location.reload();
    });
  }

  inputNumberValidation = (event: KeyboardEvent) => {
    if (this.selectedStoredPayment !== null) {
      this.selectedStoredPayment = null;
      this.cardNumber = '';
    }
    const num = event.key;
    if (!((num >= '0' && num <= '9') || event.key === 'Backspace')) {
      event.preventDefault();
    }
  }

  inputHolderValidation = (event: KeyboardEvent) => {
    if (this.selectedStoredPayment !== null) {
      this.selectedStoredPayment = null;
      this.cardNumber = '';
    }
    const num = event.key;
    if (!((num >= '0' && num <= '9') || event.key === 'Backspace')) {
      event.preventDefault();
    }
  }

  validateCard = () => {
    const processedCardNumber = this.cardNumber.replace(/ /g, '');
    const isVisa = PaymentPageComponent.VISA_REGEX.test(processedCardNumber);
    const isMaster = PaymentPageComponent.MASTER_REGEX.test(processedCardNumber);
    if (isVisa) {
      this.disabledCard = 'master';
    } else if (isMaster) {
      this.disabledCard = 'visa';
    } else {
      this.disabledCard = null;
    }
    if (isVisa || isMaster) {
      this.cardNumber = processedCardNumber.replace(/[^\dA-Z]/g, '').replace(/(.{4})/g, '$1 ').trim();
    }
  }

  validateHolderName = () => {
    return this.holderName !== null || this.holderName !== '' || this.holderName.length < 256;
  }

  payOrder = () => {
    if (this.isCardValid()) {
      if (this.selectedStoredPayment !== null && this.selectedStoredPayment !== undefined) {
        this.authorizedStored();
      } else {
        this.paymentService.authorize({
          authorize: {
            value: '10',
            currency: 'PLN',
            cardEncryptedJson: this.getEncryptedFormData(),
            stored: this.storeCurrentCard,
            uuid: uuid.v4()
          }
        }).subscribe(el => {
          const {
            resultCode
          } = el.data.authorize;
          if (resultCode === 'RedirectShopper') {
            const {
              md,
              paRequest,
              issuerUrl,
            } = el.data.authorize;
            this.router.navigate(['/auth3ds', issuerUrl, md, paRequest, 'http://localhost:8080/checkout/authorize3d']);
          }
          if (resultCode === 'Authorised') {
            this.router.navigate(['/success']);
          }
        }, () => {
          this.router.navigate(['/failed']);
        });
      }
    }
  }

  private authorizedStored = (): void => {
    this.paymentService.authorizeStored({
      authorize: {
        value: '10',
        currency: 'PLN',
        cvc: this.cvc,
        uuid: uuid.v4(),
        selectedRecurringDetailReference: this.selectedStoredPayment.recurringDetailReference
      }
    }).subscribe(el => {
      const {
        resultCode
      } = el.data.authorizeStored;
      if (resultCode === 'RedirectShopper') {
        const {
          md,
          paRequest,
          issuerUrl,
        } = el.data.authorizeStored;
        this.router.navigate(['/auth3ds', issuerUrl, md, paRequest, 'http://localhost:8080/checkout/authorize3d']);
      }
      if (resultCode === 'Authorised') {
        this.router.navigate(['/success']);
      }
    }, () => {
    });
  }

  private isNumberValid = () => {
    if (this.selectedStoredPayment === null) {
      return true;
    }
    if (this.cardNumber) {
      const processedCardNumber = this.cardNumber.replace(/ /g, '');
      const isVisa = PaymentPageComponent.VISA_REGEX.test(processedCardNumber);
      const isMaster = PaymentPageComponent.MASTER_REGEX.test(processedCardNumber);
      if (isVisa) {
        this.disabledCard = 'master';
      } else if (isMaster) {
        this.disabledCard = 'visa';
      } else {
        this.disabledCard = null;
      }

      return isVisa || isMaster;
    }
    return false;
  }

  private isHolderNameValid = () => {
    return this.holderName && this.holderName.length < 256;
  }

  private isExpirationValid = () => {
    try {
      const month = Number.parseInt(this.expMonth, 10);
      const year = Number.parseInt(this.expYear, 10);

      const isMonthValid = month > 0 && month < 13;
      const isYearValid = year > 0 && year < 9999;

      return isMonthValid && isYearValid;
    } catch (ignore) {
      return false;
    }
  }

  private isCVCValid = () => {
    try {
      Number.parseInt(this.cvc, 10);
      return this.cvc.length === 3;
    } catch (ignore) {
      return false;
    }
  }

  private isCardValid = () => {
    if (this.selectedStoredPayment){
      return true;
    }
    return this.isNumberValid()
      && this.isHolderNameValid()
      && this.isExpirationValid()
      && this.isCVCValid();
  }

  private getEncryptedFormData = (): any => {
    const key = '10001|A825054AE1EAEA97E05EB419E3C9FB98E56ACD9D2FEFBF9B30209CE14346D27B99A94474026CAA587C9F9663410BC4DE26105156AA9F077C933EA988656A74CE9DF03A4232866022F41179988E1BAB54900E6892C403E18E5AF01EF8DC172ECFD0978F5DDC7C0BDBD4221D9D1E46F033E70408B90C3C177856BA1EE8F0A3F787800752D784CD74B7A09F1657772E5BB65BD142E7932D4E96BC93C4E621B6DF225F6260B1A48D24180FDDA9CD96F297B9D3C9589F1E3E50CD27CAEFB4C71B0C02FD0C8B1B14BC0AE3C68E7BA5D5003E157F6AB7E3384237C6D9529E19A94443E7C72271502486C1BDB33807FF4ACE5FFDE9DB5B6327BC6906BBBF843C33796217';

    const cseInstance = adyen.encrypt.createEncryption(key, {});

    return cseInstance.encrypt({
      number: this.cardNumber.replace(/ /g, ''),
      cvc: this.cvc,
      holderName: this.holderName,
      expiryMonth: this.expMonth,
      expiryYear: '20' + this.expYear,
      generationtime: new Date().toISOString()
    });
  }

}
