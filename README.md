# Eler test Project

Para ver o resultado da atividade tem de rodar a aplicação na classe Application.

## Sobre a saída

### File
Priemiro temos a leitura do de um arquivo, mostrando o que está nele.

### Grafo

Depois temos a transformação do codigo para um grafo. 
Ondem limpa-se alguns parametros no arquvio que nao são importantes no momentos, como os imports
E cria-se um Nó com os parametros relevantes. Onde uma folha sempre será um return.
A apresentação é feita em ordem, a partir da raiz e seus filhos da esquerda e direitra.

### Identificar caminhos

É apresentado todos os possiveis caminhos da raiz até as folhas

### Sequência de Restrições

É aprensatod as condições para seguir cada um dos caminhos da raiz até as folhas

### Dados para o teste

Para cada parametro do método é gerado dados de testes para seguir cada caminho.
Quando possível é gerado um dado no valorLimite e outro acima.

### Casos de Teste

É combinado, sem repetição, os dados para o teste.Assim, gera-se todos os possiveis casos de testes para
percorrer todas as linhas e possibilidades do código inicial.

### Classe de Test

É finalmente é gerado uma classe de teste em java para percorrer o código dado.