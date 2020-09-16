import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { GenericService } from 'src/app/services/generic.service';
import { HttpClient } from '@angular/common/http';
//import { readFileSync } from 'fs';


@Component({
  selector: 'app-generic-form',
  templateUrl: './generic-form.component.html',
  styleUrls: ['./generic-form.component.scss']
})
export class GenericFormComponent implements OnInit {

  constructor(private sanitizer: DomSanitizer,private genericService:GenericService,private activatedRoute: ActivatedRoute, private httpClient:HttpClient) {}

  private body={};
  private json=null;
  private button="Submit";
  private header="";
  private method=null;
  private rows = [
    {
      cols:[]
    }
  ]

  ngOnInit() {

    this.method = this.activatedRoute.snapshot.paramMap.get('method');

    this.httpClient.get("assets/json/"+this.activatedRoute.snapshot.paramMap.get('filePath')+".json").subscribe(
      data => {
        console.log(data);
        
        this.json = data;
      
        this.getJson();
      }
    );

  }

  getJson(){


    //this.json = JSON.parse(readFileSync(filePath, 'utf-8'));
       
    for(let field of this.json.form){

      if(field.default!=null){
        field.model=field.default;
      }

      if(field.validation===undefined){
        field.validation = {pattern:".*"};
      }else if(field.validation.pattern===undefined){
        field.validation.pattern = ".*";
      }

      if(field.row != null || field.col != null){
        if(this.rows[field.row] === undefined){
          this.rows[field.row]={cols:[]};
        }
        this.rows[field.row].cols[field.col] = field;
      }
    }

    this.rows = this.rows.filter(function (el) {
      return el != null;
    });

    for(let r of this.rows){
      r.cols= r.cols.filter(function (el) {
        return el != null;
      });
    }

    if(this.json.button !== undefined && this.json.button.name !== undefined){
      this.button = this.json.button.name;
    }

    if(this.json.header !== undefined){
      this.header = this.json.header;
    }
  }

  onSubmit(genericForm){
    if(genericForm.valid===true){
      for(let item of this.json.form){
        if(item.type!="reset"){
          this.body[item.id]=item.model;
        }
      }

      this.sendActionToBackend();
    }
  }

  sendActionToBackend(){
    console.log(this.body);
    this.genericService.sendActionToBackend(this.body,this.json.button.url,this.method).subscribe(
      res => {
        console.log(res);
      },
      err => {
        console.log(err);
        alert("Error while calling backend");
      }
    );
  }
}