import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { MinisterioModule } from './ministerio/ministerio.module';
import { MembroModule } from './membro/membro.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    MinisterioModule,
    MembroModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
