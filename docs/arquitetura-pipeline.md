# Arquitetura do Pipeline DevSecOps

Este documento descreve a arquitetura do pipeline DevSecOps implementado para o projeto. O objetivo é garantir a segurança e a qualidade do código em todas as etapas do desenvolvimento e implantação.

## Estrutura do Pipeline

O pipeline é dividido em várias etapas, cada uma com suas responsabilidades específicas:

1. **CI para Branches de Feature**
   - Verifica se a branch está atualizada em relação às branches `develop` e `main`.
   - Realiza verificações de lint.
   - Executa testes unitários.
   - Realiza varreduras de segurança.

2. **CI/CD para Branch Develop**
   - Verifica se a branch `develop` está atualizada em relação à branch `main`.
   - Executa verificações de lint e testes unitários.
   - Realiza testes de integração.
   - Constrói a aplicação.

3. **CD para Branch Main**
   - Faz checkout do código.
   - Baixa artefatos da construção anterior.
   - Re-tagging da imagem Docker.
   - Realiza a implantação em produção.

## Ferramentas de Segurança

O pipeline utiliza várias ferramentas para garantir a segurança do código:

- **SonarQube**: Para análise de qualidade de código e detecção de vulnerabilidades.
- **OWASP Dependency Check**: Para identificar vulnerabilidades em dependências.
- **GitLeaks**: Para detectar segredos expostos no repositório.

## Estrutura do Projeto

O projeto é estruturado da seguinte forma:

- **./app/src/main/java**: Contém o código-fonte da aplicação Spring Boot.
- **./app/src/main/resources**: Contém arquivos de configuração e recursos estáticos.
- **security**: Contém arquivos de configuração para ferramentas de segurança.
- **.github/workflows**: Contém definições de workflows para CI/CD.
- **Dockerfile**: Define a construção da imagem Docker para a aplicação.

## Conclusão

A arquitetura do pipeline DevSecOps foi projetada para integrar práticas de segurança em cada etapa do desenvolvimento e implantação, garantindo que o código seja seguro e de alta qualidade antes de ser disponibilizado em produção.