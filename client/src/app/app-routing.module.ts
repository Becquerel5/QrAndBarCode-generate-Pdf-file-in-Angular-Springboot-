import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateComponent } from './component/create/create.component';
import { ListComponent } from './component/list/list.component';
import { ScanComponent } from './component/scan/scan.component';
import { Scanv2Component } from './component/scanv2/scanv2.component';

const routes: Routes = [
  {path:"create",component:CreateComponent},
  {path:"scan",component:ScanComponent},
  {path:"scanv2",component:Scanv2Component},
  {path:"list",component:ListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
