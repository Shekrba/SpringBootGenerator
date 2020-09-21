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
  private oTmColumns={};
  private oTmData={};


  ngOnInit() {

    this.method = this.activatedRoute.snapshot.paramMap.get('method');

    this.httpClient.get("assets/json/"+this.activatedRoute.snapshot.paramMap.get('filePath')+".json").subscribe(
      data => {
        
        this.json = data;
      
        this.getJson();
      }
    );

  }

  getJson(){  
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

      //oneToMany manyToOne manyToMany
      if(field.type!==undefined && (field.type=="manyToOne" || field.type=="manyToMany")) {
        if(field.getOptionsUrl!==undefined){
          this.genericService.sendActionToBackend(null,field.getOptionsUrl,"get").subscribe(
            res => {
              field.options = [];
              
              for(var opt of res){

                let optText = JSON.stringify(Object.values(opt));
                let optValue = opt.id;

                field.options.push(
                  {
                    text:optText.substring(1, optText.length-1),
                    value:optValue
                  }
                )
              }
            
            },
            err => {
              console.log(err);
              alert(err);
            }
          );
        }
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
      if(this.method == "post"){
        this.header = "Add "+this.json.header;
      }
      else if(this.method == "put"){
        this.header = "Update "+this.json.header;
      }
      else if(this.method == "delete"){
        this.header = "Delete "+this.json.header;
      }
      else{
        this.header = this.json.header;
      }

    }

    if(this.method == "put"){
      if(this.json.form.some(item => item.type === 'oneToMany')){
        let oneToManyFields = this.json.form.filter(item => item.type === 'oneToMany');
        for(let field of oneToManyFields){
          console.log(field);
          this.genericService.sendActionToBackend("",field.getOptionsUrl,"get").subscribe(ret => {
            console.log(ret);
            this.oTmData[field.id] = ret;
            this.oTmColumns[field.id] = Object.keys(ret[0]);
          });
        }
      }
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
        alert(err);
      }
    );
  }
}