import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GenericFormComponent } from './components/generic-form/generic-form.component';
import { ListTemplateComponent } from './components/list-template/list-template.component';

const routes: Routes = [
  { path: 'generic/:filePath/:method', component: GenericFormComponent },
  { path: 'generic/:filePath/:method/:id', component: GenericFormComponent },
  { path: 'entities/:entity', component: ListTemplateComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
