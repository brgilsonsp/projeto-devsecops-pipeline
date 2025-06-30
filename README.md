# DevSecOps Pipeline - Documentação dos Workflows GitHub Actions

Este projeto utiliza uma pipeline DevSecOps automatizada com **GitHub Actions** para garantir qualidade, segurança e automação no ciclo de vida de desenvolvimento.  
A seguir, você encontrará a explicação detalhada de cada workflow e dos principais pontos de implementação.

---

## Estrutura dos Workflows

Os workflows estão localizados em `.github/workflows/` e são divididos por contexto de branch e objetivo:

- **ci-feature.yml**: CI para branches de feature
- **ci-cd-develop.yml**: CI/CD para branch `develop`
- **ci-cd-main.yml**: CI/CD para branch `main`

Além disso, há actions customizadas no diretório `.github/actions/` para variáveis de ambiente e configuração de Docker.

---

## 1. `ci-feature.yml` - Integração Contínua para Branches de Feature

### Quando é executado?
- Em todo `push` para branches que seguem o padrão `feature/*`.

### O que faz?

1. **Checkout do código**  
   Baixa o código da branch atual para o runner.

2. **Seta variáveis de pipeline**  
   Executa uma action local para definir variáveis de ambiente usadas nos próximos steps.

3. **Verifica se a branch está atrás de `develop` ou `main`**  
   - Se a branch estiver desatualizada, a pipeline é interrompida e o desenvolvedor é avisado para atualizar sua branch.

4. **Configura o JDK 21**  
   Garante ambiente Java consistente para build e testes.

5. **Cache do Maven**  
   Acelera builds reutilizando dependências já baixadas.

6. **Lint (Checkstyle)**  
   Executa análise de estilo de código.  
   - Se houver erros, a pipeline pode ser interrompida, a menos que a variável `BYPASS_LINT` esteja como `true`.

7. **Dependency Review**  
   Usa a action oficial do GitHub para verificar se novas dependências adicionadas possuem vulnerabilidades conhecidas.

8. **Testes Unitários**  
   Executa todos os testes unitários do projeto.

9. **Build da aplicação**  
   Gera o artefato final do projeto.

10. **Geração da lista de commits**  
    Coleta as mensagens dos commits feitos na branch de feature em relação à `develop`.

11. **Criação/atualização de Pull Request para `develop`**  
    Utiliza a action `devops-infra/action-pull-request` para abrir (ou atualizar) um PR da branch de feature para `develop`, com o título e o corpo contendo a lista de commits.

---

## 2. `ci-cd-develop.yml` - CI/CD para Branch `develop`

### Quando é executado?
- Em todo `push` para a branch `develop`.

### O que faz?

1. **Checkout do código**
2. **Seta variáveis de pipeline**
3. **Configura JDK 21**
4. **Cache do Maven**
5. **Lint (Checkstyle)**
6. **Testes Unitários**
7. **Build da aplicação**
8. **Seta variáveis de Docker**
9. **Define tags da imagem Docker**
10. **Build da imagem Docker**
11. **Push da imagem Docker para o repositório**
    - Exibe no console o endereço do repositório Docker onde a imagem foi enviada.

---

## 3. `ci-cd-main.yml` - CI/CD para Branch `main` (Produção)

### Quando é executado?
- Em todo `push` para a branch `main`.

### O que faz?

1. **Checkout do código**
2. **Seta variáveis de pipeline**
3. **Configura JDK 21**
4. **Cache do Maven**
5. **Lint (Checkstyle)**
6. **Testes Unitários**
7. **Build da aplicação**
8. **Seta variáveis de Docker**
9. **Define tags da imagem Docker**  
   - Tag de versão (`v${GITHUB_RUN_NUMBER}`)  
   - Tag `latest`
10. **Build da imagem Docker**
11. **Push da imagem Docker para o repositório**  
    - Faz push tanto da tag de versão quanto da tag `latest`.
12. **Exibe no console o endereço do repositório Docker**
13. **Deploy para produção**  
    - (Simulado, pode ser adaptado para deploy real)

---

## 4. Actions customizadas

### `.github/actions/vars-pipe`
- Define variáveis de ambiente globais para uso nos workflows.

### `.github/actions/vars-docker`
- Define variáveis específicas para build e push de imagens Docker.

---

## 5. Segurança

- **Dependency Review**:  
  Todos os PRs de feature passam por análise de dependências para evitar inclusão de vulnerabilidades conhecidas.
- **Lint e Testes**:  
  O código só avança se passar por validação de estilo e testes automatizados.
- **Controle de atualização de branch**:  
  Nenhuma feature pode ser integrada se estiver atrás de `develop` ou `main`.

---

## 6. Observações e Dicas

- O token `${{ secrets.GITHUB_TOKEN }}` precisa de permissão de escrita para criar/atualizar PRs e fazer push de imagens Docker.
- O body do PR é atualizado apenas na criação; para atualizar em PRs já existentes, use um step extra com a GitHub CLI.
- O deploy para produção é apenas um placeholder.

---

## 7. Sobre a aplicação de exemplo (`./app`)

O diretório `./app` contém uma aplicação de exemplo, criada **exclusivamente para fins de validação e demonstração da pipeline DevSecOps**.

### Objetivo

- Servir como base para execução dos workflows de CI/CD.
- Permitir a validação de etapas como build, testes, análise de código (lint), revisão de dependências e empacotamento Docker.
- Não possui finalidade de negócio ou uso em produção.

### Estrutura típica

A aplicação segue uma estrutura padrão de projetos Java com Maven, incluindo:

- `pom.xml`: arquivo de configuração do Maven, com dependências e plugins necessários para build, testes e análise de código.
- `src/main/java/`: código-fonte principal da aplicação.
- `src/test/java/`: testes unitários para validação automática.
- Arquivos de configuração para ferramentas de lint (ex: Checkstyle).

### Funcionalidades

- Implementa funcionalidades básicas apenas para permitir a execução dos testes e validações automatizadas.
- Inclui exemplos de classes, métodos e testes para garantir que todas as etapas da pipeline possam ser exercitadas.

### Observações

- **Não utilize esta aplicação como base para projetos reais.**
- O código pode conter exemplos simplificados ou artificiais, focados apenas em acionar os workflows e validar o funcionamento da pipeline.

---

## 8. Sobre o Dockerfile (`./Dockerfile`)

O arquivo `Dockerfile` localizado na raiz do projeto é responsável por definir o processo de **containerização da aplicação de exemplo** deste projeto.

### Objetivo

- Permitir a criação de uma imagem Docker da aplicação para testes, validação e publicação em repositórios de imagens.
- Servir como base para os workflows de CI/CD que realizam o build, push e deploy da aplicação em ambientes isolados.
- Não possui otimizações avançadas, pois seu foco é a validação da pipeline.

### Estrutura típica

O `Dockerfile` segue um padrão comum para aplicações Java com Maven, incluindo:

- **Imagem base:**  
  Utiliza uma imagem oficial do Java (ex: `openjdk:21-jdk-slim`) para garantir compatibilidade com o ambiente de build e execução.
- **Cópia dos artefatos:**  
  Copia o arquivo `.jar` gerado pelo Maven (`target/*.jar`) para dentro do container.
- **Definição do entrypoint:**  
  Define o comando padrão para iniciar a aplicação Java ao rodar o container.
