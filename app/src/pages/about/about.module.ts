import { NgModule } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { IonicPageModule } from 'ionic-angular';

import { AboutPage } from './about';

@NgModule({
  declarations: [
    AboutPage,
  ],
  imports: [
    IonicPageModule.forChild(AboutPage),
    TranslateModule.forChild()
  ],
  exports: [
    AboutPage
  ]
})
export class AboutPageModule { }
