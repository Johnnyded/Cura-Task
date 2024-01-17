// employee.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee} from "../Model/employee";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiUrl = 'http://localhost:8080/employees'; // Adjust the URL to your backend

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.apiUrl);
  }
}
