import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GenericService {

  constructor(private http: HttpClient) { }

  public sendActionToBackend(body,url,method): Observable<any>{

    if(method=="post"){
      return this.http.post(url,body, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        })
      });
    }
    else if(method=="put"){
      return this.http.put(url,body, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        })
      });
    }
    else if(method=="delete"){
      return this.http.delete(url);
    }
    else if(method=="get"){
      return this.http.get(url);
    }
    else{
      return null;
    }

  } 

  public getSelectOptions(url): Observable<any>{
    return this.http.get(url);
  }
}
