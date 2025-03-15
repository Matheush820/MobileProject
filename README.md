📤 Enviar arquivo para o GitHub:
Inicializar o repositório (se ainda não fez):
git init
Conectar ao seu repositório no GitHub (se ainda não fez):

git remote add origin https://github.com/seu-usuario/seu-repositorio.git
Adicionar o arquivo ao controle de versão:

git add nome-do-arquivo ou git add . para add tudo

Fazer o commit com uma mensagem:
git commit -m "Adiciona nome-do-arquivo"

Enviar para o GitHub:

Se o branch principal for main:
git push origin main

Se o branch principal for master:

git push origin master

📥 Baixar arquivo do GitHub:
Clonar o repositório (se for a primeira vez):

git clone https://github.com/seu-usuario/seu-repositorio.git
Entrar na pasta do repositório:

cd seu-repositorio
Atualizar o repositório para pegar as últimas mudanças:

Se o branch principal for main:
git pull origin main

Se o branch principal for master:
git pull origin master
