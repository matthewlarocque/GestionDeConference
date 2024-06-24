# Système de gestion de conférence (Meeting management system)

#### Description

Il s'agit d'un système intelligent de gestion de réunions basé sur Springboot et Vue 2. Le principal avantage est la séparation des extrémités frontales et dorsales.

It is an intelligent meeting management system based on Springboot and Vue 2. The major advantage is the separation of the front and back ends.

#### Architecture Logicielle / Software Architecture

Spring Boot & Vue

#### Installation

##### Français
1.  Ouvrez le dossier "Server" en tant que projet Intellij IDEA, construisez les artefacts et exécutez enfin la fonction main() sous `ManagerApplication` pour mettre en service le backend.
2.  Configurez le miroir NPM sur Taobao si vous êtes un utilisateur chinois. Ligne de commande:
`
npm config set registry https://registry.npmjs.org
`
3.  Installez les dépendances dans le dossier "FrontEnd". Ligne de commande:
`
npm install --ignore-scripts
`
4.  Exécutez le frontend. Ligne de commande:
`
npm run serve
`

##### English
1.  Open "Server" folder as an Intellij IDEA project, build the artifacts, and finally run main() function under `ManagerApplication` to bring up backend service.
2.  Set NPM mirror to Taobao if you are Chinese user. Command Line:
`
npm config set registry https://registry.npmjs.org
`
3.  Install dependencies under "FrontEnd" folder. Command Line:
`
npm install --ignore-scripts
`
4.  Run frontend. Command Line:
`
npm run serve
`
