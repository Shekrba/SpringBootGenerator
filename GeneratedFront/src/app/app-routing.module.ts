import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GenericFormComponent } from './components/generic-form/generic-form.component';

const routes: Routes = [
  { path: 'generic/:filePath/:method', component: GenericFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
