import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TabsPage } from './tabs.page';

const routes: Routes = [
  {
    path: 'tabs',
    component: TabsPage,
    children: [
      {
        path: 'aufgaben',
        loadChildren: () => import('../aufgaben/aufgaben.module').then(m => m.AufgabenPageModule)
      },
      {
        path: 'erledigte-aufgaben',
        loadChildren: () => import('../erledigte-aufgaben/erledigte-aufgaben.module').then( m => m.ErledigteAufgabenPageModule)
      },
      {
        path: 'einstellungen',
        loadChildren: () => import('../einstellungen/einstellungen.module').then( m => m.EinstellungenPageModule)
      },
      {
        path: 'hilfe',
        loadChildren: () => import('../hilfe/hilfe.module').then(m => m.HilfePageModule)
      },
      {
        path: '',
        redirectTo: '/tabs/aufgaben',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '',
    redirectTo: '/tabs/aufgaben',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
})
export class TabsPageRoutingModule { }
