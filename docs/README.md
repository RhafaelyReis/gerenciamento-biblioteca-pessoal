# 📚 Gerenciamento de Biblioteca Pessoal

## 🚧 Status do Projeto

**🔄 EM ANDAMENTO** - O projeto está sendo desenvolvido e aprimorado continuamente.

## Descrição do Projeto

Sistema de gerenciamento de biblioteca pessoal desenvolvido em Java com interface gráfica Java Swing. Esta solução foi criada para ajudar usuários a gerenciar sua coleção pessoal de livros, ebooks e audiobooks de forma simples e intuitiva, oferecendo funcionalidades completas para catalogação, avaliação e acompanhamento de leitura.

## ✨ Funcionalidades Principais

### 🔖 Gerenciamento de Itens
- **Cadastro completo**: Adicione livros físicos, ebooks e audiobooks à sua biblioteca
- **Edição flexível**: Modifique informações dos itens já cadastrados
- **Remoção segura**: Exclua itens da biblioteca quando necessário
- **Controle de leitura**: Marque itens como lidos/não lidos
- **Sistema de avaliação**: Classifique obras lidas de 1 a 5 estrelas

### 🔍 Busca e Filtros
- **Busca inteligente**: Encontre títulos por nome, autor ou gênero
- **Filtros por tipo**: Separe livros físicos, ebooks e audiobooks
- **Filtros por avaliação**: Visualize apenas itens com classificações específicas
- **Resultados organizados**: Interface clara para navegar pelos resultados

### 📊 Estatísticas da Coleção
- **Visão geral**: Total de itens na biblioteca
- **Progresso de leitura**: Quantidade de obras já lidas
- **Análise por categoria**: Distribuição entre livros, ebooks e audiobooks
- **Média de avaliações**: Acompanhe suas preferências literárias

## 🛠️ Tecnologias Utilizadas

- **Java** - Linguagem de programação principal
- **Java Swing** - Interface gráfica do usuário
- **Paradigma Orientado a Objetos** - Estrutura do código

## 📋 Pré-requisitos

- Java JDK 8 ou superior
- IDE Java (recomendado: IntelliJ IDEA, Eclipse ou NetBeans)

## 🚀 Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/RhafaelyReis/gerenciamento-biblioteca-pessoal.git
```

2. Compile o projeto:
```bash
javac -cp . *.java
```

3. Execute a aplicação:
```bash
java Main
```

## 📝 Validações e Tratamento de Erros

O sistema inclui validações robustas para garantir a integridade dos dados:

### Validações de Entrada
- **Título obrigatório**: Impede cadastro de itens sem título
- **Ano de publicação**: Não aceita datas futuras
- **Páginas válidas**: Número de páginas deve ser positivo
- **Duração de audiobook**: Não aceita duração zerada
- **Avaliações consistentes**: Apenas livros lidos podem ser avaliados (1-5 estrelas)
- **Formatos válidos**: Validação de formatos para ebooks e audiobooks

### Tratamento de Exceções
- **ItemNaoEncontradoException**: Quando buscas não retornam resultados
- **DadosInvalidosException**: Para entradas que não atendem aos critérios
- **AvaliacaoInvalidaException**: Para avaliações inconsistentes

## 🎯 Regras de Negócio

- **Avaliação condicionada**: Apenas itens marcados como "lidos" podem receber avaliações
- **ID automático**: Sistema gera identificadores únicos automaticamente
- **Estatísticas dinâmicas**: Dados atualizados em tempo real através do botão "Gerar Estatísticas"

## 📊 Estatísticas Disponíveis

- Total de itens cadastrados
- Quantidade de itens lidos vs. não lidos
- Distribuição por tipo de mídia (livros, ebooks, audiobooks)
- Média geral de avaliações da biblioteca

## 🔄 Fluxo de Uso

1. **Cadastro**: Adicione novos itens à biblioteca
2. **Organização**: Use filtros e busca para encontrar itens específicos
3. **Acompanhamento**: Marque itens como lidos conforme avança
4. **Avaliação**: Classifique obras lidas para referência futura
5. **Análise**: Visualize estatísticas para insights sobre seus hábitos de leitura

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para:
- Reportar bugs
- Sugerir melhorias
- Enviar pull requests

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 👩‍💻 Desenvolvedora

Projeto desenvolvido como parte do aprendizado em programação Java e desenvolvimento de interfaces gráficas.

---

*Organize sua biblioteca pessoal de forma inteligente e nunca mais perca o controle das suas leituras!* 📖✨
