# TooVendendo
Desenvolvimento de uma aplicação Android para o atendimento de uma loja de departamentos conforme requisitos apontados 


Trabalho – 1o Bimestre

1. Uma loja de departamentos necessita de uma aplicativo para seus vendedores
utilizarem para cadastrar suas vendas, onde possam já fazer pedido da venda e repassar
para o cliente. Para isso é necessário possuir as seguintes funcionalidades:
a. Possuir campos para informar o nome e cpf do cliente;
b. Campos para informar o item, quantidade e valor unitário, e um botão para adicionar o
item no pedido;
c. Uma lista utilizando para exibir os itens adicionados ao pedido;
d. Um campo informando o valor total e total de itens adicionados;
e. Abaixo da lista um campo para informar a condição de pagamento (á vista ou á prazo);
f. Caso selecionado a prazo, deverá exibir um campo para informar a quantidade de
parcelas, e abaixo uma lista informando o valor de cada parcela;
g. Um campo para exibir o valor total do pedido, onde para vendas a vista deverá ter um
desconto de 5% no valor total, e a prazo adicionar o acréscimo de 5% no valor total;
h. Um botão para concluir o pedido, exibindo uma mensagem informando que o pedido
de venda foi cadastrado com sucesso;
Características técnicas:

1. Para cada processo deverá validar se o campos digitados não estão nulos;
2. Validar campo de valores para não deixar adicionar valores < = 0 (zero);
3. Deverá ter um campo para gerar o código do pedido, ao digitar o código do
pedido deverá ser capaz de retornar as informações do pedido em questão;
4. Código do pedido deve ser gerado automaticamente;
5. Ao concluir o pedido deverá ser exibido uma mensagem informando que o
pedido foi cadastrado com sucesso exibindo o número do pedido (Toast);
6. Ao concluir o pedido todos os campos deverão ser "limpos", e gerado um novo
código de pedido.
7. As listas devem ser utilizados TextView (concatenar string com \n para quebrar
linhas);
8. Por fim, o aplicativo deve ser projetado com uma interface de usuário atraente
e intuitiva, seguindo as diretrizes de design de material do Android, e ser
responsivo em diferentes tamanhos de tela.