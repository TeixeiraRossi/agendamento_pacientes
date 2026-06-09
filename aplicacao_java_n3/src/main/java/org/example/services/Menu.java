package org.example.services;

import org.example.dto.AgendamentoDto;
import org.example.dto.MedicoDto;
import org.example.dto.PacienteDto;
import org.example.dto.UsuarioDto;
import org.example.entities.Agendamento;
import org.example.entities.Medico;
import org.example.entities.Paciente;
import org.example.entities.Usuario;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    int opc = 0;
    private MedicoService medicoService;
    private PacienteService pacienteService;
    private AgendamentoService agendamentoService;
    private UsuarioService usuarioService;

    public Menu(MedicoService medicoService, PacienteService pacienteService,
                AgendamentoService agendamentoService, UsuarioService usuarioService, Scanner scanner) {
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
        this.agendamentoService = agendamentoService;
        this.usuarioService = usuarioService;
        this.scanner = scanner;
    }

    public void menuPaciente() {
        do {
            System.out.println("" +
                    "---------Menu Paciente--------\n" +
                    "1 - Listar Pacientes\n" +
                    "2 - Buscar Paciente (por CPF)\n" +
                    "3 - Cadastrar Paciente\n" +
                    "4 - Atualizar Paciente\n" +
                    "5 - Excluir Paciente\n" +
                    "6 - Voltar");
            opc = scanner.nextInt();

            String idPaciente;
            String nome;
            Integer sexo;
            String cpf;
            String dataNascimento;
            String telefone;

            switch (opc) {
                case 1:
                    System.out.println("\n--- Lista de Pacientes ---");
                    // Alterado de listar() para buscarTodos()
                    for (Paciente paciente : pacienteService.buscarTodos()) {
                        System.out.println("Id: " + paciente.getId());
                        System.out.println("Nome: " + paciente.getNome());
                        System.out.println("CPF: " + paciente.getCpf());
                        System.out.println("Telefone: " + paciente.getTelefone() + "\n");
                    }
                    break;
                case 2:
                    System.out.print("Digite o CPF do Paciente (apenas números): ");
                    cpf = scanner.next();

                    // Alterado de encontrar() por ID para buscarPorCpf()
                    Paciente paciente = pacienteService.buscarPorCpf(cpf);

                    if (paciente != null) {
                        System.out.println("\n--- Dados do Paciente ---");
                        System.out.println("Id: " + paciente.getId());
                        System.out.println("Nome: " + paciente.getNome());
                        System.out.println("Sexo: " + paciente.getSexo());
                        System.out.println("CPF: " + paciente.getCpf());
                        System.out.println("Data de Nasc.: " + paciente.getDataNascimento());
                        System.out.println("Telefone: " + paciente.getTelefone() + "\n");
                    } else {
                        System.out.println("Paciente não encontrado!\n");
                    }
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("Digite o nome do paciente: ");
                    nome = scanner.nextLine();
                    System.out.print("Digite o sexo do paciente(0 = Masculino, 1 = Feminino): ");
                    sexo = scanner.nextInt();
                    System.out.print("Digite o CPF do paciente: ");
                    cpf = scanner.nextLine();
                    System.out.print("Digite a data de nascimento (ex: DD/MM/AAAA): ");
                    dataNascimento = scanner.nextLine();
                    System.out.print("Digite o telefone do paciente: ");
                    telefone = scanner.nextLine();

                    // Alterado de criar() para salvar(), passando todos os atributos do DTO
                    pacienteService.salvar(new PacienteDto(nome, sexo, cpf, dataNascimento, telefone));
                    System.out.println("Paciente cadastrado com sucesso!\n");
                    break;
                case 4:
                    System.out.print("Digite o Id do Paciente a ser atualizado: ");
                    idPaciente = scanner.next();
                    scanner.nextLine();

                    System.out.print("Digite o novo nome do paciente: ");
                    nome = scanner.nextLine();
                    System.out.print("Digite o novo sexo do paciente: ");
                    sexo = scanner.nextInt();
                    System.out.print("Digite o novo CPF do paciente: ");
                    cpf = scanner.nextLine();
                    System.out.print("Digite a nova data de nascimento: ");
                    dataNascimento = scanner.nextLine();
                    System.out.print("Digite o novo telefone do paciente: ");
                    telefone = scanner.nextLine();

                    // A ordem dos parâmetros no seu service é (PacienteDto, String)
                    pacienteService.atualizar(new PacienteDto(nome, sexo, cpf, dataNascimento, telefone), idPaciente);
                    System.out.println("Paciente atualizado com sucesso!\n");
                    break;
                case 5:
                    System.out.print("Digite o Id do Paciente: ");
                    idPaciente = scanner.next();

                    boolean excluiu = pacienteService.excluir(idPaciente);
                    if(excluiu) {
                        System.out.println("Paciente excluído com sucesso!\n");
                    } else {
                        System.out.println("Erro: Paciente não encontrado para exclusão.\n");
                    }
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
                    break;
            }
        } while(opc != 6);
    }

    public void menuMedico() {
        do {
            System.out.println("" +
                    "---------Menu Médico--------\n" +
                    "1 - Listar Médicos\n" +
                    "2 - Buscar Médico (por CPF)\n" +
                    "3 - Cadastrar Médico\n" +
                    "4 - Atualizar Médico\n" +
                    "5 - Excluir Médico\n" +
                    "6 - Voltar");
            opc = scanner.nextInt();

            // Variáveis baseadas no MedicoDto
            String idMedico;
            String nome;
            String especialidade;
            String cpf;

            switch (opc) {
                case 1:
                    System.out.println("\n--- Lista de Médicos ---");
                    for (Medico medico : medicoService.buscarTodos()) {
                        System.out.println("Id: " + medico.getId());
                        System.out.println("Nome: " + medico.getNome());
                        System.out.println("Especialidade: " + medico.getEspecialidade());
                        System.out.println("CPF: " + medico.getCpf() + "\n");
                    }
                    break;
                case 2:
                    System.out.print("Digite o CPF do Médico (apenas números): ");
                    cpf = scanner.next();

                    Medico medico = medicoService.buscarPorCpf(cpf);

                    if (medico != null) {
                        System.out.println("\n--- Dados do Médico ---");
                        System.out.println("Id: " + medico.getId());
                        System.out.println("Nome: " + medico.getNome());
                        System.out.println("Especialidade: " + medico.getEspecialidade());
                        System.out.println("CPF: " + medico.getCpf() + "\n");
                    } else {
                        System.out.println("Médico não encontrado!\n");
                    }
                    break;
                case 3:
                    scanner.nextLine(); // Limpa o buffer do teclado
                    System.out.print("Digite o nome do médico: ");
                    nome = scanner.nextLine();
                    System.out.print("Digite a especialidade do médico: ");
                    especialidade = scanner.nextLine();
                    System.out.print("Digite o CPF do médico: ");
                    cpf = scanner.nextLine();

                    medicoService.salvar(new MedicoDto(nome, especialidade, cpf));
                    System.out.println("Médico cadastrado com sucesso!\n");
                    break;
                case 4:
                    System.out.print("Digite o Id do Médico a ser atualizado: ");
                    idMedico = scanner.next();
                    scanner.nextLine(); // Limpa o buffer do teclado

                    System.out.print("Digite o novo nome do médico: ");
                    nome = scanner.nextLine();
                    System.out.print("Digite a nova especialidade do médico: ");
                    especialidade = scanner.nextLine();
                    System.out.print("Digite o novo CPF do médico: ");
                    cpf = scanner.nextLine();

                    // Passando o DTO e o ID conforme a assinatura do seu método atualizar
                    medicoService.atualizar(new MedicoDto(nome, especialidade, cpf), idMedico);
                    System.out.println("Médico atualizado com sucesso!\n");
                    break;
                case 5:
                    System.out.print("Digite o Id do Médico: ");
                    idMedico = scanner.next();

                    boolean excluiu = medicoService.excluir(idMedico);
                    if(excluiu) {
                        System.out.println("Médico excluído com sucesso!\n");
                    } else {
                        System.out.println("Erro: Médico não encontrado para exclusão.\n");
                    }
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
                    break;
            }
        } while(opc != 6);
    }

    public void menuUsuario() {
        do {
            System.out.println("" +
                    "---------Menu Usuário--------\n" +
                    "1 - Listar Usuários\n" +
                    "2 - Buscar Usuário (por E-mail)\n" +
                    "3 - Cadastrar Usuário\n" +
                    "4 - Atualizar Usuário\n" +
                    "5 - Excluir Usuário\n" +
                    "6 - Voltar");
            opc = scanner.nextInt();

            // Variáveis baseadas no UsuarioDto
            String idUsuario;
            String nome;
            String email;
            String senha;

            switch (opc) {
                case 1:
                    System.out.println("\n--- Lista de Usuários ---");
                    for (Usuario usuario : usuarioService.buscarTodos()) {
                        System.out.println("Id: " + usuario.getId());
                        System.out.println("Nome: " + usuario.getNome());
                        System.out.println("E-mail: " + usuario.getEmail() + "\n");
                        // Obs: A senha foi omitida da listagem por questões de segurança.
                    }
                    break;
                case 2:
                    System.out.print("Digite o E-mail do Usuário: ");
                    email = scanner.next();

                    Usuario usuario = usuarioService.buscarPorEmail(email);

                    if (usuario != null) {
                        System.out.println("\n--- Dados do Usuário ---");
                        System.out.println("Id: " + usuario.getId());
                        System.out.println("Nome: " + usuario.getNome());
                        System.out.println("E-mail: " + usuario.getEmail() + "\n");
                    } else {
                        System.out.println("Usuário não encontrado!\n");
                    }
                    break;
                case 3:
                    scanner.nextLine(); // Limpa o buffer do teclado
                    System.out.print("Digite o nome do usuário: ");
                    nome = scanner.nextLine();
                    System.out.print("Digite o e-mail do usuário: ");
                    email = scanner.nextLine();
                    System.out.print("Digite a senha do usuário: ");
                    senha = scanner.nextLine();

                    usuarioService.salvar(new UsuarioDto(nome, email, senha));
                    System.out.println("Usuário cadastrado com sucesso!\n");
                    break;
                case 4:
                    System.out.print("Digite o Id do Usuário a ser atualizado: ");
                    idUsuario = scanner.next();
                    scanner.nextLine(); // Limpa o buffer do teclado

                    System.out.print("Digite o novo nome do usuário: ");
                    nome = scanner.nextLine();
                    System.out.print("Digite o novo e-mail do usuário: ");
                    email = scanner.nextLine();
                    System.out.print("Digite a nova senha do usuário: ");
                    senha = scanner.nextLine();

                    // Passando o DTO e o ID conforme a assinatura do seu método
                    usuarioService.atualizar(new UsuarioDto(nome, email, senha), idUsuario);
                    System.out.println("Usuário atualizado com sucesso!\n");
                    break;
                case 5:
                    System.out.print("Digite o Id do Usuário: ");
                    idUsuario = scanner.next();

                    boolean excluiu = usuarioService.excluir(idUsuario);
                    if(excluiu) {
                        System.out.println("Usuário excluído com sucesso!\n");
                    } else {
                        System.out.println("Erro: Usuário não encontrado para exclusão.\n");
                    }
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
                    break;
            }
        } while(opc != 6);
    }

    public void menuAgendamento() {
        do {
            System.out.println("" +
                    "---------Menu Agendamento--------\n" +
                    "1 - Listar Agendamentos\n" +
                    "2 - Buscar Agendamento (por ID)\n" +
                    "3 - Cadastrar Agendamento\n" +
                    "4 - Atualizar Agendamento\n" +
                    "5 - Excluir Agendamento\n" +
                    "6 - Voltar");
            opc = scanner.nextInt();

            // Variáveis baseadas no AgendamentoDto
            String idAgendamento;
            String pacienteId;
            String medicoId;
            String data;
            String hora;

            switch (opc) {
                case 1:
                    System.out.println("\n--- Lista de Agendamentos ---");
                    for (Agendamento agendamento : agendamentoService.buscarTodos()) {
                        System.out.println("Id do Agendamento: " + agendamento.getId());
                        System.out.println("Id do Paciente: " + agendamento.getPacienteId());
                        System.out.println("Id do Médico: " + agendamento.getMedicoId());
                        System.out.println("Data: " + agendamento.getData());
                        System.out.println("Hora: " + agendamento.getHora() + "\n");
                    }
                    break;
                case 2:
                    System.out.print("Digite o Id do Agendamento: ");
                    idAgendamento = scanner.next();

                    Agendamento agendamento = agendamentoService.buscarPorId(idAgendamento);

                    if (agendamento != null) {
                        System.out.println("\n--- Dados do Agendamento ---");
                        System.out.println("Id do Agendamento: " + agendamento.getId());
                        System.out.println("Id do Paciente: " + agendamento.getPacienteId());
                        System.out.println("Id do Médico: " + agendamento.getMedicoId());
                        System.out.println("Data: " + agendamento.getData());
                        System.out.println("Hora: " + agendamento.getHora() + "\n");
                    } else {
                        System.out.println("Agendamento não encontrado!\n");
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID do Paciente: ");
                    pacienteId = scanner.next();
                    System.out.print("Digite o ID do Médico: ");
                    medicoId = scanner.next();

                    scanner.nextLine(); // Limpa o buffer antes de ler os próximos textos

                    System.out.print("Digite a data do agendamento (ex: DD/MM/AAAA): ");
                    data = scanner.nextLine();
                    System.out.print("Digite a hora do agendamento (ex: HH:MM): ");
                    hora = scanner.nextLine();

                    agendamentoService.salvar(new AgendamentoDto(pacienteId, medicoId, data, hora));
                    System.out.println("Agendamento cadastrado com sucesso!\n");
                    break;
                case 4:
                    System.out.print("Digite o Id do Agendamento a ser atualizado: ");
                    idAgendamento = scanner.next();

                    System.out.print("Digite o novo ID do Paciente: ");
                    pacienteId = scanner.next();
                    System.out.print("Digite o novo ID do Médico: ");
                    medicoId = scanner.next();

                    scanner.nextLine(); // Limpa o buffer

                    System.out.print("Digite a nova data do agendamento: ");
                    data = scanner.nextLine();
                    System.out.print("Digite a nova hora do agendamento: ");
                    hora = scanner.nextLine();

                    // Passando o DTO e o ID conforme a assinatura do método atualizar
                    agendamentoService.atualizar(new AgendamentoDto(pacienteId, medicoId, data, hora), idAgendamento);
                    System.out.println("Agendamento atualizado com sucesso!\n");
                    break;
                case 5:
                    System.out.print("Digite o Id do Agendamento: ");
                    idAgendamento = scanner.next();

                    boolean excluiu = agendamentoService.excluir(idAgendamento);
                    if(excluiu) {
                        System.out.println("Agendamento excluído com sucesso!\n");
                    } else {
                        System.out.println("Erro: Agendamento não encontrado para exclusão.\n");
                    }
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
                    break;
            }
        } while(opc != 6);
    }

    public void login(){
        while(true) {
            System.out.println("Digite seu email: ");
            String email = scanner.nextLine();
            System.out.println("Digite seu senha: ");
            String senha = scanner.nextLine();
            Usuario usuario = usuarioService.buscarPorEmail(email);
            if (usuario.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso!\n");
                menu();
            }else {
                System.out.println("Senha incorreta!\n");
            }
        }
    }

    public void menu(){
        do{
            System.out.println("" +
                    "--------Menu--------\n" +
                    "1 - Agendamentos" +
                    "2 - Opcoes Pacientes\n" +
                    "3 - Opcoes Medicos\n" +
                    "4 - Opcoes Usuario" +
                    "5 - Sair");
            opc = scanner.nextInt();

            switch(opc){
                case 1:
                    menuAgendamento();
                    break;
                case 2:
                    menuPaciente();
                    break;
                case 3:
                    menuMedico();
                    break;
                case 4:
                    menuUsuario();
                    break;
                case 5:
                    System.out.print("Encerrando o sistema...");
                    System.exit(0);
            }
        }while (opc != 5);
    }
}
