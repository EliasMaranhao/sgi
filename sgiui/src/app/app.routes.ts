import { Routes } from '@angular/router';
import { CadastroMinisterioComponent } from './components/Ministerio/cadastro-ministerio/cadastro-ministerio.component';
import { PesquisaMinisterioComponent } from './components/Ministerio/pesquisa-ministerio/pesquisa-ministerio.component';
import { PesquisaMembroComponent } from './components/Membro/pesquisa-membro/pesquisa-membro.component';
import { CadastroMembroComponent } from './components/Membro/cadastro-membro/cadastro-membro.component';

export const routes: Routes = [
    {
        path: 'ministerios',
        component: PesquisaMinisterioComponent,
        children : [
            {
                path: 'novo',
                component: CadastroMinisterioComponent,
            }
        ]
    },
    {
        path: 'membros',
        component: PesquisaMembroComponent
    }
];
