import { Component } from '@angular/core';
import { Employee } from 'src/app/model/employee';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent {
  employees: Employee[]= [];

  constructor(private service:EmployeeService){
    
  }


  ngOnInit(): void {
    this.getEmployees();
  }

  private getEmployees(){
    this.service.getEmployeeProfile().subscribe(data=>{
      this.employees=data;
    });
  }



  onPrint(){
    this.service.getEmployeePdf().subscribe(data=>{
      //this.employees=data;
      console.log(data);
    });
  }
}
