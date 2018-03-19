import { NgModule } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { IonicPageModule } from 'ionic-angular';

import { LandingPage } from './landing';

@NgModule({
    declarations: [
        LandingPage,
    ],
    imports: [
        IonicPageModule.forChild(LandingPage),
        TranslateModule.forChild()
    ],
    exports: [
        LandingPage
    ]
})
export class LandingPageModule { }
