import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'title-bl-comp',
        data: { pageTitle: 'britishLibraryDemoApp.title.home.title' },
        loadChildren: () => import('./title-bl-comp/title-bl-comp.module').then(m => m.TitleBlCompModule),
      },
      {
        path: 'topic-bl-comp',
        data: { pageTitle: 'britishLibraryDemoApp.topic.home.title' },
        loadChildren: () => import('./topic-bl-comp/topic-bl-comp.module').then(m => m.TopicBlCompModule),
      },
      {
        path: 'name-bl-comp',
        data: { pageTitle: 'britishLibraryDemoApp.name.home.title' },
        loadChildren: () => import('./name-bl-comp/name-bl-comp.module').then(m => m.NameBlCompModule),
      },
      {
        path: 'classification-bl-comp',
        data: { pageTitle: 'britishLibraryDemoApp.classification.home.title' },
        loadChildren: () => import('./classification-bl-comp/classification-bl-comp.module').then(m => m.ClassificationBlCompModule),
      },
      {
        path: 'record-bl-comp',
        data: { pageTitle: 'britishLibraryDemoApp.record.home.title' },
        loadChildren: () => import('./record-bl-comp/record-bl-comp.module').then(m => m.RecordBlCompModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
