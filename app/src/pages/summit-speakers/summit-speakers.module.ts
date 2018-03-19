import { NgModule } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { IonicPageModule } from 'ionic-angular';

import { SummitSpeakersPage } from './summit-speakers';

@NgModule({
    declarations: [
        SummitSpeakersPage,
    ],
    imports: [
        IonicPageModule.forChild(SummitSpeakersPage),
        TranslateModule.forChild()
    ],
    exports: [
        SummitSpeakersPage
    ]
})
export class SummitSpeakersPageModule { }
