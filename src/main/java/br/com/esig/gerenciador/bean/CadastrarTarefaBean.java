package br.com.esig.gerenciador.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.esig.gerenciador.model.Tarefa;
import br.com.esig.gerenciador.repository.TarefaRepository;
import br.com.esig.gerenciador.util.JpaUtil;

@Named(value = "cadastrarTarefaBean")
@RequestScoped
public class CadastrarTarefaBean {
	
	@Inject
	private Tarefa tarefa;
	
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

		return "/consulta.xhtml?faces-redirect=true";
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	
}
