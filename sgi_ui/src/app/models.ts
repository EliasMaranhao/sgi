export interface Matriz {
    id: number;
    nome: string;
    pastorPresidente: string;
    vicePresidente: string
    denominacao: string;
    filiais: Filial[]
}

export interface Filial {
    id?: number;
    nome?: string;
    pastorDirigente: string;
    dataInauguracao:  Date;
    filial: boolean;
    endereco: Endereco;
    matriz: Matriz;
    contato: Contato;
    cnpj: string;
}

interface Endereco {
    rua: string;
    numero: string;
    complemento: string;
    cep: string;
    bairro: string;
    cidade: string;
    uf: string;
    pais: string
}

interface Contato {
    instagram: string;
    telefone: string;
    email: string;
    whatsapp: string
}

export interface Membro {
    id: number;
    nome: string;
    cpf: string;
    dataBatismo: Date;
    statusMembro: StatusMembro;
    endereco: Endereco;
    fotoUrl: string;
    filial: Filial;
    nomeacoes: Nomeacao[];
}

enum StatusMembro {
    ATIVO,
    INATIVO,
    SUSPENSO,
    EXCLUIDO,
    DESVIADO,
    MUDOU_DE_IGREJA
}

interface Nomeacao {
    id: number;
    membro: Membro;
    cargo: Cargo;
    departamento: Departamento;
    dataPosse: Date;
    dataSaida: Date;
}

interface Cargo {
    id: number;
    nome: string;
}

interface Departamento {
    id: number;
    nome: string;
}