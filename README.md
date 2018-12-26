---
layout: default
---

<body>
<h1 style="color:blue;">Hardware Hamlet</h1>
<h2>Explicação do tema</h2>
<p> O Hardware Hamlet é uma aplicação android e web que permite ao utilizador criar a sua configuração de computador, 
sem ter de se preocupar com problemas de compatibilidade, e ainda saber que componentes são compatíveis com o seu computador fixo, caso queira fazer update do seu hardware. 
É também possível postar as configurações online, de modo a ajudar outros utilizadores que não sejam tão entendidos. É possível avaliar essas configurações, e comentar.
 É também possível saber o preço de cada configuração, com base em lojas portuguesas.</p>
<p> Os dados das aplicações são atualizados através da comunidade: um utilizador nota que há novos componentes e fornece essa informação, 
que será introduzida na base de dados da empresa. Para motivar a comunidade a participar na atualização da base de dados da empresa, será introduzido um sistema
 de reputação onde os utilizadores são recompensados com títulos de medalhas pela sua participação.</p>
<h2>Repartição dos objectivos</h2>
<h3>Objetivos primários:</h3>
<ol>
	<li>- Aplicação Android e web, que partilham dados entre si</li>
    <li>- Login/Registo, com login com "remember me"</li>
    <li>- Contas de utilizador/administrador</li>
    <li>- Configuração de um computador consoante a compatibilidade de componentes</li>
    <li>- Possibilidade de o utilizador postar uma configuração que tenha feito</li>
    <li>- Possibilidade de os utilizadores atualizarem os componentes existentes na base de dados, e acrescentarem novos componentes (apenas através da página web)</li>
</ol>
<h3>Objetivos intermédios:</h3>
<ol>
    <li>- Possibilidade de vários utilizadores classificarem uma configuração de outro utilizador</li>
    <li>- Possibilidade de vários utilizadores comentarem outras configurações</li>
</ol>
<h3>Objetivos avançados</h3>
<ol>
    <li>- Sistema de rankings funcional, para os utilizadores que contribuam para a comunidade ao adicionarem componentes ou na criação
        de configurações</li>
    <li>- Implementação dos preços de lojas portuguesas nos componentes</li>
    <li>- Report de informação falsa colocada por outros utilizadores</li>
</ol>

<h2>Requisitos mínimos para o Servidor(UBUNTO)</h2>
<ol>
	<li>Sistema operativo: Ubunto server, última versão LTS</li>
	<li>RAM: entre 1,5 a 3GB de RAM</li>
	<li>Espaço de armazenamento: entre 10 a 20GB</li>
	<li>Velocidade do precessador: mais de 1,8GHz</li>
</ol>
<h3>Razão da escolha dos requisitos</h3>
<p>Escolhemos o Ubunto server, a ultima versão LTS disponível, para garantir maior longevidade do nosso servidor, sem precisar de uma atualização do SO. <br>
Pensamos que a memória RAM seja suficiente, pois não é necessário haver mais recursos para o tipo de aplicações que vamos criar. <br>
Talvez, mais tarde, precisaremos de aumentar o espaço de armazenamento, dependendo do tamanho da base de dados.</p>

<h2>Logs dos Task Managers</h2>
<h3>1º Objetivo - Elaboração de uma apresentação da nossa aplicação.</h3>
<p><strong>Task Manager -</strong> Alexandre Amorim</p>
<p><strong>Descrição da tarefa:</strong></p>
<ol>
    <li>Criação de um PowerPoint da apresentação; </li>
    <li>Atribuição dos slides a apresentar aos membros do grupo;</li>
    <li>Discução e conclusão das ideias para a apresentação;</li>
    <li>Realização da apresentação;</li>
    <li>Aquisição de ideias com base nas perguntas feitas pela turma e professor</li>
</ol>
<p><strong>Status:</strong> Concluído.</p>
<p><strong>Auto-avaliação:</strong> 5(0-5).</p>

