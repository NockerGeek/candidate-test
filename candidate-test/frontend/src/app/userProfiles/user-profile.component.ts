import { Component, OnInit } from '@angular/core';
import { UserProfile }                from './user-profile';
import { UserProfileService }         from './user-profile.service';
@Component({
  selector: 'my-heroes',
  templateUrl: './user-profile.component.html',
  styleUrls: [ './user-profile.component.scss' ]
})
export class UserProfileComponent implements OnInit {
  userProfile: UserProfile;
  constructor(
    private userProfileService: UserProfileService) { }

  getUser(): void {
    this.userProfileService
        .getUserProfile()
        .then(userProfile => this.userProfile = userProfile);
  }

  ngOnInit(): void {
      this.getUser();
    }
}
