import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/index';
import { UserProfileComponent } from './userProfiles/index';
import { AuthGuard } from './_guards/index';
 
const appRoutes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: '', component: UserProfileComponent, canActivate: [AuthGuard] },
 
    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
