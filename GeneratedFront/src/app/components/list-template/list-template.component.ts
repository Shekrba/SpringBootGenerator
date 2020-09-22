import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GenericService } from 'src/app/services/generic.service';

@Component({
  selector: 'app-list-template',
  templateUrl: './list-template.component.html',
  styleUrls: ['./list-template.component.scss']
})
export class ListTemplateComponent implements OnInit {

  entities = [];
  fields = [];

  constructor(private activatedRoute: ActivatedRoute,private genericService:GenericService) { }

  ngOnInit() {
    var url = "http://localhost:8088/" + this.activatedRoute.snapshot.paramMap.get('entity');

    this.genericService.sendActionToBackend(null,url,"get").subscribe(ret => {
      console.log(ret);
      this.fields = Object.keys(ret[0]);
      this.entities = ret;
    });
  }

}
