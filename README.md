# ArmazenAI — Sistema de Controle e Gerenciamento de Estoque

**ArmazenAI** é um sistema desktop desenvolvido em **Java**, utilizando **Swing** para a interface gráfica, com persistência de dados em banco de dados relacional **MySQL** via **JDBC**. O projeto tem como objetivo oferecer uma solução local para pequenas e médias empresas realizarem o controle eficiente de seu estoque. Este projeto foi desenvolvido para avaliação A3 das cadeiras de Modelagem de Sotware e para de Soluções Computacionais.

## 📌 Índice

- [🔧 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [📚 Funcionalidades](#-funcionalidades)
- [⚙️ Como Executar](#️-como-executar)
- [📂 Estrutura de Pastas](#-estrutura-de-pastas)
- [📷 Capturas de Tela](#-capturas-de-tela)
- [📄 Requisitos](#-requisitos)
- [📝 Licença](#-licença)
- [📖 Referências](#-referências)

---

## 🔧 Tecnologias Utilizadas

- Java 8+
- Swing (Interface gráfica)
- JDBC (Conexão com banco de dados)
- MySQL (Banco de dados relacional)
- iTextPDF (Geração de relatórios em PDF)

---

## 📚 Funcionalidades

- Cadastro e gerenciamento de produtos
- Cadastro de categorias e fornecedores
- Movimentação de estoque (entradas e saídas)
- Alerta de estoque mínimo e validade
- Cadastro de administradores e operadores
- Controle de usuários com perfis distintos
- Geração de relatórios em PDF
- Interface amigável e intuitiva
- Operação totalmente local

---

## ⚙️ Como Executar

1. Clone este repositório:
```bash
git clone https://github.com/devrgomes/armazenai.git
```
   
2. Importe o projeto na IDE NetBeans (ou Eclipse/IntelliJ, se preferir).

- Configure o banco de dados:

- Crie o banco e execute os scripts SQL da pasta /database/.

3. Atualize as credenciais de acesso ao banco na classe de conexão.

4. Execute a aplicação pela classe principal "Main.java".

---

## 📂 Estrutura de Pastas
```bash
/armazenai
├── /src
│   ├── /model
│   ├── /view
│   ├── /controller
│   ├── /util
│   └── Main.java
├── /database
│   └── script.sql
├── /relatorios
│   ├── templates/
│   └── documento.pdf
└── README.md
```
---

## 📷 Capturas de Tela

---

## 📄 Requisitos

### Requisitos Funcionais:

- RF001. O sistema deve permitir o cadastro de novos produtos com nome, código, categoria, fornecedor, quantidade, lote (opcional) e data de validade (opcional).
- RF002. O sistema deve validar a unicidade do nome e código do produto para evitar duplicidade de registros.
- RF003. O sistema deve permitir o cadastro, edição e exclusão de categorias de produto.
- RF004. O sistema deve permitir o cadastro, edição e exclusão de fornecedores.
- RF005. O sistema deve impedir a exclusão de categorias ou fornecedores vinculados a produtos ativos.
- RF006. O sistema deve permitir o registro de movimentações de entrada e saída de produtos com data, hora e quantidade.
- RF007. O sistema deve atualizar automaticamente o estoque do produto conforme as movimentações realizadas.
- RF008. O sistema deve impedir movimentações de saída quando a quantidade disponível for inferior à solicitada.
- RF009. O sistema deve emitir alertas visuais para produtos com estoque abaixo do nível mínimo definido.
- RF010. O sistema deve emitir alertas visuais para produtos vencidos ou com data de validade próxima.
- RF011. O sistema deve permitir a organização de produtos por lote, possibilitando rastreamento detalhado.
- RF012. O sistema deve permitir a visualização de um histórico completo de movimentações por produto.
- RF013. O sistema deve gerar relatórios em formato PDF com listagens de produtos, movimentações, produtos com estoque crítico, vencidos e por fornecedores ou categorias.
- RF014. O sistema deve possuir uma tela de resumo com estatísticas básicas (total de produtos, produtos com estoque baixo, movimentações do mês, etc.).
- RF015. O sistema deve oferecer um campo de busca com filtros por nome, categoria, fornecedor, estoque mínimo e validade.
- RF016. O sistema deve permitir o cadastro de usuários com perfis distintos (administrador e operador).
- RF017. O sistema deve restringir funcionalidades conforme o perfil do usuário autenticado.
- RF018. O sistema deve exibir mensagem de acesso negado sempre que um usuário tentar executar uma ação não permitida ao seu perfil.
- RF019. O sistema deve solicitar confirmação do usuário antes de executar ações críticas como exclusão de registros.

### Requisitos Não Funcionais:

- RNF001: O sistema deve ser desenvolvido exclusivamente na linguagem de programação Java.
- RNF002: A interface gráfica do sistema deve ser construída utilizando a biblioteca Swing, assegurando compatibilidade com versões modernas do Java SE.
- RNF003: O sistema deve adotar os princípios da programação orientada a objetos, promovendo modularidade, reutilização de código e facilidade de manutenção.
- RNF004: O sistema deve utilizar a tecnologia JDBC para gerenciar conexões com o banco de dados, otimizando o acesso e a persistência dos dados.
- RNF005: As operações de leitura, gravação e atualização no banco de dados (MySQL ou Oracle) devem ser executadas em no máximo 5 segundos sob carga normal.
- RNF006: O sistema deve responder a qualquer ação do usuário em no máximo 5 segundos durante a execução local.
- RNF007: A interface gráfica deve ser intuitiva, com organização clara dos elementos, uso de cores padronizadas e feedback visual para ações do usuário.
- RNF008: O sistema deve utilizar fontes legíveis (mínimo 12px) e contraste adequado entre texto e fundo, facilitando o uso por pessoas com dificuldades visuais leves.
- RNF009: Mensagens de erro, sucesso e advertência devem ser exibidas de forma clara e descritiva, indicando a ação que causou o evento e sugerindo soluções quando aplicável.
- RNF010: O sistema deve impedir que o valor de estoque de um produto seja inferior a zero, realizando validações antes de cada movimentação.
- RNF011: O sistema deve bloquear movimentações de estoque que violem regras de integridade como categoria inválida, produto inexistente ou operador inativo.
- RNF012: O sistema deve prevenir falhas inesperadas por meio de tratamento de exceções robusto e logs de erro locais.
- RNF013: Toda a funcionalidade do sistema deve ser mantida independentemente da conexão com a internet, funcionando completamente em modo local.
- RNF014: O sistema deve permitir exportação de relatórios em formato PDF por meio de bibliotecas como iText, JasperReports ou similares.

---

## 📝 Licença
Este projeto está licenciado sob a [MIT License](https://opensource.org/license/MIT).

---

## 📖 Referências

- MARTINS, Walteno. Estrutura de documentos de projetos de software. Disponível em: [clique aqui](http://www.waltenomartins.com.br/esof2_projeto_documento_de_sw.pdf).
- SOUZA, Thiago. Como criar o DER – Diagrama Entidade Relacional. Disponível em: [clique aqui](https://programandoesalvando.wordpress.com/2016/03/30/como-criar-o-der-diagrama-entidade-relacional/).
- BRASIL. Associação Brasileira de Normas Técnicas. NBR 6023: Informação e documentação – Referências – Elaboração. Rio de Janeiro, 2018.
