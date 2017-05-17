import { Component, OnInit } from '@angular/core';

import { UserProfile }                from './user-profile';
import { UserProfileService }         from './user-profile.service';

@Component({
  templateUrl: './user-profile.component.html',
  styleUrls: [ './user-profile.component.scss' ]
})

export class UserProfileComponent implements OnInit {
  userProfile: UserProfile;
  submitted = false;
  jsonError: string;

  constructor(
    private userProfileService: UserProfileService) {
  }

  getUserProfile(): void {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));

    this.userProfileService
        .getUserProfile(currentUser.id, currentUser.token)
        .then(userProfile => {
            this.userProfile = userProfile;
            console.log(userProfile);
        });
  }

  save(profileForm): void {
      let currentUser = JSON.parse(localStorage.getItem('currentUser'));
      let instance : UserProfileComponent = this;

      instance.submitted = false;
      this.jsonError = null;

      if (profileForm.valid) {
          this.userProfileService
            .update(currentUser.id, currentUser.token, this.userProfile)
            .then(
                function() {
                    instance.submitted = true; //this is not working
                }
            )
              .catch(error => {
                  this.jsonError = '';
                  console.log(error);
                  let jsonErrors = error.json().errors;
                  console.log(jsonErrors);
                  for (let errorMsg of jsonErrors) {
                      this.jsonError += errorMsg.defaultMessage + " ";
                  }
                  instance.submitted = false;
              });
      }
  }

  ngOnInit(): void {
      this.getUserProfile();
  }

}
