import { Component, Input, OnInit } from '@angular/core';
import { SelectedFiles } from 'ngx-scanner-qrcode';


@Component({
  selector: 'app-scan',
  templateUrl: './scan.component.html',
  styleUrls: ['./scan.component.scss']
})
export class ScanComponent implements OnInit{

  @Input() data?: SelectedFiles;

  constructor() { }

  ngOnInit() {
  }
}
