import { Component } from '@angular/core';
import { SelectedFiles, NgxScannerQrcodeService, BaseConfig } from 'ngx-scanner-qrcode';
import { cambeep_base64 } from 'src/app/beep.audio';

@Component({
  selector: 'app-scanv2',
  templateUrl: './scanv2.component.html',
  styleUrls: ['./scanv2.component.scss']
})
export class Scanv2Component {
  public selectedFiles: SelectedFiles[] = [];

  constructor(private qrcode: NgxScannerQrcodeService) { }

  
  public config: BaseConfig = {
    isAuto: false,
    isDraw: true,
    isAlwaysEmit: true,
    text: { font: '25px serif' }, // Hiden { font: '0px', bottom: 40 },
    frame: { lineWidth: 8 },
    medias: {
      audio: false,
      video: {
        facingMode: 'environment', // Front and back camera { facingMode: front ? "user" : "environment" } 
        width: { ideal: 1280 },
        height: { ideal: 720 }
      }
    }
  };


  public onEvent(e: any): void {
    new Audio(cambeep_base64).play();
    console.log(e)
  }

  public onError(e: any): void {
    alert(e);
  }

  public handle(action: any, fn: string): void {
    action[fn]().subscribe(console.log, console.error);
  }

  public onSelects(files: any) {
    this.qrcode.loadFiles(files, {...this.config, isDraw: false}).subscribe(res => {
      this.selectedFiles = res.filter(f => f.urlQR);
      console.log(res);
    });
  }


}
