# ArmazenAI — Sistema de Controle e Gerenciamento de Estoque

**ArmazenAI** é um sistema desktop desenvolvido em **Java**, utilizando **Swing** para a interface gráfica, com persistência de dados em banco de dados relacional **MySQL** via **JDBC**. O projeto tem como objetivo oferecer uma solução local para pequenas e médias empresas realizarem o controle eficiente de seu estoque. Este projeto foi desenvolvido para avaliação A3 das cadeiras de Modelagem de Sotware e para de Soluções Computacionais.

## 📌 Índice

- [🔧 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [📚 Funcionalidades](#-funcionalidades)
- [⚙️ Como Executar](#️-como-executar)
- [📂 Estrutura de Pastas](#-estrutura-de-pastas)
- [📷 Capturas de Tela](#-capturas-de-tela)
- [📄 Documentação](#-documentação)
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

3. Configure o banco de dados:

- Crie o banco e execute os scripts SQL da pasta /database/.

- Atualize as credenciais de acesso ao banco na classe de conexão.

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

## 📚 Documentação

Para mais detalhes sobre os requisitos, diagramas, casos de uso e estruturas do sistema:

📄 [Acesse a documentação completa do projeto](relatorios/documento.pdf).

---

## 📝 Licença
Este projeto está licenciado sob a [MIT License](https://opensource.org/license/MIT).

---

## 📖 Referências

- MARTINS, Walteno. Estrutura de documentos de projetos de software. Disponível em: [clique aqui](http://www.waltenomartins.com.br/esof2_projeto_documento_de_sw.pdf).
- SOUZA, Thiago. Como criar o DER – Diagrama Entidade Relacional. Disponível em: [clique aqui](https://programandoesalvando.wordpress.com/2016/03/30/como-criar-o-der-diagrama-entidade-relacional/).
- BRASIL. Associação Brasileira de Normas Técnicas. NBR 6023: Informação e documentação – Referências – Elaboração. Rio de Janeiro, 2018.
