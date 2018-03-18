import { Component, ViewChild } from '@angular/core';
import { IonicPage, Nav, NavController } from 'ionic-angular';

@IonicPage()
@Component({
  selector: 'page-about',
  templateUrl: 'about.html'
})
export class AboutPage {
  // A reference to the ion-nav in our component
  @ViewChild(Nav) nav: Nav;

  rootPage: any = 'ContentPage';

  pages: Array<{ title: string, component: any }>;

  constructor(public navCtrl: NavController) {
    // used for an example of ngFor and navigation
    this.pages = [
      { title: 'Sign in', component: 'LoginPage' },
      { title: 'Signup', component: 'SignupPage' }
    ];
  }

  ionViewDidLoad() {
    console.log('Hello MenuPage Page');
  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.nav.setRoot(page.component);
  }
}
