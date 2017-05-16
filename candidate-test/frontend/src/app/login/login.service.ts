import { Injectable } from '@angular/core';
import {Headers, Http, RequestOptions} from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { UserProfile } from './user-profile';
import { Observable } from "rxjs/Rx";

export class User {
    constructor(
        public username: string,
        public password: string) { }
}

@Injectable()
export class LoginService {

    constructor(
        private _router: Router){}

    logout() {
        localStorage.removeItem("user");
        this._router.navigate(['Login']);
    }

    login(user){
        var authenticatedUser = users.find(u => u.email === user.email);
        if (authenticatedUser && authenticatedUser.password === user.password){
            localStorage.setItem("user", authenticatedUser);
            this._router.navigate(['Home']);
            return true;
        }
        return false;

    }

    checkCredentials(){
        if (localStorage.getItem("user") === null){
            this._router.navigate(['Login']);
        }
    }

    login (user): Observable<LoginResponse> { // custom class, may be empty now

        let headers = new Headers({
            'Authorization': 'Basic ' + btoa(loginDetails.username + ':' + loginDetails.password),
            'X-Requested-With': 'XMLHttpRequest' // to suppress 401 browser popup
        });

        let options = new RequestOptions({
            headers: headers
        });

        return this
            .http
            .post(this.loginUrl, {}, options)
            .catch(e => this.handleError(e); // handle 401 error - bad credentials
    }

}