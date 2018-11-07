import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { CarouselModule } from "ngx-bootstrap/carousel";
import { RouterModule, Routes } from "../../node_modules/@angular/router";
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { SignupComponent } from "./home/signup/signup.component";
import { LoginComponent } from "./home/login/login.component";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { AdminHomeComponent } from './admin/component/admin-home/admin-home.component';
import { EmployeeComponent } from './admin/component/employee/employee.component';
import { SkillsetComponent } from './admin/component/skillset/skillset.component';
import { ManagerComponent } from './admin/component/manager/manager.component';
import { ManagerAnalysisComponent } from './admin/component/manager-analysis/manager-analysis.component';
import { EmployeeReportsComponent } from './admin/component/employee-reports/employee-reports.component';
import { SkillReportsComponent } from './admin/component/skill-reports/skill-reports.component';
import { SkillFormComponent } from './admin/component/skill-form/skill-form.component';
import { SortPipe } from './admin/pipes/sort.pipe';
import { AdminHeaderComponent } from './admin/component/admin-header/admin-header.component';
//import { AuthService } from "./home/services/auth.service";

import { DashboardComponent } from './employee/components/dashboard/dashboard.component';
import { ListSkillsComponent } from './employee/components/list-skills/list-skills.component';
import { AddSkillComponent } from './employee/components/add-skill/add-skill.component';
import { EditProfileComponent } from './employee/components/edit-profile/edit-profile.component';
import { ContactComponent } from './employee/components/contact/contact.component';
import { NotificationComponent } from './employee/components/notification/notification.component';
import { SidebarComponent } from './employee/components/sidebar/sidebar.component';
import { RequestSkillComponent } from './employee/components/request-skill/request-skill.component';
import { ViewReportComponent } from './employee/components/view-report/view-report.component';
import { LoaderComponentComponent } from './employee/components/loader-component/loader-component.component';
import { EmployeeHomeComponent } from './employee/components/employee-home/employee-home.component';

import { SkillComponent } from './manager/component/skill/skill.component';
import { EmployeeDataComponent } from './manager/component/employee-data/employee-data.component';
import { EmployeeRequestsComponent } from './manager/component/employee-requests/employee-requests.component';
import { ApprovalComponent } from './manager/component/approval/approval.component';
import { EmployeeSkillComponent } from './manager/component/employee-skill/employee-skill.component';
import { RequirementComponent } from './manager/component/requirement/requirement.component';
import { DisplayComponent } from './manager/component/display/display.component';
import { RecommendationsComponent } from './manager/component/recommendations/recommendations.component';
import { EmpCourseComponent } from './manager/component/emp-course/emp-course.component';
import { ManagerRequestComponent } from './manager/component/manager-request/manager-request.component';
import { ManagerHomeComponent } from './manager/component/manager-home/manager-home.component';
import { ManagerHeaderComponent } from './manager/component/manager-header/manager-header.component';
import { MainComponent } from './manager/component/main/main.component';
import { AnalysisComponent } from './manager/component/analysis/analysis.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'admin', component: AdminHeaderComponent, children: [
      { path: '', component : AdminHomeComponent },
      { path: 'employee', component: EmployeeComponent },
      { path: 'skillset', component: SkillsetComponent },
      { path: 'manager', component: ManagerComponent },
      { path: 'manager-analysis', component: ManagerAnalysisComponent },
      { path: 'empName/:employeeId', component: EmployeeReportsComponent },
      { path: 'skillName/:field/:srchValue', component: SkillReportsComponent },
      { path: 'skillName', component: SkillReportsComponent },
      { path: 'addSkill', component: SkillFormComponent },
      { path: 'editSkill/:skillId', component: SkillFormComponent }
    ]
  },
  {
    path: 'employee', component: EmployeeHomeComponent, children: [
      { path: '', component: DashboardComponent },
      { path: 'listSkills', component: ListSkillsComponent },
      { path: 'addSkill', component: AddSkillComponent },
      { path: 'editProfile', component: EditProfileComponent },
      { path: 'contact', component: ContactComponent },
      { path: 'notification', component: NotificationComponent },
      { path: 'viewReport', component: ViewReportComponent },
      { path: 'requestSkill', component: RequestSkillComponent }
    ]
  },
  {
    path: 'manager', component: ManagerHeaderComponent, children: [
      { path: '', component: ManagerHomeComponent },
      { path: 'skills', component: SkillComponent },
      { path: 'employeeRequest', component: EmployeeRequestsComponent },
      { path: 'approve/:id', component: ApprovalComponent },
      { path: 'employeeData', component: EmployeeDataComponent },
      { path: 'employeeSkill/:id', component: EmployeeSkillComponent },
      { path: 'requirement', component: RequirementComponent },
      { path: 'requirement/:id', component: DisplayComponent },
      { path: 'recommendations', component: RecommendationsComponent },
      { path: 'empCourse/:id/:name', component: EmpCourseComponent },
      { path: 'managerRequest', component: ManagerRequestComponent },
      { path: 'analysis', component: AnalysisComponent },
      { path: 'skillName', component : SkillReportsComponent },
      { path: 'skillName/:field/:srchValue', component: SkillReportsComponent }
    ]
  }
  //{ path: 'skillName/:field/:value', component: SkillReportsComponent }
];
@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    EmployeeComponent,
    SkillsetComponent,
    ManagerComponent,
    ManagerAnalysisComponent,
    EmployeeReportsComponent,
    SkillReportsComponent,
    SkillFormComponent,
    SortPipe,
    AdminHeaderComponent,
    AdminHomeComponent,

    DashboardComponent,
    ListSkillsComponent,
    AddSkillComponent,
    EditProfileComponent,
    LoginComponent,
    SignupComponent,
    ContactComponent,
    NotificationComponent,
    SidebarComponent,
    RequestSkillComponent,
    ViewReportComponent,
    LoaderComponentComponent,
    EmployeeHomeComponent,

    SkillComponent,
    EmployeeDataComponent,
    EmployeeRequestsComponent,
    ApprovalComponent,
    EmployeeSkillComponent,
    RequirementComponent,
    DisplayComponent,
    RecommendationsComponent,
    EmpCourseComponent,
    ManagerRequestComponent,
    ManagerHomeComponent,
    LoginComponent,
    MainComponent,
    ManagerHeaderComponent,
    AnalysisComponent

  ],
  imports: [
    CarouselModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule,
    MDBBootstrapModule,
    AngularFontAwesomeModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
