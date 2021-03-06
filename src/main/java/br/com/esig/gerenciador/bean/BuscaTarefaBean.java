package br.com.esig.gerenciador.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.esig.gerenciador.model.Tarefa;
import br.com.esig.gerenciador.repository.TarefaRepository;
import br.com.esig.gerenciador.util.JpaUtil;

import org.apache.commons.lang3.StringUtils;

@Named(value = "buscaTarefaBean")
@RequestScoped
public class BuscaTarefaBean {

	@Inject
	private Tarefa tarefa;

	private String situacaoBusca;

	private List<Tarefa> tarefas = new ArrayList<>();

	@PostConstruct
	public void postConstruct() {

		loadTarefas();
	}

	public void loadTarefas() {
		EntityManager entityManager = JpaUtil.getEntityManager();

		try {

			TarefaRepository tarefaRepo = new TarefaRepository(entityManager);
			this.tarefas = tarefaRepo.buscarTodas();

		} catch (Exception e) {
			throw (e);
		} finally {
			entityManager.close();
		}

	}

	public void buscarTarefas() {
		EntityManager entityManager = JpaUtil.getEntityManager();

		Long idBusca = tarefa.getId();

		try {

			if (idBusca != null) {
				this.tarefas.clear();
				this.tarefas.add(buscarPorId(idBusca));
				return;
			}

			if (situacaoBusca.equals("concluida")) {
				this.tarefas = buscarTodas().stream().filter(t -> t.getisConcluida()).collect(Collectors.toList());
				;
				return;
			}

			if (situacaoBusca.equals("andamento")) {
				this.tarefas = buscarTodas().stream().filter(t -> !t.getisConcluida()).collect(Collectors.toList());
				return;
			}

			this.tarefas = buscarTodas();

		} catch (Exception e) {
			throw (e);
		} finally {
			entityManager.close();
		}

	}

	public List<Tarefa> buscarTodas() {
		EntityManager entityManager = JpaUtil.getEntityManager();

		try {

			TarefaRepository tarefaRepo = new TarefaRepository(entityManager);
			return tarefaRepo.buscarTodas().stream()
					.filter(t -> StringUtils.containsIgnoreCase(t.getResponsavel(), tarefa.getResponsavel()))
					.filter(t -> StringUtils.containsIgnoreCase(t.getTitulo(), tarefa.getTitulo()))
					.filter(t -> StringUtils.containsIgnoreCase(t.getDescricao(), tarefa.getTitulo()))
					.collect(Collectors.toList());

		} catch (Exception e) {
			throw (e);
		} finally {
			entityManager.close();
		}

	}

	public Tarefa buscarPorId(Long id) {
		EntityManager entityManager = JpaUtil.getEntityManager();

		try {

			TarefaRepository tarefaRepo = new TarefaRepository(entityManager);
			return tarefaRepo.buscarId(id);

		} catch (Exception e) {
			throw (e);
		} finally {
			entityManager.close();
		}
	}

	// GETTERS & SETTERS
	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public String getSituacaoBusca() {
		return situacaoBusca;
	}

	public void setSituacaoBusca(String situacaoBusca) {
		this.situacaoBusca = situacaoBusca;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

}
