import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Employee } from '../model/employee';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private baseUrl="http://localhost:8084/api/becquerel/employees";
  private baseurl2="http://localhost:8084/api/becquerel/employees/";
  public pdflist="http://localhost:8084/api/becquerel/employees/download/employeepdf";

  constructor(private httpClient: HttpClient) { 
    this.httpClient=httpClient;
  }

   headers = new HttpHeaders().set(
    "Content-Type",
    "application/json; charset=utf-8"
  );
 Registration(employee:Employee){
    return this.httpClient.post(`http://localhost:8084/api/becquerel/employees/save`,employee);
  }

  /*const headers = new HttpHeaders({
    headers: new HttpHeaders({ "Content-Type": "multipart/form-data" })
  })*/

  saveEmployeeProfile(formData:FormData): Observable<any>{
    return this.httpClient.post('http://localhost:8084/api/becquerel/employees/save',formData);
  }

  getEmployeeProfile(): Observable<any>{
    return this.httpClient.get<Employee[]>(`${this.baseurl2}`);
  }

  getEmployeePdf(): Observable<any>{
    return this.httpClient.get<Employee[]>(`${this.pdflist}`);
  }

  getEmployeeList(): Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseUrl}`);
  }

  /*Registration(employee: Employee): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}`,employee);
  }*/
  getFiles(){
    
  }
}
