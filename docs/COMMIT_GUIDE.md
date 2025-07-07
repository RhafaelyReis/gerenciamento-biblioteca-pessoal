# 📋 Guia de Commits Semânticos - Biblioteca Pessoal

## 🎯 Formato Padrão
```
<emoji> <tipo>(<escopo>): <descrição>

[corpo opcional]
```

**Exemplo:**
```
✨ feat(cadastro): implementar validação de ano de publicação

- Adicionar validação para evitar anos futuros
- Criar exceção DadosInvalidosException
- Implementar testes unitários

Resolves: #23
```

## 📊 Tabela de Tipos de Commit

| Emoji | Tipo | Descrição | Exemplo |
|-------|------|-----------|---------|
| ✨ | `feat` | Nova funcionalidade | `✨ feat(biblioteca): adicionar cadastro de audiobooks` |
| 🐛 | `fix` | Correção de bug | `🐛 fix(validacao): corrigir validação de ano futuro` |
| 📚 | `docs` | Documentação | `📚 docs(readme): atualizar instruções de instalação` |
| 💄 | `style` | Formatação, estilo | `💄 style(ui): ajustar layout do formulário` |
| ♻️ | `refactor` | Refatoração de código | `♻️ refactor(busca): otimizar algoritmo de pesquisa` |
| ⚡ | `perf` | Melhoria de performance | `⚡ perf(estatisticas): otimizar cálculo de médias` |
| ✅ | `test` | Testes | `✅ test(validacao): adicionar testes de entrada` |
| 🔧 | `build` | Build, dependências | `🔧 build: atualizar versão do Java` |
| 👷 | `ci` | Integração contínua | `👷 ci: configurar GitHub Actions` |
| 🔨 | `chore` | Tarefas de manutenção | `🔨 chore: organizar imports` |
| ⏪ | `revert` | Reverter commit | `⏪ revert: reverter mudanças no layout` |

## 🎨 Emojis Complementares

### 🔧 Configuração e Setup
| Emoji | Uso | Exemplo |
|-------|-----|---------|
| 🎉 | `init` | Commit inicial | `🎉 init: configuração inicial do projeto` |
| 🔧 | `config` | Arquivos de configuração | `🔧 config: configurar properties da aplicação` |
| 🌱 | `seed` | Dados iniciais | `🌱 seed: adicionar dados de exemplo` |

### 🛠️ Desenvolvimento
| Emoji | Uso | Exemplo |
|-------|-----|---------|
| 🚀 | `deploy` | Deploy/Release | `🚀 deploy: versão 1.0.0` |
| 🔥 | `remove` | Remover código/arquivos | `🔥 remove: excluir classe não utilizada` |
| 📦 | `package` | Empacotamento | `📦 package: criar distribuição jar` |
| 🔀 | `merge` | Merge de branches | `🔀 merge: branch feature/estatisticas` |

### 🐛 Correções Específicas
| Emoji | Uso | Exemplo |
|-------|-----|---------|
| 🚑 | `hotfix` | Correção crítica | `🚑 hotfix: corrigir crash ao salvar` |
| 🔒 | `security` | Segurança | `🔒 security: validar entrada de dados` |
| 🩹 | `patch` | Correção simples | `🩹 patch: corrigir texto do botão` |

### 📱 Interface e UX
| Emoji | Uso | Exemplo |
|-------|-----|---------|
| 🎨 | `ui` | Interface do usuário | `🎨 ui: redesenhar tela principal` |
| 📱 | `responsive` | Responsividade | `📱 responsive: ajustar layout mobile` |
| ♿ | `accessibility` | Acessibilidade | `♿ accessibility: adicionar atalhos de teclado` |

## 📝 Exemplos Práticos para o Projeto

### ✨ Funcionalidades
```bash
✨ feat(cadastro): implementar cadastro de ebooks
✨ feat(busca): adicionar filtro por gênero
✨ feat(estatisticas): criar relatório de leitura
```

### 🐛 Correções
```bash
🐛 fix(validacao): corrigir validação de páginas negativas
🐛 fix(avaliacao): impedir avaliação de livros não lidos
🐛 fix(busca): resolver problema de busca vazia
```

### 💄 Interface
```bash
💄 style(form): ajustar espaçamento do formulário
🎨 ui(tabela): melhorar visualização da lista
💄 style(botoes): padronizar cores dos botões
```

### 📚 Documentação
```bash
📚 docs(readme): adicionar seção de instalação
📚 docs(codigo): documentar métodos principais
📚 docs(exceptions): documentar tratamento de erros
```

