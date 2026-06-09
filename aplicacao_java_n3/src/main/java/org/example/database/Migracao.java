package org.example.database;

public class Migracao {

    public static void gerarTabelas() {

        String pacientes = """
                CREATE TABLE IF NOT EXISTS pacientes (
                    id TEXT PRIMARY KEY,
                    nome TEXT NOT NULL,
                    sexo INTEGER,
                    data_nascimento TEXT,
                    cpf TEXT NOT NULL,
                    telefone TEXT
                )
                """;

        String medicos = """
                CREATE TABLE IF NOT EXISTS medicos (
                    id TEXT PRIMARY KEY,
                    nome TEXT NOT NULL,
                    especialidade TEXT NOT NULL,
                    cpf TEXT NOT NULL
                )
                """;

        String usuarios = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id TEXT PRIMARY KEY,
                    nome TEXT NOT NULL,
                    email TEXT NOT NULL,
                    senha TEXT NOT NULL
                )
                """;

        String agendamentos = """
                CREATE TABLE IF NOT EXISTS agendamentos (
                    id TEXT PRIMARY KEY,
                    paciente_id TEXT NOT NULL,
                    medico_id TEXT NOT NULL,
                    data TEXT NOT NULL,
                    hora TEXT NOT NULL,
                    FOREIGN KEY (paciente_id)
                    REFERENCES pacientes(id),
                
                    FOREIGN KEY (medico_id)
                    REFERENCES medicos(id)
                )
                """;

        try {

            Conexao.getInstancia()
                    .createStatement()
                    .execute(pacientes);

            Conexao.getInstancia()
                    .createStatement()
                    .execute(medicos);

            Conexao.getInstancia()
                    .createStatement()
                    .execute(usuarios);

            Conexao.getInstancia()
                    .createStatement()
                    .execute(agendamentos);

            System.out.println("tabelas criadas");

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao criar tabelas",
                    e
            );

        }
    }
}
