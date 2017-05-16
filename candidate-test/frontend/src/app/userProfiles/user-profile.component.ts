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
  submitted = false;
    jsonError: string;
  onSubmit() { this.submitted = true; }

  constructor(
    private userProfileService: UserProfileService) { }



  getUserProfile(): void {
    this.userProfileService
        .getUserProfile()
        .then(userProfile => {
            this.userProfile = userProfile;
            console.log(userProfile);
        });
  }

  save(profileForm): void {
      if (profileForm.valid) {
          this.userProfileService.update(this.userProfile)
              .catch(error => {
                  this.jsonError = '';
                  console.log(error);
                  let jsonErrors = error.json().errors;
                  console.log(jsonErrors);
                  for (let errorMsg of jsonErrors) {
                      this.jsonError += errorMsg.defaultMessage + " ";
                  }
              });
          this.submitted = true;
      }
  }

  ngOnInit(): void {
      this.getUserProfile();
    }
}
