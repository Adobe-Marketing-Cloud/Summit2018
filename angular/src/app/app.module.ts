import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ArticleListComponent } from './article-list/article-list.component';
import { ArticleDetailComponent } from './article-detail/article-detail.component';

import { ArticleService } from './article.service';
import { MessageService } from './message.service';

@NgModule({
    declarations: [
        AppComponent,
        ArticleListComponent,
        ArticleDetailComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule
    ],
    providers: [
        ArticleService,
        MessageService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
