import { Component, OnInit } from '@angular/core';

import { UserProfile }                from './user-profile';
import { UserProfileService }         from './user-profile.service';

@Component({
  selector: 'my-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: [ './user-profile.component.scss' ]
})

export class UserProfileComponent implements OnInit {
  userProfile: UserProfile;

  constructor(
    private userProfileService: UserProfileService) { }

  submitted = false;
  onSubmit() { this.submitted = true; }
  active = true;

  getUserProfile(): void {
    this.userProfileService
        .getUserProfile()
        .then(userProfile => {
            this.userProfile = userProfile;
            console.log(userProfile);
        });
  }

  save(): void {
    this.userProfileService.update(this.userProfile);
  }

  ngOnInit(): void {
      this.getUserProfile();
    }
}
