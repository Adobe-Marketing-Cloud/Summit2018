import { NgModule } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { IonicPageModule } from 'ionic-angular';

import { SMEOverviewPage } from './sme-overview';

@NgModule({
    declarations: [
        SMEOverviewPage,
    ],
    imports: [
        IonicPageModule.forChild(SMEOverviewPage),
        TranslateModule.forChild()
    ],
    exports: [
        SMEOverviewPage
    ]
})
export class SMEOverviewPageModule { }
