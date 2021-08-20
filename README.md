# ESIG-Gerenciador-Tarefas

## Descrição

Aplicação CRUD para gerenciar tarefas.

Desenvolvida com JPA, PostgreSQL, JSF e PrimeFaces

## Índice
<p align="center"> - 
 <a href="#status">Status</a> - 
 <a href="#status"> Funcionalidades </a> - 
 <a href="#requisitos">Requisitos</a> - 
 <a href="#executando">Executando</a> - 
 <a href="#tecnologias">Tecnologias</a> - 
 <a href="#autor">Autor</a> - 
 <a href="#licença">Licença</a> - 
</p>


https://user-images.githubusercontent.com/75989911/129587942-2f757699-a46b-49a6-98b0-0fc74e0eaf61.mp4


## Status 

Em desenvolvimento

## Funcionalidades

- [x] Cadastrar Tarefa
- [x] Buscar Todas Tarefas
- [x] Editar Tarefa
- [x] Apagar Tarefa
- [x] Concluir Tarefa
- [x] Pesquisar Tarefa

## Requisitos

### Ferramentas necessárias

Instale essas ferramnetas em sua máquina (caso não as tenha)

- [Git](https://git-scm.com)
- [Java 8+](https://www.java.com/en/)
- [PostgreSQL](https://www.postgresql.org/) instalar pgAdmin pode ajudar na manipulação* 
- [Apache TomCat](http://tomcat.apache.org/) recomendo usar as versões entre 7 ~ 9*
- [Eclipse IDE](https://www.eclipse.org/) ou outra IDE da sua preferência com suporte para Java*

### Clone o repositório

Baixe o arquivo compactado ou faça um clone do git

No terminal ou prompt de comando dentro da pasta que deseja colocar o projeto

<code> $ git clone <https://github.com/vinidesenvolve/ESIG-Gerenciador-Tarefas.git> </code>

## Executando

### Ambiente de Banco de Dados

  No Postgres crie um banco de dados CREATE DATABASE nomeDaBaseDeDados
  
  Em src/main/reources/META-INF/persistence.xml
  
  Atente-se as seguintes propriedades e configure-as para o seu ambiente: 
  
      jdbc:postgresql://localhost/nomeDaBaseDeDados aqui está configurado para a porta padrão*
      
      javax.persistence.jdbc.user value="UsuarioPostgres"
      
      javax.persistence.jdbc.password value="SenhaUsuarioPostgres"
      
### Servidor

    Configure o servidor Tomcat na sua IDE
    
    No caso do Eclipse adicione o projeto a este servidor e execute-o
    
    Acesse: <http://localhost:8080/gerenciador/>

## Tecnologias

- [JSF](https://www.oracle.com/java/technologies/javaserverfaces.html)  
- [Java](https://www.java.com/en/)
- [PrimeFaces](https://www.primefaces.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [JPA](https://jakarta.ee/specifications/persistence/3.0/)

## Autor

<p> <a href="https://github.com/vinidesenvolve">Vinicius Alves Rodrigues</a> </p>
<p> <a href="https://www.linkedin.com/in/vinidesenvolve/">Likedin</a> </p>
<p> <a href="vinidesenvolve@gmail.com">Email</a> </p>

## Licença

<p align="center">
MIT License

Copyright (c) 2021 Vinicius A. Rodrigues

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
</p>
