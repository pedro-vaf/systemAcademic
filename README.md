# 🎓 Sistema Acadêmico em Java (Maven)

Projeto desenvolvido por **Pedro Vitor Aquino Ferreira** como exemplo de aplicação Java com uso do Maven e geração de certificados em PDF.

---

## ✅ Pré-requisitos

Certifique-se de ter os seguintes itens instalados em sua máquina:

- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (versão 17 ou superior)
- [Apache Maven](https://maven.apache.org/)
- Terminal de comando (CMD, PowerShell, Git Bash ou terminal Linux/macOS)

---

## 🚀 Como executar o projeto

### 1. Faça o download e extração do projeto

Após baixar o arquivo `.zip`, extraia para uma pasta de sua preferência.

### 2. Acesse o diretório do projeto

Abra o terminal e navegue até a pasta onde se encontra o arquivo `pom.xml`.

```bash
cd caminho/para/academicEvent
```

### 3. Compile o projeto

Use o Maven para compilar:

```bash
mvn compile
```

### 4. Execute a aplicação

Após a compilação, rode o sistema com:

```bash
mvn exec:java
```

---

## 📄 Geração de Certificado (PDF)

Ao executar o sistema, um certificado é gerado automaticamente.  
Ele será salvo em uma subpasta chamada `output`, criada no mesmo diretório onde o projeto está sendo executado.

---

## 🧰 Tecnologias e Bibliotecas

- **Java 21**
- **Maven**
- **OpenPDF** – Usada para geração dos certificados em PDF  
  A lógica de geração está encapsulada na classe `CertificateLogic`.

---

## ✍️ Autor

Pedro Vitor Aquino Ferreira  
Sistema Acadêmico em Java – Projeto acadêmico
