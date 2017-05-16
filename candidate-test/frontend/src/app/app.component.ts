import { Component, OnInit } from '@angular/core';

import { UserProfile } from './userProfiles/user-profile';
import { UserProfileService } from './userProfiles/user-profile.service';

import '../style/app.scss';

@Component({
  selector: 'my-app', // <my-app></my-app>
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title: string;
  userProfile: UserProfile;

    constructor(
        private userProfileService: UserProfileService
    ) {}

    getUserProfile(): void {
        this.userProfileService.getUserProfile()
            .then(userProfile => this.userProfile = userProfile);
    }

  ngOnInit(): void {
    this.getUserProfile();
  }
}
