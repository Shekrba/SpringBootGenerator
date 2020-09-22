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
  entityName = {};

  constructor(private activatedRoute: ActivatedRoute,private genericService:GenericService) { }

  ngOnInit() {
    this.entityName = this.activatedRoute.snapshot.paramMap.get('entity');

    var url = "http://localhost:8088/" + this.entityName;

    this.genericService.sendActionToBackend(null,url,"get").subscribe(ret => {
      console.log(ret);
      this.fields = Object.keys(ret[0]);
      this.entities = ret;
    });
  }

  delete(entity){
    var url = "http://localhost:8088/" + this.entityName+"/"+entity.id;

    this.genericService.sendActionToBackend(null,url,'delete').subscribe(ret =>{
      console.log(ret);
      this.ngOnInit();
    });
  }

}
