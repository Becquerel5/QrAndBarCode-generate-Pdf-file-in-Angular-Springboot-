import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeService } from 'src/app/service/employee.service';
import { Observable } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import * as constant from '../../constantsRoutes';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent {
  
  //loading : boolean = constant.true_value;
  public userFile: any = File;
  public userFile2: any = File;
  public userFile3: any = File;
  reactiveForm: any = FormGroup;

    currentFile?: File;
    progress = 0;
    message = '';
    fileInfos?: Observable<any>;

    storeemployeeinfo = "";

  constructor(private router:Router,private employeeservice:EmployeeService,private fb: FormBuilder) {
    this.reactiveForm = this.fb.group({
      firstName:new FormControl('',Validators.required),
      lastName:new FormControl('',Validators.required),
      emailId:new FormControl('',Validators.compose([Validators.required,Validators.email]))
    });
   }

  ngOnInit(): void {
  }

  respdata:any;


 /* reactiveform = new FormGroup({
    firstName:new FormControl('',Validators.required),
    lastName:new FormControl('',Validators.required),
    emailId:new FormControl('',Validators.compose([Validators.required,Validators.email]))
  });*/

  onSelectFile(event:any){
    const file = event.target.files[0];
    this.userFile=file;
    console.log(file);
  }
  onSelectFile2(event:any){
    const file2 = event.target.files[0];
    this.userFile2=file2;
    console.log(file2);
  }

  onSelectFile3(event:any){
    const file3 = event.target.files[0];
    this.userFile3=file3;
    console.log(file3);
  }


  saveForm(submitForm: FormGroup){
    if (submitForm.valid) {
      const user = submitForm.value;
      const formData=new FormData();
      formData.append('user',JSON.stringify(user));
      formData.append('file',this.userFile);
      formData.append('file2',this.userFile2);
      formData.append('file3',this.userFile3);
      this.employeeservice.saveEmployeeProfile(formData).subscribe((response)=>{

        if (response === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * response.loaded / response.total);
        } else if (response instanceof HttpResponse) {
          this.message = response.body.message;
          //this.fileInfos = this.employeeservice.getFiles();
        }
        console.log(response);
        alert("save syccessfully");
        //this.router.navigate([constant.redirect]);

      });
    } else {
      //this.va
      alert("todo more validations");
      this.message = 'Could not upload the file!';
      
    }

  }


}
