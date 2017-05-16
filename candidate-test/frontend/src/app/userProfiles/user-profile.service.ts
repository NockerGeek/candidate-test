import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { UserProfile } from './user-profile';

@Injectable()
export class UserProfileService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private userProfileUrl = 'http://localhost:8090/users';  // URL to web api

    constructor(private http: Http) { }

    getUserProfile(): Promise<UserProfile> {
        return this.http.get(this.userProfileUrl)
                        .toPromise()
                        .then(response => response.json() as UserProfile)
                        .catch(error => this.handleError(error));
    }

    update(userProfile: UserProfile): Promise<UserProfile> {
        return this.http.post(this.userProfileUrl, JSON.stringify(userProfile), {headers: this.headers})
                        .toPromise()
                        .then(() => userProfile)
                        .catch(error => this.handleError(error));
    }

    private handleError(error: any): Promise<any> {
        console.log(error);
        return Promise.reject(error);
    }
}
