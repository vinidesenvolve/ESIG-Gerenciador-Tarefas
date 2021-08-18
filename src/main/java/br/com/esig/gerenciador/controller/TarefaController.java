package br.com.esig.gerenciador.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.esig.gerenciador.model.Tarefa;
import br.com.esig.gerenciador.repository.TarefaRepository;
import br.com.esig.gerenciador.util.JpaUtil;

@Named(value = "tarefaController")
@RequestScoped
public class TarefaController {

	@Inject
	private Tarefa tarefa;

	private Long tarefaId;

	private String situacaoBusca;

	private List<Tarefa> tarefas = new ArrayList<>();

	@PostConstruct
	public void postConstruct() {
		buscarTodas();
		
		String tarefaIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("tarefaId");

		if (tarefaIdParam != null) {
			tarefaId = Long.parseLong(tarefaIdParam);
			tarefa = buscarPorId(tarefaId);
		}
	}

	public void buscarTodas() {
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

	//Pesquisar por descrição
	//Bugs depois da pesquisa para usar excluir e concluir, está enviando id de outra tarefa
	
	public void buscarTarefas() {
		EntityManager entityManager = JpaUtil.getEntityManager();

		Long idBusca = tarefa.getId();
		
		try {

			if (idBusca != null) {
				this.tarefas.clear();
				this.tarefas.add(buscarPorId(idBusca));
				return;
			}
			
			if(situacaoBusca.equals("todas")) {
				buscarTodas();
				return;
			}
			
			if(situacaoBusca.equals("concluida")) {
				tarefa.setisConcluida(true);
			}else {
				tarefa.setisConcluida(false);
			}
			
			TarefaRepository tarefaRepo = new TarefaRepository(entityManager);
			this.tarefas = tarefaRepo.buscarTodas().stream()
					.filter(t -> t.getisConcluida() == tarefa.getisConcluida())
					.filter(t -> t.getTitulo().contains(tarefa.getTitulo()))
					//.filter(t -> t.getDescricao().contains(tarefa.getTitulo()))
					.filter(t -> t.getResponsavel().contains(tarefa.getResponsavel()))
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

	public String salvar() {

		EntityManager entityManager = JpaUtil.getEntityManager();

		try {

			TarefaRepository tarefaRepo = new TarefaRepository(entityManager);
			tarefaRepo.adicionar(tarefa);

		} catch (Exception e) {
			throw (e);
		} finally {
			entityManager.close();

		}

		return "/cadastro.xhtml?faces-redirect=true";
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

		return reloadConsulta();//"/consulta.xhtml?faces-redirect=true";
	}

	public void concluir(Long id) {
		EntityManager entityManager = JpaUtil.getEntityManager();

		try {
			System.out.println(id);
//			TarefaRepository tarefaRepo = new TarefaRepository(entityManager);
//			tarefaRepo.concluir(id);

		} catch (Exception e) {
			throw (e);
		} finally {
			entityManager.close();

		}

		//return reloadConsulta();//"/consulta.xhtml?faces-redirect=true";
	}

	public String excluir(Long id) {
		EntityManager entityManager = JpaUtil.getEntityManager();

		try {
			System.out.println(id);
//			TarefaRepository tarefaRepo = new TarefaRepository(entityManager);
//			tarefaRepo.apagar(id);

		} catch (Exception e) {
			throw (e);
		} finally {
			entityManager.close();
		}

		return reloadConsulta();//"/consulta.xhtml?faces-redirect=true";
	}

	public String reloadConsulta() {
		return "/consulta.xhtml?faces-redirect=true";
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

	public Long getTarefaId() {
		return tarefaId;
	}

	public void setTarefaId(Long tarefaId) {
		this.tarefaId = tarefaId;
	}
}
