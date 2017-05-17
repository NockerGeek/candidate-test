import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { UserProfile } from './user-profile';
import { Observable } from "rxjs/Rx";

@Injectable()
export class UserProfileService {

    private userProfileUrl = 'http://' + [process.env.API_URL] + '/users';  // URL to web api

    constructor(private _http: Http) {}

    post(url, token, data): Observable<any> {
        let headers: Headers = new Headers();
        headers.append("Authorization", "Basic " + token);
        headers.append("Content-Type", "application/json");
        return this._http.post(url, data, {headers: headers})
    }

    call(url, token): Observable<any> {
        let headers: Headers = new Headers();
        headers.append("Authorization", "Basic " + token);
        headers.append("Content-Type", "application/json");
        return this._http.get(url, {headers: headers})
    }

    getUserProfile(id, token): Promise<UserProfile> {
        return this.call(this.userProfileUrl + "/" + id, token)
                        .toPromise()
                        .then(response => response.json() as UserProfile)
                        .catch(error => this.handleError(error));
    }

    update(id, token, userProfile: UserProfile): Promise<UserProfile> {
        return this.post(this.userProfileUrl + "/" + id, token, JSON.stringify(userProfile))
                        .toPromise()
                        .then(() => userProfile)
                        .catch(error => this.handleError(error));
    }

    private handleError(error: any): Promise<any> {
        console.log(error);
        return Promise.reject(error);
    }
}
