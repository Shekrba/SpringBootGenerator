import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit {

  currentUser$: Observable<any>;
  private numberOfTasks = 0;

  constructor(private router: Router) { 
  }

  ngOnInit() {
  }
}