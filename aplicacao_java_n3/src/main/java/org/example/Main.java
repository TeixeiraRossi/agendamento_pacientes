package org.example;

import org.example.database.*;
import org.example.dto.AgendamentoDto;
import org.example.dto.MedicoDto;
import org.example.dto.PacienteDto;
import org.example.entities.Medico;
import org.example.entities.Paciente;
import org.example.repositories.AgendamentoRepository;
import org.example.repositories.MedicoRepository;
import org.example.repositories.PacienteRepository;
import org.example.repositories.UsuarioRepository;
import org.example.services.*;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Migracao.gerarTabelas();
        PacienteRepository pacienteRepository = new PacienteRepositoryJBDC(Conexao.getInstancia());
        MedicoRepository medicoRepository = new MedicoRepositoryJBDC(Conexao.getInstancia());
        AgendamentoRepository agendamentoRepository = new AgendamentoRepositoryJBDC(Conexao.getInstancia());
        UsuarioRepository usuarioRepository = new UsuarioRepositoryJBDC(Conexao.getInstancia());


        PacienteService pacienteService = new PacienteService(pacienteRepository);
        MedicoService medicoService = new MedicoService(medicoRepository);
        AgendamentoService agendamentoService = new AgendamentoService(agendamentoRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(medicoService, pacienteService, agendamentoService, usuarioService, scanner);

        menu.login();
        /*
        Paciente paciente = pacienteService.salvar(
                new PacienteDto(
                        "Guilherme FDS",
                        1,
                        "89698712309",
                        "31-08-2005",
                        "6698123456"
                )
        );
        Medico medico = medicoService.salvar(
                new MedicoDto(
                        "Dr.Belzebu da Silva",
                        "Urologista",
                        "666.666.666-66"
                )
        );
        agendamentoService.salvar(
                new AgendamentoDto(
                        paciente.getId().toString(),
                        medico.getId().toString(),
                        "09-06-2026",
                        "12:00"
                )
        );

        System.out.println("PACIENTES");
        pacienteService.buscarTodos().forEach(p -> System.out.println(p.getId()+ " | "+p.getNome()));

        System.out.println("MEDICOS");
        medicoService.buscarTodos().forEach(m -> System.out.println(m.getId()+ " | "+m.getNome()));

        System.out.println("AGENDAMENTOS");
        agendamentoService.buscarTodos().forEach(a -> System.out.println(a.getId()+ " | "+a.getData()+ " "+ a.getHora()));

         */
    }
}