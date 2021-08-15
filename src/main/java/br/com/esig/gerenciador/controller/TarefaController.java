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

	public String salvar() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TarefasPU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		tarefa.setisConcluida(false);
		entityManager.persist(tarefa);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

		return "/cadastro.xhtml?faces-redirect=true";
	}

	public List<Tarefa> buscarTodas() {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TarefasPU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		List<Tarefa> tarefasDB = entityManager.createQuery("select t from Tarefa as t").getResultList();

		entityManager.close();
		entityManagerFactory.close();

		return tarefasDB;
	}

	// GETS...
	public List<Tarefa> buscarConcluidas() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TarefasPU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		List<Tarefa> tarefasConcluidas = entityManager.createQuery("select t from Tarefa as t where t.isConcluida=false").getResultList();

		entityManager.close();
		entityManagerFactory.close();

		return tarefas;
	}
	
	public void buscarPorId(Tarefa tarefa) {
		System.out.println(tarefa.getId());

	}
	
	// PUT
	public String editar(Tarefa tarefaAtualizada) {
		
		System.out.println(tarefaAtualizada.getId());
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TarefasPU");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//		Tarefa tarefa = entityManager.find(Tarefa.class, tarefaAtualizada.getId());
//
//		entityManager.getTransaction().begin();
//
//		tarefa.setTitulo(tarefaAtualizada.getTitulo());
//		tarefa.setDescricao(tarefaAtualizada.getDescricao());
//		tarefa.setResponsavel(tarefaAtualizada.getResponsavel());
//		tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
//		tarefa.setDeadline(tarefaAtualizada.getDeadline());
//
//		entityManager.getTransaction().commit();
//
//		entityManager.close();
//		entityManagerFactory.close();

		return "/consulta.xhtml?faces-redirect=true";
	}

	public String concluir(Long id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TarefasPU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Tarefa tarefa = entityManager.find(Tarefa.class, id);

		entityManager.getTransaction().begin();
		tarefa.setisConcluida(true);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

		return "/consulta.xhtml?faces-redirect=true";
	}

	public String excluir(Long id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TarefasPU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Tarefa tarefa = entityManager.find(Tarefa.class, id);

		entityManager.getTransaction().begin();
		entityManager.remove(tarefa);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
		
		return "/consulta.xhtml?faces-redirect=true";
	}

	// GETTERS & SETTERS
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
