export class Ministerio {
    codigo!: number;
    nome!: string;
    dataInauguracao!: Date;
    prPresidente!: string;
    vcPresidenete!: string;
    endereco!: Endereco;
}

class Endereco {

}

export class Membro {
    codigo!: number;
    nome!: string;
    dataNascimento!: Date;
}