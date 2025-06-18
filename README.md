# Sistema de Presença Escolar com QR Code

Este é um sistema de controle de presença desenvolvido em **Java (Swing)** com banco de dados **MySQL**, voltado para uso em instituições de ensino. Ele permite que a escola cadastre alunos e que os próprios alunos gerem e escaneiem QR Codes para registrar sua entrada e saída nas aulas.

## 📋 Funcionalidades

- ✅ Cadastro de alunos pela escola
- 🔐 Login de alunos com usuário e senha
- 📲 Geração de QR Code único pelo aluno
- 📷 Leitura do QR Code para registrar **entrada e saída**
- 📅 Verificação automática da data,hora e localização
- 📊 Registro de presença salvo no banco de dados
- 🔍 Consulta das presenças no sistema

## 🧩 Tecnologias Utilizadas

- **Java (Swing)** — interface gráfica
- **MySQL** — banco de dados relacional
- **ZXing** — biblioteca para leitura e geração de QR Codes
- **NetBeans** — ambiente de desenvolvimento

## 📁 Estrutura do Projeto

```
├── Cadastro.java / .form             → Tela de cadastro de alunos
├── Login.java / .form                → Tela de login
├── Form_Menu.java / .form           → Menu principal do sistema
├── Form_Consultar.java / .form      → Tela para consultar presença
├── Form_Escanear.java / .form       → Tela de escaneamento de QR Code
├── alunos_tabela_alunos.sql         → Script de criação da tabela de alunos
├── alunos_tabela_presenca.sql       → Script de criação da tabela de presenças
```

## 🛠️ Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/sistema-presenca.git
   ```

2. **Abra o projeto no NetBeans.**

3. **Configure o banco de dados MySQL:**
   - Crie o banco de dados.
   - Execute os scripts:
     - `alunos_tabela_alunos.sql`
     - `alunos_tabela_presenca.sql`

4. **Atualize a string de conexão no código Java com os dados do seu MySQL.**

5. **Compile e execute o projeto.**

## 🧪 Exemplos de Uso

- A **escola** cadastra os alunos na tela de Cadastro.
- O **aluno** acessa o sistema com seu RA e senha.
- Ele gera um **QR Code** com seus dados e mostra na tela.
- Ao chegar ou sair, ele **escaneia** o QR Code em um leitor (ou webcam) para registrar a presença.
- Os dados ficam armazenados com **data e hora**.

## 🔐 Segurança

- Os dados são autenticados via login com RA e senha.
- Cada QR Code contém informações criptografadas.

## 📌 Observações

- O projeto está em fase inicial. Algumas funcionalidades podem ser aprimoradas.
- Sugestões e contribuições são bem-vindas!

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
