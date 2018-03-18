import { NgModule } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { IonicPageModule } from 'ionic-angular';

import { SMEDetailPage } from './sme-detail';

@NgModule({
    declarations: [
        SMEDetailPage,
    ],
    imports: [
        IonicPageModule.forChild(SMEDetailPage),
        TranslateModule.forChild()
    ],
    exports: [
        SMEDetailPage
    ]
})
export class SMEDetailPageModule { }
