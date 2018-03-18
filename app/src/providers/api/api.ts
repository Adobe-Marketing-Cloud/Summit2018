import 'rxjs/add/operator/map';

import { Injectable } from '@angular/core';
import { Http, RequestOptions } from '@angular/http';
import { STUBS, STUBS_URL, SERVER_URL } from '../config'

/**
 * Api is a generic REST Api handler. Set your API url first.
 */
@Injectable()
export class Api {

    constructor(public http: Http) {
    }

    buildEndPoint(endpoint: string) {
        if (STUBS === true) {
            endpoint = STUBS_URL + endpoint + ".json";
            console.info("Stubs Built Endpoint: "+endpoint);
        } else {
            endpoint = SERVER_URL + endpoint;
            console.info("Server Built Endpoint: "+endpoint);
        }
        return endpoint;
    }

    get(endpoint: string, params?: any, options?: RequestOptions) {
        endpoint = this.buildEndPoint(endpoint);

        if (!options) {
            options = new RequestOptions();
        }
        // Support easy query params for GET requests
        // if (params) {
        //     let p = new URLSearchParams();
        //     for (let k in params) {
        //         p.set(k, params[k]);
        //     }
        //     // Set the search field if we have params and don't already have
        //     // a search field set in options.
        //     options.search = !options.search && p || options.search;
        // }

        return this.http.get(endpoint, options);
    }

    post(endpoint: string, body: any, options?: RequestOptions) {
        if (STUBS === true) {
            return this.get(endpoint, options);
        } else {
            endpoint = this.buildEndPoint(endpoint);
            return this.http.post(endpoint, body, options);
        }
    }

    put(endpoint: string, body: any, options?: RequestOptions) {
        endpoint = this.buildEndPoint(endpoint);
        return this.http.put(endpoint, body, options);
    }

    delete(endpoint: string, options?: RequestOptions) {
        endpoint = this.buildEndPoint(endpoint);
        return this.http.delete(endpoint, options);
    }

    patch(endpoint: string, body: any, options?: RequestOptions) {
        endpoint = this.buildEndPoint(endpoint);
        return this.http.put(endpoint, body, options);
    }
}
