import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgxScannerQrcodeModule } from 'ngx-scanner-qrcode';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateComponent } from './component/create/create.component';
import { ListComponent } from './component/list/list.component';
import { UpdateComponent } from './component/update/update.component';
import { ScanComponent } from './component/scan/scan.component';
import { SafePipe } from './SafePipe';
import { Scanv2Component } from './component/scanv2/scanv2.component';
import { MenuComponent } from './component/menu/menu.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateComponent,
    ListComponent,
    UpdateComponent,
    ScanComponent,
    Scanv2Component,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxScannerQrcodeModule,
  ],
  providers: [AppComponent, SafePipe, ScanComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
