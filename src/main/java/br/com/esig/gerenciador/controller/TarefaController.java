package br.com.esig.gerenciador.controller;

import java.util.ArrayList;
import java.util.List;

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

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TarefasPU");
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Inject
	Tarefa tarefa;

	public void salvar() {
		
		entityManager.getTransaction().begin();
		entityManager.persist(tarefa);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
	}

	private List<Tarefa> tarefas = new ArrayList<>();

	public void salvar2() {
		tarefas.add(tarefa);
		System.out.println("Tarefa: " + tarefa.getTitulo());
		this.tarefa = new Tarefa();
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
