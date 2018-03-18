import 'rxjs/add/operator/map';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import {Http, RequestOptions, Response} from '@angular/http';

import {SPEAKERS_ENDPOINT, SERVER_URL} from '../config';

@Injectable()
export class Speakers {

    constructor(private http: Http) {
    }

    getSpeakers(): Observable<Response>{
        //Make call out to Speakers endpoint and get JSON back.
        console.log("Make a call out to get the categories");

        let requestOptions = new RequestOptions();

        console.log("Making a call out to "+SERVER_URL+SPEAKERS_ENDPOINT);
        return this.http.get(SERVER_URL+SPEAKERS_ENDPOINT,requestOptions);
    }

}
