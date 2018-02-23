import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';

import { Article } from './article';
import { MessageService } from './message.service';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ArticleService {

    private articlesUrl = 'http://localhost:4503/content/spa-demo/api/jcr:content/root/responsivegrid.model.json?nocache=true';
    
    constructor(
        private http: HttpClient,
        private messageService: MessageService
    ) { }
    
    /** GET article content fragments from the server */
    getArticles(): Observable<Article[]> {
        return new Observable<Article[]>(observer => {
            this.http.get<Article[]>(this.articlesUrl)
                .pipe(
                    tap(articles => this.log(`fetched articles`)),
                    catchError(this.handleError('getArticles', []))
                )
                .subscribe(response => {
                    observer.next(Object.entries(response[":items"])
                          // Ignore anything that isn't an article fragment
                          .filter(nvp=>nvp[1].model === "spa-demo/models/article") 
                          // Convert fragment to an article object      
                          .map(nvp=>this.parseFragment.apply(this, nvp)));
                    observer.complete();
                });
        });
    }
    
    parseFragment(name, fragment): Article {
        return {
            title: fragment.title,
            author: fragment.elements.author.value,
            description: fragment.description.value,
            path: name,
            publishDate: new Date(fragment.elements.published.value),
            tags: fragment.elements.keywords.value,
            body: fragment.elements.body.value
        };
    }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
    private handleError<T> (operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error); // log to console instead
            this.log(`${operation} failed: ${error.message}`);
            return of(result as T);
        };
    }

    private log(message: string) {
        this.messageService.add('ArticleListService: ' + message);
    }    
}