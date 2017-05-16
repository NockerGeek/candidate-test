import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { UserProfile } from './user-profile';
import { Observable } from "rxjs/Rx";

@Injectable()
export class UserProfileService {

    private userProfileUrl = 'http://localhost:8090/users/1';  // URL to web api

    constructor(private _http: Http) {}

    post(url, data): Observable<any> {
        let username: string = 'rbarr';
        let password: string = 'password';
        let headers: Headers = new Headers();
        headers.append("Authorization", "Basic " + btoa(username + ":" + password));
        headers.append("Content-Type", "application/json");
        return this._http.post(url, data, {headers: headers})
    }

    call(url): Observable<any> {
        let username: string = 'rbarr';
        let password: string = 'password';
        let headers: Headers = new Headers();
        headers.append("Authorization", "Basic " + btoa(username + ":" + password));
        headers.append("Content-Type", "application/json");
        return this._http.get(url, {headers: headers})
    }

    getUserProfile(): Promise<UserProfile> {
        return this.call(this.userProfileUrl)
                        .toPromise()
                        .then(response => response.json() as UserProfile)
                        .catch(error => this.handleError(error));
    }

    update(userProfile: UserProfile): Promise<UserProfile> {
        return this.post(this.userProfileUrl, JSON.stringify(userProfile))
                        .toPromise()
                        .then(() => userProfile)
                        .catch(error => this.handleError(error));
    }

    private handleError(error: any): Promise<any> {
        console.log(error);
        return Promise.reject(error);
    }
}