<h3>2º Objetivo - Criar uma conta GitHub e um repositório partilhado.</h3>
<p><strong>Task Manager -</strong> Rodrigo Serra</p>
<p><strong>Descrição da tarefa:</strong></p>
<p>Os 3 membros começaram por criar uma conta no github.
    O task manager, eu, ficou encarregue de criar o repositório git e colocar os restantes membros como colaboradores do repositório.
    Após essa tarefa, procedeu-se para a criação do ficheiro readme onde ficará registada toda a informação do projeto, incluindo a sua evolução.
</p>
<p><strong>Status:</strong> Concluído.</p>
<p><strong>Auto-avaliação:</strong> 3(0-5).</p>

<h3>3º Objetivo - No ficheiro readme, introduzir a informação sobre o projeto, lista de objetivos e os logs dos líderes de grupo.</h3>
<p><strong>Task Manager -</strong> Mário Miguel</p>
<p><strong>Descrição da tarefa:</strong>
<p>Nesta tarefa introduzimos no ficheiro readme, em formato HTML, a ideia do projeto e uma lista de objetivos, dividida entre objetivos primários, intermédios e avançados.<br>
    Introduzimos os logs das tarefas concluidas até à data, onde cada log é da autoria do respetivo task manager.
</p>
<p><strong>Status:</strong> Concluído.</p>
<p><strong>Auto-avaliação:</strong> 4(0-5).</p>

<h3>4º Objetivo - Estrutura e design das aplicações</h3>
<p><strong>Task Manager -</strong> Mário Miguel </p>
<p><strong>Descrição da tarefa:</strong></p>
<p>Começámos por definir o design geral da aplicação web.</p>
<p>Após terminar o design da aplicação web (ainda por colocar no repositório de imagens), procedemos para o design da aplicação Android.</p>
<p>Terminamos a estruturação da aplicação android.</p>
<p>Após umas dicas e correções fornecidas pelos professores, fizemos um aperfeiçoamento do design inicial, tendo mais em conta a UX. </p>
<p><strong>Status:</strong> Terminado.</p>
<p><strong>Auto-avaliação:</strong> 3(0-5).</p>

<h3>5º Objetivo - Estruturação da base de dados</h3>
<p><strong>Task Manager -</strong> Alexandre Amorim </p>
<p><strong>Descrição da tarefa:</strong></p>
<p>Nesta tarefa começámos por destinguir todos os atributos do nosso projeto para criar as tabelas da base de dados, depois com a ajuda dos professores atribuímos as suas relações para evitar possíveis redundâncias.</p> 
<p><strong>Status:</strong> Terminado.</p>
<p><strong>Auto-avaliação:</strong> 4(0-5).</p>

<h3>6º Objetivo - Configuração do servidor real</h3>
<p><strong>Task Manager -</strong> Rodrigo Serra </p>
<p><strong>Descrição da tarefa:</strong></p>
<p>Configuração dos serviços Apache2, MySQL e PHP7.0 no servidor fornecido pela instituição, incluindo algumas medidas de segurança</p>
<p><strong>Status:</strong> Terminado.</p>
<p><strong>Auto-avaliação:</strong> 5 (0-5).</p>

<h3>7º Objetivo - Escolha de um template para o website</h3>
<p><strong>Task Manager -</strong> Mário Miguel </p>
<p><strong>Descrição da tarefa:</strong></p>
<p>Utilizando websites de templates gratuitos, procedemos para a escolha de um template para o nosso website.</p>
<p>Estudámos a framework Smartie de modo a implementar o código php que iremos desenvolver mais tarde.</p>
<p><strong>Status:</strong> Em curso.</p>
<p><strong>Auto-avaliação:</strong> (0-5).</p>

<h3>8º Objetivo - Desenvolvimento da API para a APP Android</h3>
<p><strong>Task Manager -</strong> Rodrigo Serra </p>
<p><strong>Descrição da tarefa:</strong></p>
<p>Utilizando a linguagem PHP e os exemplos desenvolvidos nas aulas de Programação para a Web, procedemos para o desenvolvimento da API que iremos utilizar na Aplicação Android que iremos desenvolver.</p>
<p><strong>Status:</strong> Em curso.</p>
<p><strong>Auto-avaliação:</strong> (0-5).</p>

    .....
