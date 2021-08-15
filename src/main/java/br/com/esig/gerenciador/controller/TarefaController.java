package br.com.esig.gerenciador.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.esig.gerenciador.model.Tarefa;

@Named(value = "tarefaController")
@RequestScoped
public class TarefaController {

	@Inject
	private Tarefa tarefa;

	private List<Tarefa> tarefas = buscarTodas();
	
	public void salvar() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TarefasPU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		tarefa.setisConcluida(false);
		entityManager.persist(tarefa);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

		buscarTodas();
	}

	public List<Tarefa> buscarTodas() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TarefasPU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		List<Tarefa> tarefasDB = entityManager.createQuery("select t from Tarefa as t").getResultList();
		
		entityManager.close();
		entityManagerFactory.close();
		
		return tarefasDB;
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

}
