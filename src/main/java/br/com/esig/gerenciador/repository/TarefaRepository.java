package br.com.esig.gerenciador.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import br.com.esig.gerenciador.model.Tarefa;

public class TarefaRepository {

	private EntityManager entityManager;

	public TarefaRepository(EntityManager em) {
		this.entityManager = em;
	}

	public List<Tarefa> buscarTodas() {

		List<Tarefa> tarefas = entityManager.createQuery("select t from Tarefa as t").getResultList();
		return tarefas;
	}

	public Tarefa buscarId(Long id) {

		Tarefa tarefa = entityManager.find(Tarefa.class, id);
		return tarefa;
	}

	public void adicionar(Tarefa tarefa) {

		entityManager.getTransaction().begin();
		entityManager.persist(tarefa);
		entityManager.getTransaction().commit();
	}

	public void atualizar(Tarefa tarefa) {

		entityManager.getTransaction().begin();
		entityManager.merge(tarefa);
		entityManager.getTransaction().commit();
	}

	public void apagar(Long id) {

		Tarefa tarefa = entityManager.find(Tarefa.class, id);

		entityManager.getTransaction().begin();
		entityManager.remove(tarefa);
		entityManager.getTransaction().commit();
	}

	public void concluir(Long id) {

		Tarefa tarefa = entityManager.find(Tarefa.class, id);

		entityManager.getTransaction().begin();
		tarefa.setisConcluida(true);
		entityManager.getTransaction().commit();
	}
}