### ♻️ Refatoração
```bash
♻️ refactor(model): reorganizar classes de domínio
♻️ refactor(dao): simplificar acesso aos dados
♻️ refactor(controller): melhorar separação de responsabilidades
```

## 📏 Boas Práticas

### ✅ Faça
- Use o presente do indicativo: "adicionar" não "adicionado"
- Mantenha a primeira linha com até 72 caracteres
- Use escopos específicos: `(cadastro)`, `(busca)`, `(ui)`
- Seja descritivo mas conciso

### ❌ Evite
- Usar pontos finais na descrição
- Ser muito vago: "corrigir bug" → "corrigir validação de ano futuro"
- Misturar tipos diferentes no mesmo commit
- Commits muito grandes

## 🔄 Fluxo de Trabalho Sugerido

```bash
# Funcionalidade nova
✨ feat(audiobook): adicionar suporte a audiobooks

# Correção de bug
🐛 fix(validacao): corrigir validação de duração zerada

# Documentação
📚 docs(readme): atualizar seção de funcionalidades

# Refatoração
♻️ refactor(exceptions): reorganizar hierarquia de exceções

# Estilo/UI
💄 style(tabela): melhorar layout da biblioteca
```

## 🏷️ Tags de Versionamento

Combine com versionamento semântico:
```bash
🚀 release: versão 1.0.0
🚀 release: versão 1.1.0 - adicionar estatísticas
🚀 release: versão 1.0.1 - correções de bugs
```

## 📋 Escopos Específicos do Projeto

### **Escopos Principais:**
- `model` - Classes de domínio (Livro, Ebook, Audiobook)
- `view` - Interface gráfica (panels, dialogs, components)
- `controller` - Controladores da aplicação
- `service` - Lógica de negócio e validações
- `dao` - Acesso aos dados
- `exception` - Exceções customizadas
- `util` - Classes utilitárias

### **Escopos Funcionais:**
- `cadastro` - Funcionalidades de cadastro
- `busca` - Sistema de busca e filtros
- `estatisticas` - Relatórios e métricas
- `validacao` - Validações de entrada
- `avaliacao` - Sistema de avaliação

### **Escopos Técnicos:**
- `config` - Configurações
- `resources` - Recursos (imagens, arquivos)
- `test` - Testes unitários
- `docs` - Documentação

## 🎯 Commits por Fase do Projeto

### **Fase 1: Estrutura Base**
```bash
🎉 init: configuração inicial do projeto
📁 structure: criar estrutura de pastas
🔧 config: configurar gitignore e dependências
```

### **Fase 2: Domínio**
```bash
✨ feat(model): implementar classe ItemBiblioteca
✨ feat(model): adicionar classes Livro, Ebook e Audiobook
✨ feat(exception): criar exceções customizadas
```

### **Fase 3: Lógica de Negócio**
```bash
✨ feat(service): implementar BibliotecaService
✨ feat(service): adicionar validações de entrada
✨ feat(dao): criar camada de acesso aos dados
```

### **Fase 4: Interface**
```bash
✨ feat(view): implementar tela principal
✨ feat(view): criar formulário de cadastro
✨ feat(controller): conectar view com service
```

### **Fase 5: Funcionalidades**
```bash
✨ feat(busca): implementar sistema de busca
✨ feat(estatisticas): adicionar relatórios
✨ feat(avaliacao): implementar sistema de notas
```

## 🚫 Commits a Evitar

### **❌ Mensagens ruins:**
```bash
fix: correção
update: mudanças
feat: nova funcionalidade
```

### **✅ Mensagens boas:**
```bash
🐛 fix(validacao): corrigir validação de ano futuro
♻️ refactor(service): otimizar busca por título
✨ feat(estatisticas): adicionar média de avaliações
```

## 📈 Exemplo de Histórico Ideal

```bash
🎉 init: configuração inicial do projeto
📁 structure: criar estrutura de pastas MVC
✨ feat(model): implementar hierarquia ItemBiblioteca
✨ feat(exception): adicionar exceções customizadas
✨ feat(service): implementar BibliotecaService com validações
✨ feat(dao): criar persistência de dados
✨ feat(view): implementar interface principal
✨ feat(controller): conectar camadas MVC
✨ feat(busca): adicionar sistema de busca e filtros
✨ feat(estatisticas): implementar relatórios
🐛 fix(validacao): corrigir validação de páginas negativas
💄 style(ui): melhorar layout do formulário
📚 docs(readme): atualizar documentação
🚀 release: versão 1.0.0 - sistema completo
```

---

*Commits bem estruturados facilitam o entendimento do histórico e colaboração em equipe!* 🚀✨