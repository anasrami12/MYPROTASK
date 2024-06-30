import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './modules/header/header.component';
import { AsideComponent } from './modules/aside/aside.component';
import { LoginComponent } from './modules/login/login.component';
import { HomeComponent } from './modules/home/home.component';
import { ProjectsComponent } from './modules/projects/projects.component';
import { HttpClientModule } from '@angular/common/http';
import { CalendarComponent } from './modules/calendar/calendar.component';
import { FullCalendarModule } from '@fullcalendar/angular';
import { EditprofileComponent } from './modules/editprofile/editprofile.component';
import { UsersComponent } from './modules/users/users.component';
import { TasksComponent } from './modules/tasks/tasks.component';
import { HistoryComponent } from './modules/history/history.component';
import { UserComponent } from './modules/user/user.component';
import { NewUserComponent } from './modules/new-user/new-user.component';
import { HomePmComponent } from './modules/home-pm/home-pm.component';
import { RouterEvent, RouterModule, Routes } from '@angular/router';
import { ProjectUsersPmComponent } from './modules/project-users-pm/project-users-pm.component';
import { RequestComponent } from './modules/request/request.component';
import { AllProjectsComponent } from './modules/all-projects/all-projects.component';
import { AngularFireModule } from '@angular/fire/compat';
import { AngularFireStorageModule } from '@angular/fire/compat/storage';
import { environment } from '../environments/environment';
import { UserAdminComponent } from './modules/usersadmin/usersadmin.component';

import { CreateprojectComponent } from './modules/createproject/createproject.component';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AsideComponent,
    LoginComponent,
    HomeComponent,
    ProjectsComponent,
    CalendarComponent,
    EditprofileComponent,
    UsersComponent,
    TasksComponent,
    HistoryComponent,
    UserComponent,
    NewUserComponent,
    HomePmComponent,
    ProjectUsersPmComponent,
    RequestComponent,
    UsersComponent,
    AllProjectsComponent,
    UserAdminComponent,
    CreateprojectComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FullCalendarModule,
    RouterModule,AngularFireModule.initializeApp(environment.firebase),
    AngularFireStorageModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
