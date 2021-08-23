package br.com.esig.gerenciador.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.esig.gerenciador.model.Tarefa;
import br.com.esig.gerenciador.repository.TarefaRepository;
import br.com.esig.gerenciador.util.JpaUtil;
import br.com.esig.gerenciador.util.Navigation;

@Named(value = "manipulacaoTarefaBean")
@RequestScoped
public class ManipulacaoTarefaBean {

	@Inject
	private Tarefa tarefa;

	private Long tarefaId;

	@PostConstruct
	public void postConstruct() {
		
		String tarefaIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("tarefaId");

		if (tarefaIdParam != null) {
			tarefaId = Long.parseLong(tarefaIdParam);
			tarefa = buscarPorId(tarefaId);
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
	
	public String editar() {
		EntityManager entityManager = JpaUtil.getEntityManager();

		try {

			TarefaRepository tarefaRepo = new TarefaRepository(entityManager);
			tarefaRepo.atualizar(tarefa);

		} catch (Exception e) {
			throw (e);
		} finally {
			entityManager.close();
		}
		
		tarefaId = null;
		return Navigation.loadConsulta();
	}
	
	public String excluir() {
		EntityManager entityManager = JpaUtil.getEntityManager();

		try {
			TarefaRepository tarefaRepo = new TarefaRepository(entityManager);
			tarefaRepo.apagar(tarefa.getId());

		} catch (Exception e) {
			throw (e);
		} finally {
			entityManager.close();
		}

		tarefaId = null;
		return Navigation.loadConsulta();
	}

	public String concluir() {
		EntityManager entityManager = JpaUtil.getEntityManager();

		try {
			TarefaRepository tarefaRepo = new TarefaRepository(entityManager);
			tarefaRepo.concluir(tarefa.getId());

		} catch (Exception e) {
			throw (e);
		} finally {
			entityManager.close();

		}

		tarefaId = null;
		return Navigation.loadConsulta();
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	public Long getTarefaId() {
		return tarefaId;
	}

	public void setTarefaId(Long tarefaId) {
		this.tarefaId = tarefaId;
	}
}
