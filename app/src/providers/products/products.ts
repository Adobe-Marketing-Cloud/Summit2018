import 'rxjs/add/operator/map';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import {Http, RequestOptions, Response} from '@angular/http';

import {PRODUCT_CATEGORIES, SERVER_URL, SETTINGS_PRODUCT_DETAILS_SELECTOR} from '../config';

@Injectable()
export class Products {

    constructor(private http: Http) {
    }

    getCategories(): Observable<Response>{
        //Make call out to Products List endpoint and get JSON back. Store JSON.
        //If JSON available locally, check timestamp and use it, Else go and get a fresh list from endpoint
        console.log("Make a call out to get the categories");

        let requestOptions = new RequestOptions();

        console.log("Making a call out to "+SERVER_URL+PRODUCT_CATEGORIES);
        return this.http.get(SERVER_URL+PRODUCT_CATEGORIES,requestOptions);
    }

    getProductDetails(path: string): Observable<Response>{
        //Make call out to Product Details endpoint and get JSON back. Store JSON.
        //If JSON available locally, check timestamp and use it, Else go and get a fresh list from endpoint
        console.log("Make a call out to get the product details");
        let requestOptions = new RequestOptions();

        console.log("Making a call out to "+SERVER_URL+path+SETTINGS_PRODUCT_DETAILS_SELECTOR);
        return this.http.get(SERVER_URL+path+SETTINGS_PRODUCT_DETAILS_SELECTOR,requestOptions);
    }
}
