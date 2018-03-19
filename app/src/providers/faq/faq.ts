import 'rxjs/add/operator/map';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import {Http, RequestOptions, Response} from '@angular/http';

import {FAQ_ENDPOINT, SERVER_URL} from '../config';

@Injectable()
export class FAQ {

    constructor(private http: Http) {
    }

    getFAQs(): Observable<Response>{
        //Make call out to FAQ endpoint and get JSON back.
        console.log("Make a call out to get the categories");

        let requestOptions = new RequestOptions();

        console.log("Making a call out to "+SERVER_URL+FAQ_ENDPOINT);
        return this.http.get(SERVER_URL+FAQ_ENDPOINT,requestOptions);
    }

}
